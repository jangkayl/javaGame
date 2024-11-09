import world1.GameLogic;
import world1.Skill.SkillsRegistry;


public class Main {

    SkillsRegistry skills = new SkillsRegistry();
    
    // public void jab() {
    //     int damage = (int)Math.floor(10 * getDamageSetter());
    //     int staminaReduced = 5;
    //     opponent.setHp(opponent.getHp() - damage);
    //     this.setStamina(this.getStamina() - staminaReduced);
    //     System.out.print(GameLogic.centerText(40,this.getName() + " jabs " + opponent.getName() + " for " + damage + " damage!"));
    // }
    
    // public void hook() {
    //     int damage = (int)Math.floor(15 * getDamageSetter());
    //     int staminaReduced = 7;
    //     opponent.setHp(opponent.getHp() - damage);
    //     this.setStamina(this.getStamina() - staminaReduced);
    //     System.out.print(GameLogic.centerText(40,this.getName() + " hooks " + opponent.getName() + " for " + damage + " damage!"));
    // }
    
    // public void block() {
    //     int newStamina = this.getStamina() + 5;
    //     if (newStamina > this.getMaxStamina()) {
    //         newStamina = this.getMaxStamina();
    //     } else {
    //         System.out.print(GameLogic.centerText(40,this.getName() + " blocks and gains 5 stamina!"));
    //     }
    //     this.setStamina(newStamina);
    // }
    
    // public void uppercut() {
    //     int damage = (int)Math.floor(20 * getDamageSetter());
    //     int staminaReduced = 10;
    //     opponent.setHp(opponent.getHp() - damage);
    //     this.setStamina(this.getStamina() - staminaReduced);
    //     System.out.print(GameLogic.centerText(40,this.getName() + " uppercuts " + opponent.getName() + " for " + damage + " damage!"));
    // }

    public void useSkill(String skillName){
        System.out.println(skills.getSkillByName(skillName).toString());
        System.out.println();
        System.out.println(skills.getSkillByName(skillName).counters("Hook"));

        int damage = (int)Math.floor(skills.getSkillByName(skillName).getHpDamage() * 1);
        int staminaReduced = skills.getSkillByName(skillName).getStaminaCost();
        if(skillName == "Block"){
            System.out.print(GameLogic.centerText(40, "Kyle " + skills.getSkillByName(skillName).getAttackName() + " and gains 5 stamina!"));
        } else {
            System.out.print(GameLogic.centerText(40, "Kyle " + skills.getSkillByName(skillName).getAttackName() + " Jexter for " + damage + " damage! -" + staminaReduced));
        }
    }

    public static void main(String[] args){
        GameLogic.startGame();
        // Main obj = new Main();
        // String name = "Lead Body Shot";
        // obj.useSkill(name);
    }
}
