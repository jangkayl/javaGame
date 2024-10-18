import java.util.ArrayList;

public class Inventory {
    static class Item {
        String name;
        String description;

        Item(String name, String description) {
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

    public static void inventoryMenu() {
        GameLogic.clearConsole();
        GameLogic.printHeading("\tInventory");
        if (isEmpty) {
            System.out.println("\tNO ITEM YET");
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
