package world2;

import world1.GameLogic;

public class Tournament {
    public static void attemptTournament(int playerStage) {
        GameLogic.clearConsole();
        if (playerStage < 10) {
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
            GameLogic.pressAnything();
        } else {
            
        }
    }

}
