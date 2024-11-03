package world2;

import world1.GameLogic;

public class StoryUnderground {
    public static void printUnderground() {
        System.out.println();
        System.out.println("Welcome to the Underground Arena, where the air is thick with tension and opportunity.");
        System.out.println("In this gritty world, fighters engage in ruthless matches, pushing the limits of dirty boxing.");
        System.out.println();
        System.out.println("Under the watchful eye of the enigmatic Coach, you train hard, mastering every underhanded tactic.");
        System.out.println("Every punch, every slip, and every strategy is crucial as you prepare for the high-stakes battles ahead.");
        System.out.println("Once you're ready, step into the ring and prove your mettle in tournaments where cash flows like water.");
        System.out.println("Victory will not only bring glory but also piles of cash and the chance to ascend to the next level of this underground world.");
        System.out.println();
        System.out.println("Keep fighting for your fortune!");
        System.out.println();
    }

    public static void printIntroduction(String name){
        GameLogic.clearConsole();
        GameLogic.printSeparator(50);
        System.out.println();
        System.out.println("Niko: \t\"Alright, if you're serious about making it down here, there's someone you need to meet.");
        System.out.println("\tThis is Coach Jakester. He's a legend in these underground circles knows every trick, every dirty move you'll need.\"");
        System.out.println();
        System.out.println("Niko: \t\"He's the guy who can turn you into a real contender, but it won't be easy. Think you're up for it?\"");
        GameLogic.pressAnything();
        System.out.println();
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println();
        System.out.println("Coach Jakester: \t\"Welcome to the underground, " + name + ". You play by my rules, and I'll show you how to survive and make some real money while you're at it.\"");
        System.out.println();
        GameLogic.printSeparator(50);
        GameLogic.pressAnything();
        undergroundPrompt1(name);
    }

    public static void undergroundPrompt1(String name){
        GameLogic.clearConsole();
        GameLogic.printSeparator(50);
        System.out.println();
        System.out.println("Coach Jakester: \t\"Alright, Kyle, welcome to the underbelly of the fighting world. Down here, you can double your money if you bet smart.");
        System.out.println("\tThe fights are brutal no rules, no limits. It's all about who's willing to go farther.\"");
        System.out.println();
        System.out.println("\t( You watch as a cage fight unfolds, raw and ruthless, with the crowd roaring as every brutal hit lands. )");
        System.out.println();
        System.out.println("Coach Jakester: \t\"And see that row of stalls over there? That's the black market. Need a boost? They've got all kinds of... enhancers.");
        System.out.println("\tIllegal, sure but they'll give you an edge. Gear, boosters, meds, anything you need to keep you in the game.\"");
        System.out.println();
        System.out.println("\t( You catch sight of shady vendors and dark exchanges, each promising an advantage for a price. )");
        System.out.println();
        GameLogic.printSeparator(50);
        GameLogic.pressAnything();
    }
    
}
