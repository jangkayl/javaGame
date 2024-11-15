package world2;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world1.Tournament;
import world2.TournaUnderFight.NavarroTourna;
import world2.TournaUnderFight.RaulTourna;
import world2.TournaUnderFight.SalvahezTourna;

public class TournamentUnderground {
    static StreetFighter opponent;
    static Player player = GameLogic.player;
    static String[] opponents = {"The Reaper", "The Bullseye", "The Ghost"};
    static PlayerProgress playerProgress = GameLogic.playerProgress;

    public void setOpponent(StreetFighter enemy){
        opponent = enemy;
    }

    public static void attemptTournament(int playerAddStats) {
        GameLogic.clearConsole();
        if (playerAddStats < 5) {
            System.out.println("⚔️  UNDERGROUND TOURNAMENT ENTRY ⚔️");
            GameLogic.printSeparator(40);
            System.out.println("You enter a dimly lit arena, the air thick with sweat and smoke...");
            System.out.println();
            GameLogic.printSeparator(40);
            System.out.println("[ A grizzled official glares at you, a crooked grin forming ]");
            System.out.println("Gatekeeper: \"Whoa there! You think you can just stroll in here? You ain't ready for this blood pit! One hit and you'll be begging for mercy.");
            System.out.println("\t\t\tYou need to spar harder and toughen up! Earn your stripes before stepping into this ring!\"");
            System.out.println();
            System.out.println("🏋️ Tip: Train hard, spar fiercely, and build your strength to earn a shot at the underground brawls! 🏆");
            System.out.println("🔥 Prove your worth in 3 sparring matches before stepping into the ring of legends! 🔥");
            resetMatchScores();
            GameLogic.pressAnything();
            GameLogic.gameData.saveGame();
        } else {
            startTournament();
        }
    }

    public static void startTournament() {
        GameLogic.clearConsole();
        GameLogic.printHeading("\t🏆 Underground Brawl Tournament 🏆");

        if(playerProgress.getPlayerWins() == 0 && playerProgress.getOpponentWins() == 0){
            playerProgress.setRound(1);
        }

        if(player.getStage() < 12){
            System.out.println("Welcome, " + player.getName() + "! This is no ordinary fight - it's a battle for survival in the underground ring!");
            playerProgress.setDone(2);
            Tournament.printTournament();

            while(true){
                if (playerProgress.getOpponentWins() == 3) {
                    if(offerRematch()){
                        resetMatchScores();
                        continue;
                    } else {
                        return;
                    }
                }
    
                // Before each major stage, encourage checking the shop or inventory
                if (player.getStage() == 9) {
                    if (visitShopOrInventory()) {
                        startMatch(0);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(10);
                            resetMatchScores();
                            continue;
                        }
                    }
                } else if (player.getStage() == 10) {
                    if (visitShopOrInventory()) {
                        startMatch(1);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(11);
                            resetMatchScores();
                            continue;
                        }
                    }
                } else if (player.getStage() == 11) {
                    if (visitShopOrInventory()) {
                        startMatch(2);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(12);
                            resetMatchScores();
                            break;
                        }
                    }
                }
            }
        }
        concludeTournament();
        if(player.getCurrentWorld() == 2) {
            GameLogic2.gameLoop();
        }
    }

    private static void startMatch(int opponentIndex) {
        System.out.println();
        System.out.println();
    
        String opponentName = opponents[opponentIndex];
        System.out.print(opponentIndex == 2 ? "FINAL OPPONENT: " : "You will face: ");
        System.out.print(opponentName);
    
        
        // Initialize opponent based on the index
        switch (opponentIndex) {
            case 0 -> {
                // The Reaper
                opponent = new StreetFighter("Julio Navarro", 250, 100, 0.4, 2, 0.25, 4);
                opponent.setPlayerOpponent(player);
                StoryUnderground.tournaOpponentBackstory(opponent);
                fightWithOpponent(new NavarroTourna(player, opponent));
            }
            case 1 -> {
                // The Bullseye
                opponent = new StreetFighter("Raul Villanueva", 300, 120, 0.45, 2, 0.3, 4);
                opponent.setPlayerOpponent(player);
                StoryUnderground.tournaOpponentBackstory(opponent);
                fightWithOpponent(new RaulTourna(player, opponent));
            }
            case 2 -> {
                // The Ghost
                opponent = new StreetFighter("Ralfo Salvahez", 350, 140, 0.5, 2.5, 0.40, 5);
                opponent.setPlayerOpponent(player);
                StoryUnderground.tournaOpponentBackstory(opponent);
                fightWithOpponent(new SalvahezTourna(player, opponent));
            }
            default -> System.out.println("Invalid opponent index.");
        }
    }

    private static boolean visitShopOrInventory() {
        System.out.println();
        System.out.println("Before continuing, would you like to visit the shop or check your inventory?");
        System.out.println("1. Visit Shop");
        System.out.println("2. Check Inventory");
        System.out.println("0. Continue Tournament");
        System.out.println();
        System.out.print("Enter your choice: ");
        
        int choice = GameLogic.readInt("", 0, 2);
        if (choice == 0) {
            return true;  
        } else if(choice == 1){
            GameLogic.shop.showShop(false);
            GameLogic.gameData.inputInventory(GameLogic.inventory.getInventoryItems());
        } else if(choice == 2){
            GameLogic.gameData.getGameDataManager().getInventory();
            GameLogic.gameData.getGameDataManager().getSlots();
            GameLogic.inventory.inventoryMenu();
            GameLogic.gameData.inputSlots(GameLogic.inventory.getSlots());
        }
        return false;
    }

    private static void fightWithOpponent(SparFightLogic fightLogic) {
        while (!isMatchConcluded()) {
            fightLogic.fightLoop();
        }
    }

    private static void resetMatchScores() {
        playerProgress.setRound(1);
        playerProgress.setPlayerWins(0);
        playerProgress.setOpponentWins(0);
    }

    private static boolean isMatchConcluded() {
        return playerProgress.getPlayerWins() == 3 || playerProgress.getOpponentWins() == 3;
    }

    private static boolean offerRematch() {
        System.out.println();
        System.out.println("You lost your previous match. Would you like to:");
        System.out.println("1. Try the tournament again?");
        System.out.println("2. Boost your stats by sparring more!");
        System.out.println();
        System.out.print("Enter your choice (1 or 2): ");
        
        int choice = GameLogic.readInt("", 1, 2);
        if (choice == 1) {
            playerProgress.setPlayerWins(0);
            playerProgress.setOpponentWins(0);
            player.setStage(9);
            return true;  
        } else {
            return false;
        }
    }

    public void printStanding(){
        System.out.println();  
        GameLogic.printSeparator(40);
        System.out.println();  
        System.out.println("\t ~ ~ ~ BEST OF 3 ~ ~ ~");
        System.out.println();  
        System.out.println(player.getName() + " - " + playerProgress.getPlayerWins() + "\t\t" + opponent.getName() + " - " + playerProgress.getOpponentWins());
        System.out.println();  
        GameLogic.printSeparator(40);
        System.out.println();  
        GameLogic.pressAnything();
    }

    private static void concludeTournament() {
        GameLogic.clearConsole();
        System.out.println("🥊 Champion of the Underground! 🥊");
        System.out.println("You've dominated the Dirty Boxing Tournament, emerging as the fiercest fighter in the shadows.");
        System.out.println("Your name strikes fear in the underground, but the chaos never ends...");
        GameLogic.pressAnything();

        GameLogic.printSeparator(100);
        
        System.out.println("\nAs you stand victorious, your father steps forward, his face filled with regret.");
        System.out.println("He opens his mouth to confess why he abandoned you... but suddenly, chaos erupts.");
        System.out.println("Gunshots echo, and you hear the shout: 'Police raid! Everybody down!' The room is thrown into chaos!");

        System.out.println("\nChoose your path:");
        System.out.println("(1) Run and escape the raid.");
        System.out.println("(2) Stay and hear your father's confession, then fight the police.");
        int choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1){
            
        } else {
            
        }
        GameLogic.pressAnything();
        
        playerProgress.setAddStats(0);
        player.setCurrentWorld(2);
    }



}
