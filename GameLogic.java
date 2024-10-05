import java.util.Scanner;

public class GameLogic{
    static Scanner scan = new Scanner(System.in);   
    static Player player;
    public static boolean isRunning;

    // Read user input
    public static int readInt(String prompt, int choice){
        int input;
        do {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scan.next());
            } catch(Exception e){
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while(input < 1 || input > choice);
        return input;
    }

    // Clear console
    public static void clearConsole(){
        for(int i = 0; i < 50; i++){
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
        System.out.print("Enter anything to continue....");
        scan.nextLine();
    }

    // Starting the game
    public static void startGame(){
        boolean nameSet = false;
        String name;
        clearConsole();
        printSeparator(40);
        printHeading("      FIST OF REDEMPTION\n         RPG BY NWORLD       ");
        printSeparator(40);
        System.out.println();
        pressAnything();
        do {
            clearConsole();
            printHeading("What's your name amigo?");
            System.out.print("-> ");
            name = scan.nextLine();
            printHeading("You are " + name + " right?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int input = readInt("-> ", 2);
            if(input == 1) nameSet = true;
            scan.nextLine();
        } while(!nameSet);
        
        player = new Player(name, 100, 50);

        player.chooseTrait();

        gameLoop();
    }

    // Prints the menu options
    public static void printMenu(){
        clearConsole();
        printHeading("MENU");
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Check your stats");
        System.out.println("(3) Exit the game");
    }

    // Continues players journey
    public static void continueJourney(){
        
    }

    // Checks players stats
    public static void printStats(){
        clearConsole();
        printHeading("Character Stats:");
        System.out.println("");
    }

    // Loops the menu options
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("-> ", 3);
            if(input == 1){
                continueJourney();
            } else if(input == 2){
                printStats();
            } else {
                isRunning = false;
            }
        }
    }

}