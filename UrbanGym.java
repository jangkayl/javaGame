import java.util.Random;

public class UrbanGym {
    static Player player;
    static String[] attack = {"Jab", "Hook", "Block", "Uppercut"};
    static StreetFighter opponent = new StreetFighter("Carlito Cortez", 200, 100, 0.2, 2, .20);
    
    public static void setPlayer(Player p) {
        player = p;
    }

    static void printStats(){
        System.out.println();
        System.out.println(player.getName() + "\t\t\t\t" + opponent.getName());
        System.out.println("HP\t  " + player.getHp() + "/" + player.getMaxHp() + "\t\tHP\t  " + opponent.getHp() + "/" + opponent.getMaxHp());
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
            choices[i] = Character.getNumericValue(input.charAt(i));
            opponentChoices[i] = rand.nextInt(4) + 1;
        }
    
        System.out.println("You've selected:");
        for (int choice : choices) {
            System.out.println(attack[choice - 1]);
        }
        
        System.out.println();
        System.out.println("Opponent selected:");
        for (int opponentChoice : opponentChoices) {
            System.out.println(attack[opponentChoice - 1]);
        }
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

    public static void fightLoop() {
        GameLogic.clearConsole();
        System.out.println("You are fighting " + opponent.getName() + "!");
        System.out.println();
        GameLogic.printSeparator(40);
        do {
            printStats();
            selectAttack();

            GameLogic.pressAnything();
        } while (player.getHp() > 0);
    }
    
    static void printFight(){
        
    }
}