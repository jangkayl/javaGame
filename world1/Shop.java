package world1;

import world1.TrainInGym.UrbanGym;

public class Shop {
    static Player player;
    static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static Item[] items = {
        new Item("Wrist Wraps", "Protects hands during training, boosting strength and critical hit chance.", 75, "+10% Health, +5% Critical Hit Chance","false","HAND"),
        new Item("Light Training Gloves", "Increases punch speed and improves dodge ability.", 75, "+15% Stamina, +5% Dodge Chance","false","HAND"),
        new Item("Warrior's Helmet", "A sturdy helmet that provides excellent protection and boosts health recovery.", 30, "+10% Health", "false", "HEAD"),
        new Item("Beginner's Boots", "Improves footwork and stamina.", 40, "+13% Stamina","false","BOOTS"),
        new Item("Basic Energy Drink", "Increases stamina for the next fight.", 20, "+15% Stamina for next fight","false","FOOD"),
    };

    public Shop(Player p, PlayerProgress progress){
        player = p;
        playerProgress = progress;
    }
    
    public static class Item {
        String name;
        String description;
        int cost;
        String effect;
        String status;
        String body;

        public Item(String name, String description, int cost, String effect, String status, String body) {
            this.name = name;
            this.description = description;
            this.cost = cost;
            this.effect = effect;
            this.status = status;
            this.body = body;
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

    public static String getItemNameByIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items[index].name;
    }

    public static String getItemDescriptionByIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items[index].description;
    }

    public static String getItemBodyByIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items[index].body;
    }

    public static String getItemEffectByIndex(int index) {
        if (index < 0 || index >= items.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items[index].effect;
    }

    public static int getItemIndexByDescription(String description) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && description.trim().equals(items[i].description.trim())) {
                return i; 
            }
        }
        return -1; 
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
            StreetFighter.setPlayerOpponent(player);
            UrbanGym.fightLoop();
        } else {
            showShop(true);
        }
    }

    static void showShop(boolean isTraining) {
        int choice = 0;
        boolean isSold = false;

        System.out.println();
        GameLogic.printHeading("        Gym Shop");
        System.out.println();
        
        GameLogic.printSeparator(20);
        System.out.println("Player Points: " + player.getPlayerPoints());
        GameLogic.printSeparator(20);
        System.out.println();
        
        for (int i = 0; i < items.length; i++) {
            System.out.println("(" + (i + 1) + ") " + items[i].name);
            items[i].displayItem();
        }
        
        System.out.println("Enter the number of the item you wish to buy or 0 to exit.");

        if(playerProgress.getShopStage() < 1 && isTraining){
            choice = GameLogic.readInt("-> ", 0, items.length);
            UrbanStory.urbanTraining7();
        } else {
            while (!isSold) {
                choice = GameLogic.readInt("-> ", 0, items.length);
        
                if (choice == 0) {
                    System.out.println("Exiting the shop...");
                    return;  
                }
                
                if (soldChecker(choice)) {
                    System.out.println(items[choice - 1].name + " is already sold. Choose another item.");
                    isSold = false;
                } else {
                    if (!notEnoughtPoints(choice)) {      
                        isSold = true;
                        player.setPlayerPoints(player.getPlayerPoints() - items[choice - 1].cost);
                        
                    } else {
                        System.out.println("You don't have enough points to buy " + items[choice - 1].name + ". Please choose another item or earn more points.");
                    }
                }
            }

            switch(choice){
                case 1:
                    if(items[choice-1].isSoldOut()) break;
                    GameLogic.printSeparator(35);
                    System.out.println("You bought " + items[choice - 1].name + " for " + items[choice - 1].cost + " points.");
                    GameLogic.printSeparator(35);
                    System.out.println();
                    Inventory.setInventory(items[choice-1].name, items[choice-1].description, items[choice-1].body, items[choice-1].effect);
                    items[choice-1].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 2:
                    if(items[choice-1].isSoldOut()) break;
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 2 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    Inventory.setInventory(items[choice-1].name, items[choice-1].description, items[choice-1].body, items[choice-1].effect);
                    items[choice-1].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 3:
                    if(items[choice-1].isSoldOut()) break;
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 3 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    Inventory.setInventory(items[choice-1].name, items[choice-1].description, items[choice-1].body, items[choice-1].effect);
                    items[choice-1].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 4:
                    if(items[choice-1].isSoldOut()) break;
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 4 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    Inventory.setInventory(items[choice-1].name, items[choice-1].description, items[choice-1].body, items[choice-1].effect);
                    items[choice-1].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                case 5:
                    if(items[choice-1].isSoldOut()) break;
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased 5 " + items[choice-1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
                    items[choice-1].setSoldOut();
                    GameLogic.pressAnything();
                    break;
                }
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
        showShop(true);
    }

    static boolean soldChecker(int choice){
        if(items[choice-1].isSoldOut()){
            return true;
        }
        return false;
    }
    
    static boolean notEnoughtPoints(int choice){
        if(player.getPlayerPoints() < items[choice-1].cost){
            return true;
        }
        return false;
    }

    public static void applyEffect(String effect){
        int choice = 0 ;
        for(int i = 0 ; i < 5 ; i++){
            if(effect.equals(items[i].effect)){
                choice = i;
                break;
            }
        }
        System.out.println();

        switch(choice){
            case 0:
                applyHpEffect(0.10, 0.05,choice);
                break;
            case 1:
                applyStaminaEffect(0.15, 0.05,choice);
                break;
            case 2:
                applyHpEffect(0.10, 0,choice);
                break;
            case 3:
                applyStaminaEffect(0.13, 0,choice);
                break;
            case 4:
                applyStaminaEffect(0.13, 0,choice);
                break;
            default:
                System.out.println("Invalid item choice.");
        }
    }

    public static void removeEffect(String effect){
        int choice = 0 ;
        for(int i = 0 ; i < 5 ; i++){
            if(effect.equals(items[i].effect)){
                choice = i;
                break;
            }
        }
        
        switch(choice){
            case 0:
                removeHpEffect(0.10, 0.05,choice);
                break;
            case 1:
                removeStaminaEffect(0.15, 0.05,choice);
                break;
            case 2:
                removeHpEffect(0.10, 0,choice);
                break;
            case 3:
                removeStaminaEffect(0.13, 0,choice);
                break;
            case 4:
                removeStaminaEffect(0.15, 0,choice);
                break;
            default:
                System.out.println("Invalid item choice.");
        }
    }

    private static void applyHpEffect(double hpIncreasePercentage, double critChanceIncreasePercentage,int choice) {
        double hpMultiplier = 1 + hpIncreasePercentage;
        int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
        player.setHp(maxHp);
        player.setMaxHp(maxHp);
        
        if (critChanceIncreasePercentage > 0) {
            double newCrit = player.getCritChance() + critChanceIncreasePercentage;
            player.setCritChance(newCrit);
        }
    }
    
    private static void applyStaminaEffect(double stamIncreasePercentage, double dodgeChanceIncreasePercentage,int choice) {
        double staminaMultiplier = 1 + stamIncreasePercentage;
        int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
        player.setStamina(maxStamina);
        player.setMaxStamina(maxStamina);
        
        if (dodgeChanceIncreasePercentage > 0) {
            double newDodge = player.getDodgeChance() + dodgeChanceIncreasePercentage;
            player.setDodgeChance(newDodge);
        }
    }

    private static void removeHpEffect(double hpIncreasePercentage, double critChanceIncreasePercentage,int choice) {
        double hpMultiplier = 1 + hpIncreasePercentage;
        int maxHp = (int) Math.floor(player.getMaxHp() / hpMultiplier);
        player.setHp(maxHp);
        player.setMaxHp(maxHp);
        
        if (critChanceIncreasePercentage > 0) {
            double newCrit = player.getCritChance() - critChanceIncreasePercentage;
            player.setCritChance(newCrit);
        }
    }
    
    private static void removeStaminaEffect(double stamIncreasePercentage, double dodgeChanceIncreasePercentage,int choice) {
        double staminaMultiplier = 1 + stamIncreasePercentage;
        int maxStamina = (int)Math.floor(player.getMaxStamina() / staminaMultiplier);
        player.setStamina(maxStamina);
        player.setMaxStamina(maxStamina);
        
        if (dodgeChanceIncreasePercentage > 0) {
            player.setDodgeChance(player.getDodgeChance() - dodgeChanceIncreasePercentage);
        }
    }
}
