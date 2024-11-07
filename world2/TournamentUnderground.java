package world2;

import world1.FightLogic;
import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world1.Tournament;
import world1.TournamentFight.LopezTourna;
import world1.TournamentFight.RamirezTourna;
import world1.TournamentFight.TettehTourna;

public class TournamentUnderground {
    static StreetFighter opponent;
    static Player player = GameLogic.player;
    static String[] opponents = {"El Tigre", "El Jablo", "El Taeh"};
    static PlayerProgress playerProgress = GameLogic.playerProgress;

    public void setOpponent(StreetFighter enemy){
        opponent = enemy;
    }

    public static void attemptTournament(int playerAddStats) {
        GameLogic.clearConsole();
        if (playerAddStats < 3) {
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

        if(player.getStage() < 10){
            System.out.println("Welcome, " + player.getName() + "! This is no ordinary fight - it's a battle for survival in the underground ring!");
            playerProgress.setDone(2);
            Tournament.printTournament();

            while(true){
                if (playerProgress.getOpponentWins() == 3) {
                    if(offerRematch()){
                        resetMatchScores();
                        continue;
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
                opponent = new StreetFighter("Julio Navarro", 350, 100, 0.4, 2, 0.25, 4);
                opponent.setPlayerOpponent(player);
                StoryUnderground.tournaOpponentBackstory(opponent);
                fightWithOpponent(new RamirezTourna(player, opponent));
            }
            case 1 -> {
                // The Bullseye
                opponent = new StreetFighter("Raul Villanueva", 450, 120, 0.45, 2, 0.3, 4);
                opponent.setPlayerOpponent(player);
                StoryUnderground.tournaOpponentBackstory(opponent);
                fightWithOpponent(new LopezTourna(player, opponent));
            }
            case 2 -> {
                // The Ghost
                opponent = new StreetFighter("Ralfo Salvahez", 500, 140, 0.5, 2.5, 0.40, 5);
                opponent.setPlayerOpponent(player);
                StoryUnderground.tournaOpponentBackstory(opponent);
                fightWithOpponent(new TettehTourna(player, opponent));
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

    private static void fightWithOpponent(FightLogic fightLogic) {
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
        System.out.println("2. Train with Fred or your coach to sharpen your skills and gain more stats!");
        System.out.println();
        System.out.print("Enter your choice (1 or 2): ");
        
        int choice = GameLogic.readInt("", 1, 2);
        if (choice == 1) {
            playerProgress.setPlayerWins(0);
            playerProgress.setOpponentWins(0);
            player.setStage(3);
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
        System.out.println();
        System.out.println("🥊 Congratulations, Champion of the Ring! 🥊");
        System.out.println("You've conquered the tournament, delivering knockout blows and proving you have the heart of a true fighter!");
        System.out.println("The crowd roars, and your name is now legend. But the journey isn't over...\n");
        
        System.out.println("As you return home, you can still hear the echoes of cheering fans, the thrill of victory fresh in your mind.");
        System.out.println("But your celebration is cut short when you see your childhood friend, Niko, waiting anxiously outside your door.");
        GameLogic.pressAnything();

        GameLogic.printSeparator(70);
        System.out.println();
        System.out.println("Niko: \t\"Hey, I'm glad I found you. It's urgent... your mom has been taken to the hospital. She needs help, and it doesn't look good.\"");
        System.out.println();
        System.out.println("\t( Panic rises in your chest as you race to the hospital, each step heavier than the last. )");
        System.out.println();
        System.out.println("\tYou find your mother weak, hooked to machines. The doctors say the operation could save her life, but it's costly, your recent winnings barely cover it.\n");
        GameLogic.pressAnything();
    
        System.out.println();
        GameLogic.printSeparator(70);
        System.out.println();
        System.out.println("Niko: \t\"I know it's tough, but there's another way. It's risky and illegal... Fighters can earn big money in the underground ring.");
        System.out.println("\tIt's brutal, but you could make what you need fast.\"");
        System.out.println();
        System.out.println("\t( Niko's eyes are serious, and you can see the weight of the decision settling in. )");
        System.out.println();
        System.out.println("\tYou take a deep breath, feeling the burden of your mother's health pressing down on you.");
        System.out.println();
        System.out.println("Niko: \t\"It's dangerous, but if you're as good as I think you are, you could pull this off. Just think about it...\"\n");
        GameLogic.pressAnything();
    
        System.out.println();
        GameLogic.printSeparator(70);
        System.out.println();
        System.out.println("\tWith your mother's life at stake, you know what you must do. You nod at Niko.");
        System.out.println();
        System.out.println("Niko: \t\"Alright, let's do this. I'll show you the ropes. Get ready for a new world of fighting!\"\n");
        GameLogic.pressAnything();
        GameLogic.printSeparator(70);

        // Update the player's world/state to reflect the new story path.
        player.setCurrentWorld(1);
    }

}
