package world1.TournamentFight;

import world1.FightLogic;
import GlobalClasses.Player;
import GlobalClasses.StreetFighter;

public class LopezTourna extends FightLogic{
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Quick Jab", "Cross", "Power Punch"};
    
    public LopezTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Lopez";
    }
}