public class Story {
    public static void printIntro(String name){
        GameLogic.clearConsole();
        GameLogic.printSeparator(40);
        GameLogic.printHeading("\tThe Redemption Story");
        GameLogic.printSeparator(40);
        System.out.println();
        System.out.println("In the shadowy alleys of Tondo Manila, there was a boy named " + name + " has spent his life,");
        System.out.println("surviving the harsh streets while studying hard to help his family from poverty.");
        System.out.println("He has been bullied all his life and all he wants is to graduate and end his sufferings. ");
        System.out.println("One day, after hearing his bullies mock his late mother, he snaps and unleashes a mysterious");
        System.out.println("sudden surge of power, easily defeating them. At that time Fred, a retired boxer,");
        System.out.println("witnesses how he fights and was amazed and saw a great potential in " + name + " untamed strength.");
        System.out.println("He offers to teach " + name + " how to control his newfound strength, hinting at a deeper power within him.");
        System.out.println("and warning of greater challenges ahead.");
        System.out.println();
        System.out.println(name + "'s journey has just begun, with battles ahead that go beyond the streets and the ring.");
        System.out.println();
        GameLogic.pressAnything();
    }

    public static void printUrban(){
        System.out.println();
        System.out.println("A gritty, inner-city gym where local fighters train and compete in underground bouts.");
        System.out.println();
        System.out.println("You are training in the streets, fighting to survive and improve your skills.");
        System.out.println("Train hard with your coach Fred to unlock the next world. Keep pushing forward!");
        System.out.println();
    }
}

