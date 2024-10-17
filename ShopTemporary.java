public class ShopTemporary {
    static Player player;
    private static int stage;
    static Item[] items = {
        new Item("Wrist Wraps", "Protects hands during training, boosting strength and critical hit chance.", 75, "+10% Health, +5% Critical Hit Chance","false"),
        new Item("Light Training Gloves", "Increases punch speed and improves dodge ability.", 75, "+15% Stamina, +5% Dodge Chance","false"),
        new Item("Protein Bar", "A quick recovery boost after training.", 30, "+10% Health","false"),
        new Item("Beginner's Jump Rope", "Improves footwork and stamina.", 40, "+13% Stamina","false"),
        new Item("Basic Energy Drink", "Increases stamina for the next fight.", 20, "+15% Stamina for next fight","false"),
    };
    
    public ShopTemporary(Player player){
        ShopTemporary.player = player;
    }

    public static Player getPlayer(){
        return player;
    }

    static void setStage(int stage){
        ShopTemporary.stage = stage;
    }

    static int getStage(){
        return stage;
    }
    
    public static class Item {
        String name;
        String description;
        int cost;
        String effect;
        String status;

        public Item(String name, String description, int cost, String effect, String status) {
            this.name = name;
            this.description = description;
            this.cost = cost;
            this.effect = effect;
            this.status = status;
        }

        public void displayItem() {
            System.out.println("\t-> " + description);
            System.out.println("\tCost:\tPHP " + cost);
            System.out.println("\tEffect:\t" + effect);
            System.out.println();
        }
        
       public boolean isSoldOut() {
            return "true".equals(status);
        }

        public void setSoldOut() {
            this.status = "true";
            this.name += " - SOLD OUT";
        }
    }
    

    public static void showMenu(){
        showShop();
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
        int choice = GameLogic.readInt("-> ", 1, items.length);
            
            switch(choice){
                case 1:
                    if(items[0].isSoldOut()){
                        break;
                    }
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 1 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    player.setMaxHp((int)(player.getMaxHp() + (player.getMaxHp() * 0.10)));
                    player.setCritChance(player.getCritChance() +(player.getCritChance() * .5));
                    items[0].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 2:
                    if(items[1].isSoldOut()){
                        break;
                    }
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 2 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    player.setMaxStamina((int) (player.getMaxStamina() + (player.getMaxStamina() * 0.15)));
                    player.setDodgeChance(player.getDodgeChance() +(player.getDodgeChance() * .5));
                    items[1].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 3:
                    if(items[2].isSoldOut()){
                        break;
                    }
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 3 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    player.setMaxHp((int) (player.getMaxHp() + (player.getMaxHp() * 0.10)));
                    items[2].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 4:
                    if(items[3].isSoldOut()){
                        break;
                    }
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 4 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    player.setStamina((int) (player.getStamina() + (player.getStamina() * 0.10)));
                    items[3].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 5:
                    if(items[4].isSoldOut()){
                        break;
                    }
                    boolean drink = true;
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 5 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    player.setStamina((int) (player.getStamina() + (player.getStamina() * 0.15)));
                    items[4].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 0:
                    GameLogic.printMenu();
                    return;
            }

    }
    
    
    
}
