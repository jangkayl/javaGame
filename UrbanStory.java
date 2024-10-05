public class UrbanStory {
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
        System.out.println("Select what to reply:");
        System.out.println("\t(1) \"I'm ready. Let's do this!\"");
        System.out.println("\t(2) \"I don't know… I've never trained in a real gym before.\"");
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
        System.out.println("\tThe rest? I'll teach you.Keep your head in the game, and let's see what you've got.\"");
        System.out.println();
        System.out.println(name + ": \t\"Thanks, Fred. I'll give it my all.\"");
    }

    public static void urbanTraining2(String name){
        System.out.println();
        System.out.println("\t[ Fred leads you inside the gym. The smell of sweat and leather fills the air as you step onto the mat.");
        System.out.println("\tHe hands you a pair of old, worn boxing gloves. ]");
        System.out.println();
        System.out.println("Fred: \t\"First things first. Let's see your stance. You've got power, but power's no good without balance.\"");
        System.out.println();
        System.out.println("\t( Fred demonstrates the proper stance. )");
        System.out.println();
        System.out.println("Select what to reply:");
        System.out.println("\t(1) \"Got it. Let's do this!\"");
        System.out.println("\t(2) \"This feels a little awkward…\"");
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
        System.out.println("\t[ You begin your training. Fred takes you through a series of drills: jabs, hooks, uppercuts, and footwork.");
        System.out.println("\tEach time you throw a punch, Fred offers corrections, pushing you to go harder and faster. ]");
        System.out.println();
        System.out.println("Fred: \t\"You've got potential, " + name + ". But remember, boxing isn't just about strength. It's about outsmarting your");
        System.out.println("\topponent. When you're in the ring, you have to think two steps ahead. Now, let's see what you've really got.\"");
        System.out.println();
        System.out.println("\t[ " + name + " nods, ready for the challenge. ]");
        System.out.println();
        System.out.println("\t(1) \"Got it. Let's do this!\"");
        System.out.println("\t(2) \"This feels a little awkward…\"");
    }
}
