package world2.SparringLogic;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.TournaUnderFight.NavarroTourna;

public class VsNavarro extends PlayerVsSpar{
    static Player player = GameLogic.player;

    public VsNavarro(Player play, StreetFighter opponent){
        super(player, opponent);
    }

    @Override
    protected String[] getOpponentAttacks() {
        return NavarroTourna.opponentAttacks;
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
                if (opponent.hasEnoughStamina(9)) opponent.rightUppercut();
                break;
            case 5:
                if (opponent.hasEnoughStamina(10)) opponent.leftHook();
                break;
            case 6:
                if (opponent.hasEnoughStamina(14)) opponent.rightCross();
                break;
            case 7:
                if (opponent.hasEnoughStamina(9)) opponent.elbowStrike();
                break;
            case 8:
                if (opponent.hasEnoughStamina(10)) opponent.headButt();
                break;
            case 9:
                if (opponent.hasEnoughStamina(14)) opponent.lowBlow();
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
                opponent.setStamina(opponent.getStamina() - 10);
                break;
            case 6:
                opponent.setStamina(opponent.getStamina() - 14);
                break;
            case 7:
                opponent.setStamina(opponent.getStamina() - 25);
                break;
            case 8:
                opponent.setStamina(opponent.getStamina() - 20);
                break;
            case 9:
                opponent.setStamina(opponent.getStamina() - 25);
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }
}
