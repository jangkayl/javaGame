package world2.TournaUnderFight;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparFightLogic;
import world2.TournamentUnderground;

public class SalvahezTourna extends SparFightLogic{
    TournamentUnderground tournamentUnderground = new TournamentUnderground();
    public static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Quick Jab", "Cross", "Power Punch", "Elbow Strike", "Head Butt", "Low Blow"};
    
    public SalvahezTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Lopez";
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