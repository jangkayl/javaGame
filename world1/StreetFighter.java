package world1;
public class StreetFighter extends Boxer {
    static Player player = GameLogic.player;
    public StreetFighter(String name, int hp, int stamina, double critChance, double critMultiplier,
            double dodgeChance, int rank) {
        super(name, hp, stamina, critChance, critMultiplier, dodgeChance, rank);
    }

    public void setPlayerOpponent(Player play) {
        player = play;
    }

    @Override
    public void jab() {
        int damage = (int)Math.floor(10 * getDamageSetter());
        int staminaReduced = 5;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " jabs " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void hook() {
        int damage = (int)Math.floor(15 * getDamageSetter());
        int staminaReduced = 7;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " hooks " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void uppercut() {
        int damage = (int)Math.floor(20 * getDamageSetter());
        int staminaReduced = 10;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " uppercuts " + player.getName() + " for " + damage + " damage!");
    }

    @Override
    public void block() {
        int newStamina = this.getStamina() + 5;
        if (newStamina > this.getMaxStamina()) {
            newStamina = this.getMaxStamina();
        } else {
            System.out.println(this.getName() + " blocks and gains 5 stamina!");
        }
        this.setStamina(newStamina);
    }

    @Override
    public void elbowStrike(){
        int damage = (int)Math.floor(40 * getDamageSetter());
        int staminaReduced = 25;
        int hpReduced = 10;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        this.setHp(this.getHp() - hpReduced);
        System.out.println(this.getName() + " elbow strike " + player.getName() + " for " + damage + " damage!");
    }
    
    @Override
    public void headButt(){
        int damage = (int)Math.floor(30 * getDamageSetter());
        int staminaReduced = 20;
        int hpReduced = 15;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        this.setHp(this.getHp() - hpReduced);
        System.out.println(this.getName() + " head butt " + player.getName() + " for " + damage + " damage!");
    }
    
    @Override
    public void lowBlow(){
        int damage = (int)Math.floor(40 * getDamageSetter());
        int staminaReduced = 25;
        int hpReduced = 20;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        this.setHp(this.getHp() - hpReduced);
        System.out.println(this.getName() + " low blow " + player.getName() + " for " + damage + " damage!");
    }

    public boolean hasEnoughStamina(int requiredStamina) {
        return this.getStamina() >= requiredStamina;
    }

    // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~

    // Pablo's COMBO
    public void jabToTheBody() {
        int damage = (int)Math.floor(15 * getDamageSetter());
        int staminaReduced = 7;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " jabs to the body " + player.getName() + " for " + damage + " damage!");
    }

    public void leadHook() {
        int damage = (int)Math.floor(20 * getDamageSetter());
        int staminaReduced = 9;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " lead hooks " + player.getName() + " for " + damage + " damage!");
    }

    public void rearUppercut() {
        int damage = (int)Math.floor(25 * getDamageSetter());
        int staminaReduced = 14;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " rear uppercuts " + player.getName() + " for " + damage + " damage!");
    }

    // Ramirez's COMBO
    public void cross() {
        int damage = (int)Math.floor(15 * getDamageSetter());
        int staminaReduced = 7;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " cross " + player.getName() + " for " + damage + " damage!");
    }

    // Lopez COMBO
    public void quickJab() {
        int damage = (int)Math.floor(25 * getDamageSetter());
        int staminaReduced = 9;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " quick jab " + player.getName() + " for " + damage + " damage!");
    }

    public void powerPunch() {
        int damage = (int)Math.floor(30 * getDamageSetter());
        int staminaReduced = 14;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " power punch " + player.getName() + " for " + damage + " damage!");
    }

    // Tetteh COMBO
    public void rightUppercut() {
        int damage = (int)Math.floor(20 * getDamageSetter());
        int staminaReduced = 9;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " right uppercut " + player.getName() + " for " + damage + " damage!");
    }

    public void leftHook() {
        int damage = (int)Math.floor(25 * getDamageSetter());
        int staminaReduced = 10;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " left hook " + player.getName() + " for " + damage + " damage!");
    }

    public void rightCross() {
        int damage = (int)Math.floor(30 * getDamageSetter());
        int staminaReduced = 14;
        player.setHp(player.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " right cross " + player.getName() + " for " + damage + " damage!");
    }
}