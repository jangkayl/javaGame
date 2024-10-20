package world1;

import world1.interfaces.PlayerProgressInterface;

public class PlayerProgress implements PlayerProgressInterface {
    private int round;
    private int shopStage;

    public PlayerProgress(int round, int shopStage) {
        this.round = round;
        this.shopStage = shopStage;
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
    
}
