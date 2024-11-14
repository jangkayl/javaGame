package world2.BetFight;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;

public class BetFight {
    private StreetFighter fighter1;
    private Player player = GameLogic.player;
    private StreetFighter fighter2;
    private Random rand = new Random();
    private static String[] opponent1Attacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Elbow Strike", "Head Butt", "Low Blow"};
    private static String[] opponent2Attacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook", "Elbow Strike", "Head Butt", "Low Blow"};

    public void betFight() {
        System.out.println();
        GameLogic.clearConsole();
        System.out.println("🎲 Welcome to the Underground BetFight Arena 🎲");
        GameLogic.printSeparator(40);
        System.out.println("The dim glow of flickering neon lights illuminates the smoky hall. The crowd's cheers and jeers create a chaotic symphony of excitement.");
        System.out.println();
        GameLogic.printSeparator(40);
        System.out.println("[ A shadowy figure approaches, cloaked in mystery, their voice a raspy growl. ]");
        System.out.println("Bookie: \"Two champions will clash. Who's your pick? Place your bets and watch the carnage!\"");
        System.out.println();

        fighter1 = generateRandomOpponent("Joaquin Perez");
        fighter2 = generateRandomOpponent("Lando Pitik");

        System.out.println("Meet the fighters:");
        System.out.println("Fighter 1: " + fighter1.getName());
        world1.Tournament.showOpStats(fighter1);
        System.out.println();
        System.out.println("Fighter 2: " + fighter2.getName());
        world1.Tournament.showOpStats(fighter2);
        System.out.println();

        System.out.println("Place your bet! (1) Joaquin Perez or (2) Lando Pitik:");
        int betChoice = GameLogic.readInt("-> ", 1, 2);

        System.out.println("Enter your bet amount (minimum 100 points):");
        System.out.println("Available Points: " + player.getPlayerPoints());
        int betAmount = GameLogic.readInt("-> ", 100, Integer.MAX_VALUE);

        System.out.println("The fight begins...");
        GameLogic.pressAnything();

        StreetFighter winner = simulateFight(fighter1, fighter2);

        System.out.println("The winner is: " + winner.getName());
        if ((betChoice == 1 && winner == fighter1) || (betChoice == 2 && winner == fighter2)) {
            System.out.println("🎉 Congratulations! You've doubled your bet and earned " + (2 * betAmount) + " points!");
        System.out.println("Current Points: " + player.getPlayerPoints());
            GameLogic.player.setPlayerPoints(GameLogic.player.getPlayerPoints() + betAmount);
        } else {
            System.out.println("😢 Tough luck! You lost " + betAmount + " points.");
            GameLogic.player.setPlayerPoints(GameLogic.player.getPlayerPoints() - betAmount);
        }

        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();
    }

    private StreetFighter generateRandomOpponent(String name) {
        int hp = rand.nextInt(51) + 150; // HP between 150 and 200
        int stamina = rand.nextInt(21) + 80; // Stamina between 80 and 100
        double critChance = 0.3 + (rand.nextDouble() * 0.3); // Crit chance between 0.3 and 0.6
        double critMulti = 2 + (rand.nextDouble() * 1); // Crit multi between 2 and 3
        double dodgeChance = 0.3 + (rand.nextDouble() * 0.2); // Dodge chance between 0.3 and 0.5

        return new StreetFighter(name, hp, stamina, critChance, critMulti, dodgeChance, 3);
    }

    private StreetFighter simulateFight(StreetFighter fighter1, StreetFighter fighter2) {
        int round = 1;
        while (fighter1.getHp() > 0 && fighter2.getHp() > 0) {
            System.out.println("Round " + round + ":");
    
            // Fighter 1 attacks Fighter 2 using a skill
            String skill1 = chooseSkill(fighter1); // Choose skill for Fighter 1
            fighter1.useSkill(skill1, fighter2);
            if (fighter2.getHp() <= 0) {
                System.out.println(fighter2.getName() + " is knocked out!");
                displayStats(fighter1, fighter2);
                return fighter1;
            }
    
            // Fighter 2 attacks Fighter 1 using a skill
            String skill2 = chooseSkill(fighter2); // Choose skill for Fighter 2
            fighter2.useSkill(skill2, fighter1);
            if (fighter1.getHp() <= 0) {
                System.out.println(fighter1.getName() + " is knocked out!");
                displayStats(fighter1, fighter2);
                return fighter2;
            }
    
            // Display updated stats after each round
            displayStats(fighter1, fighter2);
    
            round++;
            System.out.println();
            GameLogic.pressAnything();
        }
        return null; // Should never reach here
    }

    private String chooseSkill(StreetFighter streetFighter) {
        // Randomly select an attack from the opponentAttacks array
        return opponent1Attacks[rand.nextInt(opponent1Attacks.length)];
    }

    private void displayStats(StreetFighter fighter1, StreetFighter fighter2) {
        System.out.println();
        System.out.println("Current Stats:");
        System.out.println(fighter1.getName() + " - HP: " + fighter1.getHp() + ", Stamina: " + fighter1.getStamina());
        System.out.println(fighter2.getName() + " - HP: " + fighter2.getHp() + ", Stamina: " + fighter2.getStamina());
        System.out.println();
    }
    
}
