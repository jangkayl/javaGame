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
    private boolean opponent1Dodged = false;
    private boolean opponent2Dodged = false;
    private SkillsRegistry skills = new SkillsRegistry();
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Elbow Strike", "Head Butt", "Low Blow"};
    private String[] opponentNames = {"Manolo Salonga", "Ramon Lagman", "Eddie Dela Cruz", "Victor Magtangs", "Arnold Goggins", "Lando Pitik", "Joaquin Perez"};

    public void betFight() {
        System.out.println();
        GameLogic.clearConsole();
        System.out.print(GameLogic.centerBox("🎲 Welcome to the Underground BetFight Arena 🎲\n", 100));
        System.out.println("\n");
        System.out.println(GameLogic.centerText(50,
                "The dim glow of flickering neon lights illuminates the smoky hall. The crowd's cheers and jeers create a chaotic symphony of excitement.\n" + "[ A shadowy figure approaches, cloaked in mystery, their voice a raspy growl. ]\n" +
                        "Bookie: \"Two champions will clash. Who's your pick? Place your bets and watch the carnage!\"\n"));

        fighter1 = generateRandomOpponent(null);
        fighter2 = generateRandomOpponent(fighter1.getName());

        System.out.print(GameLogic.centerText(50,"Meet the fighters:"));
        System.out.print(GameLogic.centerText(50,"Fighter 1: " + fighter1.getName()));
        world1.Tournament.showOpStats(fighter1);
        System.out.println();
        System.out.print(GameLogic.centerText(50,"Fighter 2: " + fighter2.getName()));
        world1.Tournament.showOpStats(fighter2);
        System.out.println();

        System.out.print(GameLogic.centerText(50,"Place your bet! (1) " + fighter1.getName() + " or (2) " + fighter2.getName() + " | (0) Go Back:"));
        int betChoice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 0, 2);
        if(betChoice == 0) return;
        int betAmount = 0;
        do {
            System.out.print(GameLogic.centerText(50,"\nEnter your bet amount (minimum 100 points) | (0) Go Back:"));
            System.out.print(GameLogic.centerText(50,"Available Points: " + player.getPlayerPoints()));
            betAmount = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 0, Integer.MAX_VALUE);
            if(betAmount == 0) return;
        
            if (betAmount < 100){
                System.out.println(GameLogic.centerBox("Minimum bet is 100 points. Please try again.", 60));
            } else if (betAmount > player.getPlayerPoints()) {
                System.out.println(GameLogic.centerBox("You don't have enough points to place this bet. Please try again.", 80));
            }
        } while (betAmount > player.getPlayerPoints() || betAmount < 100);

        System.out.println(GameLogic.centerText(50,"\nThe fight begins..."));
        GameLogic.pressAnything();

        StreetFighter winner = simulateFight(fighter1, fighter2);

        System.out.print(GameLogic.centerText(50,"The winner is: " + winner.getName()));
        if ((betChoice == 1 && winner == fighter1) || (betChoice == 2 && winner == fighter2)) {
            System.out.print(GameLogic.centerText(50,"🎉 Congratulations! You've doubled your bet " + betAmount + " and earned " + (2 * betAmount) + " points!"));
            GameLogic.player.setPlayerPoints(GameLogic.player.getPlayerPoints() + betAmount);
            System.out.print(GameLogic.centerText(50,"Current Points: " + player.getPlayerPoints()));
        } else {
            System.out.print(GameLogic.centerText(50,"😢 Tough luck! You lost " + betAmount + " points."));
            GameLogic.player.setPlayerPoints(GameLogic.player.getPlayerPoints() - betAmount);
            System.out.print(GameLogic.centerText(50,"Current Points: " + player.getPlayerPoints()));
        }

        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();
    }

    private String getRandomOpponentName(String excludeName) {
        String randomName;
        do {
            randomName = opponentNames[rand.nextInt(opponentNames.length)];
        } while (randomName.equals(excludeName)); // Ensure the name is not the same as excludeName
        return randomName;
    }

    // Method to generate a random StreetFighter opponent
    private StreetFighter generateRandomOpponent(String excludeName) {
        String name = getRandomOpponentName(excludeName); // Get a unique opponent name
        int hp = rand.nextInt(51) + 150; // HP between 150 and 200
        int stamina = rand.nextInt(21) + 80; // Stamina between 80 and 100
        double critChance = 0.3 + (rand.nextDouble() * 0.3); // Crit chance between 0.3 and 0.6
        double critMulti = 2 + (rand.nextDouble() * 1); // Crit multi between 2 and 3
        double dodgeChance = 0.3 + (rand.nextDouble() * 0.2); // Dodge chance between 0.3 and 0.5

        // Create and return a new StreetFighter with the randomized stats
        return new StreetFighter(name, hp, stamina, critChance, critMulti, dodgeChance, 3);
    }


    private StreetFighter simulateFight(StreetFighter fighter1, StreetFighter fighter2) {
        int round = 1;
        int[] fighter1Skills = new int[3], fighter2Skills = new int[3];
        printStats();
        GameLogic.pressAnything();
        while (fighter1.getHp() > 0 && fighter2.getHp() > 0) {
            System.out.println();
            System.out.println(GameLogic.centerText(50, "Round " + round + ":"));
    
            // Fighter 1 selects 3 skills and uses them in sequence
            fighter1Skills = chooseSkill(fighter1);
            // Fighter 2 selects 3 skills and uses them in sequence
            fighter2Skills = chooseSkill(fighter2);

            System.out.println();
            System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
            System.out.print(GameLogic.centerText(30, GameLogic.formatColumns(fighter1.getName() + " selected:", fighter2.getName() + " selected:", 30)));
    
            for(int i = 0; i < 3; i++){
                String opponent1Attack = opponentAttacks[fighter1Skills[i]];
                String opponent2Attack = opponentAttacks[fighter2Skills[i]];
    
                String line =  GameLogic.formatColumns(opponent1Attack, opponent2Attack, 30);
                System.out.print(GameLogic.centerText(30, line));
            }

            System.out.println();
            // Display updated stats after each round
            printFight(fighter1Skills, fighter2Skills);
            printStats();

            if(fighter1.getHp() <= 0 || fighter1.getHp() <= 0) break;
    
            System.out.println();
            GameLogic.pressAnything();
        }

        // Determine the winner
        System.out.println("\n");
        if (fighter1.getHp() > 0) {
            return fighter1;
        } else if (fighter2.getHp() > 0) {
            return fighter2;
        } else {
            return null;
        }
    }

    private int[] chooseSkill(StreetFighter fighter) {
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
    
        return opponentChoices;
    }

    private void printFight(int[] opponent1Choices, int[] opponent2Choices) {
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentAttacks[opponent1Choices[i]], opponentAttacks[opponent2Choices[i]]);
            String fighter1Attack = fighter1.getName() + " throws a " + opponentAttacks[opponent1Choices[i]] + " to " + fighter2.getName();

            if(countered == 1){
                System.out.print(GameLogic.centerText(50, fighter1Attack));
                opponent1SuccessAction(opponent1Choices[i], opponent2Choices[i], false);
                opponent2FailedAction(opponentAttacks[opponent2Choices[i]]);
            } else if(countered == 2){
                System.out.print(GameLogic.centerText(50, fighter1Attack));
                opponent2SuccessAction(opponent2Choices[i], opponent1Choices[i], false);
                opponent1FailedAction(opponentAttacks[opponent1Choices[i]]);
            } else {
                System.out.print(GameLogic.centerText(50, fighter1Attack));
                String opponentAttack = fighter2.getName() + " draws " + fighter1.getName() + " with " + opponentAttacks[opponent2Choices[i]];
                System.out.print(GameLogic.centerText(50, opponentAttack));
                drawAction(opponent1Choices[i], opponent2Choices[i]);
            }
            if(fighter1.getHp() <= 0 || fighter2.getHp() <= 0){
                return;
            }
            System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        }
    }

    private int isCounter(String opponentMove, String playerMove) {
        if(skills.getSkillByName(opponentMove).isEffectiveAgainst(playerMove))
            return 2;
        if(skills.getSkillByName(opponentMove).counters(playerMove))
            return 1;
        return 0;
    }

    private void opponent1SuccessAction(int choice, int opponentChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < fighter1.getCritChance() && choice != 2 && !isDraw && !opponent2Dodged) {
            fighter1.setDamageSetter(fighter1.getCritMultiplier());
            System.out.print(GameLogic.centerText(40,fighter1.getName() + "'s " + opponentAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
        }

        if (dodgeChance < fighter1.getDodgeChance() && opponentChoice != 2 && !isDraw) {
            opponent1Dodged = true;
        }

        if (opponent2Dodged) {
            fighter1.setDamageSetter(0);
            System.out.print(GameLogic.centerText(40, fighter2.getName() + " dodges " + fighter1.getName() + "'s punch!"));
        }

        fighter1.useSkill(opponentAttacks[choice], fighter2);

        fighter1.setDamageSetter(1);
        opponent2Dodged = false;
    }

    private void opponent2FailedAction(String attack) {
        fighter2.setStamina(fighter2.getStamina() - skills.getSkillByName(attack).getStaminaCost());
    }
    
    private void opponent2SuccessAction(int choice, int opponentChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();
        
        if (critChance < fighter2.getCritChance() && choice != 2 && !isDraw) {
            fighter2.setDamageSetter(fighter2.getCritMultiplier());
            System.out.print(GameLogic.centerText(40,fighter2.getName() + "'s " + opponentAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
        }
        
        if (dodgeChance < fighter2.getDodgeChance() && choice != 2 && !isDraw) {
            opponent2Dodged = true;
        }
        
        if (opponent1Dodged) {
            fighter2.setDamageSetter(0);
            System.out.print(GameLogic.centerText(40, fighter1.getName() + " dodges " + fighter2.getName() + "'s punch!"));
        }
        
        opponentPerformAction(opponentAttacks[choice]);
        
        fighter2.setDamageSetter(1);
        opponent1Dodged = false;
    }

    private void opponent1FailedAction(String attack) {
        fighter1.setStamina(fighter1.getStamina() - skills.getSkillByName(attack).getStaminaCost());
    }
    
    private void opponentPerformAction(String attack) {
        fighter2.useSkill(attack, fighter1);
        fighter2.setDamageSetter(1);
        opponent1Dodged = false;
    }

    private void drawAction(int choice, int opponentChoice) {
        fighter1.setDamageSetter(0.5);
        opponent1SuccessAction(choice, opponentChoice, false);
        fighter1.setDamageSetter(1);

        fighter2.setDamageSetter(0.5);
        opponent2SuccessAction(opponentChoice, choice, false);
        fighter2.setDamageSetter(1);
    }
    
    private void printStats(){
        System.out.println();
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("*"+ fighter1.getName() +"*" , "*"+ fighter2.getName()+"*", 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("HP       " + fighter1.getHp() + "/" + fighter1.getMaxHp(), "HP       " + fighter2.getHp() + "/" + fighter2.getMaxHp(), 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("Stamina   " + fighter1.getStamina() + "/" + fighter1.getMaxStamina(), "Stamina   " + fighter2.getStamina() + "/" + fighter2.getMaxStamina(), 30)));
        System.out.println();
    }
    
}
