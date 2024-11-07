package world2;

import world1.GameLogic;
import world1.StreetFighter;
import world1.Tournament;

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

    public static void tournaOpponentBackstory(StreetFighter opponent) {
        Tournament.showOpStats(opponent);
        switch (opponent.getName()) {
            case "Julio Navarro":
                System.out.println("\n\t\t🔥Backstory: Introducing Julio \"The Reaper\" Navarro, a ruthless fighter from the streets of Manila." +
                        "\n\t\tRaised in the underworld and hardened by gang violence, Julio earned his reputation through brutal street brawls." +
                        "\n\t\tKnown for his dirty tactics and no-mercy approach, he's earned the nickname 'The Reaper' for his devastating fights." +
                        "\n\t\tNow, he steps into the underground tournament, fighting not just for money, but for vengeance and the chance to rise above his past!" +
                        "\n\t\tWith every match, Julio fights to prove his strength and claim the top spot in the world of dirty boxing!");
                GameLogic.pressAnything();
                break;
            case "Raul Villanueva":
                System.out.println("\n\t\t🔥Backstory: Meet Raul \"The Bullseye\" Villanueva, a sharpshooter in the underground boxing scene." +
                        "\n\t\tRaised in the rough neighborhoods of Cebu, Raul earned his moniker for his deadly precision and accuracy in the ring." +
                        "\n\t\tA former marksman in the military, he brings a calm, calculated approach to his brutal fights, targeting his opponent’s weaknesses." +
                        "\n\t\tRaul enters the tournament with one goal: to prove that his focus and discipline can take him to the top, no matter the cost!");
                GameLogic.pressAnything();
                break;
            case "Ralfo Salvahez":
                System.out.println("\n\t\t🔥Backstory: Ralfo \"The Ghost\" Salvahez, a feared and infamous fighter in the underground boxing world, is a man with a dark past." +
                        "\n\t\tOnce a champion in his prime, Ralfo abandoned his family, leaving his young son behind to pursue glory in the ring." +
                        "\n\t\tYears of betrayal and silence have fueled a burning resentment in the child he left behind — now a rising star in the same brutal world." +
                        "\n\t\tKnown as \"The Ghost\" for his silent, elusive fighting style, Ralfo’s return to the ring is not just to fight, but to face the consequences of his past." +
                        "\n\t\tThe child, now grown, has risen through the ranks, and the ultimate showdown awaits: father versus child, in a fight that will either redeem or destroy them both!");
                GameLogic.pressAnything();
                break;
            default:
                System.out.println("No backstory available for this opponent.");
        }
    }
}
