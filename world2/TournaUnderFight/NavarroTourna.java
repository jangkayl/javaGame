package world2.TournaUnderFight;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparFightLogic;
import world2.TournamentUnderground;

public class NavarroTourna extends SparFightLogic{
    TournamentUnderground tournamentUnderground = new TournamentUnderground();
    public static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Elbow Strike", "Head Butt", "Low Blow"};
    
    public NavarroTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Navarro";
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

