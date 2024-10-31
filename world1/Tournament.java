package world1;

import java.util.Scanner;

import world1.TournamentFight.RamirezTourna;

public class Tournament {
    private static Player player = GameLogic.player;
    private static String[] opponents = {"El Tigre"};
    static PlayerProgress playerProgress = GameLogic.playerProgress;
    public static StreetFighter opponent1 = new StreetFighter("Rico Ramirez", 150, 80, 0.2, 2, .30);

    public static void attemptTournament(int playerStage) {
        GameLogic.clearConsole();
        if (playerStage < 3) {
            System.out.println("⚔️  Tournament Entry Attempt  ⚔️");
            GameLogic.printSeparator(40);
            System.out.println("You step forward, ready to face the toughest challengers...");
            System.out.println();
            GameLogic.printSeparator(40);
            System.out.println("[ But the tournament official stops with a grin ]");
            System.out.println("Gatekeeper: \"Hold it right there! You're not ready for this arena yet. Step into the ring now, and one solid hit");
            System.out.println("\t\t\tHead back to the Train in Gym, build your skills and get stronger will have you on the floor before you even know it!");
            System.out.println("\t\t\tGo back to Coach Fred at the gym, work those muscles, and test your mettle in sparring. No pulling punches—this is where the real training begins!\"");
            System.out.println();
            System.out.println("🏋️ Tip: Train hard, rank up, and grow stronger to unlock the tournament! 🏆");
            GameLogic.pressAnything();
        } else {
            startTournament();
        }
    }

    public static void startTournament() {
        GameLogic.clearConsole();
        GameLogic.printHeading("\t🏆 Champ Arena Tournament 🏆");
        System.out.println("Welcome, " + player.getName() + "! Prepare to fight your way to the top!");
        playerProgress.setDone(1);

        while(true){
            if (player.getStage() == 3) {
                startMatch(0);
                if (playerProgress.getOpponentWins() == 3) {
                    if (!offerRematch()) {
                        break;
                    } else {
                        resetMatchScores();
                        continue;
                    }
                } else if(playerProgress.getPlayerWins() == 3){
                    player.setStage(4);
                    resetMatchScores();
                }
                GameLogic.pressAnything();
            } else if (player.getStage() == 4) {
                startMatch(1);
                if (playerProgress.getOpponentWins() == 3) {
                    if (!offerRematch()) {
                        break;
                    } else {
                        resetMatchScores();
                        continue;
                    }
                } else if(playerProgress.getPlayerWins() == 3){
                    player.setStage(4);
                    resetMatchScores();
                }
                GameLogic.pressAnything();
            }
        }
        // concludeTournament();
        GameLogic.pressAnything();
    }

    private static void startMatch(int opponentIndex) {
        System.out.println("You will face: " + opponents[opponentIndex]);
        UrbanStory.tournaOpponentBackstory(opponents[opponentIndex]);
        
        while (!isMatchConcluded()) {
            RamirezTourna.setPlayer(player);
            StreetFighter.setPlayerOpponent(player);
            RamirezTourna.fightLoop2();
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
        System.out.println("You lost this match. Do you want a rematch? (yes/no)");
        
        String response = GameLogic.scan.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    private static void concludeTournament() {
        System.out.println("Tournament has ended. Thank you for participating!");
        if (player.getCurrentRank().equals("CHAMPION")) {
            System.out.println("You've solidified your status as a champion! Keep training to maintain your rank.");
        }
    }

    public static void printTournament() {
        GameLogic.printHeading("\t\tTournament Rules");
        System.out.println("1. If you lose, the tournament ends.");
        System.out.println("2. You will face a series of opponents.");
        System.out.println("3. Defeat all opponents to win the tournament.");
        System.out.println("4. Earn points for each win to strengthen your character.");
        GameLogic.clearConsole();
    }

    public static void printStanding(){
        System.out.println();  
        System.out.println("\t ~ ~ ~ BEST OF 3 ~ ~ ~");
        System.out.println(player.getName() + " - " + playerProgress.getPlayerWins() + "\t\t" + opponent1.getName() + " - " + playerProgress.getOpponentWins());
        GameLogic.pressAnything();
    }
}
