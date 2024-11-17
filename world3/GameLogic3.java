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
                continueJourney();
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
        GameLogic.printHeading(GameLogic.centerText("MENU", 30));
        System.out.println("Choose an action:");
        GameLogic.printSeparator(20);
        System.out.println("(0) Exit Game");
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Check your Stats");
        System.out.println("(3) Inventory");
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
    }

    // Continues players journey
    private static void continueJourney() {
        TrainWithFred trainWithFred = new TrainWithFred();
        SparringRing sparringRing = new SparringRing();

        GameLogic.clearConsole();
        if(player.getCurrentWorld() == 2) {
            if(player.getStage() == 12){
                GameLogic.printSeparator(40);
                String[] worlds = player.getWorlds();
                GameLogic.printHeading("   Welcome to the " + worlds[player.getCurrentWorld()]);
                GameLogic.printSeparator(40);
                StoryChampsArena.printChampsArena();
                GameLogic.printSeparator(50);
                System.out.println();
                System.out.println("Are you ready to start your journey?");
                System.out.println("(1) Yes\n(2) No");
                int choice2 = GameLogic.readInt("-> ", 1, 2);
                if(choice2 == 2){
                    return;
                }
                StoryChampsArena.printChampsArena();
                StoryChampsArena.printIntroduction(player.getName());
                player.setStage(13);
                trainWithFred.teachPassiveSkills();
            } else {
                while (true) {
                    GameLogic.clearConsole();
                    System.out.println("(1) Sparring Ring");
                    System.out.println("(2) Boxing Arsenal");
                    System.out.println("(3) Enter Tournament");
                    System.out.println("(4) Go back");
                    int choice = GameLogic.readInt("-> ", 1, 4);
                    if(choice == 1){
                        if(player.getStage() == 13)
                            trainWithFred.teachPassiveSkills();
                        else 
                            sparringRing.start();
                    } else if(choice == 2){
                        shop.showShop(false);
                        GameLogic.gameData.inputInventory(inventory.getInventoryItems());
                        GameLogic.gameData.saveGame();
                    } else if(choice == 3){

                    } else if(choice == 4){
                        break;
                    } 
                }
            }
        }
    }
}
