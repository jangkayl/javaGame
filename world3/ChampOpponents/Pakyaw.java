package world3.ChampOpponents;

import world1.GameLogic;
import GlobalClasses.Player;
import GlobalClasses.StreetFighter;
import world3.ChampTournament;
import world3.SparFightLogic;

public class Pakyaw extends SparFightLogic{
    ChampTournament champTournament = new ChampTournament();
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Quick Jab", "Cross", "Power Punch", "Flow State", "Adrenaline Rush", "Sixth Sense"};
    
    public Pakyaw(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Pakyaw";
    }

    @Override
    protected void handleWin() {
        getPlayer().setIsLose(false);
        if(getPlayerProgress().getPlayerWins() != 3){
            System.out.println();
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerBox("Congratulations! You've won the match!", 50));
            System.out.print(GameLogic.reset);
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

