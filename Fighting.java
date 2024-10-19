import java.util.Random;

public class Fighting {
    static StreetFighter opponent;
    static Player player;
    static boolean playerDodged = false;
    static boolean opponentDodged = false;


    public static void setPlayerOpponent(Player player) {
        Fighting.player = player;
    }

    public static void setOpponent(StreetFighter opponent) {
        Fighting.opponent = opponent;
    }
    
    static double generateRandom(){
        Random rand = new Random();
        return rand.nextDouble();
    }

    public static void playerSuccessAction(int choice, boolean isDraw) {
        double critChance = generateRandom();
        double dodgeChance = generateRandom();
        
        if(critChance < player.getCritChance() && choice != 2 && !isDraw && !opponentDodged){
            player.setDamageSetter(player.getCritMultiplier());
            System.out.println(player.getName() + "\'s " + UrbanGym.attack[choice][0] + " hit the weak spot! CRITICAL HIT!");
        }

        if(dodgeChance < player.getDodgeChance() && choice != 2 && !isDraw){
            playerDodged = true;
        }

        if(opponentDodged){
            player.setDamageSetter(0);
            System.out.println(opponent.getName() + " dodges " + player.getName() + " punch!");
        }
        
        switch (choice) {
            case 0:
                if (player.hasEnoughStamina(10)) {
                    player.jab();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to jab!");
                }
                break;
            case 1:
                if (player.hasEnoughStamina(15)) {
                    player.hook();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to hook!");
                }
                break;
            case 2:
                player.block();
                break;
            case 3:
                if (player.hasEnoughStamina(20)) {
                    player.uppercut();
                } else {
                    System.out.println(player.getName() + " doesn't have enough stamina to uppercut!");
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
                player.setStamina(player.getStamina() - 10);
                break;
            case 1:
                player.setStamina(player.getStamina() - 15);
                break;
            case 2:
                break;
            case 3:
                player.setStamina(player.getStamina() - 20);
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }

    public static void opponentSuccessAction(int choice, boolean isDraw) {
        double critChance = generateRandom();
        double dodgeChance = generateRandom();
        
        if(critChance < opponent.getCritChance() && choice != 2 && !isDraw){
            opponent.setDamageSetter(opponent.getCritMultiplier());
            System.out.println(opponent.getName() + "\'s " + UrbanGym.attack[choice][0] + " hit the weak spot! CRITICAL HIT!");
        }

        if(dodgeChance < opponent.getDodgeChance() && choice != 2 && !isDraw){
            opponentDodged = true;
        }

        if(playerDodged){
            opponent.setDamageSetter(0);
            System.out.println(player.getName() + " dodges " + opponent.getName() + " punch!");
        }

        switch (choice) {
            case 0:
                if (opponent.hasEnoughStamina(10)) {
                    opponent.jab();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to jab!");
                }
                break;
            case 1:
                if (opponent.hasEnoughStamina(15)) {
                    opponent.hook();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to hook!");
                }
                break;
            case 2:
                opponent.block();
                break;
            case 3:
                if (opponent.hasEnoughStamina(20)) {
                    opponent.uppercut();
                } else {
                    System.out.println(opponent.getName() + " doesn't have enough stamina to uppercut!");
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
                opponent.setStamina(opponent.getStamina() - 10);
                break;
            case 1:
                opponent.setStamina(opponent.getStamina() - 15);
                break;
            case 2:
                break;
            case 3:
                opponent.setStamina(opponent.getStamina() - 20);
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }

    public static void drawAction(int choice, int opponentChoice) {
        player.setDamageSetter(0.5);
        playerSuccessAction(choice, true);
        player.setDamageSetter(1);

        opponent.setDamageSetter(0.5);
        opponentSuccessAction(opponentChoice, true);
        opponent.setDamageSetter(1);
    }
}
