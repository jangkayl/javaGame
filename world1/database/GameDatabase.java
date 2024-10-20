package world1.database;

import world1.Player;
import world1.PlayerProgress;
import world1.Inventory;

public class GameDatabase {
    private static final String FILE_NAME = "gameData.txt";
    private GameDataManager gameDataManager;

    public GameDatabase() {
        gameDataManager = new GameDataManager();
    }
    
    public GameDataManager getGameDataManager() {
        return gameDataManager;
    }

    public void loadGame() {
        gameDataManager.loadGameData(FILE_NAME);
    }

    public void saveGame() {
        gameDataManager.saveGameData(FILE_NAME);
    }

    public void inputPlayerDetails(Player player) {
        gameDataManager.setPlayer(player);
    }

    public void inputProgress(PlayerProgress playerProgress) {
        gameDataManager.setPlayerProgress(playerProgress);
    }

    public void inputInventory(Inventory arrayList) { 
        gameDataManager.setInventory(arrayList);
    }
}
