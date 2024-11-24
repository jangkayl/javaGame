package world1.interfaces;

import world1.Inventory;

public interface InvetoryInterface {
     int getItemCount();
     void setSlots(Inventory.Item[] slots);
     void setInventoryItems(Inventory.Item[] items);
     void setInventory(String item, String description, String body, String effect);
     void inventoryMenu() ;
     void inventoryAsk();
     void remove(Inventory.Item removedItem);
     void removeAllInventory();
     void inventoryRemove(String equipmentSlot);
}
