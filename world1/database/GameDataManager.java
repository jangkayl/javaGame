    package world1.database;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;

    import world1.Inventory;
    import world1.Player;
    import world1.PlayerProgress;
    import world1.Shop;
    import world1.Inventory.Item; 

    public class GameDataManager {
        private Player player;
        private PlayerProgress playerProgress;
        private Item[] inventoryItems;
        private Item[] slots;

        public void setPlayer(Player player){
            this.player = player;
        }

        public void setPlayerProgress(PlayerProgress playerProgress){
            this.playerProgress = playerProgress;
        }

        public void setInventory(Item[] inventoryItems){
            this.inventoryItems = inventoryItems;
        }

        public void setSlots(Item[] slots){
            this.slots = slots;
        }

        public void loadGameData(String fileName) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                String section = "";

                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("[") && line.endsWith("]")) {
                        // Identify section (e.g., [player])
                        section = line.substring(1, line.length() - 1).toLowerCase();
                    } else if (line.contains("=")) {
                        // Split key and value
                        String[] parts = line.split("=");
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        // Populate data based on section
                        switch (section) {
                            case "player":
                                setPlayerData(key, value);
                                break;
                            case "inventory":
                                addInventoryItem(key, value);
                                break;
                            case "progress":
                                addProgress(key, value);
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void setPlayerData(String key, String value) {
            if (player == null) {
                player = new Player("", 0, 0, 0, 0,0, 0, 0, 0, 0, false);
            }

            switch (key) {
                case "name":
                    player.setName(value);
                    break;
                case "hp":
                    player.setHp(Integer.parseInt(value));
                    break;
                case "maxHp":
                    player.setMaxHp(Integer.parseInt(value));
                    break;
                case "stamina":
                    player.setStamina(Integer.parseInt(value));
                    break;
                case "maxStamina":
                    player.setMaxStamina(Integer.parseInt(value));
                    break;
                case "critChance":
                    player.setCritChance(Double.parseDouble(value));
                    break;
                case "critMultiplier":
                    player.setCritMultiplier(Double.parseDouble(value));
                    break;
                case "dodgeChance":
                    player.setDodgeChance(Double.parseDouble(value));
                    break;
                case "playerPoints":
                    player.setPlayerPoints(Integer.parseInt(value));
                    break;
                case "currentWorld":
                    player.setCurrentWorld(Integer.parseInt(value));
                    break;
                case "currentRank":
                    player.setCurrentRank(Integer.parseInt(value));
                    break;
                case "stage":
                    player.setStage(Integer.parseInt(value));
                    break;
                case "isLose":
                    player.setIsLose(Boolean.parseBoolean(value));
                    break;
            }
        }

        private void addProgress(String key, String value) {
            if (playerProgress == null) {
                playerProgress = new PlayerProgress(0, 0);
            }

            switch (key) {
                case "round":
                    playerProgress.setRound(Integer.parseInt(value));
                    break;
                case "shopStage":
                    playerProgress.setShopStage(Integer.parseInt(value));
                    break;
            }
        }

        private void addInventoryItem(String key, String value) {
            if (inventoryItems == null) {
                inventoryItems = new Item[0];
            }

            if (slots == null) {
                slots = new Item[5];
            }

            String name = Shop.getItemNameByIndex(Integer.parseInt(value)); 
            String description = Shop.getItemDescriptionByIndex(Integer.parseInt(value)); 
            String body = Shop.getItemBodyByIndex(Integer.parseInt(value)); 
            String effect = Shop.getItemEffectByIndex(Integer.parseInt(value)); 

            if(name == null && description == null && body == null) return;
            Item newItem = new Item(name, description, body, effect);

            if (key.equals("world1")) {
                Item[] newInventory = new Item[inventoryItems.length + 1];
                for (int i = 0; i < inventoryItems.length; i++) {
                    newInventory[i] = inventoryItems[i];
                }
                newInventory[newInventory.length - 1] = newItem;
                inventoryItems = newInventory;
            } else if (key.equals("HEAD")) {
                slots[0] = newItem;
            } else if (key.equals("BODY")) {
                slots[1] = newItem;
            } else if (key.equals("HAND")) {
                slots[2] = newItem;
            } else if (key.equals("BOOTS")) {
                slots[3] = newItem;
            } else if (key.equals("FOOD")) {
                slots[4] = newItem;
            } 
        }
        
        public void saveGameData(String fileName) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                // Save player data
                bw.write("[player]\n");
                if (player != null) {
                    bw.write("name=" + player.getName() + "\n");
                    bw.write("hp=" + player.getHp() + "\n");
                    bw.write("maxHp=" + player.getMaxHp() + "\n");
                    bw.write("stamina=" + player.getStamina() + "\n");
                    bw.write("maxStamina=" + player.getMaxStamina() + "\n");
                    bw.write("critChance=" + player.getCritChance() + "\n");
                    bw.write("critMultiplier=" + player.getCritMultiplier() + "\n");
                    bw.write("dodgeChance=" + player.getDodgeChance() + "\n");
                    bw.write("playerPoints=" + player.getPlayerPoints() + "\n");
                    bw.write("currentWorld=" + player.getCurrentWorld() + "\n");
                    bw.write("currentRank=" + player.getRank() + "\n");
                    bw.write("stage=" + player.getStage() + "\n");
                    bw.write("isLose=" + player.getIsLose() + "\n");
                }

                bw.write("\n[progress]\n");
                if (playerProgress != null) {
                    bw.write("round=" + playerProgress.getRound() + "\n");
                    bw.write("shopStage=" + playerProgress.getShopStage() + "\n");
                }

                // Save inventory data
                bw.write("\n[inventory]\n");
                if(inventoryItems != null){
                    inventoryItems = Inventory.getInventoryItems();
                    for(int i = 0; i < Inventory.getItemCount(); i++){
                        int index = Shop.getItemIndexByDescription(inventoryItems[i].description);
                        if(!Shop.items[index].isSoldOut()) Shop.items[index].setSoldOut();
                        bw.write("world1=" + index + "\n");
                    }
                }

                if(slots != null){
                    slots = Inventory.getSlots();
                    for (int i = 0; i < slots.length; i++) {
                        if (slots[i] != null) { 
                            int index = Shop.getItemIndexByDescription(slots[i].description);
                            if (index == -1) continue; 
                            else 
                                if(!Shop.items[index].isSoldOut()) Shop.items[index].setSoldOut();
                            switch (i) {
                                case 0:
                                    bw.write("HEAD=" + index + "\n");
                                    break;
                                case 1:
                                    bw.write("BODY=" + index + "\n");
                                    break;
                                case 2:
                                    bw.write("HAND=" + index + "\n");
                                    break;
                                case 3:
                                    bw.write("BOOTS=" + index + "\n");
                                    break;
                                case 4:
                                    bw.write("FOOD=" + index + "\n");
                                    break;
                            }
                        }
                    }

                }
                
                System.out.println();
                System.out.println("Game data saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Getters for accessing the data
        public Player getPlayer() {
            return player;
        }
        
        public PlayerProgress getPlayerProgress() {
            return playerProgress;
        }

        public Item[] getInventory() {
            return inventoryItems;
        }

        public Item[] getSlots() {
            return slots;
        }
    }
