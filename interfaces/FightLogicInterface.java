package interfaces;

import GlobalClasses.Player;
import Skill.SkillsRegistry;
import GlobalClasses.StreetFighter;

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
