package world2;

import java.util.Random;

import world1.GameLogic;
import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.StreetFighter;
import Skill.SkillsRegistry;
import interfaces.SparFightLogicInterface;

public abstract class SparFightLogic implements SparFightLogicInterface{
    protected Random rand = new Random();
    private PlayerProgress playerProgress = GameLogic.playerProgress;
    private Player player;
    private SkillsRegistry skills = new SkillsRegistry();
    private StreetFighter opponent;
    private boolean playerDodged = false;
    private boolean opponentDodged = false;
    protected static BoxerHints boxerHints;
    private int[] opponentChoices = new int[3];
    protected static String[][] attackOption = {{"Jab", "Damage: 10 | Stamina: -5      "}, 
                                                {"Hook", "Damage: 15 | Stamina: -7     "}, 
                                                {"Block", "Stamina: +5                 "}, 
                                                {"Uppercut", "Damage: 20 | Stamina: -10"},
                                                {"The Body Breaker", ""},
                                                {"Elbow Strike", "Damage: 40 | Stamina: -25 | HP: -10"},
                                                {"Head Butt", "Damage: 30 | Stamina: -20 | HP: -15   "},
                                                {"Low Blow", "Damage: 40 | Stamina: -25 | HP: -20    "}};
    protected static String[][] comboOption = { {"Lead Body Shot", "Damage: 15 | Stamina: -7     "},
                                                {"Cross to the Ribs", "Damage: 20 | Stamina: -9  "},
                                                {"Finishing Uppercut", "Damage: 25 | Stamina: -14"}};
    public static String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut", "Elbow Strike", "Head Butt", "Low Blow"};
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
        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox("Round " + playerProgress.getRound(), 40));
        System.out.print(GameLogic.reset);
        System.out.print(GameLogic.redText);
        System.out.println(GameLogic.centerBox("You are fighting " + opponent.getName() + "!", 40));
        System.out.print(GameLogic.reset);
        printStats();
        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            if (player.getHp() <= 0) {
                System.out.println();
                System.out.print(GameLogic.redText);
                System.out.println(GameLogic.centerBox(getPlayer().getName() + " is knocked out! " + opponent.getName() + " wins!", 60));
                System.out.print(GameLogic.reset);
                handleLoss();
                return;
            } else if (opponent.getHp() <= 0) {
                System.out.println();
                System.out.print(GameLogic.greenText);
                System.out.println(GameLogic.centerBox(opponent.getName() + " is knocked out! " + getPlayer().getName() + " wins!", 60));
                System.out.print(GameLogic.reset);
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
            if (randomValue < 8) { // 70% chance
                opponentChoices[i] = rand.nextInt(4); // 0, 1, 2, or 3 (which correspond to 1 to 4)
            } else { // 20% chance
                opponentChoices[i] = 4 + rand.nextInt(6); // 4, 5, or 6
            }
        }
        
        // Check for opponentChoices being >= 4
        for (int i = 0; i < 3; i++) {
            if (opponentChoices[i] >= 4 && opponentChoices[i] <= 6) {
                opponentChoices = new int[]{4, 5, 6}; 
                break;
            }
        }

        opponentValid(opponentChoices);

        // Player selects a move
        System.out.print(GameLogic.blueText);
        System.out.println(GameLogic.centerText(30, ("~ ~ " + boxerHints.getRandomHint(opponentAttacks[opponentChoices[0]]) + " ~ ~")));
        System.out.print(GameLogic.reset);
        
        System.out.print(GameLogic.orangeText);
        System.out.print(GameLogic.centerText(30,"You're the first one to attack!"));

        for (int i = 0; i < attackOption.length - 3; i++) {
            String attackInfo;
            if(i < attackOption.length - 4){
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
        for (int i = attackOption.length - 3; i < attackOption.length; i++) {
            System.out.print(GameLogic.centerText(40, ((i + 1) + ") " + attackOption[i][0] + " - " + attackOption[i][1])));
        }
        
        System.out.print(GameLogic.centerText(30,"\n(0) Check " + opponent.getName() + "'s combo counters"));

        System.out.print(GameLogic.greenText);
        System.out.print(GameLogic.centerText(30,"\nSelect 3 combos:"));
        System.out.print(GameLogic.reset);
        while (true) {
            input = GameLogic.readString(GameLogic.centerText("", 97) + "-> ");

            if(input.equals("0")){
                counterInfos(opponent.getName());
                GameLogic.pressAnything();
                GameLogic.clearConsole();
                System.out.print(GameLogic.greenText);
                System.out.println(GameLogic.centerBox("Round " + playerProgress.getRound(), 40));
                System.out.print(GameLogic.reset);
                System.out.print(GameLogic.redText);
                System.out.println(GameLogic.centerBox("You are fighting " + opponent.getName() + "!", 40));
                System.out.print(GameLogic.reset);
                return;
            }

            System.out.print(GameLogic.redText);
            if (input.equals("5")) {
                if(player.getStamina() - 30 >= 0){
                    input = "567";
                    choices = new int[]{5, 6, 7}; 
                    System.out.print(GameLogic.reset);
                    break;
                } else {
                    String message = getPlayer().getName() + " doesn't have enough stamina for this combo!\n" +
                        "You may use 3 Blocks as your combo to regain stamina";
                    System.out.println(GameLogic.centerBox(message, 60));
                    System.out.print(GameLogic.reset);
                    continue;
                }
            } else if (input.contains("5")) {
                System.out.println();
                String message = "You can use your special combo by entering '5'!\n" +
                        "If you want to proceed with the combo, just enter '5'.";
                System.out.println(GameLogic.centerBox(message, 60));
                System.out.print(GameLogic.reset);
                continue; 
            }

            // Map 6 -> 8, 7 -> 9, and 8 -> 0
            StringBuilder mappedInput = new StringBuilder();
            for (char c : input.toCharArray()) {
                if (c == '6') {
                    mappedInput.append('8');
                } else if (c == '7') {
                    mappedInput.append('9');
                } else if (c == '8') {
                    mappedInput.append('0');
                } else {
                    mappedInput.append(c); // Keep other digits as they are
                }
            }
            input = mappedInput.toString();

            System.out.print(GameLogic.redText);
            if(isValidCombo(input, player.getStamina()) == 1){
                System.out.println(GameLogic.centerBox("Please enter a valid combo (e.g., 123):", 50));
                System.out.print(GameLogic.reset);
                continue;
            } else if(isValidCombo(input, player.getStamina()) == 2) {
                String message = player.getName() + " doesn't have enough stamina for this combo!\n" +
                                                "You may use 3 Blocks as your combo to regain stamina";
                System.out.println(GameLogic.centerBox(message, 60));
                System.out.print(GameLogic.reset);
                continue;
            }
            break;
        }

        for (int i = 0; i < 3; i++) {
            // Adjust the character input value correctly
            choices[i] = Character.getNumericValue(input.charAt(i)) - 1; // Use input directly

            if (choices[i] == -1) {
                choices[i] = 9;
            }
        }
        
        System.out.print(GameLogic.reset);
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
                System.out.print(GameLogic.greenText);
                System.out.print(GameLogic.centerText(50, playerAttack));
                System.out.print(GameLogic.reset);
                playerSuccessAction(choices[i], opponentChoices[i], false);
                opponentFailedAction(opponentAttacks[opponentChoices[i]]);
            } else if(countered == 2){
                System.out.print(GameLogic.greenText);
                System.out.print(GameLogic.centerText(50, playerAttack));
                System.out.print(GameLogic.reset);
                opponentSuccessAction(opponentChoices[i], choices[i], false);
                playerFailedAction(playerAttacks[choices[i]]);
            } else {
                System.out.print(GameLogic.greenText);
                System.out.print(GameLogic.centerText(50, playerAttack));
                System.out.print(GameLogic.reset);
                String opponentAttack = opponent.getName() + " draws " + getPlayer().getName() + " with " + opponentAttacks[opponentChoices[i]];
                System.out.print(GameLogic.redText);
                System.out.print(GameLogic.centerText(50, opponentAttack));
                System.out.print(GameLogic.reset);
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
            if (!Character.isDigit(c) || c < '0' || c > '9') {
                return 1;
            }
            
            int move = Character.getNumericValue(c);
            int staminaCost;

            staminaCost = getSkills().getSkillByName(move-1 == -1 ? "Low Blow" : opponentAttacks[move-1]).getStaminaCost();

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
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerText(40,player.getName() + "'s " + playerAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
            System.out.print(GameLogic.reset);
        }

        if (dodgeChance < player.getDodgeChance() && opponentChoice != 2 && !isDraw) {
            playerDodged = true;
        }

        if (opponentDodged) {
            player.setDamageSetter(0);
            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40,opponent.getName() + " dodges " + player.getName() + "'s punch!"));
            System.out.print(GameLogic.reset);
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
            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40,opponent.getName() + "'s " + opponentAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
            System.out.print(GameLogic.reset);
        }

        if (dodgeChance < opponent.getDodgeChance() && playerChoice != 2 && !isDraw) {
            opponentDodged = true;
        }

        if (playerDodged) {
            opponent.setDamageSetter(0);
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerText(40,player.getName() + " dodges " + opponent.getName() + "'s punch!"));
            System.out.print(GameLogic.reset);
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
        String prompt = GameLogic.formatColumns("*"+ getPlayer().getName() +"*" , "*"+ opponent.getName()+"*", 30)
                        + "\n" + GameLogic.formatColumns("HP       " + getPlayer().getHp() + "/" + getPlayer().getMaxHp(), "HP       " + opponent.getHp() + "/" + opponent.getMaxHp(), 30)
                         + "\n" + GameLogic.formatColumns("Stamina   " + getPlayer().getStamina() + "/" + getPlayer().getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30);
        System.out.print(GameLogic.centerBox(prompt, 55));
        System.out.println("\n");
    }
    
    protected void winnerReward(){
        if(playerProgress.getPlayerWins() != 3){
            System.out.println();
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerBox("Congratulations! You've won the match!", 50));
            System.out.print(GameLogic.reset);
        }
    }

    protected void winnerRewardPoints(){
        System.out.println();
        player.setPlayerPoints(player.getPlayerPoints() + 200);
        String message = "Congratulations! You've won the match!\n\n" +
            "You won 200 points.\n\n" +
            "You now have " + player.getPlayerPoints() + " points.\n\n" +
            "Visit the shop and use your points to buy items.\n";

        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox(message, 90));
        System.out.print(GameLogic.reset);
    }

    protected void addStats(int choice){
        if(choice == 1){
            double hpMultiplier = 1 + 0.10;
            int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
            player.setHp(maxHp);
            player.setMaxHp(maxHp);
        } else if(choice == 2){
            double staminaMultiplier = 1 + 0.10;
            int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
            player.setStamina(maxStamina);
            player.setMaxStamina(maxStamina);
        } else if(choice == 3){
            double newCrit = player.getCritChance() + 0.03;
            player.setCritChance(newCrit);
        } else if(choice == 4){
            double newDodge = player.getDodgeChance() + 0.03;
            player.setDodgeChance(newDodge);
        } else if(choice == 5){
            double newMulti = player.getCritMultiplier() + 0.03;
            player.setCritMultiplier(newMulti);
        }
    }

    private void counterInfos(String name){
        GameLogic.clearConsole();
        System.out.print(GameLogic.blueText);
        if(opponent.getName() == "Joaquin Perez"){
            System.out.print(GameLogic.centerBox("Joaquin Perez Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Right Uppercut < Block"));
            System.out.print(GameLogic.centerText(50,"(2) Left Hook < Jab"));
            System.out.print(GameLogic.centerText(50,"(3) Right Cross < Uppercut"));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(4) Elbow Strike < Block"));
            System.out.print(GameLogic.centerText(50,"(5) Head Butt < Hook"));
            System.out.print(GameLogic.centerText(50,"(6) Low Blow < Uppercut"));
        } else if(opponent.getName() == "Lando Pitik"){
            System.out.print(GameLogic.centerBox("Lando Pitik Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Cross < Uppercut"));
            System.out.print(GameLogic.centerText(50, "(2) Rear Uppercut < Block"));
            System.out.print(GameLogic.centerText(50,"(3) Lead Hook < Jab"));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(4) Elbow Strike < Block"));
            System.out.print(GameLogic.centerText(50,"(5) Head Butt < Hook"));
            System.out.print(GameLogic.centerText(50,"(6) Low Blow < Uppercut"));
        } else if(opponent.getName() == "Julio Navarro"){
            System.out.print(GameLogic.centerBox("Julio Navarro Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Right Uppercut < Block"));
            System.out.print(GameLogic.centerText(50,"(2) Left Hook < Jab"));
            System.out.print(GameLogic.centerText(50,"(3) Right Cross < Uppercut"));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(4) Elbow Strike < Block"));
            System.out.print(GameLogic.centerText(50,"(5) Head Butt < Hook"));
            System.out.print(GameLogic.centerText(50,"(6) Low Blow < Uppercut"));
        } else if(opponent.getName() == "Raul Villanueva"){
            System.out.print(GameLogic.centerBox("Raul Villanueva Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(1) Cross < Uppercut"));
            System.out.print(GameLogic.centerText(50, "(2) Rear Uppercut < Block"));
            System.out.print(GameLogic.centerText(50,"(3) Lead Hook < Jab"));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(4) Elbow Strike < Block"));
            System.out.print(GameLogic.centerText(50,"(5) Head Butt < Hook"));
            System.out.print(GameLogic.centerText(50,"(6) Low Blow < Uppercut"));
        } else if(opponent.getName() == "Ralfo Salvahez"){
            System.out.print(GameLogic.centerBox("Ralfo Salvahez Combo Counter:",50));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"1) Quick Jab < Uppercut"));
            System.out.print(GameLogic.centerText(50, "(2) Cross < Uppercut"));
            System.out.print(GameLogic.centerText(50,"(3) Power Punch < Hook"));
            System.out.println();
            System.out.print(GameLogic.centerText(50,"(4) Elbow Strike < Block"));
            System.out.print(GameLogic.centerText(50,"(5) Head Butt < Hook"));
            System.out.print(GameLogic.centerText(50,"(6) Low Blow < Uppercut"));
        }
        System.out.print(GameLogic.reset);
    }
}
