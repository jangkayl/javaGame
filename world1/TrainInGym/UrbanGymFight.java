package world1.TrainInGym;

import java.util.Random;
import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;

public abstract class UrbanGymFight {
    protected Random rand = new Random();
    protected int[] opponentChoices = new int[3];
    protected PlayerProgress playerProgress = GameLogic.playerProgress;
    protected Player player;
    protected StreetFighter opponent;

    public abstract void setOpponent(StreetFighter opponent);
    protected abstract void selectAttack();
    protected abstract void winnerReward();

    protected String[][] attackOption = {
        {"Jab", "Damage: 10 | Stamina: -5      "},
        {"Hook", "Damage: 15 | Stamina: -7     "},
        {"Block", "Stamina: +5                 "},
        {"Uppercut", "Damage: 20 | Stamina: -10"},
        {"The Body Breaker", ""}
    };

    protected String[][] comboOption = {
        {"Lead Body Shot", "Damage: 15 | Stamina: -7     "},
        {"Cross to the Ribs", "Damage: 20 | Stamina: -9  "},
        {"Finishing Uppercut", "Damage: 25 | Stamina: -14"}
    };

    public void fightLoop() {
        player.setStage(2);
        GameLogic.gameData.saveGame();
        GameLogic.clearConsole();
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("Round " + playerProgress.getRound(), 40));
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("You are fighting " + opponent.getName() + "!", 40));
        System.out.println();
        GameLogic.printSeparator(40);
        player.setOpponent(opponent);
        printStats();

        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            checkFightOutcome();
        }
    }

    protected void checkFightOutcome() {
        if (player.getHp() <= 0) {
            System.out.println();
            System.out.println(player.getName() + " is knocked out! " + opponent.getName() + " wins!");
            player.setIsLose(true);
            playerProgress.setRound(playerProgress.getRound() + 1);
            resetHealthAndStamina();
            GameLogic.pressAnything();
        } else if (opponent.getHp() <= 0) {
            System.out.println();
            System.out.println(opponent.getName() + " is knocked out! " + player.getName() + " wins!");
            player.setIsLose(false);
            winnerReward();
            resetHealthAndStamina();
            playerProgress.setRound(1);
            playerProgress.setShopStage(3);
            player.setStage(3);
            GameLogic.gameData.saveGame();
        }
    }

    protected void resetHealthAndStamina() {
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
    }

    protected void printStats() {
        System.out.println();
        String prompt = GameLogic.formatColumns("*"+ player.getName() +"*" , "*"+ opponent.getName()+"*", 30)
                        + "\n" + GameLogic.formatColumns("HP       " + player.getHp() + "/" + player.getMaxHp(), "HP       " + opponent.getHp() + "/" + opponent.getMaxHp(), 30)
                         + "\n" + GameLogic.formatColumns("Stamina   " + player.getStamina() + "/" + player.getMaxStamina(), "Stamina   " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30);
        System.out.print(GameLogic.centerBox(prompt, 55));
        System.out.println("\n");
    }
}
