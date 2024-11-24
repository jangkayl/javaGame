package world1.TrainInGym;

import java.util.Random;

import world1.GameLogic;
import GlobalClasses.Player;
import GlobalClasses.StreetFighter;
import Skill.SkillsRegistry;

public abstract class PlayerVsOpponent {
    protected Random rand = new Random();
    private StreetFighter opponent;
    private SkillsRegistry skills = new SkillsRegistry();
    private Player player;
    private boolean playerDodged = false;
    private boolean opponentDodged = false;
    private String[] playerAttacks = {"Jab", "Hook", "Block", "Uppercut", "Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut"};
    private String[] opponentAttacks;

    public PlayerVsOpponent(Player player, StreetFighter opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void setOpponentAttacks(String[] opponentAttacks){
        this.opponentAttacks = opponentAttacks;
    }
    
    public Player getPlayer(){
        return player;
    }

    public SkillsRegistry getSkills(){
        return skills;
    }

    protected int isCounter(String opponentMove, String playerMove) {
        if(skills.getSkillByName(opponentMove).counters(playerMove))
            return 1;
        if(skills.getSkillByName(opponentMove).isEffectiveAgainst(playerMove))
            return 2;
        return 0;
    }

    public void playerSuccessAction(int choice, int opponentChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < player.getCritChance() && choice != 2 && !isDraw && !opponentDodged) {
            player.setDamageSetter(player.getCritMultiplier());
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerText(40,player.getName() + "'s " + playerAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
            System.out.print(GameLogic.reset);
        }

        if (dodgeChance < player.getDodgeChance() && opponentChoice != 2 && !isDraw) {
            playerDodged = true;
        }

        if (opponentDodged) {
            player.setDamageSetter(0);
            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40,opponent.getName() + " dodges " + player.getName() + "'s punch!"));
            System.out.print(GameLogic.reset);
        }

        player.useSkill(playerAttacks[choice]);

        player.setDamageSetter(1);
        opponentDodged = false;
    }

    public void playerFailedAction(String attack) {
        player.setStamina(player.getStamina() - skills.getSkillByName(attack).getStaminaCost());
    }

    public void opponentSuccessAction(int choice, int playerChoice, boolean isDraw) {
        double critChance = rand.nextDouble();
        double dodgeChance = rand.nextDouble();

        if (critChance < opponent.getCritChance() && choice != 2 && !isDraw) {
            opponent.setDamageSetter(opponent.getCritMultiplier());
            System.out.print(GameLogic.redText);
            System.out.print(GameLogic.centerText(40,opponent.getName() + "'s " + opponentAttacks[choice] + " hit the weak spot! CRITICAL HIT!"));
            System.out.print(GameLogic.reset);
        }

        if (dodgeChance < opponent.getDodgeChance() && playerChoice != 2 && !isDraw) {
            opponentDodged = true;
        }

        if (playerDodged) {
            opponent.setDamageSetter(0);
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerText(40,player.getName() + " dodges " + opponent.getName() + "'s punch!"));
            System.out.print(GameLogic.reset);
        }

        opponentPerformAction(opponentAttacks[choice]);

        opponent.setDamageSetter(1);
        playerDodged = false;
    }

    public void drawAction(int choice, int opponentChoice) {
        player.setDamageSetter(0.5);
        playerSuccessAction(choice, opponentChoice, false);
        player.setDamageSetter(1);

        opponent.setDamageSetter(0.5);
        opponentSuccessAction(opponentChoice, choice, false);
        opponent.setDamageSetter(1);
    }

    public void opponentFailedAction(String attack) {
        opponent.setStamina(opponent.getStamina() - skills.getSkillByName(attack).getStaminaCost());
    }

    private void opponentPerformAction(String attack) {
        opponent.useSkill(attack);
        opponent.setDamageSetter(1);
        playerDodged = false;
    }
}
