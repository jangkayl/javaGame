package world1.FightingLogic;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world1.TournamentFight.LopezTourna;

public class VsLopez extends PlayerVsOpponent{
    static Player player = GameLogic.player;

    public VsLopez(Player play, StreetFighter opponent){
        super(player, opponent);
    }

    @Override
    protected String[] getOpponentAttacks() {
        return LopezTourna.opponentAttacks;
    }

    @Override
    protected void opponentPerformAction(int choice) {
        switch (choice) {
            case 0:
                if (opponent.hasEnoughStamina(5)) opponent.jab();
                break;
            case 1:
                if (opponent.hasEnoughStamina(7)) opponent.hook();
                break;
            case 2:
                opponent.block();
                break;
            case 3:
                if (opponent.hasEnoughStamina(10)) opponent.uppercut();
                break;
            case 4:
                if (opponent.hasEnoughStamina(9)) opponent.quickJab();
                break;
            case 5:
                if (opponent.hasEnoughStamina(7)) opponent.cross();
                break;
            case 6:
                if (opponent.hasEnoughStamina(14)) opponent.powerPunch();
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }

    @Override
    protected void opponentPerformFailed(int choice) {
        switch (choice) {
            case 0:
                opponent.setStamina(opponent.getStamina() - 5);
                break;
            case 1:
                opponent.setStamina(opponent.getStamina() - 7);
                break;
            case 2:
                break;
            case 3:
                opponent.setStamina(opponent.getStamina() - 10);
                break;
            case 4:
                opponent.setStamina(opponent.getStamina() - 9);
                break;
            case 5:
                opponent.setStamina(opponent.getStamina() - 7);
                break;
            case 6:
                opponent.setStamina(opponent.getStamina() - 14);
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }
}
