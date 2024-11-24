package world3;

import world1.GameLogic;
import GlobalClasses.Inventory;
import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.StreetFighter;
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
        String text = GameLogic.redText + GameLogic.centerText(100,
                   "▒█▀▀█ █▀▀ █▀▀ ▀▀█▀▀ 　 █▀▀█ █▀▀ 　 █▀▀█   \n" +
                        "▒█▀▀▄ █▀▀ ▀▀█ ░░█░░ 　 █░░█ █▀▀ 　 ░░▀▄   \n" +
                        "▒█▄▄█ ▀▀▀ ▀▀▀ ░░▀░░ 　 ▀▀▀▀ ▀   　 █▄▄█   \n\n" ) + GameLogic.reset;

        System.out.print(text);

        System.out.print(GameLogic.centerBox( player.getName() + " - " + playerProgress.getPlayerWins() + "\n" +
                GameLogic.printCenteredSeparator(30) + "\n" + opponent.getName() + " - " + playerProgress.getOpponentWins(), 40));
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
            continueReign();
        } else if (choice == 2) {
            retire();
        } else {
            disappear();
        }
        sharedEnding();
        gameEndingLogo();
    }

    private void continueReign(){
        GameLogic.clearConsole();
        String prompt = "You choose to stay in the spotlight, defending your title with the grit and determination that brought\n" +
                        "you here. Fight after fight, your victories stack up, but so does the weight of responsibility. The roar\n" +
                        "of the crowd shifts in tone—not just cheering for your triumphs but marveling at the inspiration you've become.\n" +
                        "Young fighters flock to your corner, their eyes filled with dreams you once had.\n\n" + 
                        "One day, as you raise your championship belt high, you see a child in the crowd shadowboxing with wild enthusiasm.\n" +
                        "A spark ignites in your heart: your greatest legacy isn't in the ring but in what you leave behind.\n\n" +
                        "Years later, the gym you built hums with activity. Sweat-drenched mats, clinking chains from heavy bags, and\n" +
                        "shouts of \"One more round!\" echo in the air. You train the next generation to fight with their fists and\n" + 
                        "their hearts, knowing their victories will be yours too.";
        GameLogic.printWithDelay(GameLogic.centerText(20, prompt));
        GameLogic.pressAnything();
    }

    private void retire(){
        GameLogic.clearConsole();
        String prompt = "At your peak, you make the bold decision to step away from the ring. Your fame becomes a powerful tool for change.\n" +
                        "You start initiatives that transform lives: scholarships for underprivileged youths, programs to keep kids off the\n" +
                        "streets, and motivational speeches that spark hope where despair once loomed.\n\n" +
                        "But something tugs at you. As you meet young fighters chasing the same dreams you once pursued, a longing grows.\n" + 
                        "They don't just need opportunities; they need a mentor. One fateful night, you step into an old, worn-down gym and\n" +
                        "lace up your gloves again—not to fight but to teach.\n\n" +
                        "The small gym you open becomes a sanctuary. Fighters come not for glory but for guidance. The lessons you share\n" +
                        "hard-won in the ring and beyond—forge champions who carry your torch forward.";
        GameLogic.printWithDelay(GameLogic.centerText(20, prompt));
        GameLogic.pressAnything();
    }

    private void disappear(){
        GameLogic.clearConsole();
        String prompt = "Without fanfare, you disappear. The world speculates: did you retire? Were you injured? Did the thrill of\n" +
                        "victory finally fade? The mystery deepens as time passes, your name becoming a legend whispered in gyms and\n" +
                        "fight clubs across the globe.\n\n" +
                        "Years later, in a small, unassuming gym on the edge of town, a new crop of fighters begins their journey.\n" + 
                        "They train under a coach who moves with a precision and power that only the greatest could possess.\n" +
                        "Whispers swirl among the students: Could this be... the Champion?\n" +
                        "You never confirm or deny it. Instead, you focus on shaping your fighters into warriors—not just in the ring\n" +
                        "but in life. They may never know the truth about you, but your legacy burns brighter with each jab, each block,\n" +
                        "each hard-won victory they achieve.\n";
        GameLogic.printWithDelay(GameLogic.centerText(20, prompt));
        GameLogic.pressAnything();
    }

    private void sharedEnding(){
        GameLogic.clearConsole();
        String prompt = "As you lean against the ropes, the gym alive with the sounds of determination, your gaze falls on the\n" +
                        "trophies that line the walls. They're not just relics of your past; they're reminders of how far you've come\n" +
                        "and how far your students will go.\n\n" +
                        "You hear a loud thud as a young fighter lands their first perfect hook. The sound reverberates, filling\n" + 
                        "you with pride. The crowds may no longer chant your name, but in this moment, you know your\n" +
                        "impact is greater than ever.\n\n\n" +
                        "🥊 The End of a Fighter... The Start of a Legacy 🥊\n";
        GameLogic.printWithDelay(GameLogic.centerText(20, prompt));
        GameLogic.pressAnything();
    }

    public void gameEndingLogo(){
        GameLogic.clearConsole();
        player.setCurrentWorld(3);

        GameLogic.gameLogo();
        // String mainAscii = GameLogic.greenText + GameLogic.centerText(100,
        // "████████╗██╗  ██╗ █████╗ ███╗   ██╗██╗  ██╗███████╗    ███████╗ ██████╗ ██████╗     ██████╗ ██╗      █████╗ ██╗   ██╗██╗███╗   ██╗ ██████╗ \n" +
        // "╚══██╔══╝██║  ██║██╔══██╗████╗  ██║██║ ██╔╝██╔════╝    ██╔════╝██╔═══██╗██╔══██╗    ██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝██║████╗  ██║██╔════╝ \n" +
        // "   ██║   ███████║███████║██╔██╗ ██║█████╔╝ ███████╗    █████╗  ██║   ██║██████╔╝    ██████╔╝██║     ███████║ ╚████╔╝ ██║██╔██╗ ██║██║  ███╗\n" +
        // "   ██║   ██╔══██║██╔══██║██║╚██╗██║██╔═██╗ ╚════██║    ██╔══╝  ██║   ██║██╔══██╗    ██╔═══╝ ██║     ██╔══██║  ╚██╔╝  ██║██║╚██╗██║██║   ██║\n" +
        // "   ██║   ██║  ██║██║  ██║██║ ╚████║██║  ██╗███████║    ██║     ╚██████╔╝██║  ██║    ██║     ███████╗██║  ██║   ██║   ██║██║ ╚████║╚██████╔╝\n" +
        // "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝    ╚═╝      ╚═════╝ ╚═╝  ╚═╝    ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚═╝╚═╝  ╚═══╝ ╚═════╝ \n"
        // ) + GameLogic.reset;

        String mainAscii = GameLogic.greenText + GameLogic.centerText(100,
        "        ███        ▄█    █▄       ▄████████ ███▄▄▄▄      ▄█   ▄█▄    ▄████████         ▄████████  ▄██████▄     ▄████████         ▄███████▄  ▄█          ▄████████ ▄██   ▄    ▄█  ███▄▄▄▄      ▄██████▄  \n" +
        "    ▀█████████▄   ███    ███     ███    ███ ███▀▀▀██▄   ███ ▄███▀   ███    ███        ███    ███ ███    ███   ███    ███        ███    ███ ███         ███    ███ ███   ██▄ ███  ███▀▀▀██▄   ███    ███ \n" +
        "       ▀███▀▀██   ███    ███     ███    ███ ███   ███   ███▐██▀     ███    █▀         ███    █▀  ███    ███   ███    ███        ███    ███ ███         ███    ███ ███▄▄▄███ ███▌ ███   ███   ███    █▀  \n" +
        "        ███   ▀  ▄███▄▄▄▄███▄▄   ███    ███ ███   ███  ▄█████▀      ███              ▄███▄▄▄     ███    ███  ▄███▄▄▄▄██▀        ███    ███ ███         ███    ███ ▀▀▀▀▀▀███ ███▌ ███   ███  ▄███        \n" +
        "        ███     ▀▀███▀▀▀▀███▀  ▀███████████ ███   ███ ▀▀█████▄    ▀███████████      ▀▀███▀▀▀     ███    ███ ▀▀███▀▀▀▀▀        ▀█████████▀  ███       ▀███████████ ▄██   ███ ███▌ ███   ███ ▀▀███ ████▄  \n" +
        "        ███       ███    ███     ███    ███ ███   ███   ███▐██▄            ███        ███        ███    ███ ▀███████████        ███        ███         ███    ███ ███   ███ ███  ███   ███   ███    ███ \n" +
        "        ███       ███    ███     ███    ███ ███   ███   ███ ▀███▄    ▄█    ███        ███        ███    ███   ███    ███        ███        ███▌    ▄   ███    ███ ███   ███ ███  ███   ███   ███    ███ \n" +
        "       ▄████▀     ███    █▀      ███    █▀   ▀█   █▀    ███   ▀█▀  ▄████████▀         ███         ▀██████▀    ███    ███       ▄████▀      █████▄▄██   ███    █▀   ▀█████▀  █▀    ▀█   █▀    ████████▀  \n" +
        "                                                        ▀                                                     ███    ███                   ▀                                                            \n"
        ) + GameLogic.reset;

        System.out.print(mainAscii);
        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();

        System.exit(0);
    }
}
