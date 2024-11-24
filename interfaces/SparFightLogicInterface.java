package interfaces;

import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import Skill.SkillsRegistry;
import GlobalClasses.StreetFighter;

public interface SparFightLogicInterface {
     Player getPlayer();
     StreetFighter getOpponent();
     PlayerProgress getPlayerProgress();
     int[] getOpponentChoices();
     void setOpponentChoices(int[] opponentChoices);
     void setOpponent(StreetFighter opponent);
     String getPlayerAttackByChoice(int choice);
     SkillsRegistry getSkills();
     void fightLoop();
}
