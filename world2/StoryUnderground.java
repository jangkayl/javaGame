package world2;

import world1.GameLogic;
import GlobalClasses.StreetFighter;
import world1.Tournament;

public class StoryUnderground {
    public static void printUnderground() {
        System.out.println();
        System.out.print(GameLogic.centerBox(
                "Welcome to the Underground Arena, where the air is thick with tension and opportunity.\n" +
                        "In this gritty world, fighters engage in ruthless matches, pushing the limits of dirty boxing.\n\n" +
                        "Under the watchful eye of the enigmatic Coach, you train hard, mastering every underhanded tactic.\n" +
                        "Every punch, every slip, and every strategy is crucial as you prepare for the high-stakes battles ahead.\n" +
                        "Once you're ready, step into the ring and prove your mettle in tournaments where cash flows like water.\n" +
                        "Victory will not only bring glory but also piles of cash and the chance \nto ascend to the next level of this underground world.\n\n" +
                        "Keep fighting for your fortune!\n", 110));
    }

    public static void printIntroduction(String name){
        GameLogic.clearConsole();
        System.out.print(GameLogic.centerBox(
                "Niko: \"Alright, if you're serious about making it down here, there's someone you need to meet.\n" +
                        "This is Coach Jakester. He's a legend in these underground circles, \nknows every trick, every dirty move you'll need.\"\n\n" +
                        "Niko: \"He's the guy who can turn you into a real contender, but it won't be easy. Think you're up for it?\"\n", 110));
        System.out.println();
        GameLogic.pressAnything();
        System.out.println("\n");
        System.out.println(GameLogic.centerBox("Coach Jakester: \"Welcome to the underground, " + name + ". \nYou play by my rules, and I'll show you how to survive and make some real money while you're at it.\"",110));
        System.out.println();
        GameLogic.pressAnything();
        System.out.println("\n");
        undergroundPrompt1(name);
    }

    public static void undergroundPrompt1(String name){
        GameLogic.clearConsole();
        System.out.print(GameLogic.centerBox(
                "Coach Jakester: \"Alright, " + name + ", welcome to the underbelly of the fighting world. Down here, \nyou can double your money if you bet smart." +
                        " The fights are brutal — no rules, no limits. \nIt's all about who's willing to go farther.\"\n\n" +
                        "( You watch as a cage fight unfolds, raw and ruthless, with the crowd roaring as every brutal hit lands. )\n\n" +
                        "Coach Jakester: \"And see that row of stalls over there? That's the black market. Need a boost? \nThey've got all kinds of... enhancers." +
                        " Illegal, sure, but they'll give you an edge. \nGear, boosters, meds, anything you need to keep you in the game.\"\n\n" +
                        "( You catch sight of shady vendors and dark exchanges, each promising an advantage for a price. )\n", 110));
        GameLogic.pressAnything();
    }

    public static void tournaOpponentBackstory(StreetFighter opponent) {
        Tournament.showOpStats(opponent);
        System.out.print(GameLogic.orangeText);
        switch (opponent.getName()) {
            case "Julio Navarro":
                System.out.println(GameLogic.centerText(20, "\n🔥Backstory: Introducing Julio \"The Reaper\" Navarro, a ruthless fighter from the streets of Manila." +
                        "\nRaised in the underworld and hardened by gang violence, Julio earned his reputation through brutal street brawls." +
                        "\nKnown for his dirty tactics and no-mercy approach, he's earned the nickname 'The Reaper' for his devastating fights." +
                        "\nNow, he steps into the underground tournament, fighting not just for money, but for vengeance and the chance to rise above his past!" +
                        "\nWith every match, Julio fights to prove his strength and claim the top spot in the world of dirty boxing!"));
                        System.out.print(GameLogic.reset);
                        GameLogic.pressAnything();
                break;
            case "Raul Villanueva":
                System.out.println(GameLogic.centerText(20, "\n🔥Backstory: Meet Raul \"The Bullseye\" Villanueva, a sharpshooter in the underground boxing scene." +
                        "\nRaised in the rough neighborhoods of Cebu, Raul earned his moniker for his deadly precision and accuracy in the ring." +
                        "\nA former marksman in the military, he brings a calm, calculated approach to his brutal fights, targeting his opponent’s weaknesses." +
                        "\nRaul enters the tournament with one goal: to prove that his focus and discipline can take him to the top, no matter the cost!"));
                        System.out.print(GameLogic.reset);
                        GameLogic.pressAnything();
                break;
            case "Ralfo Salvahez":
                System.out.println(GameLogic.centerText(20, "\n🔥Backstory: Ralfo \"The Ghost\" Salvahez, a feared and infamous fighter in the underground boxing world, is a man with a dark past." +
                        "\nOnce a champion in his prime, Ralfo abandoned his family, leaving his young son behind to pursue glory in the ring." +
                        "\nYears of betrayal and silence have fueled a burning resentment in the child he left behind — now a rising star in the same brutal world." +
                        "\nKnown as \"The Ghost\" for his silent, elusive fighting style, Ralfo’s return to the ring is not just to fight, but to face the consequences of his past." +
                        "\nThe child, now grown, has risen through the ranks, and the ultimate showdown awaits: father versus child, in a fight that will either redeem or destroy them both!"));
                        System.out.print(GameLogic.reset);
                        GameLogic.pressAnything();
                break;
            default:
                System.out.println("No backstory available for this opponent.");
        }
    }
}
