package world2;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world2.SparringOpponents.PerezSparring;

public class Sparring {
    static StreetFighter opponent;
    static Random rand = new Random();
    static Player player = GameLogic.player;
    static String[] opponents = {"El Tigre", "El Jablo", "El Taeh"};
    static PlayerProgress playerProgress = GameLogic.playerProgress;

    public static void enterSparring() {
        if(player.getStage() == 8){
            playerProgress.setAddStats(0);
            player.setStage(9);
            GameLogic.clearConsole();
            System.out.println("🥊  SPARRING ARENA - DIRTY BOXING FIGHT 🥊");
            GameLogic.printSeparator(40);
            System.out.println("You step into the gritty sparring arena, the atmosphere thick with tension and excitement...");
            System.out.println();
            GameLogic.printSeparator(40);
            System.out.println("Fighters around you are placing bets, eager to double their cash while proving their strength in the ring!");
            System.out.println("Prepare yourself for a dirty boxing match where anything goes. Victory means not just glory, but also cash and valuable points to boost your stats!");
            System.out.println("But beware: losing a fight can cost you hard-earned stats! Are you ready to risk it all for fame and fortune? Show the world your skills!");
            System.out.println();
            System.out.println("💰 Tip: Fight fiercely, win rewards, and enhance your abilities with every match. But remember, losses can hurt your stats! 💰");
            GameLogic.pressAnything();
            GameLogic.gameData.saveGame();
        }
        sparRules();

        int randomNum = rand.nextInt(3) + 1;
        startSparring(randomNum);
    }

    private static void sparRules() {
        GameLogic.clearConsole();
        System.out.println("🥊  SPARRING ARENA - DIRTY BOXING FIGHT 🥊");
        GameLogic.printSeparator(40);
        System.out.println("Mechanics: ");
        GameLogic.printSeparator(40);
        System.out.println("Fighters are betting cash to prove their strength. Victory means glory, cash, and stat boosts.");
        System.out.println("But losing a fight will cost you valuable stats, so be cautious!");
        System.out.println();
        System.out.println("⚠️ Only 1 round to WIN or LOSE");
        System.out.println("💪 **Win**: Gain points and boost your stats.");
        System.out.println("💔 **Lose**: Lose stats, making future fights tougher.");
        System.out.println();
        System.out.println("💰 Tip: Win rewards, lose stats. Choose wisely!");
        GameLogic.pressAnything();
    }
    
    private static void startSparring(int opponentIndex) {
        GameLogic.clearConsole();
        PerezSparring perez = null;
        int choice = 0;
    
        StreetFighter opponent = generateRandomOpponent();  
        perez = new PerezSparring(player, opponent);
    
        while (choice != 1) {
            world1.Tournament.showOpStats(opponent);
    
            System.out.println("Select an option: ");
            System.out.println("\t\t(1) Fight now");
            System.out.println("\t\t(2) Check your Stats");
            System.out.println("\t\t(3) Change Opponent Stats");
    
            choice = GameLogic.readInt("-> ", 1, 3); 
    
            if (choice == 1) {
                break;
            } else if (choice == 2) {
                GameLogic.printStats();
            } else if (choice == 3) {
                opponent = generateRandomOpponent(); 
                perez = new PerezSparring(player, opponent); 
                System.out.println("New opponent generated!");
            }
        }
    
        perez.fightLoop();
    }
    
    private static StreetFighter generateRandomOpponent() {
        Random rand = new Random();
    
        // Random Stats for Opponent
        int hp = rand.nextInt(51) + 180; // HP between 180 and 230
        int stamina = rand.nextInt(21) + 80; // Stamina between 80 and 101
        double critChance = 0.3 + (rand.nextDouble() * 0.3); // Crit chance between 0.3 and 0.6
        double critMulti = 2 + (rand.nextDouble() * 1); // Crit multi between 2 and 3
        double dodgeChance = 0.3 + (rand.nextDouble() * 0.2); // Dodge chance between 0.3 and 0.5
    
        return new StreetFighter("Joaquin Perez", hp, stamina, critChance, critMulti, dodgeChance, 3);
    }
    
}
