package interfaces;

public interface PassiveSkillInterface {
     String getName();
     String getDescription();
     int getRoundActive();
     void setRoundActive(int rounds);
     void activatePassive(String name);
     void deactivatePassive(String name);
}
