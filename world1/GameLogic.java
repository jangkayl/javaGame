package world1;
import java.util.Scanner;

import world1.Inventory.Item;
import world1.TrainInGym.FredGym;
import world1.TrainInGym.PabloUrbanGym;
import world1.TrainInGym.UrbanGym;
import world1.database.GameDataManager;
import world1.database.GameDatabase;

import java.text.DecimalFormat;

public class GameLogic{
    public static Scanner scan = new Scanner(System.in);   
    static DecimalFormat df = new DecimalFormat("#,###.00");   
    public static Player player;
    public static PlayerProgress playerProgress;
    static Item[] inventoryItems;
    static Item[] slots;
    public static Inventory inventory = new Inventory();
    public static Shop shop = new Shop(player, playerProgress);
    static boolean isRunning;
    public static GameDatabase gameData = new GameDatabase();
    static GameDataManager gameDataManager = new GameDataManager();

    // Read user input
    public static int readInt(String prompt, int min, int max){
        int input;
        do {
            System.out.print(prompt);
            try {
                input = scan.nextInt();
            } catch(Exception e){
                input = -1;
                System.out.println("Please enter a valid number of choice!");
                scan.next();
            }
            if(input > max || input < min){
                System.out.println("Please enter valid choice number!");
            }
            scan.nextLine();
        } while(input < min || input > max);
        return input;
    }

    // Clear console
    public static void clearConsole(){
        for(int i = 0; i < 5; i++){
            System.out.println();
        }
    }

    // Add separator -----
    public static void printSeparator(int n){
        for(int i = 0; i < n; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    // Prints heading 
    public static void printHeading(String heading){
        printSeparator(30);
        System.out.println(heading);
        printSeparator(30);
    }

    // Press anything to continue
    public static void pressAnything(){
        System.out.println();
        System.out.print("Enter anything to continue....");
        scan.nextLine();
    }

    public static String formatColumns(String leftText, String rightText, int columnWidth) {
		StringBuilder formattedLine = new StringBuilder();

		formattedLine.append(leftText);
		int spaces = columnWidth - leftText.length();
		formattedLine.append(" ".repeat(Math.max(0, spaces)));

		formattedLine.append(rightText);

		return formattedLine.toString();
	}

    public static String centerText(String text, int totalWidth) {
        if (text == null || text.length() >= totalWidth) {
            return text;
        }

        int padding = (totalWidth - text.length()) / 2;
        StringBuilder centeredText = new StringBuilder();

        centeredText.append(" ".repeat(padding));

        centeredText.append(text);

        while (centeredText.length() < totalWidth) {
            centeredText.append(" ");
        }

        return centeredText.toString();
    }

    // Starting the game
    public static void startGame() {
        boolean nameSet = false;
        String name;

        clearConsole();
        printSeparator(40);
        printHeading("\t\tFIST OF FURY\n     DEVELOPED BY NWORLD");
        printSeparator(40);
        pressAnything();
        System.out.println();
        printSeparator(40);

        while (true) { 
            clearConsole();
            System.out.println("Have you ever played this game?");
            System.out.println("1) Yep!");
            System.out.println("2) Not yet");
            int choice = readInt("-> ", 1, 2);
            
            if (choice == 2) {
                do {
                    clearConsole();
                    printHeading("Enter your name:");
                    System.out.print("-> ");
                    name = scan.nextLine();
                    printHeading("You are " + name + " right?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int input = readInt("-> ", 1, 2);
                    if (input == 1) nameSet = true;
                } while (!nameSet);
    
                player = new Player(name, 100, 50, 0.1, 2.0, 0.1, 0, 0, 0, 0, false);
                playerProgress = new PlayerProgress(1, 0, 0);
                gameData.inputPlayerDetails(player);
                gameData.inputProgress(playerProgress);
                gameData.saveGame();
                break;
            } else {
                gameData.loadGame();
                player = gameData.getGameDataManager().getPlayer();
                playerProgress = gameData.getGameDataManager().getPlayerProgress();
                inventoryItems = gameData.getGameDataManager().getInventory();
                slots = gameData.getGameDataManager().getSlots();
                inventory.setInventoryItems(inventoryItems);
                inventory.setSlots(slots);
                if (player != null) {
                    break; 
                } else {
                    System.out.println();
                    System.out.println("No player found in the database. Please create a new player.");
                    pressAnything();
                }
            }
        }
    
        Story.printIntro(player.getName());
        isRunning = true;
        gameLoop();
    }

    // Prints the menu options
    static void printMenu(){
        gameData.saveGame();
        clearConsole();
        printHeading(centerText("MENU", 30));
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(0) Save progress");
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Check your stats");
        System.out.println("(3) Train in Gym");
        System.out.println("(4) Enter Tournament");
        System.out.println("(5) Inventory");
        System.out.println("(6) Shop");
        System.out.println("(7) Exit the game");
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
    }

    static void printMenu2(){
        gameData.saveGame();
        clearConsole();
        printHeading(centerText("MENU", 30));
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(0) Save progress");
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Check your Stats");
        System.out.println("(3) Tutorial");
        System.out.println("(4) Exit the game");
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
    }

    // Loops the menu options
    static void gameLoop(){
        while(isRunning){
            shop = new Shop(player, playerProgress);
            if(player.getStage() < 2){
                printMenu2();
            } else {
                printMenu();
            }
            int input = readInt("-> ", 0, 7);
            if(input == 0){
                gameData.saveGame();
                pressAnything();
            } else if(input == 1){
                continueJourney();
            } else if(input == 2){
                printStats();
            } else if(input ==3){
                gymTraining();
            } else if(input == 4){
                enterTournament();
            } else if(input == 5) {
                gameData.getGameDataManager().getInventory();
                gameData.getGameDataManager().getSlots();
                inventory.inventoryMenu();
                gameData.inputSlots(inventory.getSlots());
            } else if(input == 6) {
                shop.showShop(false);
                gameData.inputInventory(inventory.getInventoryItems());
            } else if(input == 7) {
                isRunning = false;
            }
        }
    }

    // Continues players journey
    static void continueJourney() {
        clearConsole();
        if(player.getStage() == 0){
            printSeparator(40);
            String[] worlds = player.getWorlds();
            printHeading("   Welcome to the " + worlds[player.getCurrentWorld()]);
            printSeparator(40);
            UrbanStory.printUrban();
            printSeparator(50);
            System.out.println("Are you ready to start your journey?");
            System.out.println("(1) Yes\n(2) No");
            int choice2 = GameLogic.readInt("-> ", 1, 2);
            if(choice2 == 1){
                gymTraining();
            } else {
                return;
            }
        } else {
            gymTraining();
        }
        // System.out.println("You have completed your street training. Time to move to the next level.");
        // player.setCurrentWorld(1); 
        // System.out.println("You are now moving to the Training Facility...");
        // else if(player.getCurrentWorld() == 1) {
        //     printHeading("Training Facility");
        //     System.out.println("You've made it to a proper gym! Time to hone your skills and become stronger.");
        //     System.out.println("You feel your power growing with every session. Keep it up!");
            
        //     System.out.println("You have finished training at the facility. Prepare for the final challenge.");
        //     player.setCurrentWorld(2);
        //     System.out.println("You're now headed to the Champ Arena...");
        // }
        // else if(player.getCurrentWorld() == 2) {
        //     printHeading("Champ Arena");
        //     System.out.println("This is it! You've reached the pinnacle of your journey. Time to prove your worth in the arena.");
        //     System.out.println("Fighters from all over the world await you in the arena. Get ready to face the best.");
        //     System.out.println("Good luck, Champion!");
        // }
        // else {
        //     printHeading("Invalid world state.");
        //     System.out.println("Something went wrong! You are stuck in an unknown world.");
        // }
    }


    // Checks players stats
    static void printStats(){
        clearConsole();
        printHeading(centerText("CHARACTER STATS", 30));
        System.out.print("\t\t");
        printSeparator(10);
        System.out.println(centerText(player.getName(), 28));
        System.out.println(centerText("* " + player.getCurrentRank() + " *", 27));
        System.out.print(centerText(" ", 6));
        printSeparator(15);
        System.out.print(centerText(" ", 8));
        printSeparator(10);
        System.out.println(formatColumns(" HP:",player.getHp() + " / " + player.getMaxHp(), 25));
        printSeparator(35);
        System.out.println(formatColumns(" Stamina:",player.getStamina() + " / " + player.getMaxStamina(), 25));
        printSeparator(35);
        System.out.println(formatColumns(" Critical Chance:", df.format(player.getCritChance() * 100) + "%", 25));
        printSeparator(35);
        System.out.println(formatColumns(" Critical Multiplier:", df.format(player.getCritMultiplier()) + "x", 25));
        printSeparator(35);
        System.out.println(formatColumns(" Dodge Chance:", df.format(player.getDodgeChance() * 100) + "%", 25));
        pressAnything();
    }

    // Enter gym and train with coach
    static void gymTraining(){
        int choice = 0;
        if(player.getCurrentWorld() == 0){
            
            // Train with Fred after losing in the Tournament
            if(playerProgress.getOpponentWins() == 3 && playerProgress.getAddStats() != 5){
                if(UrbanStory.tournaLoseTraining(player.getName())){
                    playerProgress.setRound(1);
                    FredGym.setPlayer(GameLogic.player);
                    FredGym.fightLoop2();
                    return;
                }
                return;
            }

            // Train with Fred
            if(player.getStage() == 0){
                clearConsole();
                // UrbanStory.printTraining(player.getName());
                player.chooseTrait();
                player.setStage(1);
                gameData.saveGame();
            } else {
                clearConsole();
                if(playerProgress.getShopStage() < 1){
                    if(player.getIsLose()){
                        UrbanStory.urbanTrainingLose(player.getName(), UrbanGym.opponent.getName());
                    } else {
                        UrbanStory.urbanTraining6(player.getName());
                        choice = readInt("-> ", 1, 1);
                        if(choice == 1) shop.shop();
                    }
                } else {
                    if(player.getStage() == 1) UrbanStory.urbanTraining8(player.getName());  
                    else if(player.getStage() == 2){
                        PabloUrbanGym.setPlayer(GameLogic.player);
                        PabloUrbanGym.fightLoop2();
                    }
                    if(player.getStage() >= 3){
                        if(playerProgress.getDone() != 1){
                            UrbanStory.inviteToTournament(player.getName());
                        } else {
                            if(playerProgress.getAddStats() == 5){
                                System.out.println("Fred: \t\"You've reached your training limit 5 sessions max! Time to put those skills to the test!\"\n");
                            }
                            System.out.println("Go to tournament and continue fighting your opponent!");
                        }
                        pressAnything();
                    }
                }
            }
        }
    }

    // Enter tournament and fight with your opponents
    public static void enterTournament(){
	    clearConsole();
        Tournament.attemptTournament(player.getStage());
    }

    public static void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(5); 
            } catch (InterruptedException e) {
                System.out.println("Printing interrupted.");
                return;
            }
        }
        System.out.println();
    } 
    
    public static void addPoints(int points){
        int currentPoints = player.getPlayerPoints();
        currentPoints += points;
        player.setPlayerPoints(currentPoints);
    }


}