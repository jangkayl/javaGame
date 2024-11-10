package world2.SparringOpponents;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparringLogic.SparFightLogic;

public class PerezSparring extends SparFightLogic{
    public static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Elbow Strike", "Head Butt", "Low Blow"};

    public PerezSparring(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Perez";
    }

    @Override
    protected void handleWin() {
        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        winnerReward();
        GameLogic.gameData.saveGame();
    }

    @Override
    protected void handleLoss() {
        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        GameLogic.printSeparator(40);
        System.out.println(); 
        System.out.println("You have been defeated!");
        System.out.println("You lost 150 points");
        getPlayer().setPlayerPoints(getPlayer().getPlayerPoints() - 150);
        System.out.println("You now have " + getPlayer().getPlayerPoints() + " points.");
        System.out.println();
        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();
    }
}

