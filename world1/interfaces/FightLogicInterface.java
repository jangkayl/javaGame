package world1.interfaces;

import world1.Player;
import world1.Skill.SkillsRegistry;
import world1.StreetFighter;

public interface FightLogicInterface {
    String getOpponentName();
    Player getPlayer();
    StreetFighter getOpponent();
    int[] getOpponentChoices();
    void setOpponentChoices(int[] opponentChoices);
    void setOpponent(StreetFighter opponent);
    String getPlayerAttackByChoice(int choice);
    SkillsRegistry getSkills();
    void fightLoop();
}
