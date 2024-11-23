package world2.SparringOpponents;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparFightLogic;

public class PerezSparring extends SparFightLogic{
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Elbow Strike", "Head Butt", "Low Blow"};

    public PerezSparring(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Perez";
    }

    @Override
    protected void handleWin() {
        winnerRewardPoints();
        System.out.println();
        
        if(getPlayerProgress().getAddStats() < 5){
            getPlayerProgress().setAddStats(getPlayerProgress().getAddStats() + 1);
            System.out.print(GameLogic.centerBox("Congratulations! You've won " + getPlayerProgress().getAddStats() + " / 5 matches", 90));
            System.out.println(); 
            System.out.print(GameLogic.centerText(50,
                    "Here are your choices: ( Select one only )\n" +
                            "1. HP - Increase by +15%\n" +
                            "2. Stamina - Increase by +15%\n" +
                            "3. Crit Chance - Increase by +5%\n" +
                            "4. Dodge Chance - Increase by +5%\n" +
                            "5. Crit Multiplier - Increase by +5%\n" +
                            "\nEnter the number of the stat you'd like to upgrade: "));
            int choice = GameLogic.readInt("", 1, 5);
            addStats(choice);
            System.out.println();
            System.out.print(GameLogic.centerBox("Stats added! Remember, you can gain stats up to 5 times!",70));
            if(getPlayerProgress().getAddStats() == 3){
                getPlayer().setRank(4);
                GameLogic.rankReward();
            }
        }

        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();
    }

    @Override
    protected void handleLoss() {
        resetFighterStats();
        getPlayerProgress().setRound(getPlayerProgress().getRound() + 1);
        System.out.print(GameLogic.centerBox("You have been defeated!\nYou lost 150 points", 70));
        getPlayer().setPlayerPoints(getPlayer().getPlayerPoints() - 150);
        System.out.println();
        System.out.print(GameLogic.centerBox("You now have " + getPlayer().getPlayerPoints() + " points.",70));
        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();
    }
}

