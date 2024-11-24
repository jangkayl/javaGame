package interfaces;

public interface BoxerInterface {
    String getName();
    void setName(String name);
    int getMaxHp();
    void setMaxHp(int maxHp);
    int getHp();
    void setHp(int hp);
    void setStamina(int stamina);
    int getMaxStamina();
    void setMaxStamina(int maxStamina);
    double getCritChance();
    void setCritChance(double critChance);
    double getCritMultiplier();
    void setCritMultiplier(double critMultiplier);
    double getDodgeChance();
    void setDodgeChance(double dodgeChance);
    double getDamageSetter();
    void setDamageSetter(double damageSetter);
    String getRank();
    String[] getAllRankings();
}
