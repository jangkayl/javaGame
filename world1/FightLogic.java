package world1;

import java.util.Random;

public abstract class FightLogic {
    protected static Random rand = new Random();
    protected static PlayerProgress playerProgress = GameLogic.playerProgress;
    protected static Player player;
    protected static StreetFighter opponent;
    protected static Tournament tournament = new Tournament();

    public FightLogic(Player player) {
        FightLogic.player = player;
    }

    public abstract String getOpponentName();

    public void setOpponent(StreetFighter opponent) {
        FightLogic.opponent = opponent;
    }

    public void fightLoop() {
        player.setStage(3);
        GameLogic.gameData.saveGame();
        GameLogic.clearConsole();
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("Round " + playerProgress.getRound(), 40));
        GameLogic.printSeparator(40);
        System.out.println(GameLogic.centerText("You are fighting " + getOpponentName() + "!", 40));
        System.out.println();
        GameLogic.printSeparator(40);
        player.setOpponent(opponent);
        printStats();
        while (player.getHp() > 0 && opponent.getHp() > 0) {
            selectAttack();
            printStats();
            checkForWin();
        }
        GameLogic.pressAnything();
    }

    protected void checkForWin() {
        if (player.getHp() <= 0) {
            System.out.println("\n" + player.getName() + " is knocked out! " + getOpponentName() + " wins!");
            handleLoss();
        } else if (opponent.getHp() <= 0) {
            System.out.println("\n" + getOpponentName() + " is knocked out! " + player.getName() + " wins!");
            handleWin();
        }
    }

    protected void handleWin() {
        player.setIsLose(false);
        winnerReward();
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if (playerProgress.getPlayerWins() != 3) {
            playerProgress.setPlayerWins(playerProgress.getPlayerWins() + 1);
        } else {
            player.setStage(4);
        }
        tournament.printStanding();
        GameLogic.gameData.saveGame();
    }

    protected void handleLoss() {
        player.setIsLose(true);
        resetFighterStats();
        playerProgress.setRound(playerProgress.getRound() + 1);
        if (playerProgress.getOpponentWins() != 3) {
            playerProgress.setOpponentWins(playerProgress.getOpponentWins() + 1);
        }
        tournament.printStanding();
        GameLogic.gameData.saveGame();
    }

    private void resetFighterStats() {
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
    }

    protected abstract void winnerReward();

    protected void printStats() {
        System.out.println();
        System.out.println(GameLogic.formatColumns(player.getName(), opponent.getName(), 30));
        System.out.println(GameLogic.formatColumns("HP " + player.getHp() + "/" + player.getMaxHp(), "HP " + opponent.getHp() + "/" + opponent.getMaxHp(), 30));
        System.out.println(GameLogic.formatColumns("Stamina " + player.getStamina() + "/" + player.getMaxStamina(), "Stamina " + opponent.getStamina() + "/" + opponent.getMaxStamina(), 30));
        System.out.println();
    }

    protected abstract void selectAttack();

    protected abstract void printFight(int[] choices, int[] opponentChoices);

    protected abstract int isCounter(int opponentMove, int playerMove);
}
