package database;

import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.Inventory.Item;

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

    public void inputInventory(Item[] inventoryItems) { 
        gameDataManager.setInventory(inventoryItems);
    }

    public void inputSlots(Item[] slots) { 
        gameDataManager.setSlots(slots);
    }
    
}
