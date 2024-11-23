package world1;

import java.util.Random;

import world1.Skill.SkillsRegistry;
import world1.interfaces.FightLogicInterface;

public abstract class FightLogic implements FightLogicInterface{
    protected Random rand = new Random();
    private PlayerProgress playerProgress = GameLogic.playerProgress;
    private Player player;
    private SkillsRegistry skills = new SkillsRegistry();
    private StreetFighter opponent;
    private boolean playerDodged = false;
    private boolean opponentDodged = false;
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

    public FightLogic(Player player, String[] opponentAttacks, StreetFighter opponent) {
        this.player = player;
        this.opponentAttacks = opponentAttacks;
        this.opponent = opponent;
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

    public SkillsRegistry getSkills(){
        return skills;
    }

    private int isCounter(String opponentMove, String playerMove) {
        if(skills.getSkillByName(opponentMove).counters(playerMove))
            return 1;
        if(skills.getSkillByName(opponentMove).isEffectiveAgainst(playerMove))
            return 2;
        return 0;
    }
    
    public void fightLoop() {
        player.setStage(player.getStage());
        GameLogic.gameData.saveGame();
        player.setOpponent(opponent);
        GameLogic.clearConsole();
        System.out.println(GameLogic.centerBox("Round " + playerProgress.getRound(), 40));
        System.out.println(GameLogic.centerBox("You are fighting " + opponent.getName() + "!", 40));
        printStats();
        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            if (player.getHp() <= 0) {
                System.out.println();
                System.out.println(GameLogic.centerBox(getPlayer().getName() + " is knocked out! " + opponent.getName() + " wins!", 60));
                handleLoss();
                return;
            } else if (opponent.getHp() <= 0) {
                System.out.println();
                System.out.println(GameLogic.centerBox(opponent.getName() + " is knocked out! " + getPlayer().getName() + " wins!", 60));
                handleWin();
                return;
            }
        }
        GameLogic.pressAnything();
    }

    private void selectAttack() {
        int[] choices = new int[3];
        String input = "";

        System.out.print(GameLogic.centerText(30,"You're the first one to attack!"));
    
        for (int i = 0; i < attackOption.length; i++) {
            String attackInfo;
            if(i < attackOption.length - 1){
                attackInfo = (i + 1) + ") " + attackOption[i][0] + " - " + attackOption[i][1];
            } else {
                attackInfo = (i + 1) + ") " + attackOption[i][0];
            }
            System.out.print(GameLogic.centerText(40, attackInfo));
        }

        for (int i = 0; i < comboOption.length; i++) {
            String attackInfo = (comboOption[i][0] + " - " + comboOption[i][1]);
            System.out.print(GameLogic.centerText(40, attackInfo));
        }
        
        System.out.print(GameLogic.centerText(30,"\n(0) Check " + opponent.getName() + "'s combo counters"));

        System.out.print(GameLogic.centerText(30,"\nSelect 3 combos:"));
        System.out.print(GameLogic.centerText("", 97) + "-> ");
    
        while (true) {
            input = GameLogic.scan.nextLine();

            if(input.equals("0")){
                counterInfos(opponent.getName());
                GameLogic.pressAnything();
                GameLogic.clearConsole();
                System.out.println(GameLogic.centerBox("Round " + playerProgress.getRound(), 40));
                System.out.println(GameLogic.centerBox("You are fighting " + opponent.getName() + "!", 40));
                return;
            }

            if (input.equals("5")) {
                if(player.getStamina() - 30 >= 0){
                    input = "567";
                    choices = new int[]{5, 6, 7}; 
                    break;
                } else {
                    String message = getPlayer().getName() + " doesn't have enough stamina for this combo!\n" +
                        "You may use 3 Blocks as your combo to regain stamina";
                    System.out.println(GameLogic.centerBox(message, 60));
                    continue;
                }
            } else if (input.contains("5") || input.contains("6") || input.contains("7")) {
                System.out.println();
                String message = "You can use your special combo by entering '5'!\n" +
                        "If you want to proceed with the combo, just enter '5'.";
                System.out.println(GameLogic.centerBox(message, 60));
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
            } else { // 20% chance
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
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));

        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("You've selected:", "Opponent selected:", 30)));

        for(int i = 0; i < 3; i++){
            String playerAttack = playerAttacks[choices[i]];
            String opponentAttack = opponentAttacks[opponentChoices[i]];

            String line =  GameLogic.formatColumns(playerAttack, opponentAttack, 30);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + line);
        }

        System.out.println();
        printFight(choices, opponentChoices);
    }

    private void printFight(int[] choices, int[] opponentChoices) {
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentAttacks[opponentChoices[i]], playerAttacks[choices[i]]);
            String playerAttack = getPlayer().getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName();

            if(countered == 1){
                System.out.print(GameLogic.centerText(50, playerAttack));
                playerSuccessAction(choices[i], opponentChoices[i], false);
                opponentFailedAction(opponentAttacks[opponentChoices[i]]);
            } else if(countered == 2){
                System.out.print(GameLogic.centerText(50, playerAttack));
                opponentSuccessAction(opponentChoices[i], choices[i], false);
                playerFailedAction(playerAttacks[choices[i]]);
            } else {
                System.out.print(GameLogic.centerText(50, playerAttack));
                String opponentAttack = opponent.getName() + " draws " + getPlayer().getName() + " with " + opponentAttacks[opponentChoices[i]];
                System.out.print(GameLogic.centerText(50, opponentAttack));
                drawAction(choices[i], opponentChoices[i]);
            }
            if(getPlayer().getHp() <= 0 || getOpponent().getHp() <= 0){
                return;
            }
            System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        }
    }

    private void opponentValid(int[] opponentChoice) {
        int tempStamina = getOpponent().getStamina();
        
        for (int i = 0; i < opponentChoice.length; i++) {
            int staminaCost = 0;
            boolean validChoice = false;

            while (!validChoice) {
                staminaCost = getSkills().getSkillByName(opponentAttacks[opponentChoice[i]]).getStaminaCost();
                if (tempStamina - staminaCost >= 0) {
                    validChoice = true;
                } else {
                    if(rand.nextDouble() > 0.3)
                        opponentChoice[i] = rand.nextInt(7);
                        if (opponentChoice[i] >= 4) {
                            if(tempStamina - 30 < 0){
                                int[] opChoices = {2, 2, 2};
                                setOpponentChoices(opChoices); 
                                return;
                            } else {
                                int[] opChoices = {4, 5, 6};
                                setOpponentChoices(opChoices);
                                return;
                            }
                        }
                    else 
                        opponentChoice[i] = 2;
                }
            }
            tempStamina -= staminaCost;
        }
    }

    private int isValidCombo(String input, int stamina) {
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
            
            staminaCost = getSkills().getSkillByName(opponentAttacks[move-1]).getStaminaCost();

            if (tempStamina - staminaCost < 0) {
                return 2;
            }
            tempStamina -= staminaCost;
        }
        return 0;
    }

    private void playerSuccessAction(int choice, int opponentChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < player.getCritChance() && choice != 2 && !isDraw && !opponentDodged) {
            player.setDamageSetter(player.getCritMultiplier());
            System.out.print(GameLogic.centerText(40,player.getName() + "'s " + playerAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
        }

        if (dodgeChance < player.getDodgeChance() && opponentChoice != 2 && !isDraw) {
            playerDodged = true;
        }

        if (opponentDodged) {
            player.setDamageSetter(0);
            System.out.print(GameLogic.centerText(40,opponent.getName() + " dodges " + player.getName() + "'s punch!"));
        }

        player.useSkill(playerAttacks[choice]);

        player.setDamageSetter(1);
        opponentDodged = false;
    }

    private void playerFailedAction(String attack) {
        player.setStamina(player.getStamina() - skills.getSkillByName(attack).getStaminaCost());
    }

    private void opponentSuccessAction(int choice, int playerChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < opponent.getCritChance() && choice != 2 && !isDraw) {
            opponent.setDamageSetter(opponent.getCritMultiplier());
            System.out.print(GameLogic.centerText(40,opponent.getName() + "'s " + opponentAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
        }

        if (dodgeChance < opponent.getDodgeChance() && playerChoice != 2 && !isDraw) {
            opponentDodged = true;
        }

        if (playerDodged) {
            opponent.setDamageSetter(0);
            System.out.print(GameLogic.centerText(40,player.getName() + " dodges " + opponent.getName() + "'s punch!"));
        }

        opponentPerformAction(opponentAttacks[choice]);

        opponent.setDamageSetter(1);
        playerDodged = false;
    }

    private void opponentFailedAction(String attack) {
        opponent.setStamina(opponent.getStamina() - skills.getSkillByName(attack).getStaminaCost());
    }

    private void opponentPerformAction(String attack) {
        opponent.useSkill(attack);
        opponent.setDamageSetter(1);
        playerDodged = false;
    }

    private void drawAction(int choice, int opponentChoice) {
        player.setDamageSetter(0.5);
        playerSuccessAction(choice, opponentChoice, false);
        player.setDamageSetter(1);

        opponent.setDamageSetter(0.5);
        opponentSuccessAction(opponentChoice, choice, false);
        opponent.setDamageSetter(1);
    }

    protected void resetFighterStats() {
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
    }
    
    private void printStats(){
        System.out.println();
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("*"+ getPlayer().getName() +"*" , "*"+ opponent.getName()+"*", 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("HP       " + getPlayer().getHp() + "/" + getPlayer().getMaxHp(), "HP       " + opponent.getHp() + "/" + opponent.getMaxHp(), 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("Stamina   " + getPlayer().getStamina() + "/" + getPlayer().getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30)));
        System.out.println();
    }
    
    private void winnerReward(){
        if(playerProgress.getPlayerWins() != 3){
            System.out.println();
            player.setRank(1);
            System.out.print(GameLogic.centerBox("Congratulations! You've won the match!", 50));
        }
    }
    
    private void handleWin() {
        player.setIsLose(false);
        winnerReward();
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if(playerProgress.getPlayerWins() != 3){
            playerProgress.setPlayerWins(playerProgress.getPlayerWins() + 1);
        }
        tournament.printStanding();
    }

    private void handleLoss() {
        player.setIsLose(true);
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if (playerProgress.getOpponentWins() != 3) {
            playerProgress.setOpponentWins(playerProgress.getOpponentWins() + 1);
        }
        tournament.printStanding();
        GameLogic.gameData.saveGame();
    }

    private void counterInfos(String name){
        GameLogic.clearConsole();
        if(opponent.getName() == "Rico Ramirez"){
            System.out.print(GameLogic.centerBox("Rico Ramirez Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Cross < Uppercut"));
            System.out.print(GameLogic.centerText(50,"(2) Rear Uppercut < Block"));
            System.out.print(GameLogic.centerText(50,"(3) Lead Hook < Jab"));
        } else if(opponent.getName() == "Oscar Lopez"){
            System.out.print(GameLogic.centerBox("Oscar Lopez Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Quick Jab < Uppercut"));
            System.out.print(GameLogic.centerText(50,"(2) Cross < Uppercut"));
            System.out.print(GameLogic.centerText(50,"(3) Power Punch < Block"));
        } else if(opponent.getName() == "Ishmael Tetteh"){
            System.out.print(GameLogic.centerBox("Ishmael Tetteh Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Right Uppercut < Block"));
            System.out.print(GameLogic.centerText(50,"(2) Left Hook < Jab"));
            System.out.print(GameLogic.centerText(50,"(3) Right Cross < Uppercut"));
        } 
    }
}
