package world1.interfaces;

public interface PlayerInterface {
    void chooseTrait();
    int getCurrentWorld();
    void setCurrentWorld(int currentWorld);
    String[] getWorlds();
    void setPlayerPoints(int playerPoints);
    int getPlayerPoints();
    void addPlayerPoints(int points);
    int getStage();
    void setStage(int stage);
    boolean getIsLose();
    void setIsLose(boolean isLose);
}
