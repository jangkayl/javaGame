public class StreetFighter extends Boxer {
    static Player player;
    public StreetFighter(String name, int hp, int stamina, double critChance, double critMultiplier,
            double dodgeChance) {
        super(name, hp, stamina, critChance, critMultiplier, dodgeChance);
    }

    public static void setPlayerOpponent(Player player) {
        StreetFighter.player = player;
    }

    @Override
    public void jab() {
        int damage = 10;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - 15);
        System.out.println(this.getName() + " jabs " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void hook() {
        int damage = 15;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - 20);
        System.out.println(this.getName() + " hooks " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void uppercut() {
        int damage = 20;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - 25);
        System.out.println(this.getName() + " uppercuts " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void block() {
        int newStamina = this.getStamina() + 5;
        if (newStamina > this.getStamina()) {
            newStamina = this.getStamina();
        } else {
            System.out.println(this.getName() + " blocks and gains 5 stamina!");
        }
        this.setStamina(newStamina);
    }

    @Override
    public void performAction(int choice){
        switch (choice) {
            case 0:
                jab();
                break;
            case 1:
                hook();
                break;
            case 2:
                uppercut();
                break;
            case 3:
                block();
                break;
            default:
                System.out.println("Invalid action choice!");
                break;
        }
    }
}