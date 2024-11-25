package world2;

import world1.GameLogic;

public class TrainWithJakester {
    private static String[] array = {"Jab", "Hook", "Block"};
    private static String[] dirtyMoves = {"Elbow Strike", "Head Butt", "Low Blow"};

    public static void teachDirtyBoxingMoves() {
        GameLogic.clearConsole();
        System.out.println(GameLogic.centerBox(
                "Coach Jakester: \"Listen up, Kyle. Down here, you need more than just jabs and hooks. \nI'm gonna teach you a few moves that break the rules but win fights.\"\n" +
                        "\n" +
                        "Coach Jakester: \"Elbow Strike - Fast and brutal, it dazes them but is countered by a solid block.\"\n" +
                        "*Effective Against: Jab & Hook - Quickly closes distance, disrupting their attacks.\n" +
                        "*Counter: Block - Absorbs the impact and prevents the dazing effect.\n\n" + GameLogic.printCenteredSeparator(60) +
                        "\n\n" +
                        "Coach Jakester: \"Head Butt - Breaks through blocks and slows their reflexes. \nWatch out, though an uppercut can counter it.\"\n" +
                        "*Effective Against: Block - Forces them back, lowering their reflexes.\n" +
                        "*Counter: Uppercut - Strong enough to knock you back and interrupt your attack.\n\n" + GameLogic.printCenteredSeparator(60) +
                        "\n\n" +
                        "Coach Jakester: \"Low Blow - Drains their stamina if they're guarding high, but a quick hook can stop it.\"\n" +
                        "*Effective Against: Block - Hits under their guard, draining their stamina.\n" +
                        "*Counter: Hook - Quick enough to intercept the low blow and keep them on defense.\n\n" + GameLogic.printCenteredSeparator(60) +
                        "\n\n" +
                        "( You take note, understanding how each move gives you an advantage against specific attacks. )\n", 110));

        GameLogic.pressAnything();
        train(GameLogic2.player.getName());
    }

    static void space(int separator){
        System.out.println();
        GameLogic.printSeparator(separator);
        System.out.println();
    }

    static int punch(){
        System.out.println();
        System.out.print(GameLogic.orangeText);
        System.out.print(GameLogic.centerText(50,"Select the counter punch:"));
        for(int i = 1; i <= 3; i++){
            System.out.print(GameLogic.centerText(50,"(" + i + ") \"" + dirtyMoves[i-1] + "\""));
        }
        System.out.print(GameLogic.reset);
        int choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 3);
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

        GameLogic.clearConsole();
        System.out.print(GameLogic.greenText);
        System.out.print(GameLogic.centerBox("Jakester: \"Alright, let's put your skills to the test. Try to counter my next punch.\"",100));
        System.out.print(GameLogic.reset);
        do {
            int randomNum = 0 + (int)(Math.random() * ((2 - 0) + 1));
            System.out.println();
            System.out.print(GameLogic.redText);
            if(randomNum == 2){
                System.out.println(GameLogic.centerBox("( Jakester " + array[randomNum] + "s )", 50));
            } else {
                System.out.println(GameLogic.centerBox("( Jakester throws a " + array[randomNum] + " ) ", 50));
            }
            System.out.print(GameLogic.reset);

            System.out.println();
            System.out.print(GameLogic.greenText);
            System.out.print(GameLogic.centerText(50,"Success: " + success + " / 5"));
            System.out.print(GameLogic.reset);
            choice = punch();
            boolean countered = isCounter(randomNum+1, choice);

            System.out.println("\n\n");
            if(countered){
                System.out.print(GameLogic.greenText);
                System.out.print(GameLogic.centerBox("Great job!",50));
                success++;
            } else {
                System.out.print(GameLogic.redText);
                System.out.print(GameLogic.centerBox("No, you should try to counter punch it!",50));
            }
            System.out.print(GameLogic.reset);
        } while(success < 5);

        System.out.println();
        System.out.print(GameLogic.blueText);
        System.out.print(GameLogic.centerBox("Coach Jakester: \"Great job, " + name + "! Remember, in the underground, it's not just about dirty moves.\n" +
                        "Real strength lies in reading your opponent's first move; it reveals their strategy—cautious or reckless.\"\n" +
                        "\n" +
                        "\"Let's dive into how to read their moves effectively.\"\n" +
                        "\n" +
                        "( You nod, realizing that survival hinges not just on tricks, but on outsmarting your opponent. )", 110));

        System.out.print(GameLogic.reset);
        GameLogic.pressAnything();
        GameLogic.clearConsole();
        System.out.print(GameLogic.centerBox("Coach Jakester: \"Listen up, " + name + "! In the ring, your opponent's first move reveals their strategy. \nLet me show you how to read them.\"",100));
        System.out.println();
        System.out.print(GameLogic.orangeText);
        System.out.print(GameLogic.centerText(50,"(1) Check Hints"));
        System.out.print(GameLogic.reset);
        choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 1);
        System.out.println();
        if(choice == 1){
            BoxerHints.teachHints();
        }
        readyToSpar(name);
    }

    static void readyToSpar(String name) {
        GameLogic.clearConsole();
        System.out.println(GameLogic.centerBox(
                "Coach Jakester: \"Listen up, " + name + "! You've learned the ropes, and now it's time to put that knowledge to the test.\n" +
                        "Sparring is your chance to gain stats and prove yourself in the ring!\n" +
                        "But remember, success hinges on how well you've memorized those hints. Trust your instincts, and let's see what you've got!\"\n", 140));
        GameLogic.player.setStage(8);
        GameLogic.pressAnything();
    }
    
}
