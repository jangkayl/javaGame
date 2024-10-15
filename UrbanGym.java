public class UrbanGym {
    static Player player;
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
        String[] attack = {"Jab", "Hook", "Block", "Uppercut"};
        System.out.println("You're the first one to attack!");
        System.out.println("Select 3 combos");
    
        for (int i = 1; i <= attack.length; i++) {
            System.out.println(i + ") " + attack[i - 1]);
        }
    
        String input = GameLogic.scan.nextLine();
    
        while (!isValidCombo(input)) {
            System.out.println("Please enter a valid combo (e.g., 123):");
            input = GameLogic.scan.nextLine();
        }
    
        int[] choices = new int[3];
        for (int i = 0; i < 3; i++) {
            choices[i] = Character.getNumericValue(input.charAt(i));
        }
    
        System.out.println("You've selected:");
        for (int choice : choices) {
            System.out.println(attack[choice - 1]);
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
}
