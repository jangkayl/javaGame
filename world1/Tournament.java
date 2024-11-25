package world1;

import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.StreetFighter;
import world1.TournamentFight.LopezTourna;
import world1.TournamentFight.RamirezTourna;
import world1.TournamentFight.TettehTourna;
import world2.GameLogic2;

public class Tournament {
    private static StreetFighter opponent;
    private static Player player = GameLogic.player;
    private static String[] opponents = {"El Tigre", "El Jablo", "El Taeh"};
    private static PlayerProgress playerProgress = GameLogic.playerProgress;

    public void setOpponent(StreetFighter enemy){
        opponent = enemy;
    }

    public StreetFighter getOpponent(){
        return opponent;
    }

    public static void attemptTournament(int playerStage) {
        GameLogic.clearConsole();
        if (playerStage < 3) {
            String text = GameLogic.redText + GameLogic.centerText(100,
                         "▀▀█▀▀ █▀▀█ █░░█ █▀▀█ █▀▀▄ █▀▀█ █▀▄▀█ █▀▀ █▀▀▄ ▀▀█▀▀ 　 ▒█▀▀▀ █▀▀▄ ▀▀█▀▀ █▀▀█ █░░█ 　 ░█▀▀█ ▀▀█▀▀ ▀▀█▀▀ █▀▀ █▀▄▀█ █▀▀█ ▀▀█▀▀\n" +
                         "░▒█░░ █░░█ █░░█ █▄▄▀ █░░█ █▄▄█ █░▀░█ █▀▀ █░░█ ░░█░░ 　 ▒█▀▀▀ █░░█ ░░█░░ █▄▄▀ █▄▄█ 　 ▒█▄▄█ ░░█░░ ░░█░░ █▀▀ █░▀░█ █░░█ ░░█░░\n" +
                         "░▒█░░ ▀▀▀▀ ░▀▀▀ ▀░▀▀ ▀░░▀ ▀░░▀ ▀░░░▀ ▀▀▀ ▀░░▀ ░░▀░░ 　 ▒█▄▄▄ ▀░░▀ ░░▀░░ ▀░▀▀ ▄▄▄█ 　 ▒█░▒█ ░░▀░░ ░░▀░░ ▀▀▀ ▀░░░▀ █▀▀▀ ░░▀░░\n\n") + GameLogic.reset;

            System.out.print(text);
            System.out.println();
            
            System.out.print(GameLogic.centerText(20, 
                                            "You step forward, ready to face the toughest challengers...\n\n" +
                                            "[ But the tournament official stops with a grin ]\n" +
                                            "\nGatekeeper: \"Hold it right there! You're not ready for this arena yet. \nStep into the ring now, and one solid hit" +
                                            "will have you on the floor before you even know it!\"\n" +
                                            "\n\"Head back to the Train in Gym, build your skills and get stronger.\n" +
                                            "Go back to Coach Fred at the gym, work those muscles, and test your mettle in sparring.\n" +
                                            "No pulling punches—this is where the real training begins!\"\n\n" +
                                            "🏋️Tip: Train hard, rank up, and grow stronger to unlock the tournament!"));
            GameLogic.pressAnything();
        } else {
            startTournament();
        }
    }

    public static void startTournament() {
        GameLogic.clearConsole();
        String text = GameLogic.redText + GameLogic.centerText(100,
                     "▒█▀▀█ █░░█ █▀▀█ █▀▄▀█ █▀▀█ 　 ░█▀▀█ █▀▀█ █▀▀ █▀▀▄ █▀▀█ 　 ▀▀█▀▀ █▀▀█ █░░█ █▀▀█ █▀▀▄ █▀▀█ █▀▄▀█ █▀▀ █▀▀▄ ▀▀█▀▀ \n" +
                     "▒█░░░ █▀▀█ █▄▄█ █░▀░█ █░░█ 　 ▒█▄▄█ █▄▄▀ █▀▀ █░░█ █▄▄█ 　 ░▒█░░ █░░█ █░░█ █▄▄▀ █░░█ █▄▄█ █░▀░█ █▀▀ █░░█ ░░█░░ \n" +
                     "▒█▄▄█ ▀░░▀ ▀░░▀ ▀░░░▀ █▀▀▀ 　 ▒█░▒█ ▀░▀▀ ▀▀▀ ▀░░▀ ▀░░▀ 　 ░▒█░░ ▀▀▀▀ ░▀▀▀ ▀░▀▀ ▀░░▀ ▀░░▀ ▀░░░▀ ▀▀▀ ▀░░▀ ░░▀░░" ) + GameLogic.reset;

        System.out.print(text);

        if(player.getStage() < 6){
            System.out.println();
            System.out.print(GameLogic.centerText(50,"Welcome, " + player.getName() + "! Prepare to fight your way to the top!"));
            System.out.println();
            playerProgress.setDone(1);
            printTournament();

            while(true){
                if (playerProgress.getOpponentWins() == 3 && player.getIsLose() == true) {
                    if(offerRematch()){
                        resetMatchScores();
                        continue;
                    } else {
                        GameLogic.gymTraining();
                        return;
                    }
                }
    
                // Before each major stage, encourage checking the shop or inventory
                if (player.getStage() == 3) {
                    if (visitShopOrInventory()) {
                        startMatch(0);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(4);
                            resetMatchScores();
                            continue;
                        }
                    }
                } else if (player.getStage() == 4) {
                    if (visitShopOrInventory()) {
                        startMatch(1);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(5);
                            resetMatchScores();
                            continue;
                        }
                    }
                } else if (player.getStage() == 5) {
                    if (visitShopOrInventory()) {
                        startMatch(2);
                        if (playerProgress.getPlayerWins() == 3) {
                            player.setStage(6);
                            resetMatchScores();
                            break;
                        }
                    }
                }
            }
        }
        concludeTournament();
        if(player.getCurrentWorld() == 1) {
            GameLogic2.gameLoop();
        }
    }

    private static void startMatch(int opponentIndex) {
        System.out.println();
        System.out.println();
    
        String opponentName = opponents[opponentIndex];
        System.out.print(GameLogic.redText);
        System.out.print(GameLogic.centerBox((opponentIndex == 2 ? "FINAL OPPONENT: " : "You will face: ") + opponentName, 50));
        System.out.print(GameLogic.reset);
        
        // Initialize opponent based on the index
        switch (opponentIndex) {
            case 0 -> {
                opponent = new StreetFighter("Rico Ramirez", 150, 80, 0.2, 2, 0.30, 2);
                opponent.setPlayerOpponent(player);
                UrbanStory.tournaOpponentBackstory(opponent);
                fightWithOpponent(new RamirezTourna(player, opponent));
            }
            case 1 -> {
                opponent = new StreetFighter("Oscar Lopez", 170, 100, 0.2, 2, 0.30, 2);
                opponent.setPlayerOpponent(player);
                UrbanStory.tournaOpponentBackstory(opponent);
                fightWithOpponent(new LopezTourna(player, opponent));
            }
            case 2 -> {
                opponent = new StreetFighter("Ishmael Tetteh", 200, 120, 0.3, 2.5, 0.40, 3);
                opponent.setPlayerOpponent(player);
                UrbanStory.tournaOpponentBackstory(opponent);
                fightWithOpponent(new TettehTourna(player, opponent));
            }
            default -> System.out.println("Invalid opponent index.");
        }
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
        System.out.print(GameLogic.redText);
        System.out.print(GameLogic.centerBox("You lost your previous match. Would you like to:", 55));
        System.out.print(GameLogic.reset);
        System.out.println();
        System.out.print(GameLogic.orangeText);
        System.out.print(GameLogic.centerText(80, "1. Try the tournament again?"));
        System.out.print(GameLogic.centerText(80, "2. Train with Fred or your coach to sharpen your skills and gain more stats!"));
        System.out.println();
        System.out.print(GameLogic.centerText(80, "Enter your choice (1 or 2): "));
        
        System.out.print(GameLogic.reset);
        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 2);
        if (choice == 1) {
            playerProgress.setPlayerWins(0);
            playerProgress.setOpponentWins(0);
            player.setStage(3);
            return true;  
        } else {
            return false;
        }
    }

    private static void concludeTournament() {
        GameLogic.clearConsole();
        player.setRank(3);
        GameLogic.rankReward();
        System.out.print(GameLogic.blueText);
        System.out.println(GameLogic.centerBox("🥊 Congratulations, Champion of the Ring! 🥊\n" +
                "You've conquered the tournament, delivering knockout blows and proving you have the heart of a true fighter!\n" +
                "The crowd roars, and your name is now legend. \nBut the journey isn't over...\n\n" +
                "As you return home, you can still hear the echoes of cheering fans, the thrill of victory fresh in your mind.\n" +
                "But your celebration is cut short when you see your childhood friend, \nNiko, waiting anxiously outside your door.", 115));
        System.out.print(GameLogic.reset);
        GameLogic.pressAnything();

        System.out.println("\n\n");
        System.out.println(GameLogic.centerBox(
                "Niko: \"Hey, I'm glad I found you. It's urgent... your mom has been taken to the hospital. \nShe needs help, and it doesn't look good.\"\n\n" +
                "( Panic rises in your chest as you race to the hospital, each step heavier than the last. )\n\n" +
                "You find your mother weak, hooked to machines. The doctors say the operation could save her life, \nbut it's costly, your recent winnings barely cover it.\n", 100));
        GameLogic.pressAnything();
    
        System.out.println("\n\n");
        System.out.println(GameLogic.centerBox(
                "Niko: \"I know it's tough, but there's another way. It's risky and illegal... \nFighters can earn big money in the underground ring.\"\n" +
                        "It's brutal, but you could make what you need fast.\"\n\n" +
                        "( Niko's eyes are serious, and you can see the weight of the decision settling in. )\n\n" +
                        "You take a deep breath, feeling the burden of your mother's health pressing down on you.\n\n" +
                        "Niko: \"It's dangerous, but if you're as good as I think you are, you could pull this off. \nJust think about it...\"\n", 100));
        GameLogic.pressAnything();
    
        System.out.println("\n\n");
        System.out.println(GameLogic.centerBox(
                "With your mother's life at stake, you know what you must do. You nod at Niko.\n\n" +
                        "Niko: \"Alright, let's do this. I'll show you the ropes. \nGet ready for a new world of fighting!\"\n", 100));
        GameLogic.pressAnything();

        // Update the player's world/state to reflect the new story path.
        playerProgress.setAddStats(0);
        player.setCurrentWorld(1);
    }
    
    public static void showOpStats(StreetFighter opponent){
        System.out.println("\n");
        System.out.print(GameLogic.centerText(20, opponent.getName()));
        System.out.print(GameLogic.centerText(20, "* " + opponent.getRank() + " *"));
        System.out.print(GameLogic.centerText(55, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(" ", 80));
        System.out.println(GameLogic.formatColumns(" HP:",opponent.getHp() + " / " + opponent.getMaxHp(), 30));
        System.out.print(GameLogic.centerText(55, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(" ", 80));
        System.out.println(GameLogic.formatColumns(" Stamina:",opponent.getStamina() + " / " + opponent.getMaxStamina(), 30));
        System.out.print(GameLogic.centerText(55, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(" ", 80));
        System.out.println(GameLogic.formatColumns(" Critical Chance:", GameLogic.df.format(opponent.getCritChance() * 100) + "%", 30));
        System.out.print(GameLogic.centerText(55, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(" ", 80));
        System.out.println(GameLogic.formatColumns(" Critical Multiplier:", GameLogic.df.format(opponent.getCritMultiplier()) + "x", 30));
        System.out.print(GameLogic.centerText(55, GameLogic.printCenteredSeparator(50)));
        System.out.print(GameLogic.centerText(" ", 80));
        System.out.println(GameLogic.formatColumns(" Dodge Chance:", GameLogic.df.format(opponent.getDodgeChance() * 100) + "%", 30));
    }

    public static void printTournament() {
        System.out.print(GameLogic.blueText);
        System.out.print(GameLogic.centerBox("Tournament Rules", 50));
        System.out.print(GameLogic.reset);
        System.out.println();
        System.out.print(GameLogic.centerText(80, "1. You will face 3 opponents in this tournament."));
        System.out.print(GameLogic.centerText(80, "2. Each opponent match is a best-of-3 rounds."));
        System.out.print(GameLogic.centerText(80, "3. Win 3 rounds against each opponent to proceed."));
        System.out.print(GameLogic.centerText(80, "4. If you lose a best-of-3 match, you are out of the tournament \nand must return to training."));
        System.out.print(GameLogic.centerText(80, "5. Defeat all 3 opponents to claim victory in the tournament."));
    }
    
    private static boolean visitShopOrInventory() {
        System.out.println();
        System.out.print(GameLogic.orangeText);
        System.out.print(GameLogic.centerText(80,"Before continuing, would you like to visit the shop or check your inventory?"));
        System.out.print(GameLogic.centerText(80,"1. Visit Shop"));
        System.out.print(GameLogic.centerText(80,"2. Check Inventory"));
        System.out.print(GameLogic.centerText(80,"0. Continue Tournament"));
        System.out.println();
        System.out.print(GameLogic.centerText(80,"Enter your choice: "));
        
        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 0, 2);
        System.out.print(GameLogic.reset);
        if (choice == 0) {
            if(playerProgress.getOpponentWins() == 3 && player.getIsLose() == false){
                resetMatchScores();
                GameLogic.gameData.saveGame();
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
}
