package world1;

import world1.interfaces.PlayerProgressInterface;

public class PlayerProgress implements PlayerProgressInterface {
    private int round;
    private int shopStage;
    private int done;
    private int opponentWins;
    private int playerWins;
    private int addStats;

    public PlayerProgress(int round, int shopStage, int done) {
        this.round = round;
        this.shopStage = shopStage;
        this.done = done;
        this.playerWins = 0;
        this.opponentWins = 0;
        this.addStats = 0;
    }

    public void setRound(int round){
        this.round = round;
    }

    public int getRound(){
        return round;
    }

    public void setShopStage(int shopStage){
        this.shopStage = shopStage;
    }

    public int getShopStage(){
        return shopStage;
    }

    public void setDone(int done){
        this.done = done;
    }

    public int getDone(){
        return done;
    }
    
    public void setOpponentWins(int wins){
        this.opponentWins = wins;
    }

    public int getOpponentWins(){
        return opponentWins;
    }

    public void setPlayerWins(int wins){
        this.playerWins = wins;
    }

    public int getPlayerWins(){
        return playerWins;
    }

    public void setAddStats(int stats){
        this.addStats = stats;
    }

    public int getAddStats(){
        return addStats;
    }
}
