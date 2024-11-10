package world1.TrainInGym;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;

public class FredGym extends PlayerVsOpponent2{
    private static PlayerProgress playerProgress = GameLogic.playerProgress;
    private static Player player;
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Jab to the Body", "Lead Hook", "Rear Uppercut"};
    private static StreetFighter opponent = new StreetFighter("Fred", 130, 70, 0.2, 2, .30, 2);
    
    public FredGym(Player player) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Martinez";
    }

    public void setPlayer(Player p) {
        player = p;
    }

    @Override
    protected void handleWin() {
        System.out.println();
        System.out.println(GameLogic.centerBox(opponent.getName() + " is knocked out! " + player.getName() + " wins!",60));
        winnerReward();
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
        playerProgress.setRound(1);  
        playerProgress.setShopStage(3);  
        GameLogic.gameData.saveGame();
    }
    
    @Override
    protected void handleLoss() {
        System.out.println();
        System.out.println(GameLogic.centerBox(player.getName() + " is knocked out! " + opponent.getName() + " wins!",60));
        playerProgress.setRound(playerProgress.getRound() + 1);
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        GameLogic.pressAnything();
    }

    @Override
    protected void winnerReward() {
        System.out.println(); 
        GameLogic.printSeparator(40);
        System.out.println(); 
        playerProgress.setAddStats(playerProgress.getAddStats() + 1);
        System.out.println("Congratulations! You've won " + playerProgress.getAddStats() + " / 5 matches");
        System.out.println();  
        System.out.println("Fred: \t\"Great job! Now, choose what stats you want to upgrade.\"");
        
        System.out.println("\nHere are your choices: ( Select one only )");
        System.out.println("1. HP - Increase by +20% ");
        System.out.println("2. Stamina - Increase by +20%");
        System.out.println("3. Crit Chance - Increase by +10%");
        System.out.println("4. Dodge Chance - Increase by +10%");
        System.out.println("5. Crit Multiplier - Increase by +10%");
        System.out.print("\nEnter the number of the stat you'd like to upgrade: ");
        int choice = GameLogic.readInt("", 1, 5);
        addStats(choice);
        System.out.println();
        System.out.println("Fred: \"Stats added! Remember, you can train up to 5 times!\"");
        GameLogic.printCenteredSeparator(50);
    }

    private void addStats(int choice){
        if(choice == 1){
            double hpMultiplier = 1 + 0.20;
            int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
            player.setHp(maxHp);
            player.setMaxHp(maxHp);
        } else if(choice == 2){
            double staminaMultiplier = 1 + 0.20;
            int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
            player.setStamina(maxStamina);
            player.setMaxStamina(maxStamina);
        } else if(choice == 3){
            double newCrit = player.getCritChance() + 0.10;
            player.setCritChance(newCrit);
        } else if(choice == 4){
            double newDodge = player.getDodgeChance() + 0.10;
            player.setDodgeChance(newDodge);
        } else if(choice == 5){
            double newMulti = player.getCritMultiplier() + 0.10;
            player.setCritMultiplier(newMulti);
        }
    }
}
