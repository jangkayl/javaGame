package world1.TrainInGym;

import world1.*;

import java.util.Random;

public class CarlitoUrbanGym extends PlayerVsOpponent{
    private static Random rand = new Random();
    private static PlayerProgress playerProgress = GameLogic.playerProgress;
    private static String[][] attack = {{"Jab", "Damage: 10 | Stamina: -5"}, 
                                {"Hook", "Damage: 15 | Stamina: -7"}, 
                                {"Block", "Stamina: +5"}, 
                                {"Uppercut", "Damage: 20 | Stamina: -10"}};
    private static StreetFighter opponent = new StreetFighter("Carlito Cortez", 120, 80, 0.2, 2, .20, 1);

    public CarlitoUrbanGym(Player player){
        super(player, opponent);
        String[] attacks = new String[4];
        for(int i = 0; i < 4; i++){
            attacks[i] = attack[i][0];
        }
        setOpponentAttacks(attacks);
    }

    public String getOpponentName(){
        return opponent.getName();
    }

    public String[][] getAttacks(){
        return attack;
    }

    public void fightLoop() {
        GameLogic.clearConsole();
        System.out.println(GameLogic.centerBox("Round " + playerProgress.getRound(), 40));
        System.out.println(GameLogic.centerBox("You are fighting " + opponent.getName() + "!", 40));
        getPlayer().setOpponent(opponent);
        printStats();
        while (getPlayer().getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            if (getPlayer().getHp() <= 0) {
                System.out.println();
                System.out.println(GameLogic.centerBox(getPlayer().getName() + " is knocked out! " + opponent.getName() + " wins!", 60));
                getPlayer().setIsLose(true);
                playerProgress.setRound(playerProgress.getRound() + 1);
                getPlayer().setHp(getPlayer().getMaxHp());
                getPlayer().setStamina(getPlayer().getMaxStamina());
                opponent.setHp(opponent.getMaxHp());
                opponent.setStamina(opponent.getMaxStamina());
                GameLogic.pressAnything();
                UrbanStory.urbanTrainingLose(getPlayer().getName(), opponent.getName());
                return;
            } else if(opponent.getHp() <= 0){
                System.out.println();
                System.out.println(GameLogic.centerBox(opponent.getName() + " is knocked out! " + getPlayer().getName() + " wins!", 60));
                getPlayer().setIsLose(false);
                winnerReward();
                getPlayer().setHp(getPlayer().getMaxHp());
                getPlayer().setStamina(getPlayer().getMaxStamina());
                playerProgress.setRound(1);  
                playerProgress.setShopStage(2);  
                UrbanStory.urbanTraining8(getPlayer().getName());    
                return;
            }
        }
    }

    private void printStats(){
        System.out.println();
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("*"+ getPlayer().getName() +"*" , "*"+ opponent.getName()+"*", 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("HP       " + getPlayer().getHp() + "/" + getPlayer().getMaxHp(), "HP       " + opponent.getHp() + "/" + opponent.getMaxHp(), 30)));
        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("Stamina   " + getPlayer().getStamina() + "/" + getPlayer().getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30)));
        System.out.println();
    }
    
    private void selectAttack() {
        System.out.println();
        System.out.print(GameLogic.centerText(30,"You're the first one to attack!"));
        
        for (int i = 0; i < attack.length; i++) {
            String attackInfo = (i + 1) + ") " + attack[i][0] + " - " + attack[i][1];
            System.out.print(GameLogic.centerText(40, attackInfo));
        }
        
        System.out.print(GameLogic.centerText(30,"\nSelect 3 combos:"));
        System.out.print("-> ");
        String input = GameLogic.scan.nextLine();
    
        while (isValidCombo(input, getPlayer().getStamina()) != 0) {
            if(isValidCombo(input, getPlayer().getStamina()) == 1){
                System.out.println(GameLogic.centerBox("Please enter a valid combo (e.g., 123):", 60));
            } else if(isValidCombo(input, getPlayer().getStamina()) == 2) {
                String message = getPlayer().getName() + " doesn't have enough stamina for this combo!\n" +
                        "You may use 3 Blocks as your combo to regain stamina";
                System.out.println(GameLogic.centerBox(message, 60));
            }
            input = GameLogic.scan.nextLine();
        }

        int[] choices = new int[3];
        int[] opponentChoices = new int[3];

        for (int i = 0; i < 3; i++) {
            choices[i] = Character.getNumericValue(input.charAt(i) - 1);
            opponentChoices[i] = rand.nextInt(4);
        }

        opponentValid(opponentChoices);

        System.out.println();
        System.out.println(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));

        System.out.print(GameLogic.centerText(30, GameLogic.formatColumns("You've selected:", "Opponent selected:", 30)));

        for(int i = 0; i < 3; i++){
            String playerAttack = attack[choices[i]][0];
            String opponentAttack = attack[opponentChoices[i]][0];

            String line =  GameLogic.formatColumns(playerAttack, opponentAttack, 30);
            System.out.print(GameLogic.centerText(30, line));
        }

        System.out.println();
        printFight(choices, opponentChoices);
    }

    private int isValidCombo(String input, int stamina) {
        if (input.length() != 3) {
            return 1;
        }

        int tempStamina = stamina;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c) || c < '1' || c > '4') {
                return 1;
            }
            
            int move = Character.getNumericValue(c);
            int staminaCost;

            staminaCost = getSkills().getSkillByName(attack[move - 1][0]).getStaminaCost();
            
            if (tempStamina - staminaCost < 0) {
                return 2;
            }
            tempStamina -= staminaCost;
        }
        return 0;
    }

    private void opponentValid(int[] opponentChoice) {
        int tempStamina = opponent.getStamina();
        
        for (int i = 0; i < opponentChoice.length; i++) {
            int staminaCost = 0;
    
            boolean validChoice = false;
            while (!validChoice) {
                staminaCost = getSkills().getSkillByName(attack[opponentChoice[i]][0]).getStaminaCost();
    
                if (tempStamina - staminaCost >= 0) {
                    validChoice = true;
                } else {
                    if(rand.nextDouble() > 0.3)
                        opponentChoice[i] = rand.nextInt(4);
                    else 
                        opponentChoice[i] = 2;
                }
            }
    
            tempStamina -= staminaCost;
        }
    }

    private void printFight(int[] choices, int[] opponentChoices){
        System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        for(int i = 0; i < 3; i++){
            int countered = isCounter(attack[opponentChoices[i]][0], attack[choices[i]][0]);
            String playerAttack = getPlayer().getName() + " throws a " + attack[choices[i]][0] + " to " + opponent.getName();
            
            if(countered == 1){
                System.out.print(GameLogic.centerText(50, playerAttack));
                playerSuccessAction(choices[i], opponentChoices[i], false);
                opponentFailedAction(attack[opponentChoices[i]][0]);
            } else if(countered == 2){
                System.out.print(GameLogic.centerText(50, playerAttack));
                opponentSuccessAction(opponentChoices[i], choices[i], false);
                playerFailedAction(attack[choices[i]][0]);
            } else {
                System.out.print(GameLogic.centerText(50, playerAttack));
                String opponentAttack = opponent.getName() + " draws " + getPlayer().getName() + " with " + attack[opponentChoices[i]][0];
                System.out.print(GameLogic.centerText(50, opponentAttack));
                drawAction(choices[i], opponentChoices[i]);
            }
            if(getPlayer().getHp() <= 0 || opponent.getHp() <= 0){
                return;
            }
            System.out.print(GameLogic.centerText(50, GameLogic.printCenteredSeparator(50)));
        }
    }

    private void winnerReward() {
        getPlayer().setPlayerPoints(getPlayer().getPlayerPoints() + 100);
        String rewardMessage =
                "Congratulations! You've won the match!\n" +
                        "Fred is giving you 100 points as a starter pack to get you started.\n" +
                        "You now have " + getPlayer().getPlayerPoints() + " points.\n" +
                        "You can now visit the shop and use your points to buy items.";
        System.out.println(GameLogic.centerBox(rewardMessage, 90));
        GameLogic.pressAnything();
    }
}