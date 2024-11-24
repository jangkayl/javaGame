package interfaces;

import java.util.List;

public interface SkillInterface {
    String getName();
    String getAttackName();
    int getStaminaCost();
    int getHpDamage();
    int getHpReduced();
    int getStaminaReduced();
    boolean counters(String skillName);
    boolean isEffectiveAgainst(String skillName);
    List<String> getCounters();
    List<String> getEffectiveAgainst();
}
