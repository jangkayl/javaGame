package world1;

import world1.interfaces.PlayerProgressInterface;

public class PlayerProgress implements PlayerProgressInterface {
    private int round;

    public PlayerProgress(int round) {
        this.round = round;
    }

    public void setRound(int round){
        this.round = round;
    }

    public int getRound(){
        return round;
    }
    
}
