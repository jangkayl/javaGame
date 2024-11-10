package world2.SparringOpponents;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparringLogic.SparFightLogic;

public class PitikSparring extends SparFightLogic{
    public static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook", "Elbow Strike", "Head Butt", "Low Blow"};
    
    public PitikSparring(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Pitik";
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