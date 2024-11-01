package world1;

import world1.TrainInGym.PabloUrbanGym;

public class UrbanStory {
    static Inventory inventory = new Inventory();
    static Shop shop = GameLogic.shop;
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
        reply("I'm ready. Let's do this!", "I don't know... I've never trained in a real gym before.");
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
        reply("Got it. Let's do this!", "This feels a little awkward...");
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
        } while(success < 5);

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
        shop.showMenu();
    }

    public static void urbanTrainingLose(String name, String opponent) {
        space(70);
        System.out.println("Fred: \t\"Ahhh, tough break there, " + name + ". "+ opponent + " is a beast in the ring.\"");
        System.out.println();
        System.out.println("\t( Fred steps forward, his expression calm yet encouraging )");
        System.out.println();
        System.out.println("Fred:\t\"Listen, losses aren't the end. They're the stepping stones to real progress.");
        System.out.println("\tEvery time you fall, you get back up stronger.\"");
        System.out.println();
        System.out.println("\t( He places a reassuring hand on your shoulder, a knowing smile on his face )");
        System.out.println();
        System.out.println("Fred:\t\"You've got what it takes, I see that. So, how about another go? The road to victory isn't easy,");
        System.out.println("\tbut it's worth every fight. Remember this, Strength isn't in never falling, but in rising every single time.\"");
        System.out.println();
        System.out.println("Fred:\t\"Do you want a rematch?\"");
        GameLogic.pressAnything();
        GameLogic.shop.showMenu();
    }

    public static void urbanTraining8(String name){
        space(70);
        System.out.println("\t[ You step out of the ring, sweat dripping from your brow.");
        System.out.println("\tThe crowd of gym-goers cheer as you emerge victorious. ]");
        System.out.println();
        System.out.println("\t[ Fred, your coach, claps you on the back with a big grin on his face. ]");
        System.out.println();
        System.out.println("Fred:\t\"Well done, " + name + "! You showed some real grit out there, you're improving fast. But remember,");
        System.out.println("\twinning one sparring match is just the beginning. If you want to get stronger, you'll need to keep pushing.\"");
        System.out.println();
        System.out.println("Fred:\t\"You can take a break, or if you're ready for more, we can keep training. What do you say?\"");
        System.out.println();
        System.out.println("Select what to do:");
        System.out.println("\t1) Yes, I wanna keep training!");
        System.out.println("\t2) I'll take a break first and check the Shop.");
        int choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1) {
            urbanTrainingCombo(name);
        } else {
            shop.showShop(false);
            GameLogic.gameData.inputInventory(inventory.getInventoryItems());
        }
    }

    public static void urbanTrainingCombo(String name) {
        space(70);
        System.out.println("Fred:\t\"Alright " + name + ", let's elevate your skills with the \"The Body Breaker\" combo!\"");
        System.out.println();
        System.out.println("Fred:\t\"First, deliver a Lead Body Shot to counter a Jab!\"");
        System.out.println("\t[ Fred demonstrates the Lead Body Shot, successfully countering a Jab. ]");
        System.out.println();
        System.out.println("Fred:\t\"Next, follow up with a Cross to the Ribs to counter an Hook!\"");
        System.out.println("\t[ Fred executes a Cross to the Ribs, countering an Hook. ]");
        System.out.println();
        System.out.println("Fred:\t\"Finally, finish with a powerful Finishing Uppercut to counter a Block!\"");
        System.out.println("\t[ Fred showcases the Finishing Uppercut, effectively countering a Block. ]");
        System.out.println();
        System.out.println("Fred:\t\"Now that you've seen the 'The Body Breaker' in action, let's see you practice it!");
        System.out.println("\tAfter that, I invite you for a spar. It'll be a great chance to hone your skills!\"");
        System.out.println();
        GameLogic.pressAnything();
        train2(name);
    }

    static void train2(String name){
        String[] skill = {"Jab", "Hook", "Block"};
        String[] combo = {"Lead Body Shot", "Cross to the Ribs", "Finishing Uppercut"};
        int success = 0;
        int choice;
        
        space(70);
        System.out.println("Fred: \t\"Alright, let's put your skills to the test. Try to counter my next punch.\"");
        do {
            int randomNum = 0 + (int)(Math.random() * ((2 - 0) + 1));
            System.out.println();
            if(randomNum == 2){
                System.out.println("( Fred " + skill[randomNum] + "s )");
            } else if(randomNum == 3){
                System.out.println("( Fred throws an " + skill[randomNum] + " )");
            } else {
                System.out.println("( Fred throws a " + skill[randomNum] + " ) ");
            }

            System.out.println("Success: " + success + " / 5");
            System.out.println("Select the counter punch:");
            for(int i = 1; i <= 3; i++){
                System.out.println("\t(" + i + ") \"" + combo[i-1] + "\"");
            }
            choice = GameLogic.readInt("-> ", 1, 3);

            System.out.println();
            GameLogic.printSeparator(30);
            System.out.println();
            if(isCounter2(randomNum+1, choice)){
                System.out.println("Great job!");
                success++;
            } else {
                System.out.println("No, you should try to counter punch it!");
            }
        } while(success < 5);

        space(70);
        System.out.println("Fred:\t\"Great job with the 'The Body Breaker' combo, " + name + "! Now it's time to put those skills to the test.\"");
        System.out.println("\tI've arranged a sparring match for you against one of the best Pablo 'El Halcón' Martínez!");
        System.out.println("\tPablo's fast and skilled, so this will be a true test of what you've learned. Remember to stay focused and use your combo!\"");
        System.out.println();
        System.out.println("\t[ Fred gestures toward the sparring ring, where Pablo stands ready, his eyes sharp and confident. ]");
        System.out.println();
        System.out.println("Fred:\t\"Step into the ring, and show Pablo what you've got!\"");
        System.out.println();
        System.out.println("\t[ You take a deep breath, stepping into the ring to face Pablo 'El Halcón' Martínez, eager to prove your skills... ]");
        GameLogic.pressAnything();
        PabloUrbanGym.setPlayer(GameLogic.player);
        PabloUrbanGym.fightLoop2();
    }

    static boolean isCounter2(int fredMove, int playerMove) {
        switch (fredMove) {
            case 1: 
                if (playerMove == 1) return true;
                return false; 
            case 2:
                if (playerMove == 2) return true;
                return false; 
            case 3: 
                if (playerMove == 3) return true;
                return false; 
            default:
                return false;
        }
    }

    public static void inviteToTournament(String name) {
        System.out.println();
        System.out.println("Fred: \t\"You've really impressed me today, " + name + ". I've seen the way you've put in the work, and");
        System.out.println("\tI've got to say, it's paying off. Those punches, the footwork... you've got the makings of");
        System.out.println("\tsomeone who can go the distance.\"");
        System.out.println();
        System.out.println("\t( He claps his hands in approval, a proud smile spreading across his face. )");
        System.out.println();
        System.out.println("Fred: \t\"Now, here's the thing. There's a tournament coming up. It's tough, no doubt about it, but");
        System.out.println("\tit's also the kind of challenge that'll push you to the next level. You've earned the right");
        System.out.println("\tto be there if you want it. Just say the word, and I'll get you signed up.\"");
        System.out.println();
        reply("I'm in! Let's do this.", "I'll think about it, Fred. Not sure if I'm ready yet.");
        int choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1){
            GameLogic.enterTournament();
        } else {
            return;
        }
    }

    public static boolean tournaLoseTraining(String name) {
        System.out.println();
        GameLogic.printSeparator(40);
        System.out.println();
        System.out.println("Fred: \t\"The tournament was a tough test. Losing happens, but it's how we get stronger.\"");
        System.out.println("\t\tLet's train harder, gain more stats, and you'll be ready for the next challenge.");
        System.out.println("\t\tReady to get back to work?\"");
        System.out.println();
        reply("I'm in! Let's do this.", "I'll think about it, Fred. Not sure if I'm ready yet.");
        int choice = GameLogic.readInt("-> ", 1, 2);
        if(choice == 1){
            return true;
        } 
        return false;
    }
    
    public static void tournaOpponentBackstory(String opponentName) {
        switch (opponentName) {
            case "El Tigre":
                System.out.println("\n\t\t🔥Backstory: Introducing Rico \"El Tigre\" Ramirez, a fierce contender from Manila, inspired by his father's legacy as a former champion." +
                        "\n\t\tKnown for his explosive style and powerful uppercuts, Rico has quickly climbed the ranks of boxing, embodying resilience and determination." +
                        "\n\t\tAs he steps into the ring for this tournament, he carries the hopes of his community, ready to unleash the spirit of the tiger and claim" +
                        "\n\t\this place among the greats! Now, they seek to prove their strength in the tournament!");
                GameLogic.pressAnything();
                break;
            case "El Jablo":
                System.out.println("\n\t\t💪Backstory: Oscar \"El Jablo\" Lopez, from Cebu City, grew up in a hardworking family as the youngest of five. Inspired by local boxing matches," +
                        "\n\t\the honed his skills in underground fights, earning a reputation for his lightning-fast jabs and explosive combos. Driven by a desire to uplift" +
                        "\n\t\t his community and motivated by a friend's injury in the ring, Oscar turned pro. Now, as he enters the tournament, he's determined to prove" +
                        "\n\t\thimself as a champion, ready to unleash his quick jab, powerful cross, and knockout power punch.");
                        GameLogic.pressAnything();
                break;
            case "El Taeh":
                System.out.print(" - The Thunderous Finisher");
                System.out.println("\n\t\t🌟Backstory: Ishmael Tetteh, \"The Thunderous Finisher,\" is a 28-year-old Ghanaian-American middleweight boxer with 28 wins (24 by KO)." +
                        "\n\t\tInspired by Canelo Alvarez, he has mastered a signature combo—Right Uppercut, Left Hook, Right Cross—reflecting his journey from Accra to the boxing ring." +
                        "\n\t\t After a pivotal early loss, he trained under a retired champion, transforming into a powerful and agile fighter." +
                        "\n\t\tNow, as he prepares for a major tournament, Ishmael fights for victory and his community, ready to make his mark in the boxing world.");
                GameLogic.pressAnything();
                break;
            default:
                System.out.println("No backstory available for this opponent.");
        }
    }
}

