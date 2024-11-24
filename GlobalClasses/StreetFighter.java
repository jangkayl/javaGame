package world1;

import Skill.SkillsRegistry;
import interfaces.StreetFighterInterface;

public class StreetFighter extends Boxer implements StreetFighterInterface{
    private static Player player = GameLogic.player;
    private SkillsRegistry skills = new SkillsRegistry();

    public StreetFighter(String name, int hp, int stamina, double critChance, double critMultiplier,
            double dodgeChance, int rank) {
        super(name, hp, stamina, critChance, critMultiplier, dodgeChance, rank);
    }

    public void setPlayerOpponent(Player play) {
        player = play;
    }

    public void useSkill(String skillName){
        if(skillName == "Block"){
            int newStamina = this.getStamina() + 5;
            if (newStamina > this.getMaxStamina()) {
                newStamina = this.getMaxStamina();
            }

            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40, getName() + " " + skills.getSkillByName(skillName).getAttackName() + " and gains 5 stamina!"));
            System.out.print(GameLogic.reset);
            
            this.setStamina(newStamina);
        } else {
            int damage = (int)Math.floor(skills.getSkillByName(skillName).getHpDamage() * getDamageSetter());
            int staminaReduced = skills.getSkillByName(skillName).getStaminaCost();
            int hpReduced = skills.getSkillByName(skillName).getHpReduced() != 0 ? skills.getSkillByName(skillName).getHpReduced() : 0;
            int staminaMinus = skills.getSkillByName(skillName).getStaminaReduced() != 0 ? skills.getSkillByName(skillName).getStaminaReduced() : 0;
            if(hpReduced != 0)
                this.setHp(this.getHp() - hpReduced);
            if(staminaMinus != 0)
                player.setStamina(player.getStamina() - staminaMinus);
            player.setHp(player.getHp() - damage);
            this.setStamina(this.getStamina() - staminaReduced);

            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40, getName() + " " + skills.getSkillByName(skillName).getAttackName() + " " + player.getName() + " for " + damage + (staminaMinus != 0 ? " damage and drains " + staminaMinus + " stamina!" : " damage!")));
            System.out.print(GameLogic.reset);
            
        }
    }

    public void useSkill(String skillName, StreetFighter target) {
        if (skillName.equals("Block")) {
            int newStamina = this.getStamina() + 5;
            if (newStamina > this.getMaxStamina()) {
                newStamina = this.getMaxStamina();
            }

            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40, this.getName() + " blocks and gains 5 stamina!"));
            System.out.print(GameLogic.reset);
            
            this.setStamina(newStamina);
        } else {
            int damage = (int) Math.floor(skills.getSkillByName(skillName).getHpDamage() * this.getDamageSetter());
            int staminaReduced = skills.getSkillByName(skillName).getStaminaCost();
            int hpReduced = skills.getSkillByName(skillName).getHpReduced();
            int staminaMinus = skills.getSkillByName(skillName).getStaminaReduced();
    
            if (hpReduced != 0) {
                this.setHp(this.getHp() - hpReduced);
            }
            if (staminaMinus != 0) {
                target.setStamina(target.getStamina() - staminaMinus);
            }
            target.setHp(target.getHp() - damage);
            this.setStamina(this.getStamina() - staminaReduced);
    
            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40, this.getName() + " uses " + skills.getSkillByName(skillName).getAttackName() +
                    " on " + target.getName() + " for " + damage +
                    (staminaMinus != 0 ? " damage and drains " + staminaMinus + " stamina!" : " damage!")));
            System.out.print(GameLogic.reset);
        }
    }

    public boolean hasEnoughStamina(int requiredStamina) {
        return this.getStamina() >= requiredStamina;
    }
}