package world1.TrainInGym;

import world1.GameLogic;
import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.StreetFighter;

public class PabloUrbanGym extends PlayerVsOpponent2{
    private static PlayerProgress playerProgress = GameLogic.playerProgress;
    private static Player player;
    private static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Jab to the Body", "Lead Hook", "Rear Uppercut"};
    private static StreetFighter opponent = new StreetFighter("Pablo Martinez", 130, 70, 0.2, 2, .30, 2);
    
    public PabloUrbanGym(Player player) {
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
        player.setIsLose(false);
        player.setRank(2);
        GameLogic.rankNotif();
        winnerReward();
        resetFighterStats();
        playerProgress.setRound(1);  
        playerProgress.setShopStage(3);  
        player.setStage(3);
    }
    
    @Override
    protected void handleLoss() {
        System.out.println();
        System.out.print(GameLogic.redText);
        System.out.println(GameLogic.centerBox(player.getName() + " is knocked out! " + opponent.getName() + " wins!",60));
        System.out.print(GameLogic.reset);
        player.setIsLose(true);
        playerProgress.setRound(playerProgress.getRound() + 1);
        resetFighterStats();
        GameLogic.pressAnything();
    }

    @Override
    protected void winnerReward() {
        player.setPlayerPoints(player.getPlayerPoints() + 125);
        String message = "Congratulations! You've won the match!\n\n" +
                "Fred is giving you another 125 points.\n\n" +
                "You now have " + player.getPlayerPoints() + " points.\n\n" +
                "Visit the shop and use your points to buy items.\n";

        System.out.print(GameLogic.greenText);
        System.out.println(GameLogic.centerBox(message, 90));
        System.out.print(GameLogic.reset);
        GameLogic.pressAnything();
    }
}