package world2;

import world1.GameLogic;

public class Sparring {
    public static void enterSparring() {
        GameLogic.clearConsole();
        System.out.println("🥊  SPARRING ARENA - DIRTY BOXING FIGHT 🥊");
        GameLogic.printSeparator(40);
        System.out.println("You step into the gritty sparring arena, the atmosphere thick with tension and excitement...");
        System.out.println();
        GameLogic.printSeparator(40);
        System.out.println("Fighters around you are placing bets, eager to double their cash while proving their strength in the ring!");
        System.out.println("Prepare yourself for a dirty boxing match where anything goes. Victory means not just glory, but also cash and valuable points to boost your stats!");
        System.out.println("But beware: losing a fight can cost you hard-earned stats! Are you ready to risk it all for fame and fortune? Show the world your skills!");
        System.out.println();
        System.out.println("💰 Tip: Fight fiercely, win rewards, and enhance your abilities with every match. But remember, losses can hurt your stats! 💰");
        GameLogic.pressAnything();
    }
    
}
