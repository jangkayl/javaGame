package world1.TrainInGym;

import world1.GameLogic;
import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.StreetFighter;

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
        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox(opponent.getName() + " is knocked out! " + player.getName() + " wins!",60));
        System.out.print(GameLogic.reset);
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
        System.out.print(GameLogic.redText);
        System.out.println(GameLogic.centerBox(player.getName() + " is knocked out! " + opponent.getName() + " wins!",60));
        System.out.print(GameLogic.reset);
        playerProgress.setRound(playerProgress.getRound() + 1);
        opponent.setHp(opponent.getMaxHp());
        opponent.setStamina(opponent.getMaxStamina());
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
        GameLogic.pressAnything();
    }

    @Override
    protected void winnerReward() {
        playerProgress.setAddStats(playerProgress.getAddStats() + 1);
        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox("* Congratulations! You've won " + playerProgress.getAddStats() + " / 5 matches. *\n\n" +
                        "Fred: \"Great job! Now, choose what stats you want to upgrade.\"",90));
        System.out.print(GameLogic.reset);

        System.out.print(GameLogic.orangeText);
        System.out.print(GameLogic.centerText(50,
                "Here are your choices: ( Select one only )\n" +
                        "1. HP - Increase by +10%\n" +
                        "2. Stamina - Increase by +10%\n" +
                        "3. Crit Chance - Increase by +5%\n" +
                        "4. Dodge Chance - Increase by +5%\n" +
                        "5. Crit Multiplier - Increase by +5%\n" +
                        "\nEnter the number of the stat you'd like to upgrade: "));

        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 5);
        addStats(choice);
        System.out.print(GameLogic.reset);
        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox("Fred: \"Stats added! Remember, you can train up to 5 times!\"",70));
        System.out.print(GameLogic.reset);
        GameLogic.printCenteredSeparator(50);
        GameLogic.pressAnything();
    }

    private void addStats(int choice){
        if(choice == 1){
            double hpMultiplier = 1 + 0.10;
            int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
            player.setHp(maxHp);
            player.setMaxHp(maxHp);
        } else if(choice == 2){
            double staminaMultiplier = 1 + 0.10;
            int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
            player.setStamina(maxStamina);
            player.setMaxStamina(maxStamina);
        } else if(choice == 3){
            double newCrit = player.getCritChance() + 0.05;
            player.setCritChance(newCrit);
        } else if(choice == 4){
            double newDodge = player.getDodgeChance() + 0.05;
            player.setDodgeChance(newDodge);
        } else if(choice == 5){
            double newMulti = player.getCritMultiplier() + 0.05;
            player.setCritMultiplier(newMulti);
        }
    }
}
