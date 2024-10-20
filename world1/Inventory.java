package world1;
import java.util.ArrayList;

public class Inventory {
    public static class Item {
        public String name;
        public String description;

        public Item(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    private static ArrayList<Item> inventoryItems = new ArrayList<>();
    static boolean isEmpty = true;

    public static void setInventory(String item, String description) {
        inventoryItems.add(new Item(item, description));
        isEmpty = false;
    }

    public static ArrayList<Item> getInventoryItems() {
        ArrayList<Item> items = new ArrayList<>();
        return items; 
    }

    public static void inventoryMenu() {
        GameLogic.clearConsole();
        GameLogic.printHeading(GameLogic.centerText("Inventory", 30));
        if (isEmpty) {
            System.out.println();
            System.out.println(GameLogic.centerText("NO ITEM YET", 30));
            GameLogic.pressAnything();
            return;
        }
        for (int j = 0; j < inventoryItems.size(); j++) {
            Item currentItem = inventoryItems.get(j);
            System.out.println((j + 1) + ". " + currentItem.name + " - " + currentItem.description);
        }
        GameLogic.pressAnything();
    }
}
