package world3;

import world1.GameLogic;
import world1.Player;
import world2.GameLogic2;

public class TrainWithFred {
    private Player player = GameLogic2.player;

    public void teachPassiveSkills() {
        GameLogic.clearConsole();
        String prompt = "Coach Fred: \"Alright, " + player.getName() + ", listen up. In the Champs Arena, it's not just about power—timing is everything.\n" +
                "Land 3 consecutive successful hits, blocks, or survive with 20% of your max HP, and you'll unlock the ability to activate one of these skills.\n" +
                "Choose wisely—each skill lasts 2-3 turns. The best part? They don't cost mana, so it's all about your strategy and heart.\"";
        System.out.print(GameLogic.centerBox(prompt, 150));
        System.out.println();
        prompt = "\"Flow State - 100% Dodge Chance, blocks all damage next 3 turns.\"" + 
                "\n*Best used when you're on defensive." + 
                "\n\n\"Adrenaline Rush - Boost your Crit Chance by 100% next 3 turns.\"" +
                "\n*Perfect for finishing off your opponent." + 
                "\n\n\"Sixth Sense - Reveals the opponent's next 3 moves for next 2 turns.\"" +
                "\n*Plan ahead and conserve stamina.";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
        
        prompt = "Coach Fred: \"You can only activate one of these skills at a time, so choose wisely and make those three hits count." + 
                "\nTiming is everything in the ring.\"" + 
                "\n\n( You nod, understanding that your next few attacks will determine the outcome of the match. )";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
        GameLogic.pressAnything();

        GameLogic.clearConsole();
        prompt = "Coach Fred: \"Alright, you've learned the skills. If you want to test them out," + 
                        "\nhead to the sparring ring. My fighters will be waiting to help you practice." + 
                        "\nRemember, timing and strategy are everything. Show me what you've got!\"";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();

        prompt = "( You nod and make your way to the training ground, determined to master your new abilities. )";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();

        player.setStage(14);
        GameLogic.pressAnything();
    }
}
