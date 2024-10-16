public class StreetFighter extends Boxer {
    static Boxer player = UrbanGym.player;
    public StreetFighter(String name, int hp, int stamina, double critChance, double critMultiplier,
            double dodgeChance) {
        super(name, hp, stamina, critChance, critMultiplier, dodgeChance);
    }

    @Override
    public void jab() {
        int damage = 10;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getMaxStamina() - damage);
        System.out.println(this.getName() + " jabs " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void hook() {
        int damage = 15;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getMaxStamina() - damage);
        System.out.println(this.getName() + " hooks " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void uppercut() {
        int damage = 20;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getMaxStamina() - damage);
        System.out.println(this.getName() + " uppercuts " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void block() {
        int newStamina = this.getStamina() + 10;
        if (newStamina > this.getMaxStamina()) {
            newStamina = this.getMaxStamina();
        } else {
            System.out.println(this.getName() + " blocks and gains 10 stamina!");
        }
        this.setStamina(newStamina);
    }
    
}
