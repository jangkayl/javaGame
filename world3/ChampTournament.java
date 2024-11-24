package world3;

import world1.GameLogic;
import world1.Inventory;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world1.Tournament;
import world3.ChampOpponents.Macuez;
import world3.ChampOpponents.Pakyaw;
import world3.ChampOpponents.Welder;

public class ChampTournament {
    private static StreetFighter opponent;
    private static Player player = GameLogic.player;
    private static String[] opponents = {"Money", "El Matador", "Pekman"};
    private static PlayerProgress playerProgress = GameLogic.playerProgress;
    private static Inventory inventory = GameLogic.inventory;

    public void setOpponent(StreetFighter enemy){
        opponent = enemy;
    }

    public void attemptTournament(int playerAddStats) {
        GameLogic.clearConsole();
        if (player.getStage() < 19) {
            System.out.print(GameLogic.centerBox(
                    "⚔️ Tournament Entry Attempt ⚔️", 100));
            System.out.println();
            System.out.print(GameLogic.centerText(20, 
                                "You stand before the legendary arena, but the gatekeeper blocks your path.\n\n" +
                                "Gatekeeper: \"Hold it, rookie! You're not ready for the Champion's Tournament.\n" +
                                "Get back to the gym, spar harder, and prove you're no pushover.\n" +
                                "Only the strongest warriors earn their place here!\"\n\n" +
                                "🏋️ Tip: Spar relentlessly and rank up to unlock the tournament."));
            GameLogic.pressAnything();
        } else {
            startTournament();
        }
    }

    public void startTournament() {
        GameLogic.clearConsole();
        System.out.print(GameLogic.centerBox("🏆 Underground Brawl Tournament 🏆",60));
        System.out.println();

        if(playerProgress.getPlayerWins() == 0 && playerProgress.getOpponentWins() == 0){
            playerProgress.setRound(1);
        }

        if(player.getStage() < 22){
            System.out.print(GameLogic.centerText(50, 
                "Welcome, " + player.getName() + "! The arena roars with anticipation as you step into the legendary Champion's Tournament!" +
                "\nThis is not just a fight—it's your chance to etch your name in history, to rise above the rest, and claim ultimate glory!" +
                "\nAre you ready to face the fiercest warriors and prove yourself as the true champion? Let the battles begin!"));
            System.out.println();
            playerProgress.setDone(3);
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
                if (player.getStage() == 19) {
                    if (visitShopOrInventory()) {
                        startMatch(0);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(20);
                            resetMatchScores();
                            continue;
                        }
                    }
                } else if (player.getStage() == 20) {
                    if (visitShopOrInventory()) {
                        startMatch(1);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(21);
                            resetMatchScores();
                            continue;
                        }
                    }
                } else if (player.getStage() == 21) {
                    if (visitShopOrInventory()) {
                        startMatch(2);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(22);
                            resetMatchScores();
                            break;
                        }
                    }
                }
            }
        }
        concludeTournament();
        // if(player.getCurrentWorld() == 2) {
        //     GameLogic2.gameLoop();
        // }
    }

    private static void startMatch(int opponentIndex) {
        System.out.println();
        System.out.println();
    
        String opponentName = opponents[opponentIndex];
        System.out.print(GameLogic.centerBox(opponentIndex == 2 ? "FINAL OPPONENT: " : "You will face: " + opponentName, 50));
    
        
        // Initialize opponent based on the index
        switch (opponentIndex) {
            case 0 -> {
                // The Reaper
                opponent = new StreetFighter("May Welder", 300, 110, 0.35, 2, 0.3, 4);
                opponent.setPlayerOpponent(player);
                StoryChampsArena.champTournaBackstory(opponent);
                fightWithOpponent(new Welder(player, opponent));
            }
            case 1 -> {
                // The Bullseye
                opponent = new StreetFighter("Manual Macuez", 320, 120, 0.4, 2, 0.35, 4);
                opponent.setPlayerOpponent(player);
                StoryChampsArena.champTournaBackstory(opponent);
                fightWithOpponent(new Macuez(player, opponent));
            }
            case 2 -> {
                // The Ghost
                opponent = new StreetFighter("Mani Pakyaw", 500, 130, 0.5, 2, 0.5, 5);
                opponent.setPlayerOpponent(player);
                StoryChampsArena.champTournaBackstory(opponent);
                fightWithOpponent(new Pakyaw(player, opponent));
            }
            default -> System.out.print(GameLogic.centerBox("Invalid opponent index.",50));
        }
    }

    private static boolean visitShopOrInventory() {
        System.out.println();
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(80)));
        System.out.println();
        System.out.print(GameLogic.centerText(80,"Before continuing, would you like to visit the shop or check your inventory?"));
        System.out.print(GameLogic.centerText(80,"1. Visit Shop"));
        System.out.print(GameLogic.centerText(80,"2. Check Inventory"));
        System.out.print(GameLogic.centerText(80,"0. Continue Tournament"));
        System.out.println();
        System.out.print(GameLogic.centerText(80,"Enter your choice: "));
        
        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 0, 2);
        if (choice == 0) {
            if(!inventory.checkSlotsValid()){
                System.out.println();
                System.out.print(GameLogic.centerBox("✋ Please UNEQUIP all items from Underworld Rumble before fighting", 75));
                GameLogic.pressAnything();
                return false;
            }
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
        System.out.print(GameLogic.centerBox("You lost your previous match. Would you like to:", 55));
        System.out.println();
        System.out.print(GameLogic.centerText(20,"1. Try the tournament again?"));
        System.out.print(GameLogic.centerText(20,"2. Boost your stats by sparring more!"));
        System.out.println();
        System.out.print(GameLogic.centerText(20, "Enter your choice (1 or 2): "));
        
        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 2);
        if (choice == 1) {
            playerProgress.setPlayerWins(0);
            playerProgress.setOpponentWins(0);
            player.setStage(19);
            return true;  
        } else {
            return false;
        }
    }

    public void printStanding(){
        System.out.println("\n");
        System.out.print(GameLogic.centerBox("🤼 BEST OF 3 🤼\n\n" +
                player.getName() + " - " + playerProgress.getPlayerWins() + "\n🤜💥🤛\n" +
                opponent.getName() + " - " + playerProgress.getOpponentWins(), 50));
        GameLogic.pressAnything();
    }

    private void concludeTournament() {
        System.out.println("\n\n");
        System.out.println(GameLogic.centerBox(
                "🏆 Champion of the World! 🏆\n" +
                "You've conquered the legendary Champion's Tournament, outlasting the fiercest fighters the world has ever known.\n" +
                "Your name will be etched in history as the ultimate warrior, the one who overcame every challenge to claim the title of Champion!", 140));
        GameLogic.pressAnything();

        player.setRank(6); 
        GameLogic.rankNotif();
        System.out.println("\n");
        System.out.println(GameLogic.centerBox(
                "As the crowd roars in celebration, the tournament organizer approaches you, holding the golden trophy.\n" +
                "With a wide smile, they announce: 'You've earned it. This is your moment!'\n" +
                "You lift the trophy high, the weight of your journey finally sinking in. Every battle, every sacrifice, has led to this.\n" +
                "Your dream is now a reality—a testament to your strength, perseverance, and unbreakable spirit!", 130));
    
        GameLogic.pressAnything();
        System.out.println("\n");
        System.out.println(GameLogic.centerBox(
                "But as the celebrations continue, you find a quiet moment to reflect.\n" +
                "From the underground ring to the world stage, you've faced it all. Now, a new question arises:\n" +
                "What will you do with this legacy?\n" +
                "The world watches, ready for the next chapter in the story of the Champion.", 120));
    
        System.out.println();
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(90)));
        System.out.print(GameLogic.centerText(50, "Choose your path:"));
        System.out.print(GameLogic.centerText(50, "(1) Continue as the reigning Champion, inspiring future fighters."));
        System.out.print(GameLogic.centerText(50, "(2) Retire and use your fame to bring change to the world."));
        System.out.print(GameLogic.centerText(50, "(3) Disappear into legend, leaving behind a mystery for the ages."));

        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 3);
        if (choice == 1) {
            gameEndingLogo();
        } else if (choice == 2) {
            gameEndingLogo();
        } else {
            gameEndingLogo();
        }
    }

    public void gameEndingLogo(){
        GameLogic.clearConsole();

        player.setCurrentWorld(3);
        String mainAscii = GameLogic.greenText + GameLogic.centerText(100,
"████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗███████╗    ███████╗ ██████╗ ██████╗     ██████╗ ██╗      █████╗ ██╗   ██╗██╗███╗   ██╗ ██████╗ \n" +
"╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝██╔════╝    ██╔════╝██╔═══██╗██╔══██╗    ██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝██║████╗  ██║██╔════╝ \n" +
"   ██║   ███████║███████║██╔██╗ ██║█████╔╝ ███████╗    █████╗  ██║   ██║██████╔╝    ██████╔╝██║     ███████║ ╚████╔╝ ██║██╔██╗ ██║██║  ███╗\n" +
"   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗ ╚════██║    ██╔══╝  ██║   ██║██╔══██╗    ██╔═══╝ ██║     ██╔══██║  ╚██╔╝  ██║██║╚██╗██║██║   ██║\n" +
"   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗███████║    ██║     ╚██████╔╝██║  ██║    ██║     ███████╗██║  ██║   ██║   ██║██║ ╚████║╚██████╔╝\n" +
"   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝    ╚═╝      ╚═════╝ ╚═╝  ╚═╝    ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝╚═╝  ╚═══╝ ╚═════╝ \n"
) + GameLogic.reset;

        String mainAscii2 = GameLogic.redText + GameLogic.centerText(100,

                        "███▄    █  ██▓  ▄████   ▄████  ▄▄▄        ██████  \n" +
                        "██ ▀█   █ ▓██▒ ██▒ ▀█▒ ██▒ ▀█▒▒████▄    ▒██    ▒  \n" +
                        "▓██  ▀█ ██▒▒██▒▒██░▄▄▄░▒██░▄▄▄░▒██  ▀█▄  ░ ▓██▄   \n" +
                        "▓██▒  ▐▌██▒░██░░▓█  ██▓░▓█  ██▓░██▄▄▄▄██   ▒   ██▒\n" +
                        "▒██░   ▓██░░██░░▒▓███▀▒░▒▓███▀▒ ▓█   ▓██▒▒██████▒▒\n" +
                        "░ ▒░   ▒ ▒ ░▓   ░▒   ▒  ░▒   ▒  ▒▒   ▓▒█░▒ ▒▓▒ ▒ ░\n" +
                        "░ ░░   ░ ▒░ ▒ ░  ░   ░   ░   ░   ▒   ▒▒ ░░ ░▒  ░ ░\n" +
                        "  ░   ░ ░  ▒ ░░ ░   ░ ░ ░   ░   ░   ▒   ░  ░  ░   \n" +
                        "        ░  ░        ░       ░       ░  ░      ░   \n"
                        ) + GameLogic.reset;


        System.out.println(mainAscii);
        System.out.println();
        System.out.println(mainAscii2);

        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();

        System.exit(0);
    }
}
