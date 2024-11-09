package world1.FightingLogic;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;

public abstract class PlayerVsOpponent {
    protected static Random rand = new Random();
    protected static StreetFighter opponent;
    protected static Player player;
    protected static boolean playerDodged = false;
    protected static boolean opponentDodged = false;
    private String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut"};
    
    protected abstract String[] getOpponentAttacks();
    protected abstract void opponentPerformAction(int choice);
    protected abstract void opponentPerformFailed(int choice);

    public PlayerVsOpponent(Player player, StreetFighter opponent) {
        PlayerVsOpponent.player = player;
        PlayerVsOpponent.opponent = opponent;
    }

    public static void setPlayerOpponent(Player player) {
        PlayerVsOpponent.player = player;
    }

    public static void setOpponent(StreetFighter opponent) {
        PlayerVsOpponent.opponent = opponent;
    }

    public void playerSuccessAction(int choice, int opponentChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < player.getCritChance() && choice != 2 && !isDraw && !opponentDodged) {
            player.setDamageSetter(player.getCritMultiplier());
            System.out.print(GameLogic.centerText(40,player.getName() + "'s " + playerAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
        }

        if (dodgeChance < player.getDodgeChance() && opponentChoice != 2 && !isDraw) {
            playerDodged = true;
        }

        if (opponentDodged) {
            player.setDamageSetter(0);
            System.out.print(GameLogic.centerText(40,opponent.getName() + " dodges " + player.getName() + "'s punch!"));
        }

        switch (choice) {
            case 0:
                if (player.hasEnoughStamina(5)) player.jab();
                break;
            case 1:
                if (player.hasEnoughStamina(7)) player.hook();
                break;
            case 2:
                player.block();
                break;
            case 3:
                if (player.hasEnoughStamina(10)) player.uppercut();
                break;
            case 4:
                if (player.hasEnoughStamina(7)) player.leadBodyShot();
                break;
            case 5:
                if (player.hasEnoughStamina(9)) player.crossToTheRibs();
                break;
            case 6:
                if (player.hasEnoughStamina(14)) player.finishingUppercut();
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
        player.setDamageSetter(1);
        opponentDodged = false;
    }

    public void playerFailedAction(int choice) {
        switch (choice) {
            case 0: player.setStamina(player.getStamina() - 5); break;
            case 1: player.setStamina(player.getStamina() - 7); break;
            case 2: break;
            case 3: player.setStamina(player.getStamina() - 10); break;
            case 4: player.setStamina(player.getStamina() - 7); break;
            case 5: player.setStamina(player.getStamina() - 9); break;
            case 6: player.setStamina(player.getStamina() - 14); break;
            default: System.out.println("Invalid action choice!"); break;
        }
    }

    public void opponentSuccessAction(int choice, int playerChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < opponent.getCritChance() && choice != 2 && !isDraw) {
            opponent.setDamageSetter(opponent.getCritMultiplier());
            System.out.print(GameLogic.centerText(40,opponent.getName() + "'s " + getOpponentAttacks()[choice] + " hit the weak spot! CRITICAL HIT!"));
        }

        if (dodgeChance < opponent.getDodgeChance() && playerChoice != 2 && !isDraw) {
            opponentDodged = true;
        }

        if (playerDodged) {
            opponent.setDamageSetter(0);
            System.out.print(GameLogic.centerText(40,player.getName() + " dodges " + opponent.getName() + "'s punch!"));
        }

        opponentPerformAction(choice);

        opponent.setDamageSetter(1);
        playerDodged = false;
    }

    public void opponentFailedAction(int choice) {
        opponentPerformFailed(choice);
    }

    public void drawAction(int choice, int opponentChoice) {
        player.setDamageSetter(0.5);
        playerSuccessAction(choice, opponentChoice, false);
        player.setDamageSetter(1);

        opponent.setDamageSetter(0.5);
        opponentSuccessAction(opponentChoice, choice, false);
        opponent.setDamageSetter(1);
    }
}
