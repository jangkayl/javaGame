package world1;

public class Inventory {
    private static final int MAX_ITEMS = 10;  
    private static Item[] inventoryItems = new Item[MAX_ITEMS];  
    private static int itemCount = 0; 
    static boolean isEmpty = true;
    private static String[] slotName = {"HEAD","BODY","HAND","BOOTS","FOOD"};

    private static Item headSlot = null;
    private static Item bodySlot = null;
    private static Item handsSlot = null;
    private static Item bootsSlot = null;
    private static Item foodSlot = null;

    public static class Item {
        public String name;
        public String description;
        String body;
        String effect;
        
        public Item(String name, String description, String body, String effect) {
            this.name = name.replace(" - SOLD OUT", "");
            this.body = body;
            this.description = description;
            this.effect = effect;
        }
    }

    public static int getItemCount(){
        return itemCount;
    }

    public static Item[] getInventoryItems(){
        return inventoryItems;
    }

    public static void setInventoryItems(Item[] items){
        if (items != null) {
            if (items.length <= MAX_ITEMS) {
                for (int i = 0; i < items.length; i++) {
                    inventoryItems[i] = items[i];
                }
                itemCount = items.length;
                System.out.println("Length: " + items.length);
                isEmpty = false;
            } else {
                System.out.println("Not enough space in the inventodry!");
            }
        }
    }

    public static void setInventory(String item, String description, String body, String effect) {
        if (itemCount < MAX_ITEMS) {
            inventoryItems[itemCount] = new Item(item, description, body, effect);
            itemCount++;
            isEmpty = false;
        } else {
            System.out.println("Inventory is full!");
        }
    }

    // Display the inventory menu
    public static void inventoryMenu() {
        GameLogic.clearConsole();
        inventoryAsk();
        GameLogic.pressAnything();
    }

    public static void displayInventory(){
        GameLogic.printHeading("\tInventory");
        System.out.println("Equipped Items:");
        System.out.println("\t1. Head: " + (headSlot != null ? headSlot.name + " - " + headSlot.effect : "Empty"));
        System.out.println("\t2. Body: " + (bodySlot != null ? bodySlot.name + " - " + bodySlot.effect : "Empty"));
        System.out.println("\t3. Hands: " + (handsSlot != null ? handsSlot.name + " - " + handsSlot.effect : "Empty"));
        System.out.println("\t4. Boots: " + (bootsSlot != null ? bootsSlot.name + " - " + bootsSlot.effect : "Empty"));
        System.out.println("Buff Items:");
        System.out.println("\t"+ (foodSlot != null ? foodSlot.name + " - " + foodSlot.effect : "Empty"));
        System.out.println(); 
        System.out.println("Inventory Items:");
        System.out.println("Item count: " + itemCount);
        if (itemCount == 0) {
            System.out.println("\tNO ITEM YET");
            GameLogic.pressAnything();
            return;
        }
        for (int j = 0; j < itemCount; j++) {
            Item currentItem = inventoryItems[j];
            System.out.println((j + 1) + ". " + currentItem.name + " - " + currentItem.effect);
        }
    }

    public static void inventoryAsk(){
        int choice;
        
        outerLoop:
        while(true){
            displayInventory();
            System.out.println(); 
            System.out.println("What would you like to do?");
            System.out.println("1. Equip an item");
            System.out.println("2. Remove an item");
            System.out.println("0. Go back to Menu");
            System.out.println(); 
            int actionChoice = GameLogic.readInt("Choose an action (0 to 2): ", 0, 2);
            if (actionChoice == 0) {
                System.out.println("Returning to the menu...");
                return;
            } else if (actionChoice == 1){
                if(itemCount == 0){
                    System.out.println();
                    System.out.println("There is nothing to equip yet."); 
                    GameLogic.pressAnything();
                    continue outerLoop;
                }
                while(true){
                    System.out.println(); 
                    System.out.println("Which item do you want to equip? (Press 0 to go back)");
                    choice = GameLogic.readInt("Choose your item by number (1 to " + itemCount + "): ", 0, itemCount);
                    System.out.println();
                    if (choice == 0) {  
                        GameLogic.pressAnything();
                        continue outerLoop;
                    }
                    if(isNull(inventoryItems[choice-1].body)){
                        inventoryUse(choice-1, inventoryItems[choice-1].body);
                        continue outerLoop;
                    } else {
                        System.out.println("You have something equipped on your " + inventoryItems[choice-1].body); 
                        GameLogic.pressAnything();
                        continue outerLoop;
                    }
                }
            } else if (actionChoice == 2){
                if(itemCount == 0){
                    System.out.println();
                    System.out.println("There is nothing to remove yet.");
                    GameLogic.pressAnything();
                    continue outerLoop;
                }
                while(true){
                    System.out.println(); 
                    System.out.println("Which item do you want to unequip? (Press 0 to go back)");
                    System.out.println(); 
            
                    choice = GameLogic.readInt("Choose your item by number (1 to 4): ", 0, 4);
                    if (choice == 0) {  
                        GameLogic.pressAnything();
                        continue outerLoop;
                    }
                    if(!isNull(slotName[choice-1])){
                        inventoryRemove(slotName[choice-1]);
                        continue outerLoop;
                    } else {
                        System.out.println();
                        System.out.println("You don't equipped something on your " + slotName[choice-1]); 
                        GameLogic.pressAnything();
                        continue outerLoop;
                    }
                } 
            }
        }
    }
    
    static boolean isNull(String body){
        if(body == "HEAD"){
            return headSlot == null;
        } else if(body == "BODY"){
            return bodySlot == null;
        } else if(body == "HAND") {
            return handsSlot == null;
        } else if(body == "BOOTS") {
            return bootsSlot == null;
        } else if(body == "FOOD") {
            return foodSlot == null;
        }
        return false;
    }

    static boolean noEquip(Item equip){
       if(equip == null){
           System.out.println();
           System.out.println("No item equipped.");
           GameLogic.pressAnything();
           return true;
       } else { 
           return false;
       }
    }
    
    public static void inventoryUse(int itemIndex, String equipmentSlot) {
        if (itemIndex < 0 || itemIndex >= itemCount) {
            System.out.println("Invalid item index.");
            return;
        }

        Item selectedItem = inventoryItems[itemIndex];
        Shop.applyEffect(selectedItem.effect);
        switch (equipmentSlot.toUpperCase()) {
            case "HEAD":
                headSlot = selectedItem;
                System.out.println(selectedItem.name + " has been equipped to HEAD.");
                break;
            case "BODY":
                bodySlot = selectedItem;
                System.out.println(selectedItem.name + " has been equipped to BODY.");
                break;
            case "HAND":
                handsSlot = selectedItem;
                System.out.println(selectedItem.name + " has been equipped to HAND.");
                break;
            case "BOOTS":
                bootsSlot = selectedItem;
                System.out.println(selectedItem.name + " has been equipped to BOOTS.");
                break;
            case "FOOD":
                foodSlot = selectedItem;
                System.out.println(selectedItem.name + " has been consumed! You feel refreshed.");
                break;
            default:
                System.out.println("Invalid slot.");
                return;
        }
        
        for (int i = itemIndex; i < itemCount - 1; i++) {
            inventoryItems[i] = inventoryItems[i + 1];
        }

        inventoryItems[itemCount - 1] = null;
        itemCount--;
        if (itemCount == 0) {
            isEmpty = true; 
        }
        
        GameLogic.pressAnything();
    }
    
    public static void inventoryRemove(String equipmentSlot) {
        Item removedItem = null;
        System.out.println();

        // Check the specified equipment slot
        switch (equipmentSlot.toUpperCase()) {
            case "HEAD":
                if(noEquip(headSlot)) return; 
                removedItem = headSlot;
                headSlot = null;
                System.out.println(removedItem.name + " have been removed from your HEAD.");
                break;
            case "BODY":
                if(noEquip(bodySlot)) return; 
                removedItem = bodySlot;
                bodySlot = null;
                System.out.println(removedItem.name + " have been removed from your BODY.");
                break;
            case "HAND":
                if(noEquip(handsSlot)) return; 
                removedItem = handsSlot;
                handsSlot = null;
                System.out.println(removedItem.name + " have been removed from your HAND.");
                break;
            case "BOOTS":
                if(noEquip(bootsSlot)) return; 
                removedItem = bootsSlot;
                bootsSlot = null;
                System.out.println(removedItem.name + " have been removed from your BOOTS.");
                break;
            case "FOOD":
                if(noEquip(foodSlot)) return; 
                foodSlot = null;
                System.out.println(removedItem.name + " has been consumed! You feel refreshed.");
                break;
            default:
                System.out.println("Invalid slot.");
                return;
        }

        if (removedItem != null) {
            inventoryItems[itemCount] = removedItem; 
            itemCount++;
            Shop.removeEffect(removedItem.effect);
            System.out.println(removedItem.name + " has been returned to your inventory.");
        }

        GameLogic.pressAnything();
    }
}