package world2.TournaUnderFight;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.TournamentUnderground;
import world2.SparringLogic.SparFightLogic;

public class RaulTourna extends SparFightLogic{
    TournamentUnderground tournamentUnderground = new TournamentUnderground();
    public static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook", "Elbow Strike", "Head Butt", "Low Blow"};
    
    public RaulTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Raul";
    }

    @Override
    protected void handleWin() {
        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        if(getPlayerProgress().getPlayerWins() != 3){
            getPlayerProgress().setPlayerWins(getPlayerProgress().getPlayerWins() + 1);
        }
        tournamentUnderground.printStanding();
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