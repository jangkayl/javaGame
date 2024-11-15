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
                            Arrays.asList("Hook", "Left Hook", "Right Uppercut", "Jab to the Body", "Quick Jab", "Elbow Strike"),
                            Arrays.asList("Uppercut", "Right Cross", "Power Punch")));
        registerSkill(new Skill("Hook", "hooks", 7, 15, 0, 0,
                            Arrays.asList("Block", "Right Cross", "Left Hook", "Elbow Strike", "Head Butt"),
                            Arrays.asList("Jab", "Right Uppercut", "Jab to the Body", "Quick Jab", "Power Punch", "Low Blow")));
        registerSkill(new Skill("Block", "blocks", 0, 5, 0, 0,
                            Arrays.asList("Uppercut", "Jab to the Body", "Quick Jab", "Low Blow", "Rear Uppercut"),
                            Arrays.asList("Hook", "Right Cross", "Left Hook", "Elbow Strike", "Right Uppercut")));
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
                            Arrays.asList("Jab", "Uppercut", "Head Butt")));
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
                            Arrays.asList("Uppercut", "Right Cross"), 
                            Arrays.asList("Jab", "Hook")));

        registerSkill(new Skill("Quick Jab", "quick jab", 9, 25, 0, 0, 
                            Arrays.asList("Uppercut"), 
                            Arrays.asList("Jab", "Hook")));
        registerSkill(new Skill("Power Punch", "power punch", 7, 15, 0, 0, 
                            Arrays.asList("Hook"), 
                            Arrays.asList("Uppercut", "Jab")));   

        registerSkill(new Skill("Elbow Strike", "elbow strikes (-10 HP)", 25, 40, 10, 0, 
                            Arrays.asList("Block"), 
                            Arrays.asList("Jab", "Hook", "Right Uppercut", "Left Hook")));
        registerSkill(new Skill("Head Butt", "head butts (-15 HP)", 20, 30, 15, 0, 
                            Arrays.asList("Uppercut"), 
                            Arrays.asList("Block")));  
        registerSkill(new Skill("Low Blow", "low blows (-20 HP)", 25, 40, 20, 15, 
                            Arrays.asList("Hook"), 
                            Arrays.asList("Block")));  
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
