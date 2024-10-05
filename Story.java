public class Story {
    public static void printIntro(String name){
        GameLogic.clearConsole();
        GameLogic.printSeparator(30);
        GameLogic.printHeading("The Redemption Story");
        GameLogic.printSeparator(30);
        System.out.println("In the shadowy alleys of Tondo Manila, there was a boy named " + name + " has spent his life,");
        System.out.println("surviving the harsh streets while studying hard to help his family from poverty.");
        System.out.println("He has been bullied all his life and all he wants is to graduate and end his sufferings. ");
        System.out.println("One day, after hearing his bullies mock his late mother, he snaps and discovers a");
        System.out.println("sudden surge of power, easily defeating them easily defeated all his bullies.");
        System.out.println("At that time Fred, a retired boxer, witnesses how he fights and was amazed and");
        System.out.println("saw a great potential in " + name + ". He offers to teach " + name + " how to control");
        System.out.println("his newfound strength, hinting at a deeper power within him.");
        System.out.println(name + "'s journey is just beginning, with battles ahead that go beyond the streets and the ring.");
        GameLogic.pressAnything();
    }
}


