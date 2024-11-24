package world1;

import world1.Inventory.Item;
import world1.TrainInGym.CarlitoUrbanGym;
import world1.TrainInGym.FredGym;
import world1.TrainInGym.PabloUrbanGym;
import world1.database.GameDataManager;
import world1.database.GameDatabase;
import world2.GameLogic2;
import world3.ChampTournament;
import world3.GameLogic3;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GameLogic{
    public static Scanner scan = new Scanner(System.in);   
    public static DecimalFormat df = new DecimalFormat("#,###.00");   
    public static Player player;
    public static String redText = "\u001B[31m";
    public static String greenText = "\u001B[32m";
    public static String reset = "\u001B[0m";
    public static String yellowText = "\u001B[33m";
    public static PlayerProgress playerProgress;
    private static Item[] inventoryItems;
    private static Item[] slots;
    public static Inventory inventory = new Inventory();
    public static Shop shop = new Shop(player, playerProgress);
    public static boolean isRunning;
    public static GameDatabase gameData = new GameDatabase();
    public static GameDataManager gameDataManager = new GameDataManager();

    // Read user input
    public static int readInt(String prompt, int min, int max){
        int input;
        do {
            System.out.print(prompt);
            try {
                input = scan.nextInt();
            } catch(Exception e){
                input = -1;
                System.out.println(centerBox("Please enter a valid number of choice!", 50));
                scan.next();
            }
            if(input > max || input < min){
                System.out.println(centerBox("Please enter valid choice number!", 50));
            }
            scan.nextLine();
        } while(input < min || input > max);
        return input;
    }

    // Read user string input
    public static String readString(String prompt){
        String input = "";
        do {
            System.out.print(prompt);
            try {
                input = scan.nextLine();
            } catch(Exception e){
                System.out.println(GameLogic.centerBox("Please enter a valid input!", 50));
                scan.next();
            }
        } while(input == "" || input == null);
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
        int terminalWidth = 200;

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
        int terminalWidth = 197;

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
        int terminalWidth = 200;
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
        gameLogo();
        pressAnything();
        System.out.println();

        while (true) { 
            clearConsole();
            System.out.print(centerText(20,"Have you ever played this game?"));
            System.out.print(centerText(20,"1) Yep!"));
            System.out.print(centerText(20,"2) Not yet"));
            int choice = readInt(centerText("", 97) + "-> ", 1, 2);
            
            if (choice == 2) {
                do {
                    clearConsole();
                    System.out.println(centerBox("Enter your name:", 50));
                    name = GameLogic.readString(GameLogic.centerText("", 97) + "-> ");
                    System.out.println();
                    System.out.print(centerText(20,"You are " + name + " right?"));
                    System.out.print(centerText(20,"(1) Yes"));
                    System.out.print(centerText(20,"(2) No "));
                    int input = readInt(centerText("", 97) + "-> ", 1, 2);
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
                    System.out.println(centerBox("No player found in the database. Please create a new player.", 70));
                    pressAnything();
                }
            }
        }
    
        Story.printIntro(player.getName());
        isRunning = true;
        
        if(player.getCurrentWorld() == 1){
            GameLogic2.gameLoop();
        } else if(player.getCurrentWorld() == 2){
            GameLogic3.gameLoop();
        } else if(player.getCurrentWorld() == 3){
            ChampTournament champTour = new ChampTournament();
            champTour.gameEndingLogo();
        } else {
            gameLoop();
        }
    }

    // Prints the menu options
    static void printMenu(){
        gameData.saveGame();
        clearConsole();
        System.out.print(greenText);
        System.out.println(centerBox("MENU", 30));
        System.out.print(reset);
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
                input = readInt(centerText("", 97) + "-> ", 0, 5);
            } else {
                input = readInt(centerText("", 97) + "-> ", 0, 3);
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
                    System.out.print(greenText);
                    System.out.print(centerBox("Welcome to the " + worlds[player.getCurrentWorld()], 100));
                    System.out.print(reset);
                    UrbanStory.printUrban();
                    System.out.print(centerText(20,"Are you ready to start your journey?"));
                    System.out.print(centerText(20,"(1) Yes"));
                    System.out.print(centerText(20,"(2) No "));
                    int choice2 = readInt(centerText("", 97) + "-> ", 1, 2);
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
                    System.out.print(centerBox("MENU", 30));
                    System.out.println();
                    System.out.print(centerText(20,"(1) Train with Fred"));
                    System.out.print(centerText(20,"(2) Enter Tournament"));
                    System.out.print(centerText(20,"(3) Go Back"));
                    int choice2 = readInt(centerText("", 97) + "-> ", 1, 3);
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
        System.out.print(greenText);
        String statsOutput =
                        centerText("CHARACTER STATS", 50) + "\n" +
                        printCenteredSeparator(30) + "\n" +
                        centerText(player.getName(), 30) + "\n" +
                        centerText("* " + player.getRank() + " *",30) + "\n" +
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
        System.out.print(reset);
        pressAnything();
    }

    // Enter gym and train with coach
    static void gymTraining(){
        int choice = 0;
        while(true){
            if(player.getCurrentWorld() == 0){

                // Train with Fred after losing in the Tournament
                if(playerProgress.getOpponentWins() == 3 && playerProgress.getAddStats() != 5){
                    if(player.getIsLose() == false){
                        FredGym fred = new FredGym(player);
                        fred.setPlayer(player);
                        fred.fightLoop();
                        clearConsole();
                        System.out.print(centerBox("Fred: \"Want to train more to gain more stats?\"",50));
                        System.out.println();
                        System.out.print(centerText(50,"(1) Sure, lets go for another round!"));
                        System.out.print(centerText(50,"(2) I'll take a break first."));
                        choice = readInt(centerText("", 97) + "-> ", 1, 2);
                        if(choice == 1 && playerProgress.getAddStats() == 5){
                            System.out.print(centerBox("Fred: \"You've reached your training limit 5 sessions max! Time to put those skills to the test!\"\n",110));
                            System.out.println("\n");
                            pressAnything();
                        }
                        if(choice == 2) break;
                        continue;
                    } else if(UrbanStory.tournaLoseTraining(player.getName())){
                        while(playerProgress.getAddStats() != 5){
                            FredGym fred = new FredGym(player);
                            fred.setPlayer(player);
                            fred.fightLoop();
                            clearConsole();
                            System.out.print(centerBox("Fred: \"Want to train more to gain more stats?\"",50));
                            System.out.println();
                            System.out.print(centerText(50,"(1) Sure, lets go for another round!"));
                            System.out.print(centerText(50,"(2) I'll take a break first."));
                            choice = readInt(centerText("", 97) + "-> ", 1, 2);
                            if(choice == 1 && playerProgress.getAddStats() == 5){
                                System.out.print(centerBox("\nFred: \"You've reached your training limit 5 sessions max! Time to put those skills to the test!\"\n",110));
                                System.out.println("\n");
                                pressAnything();
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
                            if(!UrbanStory.urbanTrainingLose(player.getName(), carlito.getOpponentName())){
                                break;
                            }
                        } else {
                            UrbanStory.urbanTraining6(player.getName());
                            choice = readInt(centerText("", 97) + "-> ", 1, 1);
                            if(choice == 1) shop.shop();
                        }
                    } else {
                        if(player.getStage() == 1){ 
                            if(player.getIsLose()){
                                PabloUrbanGym pablo = new PabloUrbanGym(player);
                                if(!UrbanStory.urbanTrainingLose(player.getName(), pablo.getOpponentName())){
                                    break;
                                }
                            } else {
                                UrbanStory.urbanTraining8(player.getName());    
                            }
                        }
                        else if(player.getStage() == 2){
                            PabloUrbanGym pablo = new PabloUrbanGym(player);
                            player.setStage(2);
                            pablo.setPlayer(player);
                            pablo.fightLoop();
                        }
                        if(player.getStage() >= 3){
                            if(playerProgress.getDone() != 1){
                                if(!UrbanStory.inviteToTournament(player.getName())){
                                    continue;
                                }
                            } else {
                                if(playerProgress.getAddStats() == 5){
                                    System.out.print(centerBox("Fred: \"You've reached your training limit 5 sessions max! Time to put those skills to the test!\"",110));
                                    System.out.println("\n");
                                    pressAnything();
                                    return;
                                }
                                System.out.print(centerBox("Go to tournament and continue fighting your opponent!",100));
                            }
                            pressAnything();
                        }
                        return;
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

    public static void rankReward() {
        System.out.println();
        String prompt1;
        rankNotif();
        System.out.println();
        System.out.print(centerText(50,
                "Here are your choices: ( Select one only )\n" +
                        "1. HP - Increase by +10%\n" +
                        "2. Stamina - Increase by +10%\n" +
                        "3. Crit Chance - Increase by +5%\n" +
                        "4. Dodge Chance - Increase by +5%\n" +
                        "5. Crit Multiplier - Increase by +5%\n" +
                        "\nEnter the number of the stat you'd like to upgrade: "));
        int choice = readInt(centerText("", 97) + "-> ", 1, 5);

        addStats(choice);

        switch (choice) {
            case 1:
                prompt1 = "Your HP has been increased by 10%!";
                break;
            case 2:
                prompt1 = "Your Stamina has been increased by 10%!";
                break;
            case 3:
                prompt1 = "Your Critical Hit Chance has been increased by 5%!";
                break;
            case 4:
                prompt1 = "Your Crit Multiplier has been increased by 5%!";
                break;
            case 5:
                prompt1 = "Your Dodge Chance has been increased by 5%!";
                break;
            default:
                prompt1 = "Invalid choice. No stat was upgraded.";
                break;
        }

        System.out.println(); 
        System.out.print(centerBox(prompt1, 97));
        printCenteredSeparator(50);
        pressAnything();
        gameData.saveGame();
    }

    public static void rankNotif(){
        String congrats = "Congratulations! You've ranked UP!\n"+
                            "You are now a " + player.getRank();
        System.out.print(centerBox(congrats, 90));
    }

    private static void addStats(int choice){
        if(choice == 1){
            double hpMultiplier = 1 + 0.10;
            int maxHp = (int)Math.ceil(player.getMaxHp() * hpMultiplier);
            player.setHp(maxHp);
            player.setMaxHp(maxHp);
        } else if(choice == 2){
            double staminaMultiplier = 1 + 0.10;
            int maxStamina = (int)Math.ceil(player.getMaxStamina() * staminaMultiplier);
            player.setStamina(maxStamina);
            player.setMaxStamina(maxStamina);
        } else if(choice == 3){
            double newCrit = player.getCritChance() + 0.05;
            player.setCritChance(newCrit);
        } else if(choice == 4){
            double newDodge = player.getDodgeChance() + 0.05;
            player.setDodgeChance(newDodge);
        } else if(choice == 5){
            double newMulti = player.getCritMultiplier() + 0.05;
            player.setCritMultiplier(newMulti);
        }
    }

    public static void gameLogo(){
        String asciiBorder = redText + centerText(100,
                                "                                    \n" +
                                " ⠀⠀⠀⠀⠀⠀⠀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⣠⣶⣿⣿⣿⡿⠓⢀⣠⣴⣶⣿⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⢀⣼⣿⣿⣿⠟⠋⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                                "⠀⠀⣾⣿⣿⣿⣇⣠⣾⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⠇⢰⣶⣶⣤⣀⠀⠀⠀⠀\n" +
                                "⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢀⣼⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⣿⣿⣷⡄⠀⠀\n" +
                                "⠀⢸⣿⣿⠛⣿⣿⣿⣿⣿⡟⢠⣾⣿⣿⣿⣿⣿⡟⢀⣾⣿⣿⣿⣿⣿⣿⣿⡄⠀\n" +
                                "⠀⠀⠙⠋⠀⣿⣿⣿⣿⣿⠃⢸⣿⣿⣿⣿⡿⠋⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀\n" +
                                "⠀⠀⠀⠀⠀⢻⣿⣿⣿⡿⠀⠘⠿⠿⠟⠋⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠙⢿⣿⡇⢸⣷⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠙⠇⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀\n" +
                                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⠀⠀⠀\n" +
                                "                  ⠉⠙⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀\n"
                        );


        String mainAscii = centerText(100,
                                "███████╗██╗███████╗████████╗     ██████╗ ███████╗    ███████╗██╗   ██╗██████╗ ██╗   ██╗\n" +
                                "██╔════╝██║██╔════╝╚══██╔══╝    ██╔═══██╗██╔════╝    ██╔════╝██║   ██║██╔══██╗╚██╗ ██╔╝\n" +
                                "█████╗  ██║███████╗   ██║       ██║   ██║█████╗      █████╗  ██║   ██║██████╔╝ ╚████╔╝ \n" +
                                "██╔══╝  ██║╚════██║   ██║       ██║   ██║██╔══╝      ██╔══╝  ██║   ██║██╔══██╗  ╚██╔╝  \n" +
                                "██║     ██║███████║   ██║       ╚██████╔╝██║         ██║     ╚██████╔╝██║  ██║   ██║   \n" +
                                "╚═╝     ╚═╝╚══════╝   ╚═╝        ╚═════╝ ╚═╝         ╚═╝      ╚═════╝ ╚═╝  ╚═╝   ╚═╝   \n" +
                                "                                                                                      "
                        );
        
        String text = centerText(100, 
                             "╔╗ ╦ ╦  ╔╗╔╦ ╦╔═╗╦═╗╦  ╔╦╗\n" +
                             "╠╩╗╚╦╝  ║║║║║║║ ║╠╦╝║   ║║\n" +
                             "╚═╝ ╩   ╝╚╝╚╩╝╚═╝╩╚═╩═╝═╩╝" ) + reset;


        System.out.println(asciiBorder);
        System.out.println(mainAscii);
        System.out.println(text);
    }
}