import java.util.Random;

public class UrbanGym {
    public static Player player;
    static String[] attack = {"Jab", "Hook", "Block", "Uppercut"};
    static StreetFighter opponent = new StreetFighter("Carlito Cortez", 200, 100, 0.2, 2, .20);
    
    public static void setPlayer(Player p) {
        player = p;
    }

    static void printStats(){
        System.out.println();
        System.out.println(player.getName() + "\t\t\t\t" + opponent.getName());
        System.out.println("HP\t  " + player.getHp() + "/" + player.getMaxHp() + "  \t\tHP\t  " + opponent.getHp() + "/" + opponent.getMaxHp());
        System.out.println("Stamina\t  " + player.getStamina() + "/" + player.getMaxStamina() + "\t\t\tStamina\t  " + opponent.getStamina() + "/" + opponent.getMaxStamina());
        System.out.println();
    }
    
    static void selectAttack() {
        System.out.println();
        System.out.println("You're the first one to attack!");
        System.out.println("Select 3 combos");
    
        for (int i = 1; i <= attack.length; i++) {
            System.out.println(i + ") " + attack[i - 1]);
        }
    
        String input = GameLogic.scan.nextLine();
        Random rand = new Random();
    
        while (!isValidCombo(input)) {
            System.out.println("Please enter a valid combo (e.g., 123):");
            input = GameLogic.scan.nextLine();
        }
    
        int[] choices = new int[3];
        int[] opponentChoices = new int[3];
        
        for (int i = 0; i < 3; i++) {
            choices[i] = Character.getNumericValue(input.charAt(i) - 1);
            opponentChoices[i] = rand.nextInt(4);
        }
    
        System.out.println();
        System.out.println("You've selected:\t\tOpponent selected:");
        for(int i = 0; i < 3; i++){
            System.out.println(attack[choices[i]] + "     \t\t\t" + attack[opponentChoices[i]]);
        }

        System.out.println();
        printFight(choices, opponentChoices);
    }
    
    private static boolean isValidCombo(String input) {
        if (input.length() != 3) {
            return false;
        }
    
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || c < '1' || c > '4') {
                return false;
            }
        }
        return true;
    }
    
    static void printFight(int[] choices, int[] opponentChoices){
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentChoices[i], choices[i]);
            if(countered == 1){
                System.out.println(player.getName() + " throws a " + attack[choices[i]] + " to " + opponent.getName());
                System.out.println(opponent.getName() + " fails to counter " + player.getName() + " with " + attack[opponentChoices[i]]);
                player.performAction(choices[i]);
            } else if(countered == 2){
                System.out.println(player.getName() + " throws a " + attack[choices[i]] + " to " + opponent.getName());
                System.out.println(opponent.getName() + " successfully counters " + player.getName() + " by " + attack[opponentChoices[i]]);
                opponent.performAction(choices[i]);
            } else {
                System.out.println(player.getName() + " throws a " + attack[choices[i]] + " to " + opponent.getName());
                System.out.println(opponent.getName() + " draws " + player.getName() + " with " + attack[opponentChoices[i]]);
            }
            GameLogic.printSeparator(50);
        }
    }

    public static void fightLoop() {
        GameLogic.clearConsole();
        System.out.println("You are fighting " + opponent.getName() + "!");
        System.out.println();
        GameLogic.printSeparator(40);
        player.setOpponent(opponent);
        do {
            printStats();
            selectAttack();
            printStats();
            GameLogic.pressAnything();
        } while (player.getHp() > 0);
    }

    static int isCounter(int opponentMove, int playerMove) {
        switch (opponentMove) {
            case 0: 
                return (playerMove == 1) ? 1 : (playerMove == 3) ? 2 : 0;
            case 1:
                return (playerMove == 2) ? 1 : (playerMove == 0) ? 2 : 0;
            case 2: 
                return (playerMove == 3) ? 1 : (playerMove == 1) ? 2 : 0;
            case 3:
                return (playerMove == 0) ? 1 : (playerMove == 2) ? 2 : 0;
            default:
                return -1;
        }
    }
}