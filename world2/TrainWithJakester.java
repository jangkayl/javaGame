package world2;

import world1.GameLogic;

public class TrainWithJakester {
    private static String[] array = {"Jab", "Hook", "Block"};
    private static String[] dirtyMoves = {"Elbow Strike", "Head Butt", "Low Blow"};

    public static void teachDirtyBoxingMoves() {
        GameLogic.clearConsole();
        GameLogic.printSeparator(50);
        System.out.println();
        System.out.println("Coach Jakester: \t\"Listen up, Kyle. Down here, you need more than just jabs and hooks. I'm gonna teach you a few moves that break the rules but win fights.\"");
        System.out.println();
    
        // Elbow Strike
        System.out.println("Coach Jakester: \t\"Elbow Strike - Fast and brutal, it dazes them but is countered by a solid block.\"");
        System.out.println("\t\t*Effective Against: Jab & Hook - Quickly closes distance, disrupting their attacks.");
        System.out.println("\t\t*Counter: Block - Absorbs the impact and prevents the dazing effect.");
        System.out.println();
    
        // Head Butt
        System.out.println("Coach Jakester: \t\"Head Butt - Breaks through blocks and slows their reflexes. Watch out, though an uppercut can counter it.\"");
        System.out.println("\t\t*Effective Against: Block - Forces them back, lowering their reflexes.");
        System.out.println("\t\t*Counter: Uppercut - Strong enough to knock you back and interrupt your attack.");
        System.out.println();
    
        // Low Blow
        System.out.println("Coach Jakester: \t\"Low Blow - Drains their stamina if they're guarding high, but a quick hook can stop it.\"");
        System.out.println("\t\t*Effective Against: Block - Hits under their guard, draining their stamina.");
        System.out.println("\t\t*Counter: Hook - Quick enough to intercept the low blow and keep them on defense.");
        System.out.println();
    
        System.out.println("\t\t( You take note, understanding how each move gives you an advantage against specific attacks. )");
        System.out.println();
        GameLogic.printSeparator(50);
        GameLogic.pressAnything();
        train(GameLogic2.player.getName());
    }
    

    static void space(int separator){
        System.out.println();
        GameLogic.printSeparator(separator);
        System.out.println();
    }

    static int punch(){
        System.out.println("Select the counter punch:");
        for(int i = 1; i <= 3; i++){
            System.out.println("\t(" + i + ") \"" + dirtyMoves[i-1] + "\"");
        }
        int choice = GameLogic.readInt("-> ", 1, 3);
        return choice;
    }

    static boolean isCounter(int fredMove, int playerMove) {
        switch (fredMove) {
            case 1:
                if (playerMove == 1) {
                    return true;
                }
                return false;
            case 2:
                if (playerMove == 1) {
                    return true;
                }
                return false;
            case 3:
                if (playerMove == 2 || playerMove == 3) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    static void train(String name){
        int success = 0;
        int choice;

        space(70);
        System.out.println("Jakester: \t\"Alright, let's put your skills to the test. Try to counter my next punch.\"");
        do {
            int randomNum = 0 + (int)(Math.random() * ((2 - 0) + 1));
            System.out.println();
            if(randomNum == 2){
                System.out.println("( Jakester " + array[randomNum] + "s )");
            } else {
                System.out.println("( Jakester throws a " + array[randomNum] + " ) ");
            }

            System.out.println("Success: " + success + " / 5");
            choice = punch();
            boolean countered = isCounter(randomNum+1, choice);

            System.out.println();
            GameLogic.printSeparator(30);
            System.out.println();
            if(countered){
                System.out.println("Great job!");
                success++;
            } else {
                System.out.println("No, you should try to counter punch it!");
            }
        } while(success < 5);

        space(70);
        System.out.println("Coach Jakester: \t\"Great job, " + name + "! Remember, in the underground, it's not just about dirty moves.");
        System.out.println("\t\tReal strength lies in reading your opponent's first move; it reveals their strategy cautious or reckless.\"");
        System.out.println();
        System.out.println("\t\t\"Let's dive into how to read their moves effectively.\"");
        System.out.println();
        System.out.println("\t\t( You nod, realizing that survival hinges not just on tricks, but on outsmarting your opponent. )");
        GameLogic.pressAnything();
        GameLogic.clearConsole();
        space(70);
        System.out.println("Coach Jakester: \t\"Listen up, " + name + "! In the ring, your opponent's first move reveals their strategy. Let me show you how to read them.\"");
        System.out.println();
        System.out.println("(1) Check Hints");
        choice = GameLogic.readInt("-> ", 1, 1);
        System.out.println();
        if(choice == 1){
            BoxerHints.teachHints();
        }
        readyToSpar(name);
    }

    static void readyToSpar(String name) {
        space(70);
        System.out.println("Coach Jakester: \t\"Listen up, " + name + "! You've learned the ropes, and now it's time to put that knowledge to the test.");
        System.out.println("\tSparring is your chance to gain stats and prove yourself in the ring!");
        System.out.println("\tBut remember, success hinges on how well you've memorized those hints. Trust your instincts, and let's see what you've got!\"");
        System.out.println();
        GameLogic.player.setStage(8);
        GameLogic.pressAnything();
    }
    
}
