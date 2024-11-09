package world1.TournamentFight;

import world1.FightLogic;
import world1.Player;
import world1.StreetFighter;

public class TettehTourna extends FightLogic{
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross"};
    
    public TettehTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Tetteh";
    }
}