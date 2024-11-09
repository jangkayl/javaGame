package world1;

import java.util.Random;

public abstract class FightLogic {
    protected Random rand = new Random();
    private PlayerProgress playerProgress = GameLogic.playerProgress;
    private Player player;
    private StreetFighter opponent;
    private Tournament tournament = new Tournament();
    private int[] opponentChoices = new int[3];
    private String[][] attackOption = {{"Jab", "Damage: 10 | Stamina: -5"}, 
                                    {"Hook", "Damage: 15 | Stamina: -7"}, 
                                    {"Block", "Stamina: +5"}, 
                                    {"Uppercut", "Damage: 20 | Stamina: -10"},
                                    {"The Body Breaker", ""}};
    private String[][] comboOption = {{"Lead Body Shot", "Damage: 15 | Stamina: -7"},
                                    {"Cross to the Ribs", "Damage: 20 | Stamina: -9"},
                                    {"Finishing Uppercut", "Damage: 25 | Stamina: -14"}};
    private String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut"};
    private String[] opponentAttacks;

    public FightLogic(Player player, String[] opponentAttacks) {
        this.player = player;
        this.opponentAttacks = opponentAttacks;
    }

    public abstract String getOpponentName();

    public Player getPlayer(){
        return player;
    }

    public StreetFighter getOpponent(){
        return opponent;
    }

    public int[] getOpponentChoices(){
        return opponentChoices;
    }

    public void setOpponentChoices(int[] opponentChoices){
        this.opponentChoices = opponentChoices;
    }

    public void setOpponent(StreetFighter opponent) {
        this.opponent = opponent;
    }

    public String getPlayerAttackByChoice(int choice){
        return playerAttacks[choice];
    }
    
    public void fightLoop() {
        player.setStage(player.getStage());
        GameLogic.gameData.saveGame();
        player.setOpponent(opponent);
        GameLogic.clearConsole();
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("Round " + playerProgress.getRound(), 40));
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("You are fighting " + getOpponentName() + "!", 40));
        System.out.println();
        GameLogic.printSeparator(40);
        printStats();
        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            if (player.getHp() <= 0) {
                System.out.println("\n" + player.getName() + " is knocked out! " + getOpponentName() + " wins!");
                handleLoss();
                return;
            } else if (opponent.getHp() <= 0) {
                System.out.println("\n" + getOpponentName() + " is knocked out! " + player.getName() + " wins!");
                handleWin();
                return;
            }
        }
        GameLogic.pressAnything();
    }

    protected void selectAttack() {
        int[] choices = new int[3];
        String input = "";

        System.out.println();
        System.out.println("You're the first one to attack!");
        System.out.println("Select 3 combos:");
    
        for (int i = 0; i < attackOption.length; i++) {
            if(i < attackOption.length - 1){
                System.out.println((i + 1) + ") " + attackOption[i][0] + " - " + attackOption[i][1]);
            } else {
                System.out.println((i + 1) + ") " + attackOption[i][0]);
            }
        }

        for (int i = 0; i < comboOption.length; i++) {
            System.out.println("\t\t- " + comboOption[i][0] + " - " + comboOption[i][1]);
        }

        System.out.println("\n(0) Check " + opponent.getName() + "'s combo counters");
    
        System.out.print("-> ");
        while (true) {
            input = GameLogic.scan.nextLine();

            if(input.equals("0")){
                counterInfos(opponent.getName());
                GameLogic.pressAnything();
                GameLogic.clearConsole();
                GameLogic.printSeparator(40);
                System.out.println(GameLogic.centerText("Round " + playerProgress.getRound(), 40));
                GameLogic.printSeparator(40);
                System.out.println(GameLogic.centerText("You are fighting " + getOpponentName() + "!", 40));
                System.out.println();
                GameLogic.printSeparator(40);
                return;
            }

            if (input.equals("5")) {
                if(player.getStamina() - 30 >= 0){
                    input = "567";
                    choices = new int[]{5, 6, 7}; 
                    break;
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina for this combo!");
                    System.out.println("You may use 3 Blocks as your combo to regain stamina");
                    continue;
                }
            } else if (input.contains("5") || input.contains("6") || input.contains("7")) {
                System.out.println();
                System.out.println("You can use your special combo by entering '5'!");
                System.out.println("If you want to proceed with the combo, just enter '5'.");
                System.out.println();
                continue; 
            }

            if(isValidCombo(input, player.getStamina()) == 1){
                System.out.println(GameLogic.centerBox("Please enter a valid combo (e.g., 123):", 50));
                continue;
            } else if(isValidCombo(input, player.getStamina()) == 2) {
                String message = player.getName() + " doesn't have enough stamina for this combo!\n" +
                                                "You may use 3 Blocks as your combo to regain stamina";
                System.out.println(GameLogic.centerBox(message, 60));
                continue;
            }
            break;
        }

        for (int i = 0; i < 3; i++) {
            // Adjust the character input value correctly
            choices[i] = Character.getNumericValue(input.charAt(i)) - 1; // Use input directly
        
            // Generate opponentChoices with higher probability for 1 to 4
            int randomValue = rand.nextInt(10); // Generate a random number between 0 and 9
        
            // Higher probability for numbers 1 to 4
            if (randomValue < 8) { // 80% chance
                opponentChoices[i] = rand.nextInt(4); // 0, 1, 2, or 3 (which correspond to 1 to 4)
            } else { // 40% chance
                opponentChoices[i] = 4 + rand.nextInt(3); // 4, 5, or 6
            }
        }
        
        // Check for opponentChoices being >= 4
        for (int i = 0; i < 3; i++) {
            if (opponentChoices[i] >= 4) {
                opponentChoices = new int[]{4, 5, 6}; 
                break;
            }
        }

        opponentValid(opponentChoices);

        System.out.println();
        System.out.println("You've selected:\t\tOpponent selected:");
        for(int i = 0; i < 3; i++){
            System.out.println(playerAttacks[choices[i]] + "     \t\t\t" + opponentAttacks[opponentChoices[i]]);
        }

        System.out.println();
        printFight(choices, opponentChoices);
    }

    protected int isValidCombo(String input, int stamina) {
        if (input.length() != 3) {
            return 1;
        }

        int tempStamina = stamina;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || c < '1' || c > '7') {
                return 1;
            }
            
            int move = Character.getNumericValue(c);
            int staminaCost;
            
            switch (move) {
                case 1: // Jab
                    staminaCost = 5;
                    break;
                case 2: // Hook
                    staminaCost = 7;
                    break;
                case 3: // Block (assuming no stamina cost)
                    staminaCost = 0;
                    break;
                case 4:  // Uppercut 
                    staminaCost = 10;
                    break;
                case 5:
                    staminaCost = 7;
                    break;
                case 6:
                    staminaCost = 9;
                    break;
                case 7:
                    staminaCost = 14;
                    break;
                default:
                    return 1;
            }

            if (tempStamina - staminaCost < 0) {
                return 2;
            }
            tempStamina -= staminaCost;
        }
        return 0;
    }

    public void winnerReward(){
        if(playerProgress.getPlayerWins() != 3){
            System.out.println();
            System.out.print(GameLogic.centerBox("Congratulations! You've won the match!", 50));
        }
    }
    
    protected void handleWin() {
        player.setIsLose(false);
        winnerReward();
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if(playerProgress.getPlayerWins() != 3){
            playerProgress.setPlayerWins(playerProgress.getPlayerWins() + 1);
        }
        tournament.printStanding();
        GameLogic.gameData.saveGame();
    }

    protected void handleLoss() {
        player.setIsLose(true);
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if (playerProgress.getOpponentWins() != 3) {
            playerProgress.setOpponentWins(playerProgress.getOpponentWins() + 1);
        }
        tournament.printStanding();
        GameLogic.gameData.saveGame();
    }

    private void resetFighterStats() {
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
    }

    private void printStats(){
        System.out.println();
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("*"+ player.getName() +"*" , "*"+ opponent.getName()+"*", 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("HP       " + player.getHp() + "/" + player.getMaxHp(), "HP       " + opponent.getHp() + "/" + opponent.getMaxHp(), 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("Stamina   " + player.getStamina() + "/" + player.getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30)));
        System.out.println();
    }

    private void counterInfos(String name){
        GameLogic.clearConsole();
        if(opponent.getName() == "Rico Ramirez"){
            GameLogic.printHeading("\tRico Ramirez Combo Counter:");
            System.err.println("(1) Cross < Uppercut");
            System.err.println("(2) Rear Uppercut < Block");
            System.err.println("(3) Lead Hook < Jab");
        } else if(opponent.getName() == "Oscar Lopez"){
            GameLogic.printHeading("\tOscar Lopez Combo Counter:");
            System.err.println("(1) Quick Jab < Uppercut");
            System.err.println("(2) Cross < Uppercut");
            System.err.println("(3) Power Punch < Block");
        } else if(opponent.getName() == "Ishmael Tetteh"){
            GameLogic.printHeading("\tIshmael Tetteh Combo Counter:");
            System.err.println("(1) Right Uppercut < Block");
            System.err.println("(2) Left Hook < Jab");
            System.err.println("(3) Right Cross < Uppercut");
        }
    }
    
    protected abstract void printFight(int[] choices, int[] opponentChoices);

    protected abstract void opponentValid(int[] opponentChoice);

    protected abstract int isCounter(int opponentMove, int playerMove);
}
