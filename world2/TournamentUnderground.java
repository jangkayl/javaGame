package world2;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world1.Tournament;
import world2.TournaUnderFight.NavarroTourna;
import world2.TournaUnderFight.RaulTourna;
import world2.TournaUnderFight.SalvahezTourna;
import world3.GameLogic3;

public class TournamentUnderground {
    private static StreetFighter opponent;
    private static Player player = GameLogic.player;
    private static String[] opponents = {"The Reaper", "The Bullseye", "The Ghost"};
    private static PlayerProgress playerProgress = GameLogic.playerProgress;

    public void setOpponent(StreetFighter enemy){
        opponent = enemy;
    }

    public static void attemptTournament(int playerAddStats) {
        GameLogic.clearConsole();
        if (playerAddStats < 5) {
            System.out.println(GameLogic.centerBox(
                    "⚔️ UNDERGROUND TOURNAMENT ENTRY ⚔️\n" +
                            "You enter a dimly lit arena, the air thick with sweat and smoke...\n\n" +
                            "[ A grizzled official glares at you, a crooked grin forming ]\n" +
                            "Gatekeeper: \"Whoa there! You think you can just stroll in here? You ain't ready for this blood pit!\n" +
                            "One hit and you'll be begging for mercy. You need to spar harder and toughen up! \n" 
                            + "Earn your stripes before stepping into this ring!\"\n\n" +
                            "🏋️ Tip: Train hard, spar fiercely, and build your strength to earn a shot at the underground brawls! 🏆\n" +
                            "🔥 Prove your worth in 3 sparring matches before stepping into the ring of legends! 🔥", 110));

            resetMatchScores();
            GameLogic.pressAnything();
            GameLogic.gameData.saveGame();
        } else {
            startTournament();
        }
    }

    public static void startTournament() {
        GameLogic.clearConsole();
        System.out.print(GameLogic.centerBox("🏆 Underground Brawl Tournament 🏆",60));

        if(playerProgress.getPlayerWins() == 0 && playerProgress.getOpponentWins() == 0){
            playerProgress.setRound(1);
        }

        if(player.getStage() < 12){
            System.out.print(GameLogic.centerText(50,"Welcome, " + player.getName() + "! This is no ordinary fight - it's a battle for survival in the underground ring!"));
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
        System.out.print(GameLogic.centerBox(opponentIndex == 2 ? "FINAL OPPONENT: " : "You will face: " + opponentName, 50));
    
        
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
            default -> System.out.print(GameLogic.centerBox("Invalid opponent index.",50));
        }
    }

    private static boolean visitShopOrInventory() {
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(80)));
        System.out.print(GameLogic.centerText(80,"Before continuing, would you like to visit the shop or check your inventory?"));
        System.out.print(GameLogic.centerText(80,"1. Visit Shop"));
        System.out.print(GameLogic.centerText(80,"2. Check Inventory"));
        System.out.print(GameLogic.centerText(80,"0. Continue Tournament"));
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(30)));
        System.out.print(GameLogic.centerText(80,"Enter your choice: "));
        
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
        System.out.print(GameLogic.centerText("You lost your previous match. Would you like to:",50));
        System.out.print(GameLogic.centerText("1. Try the tournament again?",50));
        System.out.print(GameLogic.centerText("2. Boost your stats by sparring more!",50));
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(60)));
        System.out.print(GameLogic.centerText("Enter your choice (1 or 2): ",50));
        
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
        System.out.println("\n");
        System.out.print(GameLogic.centerBox(" ~ ~ ~ BEST OF 3 ~ ~ ~\n" +
                "      " + player.getName() + " - " + playerProgress.getPlayerWins() + "   ||   " +
                opponent.getName() + " - " + playerProgress.getOpponentWins(), 50));
        GameLogic.pressAnything();
    }

    private static void concludeTournament() {
        System.out.println("\n\n");
        System.out.println(GameLogic.centerBox(
                "🥊 Champion of the Underground! 🥊\n" +
                        "You've dominated the Dirty Boxing Tournament, emerging as the fiercest fighter in the shadows.\n" +
                        "Your name strikes fear in the underground, but the chaos never ends...", 110));

        GameLogic.pressAnything();
        System.out.println("\n");
        System.out.println(GameLogic.centerBox(
                "As you stand victorious, your father steps forward, his face filled with regret.\n" +
                        "He opens his mouth to confess why he abandoned you... but suddenly, chaos erupts.\n" +
                        "Gunshots echo, and you hear the shout: 'Police raid! Everybody down!' The room is thrown into chaos!", 110));

        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(90)));
        System.out.print(GameLogic.centerText("Choose your path:",50));
        System.out.print(GameLogic.centerText("(1) Run and escape the raid.",50));
        System.out.print(GameLogic.centerText("(2) Stay and hear your father's confession, then fight the police.",50));
        int choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1){
            runAndEscape();
        } else {
            stayAndHearConfession();
        }
        GameLogic.pressAnything();
        
        playerProgress.setAddStats(0);
        player.setCurrentWorld(2);
        if(player.getCurrentWorld() == 2) {
            GameLogic3.gameLoop();
        }
    }

    private static void stayAndHearConfession() {
        GameLogic.clearConsole();
        String message = "You choose to stay, ignoring the chaos around you, as your father begins to speak." + 
                        "\n\nSalvahez: \"Son\"" + "\n\n[ He says, his voice trembling. ]" + 
                        "\n\nSalvahez: \"Your mother was sick for a long time... longer than you ever knew." +
                        "\nI couldn't bear to see her like that, and I knew I had to do something. The underground world was my escape." +
                        "\nIt gave me the money to take care of her, but it came at a cost.\"";
        System.out.print(GameLogic.centerBox(message, 110));        
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ He looks away, ashamed, as tears well up in his eyes. ]" + 
                        "\n\nSalvahez: \"I was blinded by greed. The money was too much, and... I got lost in it." +
                        "\nIllegal substances numbed the pain, but they also made me forget about her... about you.\"";
        System.out.print(GameLogic.centerBox(message, 110));        
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ The sound of gunshots grows louder, but he continues. ]" + 
                  "\n\nSalvahez: \"I failed her, son. I failed you. But if you're going to take anything from this..." +
                  "\ndon't make the same mistakes I did.\"" + 
                  "\n\n[ As he finishes, you hear the police shouting orders. Your father grabs your arm. ]" + 
                  "\n\nSalvahez: \"We have to get out of here!\" he says, pulling you toward the exit.";
        System.out.print(GameLogic.centerBox(message, 110));        
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ You both run, dodging through the chaos, but suddenly... A gunshot rings out." +
                "\nYou feel your father's grip loosen as he collapses to the ground, clutching his side. ]" +
                "\n\nSalvahez: \"Go! You have to live! Don't waste it like I did!\"";
        System.out.print(GameLogic.centerBox(message, 110));
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ But you can't leave him. Tears streaming down your face, you stay by his side." +
                    "\nThe police close in, and you surrender, knowing there's no other way out. ]";
        System.out.print(GameLogic.centerBox(message, 110));
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ The next five years pass slowly in prison. You reflect on your father's words, " + 
                    "\nvowing to rise above the darkness that consumed him. When you finally walk free, you're not" +
                    "\nthe same person anymore. You carry his legacy, his mistakes, and a burning desire to forge a better path. ]";
        System.out.print(GameLogic.centerBox(message, 110));
        System.out.println();
        GameLogic.pressAnything();

        stayAfterMath();
    }

    private static void stayAfterMath() {
        GameLogic.clearConsole();
        String message = "[ As you step out of the prison gates, a familiar voice calls out. ]" +
                        "\n\nCoach Fred: \"Hey, kid. Been a while.\"";
        System.out.print(GameLogic.centerBox(message, 110));
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "Coach Fred: \"I've followed your story - your mom, the underground, prison. You've been through it all, " +
                  "\nbut I know you've still got that fire. The Champs Arena is looking for fighters, and I know you belong there.\"" +
                  "\n\n[ He places a hand on your shoulder. ]" +
                  "\n\nCoach Fred: \"This is your shot at a fresh start.\"";
        System.out.print(GameLogic.centerBox(message, 115));
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ With a nod, you accept the offer, determination burning in your eyes. ]" +
                  "\n\n" + player.getName() + ": \"I'm ready, Coach. Let's do this.\"" +
                  "\n\nCoach Fred: \"That's my fighter. Welcome back.\"";
        System.out.print(GameLogic.centerBox(message, 110));
        System.out.println();

        GameLogic.clearConsole();
        message = "[ After spending 5 long years in prison, the toll it took on your body is undeniable." +
                        "\nYou've lost 10% of your health and stamina. The harsh reality of confinement has left its mark.";
        System.out.print(GameLogic.centerBox(message, 115));
        System.out.println();

        player.setMaxStamina(player.getMaxStamina() - (int)(player.getMaxStamina() * 0.10));
        player.setStamina(player.getMaxStamina());

        player.setMaxHp(player.getMaxHp() - (int)(player.getMaxHp() * 0.10));
        player.setHp(player.getMaxHp());
    }

    private static void runAndEscape() {
        GameLogic.clearConsole();
        String message = "[ After escaping the chaos of the raid, you try to live quietly, but life doesn't get any easier." +
                         "\nYour mother's illness worsens, and the hospital bills keep piling up. You feel the weight of it all." +
                         "\nThe next morning, a knock at the door startles you. ]";
        System.out.print(GameLogic.centerBox(message, 115));
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ You open the door to see Coach Fred, his expression serious yet hopeful. ]" +
                  "\n\nCoach Fred: \"Morning, kid. I heard about your mom. I know times are tough, and that last tournament didn't cut it.\"" +
                  "\n\n[ He pauses, then places a hand on your shoulder. ]" +
                  "\n\nCoach Fred: \"But I also know your potential. The Champs Arena is where you belong." + 
                  "\nIt's your shot at real money, real recognition and a chance to make things right.\"";
        System.out.print(GameLogic.centerBox(message, 120));
        System.out.println();
        GameLogic.pressAnything();
    
        GameLogic.clearConsole();
        message = "[ You think of your mother, her struggle, and the path ahead. With a deep breath, you nod. ]" +
                  "\n\n" + player.getName() + ": \"Alright, Coach. Let's do this for her.\"" +
                  "\n\nCoach Fred: \"That's the fighter I know. Let's get to work.\"";
        System.out.print(GameLogic.centerBox(message, 110));
        System.out.println();
    }
    
}
