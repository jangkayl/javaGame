package world1.TrainInGym;
import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;

public class FredGym extends PlayerVsOpponent{
    private static Random rand = new Random();
    private static int[] opponentChoices = new int[3];
    private static PlayerProgress playerProgress = GameLogic.playerProgress;
    private static Player player;
    private static String[][] attackOption = {{"Jab", "Damage: 10 | Stamina: -5"}, 
                                {"Hook", "Damage: 15 | Stamina: -7"}, 
                                {"Block", "Stamina: +5"}, 
                                {"Uppercut", "Damage: 20 | Stamina: -10"},
                                {"The Body Breaker", ""}};
    private static String[][] comboOption = {{"Lead Body Shot", "Damage: 15 | Stamina: -7"},
                                    {"Cross to the Ribs", "Damage: 20 | Stamina: -9"},
                                    {"Finishing Uppercut", "Damage: 25 | Stamina: -14"}};
    private static String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut"};
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Jab to the Body", "Lead Hook", "Rear Uppercut"};
    private static StreetFighter opponent = new StreetFighter("Fred", 130, 70, 0.2, 2, .30, 2);
    
    public FredGym(Player player){
        super(player, opponent);
        setOpponentAttacks(opponentAttacks);
    }

    public String getOpponentName(){
        return opponent.getName();
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public void fightLoop2() {
        playerProgress.setRound(1);
        GameLogic.gameData.saveGame();
        GameLogic.clearConsole();
        System.out.println(GameLogic.centerBox("Round " + playerProgress.getRound(), 40));
        System.out.println(GameLogic.centerBox("You are fighting " + opponent.getName() + "!", 40));
        player.setOpponent(opponent);
        printStats();
        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            if (player.getHp() <= 0) {
                System.out.println();
                System.out.println(GameLogic.centerBox(player.getName() + " is knocked out! " + opponent.getName() + " wins!",60));
                playerProgress.setRound(playerProgress.getRound() + 1);
                opponent.setHp(opponent.getMaxHp());
                opponent.setStamina(opponent.getMaxStamina());
                player.setHp(player.getMaxHp());
                player.setStamina(player.getMaxStamina());
                GameLogic.pressAnything();
                return;
            } else if(opponent.getHp() <= 0){
                System.out.println();
                System.out.println(GameLogic.centerBox(opponent.getName() + " is knocked out! " + player.getName() + " wins!",60));
                winnerReward();
                player.setHp(player.getMaxHp());
                player.setStamina(player.getMaxStamina());
                opponent.setHp(opponent.getMaxHp());
                opponent.setStamina(opponent.getMaxStamina());
                playerProgress.setRound(1);  
                playerProgress.setShopStage(3);  
                GameLogic.gameData.saveGame();
                GameLogic.pressAnything();
                return;
            }
        }
    }

    private void printStats(){
        System.out.println();
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("*"+ getPlayer().getName() +"*" , "*"+ opponent.getName()+"*", 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("HP       " + getPlayer().getHp() + "/" + getPlayer().getMaxHp(), "HP       " + opponent.getHp() + "/" + opponent.getMaxHp(), 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("Stamina   " + getPlayer().getStamina() + "/" + getPlayer().getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30)));
        System.out.println();
    }

    private void selectAttack() {
        int[] choices = new int[3];
        String input = "";

        System.out.println();
        System.out.print(GameLogic.centerText(30,"You're the first one to attack!"));
        System.out.print(GameLogic.centerText(30,"Select 3 combos:"));
        System.out.println("\n");
    
        for (int i = 0; i < attackOption.length; i++) {
            if(i < attackOption.length - 1){
                String attackInfo = (i + 1) + ") " + attackOption[i][0] + " - " + attackOption[i][1];
                System.out.print(GameLogic.centerText(40, attackInfo));
            } else {
                String attackInfo = (i + 1) + ") " + attackOption[i][0];
                System.out.print(GameLogic.centerText(40, attackInfo));
            }
        }

        for (int i = 0; i < comboOption.length; i++) {
            System.out.print(GameLogic.centerText(40," - " + comboOption[i][0] + " - " + comboOption[i][1]));
        }
    
        System.out.print("-> ");
        while (true) {
            input = GameLogic.scan.nextLine();

            if (input.equals("5")) {
                if(player.getStamina() - 30 >= 0){
                    input = "567";
                    choices = new int[]{5, 6, 7}; 
                    break;
                } else {
                    String message = player.getName() + " doesn't have enough stamina for this combo!\n" +
                            "You may use 3 Blocks as your combo to regain stamina";
                    System.out.println(GameLogic.centerBox(message, 60));
                    continue;
                }
            } else if (input.contains("5") || input.contains("6") || input.contains("7")) {
                System.out.println();
                String message = "You can use your special combo by entering '5'!\n" +
                        "If you want to proceed with the combo, just enter '5'.\n";

                System.out.println(GameLogic.centerBox(message, 50));
                System.out.println();
                continue; 
            }

            if(isValidCombo(input, player.getStamina()) == 1){
                System.out.println(GameLogic.centerBox("Please enter a valid combo (e.g., 123):", 60));
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
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("You've selected:", "Opponent selected:", 30)));

        for(int i = 0; i < 3; i++){
            String playerAttack = playerAttacks[choices[i]];
            String opponentAttack = opponentAttacks[opponentChoices[i]];

            String line =  GameLogic.formatColumns(playerAttack, opponentAttack, 30);
            System.out.print(GameLogic.centerText(30, line));
        }

        System.out.println();
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        printFight(choices, opponentChoices);
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
            
            staminaCost = getSkills().getSkillByName(opponentAttacks[move]).getStaminaCost();

            if (tempStamina - staminaCost < 0) {
                return 2;
            }
            tempStamina -= staminaCost;
        }
        return 0;
    }

    private void opponentValid(int[] opponentChoice) {
        int tempStamina = opponent.getStamina();
        
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
                                opponentChoices = new int[]{2, 2, 2}; 
                                return;
                            } else {
                                opponentChoices = new int[]{4, 5, 6}; 
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

    private void printFight(int[] choices, int[] opponentChoices){
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentAttacks[opponentChoices[i]], playerAttacks[choices[i]]);
            if(countered == 1){
                System.out.println(player.getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName());
                playerSuccessAction(choices[i], opponentChoices[i], false);
                opponentFailedAction(opponentAttacks[opponentChoices[i]]);
            } else if(countered == 2){
                System.out.println(player.getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName());
                opponentSuccessAction(opponentChoices[i], choices[i], false);
                playerFailedAction(playerAttacks[choices[i]]);
            } else {
                System.out.println(player.getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName());
                System.out.println(opponent.getName() + " draws " + player.getName() + " with " + opponentAttacks[choices[i]]);
                drawAction(choices[i], opponentChoices[i]);
            }
            if(player.getHp() <= 0 || opponent.getHp() <= 0){
                return;
            }
            GameLogic.printSeparator(50);
        }
    }

    private void winnerReward() {
        System.out.println(); 
        GameLogic.printSeparator(40);
        System.out.println(); 
        playerProgress.setAddStats(playerProgress.getAddStats() + 1);
        System.out.println("Congratulations! You've won " + playerProgress.getAddStats() + " / 5 matches");
        System.out.println();  
        System.out.println("Fred: \t\"Great job! Now, choose what stats you want to upgrade.\"");
        
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
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
    }

    private void addStats(int choice){
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
}
