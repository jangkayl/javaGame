package world2.TournaUnderFight;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparFightLogic;
import world2.TournamentUnderground;

public class RaulTourna extends SparFightLogic{
    TournamentUnderground tournamentUnderground = new TournamentUnderground();
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook", "Elbow Strike", "Head Butt", "Low Blow"};
    
    public RaulTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Raul";
    }

    @Override
    protected void handleWin() {
        getPlayer().setIsLose(false);
        if(getPlayerProgress().getPlayerWins() != 3){
            System.out.println(); 
            GameLogic.printSeparator(40);
            System.out.println(); 
            System.out.println("Congratulations! You've won the match!");
        }
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
        getPlayer().setIsLose(true);
        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        if (getPlayerProgress().getOpponentWins() != 3) {
            getPlayerProgress().setOpponentWins(getPlayerProgress().getOpponentWins() + 1);
        }
        tournamentUnderground.printStanding();
        GameLogic.gameData.saveGame();
    }

}