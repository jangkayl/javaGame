package world1;

import world1.Inventory.Item;
import world1.TrainInGym.CarlitoUrbanGym;
import world1.TrainInGym.FredGym;
import world1.TrainInGym.PabloUrbanGym;
import world1.database.GameDataManager;
import world1.database.GameDatabase;
import world2.GameLogic2;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GameLogic{
    public static Scanner scan = new Scanner(System.in);   
    public static DecimalFormat df = new DecimalFormat("#,###.00");   
    public static Player player;
    public static PlayerProgress playerProgress;
    private static Item[] inventoryItems;
    private static Item[] slots;
    public static Inventory inventory = new Inventory();
    public static Shop shop = new Shop(player, playerProgress);
    public static boolean isRunning;
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

    public static String leftBox(String text, int boxWidth) {
        String boxPad = "";
        String horizontalBorder = "\u2554" + new String(new char[boxWidth]).replace("\0", "\u2550") + "\u2557";

        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();

        sb.append(boxPad).append(horizontalBorder).append("\n");

        for (String line : lines) {
            int paddingSizeText = (boxWidth - line.length()) / 2;
            String paddingText = new String(new char[Math.max(0, paddingSizeText)]).replace("\0", " ");
            sb.append(boxPad).append("\u2551").append(paddingText).append(line).append(paddingText);
            if ((line.length() % 2) != (boxWidth % 2)) {
                sb.append(" ");
            }
            sb.append("\u2551").append("\n");
        }
        sb.append(boxPad).append("\u255A").append(new String(new char[boxWidth]).replace("\0", "\u2550")).append("\u255D");
        return sb.toString();
    }

    public static String rightBox(String text, int boxWidth) {
        int terminalWidth = 150;

        int boxPadding = terminalWidth - boxWidth;
        String boxPad = new String(new char[boxPadding]).replace("\0", " ");
        String horizontalBorder = "\u2554" + new String(new char[boxWidth]).replace("\0", "\u2550") + "\u2557";

        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();

        sb.append(boxPad).append(horizontalBorder).append("\n");
        for (String line : lines) {
            int paddingSizeText = (boxWidth - line.length()) / 2;
            String paddingText = new String(new char[Math.max(0, paddingSizeText)]).replace("\0", " ");
            sb.append(boxPad).append("\u2551").append(paddingText).append(line).append(paddingText);

            if ((line.length() % 2) != (boxWidth % 2)) {
                sb.append(" ");
            }
            sb.append("\u2551").append("\n");
        }
        sb.append(boxPad).append("\u255A").append(new String(new char[boxWidth]).replace("\0", "\u2550")).append("\u255D");
        return sb.toString();
    }

    public static String centerBox(String text, int width) {
        int boxWidth = width;
        int terminalWidth = 150;

        int boxPadding = (terminalWidth - boxWidth) / 2;
        String boxPad = new String(new char[boxPadding]).replace("\0", " ");

        // Using thick box-drawing characters
        String horizontalBorder = "\u2554" + new String(new char[boxWidth]).replace("\0", "\u2550") + "\u2557";

        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();

        sb.append(boxPad).append(horizontalBorder).append("\n");

        for (String line : lines) {
            int paddingSizeText = (boxWidth - line.length()) / 2;
            String paddingText = new String(new char[Math.max(0, paddingSizeText)]).replace("\0", " ");
            sb.append(boxPad).append("\u2551").append(paddingText).append(line).append(paddingText);

            // Adjust padding if line length and box width have different parity
            if ((line.length() % 2) != (boxWidth % 2)) {
                sb.append(" ");
            }
            sb.append("\u2551").append("\n");
        }
        sb.append(boxPad).append("\u255A").append(new String(new char[boxWidth]).replace("\0", "\u2550")).append("\u255D");

        return sb.toString();
    }

    public static String formatColumns(String leftText, String rightText, int columnWidth) {
		StringBuilder formattedLine = new StringBuilder();

		formattedLine.append(leftText);
		int spaces = columnWidth - leftText.length();
		formattedLine.append(" ".repeat(Math.max(0, spaces)));

		formattedLine.append(rightText);

		return formattedLine.toString();
	}

    public static String centerText(int size, String text) {
        int terminalWidth = 150;
        StringBuilder sb = new StringBuilder();

        for (String line : text.split("\n")) {
            int paddingSizeText = (terminalWidth - line.length()) / 2;
            String paddingText = new String(new char[Math.max(0, paddingSizeText)]).replace("\0", " ");
            sb.append(paddingText).append(line).append("\n");
        }

        return sb.toString();
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

    // Add centered separator -----
    public static String printCenteredSeparator(int width) {
        StringBuilder separator = new StringBuilder();
        int terminalWidth = 40;
        int paddingSize = (terminalWidth - width) / 6;
        String padding = new String(new char[Math.max(0, paddingSize)]).replace("\0", " ");

        for (int i = 0; i < width; i++) {
            separator.append("-");
        }

        return padding + separator.toString(); // Center the separator
    }

    // Starting the game
    public static void startGame() {
        boolean nameSet = false;
        String name;

        clearConsole();
        System.out.println(centerBox("FIST OF FURY\nDEVELOPED BY NWORLD",100));
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
    
                player = new Player(name, 100, 50, 0.1, 2.0, 0.1, 0, 0, 0, 0, false, 0);
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
        
        if(player.getCurrentWorld() == 1){
            GameLogic2.gameLoop();
        } else {
            gameLoop();
        }
    }

    // Prints the menu options
    static void printMenu(){
        gameData.saveGame();
        clearConsole();
        System.out.println(centerBox("MENU", 30));
        System.out.print(centerText(20, "Choose an action:"));
        System.out.print(centerText(20, "(0) Exit Game"));
        System.out.print(centerText(20, "(1) Continue on your journey"));
        System.out.print(centerText(20, "(2) Check your Stats"));
        System.out.print(centerText(20, "(3) Tutorial"));
        if(playerProgress.getShopStage() > 0){
            System.out.println(centerText(20, "(4) Inventory"));
            System.out.println(centerText(20, "(5) Shop"));
        }
        player.setHp(player.getMaxHp());
        player.setStamina(player.getMaxStamina());
    }

    // Loops the menu options
    static void gameLoop(){
        int input;
        while(isRunning){
            shop = new Shop(player, playerProgress);
            printMenu();
            if(playerProgress.getShopStage() > 0){
                input = GameLogic.readInt("-> ", 0, 5);
            } else {
                input = GameLogic.readInt("-> ", 0, 3);
            }
            if(input == 0){
                isRunning = false;
            } else if(input == 1){
                continueJourney();
            } else if(input == 2){
                printStats();
            } else if(input == 3){
                UrbanStory.tutorialMenu();
            } else if(playerProgress.getShopStage() > 0){
                if(input == 4){
                    gameData.getGameDataManager().getInventory();
                    gameData.getGameDataManager().getSlots();
                    inventory.inventoryMenu();
                    gameData.inputSlots(inventory.getSlots());
                } else if(input == 5){
                    shop.showShop(false);
                    gameData.inputInventory(inventory.getInventoryItems());
                }
            }
        }
    }

    // Continues players journey
    static void continueJourney() {
        clearConsole();
        if(player.getCurrentWorld() == 0){
            if(playerProgress.getShopStage() == 0){
                if(player.getStage() == 0){
                    String[] worlds = player.getWorlds();
                    System.out.print(centerBox("Welcome to the " + worlds[player.getCurrentWorld()], 100));
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
            } else {
                while(true){
                    clearConsole();
                    System.out.println("(1) Train with Fred");
                    System.out.println("(2) Enter Tournament");
                    System.out.println("(3) Go Back");
                    int choice2 = GameLogic.readInt("-> ", 1, 3);
                    if(choice2 == 1){
                        gymTraining();
                    } else if(choice2 == 2) {
                        enterTournament();
                    } else {
                        return;
                    }
                }
            }
        }  else if(player.getCurrentWorld() == 1) {
            GameLogic2.gameLoop();
        }
    }


    // Checks players stats
    public static void printStats(){
        clearConsole();
        String statsOutput =
                        centerText("CHARACTER STATS", 50) + "\n" +
                        printCenteredSeparator(30) + "\n" +
                        centerText(player.getName(), 30) + "\n" +
                        centerText("* " + player.getCurrentRank() + " *",30) + "\n" +
                        printCenteredSeparator(30) + "\n" + "\n" +

                        formatColumns("HP:", player.getHp() + " / " + player.getMaxHp(), 27) + "\n" +
                        printCenteredSeparator(45) + "\n" +
                        formatColumns("Stamina:", player.getStamina() + " / " + player.getMaxStamina(), 28) + "\n" +
                        printCenteredSeparator(45) + "\n" +
                        formatColumns("Critical Chance:", df.format(player.getCritChance() * 100) + "%", 29) + "\n" +
                        printCenteredSeparator(45) + "\n" +
                        formatColumns("Critical Multiplier:", df.format(player.getCritMultiplier()) + "x", 30) + "\n" +
                        printCenteredSeparator(45) + "\n" +
                        formatColumns("Dodge Chance:", df.format(player.getDodgeChance() * 100) + "%", 29) + "\n";

        String centeredBox = centerBox(statsOutput, 50);
        System.out.println(centeredBox);
        pressAnything();
    }

    // Enter gym and train with coach
    static void gymTraining(){
        int choice = 0;
        if(player.getCurrentWorld() == 0){
            
            // Train with Fred after losing in the Tournament
            if(playerProgress.getOpponentWins() == 3 && playerProgress.getAddStats() != 5){
                if(UrbanStory.tournaLoseTraining(player.getName())){
                    while(playerProgress.getAddStats() != 5){
                        FredGym fred = new FredGym(player);
                        fred.setPlayer(GameLogic.player);
                        fred.fightLoop2();
                        clearConsole();
                        System.out.println("Fred: \t\"Want to train more to gain more stats?\"");
                        System.out.println();
                        System.out.println("(1) Sure, lets go for another round!");
                        System.out.println("(2) I'll take a break first.");
                        choice = readInt("-> ", 1, 2);
                        if(choice == 1 && playerProgress.getAddStats() == 5){
                            System.out.println();
                            System.out.println("\nFred: \t\"You've reached your training limit 5 sessions max! Time to put those skills to the test!\"\n");
                            GameLogic.pressAnything();
                        }
                        if(choice == 2) break;
                        continue;
                    }
                }
                return;
            }

            // Train with Fred
            if(player.getStage() == 0){
                clearConsole();
                UrbanStory.printTraining(player.getName());
                player.chooseTrait();
                player.setStage(1);
            } else {
                clearConsole();
                if(playerProgress.getShopStage() < 1){
                    if(player.getIsLose()){
                        CarlitoUrbanGym carlito = new CarlitoUrbanGym(player);
                        UrbanStory.urbanTrainingLose(player.getName(), carlito.getOpponentName());
                    } else {
                        UrbanStory.urbanTraining6(player.getName());
                        choice = readInt("-> ", 1, 1);
                        if(choice == 1) shop.shop();
                    }
                } else {
                    if(player.getStage() == 1) UrbanStory.urbanTraining8(player.getName());  
                    else if(player.getStage() == 2){
                        player.setStage(2);
                        PabloUrbanGym pablo = new PabloUrbanGym(player);
                        pablo.setPlayer(GameLogic.player);
                        pablo.fightLoop();
                    }
                    if(player.getStage() >= 3){
                        if(playerProgress.getDone() != 1){
                            UrbanStory.inviteToTournament(player.getName());
                        } else {
                            if(playerProgress.getAddStats() == 5){
                                System.out.println("Fred: \t\"You've reached your training limit 5 sessions max! Time to put those skills to the test!\"");
                                pressAnything();
                                return;
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