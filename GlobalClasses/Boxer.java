package GlobalClasses;

import interfaces.BoxerInterface;

public abstract class Boxer implements BoxerInterface{
    private String name;
    protected String[] ranking = {"ROOKIE", "AMATEUR", "CONTENDER", "CHALLENGER", "PRO FIGHTER", "CHAMPION", "WORLD CHAMPION"};
    private int maxHp, hp, stamina, maxStamina, rank;
    private double critChance, critMultiplier, dodgeChance, damageSetter;

    public Boxer(String name, int hp, int stamina, double critChance, double critMultiplier, double dodgeChance, int rank){
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.stamina = stamina;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.dodgeChance = dodgeChance;
        this.maxStamina = stamina;
        this.damageSetter = 1;
        this.rank = rank;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getMaxHp(){
        return this.maxHp;
    }
    
    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public int getHp(){
        if(this.hp < 0) return 0;
        return this.hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public int getStamina(){
        if(this.stamina < 0) return 0;
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

    public double getDamageSetter(){
        return this.damageSetter;
    }

    public void setDamageSetter(double damageSetter){
        this.damageSetter = damageSetter;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public String getRank(){
        return ranking[rank];
    } 

    public int getRankInt(){
        return rank;
    }

    public String[] getAllRankings(){
        return ranking;
    }
}
