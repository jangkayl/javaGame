package world3;

import world1.GameLogic;
import world1.Player;
import world2.GameLogic2;

public class TrainWithFred {
    private Player player = GameLogic.player;

    public void teachPassiveSkills() {
        GameLogic.clearConsole();
        String prompt = "Coach Fred: \"Alright, " + GameLogic2.player.getName() + ", listen up. In the Champs Arena, it's not just about power. Timing is everything." + 
                        "\nLand three consecutive successful hits, and you'll unlock the ability to activate one of these skills. But choose wisely" + 
                        "\nonce activated, each skill lasts for the next two turns. And don't worry, none of these skills cost mana," + 
                        "\nso you can use them without draining yourself.";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
        
        prompt = "\"Iron Guard - Block all incoming damage for your next turn.\"" + 
                "\n*Best used when you're on the defensive." + 
                "\n\n\"Adrenaline Rush - Boost your crit chance by 25% for your next turn.\"" +
                "\n*Perfect for finishing off your opponent." + 
                "\n\n\"Vital Surge - Regain 20% of your health and stamina.\"" +
                "\n*Use this skill to stay in the fight longer and keep your stamina up for crucial moves.";
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
