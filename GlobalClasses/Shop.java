package GlobalClasses;

import world1.GameLogic;
import world1.TrainInGym.CarlitoUrbanGym;
import interfaces.ShopInterface;
import world1.UrbanStory;

public class Shop implements ShopInterface{
    private static Player player;
    private static Inventory inventory = new Inventory();
    private static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static Item[] items = {
        // World 1 Items
        new Item("Wrist Wraps", "Protects hands during training, boosting strength and critical hit chance.", 75, "+10% Health, +5% Critical Hit Chance","false","HAND", 0.1, 0, 0.05, 0, 0),
        new Item("Light Training Gloves", "Increases punch speed and improves dodge ability.", 75, "+15% Stamina, +5% Dodge Chance","false","HAND", 0, 0.15, 0, 0, 0.05),
        new Item("Warrior's Helmet", "A sturdy helmet that provides excellent protection and boosts health recovery.", 30, "+10% Health", "false", "HEAD", 0.1, 0, 0, 0, 0),
        new Item("Beginner's Boots", "Improves footwork and stamina.", 40, "+13% Stamina","false","BOOTS", 0, 0.13, 0, 0, 0),

        // World 2 Items
        new Item("Reinforced Headband", "Tough headband that protects the skull and conceals small illegal items.", 400, "+10% HP, +5% Crit Chance, +15% Dodge Chance","false","HEAD", 0.1, 0, 0.05, 0, 0.15),
        new Item("Blood-Forged Knuckles", "Powerful knuckles for brutal punches, but reduce health over time due to strain on the hands.", 300, "-10% Health, +10% Crit Hit Chance","false","HAND", 0.1, 0, 0.1, 0, 0),
        new Item("Shadowrunner Sneakers", "Lightweight shoes that improve agility but provide less protection.", 250, "+20% Stamina, +10% Dodge Chance, -10% HP","false","BOOTS", -0.1, 0.2, 0, 0, 0.1),
        new Item("Tactical Combat Boots", "Sturdy boots that improve footwork and durability.", 250, "+10% Health, +10% Crit Hit Chance, +10% Stamina", "false", "BOOTS", 0.1, 0.1, 0.1, 0, 0),

        // World 3 Items
        new Item("Iron Jaw Mouthguard", "A shock-absorbing mouthguard designed to reduce damage from heavy punches.", 400, "+15% HP, +10% Stamina","false","HEAD", 0.15, 0.1, 0, 0, 0),
        new Item("Lightning Grip Gloves", "Gloves crafted for unmatched speed and precision.", 300, "+20% Stamina, +10% Crit Hit Chance","false","HAND", 0, 0.2, 0.1, 0, 0),
        new Item("Victory Crest Belt", "A belt signifying champions, offering balanced stats for sustained fights.", 250, "+10% HP, +10% Stamina","false","BODY", 0.1, 0.1, 0, 0, 0),
        new Item("Shadowstep Shoes", "Sneakers that enhance agility, allowing for quick evasive maneuvers.", 250, "+10% Dodge Chance, +10% Stamina", "false", "BOOTS", 0, 0.1, 0, 0, 0.1),
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
            System.out.print(GameLogic.centerText(50, "-> " + description));
            System.out.print(GameLogic.centerText(50, "Effect: " + effect));
            System.out.print(GameLogic.centerText(50, "Cost: PHP " + cost));

            System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(90)));
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
        CarlitoUrbanGym carlito = new CarlitoUrbanGym(player);
        carlito.fightLoop();
    }

    public void showShop(boolean isTraining) {
        GameLogic.clearConsole();
        while (true) {
            int choice = -1;
            boolean isSold = false;
    
            System.out.println();
            System.out.print(GameLogic.greenText);
            if(player.getCurrentWorld() == 0){
                System.out.println(GameLogic.centerBox("GYM SHOP", 40));
            } else if(player.getCurrentWorld() == 1){
                System.out.println(GameLogic.centerBox("BLACK MARKET", 40));
            } else if(player.getCurrentWorld() == 2){
                System.out.println(GameLogic.centerBox("BOXING ARSENAL", 40));
            }

            System.out.print(GameLogic.blueText);
            System.out.print(GameLogic.centerBox(" Player Points: " + player.getPlayerPoints() + " ", 25));
            System.out.println("\n");
            System.out.print(GameLogic.reset);
            System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(90)));
            // Display items for sale
            if(player.getCurrentWorld() == 0){
                for (int i = 0; i < 4; i++) {
                    String line = "(" + (i + 1) + ") " + items[i].name;
                    System.out.print(GameLogic.centerText(50, line));
                    items[i].displayItem();
                }
            } else if(player.getCurrentWorld() == 1){
                for (int i = 4; i < 7; i++) {
                    String line = "(" + (i - 3) + ") " + items[i].name;
                    System.out.print(GameLogic.centerText(50, line));
                    items[i].displayItem();
                }
            } else if(player.getCurrentWorld() == 2){
                for (int i = 8; i < items.length; i++) {
                    String line = "(" + (i - 7) + ") " + items[i].name;
                    System.out.print(GameLogic.centerText(50, line));
                    items[i].displayItem();
                }
            }

            System.out.println(GameLogic.greenText);
            System.out.println(GameLogic.centerBox("Enter the number of the item you wish to buy or 0 to exit.",100));
    
            // Training check before entering purchase loop
            if (playerProgress.getShopStage() < 1 && isTraining) {
                choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 0, 8);
                UrbanStory.urbanTraining7();
                return;
            }
    
            // Purchase loop
            while (!isSold) {
                choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 0, 4);

                // If player is in World 2
                if(player.getCurrentWorld() == 1 && choice != 0){
                    choice += 4;
                }

                // If player is in World 3
                if(player.getCurrentWorld() == 2 && choice != 0){
                    choice += 8;
                }

                if (choice == 0) {
                    System.out.println("Exiting the shop...");
                    GameLogic.pressAnything();
                    return;
                }
    
                System.out.print(GameLogic.redText);
                if (soldChecker(choice)) {
                    System.out.println(GameLogic.centerBox(items[choice - 1].name + " is already sold. Choose another item.",100));
                } else if (notEnoughPoints(choice)) {
                    System.out.print(GameLogic.centerBox("You don't have enough points to buy " + items[choice - 1].name + ". \nPlease choose another item or earn more points.",100));
                    System.out.println();
                } else {
                    // Successful purchase
                    System.out.print(GameLogic.greenText);
                    System.out.println();
                    player.setPlayerPoints(player.getPlayerPoints() - items[choice - 1].cost);
                    System.out.print(GameLogic.centerBox("You've purchased " + items[choice - 1].name,80));
                    System.out.println();
                    
                    // Add item to inventory
                    inventory.setInventory(items[choice - 1].name, items[choice - 1].description, items[choice - 1].body, items[choice - 1].effect);
    
                    // Mark item as sold and set isSold to true to exit inner loop
                    items[choice - 1].setSoldOut();
                    isSold = true;
                }
                System.out.print(GameLogic.reset);
            }
    
            GameLogic.pressAnything(); 
        }
    }

    public void shop() {
        GameLogic.clearConsole();
        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox("GYM SHOP", 40));
        System.out.print(GameLogic.reset);

        System.out.print(GameLogic.orangeText);
        String message = "Fred: \"Welcome to the shop. Here, you can buy all sorts of gear and items that'll\n"
                + "help boost your stats. You want more stamina? We've got special protein shakes for that.\n"
                + "Need a better chance to land critical hits? Try out our precision wraps. These items can\n"
                + "be real game-changers if you use them right.\"\n";

        System.out.print(GameLogic.reset);
        System.out.println(GameLogic.centerText(50, message));

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
