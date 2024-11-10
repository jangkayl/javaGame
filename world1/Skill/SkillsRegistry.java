package world1.Skill;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SkillsRegistry {
    private final Map<String, Skill> skills;

    public SkillsRegistry() {
        this.skills = new HashMap<>();
        initializeSkills();
    }

    private void initializeSkills() {
        registerSkill(new Skill("Jab", "jabs", 5, 10, 0, 0,
                            Arrays.asList("Hook", "Left Hook", "Right Uppercut", "Jab to the Body", "Quick Jab"),
                            Arrays.asList("Uppercut", "Right Cross", "Power Punch")));
        registerSkill(new Skill("Hook", "hooks", 7, 15, 0, 0,
                            Arrays.asList("Block", "Right Cross", "Left Hook"),
                            Arrays.asList("Jab", "Right Uppercut", "Jab to the Body", "Quick Jab", "Power Punch")));
        registerSkill(new Skill("Block", "blocks", 0, 5, 0, 0,
                            Arrays.asList("Uppercut", "Right Uppercut", "Jab to the Body", "Quick Jab"),
                            Arrays.asList("Hook", "Right Cross", "Left Hook")));
        registerSkill(new Skill("Uppercut", "uppercuts", 10, 20, 0, 0, 
                            Arrays.asList("Jab", "Right Uppercut", "Right Cross", "Quick Jab"), 
                            Arrays.asList("Block", "Power Punch")));

        registerSkill(new Skill("Lead Body Shot", "lead body shots", 7, 15, 0, 0, 
                            Arrays.asList("Hook", "Power Punch"), 
                            Arrays.asList("Jab", "Block", "Uppercut")));
        registerSkill(new Skill("Cross To The Ribs", "cross to the ribs", 9, 20, 0, 0, 
                            Arrays.asList("Block"), 
                            Arrays.asList("Jab", "Hook")));
        registerSkill(new Skill("Finishing Uppercut", "finishing uppercuts", 14, 25, 0, 0, 
                            Arrays.asList("Block", "Jab"), 
                            Arrays.asList("Uppercut", "Hook")));

        registerSkill(new Skill("Right Uppercut", "right uppercuts", 9, 20, 0, 0, 
                            Arrays.asList("Block"), 
                            Arrays.asList("Jab", "Uppercut")));
        registerSkill(new Skill("Left Hook", "left hooks", 10, 25, 0, 0, 
                            Arrays.asList("Jab"), 
                            Arrays.asList("Hook", "Block")));
        registerSkill(new Skill("Right Cross", "right cross", 14, 30, 0, 0, 
                            Arrays.asList("Uppercut"), 
                            Arrays.asList("Hook", "Jab")));

        registerSkill(new Skill("Cross", "cross", 7, 15, 0, 0, 
                            Arrays.asList("Uppercut"), 
                            Arrays.asList("Jab", "Hook")));
        registerSkill(new Skill("Rear Uppercut", "rear uppercuts", 14, 25, 0, 0, 
                            Arrays.asList("Block"), 
                            Arrays.asList("Jab", "Uppercut")));
        registerSkill(new Skill("Lead Hook", "lead hooks", 9, 20, 0, 0, 
                            Arrays.asList("Jab"), 
                            Arrays.asList("Hook", "Block")));

        registerSkill(new Skill("Jab to the Body", "jab to the body", 7, 15, 0, 0, 
                            Arrays.asList("Uppercut"), 
                            Arrays.asList("Jab", "Hook")));

        registerSkill(new Skill("Quick Jab", "quick jab", 9, 25, 0, 0, 
                            Arrays.asList("Uppercut"), 
                            Arrays.asList("Jab", "Hook")));
        registerSkill(new Skill("Power Punch", "power punch", 7, 15, 0, 0, 
                            Arrays.asList("Hook"), 
                            Arrays.asList("Uppercut", "Jab")));   
    }

    public void registerSkill(Skill skill) {
        if (skills.containsKey(skill.getName())) {
            throw new IllegalArgumentException("Skill already registered: " + skill.getName());
        }
        skills.put(skill.getName(), skill);
    }

    public Skill getSkillByName(String name) {
        return skills.get(name);
    }

}
