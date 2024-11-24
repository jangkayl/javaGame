package world1.interfaces;

import world1.Skill.Skill;

public interface SkillsRegistryInterface {
    void registerSkill(Skill skill) ;
    Skill getSkillByName(String name);
}
