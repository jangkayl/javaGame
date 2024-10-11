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
                input = scan.nextInt();
            } catch(Exception e){
                input = -1;
                System.out.println("Please enter a valid number of choice!");
                scan.next();
            }
            if(input > choice){
                System.out.println("Please enter valid choice number!");
            }
        } while(input < 1 || input > choice);
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

    // Starting the game
    public static void startGame(){
        boolean nameSet = false;
        String name;
        clearConsole();
        printSeparator(40);
        printHeading("\tFIST OF REDEMPTION\n\t  RPG BY NWORLD");
        printSeparator(40);
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
        
        Story.printIntro(player.getName());
        player.chooseTrait();
        isRunning = true;
        scan.nextLine();
        pressAnything();

        gameLoop();
    }

    // Prints the menu options
    public static void printMenu(){
        clearConsole();
        printHeading("\tMENU");
        System.out.println("Choose an action:");
        printSeparator(20);
        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Check your stats");
        System.out.println("(3) Train in Gym");
        System.out.println("(4) Enter Tournament");
        System.out.println("(5) Exit the game");
    }

    // Continues players journey
    public static void continueJourney() {
        clearConsole();
        
        if(player.getCurrentWorld() == 0) {
            printSeparator(40);
            String[] worlds = player.getWorlds();
            printHeading("   Welcome to the " + worlds[player.getCurrentWorld()]);
            printSeparator(40);
            UrbanStory.printUrban();
            printSeparator(50);
            // System.out.println("You have completed your street training. Time to move to the next level.");
            // player.setCurrentWorld(1); 
            // System.out.println("You are now moving to the Training Facility...");
        }
        else if(player.getCurrentWorld() == 1) {
            printHeading("Training Facility");
            System.out.println("You've made it to a proper gym! Time to hone your skills and become stronger.");
            System.out.println("You feel your power growing with every session. Keep it up!");
            
            System.out.println("You have finished training at the facility. Prepare for the final challenge.");
            player.setCurrentWorld(2);
            System.out.println("You're now headed to the Champ Arena...");
        }
        else if(player.getCurrentWorld() == 2) {
            printHeading("Champ Arena");
            System.out.println("This is it! You've reached the pinnacle of your journey. Time to prove your worth in the arena.");
            System.out.println("Fighters from all over the world await you in the arena. Get ready to face the best.");
            System.out.println("Good luck, Champion!");
        }
        else {
            printHeading("Invalid world state.");
            System.out.println("Something went wrong! You are stuck in an unknown world.");
        }
        
        pressAnything();
    }


    // Checks players stats
    public static void printStats(){
        clearConsole();
        printHeading("\tCHARACTER STATS");
        System.out.println(player.getName());
        System.out.println("\tHP:\t\t" + player.getHp() + " / " + player.getMaxHp());
        System.out.print("\t");
        printSeparator(30);
        System.out.println("\tStamina:\t" + player.getStamina() + " / " + player.getStamina());
        pressAnything();
    }

    // Enter gym and train with coach
    public static void gymTraining(){
        if(player.getCurrentWorld() == 0){
            clearConsole();
            UrbanStory.urbanTraining1(player.getName());
            int choice = readInt("-> ", 2);
            if(choice == 1) UrbanStory.response(player.getName());
            else if(choice == 2) UrbanStory.response2(player.getName());
            
            UrbanStory.urbanTraining2();
            choice = readInt("-> ", 2);
            if(choice == 1) UrbanStory.response3();
            else UrbanStory.response4();

            UrbanStory.urbanTraining3(player.getName());
            UrbanStory.urbanTraining4();
            choice = readInt("-> ", 1);
            if(choice == 1) UrbanStory.response5();

            choice = readInt("-> ", 1);
            if(choice == 1) UrbanStory.response6();

            choice = readInt("-> ", 1);
            if(choice == 1) UrbanStory.response7();

            choice = readInt("-> ", 1);
            if(choice == 1){
                UrbanStory.response8(player.getName());
                scan.nextLine();
                pressAnything();
            }

            UrbanStory.urbanTraining5(player.getName());
            pressAnything();
            UrbanStory.train();
            scan.nextLine();
            pressAnything();
        }
    }

    // Enter tournament and fight with your opponents
    public static void enterTournament(){
        
    }

    // Loops the menu options
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("-> ", 5);
            scan.nextLine();
            if(input == 1){
                continueJourney();
            } else if(input == 2){
                printStats();
            } else if(input == 3){
                gymTraining();
            } else if(input == 4){
                enterTournament();
            } else {
                isRunning = false;
            }
        }
    }

}