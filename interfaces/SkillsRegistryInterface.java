package interfaces;

import Skill.Skill;

public interface SkillsRegistryInterface {
    void registerSkill(Skill skill) ;
    Skill getSkillByName(String name);
}
