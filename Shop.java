public class Shop {
    private static Player player;
    private static int stage;
    static Item[] items = {
        new Item("Wrist Wraps", "Protects hands during training, boosting strength and critical hit chance.", 50, "+5% Health, +5% Critical Hit Chance"),
        new Item("Light Training Gloves", "Increases punch speed and improves dodge ability.", 75, "+10% Stamina, +5% Dodge Chance"),
        new Item("Protein Bar", "A quick recovery boost after training.", 30, "+10% Stamina Recovery"),
        new Item("Beginner's Jump Rope", "Improves footwork and stamina.", 40, "+5% Stamina"),
        new Item("Basic Energy Drink", "Increases stamina for the next fight.", 20, "+10% Stamina for next fight"),
    };

    public Shop(Player player){
        Shop.player = player;
    }

    public static Player getPlayer(){
        return player;
    }

    static void setStage(int stage){
        Shop.stage = stage;
    }

    static int getStage(){
        return stage;
    }
    
    public static class Item {
        String name;
        String description;
        int cost;
        String effect;

        public Item(String name, String description, int cost, String effect) {
            this.name = name;
            this.description = description;
            this.cost = cost;
            this.effect = effect;
        }

        public void displayItem() {
            System.out.println("\t-> " + description);
            System.out.println("\tCost:\tPHP " + cost);
            System.out.println("\tEffect:\t" + effect);
            System.out.println();
        }
    }

    public static void showMenu(){
        GameLogic.clearConsole();
        GameLogic.printHeading("    Urban Gym");
        System.out.println();
        System.out.println("Select where to go:");
        System.out.println("\t(1) Practice in Gym");
        System.out.println("\t(2) Go to Fred's Shop");
        int choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1){
            UrbanGym.setPlayer(player);
            UrbanGym.fightLoop();
        } else {
            showShop();
        }
    }

    public static void shop() {
        GameLogic.clearConsole();
        GameLogic.printHeading("        Gym Shop");

        System.out.println("Fred: \t\"Welcome to the shop. Here, you can buy all sorts of gear and items that'll");
        System.out.println("\thelp boost your stats. You want more stamina? We've got special protein shakes for that.");
        System.out.println("\tNeed a better chance to land critical hits? Try out our precision wraps. These items can");
        System.out.println("\tbe real game-changers if you use them right.\"");

        GameLogic.pressAnything();
        showShop();
    }

    static void showShop(){
        System.out.println();
        GameLogic.printHeading("        Gym Shop");
        System.out.println();
        
        for (int i = 0; i < items.length; i++) {
            System.out.println("(" + (i + 1) + ") " + items[i].name);
            items[i].displayItem();
        }

        System.out.println("Enter the number of the item you wish to buy or 0 to exit.");
        int choice = GameLogic.readInt("-> ", 0, items.length);
        if(getStage() < 1){
            UrbanStory.urbanTraining7();
        } else {
            if(choice == 0){
                GameLogic.printMenu();
                return;
            } else {
                GameLogic.printSeparator(35);
                System.out.println("You've purchased 1 " + items[choice-1].name);
                GameLogic.printSeparator(35);
                System.out.println();
                GameLogic.pressAnything();
            }
        }
        
    }
}
