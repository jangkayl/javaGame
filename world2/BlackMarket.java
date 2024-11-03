package world2;

import world1.GameLogic;
import world1.Inventory;
import world1.Player;
import world1.PlayerProgress;

public class BlackMarket {
    static Player player;
    static Inventory inventory = new Inventory();
    static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static Item[] items = {
        new Item("Reinforced Headband", "Tough headband that protects the skull and conceals small illegal items.", 100, "+10% HP, +5% Crit Chance, +10% Dodge Chance","false","HEAD"),
        new Item("Blood-Forged Knuckles", "Powerful knuckles for brutal punches, but reduce health over time due to strain on the hands.", 150, "-10% Health, +15% Crit Hit Chance","false","HAND"),
        new Item("Shadowrunner Sneakers", "Lightweight shoes that improve agility but provide less protection.", 75, "+15% Stamina, +10% Dodge Chance, -10% HP","false","BOOTS"),
        new Item("Tactical Combat Boots", "Sturdy boots that improve footwork and durability.", 80, "+10% Health, +5% Crit Hit Chance, +10% Stamina", "false", "BOOTS"),
    };

    public BlackMarket(Player p, PlayerProgress progress){
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
        
        public String getName(){
            return name;
        }

        public String getDescription(){
            return description;
        }

        public int getCost(){
            return cost;
        }

        public String getEffect(){
            return effect;
        }

        public String getStatus(){
            return status;
        }

        public String getBody(){
            return body;
        }
    }

    public int getItemIndexByDescription(String description) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && description.trim().equals(items[i].description.trim())) {
                return i; 
            }
        }
        return -1; 
    }

    public void showShop(boolean isTraining) {
        GameLogic.clearConsole();
        while (true) {
            int choice = -1;
            boolean isSold = false;
    
            System.out.println();
            GameLogic.printHeading("        Black Market");
            System.out.println();
    
            GameLogic.printSeparator(20);
            System.out.println("Player Points: " + player.getPlayerPoints());
            GameLogic.printSeparator(20);
            System.out.println();
    
            // Display items for sale
            for (int i = 0; i < items.length; i++) {
                System.out.println("(" + (i + 1) + ") " + items[i].name);
                items[i].displayItem();
            }
    
            System.out.println("Enter the number of the item you wish to buy or 0 to exit.");
    
            // Purchase loop
            while (!isSold) {
                choice = GameLogic.readInt("-> ", 0, items.length);
                if (choice == 0) {
                    System.out.println("Exiting the shop...");
                    GameLogic.pressAnything();
                    return;
                }
    
                if (soldChecker(choice)) {
                    System.out.println(items[choice - 1].name + " is already sold. Choose another item.");
                } else if (notEnoughtPoints(choice)) {
                    System.out.println("You don't have enough points to buy " + items[choice - 1].name + ". Please choose another item or earn more points.");
                } else {
                    // Successful purchase
                    System.out.println();
                    player.setPlayerPoints(player.getPlayerPoints() - items[choice - 1].cost);
                    GameLogic.printSeparator(35);
                    System.out.println("You've purchased " + items[choice - 1].name);
                    GameLogic.printSeparator(35);
                    System.out.println();
    
                    // Add item to inventory
                    if (choice != 5) {
                        inventory.setInventory(items[choice - 1].name, items[choice - 1].description, items[choice - 1].body, items[choice - 1].effect);
                    }
    
                    // Mark item as sold and set isSold to true to exit inner loop
                    items[choice - 1].setSoldOut();
                    isSold = true;
                }
            }
    
            GameLogic.pressAnything(); 
        }
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

    public void applyEffect(String effect){
        int choice = 0 ;
        for(int i = 0 ; i < items.length ; i++){
            if(effect.equals(items[i].effect)){
                choice = i;
                break;
            }
        }
        System.out.println();

        switch(choice){
            case 0:
                applyHpEffect(0.10, 0.05,choice);
                applyStaminaEffect(0, 0.10,choice);
                break;
            case 1:
                removeHpEffect(0.10, 0,choice);
                applyStaminaEffect(0.15, 0,choice);
                break;
            case 2:
                removeHpEffect(0.10, 0,choice);
                applyStaminaEffect(0.15, 0.1,choice);
                break;
            case 3:
                applyHpEffect(0.10, 0.05,choice);
                applyStaminaEffect(0.10, 0,choice);
                break;
            default:
                System.out.println("Invalid item choice.");
        }
    }

    public void removeEffect(String effect){
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
                removeStaminaEffect(0, 0.10,choice);
                break;
            case 1:
                applyHpEffect(0.10, 0,choice);
                removeStaminaEffect(0.15, 0,choice);
                break;
            case 2:
                applyHpEffect(0.10, 0,choice);
                removeStaminaEffect(0.15, 0.1,choice);
                break;
            case 3:
                removeHpEffect(0.10, 0.05,choice);
                removeStaminaEffect(0.10, 0,choice);
                break;
            default:
                System.out.println("Invalid item choice.");
        }
    }

    private void applyHpEffect(double hpIncreasePercentage, double critChanceIncreasePercentage,int choice) {
        double hpMultiplier = 1 + hpIncreasePercentage;
        int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
        player.setHp(maxHp);
        player.setMaxHp(maxHp);
        
        if (critChanceIncreasePercentage > 0) {
            double newCrit = player.getCritChance() + critChanceIncreasePercentage;
            player.setCritChance(newCrit);
        }
    }
    
    private void applyStaminaEffect(double stamIncreasePercentage, double dodgeChanceIncreasePercentage,int choice) {
        double staminaMultiplier = 1 + stamIncreasePercentage;
        int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
        player.setStamina(maxStamina);
        player.setMaxStamina(maxStamina);
        
        if (dodgeChanceIncreasePercentage > 0) {
            double newDodge = player.getDodgeChance() + dodgeChanceIncreasePercentage;
            player.setDodgeChance(newDodge);
        }
    }

    private void removeHpEffect(double hpIncreasePercentage, double critChanceIncreasePercentage,int choice) {
        double hpMultiplier = 1 + hpIncreasePercentage;
        int maxHp = (int) Math.floor(player.getMaxHp() / hpMultiplier);
        player.setHp(maxHp);
        player.setMaxHp(maxHp);
        
        if (critChanceIncreasePercentage > 0) {
            double newCrit = player.getCritChance() - critChanceIncreasePercentage;
            player.setCritChance(newCrit);
        }
    }
    
    private void removeStaminaEffect(double stamIncreasePercentage, double dodgeChanceIncreasePercentage,int choice) {
        double staminaMultiplier = 1 + stamIncreasePercentage;
        int maxStamina = (int)Math.floor(player.getMaxStamina() / staminaMultiplier);
        player.setStamina(maxStamina);
        player.setMaxStamina(maxStamina);
        
        if (dodgeChanceIncreasePercentage > 0) {
            player.setDodgeChance(player.getDodgeChance() - dodgeChanceIncreasePercentage);
        }
    }
}
