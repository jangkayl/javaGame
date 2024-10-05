public abstract class Boxer {
    private String name;
    private int maxHp, hp, stamina;

    public Boxer(String name, int hp, int stamina){
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.stamina = stamina;
    }

    public abstract void jab();
    public abstract void hook();
    public abstract void uppercut();
    public abstract void defend();

    public String getName(){
        return this.name;
    }

    public int getMaxHp(){
        return this.maxHp;
    }
    
    public void setMaxHp(int maxHp){
        this.maxHp += maxHp;
    }

    public int getHp(){
        return this.hp;
    }

    public void setHp(int hp){
        this.hp += hp;
    }

    public int getStamina(){
        return this.stamina;
    }

    public void setStamina(int stamina){
        this.stamina += stamina;
    }
}
