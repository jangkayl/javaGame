package world2;

import world1.GameLogic;
import world1.Inventory;
import world1.Player;
import world1.PlayerProgress;
import world1.Shop;
import world2.BetFight.BetFight;

public class GameLogic2 {
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
    static void continueJourney() {
        GameLogic.clearConsole();
        if(player.getCurrentWorld() == 1) {
            if(player.getStage() == 6){
                GameLogic.printSeparator(40);
                String[] worlds = player.getWorlds();
                GameLogic.printHeading("   Welcome to the " + worlds[player.getCurrentWorld()]);
                GameLogic.printSeparator(40);
                StoryUnderground.printUnderground();
                GameLogic.printSeparator(50);
                System.out.println();
                System.out.println("Are you ready to start your journey?");
                System.out.println("(1) Yes\n(2) No");
                int choice2 = GameLogic.readInt("-> ", 1, 2);
                if(choice2 == 2){
                    return;
                }
                StoryUnderground.printUnderground();
                StoryUnderground.printIntroduction(player.getName());
                player.setStage(7);
            } else {
                while (true) {
                    GameLogic.clearConsole();
                    if(player.getStage() == 7){
                        System.out.println("(0) Train with Jakester");
                    } else if(player.getStage() >= 8){
                        System.out.println("(0) Continue Journey");
                    }
                    System.out.println("(1) Bet on Matches");
                    System.out.println("(2) Black Market");
                    System.out.println("(3) Go back");
                    int choice = GameLogic.readInt("-> ", 0, 3);
                    if(choice == 0){
                        if(player.getStage() == 7){
                            TrainWithJakester.teachDirtyBoxingMoves();
                        } else if(player.getStage() >= 8){
                            journeyMenu();
                        }
                    } else if (choice == 1){
                        BetFight betFight = new BetFight();
                        betFight.betFight();
                    } else if(choice == 2){
                        shop.showShop(false);
                        GameLogic.gameData.inputInventory(inventory.getInventoryItems());
                        GameLogic.gameData.saveGame();
                    } else if(choice == 3){
                        break;
                    }
                }
            }
        }
    }

    static void journeyMenu(){
        while(true){
            GameLogic.clearConsole();
            System.out.println("(1) Review Move Hints");
            System.out.println("(2) Spar - Earn points and gain stats");
            System.out.println("(3) Enter Tournament");
            System.out.println("(4) Go back");
            int choice = GameLogic.readInt("-> ", 1, 4);
            if(choice == 1){
                BoxerHints.teachHints();
            } else if(choice == 2){
                Sparring sparring = new Sparring();
                sparring.enterSparring();
            } else if(choice == 3){
                TournamentUnderground.attemptTournament(playerProgress.getAddStats());
            } else if(choice == 4){
                break;
            }
        }
    }
}
