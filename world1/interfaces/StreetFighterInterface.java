package world1.interfaces;

import world1.Player;
import world1.StreetFighter;

public interface StreetFighterInterface {
    void setPlayerOpponent(Player play);
    void useSkill(String skillName);
    void useSkill(String skillName, StreetFighter target) ;
    boolean hasEnoughStamina(int requiredStamina);
}
