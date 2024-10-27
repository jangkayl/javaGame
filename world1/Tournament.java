package world1;
public class Tournament {
    private Player player;
    private static final int MAX_OPPONENTS = 3;
    private static final String[] OPPONENTS = {
            "Brave Challenger",
            "Savage Warrior",
            "Legendary Champion"
    };
    
    public static void notValidTournament(){ 
        GameLogic.printSeparator(40); GameLogic.printHeading("TOURNAMENT - UNQUALIFIED"); 
        GameLogic.printSeparator(40); 
        System.out.println(); 
        System.out.println("Hold up there! You're not ready for this arena yet.'"); 
        System.out.println("Step into the ring now, and one solid hit will have you on the floor before you even know it!"); 
        System.out.println("Go back to Coach Fred at the gym, work those muscles, and test your mettle in sparring. No pulling punches—this is where the real training begins!"); 
        System.out.println("You’ll need more than just guts to survive in here, so sharpen those reflexes or get ready to see stars!"); 
        System.out.println(); 
        GameLogic.pressAnything();
    }

    public Tournament(Player player) {
        this.player = player;
    }

    public void attemptTournament(int playerStage) {
        GameLogic.clearConsole();
        GameLogic.printSeparator(40);
        System.out.println("⚔️  Tournament Entry Attempt  ⚔️");
        GameLogic.printSeparator(40);
        System.out.println("You step forward, ready to face the toughest challengers...");
        System.out.println();

        if (playerStage < 5) {
            System.out.println("But the tournament official stops with a grin");
            System.out.println("Gatekeeper: \"Hold it right there! You're not ready yet, you're still at stage " + playerStage + "\"");
            System.out.println("\t\t\tYou need to reach *Stage 5* before entering the tournament");
            System.out.println("\t\t\tHead back to the Training Gym, build your skills and get stronger");
            System.out.println("\t\t\tOnly once you've reached *Stage 5* status can you compete here.");
            System.out.println();
            System.out.println("🏋️ Tip: Train hard, rank up, and grow stronger to unlock the tournament! 🏆");
        } else {
            startTournament();
        }
    }

    public void startTournament() {
        GameLogic.clearConsole();
        GameLogic.printHeading("\t🏆 Champ Arena Tournament 🏆");
        System.out.println("Welcome, " + player.getName() + "! Prepare to fight your way to the top!");

        for (String opponentName : OPPONENTS) {
            System.out.println("You will face: " + opponentName);
            UrbanStory.tournaOpponentBackstory(opponentName);
            if (!battle(opponentName)) {
                System.out.println("You were defeated! Better luck next time.");
                break;
            }
        }

        concludeTournament();
    }

    private void concludeTournament() {
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

    private boolean battle(String opponentName) {
        StreetFighter opponent = new StreetFighter(opponentName, 100, 100, 0.1, 1.5, 0.1);
        player.setOpponent(opponent);
        GameLogic.clearConsole();
        System.out.println("A wild " + opponentName + " appears!");

        while (player.getHp() > 0 && opponent.getHp() > 0) {
            System.out.println("\n" + player.getName() + " HP: " + player.getHp() + " | " + opponentName + " HP: " + opponent.getHp());
            int action = getPlayerAction();

            executePlayerAction(action);

            if (opponent.getHp() <= 0) {
                System.out.println(opponentName + " has been defeated!");
                player.addPlayerPoints(10);
                System.out.println("You have earned 10 points!");
                GameLogic.pressAnything();
                return true;
            }

            executeOpponentAction(opponent);
        }

        System.out.println(player.getName() + " has been defeated!");
        return false;
    }

    private void displayHealthStatus(String opponentName, StreetFighter opponent) {
        System.out.println("\n" + player.getName() + " HP: " + player.getHp() + " | " + opponentName + " HP: " + opponent.getHp());
    }

    private int getPlayerAction() {
        System.out.println("Choose your action: ");
        System.out.println("1. Jab");
        System.out.println("2. Hook");
        System.out.println("3. Uppercut");
        System.out.println("4. Block");
        return GameLogic.readInt("-> ", 1, 4);
    }

    private void executePlayerAction(int action) {
        switch (action) {
            case 1: 
                player.jab();
                break;
            case 2: 
                player.hook();
                break;
            case 3: 
                player.uppercut();
                break;
            case 4: 
                player.block();
                break;
            default:
                System.out.println("Invalid action. Try again.");
        }
    }

    private void executeOpponentAction(StreetFighter opponent) {
        int opponentAction = (int) (Math.random() * 3);
        int damage = 0;

        switch (opponentAction) {
            case 0:
                opponent.jab();
                break;
            case 1:
                opponent.hook();
                break;
            case 2:
                opponent.uppercut();
                break;
            default:
                System.out.println("Opponent takes no action.");
                return;
        }

        player.setDamageSetter(damage);
        System.out.println("Opponent dealt " + damage + " damage!");
    }

}
