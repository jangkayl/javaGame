package world3.PassiveSkill;

import java.util.HashMap;
import java.util.Map;

import interfaces.PassiveSkillRegistryInterface;

public class PassiveSkillRegistry implements PassiveSkillRegistryInterface{
    private final Map<String, PassiveSkill> passiveSkills;

    public PassiveSkillRegistry() {
        this.passiveSkills = new HashMap<>();
        initializeSkills();
    }

    private void initializeSkills() {
        registerSkill(new PassiveSkill("Flow State", "100% Dodge Chance, blocks all damage next 3 turns.", 3));
        registerSkill(new PassiveSkill("Adrenaline Rush", "Boost your Crit Chance by 100% next 3 turns.", 3));
        registerSkill(new PassiveSkill("Sixth Sense", "Reveals the opponent's next 3 moves for next 2 turns.", 2));
    }

    public void registerSkill(PassiveSkill passiveSkill) {
        if (passiveSkills.containsKey(passiveSkill.getName())) {
            throw new IllegalArgumentException("Skill already registered: " + passiveSkill.getName());
        }
        passiveSkills.put(passiveSkill.getName(), passiveSkill);
    }

    public PassiveSkill getSkillByName(String name) {
        return passiveSkills.get(name);
    }
}
