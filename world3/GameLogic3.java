package world3;

import world1.GameLogic;
import world1.Inventory;
import world1.Player;
import world1.PlayerProgress;
import world1.Shop;

public class GameLogic3 {
    public static Player player = GameLogic.player;
    public static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static Shop shop = GameLogic.shop;
    public static Inventory inventory = new Inventory();
    
    public static void gameLoop(){
        int input;
        while(GameLogic.isRunning){
            shop = new Shop(player, playerProgress);
            printMenu();
            if(playerProgress.getShopStage() > 3){
                input = GameLogic.readInt("-> ", 0, 4);
            } else {
                input = GameLogic.readInt("-> ", 0, 3);
            }
            if(input == 0){
                GameLogic.isRunning = false;
            } else if(input == 1){
                // continueJourney();
            } else if(input == 2){
                GameLogic.printStats();
            } else if(input == 3){
                GameLogic.gameData.getGameDataManager().getInventory();
                GameLogic.gameData.getGameDataManager().getSlots();
                inventory.inventoryMenu();
                GameLogic.gameData.inputSlots(inventory.getSlots());
            }
        }
    }

    // Prints the menu options
    static void printMenu(){
        GameLogic.gameData.saveGame();
        GameLogic.clearConsole();
        GameLogic.printHeading(GameLogic.centerText("MENU SA WORLD 3", 30));
        System.out.println("Choose an action:");
        GameLogic.printSeparator(20);
        System.out.println("(0) Exit Game");
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Check your Stats");
        System.out.println("(3) Inventory");
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
    }
}
