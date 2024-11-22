package world3;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world1.Skill.SkillsRegistry;
import world2.BoxerHints;
import world2.interfaces.SparFightLogicInterface;
import world3.PassiveSkill.PassiveSkillRegistry;

public abstract class SparFightLogic implements SparFightLogicInterface{
    private Random rand = new Random();
    private PlayerProgress playerProgress = GameLogic.playerProgress;
    private Player player;
    private StreetFighter opponent;
    private SkillsRegistry skills = new SkillsRegistry();
    private PassiveSkillRegistry playerPassiveSkills = new PassiveSkillRegistry();
    private PassiveSkillRegistry opponentPassiveSkills = new PassiveSkillRegistry();
    private String playerPassive = "";
    private boolean playerHasPassive = true;
    private boolean opponentHasPassive = false;
    private boolean playerDodged = false;
    private boolean opponentDodged = false;
    private static BoxerHints boxerHints;
    private int[] opponentChoices = new int[3];
    private static String[][] attackOption = {{"Jab", "Damage: 10 | Stamina: -5"}, 
                                {"Hook", "Damage: 15 | Stamina: -7"}, 
                                {"Block", "Stamina: +5"}, 
                                {"Uppercut", "Damage: 20 | Stamina: -10"},
                                {"The Body Breaker", ""},
                            };
    private static String[][] comboOption = {{"Lead Body Shot", "Damage: 15 | Stamina: -7"},
                                    {"Cross to the Ribs", "Damage: 20 | Stamina: -9"},
                                    {"Finishing Uppercut", "Damage: 25 | Stamina: -14"}};
    private static String[][] passiveSkill = {{"Flow State", "100% Dodge Chance, blocks all damage next turn."},
                                                {"Adrenaline Rush", "Boost your Crit Chance by 100% next turn."},
                                                {"Sixth Sense", "Reveals the opponent's next 3 moves."}};
    public static String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut", "Flow State", "Adrenaline Rush", "Sixth Sense"};
    private String[] opponentAttacks;

    public SparFightLogic(Player player, String[] opponentAttacks, StreetFighter opponent) {
        this.player = player;
        this.opponentAttacks = opponentAttacks;
        this.opponent = opponent;
    }

    public abstract String getOpponentName();
    protected abstract void handleLoss();
    protected abstract void handleWin();

    public Player getPlayer(){
        return player;
    }

    public StreetFighter getOpponent(){
        return opponent;
    }

    public PlayerProgress getPlayerProgress(){
        return playerProgress;
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
        if(skills.getSkillByName(opponentMove).isEffectiveAgainst(playerMove))
            return 2;
        if(skills.getSkillByName(opponentMove).counters(playerMove))
            return 1;
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
        boxerHints = new BoxerHints();

        // Opponent selects a move
        for (int i = 0; i < 3; i++) {
            // Generate opponentChoices with higher probability for 1 to 10
            int randomValue = rand.nextInt(10); // Generate a random number between 0 and 9
        
            // Higher probability for numbers 1 to 4
            if (randomValue < 8) { // 80% chance
                opponentChoices[i] = rand.nextInt(4); // 0, 1, 2, or 3 (which correspond to 1 to 4)
            } else { // 20% chance
                opponentChoices[i] = 4 + rand.nextInt(3); // 4, 5, or 6
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (opponentChoices[i] >= 4 && opponentChoices[i] <= 6) {
                opponentChoices = new int[]{4, 5, 6}; 
                break;
            }
        }

        opponentValid(opponentChoices);

        // Player selects a move
        System.out.println(GameLogic.centerText(30, ("~ ~ " + boxerHints.getRandomHint(opponentAttacks[opponentChoices[0]]) + " ~ ~")));
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

        System.out.println();
        for (int i = 0; i < passiveSkill.length; i++) {
            System.out.print(GameLogic.centerText(40, ((i + 6) + ") " + passiveSkill[i][0] + " - " + passiveSkill[i][1])));
        }
        
        System.out.print(GameLogic.centerText(30,"\n(0) Check " + opponent.getName() + "'s combo counters"));

        System.out.print(GameLogic.centerText(30,"\nSelect 3 combos:"));
        System.out.print("-> ");
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
            } else if (input.contains("5")) {
                System.out.println();
                String message = "You can use your special combo by entering '5'!\n" +
                        "If you want to proceed with the combo, just enter '5'.";
                System.out.println(GameLogic.centerBox(message, 60));
                continue; 
            }
            
            if (input.equals("6") || input.equals("7") || input.equals("8")) {
                if(playerHasPassive){
                    playerActivatePassive(input);
                    continue;
                } else if(playerPassive != "") {
                    String message = "Your passive skill, " + playerPassive + ", is already active! You must attack first before activating it again.";
                    System.out.println(GameLogic.centerBox(message, 105));
                    continue;
                } else {
                    String message = getPlayer().getName() + " needs 3 successful consecutive hits to activate this combo!\n" +
                        "Try to land more hits before using your passive skill.";
                    System.out.println(GameLogic.centerBox(message, 70));
                    continue;
                }
            } else if (input.contains("6") || input.contains("7") || input.contains("8")) {
                System.out.println();
                String message = "You can use your passive skill by entering '6' | '7' | '8'!\n" +
                        "If you want to proceed, just enter '6' | '7' | '8'.";
                System.out.println(GameLogic.centerBox(message, 65));
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
            choices[i] = Character.getNumericValue(input.charAt(i)) - 1; 
        }

        System.out.println();
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("You've selected:", "Opponent selected:", 30)));

        for(int i = 0; i < 3; i++){
            String playerAttack = playerAttacks[choices[i]];
            String opponentAttack = opponentAttacks[opponentChoices[i]];

            String line =  GameLogic.formatColumns(playerAttack, opponentAttack, 30);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + line);
        }

        System.out.println();
        printFight(choices, opponentChoices);
        
    }

    private void printFight(int[] choices, int[] opponentChoices) {
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));

        for(int i = 0; i < 3; i++){
            
            if(playerPassive == "Flow State") playerDodged = true;
            else playerDodged = false;

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
        
        if(playerPassive != ""){
            playerDeactivatePassive();
        }
    }

    private void playerActivatePassive(String input){
        int num = Character.getNumericValue(input.charAt(0)); 
        playerPassive = playerAttacks[num + 1];
        playerPassiveSkills.getSkillByName(playerPassive).activatePassive();
        // playerHasPassive = false;
    }

    private void playerDeactivatePassive(){
        if(playerPassive != ""){
            playerPassiveSkills.getSkillByName(playerPassive).deactivatePassive();
            if(playerPassiveSkills.getSkillByName(playerPassive).getRoundActive() == 0){
                playerPassiveSkills.getSkillByName(playerPassive).setRoundActive(3);
                playerPassive = "";
            }
        }
        System.out.println("Player passive: " + playerPassive);
        if(playerPassive != ""){
            System.out.println("Player rounds active " + playerPassiveSkills.getSkillByName(playerPassive).getRoundActive());
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
            if (!Character.isDigit(c) || c < '1' || c > '9') {
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

        if(playerPassive == "Adrenaline Rush") critChance = 0;
        else critChance = rand.nextDouble();

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
    
    protected void winnerReward(){
        if(playerProgress.getPlayerWins() != 3){
            System.out.println();
            System.out.print(GameLogic.centerBox("Congratulations! You've won the match!", 50));
        }
    }

    protected void winnerRewardPoints(){
        System.out.println();
        player.setPlayerPoints(player.getPlayerPoints() + 100);
        String message = "Congratulations! You've won the match!\n\n" +
            "You have been given 100 points.\n\n" +
            "You now have " + player.getPlayerPoints() + " points.\n\n" +
            "Visit the shop and use your points to buy items.\n";

        System.out.println(GameLogic.centerBox(message, 90));
    }

    protected void addStats(int choice){
        if(choice == 1){
            double hpMultiplier = 1 + 0.15;
            int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
            player.setHp(maxHp);
            player.setMaxHp(maxHp);
        } else if(choice == 2){
            double staminaMultiplier = 1 + 0.15;
            int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
            player.setStamina(maxStamina);
            player.setMaxStamina(maxStamina);
        } else if(choice == 3){
            double newCrit = player.getCritChance() + 0.05;
            player.setCritChance(newCrit);
        } else if(choice == 4){
            double newDodge = player.getDodgeChance() + 0.05;
            player.setDodgeChance(newDodge);
        } else if(choice == 5){
            double newMulti = player.getCritMultiplier() + 0.05;
            player.setCritMultiplier(newMulti);
        }
    }

    private void counterInfos(String name){
        // GameLogic.clearConsole();
        // if(opponent.getName() == "Joaquin Perez"){
        //     GameLogic.printHeading("\tJoaquin Perez Combo Counter:");
        //     System.out.println("(1) Right Uppercut < Block");
        //     System.out.println("(2) Left Hook < Jab");
        //     System.out.println("(3) Right Cross < Uppercut");
        //     System.out.println();
        //     System.out.println("(4) Elbow Strike < Block");
        //     System.out.println("(5) Head Butt < Hook");
        //     System.out.println("(6) Low Blow < Uppercut");
        // } else if(opponent.getName() == "Lando Pitik"){
        //     GameLogic.printHeading("\tLando Pitik Combo Counter:");
        //     System.out.println("(1) Cross < Uppercut");
        //     System.out.println("(2) Rear Uppercut < Block");
        //     System.out.println("(3) Lead Hook < Jab");
        //     System.out.println();
        //     System.out.println("(4) Elbow Strike < Block");
        //     System.out.println("(5) Head Butt < Hook");
        //     System.out.println("(6) Low Blow < Uppercut");
        // } else if(opponent.getName() == "Julio Navarro"){
        //     GameLogic.printHeading("\tJulio Navarro Combo Counter:");
        //     System.out.println("(1) Right Uppercut < Block");
        //     System.out.println("(2) Left Hook < Jab");
        //     System.out.println("(3) Right Cross < Uppercut");
        //     System.out.println();
        //     System.out.println("(4) Elbow Strike < Block");
        //     System.out.println("(5) Head Butt < Hook");
        //     System.out.println("(6) Low Blow < Uppercut");
        // } else if(opponent.getName() == "Raul Villanueva"){
        //     GameLogic.printHeading("\tRaul Villanueva Combo Counter:");
        //     System.out.println("(1) Cross < Uppercut");
        //     System.out.println("(2) Rear Uppercut < Block");
        //     System.out.println("(3) Lead Hook < Jab");
        //     System.out.println();
        //     System.out.println("(4) Elbow Strike < Block");
        //     System.out.println("(5) Head Butt < Hook");
        //     System.out.println("(6) Low Blow < Uppercut");
        // } else if(opponent.getName() == "Ralfo Salvahez"){
        //     GameLogic.printHeading("\tRalfo Salvahez Combo Counter:");
        //     System.out.println("(1) Quick Jab < Uppercut");
        //     System.out.println("(2) Cross < Uppercut");
        //     System.out.println("(3) Power Punch < Block");
        //     System.out.println();
        //     System.out.println("(4) Elbow Strike < Block");
        //     System.out.println("(5) Head Butt < Hook");
        //     System.out.println("(6) Low Blow < Uppercut");
        // }
    }
}
