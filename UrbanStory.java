public class UrbanStory {

    static void reply(String reply){
        System.out.println("Select 1 to " + reply);
        System.out.println("\t(1) - " + reply);
    }

    static void reply(String reply1, String reply2){
        System.out.println("Select what to reply:");
        System.out.println("\t(1) \"" + reply1 + "\"");
        System.out.println("\t(2) \"" + reply2 + "\"");
    }

    public static void printUrban(){
        System.out.println();
        System.out.println("A gritty, inner-city gym where local fighters train and compete in underground bouts.");
        System.out.println();
        System.out.println("You are training in the streets, fighting to survive and improve your skills.");
        System.out.println("Train hard with your coach Fred to unlock the next world. Keep pushing forward!");
        System.out.println();
    }

    public static void urbanTraining1(String name){
        System.out.println();
        System.out.println("Fred: \t\"Welcome to the Urban Gym, " + name + ". It may not look like much, but this place has");
        System.out.println("\tmade champions. Today, it's gonna make you better. But first...\"");
        System.out.println();
        System.out.println("\t( He pauses, giving you a serious look. )");
        System.out.println();
        System.out.println("Fred: \t\"I need to know something. Are you ready for this? Training here isn't just about");
        System.out.println("\tlearning how to hit harder. You're gonna learn discipline, control, and how to endure pain.");
        System.out.println("\tYou think you got what it takes?.\"");
        System.out.println();
        reply("I'm ready. Let's do this!", "I don't know… I've never trained in a real gym before.");
    }

    public static void response(String name){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred nods with approval. )");
        System.out.println();
        System.out.println("Fred: \t\"That's the spirit. Confidence is good, but remember, pride can get you hurt.\"");
        System.out.println("\tKeep your head in the game, and let's see what you've got.");
        System.out.println();
        System.out.println(name + ": \t\"Let's go!\"");
    }

    public static void response2(String name){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred claps a hand on your shoulder, his grip firm but reassuring. )");
        System.out.println();
        System.out.println("Fred: \t\"Don't worry. Everyone starts somewhere, kid. You've got the heart for this, and that's what counts.");
        System.out.println("\tThe rest? I'll teach you. Keep your head in the game, and let's see what you've got.\"");
        System.out.println();
        System.out.println(name + ": \t\"Thanks, Fred. I'll give it my all.\"");
    }

    public static void urbanTraining2(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t[ Fred leads you inside the gym. The smell of sweat and leather fills the air as you step onto the mat.");
        System.out.println("\tHe hands you a pair of old, worn boxing gloves. ]");
        System.out.println();
        System.out.println("Fred: \t\"First things first. Let's see your stance. You've got power, but power's no good without balance.\"");
        System.out.println();
        System.out.println("\t( Fred demonstrates the proper stance. )");
        System.out.println();
        reply("Got it. Let's do this!", "This feels a little awkward…");
    }

    public static void response3(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("Fred: \t\"Good. Now, let's work on your jabs. Quick, sharp, and always return to guard.");
        System.out.println("\tKeep your eyes on me. Ready?\"");
    }
    
    public static void response4(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("Fred: \t\"It always feels weird at first, but trust me, it'll become second nature.");
        System.out.println("\tJust keep practicing, and it'll click.\"");
    }

    public static void urbanTraining3(String name){
        System.out.println();
        GameLogic.printSeparator(50);
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

    public static void urbanTraining4(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred throws a quick Jab )");
        System.out.println();
        System.out.println("Fred: \t\"This is your Jab. It's fast and hits first. If your opponent tries a big Uppercut, you can beat them to the punch.");
        System.out.println("\tSo remember, a Jab will always stop an Uppercut in its tracks.\"");
        System.out.println("\t[ Jab > Uppercut ]");
        System.out.println();
        System.out.println("Fred: \t\"Now, after I demonstrate, I want you to follow my lead.\"");
        System.out.println();
        reply("Jab");
    }
    
    public static void response5(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred swings a wide Hook )");
        System.out.println();
        System.out.println("Fred: \t\"But watch out! A Hook is stronger than a Jab. When someone throws a Jab at you,");
        System.out.println("\tanswer with a Hook to overpower them.\"");
        System.out.println("\t( Hook > Jab )");
        System.out.println();
        reply("Hook");
    }

    public static void response6(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred pulls back and guards with a Block )");
        System.out.println();
        System.out.println("Fred: \t\"Now, a good Block can stop a lot of punches, especially a heavy Hook. If you see that coming, ");
        System.out.println("\tget ready to Block. It'll protect you from a nasty hit.\"");
        System.out.println("\t( Block > Hook )");
        System.out.println();
        reply("Block");
    }

    public static void response7(){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred drops his guard and launches an Uppercut )");
        System.out.println();
        System.out.println("Fred: \t\"But don't get too comfortable behind that guard! A well-placed Uppercut can break through a Block.");
        System.out.println("\tIf they're just standing there guarding, hit them hard with an Uppercut.\"");
        System.out.println("\t( Uppercut > Block )");
        System.out.println();
        reply("Uppercut");
    }

    public static void response8(String name){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( Fred takes a step back, his eyes sharp )");
        System.out.println();
        System.out.println("Fred: \t\"Remember, " + name + ", it's all about knowing what's coming and how to counter. Jab stops Uppercut.");
        System.out.println("\tHook overpowers Jab. Block defends against Hook. And Uppercut breaks through Block. Get these counters down,");
        System.out.println("\tand you'll be ready for anything!\"");
    }

    public static void urbanTraining5(String name){
        System.out.println();
        GameLogic.printSeparator(50);
        System.out.println("\t( " + name + " smiles, absorbing the lesson )");
        System.out.println();
        System.out.println("Fred: \t\"Let's put that knowledge to the test. Think before you strike, and outsmart your opponent.");
        System.out.println("\tReady to try it out?\"");
    }
}
