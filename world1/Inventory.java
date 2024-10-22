package world1;

public class Inventory {
    private static final int MAX_ITEMS = 10;  
    public static Item[] inventoryItems = new Item[MAX_ITEMS];  
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
        public String body;
        
        public Item(String name, String description,String body) {
            this.name = name;
            this.body = body;
            this.description = description;
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
                System.out.println("Not enough space in the inventory!");
            }
        }
    }

    public static void setInventory(String item, String description, String body) {
        if (itemCount < MAX_ITEMS) {
            inventoryItems[itemCount] = new Item(item, description, body);
            itemCount++;
            isEmpty = false;
        } else {
            System.out.println("Inventory is full!");
        }
    }

    // Display the inventory menu
    public static void inventoryMenu() {
        GameLogic.clearConsole();
        GameLogic.printHeading("\tInventory");

        System.out.println("Equipped Items:");
        System.out.println("\t1. Head: " + (headSlot != null ? headSlot.name + " - " + headSlot.description : "Empty"));
        System.out.println("\t2. Body: " + (bodySlot != null ? bodySlot.name + " - " + bodySlot.description : "Empty"));
        System.out.println("\t3. Hands: " + (handsSlot != null ? handsSlot.name + " - " + handsSlot.description : "Empty"));
        System.out.println("\t4. Boots: " + (bootsSlot != null ? bootsSlot.name + " - " + bootsSlot.description : "Empty"));
        System.out.println("Buff Items:");
        System.out.println("\t"+ (foodSlot != null ? foodSlot.name + " - " + foodSlot.description : "Empty"));
        System.out.println(); 
        System.out.println("Inventory Items:");
        if (itemCount == 0) {
            System.out.println("\tNO ITEM YET");
            GameLogic.pressAnything();
            return;
        }

        for (int j = 0; j < itemCount; j++) {
            Item currentItem = inventoryItems[j];
            System.out.println((j + 1) + ". " + currentItem.name + " - " + currentItem.description);
        }

        inventoryAsk();
        GameLogic.pressAnything();
    }

    public static void inventoryAsk(){
        int choice;
        System.out.println(""); 
        System.out.println("You open your bag and sift through the items...");
        outerLoop:
        while(true){
            System.out.println(""); 
            System.out.println("What would you like to do?");
            System.out.println("1. Equip an item");
            System.out.println("2. Remove an item");
            System.out.println("0. Go back to Menu");
            System.out.println("");
            int actionChoice = GameLogic.readInt("Choose an action (0 to 2): ", 0, 2);
            if (actionChoice == 0) {
                System.out.println("Returning to the menu...");
                return;
            }else if(actionChoice == 1){
                inner1Loop:
                while(true){
                    if(!isNull()){
                        System.out.println(""); 
                        System.out.println("Which item do you want to equip? (Press 0 to go back)");
    
                        choice = GameLogic.readInt("Choose your item by number (1 to " + itemCount + "): ", 0, itemCount);
                        System.out.println("");
                        if (choice == 0) {  
                            GameLogic.pressAnything();
                            continue outerLoop;
                        }
   
                        inventoryUse(choice-1,inventoryItems[choice-1].body);
                        break;
                        }else{
                            System.out.println("");
                            System.out.println("There is nothing to equip yet."); 
                            GameLogic.pressAnything();
                            continue outerLoop;
                    }
                }
            }else if(actionChoice == 2){
                inner2Loop:
                while(true){
                    if(isNull()){
                        System.out.println(""); 
                        System.out.println("Which item do you want to unequip? (Press 0 to go back)");
                
                        choice = GameLogic.readInt("Choose your item by number (1 to 4): ", 0, 4);
                        if (choice == 0) {  
                            continue outerLoop;
                        }
                        inventoryRemove(slotName[choice-1]);
                        break;
                    }else{
                        System.out.println("");
                        System.out.println("There is nothing to remove yet.");
                        continue outerLoop;
                    }  
                }
            }
            inventoryMenu();
            continue;
        }
    }
    
    static boolean isNull(){
        return headSlot != null || bodySlot != null || handsSlot != null || bootsSlot != null || foodSlot != null;
    }
    static boolean noEquip(Item equip){
       if(equip == null){
           System.out.println("");
           System.out.println("No item equipped.");
           GameLogic.pressAnything();
           return true;
       }else{
           return false;
       }
    }
    
    public static void inventoryUse(int itemIndex, String equipmentSlot) {
    if (itemIndex < 0 || itemIndex >= itemCount) {
        System.out.println("Invalid item index.");
        return;
    }

    Item selectedItem = inventoryItems[itemIndex];
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
            case "BOOT":
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
        
        Shop.applyEffect(selectedItem.name);
        GameLogic.pressAnything();
    }
    
    public static void inventoryRemove(String equipmentSlot) {
        Item removedItem = null;

        // Check the specified equipment slot
        switch (equipmentSlot.toUpperCase()) {
            case "HEAD":
                if(noEquip(headSlot)) return; 
                removedItem = headSlot;
                headSlot = null;
                System.out.println(removedItem.name + " have been removed from your HEAD.");
                break;
            case "BODY":
                if(noEquip(headSlot)) return; 
                removedItem = bodySlot;
                bodySlot = null;
                System.out.println(removedItem.name + " have been removed from your BODY.");
                break;
            case "HAND":
                if(noEquip(headSlot)) return; 
                removedItem = handsSlot;
                handsSlot = null;
                System.out.println(removedItem.name + " have been removed from your HAND.");
                break;
            case "BOOT":
                if(noEquip(headSlot)) return; 
                removedItem = bootsSlot;
                bootsSlot = null;
                System.out.println(removedItem.name + " have been removed from your BOOTS.");
                break;
            case "FOOD":
                if(noEquip(headSlot)) return; 
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
            System.out.println(removedItem.name + " has been returned to your inventory.");
            }
        }
}