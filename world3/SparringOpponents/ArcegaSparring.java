package world3.SparringOpponents;

import world1.GameLogic;
import GlobalClasses.Player;
import GlobalClasses.StreetFighter;
import world3.SparFightLogic;

public class ArcegaSparring extends SparFightLogic{
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Right Uppercut", "Left Hook", "Right Cross", "Flow State", "Adrenaline Rush", "Sixth Sense"};

    public ArcegaSparring(Player player, StreetFighter opponent) {
        super(player, opponentAttacks, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Arcega";
    }

    @Override
    protected void handleWin() {
        winnerRewardPoints();
        System.out.println(); 
        
        if(getPlayerProgress().getAddStats() < 5){
            getPlayer().setStage(19);   
            System.out.println(); 
            getPlayerProgress().setAddStats(getPlayerProgress().getAddStats() + 1);
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerBox("Congratulations! You've won " + getPlayerProgress().getAddStats() + " / 5 matches", 90));
            System.out.println(); 
            System.out.print(GameLogic.orangeText);
            System.out.print(GameLogic.centerText(50,
                    "Here are your choices: ( Select one only )\n" +
                            "1. HP - Increase by +15%\n" +
                            "2. Stamina - Increase by +15%\n" +
                            "3. Crit Chance - Increase by +5%\n" +
                            "4. Dodge Chance - Increase by +5%\n" +
                            "5. Crit Multiplier - Increase by +5%\n" +
                            "\nEnter the number of the stat you'd like to upgrade: "));
            int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 5);
            System.out.print(GameLogic.reset);
            addStats(choice);
            System.out.println();
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerBox("Stats added! Remember, you can gain stats up to 5 times!",70));
            System.out.print(GameLogic.reset);
            GameLogic.printCenteredSeparator(50);
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
        System.out.println(); 
        System.out.print(GameLogic.redText);
        System.out.print(GameLogic.centerBox("You have been defeated!", 70));
        System.out.print(GameLogic.reset);
        System.out.println();
        GameLogic.pressAnything();
        GameLogic.gameData.saveGame();
    }
}

