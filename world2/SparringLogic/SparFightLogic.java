package world2.SparringLogic;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;

public abstract class SparFightLogic {
    protected static Random rand = new Random();
    protected static PlayerProgress playerProgress = GameLogic.playerProgress;
    protected static Player player;
    protected static StreetFighter opponent;
    protected static int[] opponentChoices = new int[3];
    protected static String[][] attackOption = {{"Jab", "Damage: 10 | Stamina: -5"}, 
                                {"Hook", "Damage: 15 | Stamina: -7"}, 
                                {"Block", "Stamina: +5"}, 
                                {"Uppercut", "Damage: 20 | Stamina: -10"},
                                {"The Body Breaker", ""},
                                {"Elbow Strike", "Damage: 40 | Stamina: -25 | HP: -10"},
                                {"Head Butt", "Damage: 30 | Stamina: -20 | HP: -15"},
                                {"Low Blow", "Damage: 40 | Stamina: -25 | HP: -20"},
                            };
    protected static String[][] comboOption = {{"Lead Body Shot", "Damage: 15 | Stamina: -7"},
                                    {"Cross to the Ribs", "Damage: 20 | Stamina: -9"},
                                    {"Finishing Uppercut", "Damage: 25 | Stamina: -14"}};
    public static String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut", "Elbow Strike", "Head Butt", "Low Blow"};
    private static String[] opponentAttacks;

    public SparFightLogic(Player player, String[] opponentAttacks) {
        SparFightLogic.player = player;
        SparFightLogic.opponentAttacks = opponentAttacks;
    }

    public abstract String getOpponentName();

    public void setOpponent(StreetFighter opponent) {
        SparFightLogic.opponent = opponent;
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
    
        for (int i = 0; i < attackOption.length - 3; i++) {
            if(i < attackOption.length - 4){
                System.out.println((i + 1) + ") " + attackOption[i][0] + " - " + attackOption[i][1]);
            } else {
                System.out.println((i + 1) + ") " + attackOption[i][0]);
            }
        }

        for (int i = 0; i < comboOption.length; i++) {
            System.out.println("\t\t- " + comboOption[i][0] + " - " + comboOption[i][1]);
        }

        for (int i = attackOption.length - 3; i < attackOption.length; i++) {
            System.out.println((i + 1) + ") " + attackOption[i][0]);
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
            } else if (input.contains("5")) {
                System.out.println();
                System.out.println("You can use your special combo by entering '5'!");
                System.out.println("If you want to proceed with the combo, just enter '5'.");
                System.out.println();
                continue; 
            }

            if(isValidCombo(input, player.getStamina()) == 1){
                System.out.println("Please enter a valid combo (e.g., 123):");
                continue;
            } else if(isValidCombo(input, player.getStamina()) == 2) {
                System.out.println(player.getName() + " doesn't have enough stamina for this combo!");
                System.out.println("You may use 3 Blocks as your combo to regain stamina");
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
            if (randomValue < 5) { // 50% chance
                opponentChoices[i] = rand.nextInt(4); // 0, 1, 2, or 3 (which correspond to 1 to 4)
            } else { // 50% chance
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
            if (!Character.isDigit(c) || c < '0' || c > '9') {
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
                case 8:
                    staminaCost = 25;
                    break;
                case 9:
                    staminaCost = 20;
                    break;
                case 0:
                    staminaCost = 25;
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

    protected void handleWin() {
        winnerReward();
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if(playerProgress.getPlayerWins() != 3){
            playerProgress.setPlayerWins(playerProgress.getPlayerWins() + 1);
        }
        winnerReward();
        GameLogic.gameData.saveGame();
    }

    void winnerReward() {
        System.out.println(); 
        GameLogic.printSeparator(40);
        System.out.println(); 
        System.out.println("Congratulations! You've won the match!");
        
        System.out.println("\nHere are your choices: ( Select one only )");
        System.out.println("1. HP - Increase by +20% ");
        System.out.println("2. Stamina - Increase by +20%");
        System.out.println("3. Crit Chance - Increase by +10%");
        System.out.println("4. Dodge Chance - Increase by +10%");
        System.out.println("5. Crit Multiplier - Increase by +10%");
        System.out.print("\nEnter the number of the stat you'd like to upgrade: ");
        int choice = GameLogic.readInt("", 1, 5);
        addStats(choice);
        System.out.println();
        System.out.println("Fred: \"Stats added! Remember, you can train up to 5 times!\"");
        GameLogic.printSeparator(40);
    }

    void addStats(int choice){
        if(choice == 1){
            double hpMultiplier = 1 + 0.20;
            int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
            player.setHp(maxHp);
            player.setMaxHp(maxHp);
        } else if(choice == 2){
            double staminaMultiplier = 1 + 0.20;
            int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
            player.setStamina(maxStamina);
            player.setMaxStamina(maxStamina);
        } else if(choice == 3){
            double newCrit = player.getCritChance() + 0.10;
            player.setCritChance(newCrit);
        } else if(choice == 4){
            double newDodge = player.getDodgeChance() + 0.10;
            player.setDodgeChance(newDodge);
        } else if(choice == 5){
            double newMulti = player.getCritMultiplier() + 0.10;
            player.setCritMultiplier(newMulti);
        }
    }

    protected void handleLoss() {
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if (playerProgress.getOpponentWins() != 3) {
            playerProgress.setOpponentWins(playerProgress.getOpponentWins() + 1);
        }

        System.out.println("NGAA EY PILDI NUON");

        GameLogic.gameData.saveGame();
    }

    private void resetFighterStats() {
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
    }

    protected void printStats() {
        System.out.println();
        System.out.println(GameLogic.formatColumns(player.getName(), opponent.getName(), 30));
        System.out.println(GameLogic.formatColumns("HP        " + player.getHp() + "/" + player.getMaxHp(), "HP        " + opponent.getHp() + "/" + opponent.getMaxHp(), 30));
        System.out.println(GameLogic.formatColumns("Stamina   " + player.getStamina() + "/" + player.getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30));
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
