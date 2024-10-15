public class UrbanStory {
    private static String[] array = {"Jab", "Hook", "Block", "Uppercut"};

    static void reply(int num, String reply){
        System.out.println("Select " + num + " to " + reply);
        System.out.println("\t(" + num + ") - " + reply);
    }

    static void reply(String reply1, String reply2){
        System.out.println("Select what to reply:");
        System.out.println("\t(1) \"" + reply1 + "\"");
        System.out.println("\t(2) \"" + reply2 + "\"");
    }

    static void space(int separator){
        System.out.println();
        GameLogic.printSeparator(separator);
        System.out.println();
    }

    static int isCounter(int fredMove, int playerMove) {
        switch (fredMove) {
            case 1: 
                if (playerMove == 2) {
                    return 1;
                } else if (playerMove == 4) {
                    return 2; 
                } else {
                    return 0; 
                }
            case 2:
                if (playerMove == 3) {
                    return 1;
                } else if (playerMove == 1) {
                    return 2; 
                } else {
                    return 0; 
                }
            case 3: 
                if (playerMove == 4) {
                    return 1;
                } else if (playerMove == 2) {
                    return 2; 
                } else {
                    return 0; 
                }
            case 4:
                if (playerMove == 1) {
                    return 1;
                } else if (playerMove == 3) {
                    return 2; 
                } else {
                    return 0; 
                }
            default:
                return -1;
        }
    }

    static int punch(){
        System.out.println("Select the counter punch:");
        for(int i = 1; i <= 4; i++){
            System.out.println("\t(" + i + ") \"" + array[i-1] + "\"");
        }
        int choice = GameLogic.readInt("-> ", 1, 4);
        return choice;
    }

    public static void printUrban(){
        System.out.println();
        System.out.println("A tough, inner-city gym where local fighters sharpen their skills and compete in underground matches.");
        System.out.println();
        System.out.println("You are training in the streets, fighting to survive and improve your skills.");
        System.out.println("Train rigorously with your coach, once you're ready, prove your strength by winning a tournament.");
        System.out.println("Victory will grant you the opportunity to advance to the next world. Keep striving for greatness!");
        System.out.println();
    }

    public static void printTraining(String name){
        int choice = 0;

        urbanTraining1(name);
        choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1) response(name);
        else if(choice == 2) response2(name);
        GameLogic.pressAnything();

        urbanTraining2();
        choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1) response3();
        else response4();

        GameLogic.pressAnything();
        urbanTraining3(name);
        urbanTraining4();
        choice = GameLogic.readInt("-> ", 1, 1);
        if(choice == 1) response5();

        choice = GameLogic.readInt("-> ", 2, 2);
        if(choice == 2) response6();

        choice = GameLogic.readInt("-> ", 3, 3);
        if(choice == 3) response7();

        choice = GameLogic.readInt("-> ", 4, 4);
        if(choice == 4){
            response8(name);
        }

        urbanTraining5(name);
        train(name);
    }

    static void urbanTraining1(String name){
        System.out.println();
        System.out.println("Fred: \t\"Welcome to the Urban Gym, " + name + ". It may not look like much, but this place has");
        System.out.println("\tmade champions. Today, it's gonna make you better. But first...\"");
        System.out.println();
        System.out.println("\t( He pauses, giving you a serious look. )");
        System.out.println();
        System.out.println("Fred: \t\"I need to know something. Are you ready for this? Training here isn't just about");
        System.out.println("\tlearning how to hit harder. You're gonna learn discipline, control, and how to endure pain.");
        System.out.println("\tYou think you got what it takes?\"");
        System.out.println();
        reply("I'm ready. Let's do this!", "I don't know… I've never trained in a real gym before.");
    }

    static void response(String name){
        space(70);
        System.out.println("\t( Fred nods with approval. )");
        System.out.println();
        System.out.println("Fred: \t\"That's the spirit. Confidence is good, but remember, pride can get you hurt.\"");
        System.out.println("\tKeep your head in the game, and let's see what you've got.");
        System.out.println();
        System.out.println(name + ": \t\"Let's go!\"");
    }

    static void response2(String name){
        space(70);
        System.out.println("\t( Fred claps a hand on your shoulder, his grip firm but reassuring. )");
        System.out.println();
        System.out.println("Fred: \t\"Don't worry. Everyone starts somewhere, kid. You've got the heart for this, and that's what counts.");
        System.out.println("\tThe rest? I'll teach you. Keep your head in the game, and let's see what you've got.\"");
        System.out.println();
        System.out.println(name + ": \t\"Thanks, Fred. I'll give it my all.\"");
    }

    static void urbanTraining2(){
        space(70);
        System.out.println("\t[ Fred leads you inside the gym. The smell of sweat and leather fills the air as you step onto the mat.");
        System.out.println("\tHe hands you a pair of old, worn boxing gloves. ]");
        System.out.println();
        System.out.println("Fred: \t\"First things first. Let's see your stance. You've got power, but power's no good without balance.\"");
        System.out.println();
        System.out.println("\t( Fred demonstrates the proper stance. )");
        System.out.println();
        reply("Got it. Let's do this!", "This feels a little awkward…");
    }

    static void response3(){
        space(70);
        System.out.println("Fred: \t\"Good. Now, let's work on your jabs. Quick, sharp, and always return to guard.");
        System.out.println("\tKeep your eyes on me. Ready?\"");
    }
    
    static void response4(){
        space(70);
        System.out.println("Fred: \t\"It always feels weird at first, but trust me, it'll become second nature.");
        System.out.println("\tJust keep practicing, and it'll click.\"");
    }

    static void urbanTraining3(String name){
        space(70);
        System.out.println("\t[ You begin your training. Fred takes you through a series of drills: jabs, hooks, uppercuts, and footwork.");
        System.out.println("\tEach time you throw a punch, Fred offers corrections, pushing you to go harder and faster. ]");
        System.out.println();
        System.out.println("Fred: \t\"You've got potential, " + name + ". But remember, boxing isn't just about strength. It's about outsmarting your");
        System.out.println("\topponent. When you're in the ring, you have to think two steps ahead. Now, let's see what you've really got.\"");
        System.out.println();
        System.out.println("\t[ " + name + " nods, ready for the challenge. ]");
        System.out.println();
        System.out.println("Fred: \t\"Alright, let's start with the basics. Boxing is a dance of timing, precision, and knowing when to use the right move.");
        System.out.println("\tEvery move has a counter, and knowing that will give you the edge. Here's how it works...\"");
        GameLogic.pressAnything();
    }

    static void urbanTraining4(){
        space(70);
        System.out.println("\t( Fred throws a quick Jab )");
        System.out.println();
        System.out.println("Fred: \t\"This is your Jab. It's fast and hits first. If your opponent tries a big Uppercut, you can beat them to the punch.");
        System.out.println("\tSo remember, a Jab will always stop an Uppercut in its tracks.\"");
        System.out.println("\t[ Jab > Uppercut ]");
        System.out.println();
        System.out.println("Fred: \t\"Now, after I demonstrate, I want you to follow my lead.\"");
        System.out.println();
        reply(1, "Jab");
    }
    
    static void response5(){
        space(70);
        System.out.println("\t( Fred swings a wide Hook )");
        System.out.println();
        System.out.println("Fred: \t\"But watch out! A Hook is stronger than a Jab. When someone throws a Jab at you,");
        System.out.println("\tanswer with a Hook to overpower them.\"");
        System.out.println("\t[ Hook > Jab ]");
        System.out.println();
        reply(2, "Hook");
    }

    static void response6(){
        space(70);
        System.out.println("\t( Fred pulls back and guards with a Block )");
        System.out.println();
        System.out.println("Fred: \t\"Now, a good Block can stop a lot of punches, especially a heavy Hook. If you see that coming, ");
        System.out.println("\tget ready to Block. It'll protect you from a nasty hit.\"");
        System.out.println("\t[ Block > Hook ]");
        System.out.println();
        reply(3, "Block");
    }

    static void response7(){
        space(70);
        System.out.println("\t( Fred drops his guard and launches an Uppercut )");
        System.out.println();
        System.out.println("Fred: \t\"But don't get too comfortable behind that guard! A well-placed Uppercut can break through a Block.");
        System.out.println("\tIf they're just standing there guarding, hit them hard with an Uppercut.\"");
        System.out.println("\t[ Uppercut > Block ]");
        System.out.println();
        reply(4, "Uppercut");
    }

    static void response8(String name){
        space(70);
        System.out.println("\t( Fred takes a step back, his eyes sharp )");
        System.out.println();
        System.out.println("Fred: \t\"Remember, " + name + ", it's all about knowing what's coming and how to counter. Jab stops Uppercut.");
        System.out.println("\tHook overpowers Jab. Block defends against Hook. And Uppercut breaks through Block. Get these counters down,");
        System.out.println("\tand you'll be ready for anything!\"");
        GameLogic.pressAnything();
    }

    static void urbanTraining5(String name){
        space(70);
        System.out.println("\t( " + name + " smiles, absorbing the lesson )");
        System.out.println();
        System.out.println("Fred: \t\"Let's put that knowledge to the test. Think before you strike, and outsmart your opponent.");
        System.out.println("\tReady to try it out?\"");
        GameLogic.pressAnything();
    }

    static void train(String name){
        int success = 0;
        int choice;
        
        space(70);
        System.out.println("Fred: \t\"Alright, let's put your skills to the test. Try to counter my next punch.\"");
        do {
            int randomNum = 0 + (int)(Math.random() * ((3 - 0) + 1));
            System.out.println();
            if(randomNum == 2){
                System.out.println("( Fred " + array[randomNum] + "s )");
            } else if(randomNum == 3){
                System.out.println("( Fred throws an " + array[randomNum] + " )");
            } else {
                System.out.println("( Fred throws a " + array[randomNum] + " ) ");
            }

            System.out.println("Success: " + success + " / 5");
            choice = punch();
            int countered = isCounter(randomNum+1, choice);

            System.out.println();
            GameLogic.printSeparator(30);
            System.out.println();
            if(countered == 1){
                System.out.println("Great job!");
                success++;
            } else if(countered == 2){
                System.out.println("No, you should try to counter punch it!");
            } else {
                System.out.println("Not bad, but I wanna see some counter punches!");
            }
        } while(success < 0);

        space(70);
        System.out.println("Fred: \t\"Good work, " + name + "! You've got some real potential. Remember, practice makes perfect.");
        System.out.println("\tI hope to see you again tomorrow for another session. Keep your guard up and stay sharp!\"");
        GameLogic.pressAnything();
    }

    static void urbanTraining6(String name){
        space(70);
        System.out.println("Fred: \t\"Good to see you back, " + name + ". You've made some progress, but there's still work to do.");
        System.out.println("\tI want you to know that training is important, but so is preparation. I've opened up a shop here");
        System.out.println("\tin the gym where you can buy gear and supplements. This will help boost your stats whether it's");
        System.out.println("\tyour strength, speed, or resilience. Spend your winnings wisely.\"");
        System.out.println();
        System.out.println("Select 1 to go to Shop");
        System.out.println("\t(1) - Shop");
    }

    public static void urbanTraining7(){
        space(70);
        System.out.println("Fred: \t\"But before you start spending your hard-earned cash, I've got a task for you. Consider it a ");
        System.out.println("\tchallenge to prove you're ready to take the next step.\"");
        System.out.println();
        System.out.println("\t( Fred leans in with a serious expression )");
        System.out.println();
        System.out.println("Fred: \t\"Head over to the ringside and spar with one of our top fighters, Ricky 'The Phantom'.");
        System.out.println("\tBeat him, and I'll let you purchase some exclusive items in the shop only the best get access to.");
        System.out.println("\tIt's not gonna be easy, but I know you've got it in you.\"");
        System.out.println();
        System.out.println("\t( He pats the player on the shoulder and steps back )");
        System.out.println();
        System.out.println("Fred: \t\"Go on, show me what you've got. And remember \"train hard, shop smart\".");
        GameLogic.pressAnything();
        Shop.showMenu();
    }
}
