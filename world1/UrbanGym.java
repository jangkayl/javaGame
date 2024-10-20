package world1;
import java.util.Random;

public class UrbanGym {
    static Random rand = new Random();
    static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static Player player;
    public static String[][] attack = {{"Jab", "Damage: 10 | Stamina: -5"}, 
                                {"Hook", "Damage: 15 | Stamina: -7"}, 
                                {"Block", "Stamina: +5"}, 
                                {"Uppercut", "Damage: 20 | Stamina: -10"}};
    public static StreetFighter opponent = new StreetFighter("Carlito Cortez", 120, 80, 0.2, 2, .20);
    
    public static void setPlayer(Player p) {
        player = p;
    }

    static void printStats(){
        System.out.println();
        System.out.println(GameLogic.formatColumns(player.getName(), opponent.getName(), 30));
        System.out.println(GameLogic.formatColumns("HP        " + player.getHp() + "/" + player.getMaxHp(), "HP        " + opponent.getHp() + "/" + opponent.getMaxHp(), 30));
        System.out.println(GameLogic.formatColumns("Stamina   " + player.getStamina() + "/" + player.getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30));
        System.out.println();
    }
    
    static void selectAttack() {
        System.out.println();
        System.out.println("You're the first one to attack!");
        System.out.println("Select 3 combos:");
    
        for (int i = 0; i < attack.length; i++) {
            System.out.println((i + 1) + ") " + attack[i][0] + " - " + attack[i][1]);
        }
    
        System.out.print("-> ");
        String input = GameLogic.scan.nextLine();
    
        while (isValidCombo(input, player.getStamina()) != 0) {
            if(isValidCombo(input, player.getStamina()) == 1){
                System.out.println("Please enter a valid combo (e.g., 123):");
            } else if(isValidCombo(input, player.getStamina()) == 2) {
                System.out.println(player.getName() + " doesn't have enough stamina for this combo!");
                System.out.println("You may use 3 Blocks as your combo to regain stamina");
            }
            input = GameLogic.scan.nextLine();
        }

        int[] choices = new int[3];
        int[] opponentChoices = new int[3];

        for (int i = 0; i < 3; i++) {
            choices[i] = Character.getNumericValue(input.charAt(i) - 1);
            opponentChoices[i] = rand.nextInt(4);
        }

        opponentValid(opponentChoices);

        System.out.println();
        System.out.println("You've selected:\t\tOpponent selected:");
        for(int i = 0; i < 3; i++){
            System.out.println(attack[choices[i]][0] + "     \t\t\t" + attack[opponentChoices[i]][0]);
        }

        System.out.println();
        printFight(choices, opponentChoices);
    }

    static int isValidCombo(String input, int stamina) {
        if (input.length() != 3) {
            return 1;
        }

        int tempStamina = stamina;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || c < '1' || c > '4') {
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

    static void opponentValid(int[] opponentChoice) {
        int tempStamina = opponent.getStamina();
        
        for (int i = 0; i < opponentChoice.length; i++) {
            int staminaCost = 0;
    
            boolean validChoice = false;
            while (!validChoice) {
                switch (opponentChoice[i]) {
                    case 0: // Jab
                        staminaCost = 5;
                        break;
                    case 1: // Hook
                        staminaCost = 7;
                        break;
                    case 2: // Block 
                        staminaCost = 0;
                        break;
                    case 3:  // Uppercut 
                        staminaCost = 10;
                        break;
                }
    
                if (tempStamina - staminaCost >= 0) {
                    validChoice = true;
                } else {
                    if(rand.nextDouble() > 0.3)
                        opponentChoice[i] = rand.nextInt(4);
                    else 
                        opponentChoice[i] = 2;
                }
            }
    
            tempStamina -= staminaCost;
        }
    }

    static void printFight(int[] choices, int[] opponentChoices){
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentChoices[i], choices[i]);
            if(countered == 1){
                System.out.println(player.getName() + " throws a " + attack[choices[i]][0] + " to " + opponent.getName());
                Fighting.playerSuccessAction(choices[i], opponentChoices[i], false);
                Fighting.opponentFailedAction(opponentChoices[i]);
            } else if(countered == 2){
                System.out.println(player.getName() + " throws a " + attack[choices[i]][0] + " to " + opponent.getName());
                Fighting.opponentSuccessAction(opponentChoices[i], choices[i], false);
                Fighting.playerFailedAction(choices[i]);
            } else {
                System.out.println(player.getName() + " throws a " + attack[choices[i]][0] + " to " + opponent.getName());
                System.out.println(opponent.getName() + " draws " + player.getName() + " with " + attack[opponentChoices[i]][0]);
                Fighting.drawAction(choices[i], opponentChoices[i]);
            }
            if(player.getHp() <= 0 || opponent.getHp() <= 0){
                return;
            }
            GameLogic.printSeparator(50);
        }
    }

    public static void fightLoop() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("Round " + playerProgress.getRound(), 40));
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("You are fighting " + opponent.getName() + "!", 40));
        System.out.println();
        GameLogic.printSeparator(40);
        Fighting.setPlayerOpponent(player);
        Fighting.setOpponent(opponent);
        player.setOpponent(opponent);
        printStats();
        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            if (player.getHp() <= 0) {
                System.out.println();
                System.out.println(player.getName() + " is knocked out! " + opponent.getName() + " wins!");
                player.setIsLose(true);
                playerProgress.setRound(playerProgress.getRound() + 1);
                opponent.setHp(opponent.getMaxHp());
                opponent.setStamina(opponent.getMaxStamina());
            } else if(opponent.getHp() <= 0){
                System.out.println();
                System.out.println(opponent.getName() + " is knocked out! " + player.getName() + " wins!");
                player.setIsLose(false);
                winnerReward();
                playerProgress.setRound(0);  
                playerProgress.setShopStage(2);  
                UrbanStory.urbanTraining8(player.getName());    
                GameLogic.pressAnything();
                return;
            }
        }
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        GameLogic.pressAnything();
    }

    static int isCounter(int opponentMove, int playerMove) {
        return switch (opponentMove) {
            case 0 -> (playerMove == 1) ? 1 : (playerMove == 3) ? 2 : 0;
            case 1 -> (playerMove == 2) ? 1 : (playerMove == 0) ? 2 : 0;
            case 2 -> (playerMove == 3) ? 1 : (playerMove == 1) ? 2 : 0;
            case 3 -> (playerMove == 0) ? 1 : (playerMove == 2) ? 2 : 0;
            default -> -1;
        };
    }

    static void winnerReward() {
        System.out.println(); 
        GameLogic.printSeparator(40);
        System.out.println("Congratulations! You've won the match!");
        System.out.println();  
        System.out.println("Fred is giving you 100 points as a starter pack to get you started.");
        GameLogic.addPoints(100);
        System.out.println();
        System.out.println("You now have " + player.getPlayerPoints() + " points.");
        System.out.println();
        System.out.println("You can now visit the shop and use your points to buy items.");
        GameLogic.printSeparator(40);
        GameLogic.pressAnything();
    }

}