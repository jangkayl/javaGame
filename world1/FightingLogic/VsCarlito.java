package world1.FightingLogic;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world1.TrainInGym.CarlitoUrbanGym;

public class VsCarlito extends PlayerVsOpponent{
    static Player player = GameLogic.player;

    public VsCarlito(Player play, StreetFighter opponent){
        super(player, opponent);
    }

    @Override
    protected String[] getOpponentAttacks() {
        String[] attacks = new String[4];
        for(int i = 0; i < 4; i++){
            attacks[i] = CarlitoUrbanGym.getAttacks()[i][0];
        }
        return attacks;
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
            default:
                System.out.println("Invalid action choice!");
                break;
        }
        opponent.setDamageSetter(1);
        playerDodged = false;
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
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }
}