package world1.FightingLogic;

import java.util.Random;

import world1.Player;
import world1.StreetFighter;

public abstract class FighterAction {
    protected static Random rand = new Random();
    protected static StreetFighter opponent;
    protected static Player player;
    protected static boolean playerDodged = false;
    protected static boolean opponentDodged = false;
    
    public static void setPlayerOpponent(Player player) {
        FighterAction.player = player;
    }

    public static void setOpponent(StreetFighter opponent) {
        FighterAction.opponent = opponent;
    }
}
