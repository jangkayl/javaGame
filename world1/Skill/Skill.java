package world1.Skill;

import java.util.List;

public class Skill {
    private final String name;                 
    private final String attackName;                 
    private final int staminaCost;            
    private final int hpDamage;            
    private final int hpReduced;            
    private final int staminaReduced;            
    private final List<String> counters;     
    private final List<String> effectiveAgainst; 

    public Skill(String name, String attackName, int staminaCost, int hpDamage, int hpReduced, int staminaReduced, List<String> counters, List<String> effectiveAgainst) {
        this.name = name;
        this.attackName = attackName;
        this.staminaCost = staminaCost;
        this.hpDamage = hpDamage;
        this.hpReduced = hpReduced;
        this.staminaReduced = staminaReduced;
        this.counters = counters;
        this.effectiveAgainst = effectiveAgainst;
    }

    public String getName() {
        return name;
    }

    public String getAttackName() {
        return attackName;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public int getHpDamage() {
        return hpDamage;
    }

    public int getHpReduced() {
        return hpReduced;
    }

    public int getStaminaReduced() {
        return staminaReduced;
    }

    public boolean counters(String skillName) {
        return counters.contains(skillName);
    }

    public boolean isEffectiveAgainst(String skillName) {
        return effectiveAgainst.contains(skillName);
    }

    public List<String> getCounters() {
        return counters;
    }

    public List<String> getEffectiveAgainst() {
        return effectiveAgainst;
    }

    @Override
    public String toString() {
        return String.format("Skill{\nname='%s', \nstaminaCost=%d, \ncounters=%s, \neffectiveAgainst=%s, \nhpDamage=%s, \nstaminaReduced=%s, \nhpReduced=%s}", 
                             name, staminaCost, counters, effectiveAgainst, hpDamage, staminaReduced, hpReduced, staminaReduced);
    }
}
