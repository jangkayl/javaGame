public abstract class Boxer {
    private String name;
    private int maxHp, hp, stamina, maxStamina;
    private double critChance, critMultiplier, dodgeChance;

    public Boxer(String name, int hp, int stamina, double critChance, double critMultiplier, double dodgeChance){
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.stamina = stamina;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.dodgeChance = dodgeChance;
        this.maxStamina = stamina;
    }

    public abstract void jab();
    public abstract void hook();
    public abstract void uppercut();
    public abstract void block();
    
    public String getName(){
        return this.name;
    }

    public int getMaxHp(){
        return this.maxHp;
    }
    
    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public int getHp(){
        return this.hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public int getStamina(){
        return this.stamina;
    }

    public void setStamina(int stamina){
        this.stamina = stamina;
    }

    public int getMaxStamina(){
        return this.maxStamina;
    }
    
    public void setMaxStamina(int maxStamina){
        this.maxStamina = maxStamina;
    }

    public double getCritChance(){
        return this.critChance;
    }

    public void setCritChance(double critChance){
        this.critChance = critChance;
    }

    public double getCritMultiplier(){
        return this.critMultiplier;
    }

    public void setCritMultiplier(double critMultiplier){
        this.critMultiplier = critMultiplier;
    }

    public double getDodgeChance(){
        return this.dodgeChance;
    }

    public void setDodgeChance(double dodgeChance){
        this.dodgeChance = dodgeChance;
    }
}
