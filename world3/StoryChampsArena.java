package world3;

import world1.GameLogic;

public class StoryChampsArena {
    public static void printChampsArena() {
        System.out.println();
        System.out.println("Welcome to the Champs Arena, the grand stage where legends are forged and the spotlight never dims.");
        System.out.println("No longer fighting in the shadows, you now face the best of the best, where skill, strategy, and determination reign supreme.");
        System.out.println();
        System.out.println("Under Coach Fred's expert guidance, you'll push your limits, refining your abilities and unlocking advanced techniques.");
        System.out.println("This is where you'll learn unique passive skills that can shift the momentum of any fight, rewarding precision, timing, and mastery.");
        System.out.println();
        System.out.println("Every match brings new challenges, but also the opportunity to prove your worth to the roaring crowds.");
        System.out.println("Victory here isn't just a title - it's your ticket to glory and the ultimate recognition as a champion.");
        System.out.println();
        System.out.println("Prepare yourself, step into the ring, and show the world what you're made of!");
        System.out.println();
    }

    public static void printIntroduction(String name) {
        GameLogic.clearConsole();
        String prompt = "Coach Fred: \"Alright, kid. Raw power and flashy moves might have worked against amateurs, but this is a whole different game." + 
                        "\nIf you want to succeed in the Champs Arena, brute strength isn't enough. It's about strategy, timing," + 
                        "\nand knowing when to unleash your hidden potential.\"";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
        prompt = "Coach Fred: \"This is where passive skills come into play. These aren't just techniques, they're the key to dominating the ring." +
                "\nBut don't get ahead of yourself. Mastering these takes focus and discipline. Train hard, and I'll make sure you're ready.\"";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();

        GameLogic.pressAnything();

        GameLogic.clearConsole();
        prompt = "Coach Fred: \"Now, let's get started, " + name + ". I'll shape you into a fighter who not only stands tall but dominates the ring.\"";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
        GameLogic.pressAnything();
        // champsArenaPrompt(name);
    }

    
}
