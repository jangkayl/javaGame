package world1.TournamentFight;

import world1.FightLogic;
import world1.Player;
import world1.StreetFighter;

public class RamirezTourna extends FightLogic{
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook"};
    
    public RamirezTourna(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Ramirez";
    }
}