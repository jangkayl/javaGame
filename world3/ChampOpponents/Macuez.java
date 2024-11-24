package world3.ChampOpponents;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world3.ChampTournament;
import world3.SparFightLogic;

public class Macuez extends SparFightLogic{
    ChampTournament champTournament = new ChampTournament();
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook", "Flow State", "Adrenaline Rush", "Sixth Sense"};
    
    public Macuez(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Macuez";
    }

    @Override
    protected void handleWin() {
        getPlayer().setIsLose(false);
        if(getPlayerProgress().getPlayerWins() != 3){
            System.out.println();
            System.out.print(GameLogic.centerBox("Congratulations! You've won the match!", 50));
        }
        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        if(getPlayerProgress().getPlayerWins() != 3){
            getPlayerProgress().setPlayerWins(getPlayerProgress().getPlayerWins() + 1);
        }
        champTournament.printStanding();
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
        champTournament.printStanding();
        GameLogic.gameData.saveGame();
    }

}

