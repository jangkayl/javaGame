package world3.PassiveSkill;

import java.util.HashMap;
import java.util.Map;

public class PassiveSkillRegistry {
    private final Map<String, PassiveSkill> passiveSkills;

    public PassiveSkillRegistry() {
        this.passiveSkills = new HashMap<>();
        initializeSkills();
    }

    private void initializeSkills() {
        registerSkill(new PassiveSkill("Flow State", "100% Dodge Chance, blocks all damage next turn.", 3));
        registerSkill(new PassiveSkill("Adrenaline Rush", "Boost your Crit Chance by 100% next turn.", 3));
        registerSkill(new PassiveSkill("Sixth Sense", "Reveals the opponent's next 3 moves.", 3));
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
