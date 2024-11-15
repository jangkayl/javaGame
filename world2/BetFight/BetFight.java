package world2.BetFight;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world1.Skill.SkillsRegistry;

public class BetFight {
    private StreetFighter fighter1;
    private Player player = GameLogic.player;
    private StreetFighter fighter2;
    private Random rand = new Random();
    private SkillsRegistry skills = new SkillsRegistry();
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Elbow Strike", "Head Butt", "Low Blow"};
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
    
            // Fighter 1 selects 3 skills and uses them in sequence
            String[] fighter1Skills = chooseSkill(fighter1);
            for (String skill : fighter1Skills) {
                fighter1.useSkill(skill, fighter2);
                if (fighter2.getHp() <= 0) {
                    System.out.println(fighter2.getName() + " is knocked out!");
                    displayStats(fighter1, fighter2);
                    return fighter1;
                }
            }
    
            // Fighter 2 selects 3 skills and uses them in sequence
            String[] fighter2Skills = chooseSkill(fighter2);
            for (String skill : fighter2Skills) {
                System.out.println(fighter2.getName() + " uses " + skill + "!");
                fighter2.useSkill(skill, fighter1);
                if (fighter1.getHp() <= 0) {
                    System.out.println(fighter1.getName() + " is knocked out!");
                    displayStats(fighter1, fighter2);
                    return fighter2;
                }
            }
    
            // Display updated stats after each round
            displayStats(fighter1, fighter2);
    
            round++;
            System.out.println();
            GameLogic.pressAnything();
        }

        System.out.println();
        printFight(choices, opponentChoices);
        return null; // Should never reach here
    }
    

    private String[] chooseSkill(StreetFighter fighter) {
        int[] opponentChoices = new int[3];  
        int tempStamina = fighter.getStamina(); 
    
        for (int i = 0; i < 3; i++) {
            boolean validChoice = false;
    
            while (!validChoice) {
                int skillIndex;
                if (rand.nextInt(10) < 5) {  // 50% chance for lower-cost skills
                    skillIndex = rand.nextInt(4);  // Skills with indices 0 to 3
                } else {  // 50% chance for higher-cost skills
                    skillIndex = 4 + rand.nextInt(6);  // Skills with indices 4 to 9
                }
    
                int staminaCost = skills.getSkillByName(opponentAttacks[skillIndex]).getStaminaCost();
                if (tempStamina - staminaCost >= 0) {
                    opponentChoices[i] = skillIndex;
                    tempStamina -= staminaCost;
                    validChoice = true;
                } else {
                    // If skill is unaffordable, retry with a 30% chance to pick a fallback skill
                    if (rand.nextDouble() > 0.3) {
                        skillIndex = 2;  // Default to a lower-cost skill if stamina is too low
                    }
                }
            }
        }
    
        // Convert the indices of chosen skills to skill names for easier usage in battle
        String[] chosenSkills = new String[3];
        for (int i = 0; i < 3; i++) {
            chosenSkills[i] = opponentAttacks[opponentChoices[i]];
        }
        
        return chosenSkills;
    }

    private void printFight(int[] opponent1Choices, int[] opponent2Choices) {
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentAttacks[opponentChoices[i]], playerAttacks[choices[i]]);
            String playerAttack = getPlayer().getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName();

            if(countered == 1){
                System.out.print(GameLogic.centerText(50, playerAttack));
                playerSuccessAction(choices[i], opponentChoices[i], false);
                opponentFailedAction(opponentAttacks[opponentChoices[i]]);
            } else if(countered == 2){
                System.out.print(GameLogic.centerText(50, playerAttack));
                opponentSuccessAction(opponentChoices[i], choices[i], false);
                playerFailedAction(playerAttacks[choices[i]]);
            } else {
                System.out.print(GameLogic.centerText(50, playerAttack));
                String opponentAttack = opponent.getName() + " draws " + getPlayer().getName() + " with " + opponentAttacks[opponentChoices[i]];
                System.out.print(GameLogic.centerText(50, opponentAttack));
                drawAction(choices[i], opponentChoices[i]);
            }
            if(getPlayer().getHp() <= 0 || getOpponent().getHp() <= 0){
                return;
            }
            System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        }
    }

    private void displayStats(StreetFighter fighter1, StreetFighter fighter2) {
        System.out.println();
        System.out.println("Current Stats:");
        System.out.println(fighter1.getName() + " - HP: " + fighter1.getHp() + ", Stamina: " + fighter1.getStamina());
        System.out.println(fighter2.getName() + " - HP: " + fighter2.getHp() + ", Stamina: " + fighter2.getStamina());
        System.out.println();
    }
    
}
