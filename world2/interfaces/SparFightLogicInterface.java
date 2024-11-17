package world2.interfaces;

import world1.Player;
import world1.PlayerProgress;
import world1.Skill.SkillsRegistry;
import world1.StreetFighter;

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
