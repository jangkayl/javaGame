package world1;

import world1.TrainInGym.UrbanGym;

public class Shop {
    static Player player;
    static Inventory inventory = new Inventory();
    static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static Item[] items = {
        // World 1 Items
        new Item("Wrist Wraps", "Protects hands during training, boosting strength and critical hit chance.", 75, "+10% Health, +5% Critical Hit Chance","false","HAND", 0.1, 0, 0.05, 0, 0),
        new Item("Light Training Gloves", "Increases punch speed and improves dodge ability.", 75, "+15% Stamina, +5% Dodge Chance","false","HAND", 0, 0.15, 0, 0, 0.05),
        new Item("Warrior's Helmet", "A sturdy helmet that provides excellent protection and boosts health recovery.", 30, "+10% Health", "false", "HEAD", 0.1, 0, 0, 0, 0),
        new Item("Beginner's Boots", "Improves footwork and stamina.", 40, "+13% Stamina","false","BOOTS", 0, 0.13, 0, 0, 0),

        // World 2 Items
        new Item("Reinforced Headband", "Tough headband that protects the skull and conceals small illegal items.", 200, "+10% HP, +5% Crit Chance, +15% Dodge Chance","false","HEAD", 0.1, 0, 0.05, 0, 0.15),
        new Item("Blood-Forged Knuckles", "Powerful knuckles for brutal punches, but reduce health over time due to strain on the hands.", 300, "-10% Health, +20% Crit Hit Chance","false","HAND", 0.1, 0, 0.2, 0, 0),
        new Item("Shadowrunner Sneakers", "Lightweight shoes that improve agility but provide less protection.", 150, "+20% Stamina, +10% Dodge Chance, -10% HP","false","BOOTS", -0.1, 0.2, 0, 0, 0.1),
        new Item("Tactical Combat Boots", "Sturdy boots that improve footwork and durability.", 150, "+10% Health, +10% Crit Hit Chance, +10% Stamina", "false", "BOOTS", 0.1, 0.1, 0.1, 0, 0),
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
        double hp, stamina, critChance, critMulti, dodgeChance;

        public Item(String name, String description, int cost, String effect, String status, String body, double hp, double stamina, double critChance, double critMulti, double dodgeChance) {
            this.name = name;
            this.description = description;
            this.cost = cost;
            this.effect = effect;
            this.status = status;
            this.body = body;
            this.hp = hp;
            this.stamina = stamina;
            this.critChance = critChance;
            this.critMulti = critMulti;
            this.dodgeChance = dodgeChance;
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

        public double getHp(){
            return hp;
        }

        public double getStamina(){
            return stamina;
        }

        public double getCritChance(){
            return critChance;
        }

        public double getCritMulti(){
            return critMulti;
        }

        public double getDodgeChance(){
            return dodgeChance;
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
    
    public void showMenu(){
        UrbanGym.setPlayer(player);
        UrbanGym.fightLoop();
    }

    public void showShop(boolean isTraining) {
        while (true) {
            int choice = -1;
            boolean isSold = false;
    
            System.out.println();
            if(player.getCurrentWorld() == 0){
                GameLogic.printHeading("        Gym Shop");
            } else if(player.getCurrentWorld() == 1){
                GameLogic.printHeading("        Black Market");
            }
            System.out.println();
    
            GameLogic.printSeparator(20);
            System.out.println("Player Points: " + player.getPlayerPoints());
            GameLogic.printSeparator(20);
            System.out.println();
    
            // Display items for sale
            if(player.getCurrentWorld() == 0){
                for (int i = 0; i < items.length; i++) {
                    System.out.println("(" + (i + 1) + ") " + items[i].name);
                    items[i].displayItem();
                }
            } else if(player.getCurrentWorld() == 1){
                for (int i = 4; i < items.length; i++) {
                    System.out.println("(" + (i - 3) + ") " + items[i].name);
                    items[i].displayItem();
                }
            }
    
            System.out.println("Enter the number of the item you wish to buy or 0 to exit.");
    
            // Training check before entering purchase loop
            if (playerProgress.getShopStage() < 1 && isTraining) {
                choice = GameLogic.readInt("-> ", 0, items.length);
                UrbanStory.urbanTraining7();
                return;
            }
    
            // Purchase loop
            while (!isSold) {
                choice = GameLogic.readInt("-> ", 0, 4);

                // If player is in World2
                if(player.getCurrentWorld() == 1 && choice != 0){
                    choice += 4;
                }

                if (choice == 0) {
                    System.out.println("Exiting the shop...");
                    GameLogic.pressAnything();
                    return;
                }
    
                if (soldChecker(choice)) {
                    System.out.println(items[choice - 1].name + " is already sold. Choose another item.");
                } else if (notEnoughPoints(choice)) {
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
                    inventory.setInventory(items[choice - 1].name, items[choice - 1].description, items[choice - 1].body, items[choice - 1].effect);
    
                    // Mark item as sold and set isSold to true to exit inner loop
                    items[choice - 1].setSoldOut();
                    isSold = true;
                }
            }
    
            GameLogic.pressAnything(); 
        }
    }

    public void shop() {
        GameLogic.clearConsole();
        GameLogic.printHeading("        Gym Shop");

        System.out.println("Fred: \t\"Welcome to the shop. Here, you can buy all sorts of gear and items that'll");
        System.out.println("\thelp boost your stats. You want more stamina? We've got special protein shakes for that.");
        System.out.println("\tNeed a better chance to land critical hits? Try out our precision wraps. These items can");
        System.out.println("\tbe real game-changers if you use them right.\"");

        GameLogic.pressAnything();
        showShop(true);
    }

    private boolean soldChecker(int choice) {
        return items[choice - 1].isSoldOut();
    }

    private boolean notEnoughPoints(int choice) {
        return player.getPlayerPoints() < items[choice - 1].getCost();
    }

    // Apply Effect
    public void applyEffect(String effect){
        int choice = 0 ;
        for(int i = 0 ; i < items.length; i++){
            if(effect.equals(items[i].effect)){
                choice = i;
                break;
            }
        }
        System.out.println();

        if(items[choice].getCritChance() != 0 || items[choice].getHp() != 0){
            applyHpAndCritChanceEffect(items[choice].getHp(), items[choice].getCritChance());
        }

        if(items[choice].getStamina() != 0 || items[choice].getDodgeChance() != 0){
            applyStaminaAndDodgeChanceEffect(items[choice].getStamina(), items[choice].getDodgeChance());
        }
    }

    // Remove Effect
    public void removeEffect(String effect){
        int choice = 0 ;
        for(int i = 0 ; i < items.length; i++){
            if(effect.equals(items[i].effect)){
                choice = i;
                break;
            }
        }

        if(items[choice].getCritChance() != 0 || items[choice].getHp() != 0){
            removeHpAndCritChanceEffect(items[choice].getHp(), items[choice].getCritChance());
        }

        if(items[choice].getStamina() != 0 || items[choice].getDodgeChance() != 0){
            removeStaminaAndDodgeChanceEffect(items[choice].getStamina(), items[choice].getDodgeChance());
        }
    }

    private void applyHpAndCritChanceEffect(double hpIncreasePercentage, double critChanceIncreasePercentage) {
        double hpMultiplier = 1 + hpIncreasePercentage;
        int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
        player.setHp(maxHp);
        player.setMaxHp(maxHp);
        
        if (critChanceIncreasePercentage > 0) {
            double newCrit = player.getCritChance() + critChanceIncreasePercentage;
            player.setCritChance(newCrit);
        }
    }
    
    private void applyStaminaAndDodgeChanceEffect(double stamIncreasePercentage, double dodgeChanceIncreasePercentage) {
        double staminaMultiplier = 1 + stamIncreasePercentage;
        int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
        player.setStamina(maxStamina);
        player.setMaxStamina(maxStamina);
        
        if (dodgeChanceIncreasePercentage > 0) {
            double newDodge = player.getDodgeChance() + dodgeChanceIncreasePercentage;
            player.setDodgeChance(newDodge);
        }
    }

    private void removeHpAndCritChanceEffect(double hpIncreasePercentage, double critChanceIncreasePercentage) {
        double hpMultiplier = 1 + hpIncreasePercentage;
        int maxHp = (int) Math.floor(player.getMaxHp() / hpMultiplier);
        player.setHp(maxHp);
        player.setMaxHp(maxHp);
        
        if (critChanceIncreasePercentage > 0) {
            double newCrit = player.getCritChance() - critChanceIncreasePercentage;
            player.setCritChance(newCrit);
        }
    }
    
    private void removeStaminaAndDodgeChanceEffect(double stamIncreasePercentage, double dodgeChanceIncreasePercentage) {
        double staminaMultiplier = 1 + stamIncreasePercentage;
        int maxStamina = (int)Math.floor(player.getMaxStamina() / staminaMultiplier);
        player.setStamina(maxStamina);
        player.setMaxStamina(maxStamina);
        
        if (dodgeChanceIncreasePercentage > 0) {
            player.setDodgeChance(player.getDodgeChance() - dodgeChanceIncreasePercentage);
        }
    }
}
