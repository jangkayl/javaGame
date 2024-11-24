package interfaces;

import GlobalClasses.Player;
import GlobalClasses.StreetFighter;

public interface StreetFighterInterface {
    void setPlayerOpponent(Player play);
    void useSkill(String skillName);
    void useSkill(String skillName, StreetFighter target) ;
    boolean hasEnoughStamina(int requiredStamina);
}
