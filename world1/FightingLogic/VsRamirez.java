package world1.FightingLogic;
import java.util.Random;

import world1.FightLogic;
import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world1.TournamentFight.Ramirez2;

public class VsRamirez {
    static Random rand = new Random();
    static StreetFighter opponent;
    static Player player = GameLogic.player;
    static boolean playerDodged = false;
    static boolean opponentDodged = false;

    public VsRamirez(Player play, StreetFighter enemy){
        player = play;
        opponent = enemy;
    }

    public static void setPlayerOpponent(Player player) {
        VsRamirez.player = player;
    }

    public static void setOpponent(StreetFighter opponent) {
        VsRamirez.opponent = opponent;
    }
    
    public static void playerSuccessAction(int choice, int opponentChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();
        
        if(critChance < player.getCritChance() && choice != 2 && !isDraw && !opponentDodged){
            player.setDamageSetter(player.getCritMultiplier());
            System.out.println(player.getName() + "\'s " + FightLogic.playerAttacks[choice] + " hit the weak spot! CRITICAL HIT!");
        }

        if(dodgeChance < player.getDodgeChance() && opponentChoice != 2 && !isDraw){
            playerDodged = true;
        }

        if(opponentDodged){
            player.setDamageSetter(0);
            System.out.println(opponent.getName() + " dodges " + player.getName() + " punch!");
        }
        
        switch (choice) {
            case 0:
                if (player.hasEnoughStamina(5)) {
                    player.jab();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to jab!");
                }
                break;
            case 1:
                if (player.hasEnoughStamina(7)) {
                    player.hook();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to hook!");
                }
                break;
            case 2:
                player.block();
                break;
            case 3:
                if (player.hasEnoughStamina(10)) {
                    player.uppercut();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to uppercut!");
                }
                break;
            case 4:
                if (player.hasEnoughStamina(7)) {
                    player.leadBodyShot();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to lead body shot!");
                }
                break;
            case 5:
                if (player.hasEnoughStamina(9)) {
                    player.crossToTheRibs();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to cross to the ribs!");
                }
                break;
            case 6:
                if (player.hasEnoughStamina(14)) {
                    player.finishingUppercut();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to finishing uppercut!");
                }
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
        player.setDamageSetter(1);
        opponentDodged = false;
    }

    public static void playerFailedAction(int choice) {
        switch (choice) {
            case 0:
                player.setStamina(player.getStamina() - 5);
                break;
            case 1:
                player.setStamina(player.getStamina() - 7);
                break;
            case 2:
                break;
            case 3:
                player.setStamina(player.getStamina() - 10);
                break;
            case 4:
                player.setStamina(player.getStamina() - 7);
                break;
            case 5:
                player.setStamina(player.getStamina() - 9);
                break;
            case 6:
                player.setStamina(player.getStamina() - 14);
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }

    public static void opponentSuccessAction(int choice, int playerChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();
        
        if(critChance < opponent.getCritChance() && choice != 2 && !isDraw){
            opponent.setDamageSetter(opponent.getCritMultiplier());
            System.out.println(opponent.getName() + "\'s " + Ramirez2.playerAttacks[choice] + " hit the weak spot! CRITICAL HIT!");
        }

        if(dodgeChance < opponent.getDodgeChance() && playerChoice != 2 && !isDraw){
            opponentDodged = true;
        }

        if(playerDodged){
            opponent.setDamageSetter(0);
            System.out.println(player.getName() + " dodges " + opponent.getName() + " punch!");
        }

        switch (choice) {
            case 0:
                if (opponent.hasEnoughStamina(5)) {
                    opponent.jab();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to jab!");
                }
                break;
            case 1:
                if (opponent.hasEnoughStamina(7)) {
                    opponent.hook();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to hook!");
                }
                break;
            case 2:
                opponent.block();
                break;
            case 3:
                if (opponent.hasEnoughStamina(10)) {
                    opponent.uppercut();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to uppercut!");
                }
                break;
            case 4:
                if (opponent.hasEnoughStamina(7)) {
                    opponent.cross();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to jab to the body!");
                }
                break;
            case 5:
                if (opponent.hasEnoughStamina(9)) {
                    opponent.leadHook();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to lead hook!");
                }
                break;
            case 6:
                if (opponent.hasEnoughStamina(14)) {
                    opponent.rearUppercut();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to rear uppercut!");
                }
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
        opponent.setDamageSetter(1);
        playerDodged = false;
    }

    public static void opponentFailedAction(int choice) {
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
                opponent.setStamina(opponent.getStamina() - 7);
                break;
            case 5:
                opponent.setStamina(opponent.getStamina() - 9);
                break;
            case 6:
                opponent.setStamina(opponent.getStamina() - 14);
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }

    public static void drawAction(int choice, int opponentChoice) {
        player.setDamageSetter(0.5);
        playerSuccessAction(choice, opponentChoice, false);
        player.setDamageSetter(1);

        opponent.setDamageSetter(0.5);
        opponentSuccessAction(opponentChoice, choice, false);
        opponent.setDamageSetter(1);
    }
}
