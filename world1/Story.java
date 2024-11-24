package world1;
public class Story {
    public static void introStory() {
        System.out.println();
        System.out.print(GameLogic.redText + "The Fist of Fury" + GameLogic.reset);
        System.out.println();

        System.out.println("\nThe ring isn't just a place, it's a proving ground. Here, every punch thrown tells a story of sacrifice, hunger, and ambition.");
        GameLogic.printWithDelay("Boxing is more than a sport; it's a way of life, where only the bold rise and the weak fall.");
        GameLogic.printWithDelay("Fighters enter the ring to test their limits, fueled by dreams of glory and haunted by the fear of defeat.\n");

        GameLogic.printWithDelay("In this world, it's not just about strength, it's about heart, strategy, and the ability to endure.");
        GameLogic.printWithDelay("Champions are forged through blood, sweat, and countless hours of training, honing their bodies and minds for the ultimate challenge.");
        GameLogic.printWithDelay("Every bout is a battle of wills, a delicate balance between precision and power, where a single moment can make the difference between victory and defeat.");
        GameLogic.pressAnything();

        System.out.println();
        GameLogic.printSeparator(40);

        GameLogic.printWithDelay("\nNow it's your turn to step through the ropes. Will you master the art of the jab, hook, and uppercut?");
        GameLogic.printWithDelay("Will you outthink your opponent and dance your way to greatness? Or will the pressure of the ring swallow you whole?");
        GameLogic.printWithDelay("The bell is about to ring, time to fight your way to the top and claim your place among the legends.");
        GameLogic.printWithDelay("\nAre you ready to show what you've got?");
        GameLogic.pressAnything();

        System.out.println();
        GameLogic.printSeparator(40);
    }
   
    public static void printIntro(String name){
        GameLogic.clearConsole();
        System.out.print(GameLogic.redText);
        System.out.println(GameLogic.centerBox("The Redemption Story", 95));
        System.out.print(GameLogic.reset);
        System.out.print(GameLogic.orangeText);
        String story = "In the shadowy alleys of Tondo Manila, there was a boy named " + name + " who has spent his\n" +
                "life surviving the harsh streets while studying hard to help his family from poverty.\n" +
                "He has been bullied all his life and all he wants is to graduate and end his sufferings.\n" +
                "One day, after hearing his bullies mock his late mother, he snaps and unleashes a mysterious\n" +
                "sudden surge of power, easily defeating them. At that time Fred, a retired boxer, witnesses\n" +
                "how he fights and was amazed and saw a great potential in " + name + "'s untamed strength.\n" +
                "He offers to teach " + name + " how to control his newfound strength, hinting at a deeper power\n" +
                "within him and warning of greater challenges ahead.\n\n" +
                name + "'s journey has just begun, with battles ahead that go beyond the streets and the ring.";

        String centeredStory = GameLogic.centerText(100, story);
        GameLogic.printWithDelay(centeredStory);
        System.out.print(GameLogic.reset);
        GameLogic.pressAnything();
    }
}
