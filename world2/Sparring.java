package world2;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world2.SparringOpponents.PerezSparring;
import world2.SparringOpponents.PitikSparring;

public class Sparring {
    private StreetFighter opponent;
    private Random rand = new Random();
    private Player player = GameLogic.player;
    private PlayerProgress playerProgress = GameLogic.playerProgress;

    public void enterSparring() {
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
    
    private void startSparring(int opponentIndex) {
        GameLogic.clearConsole();
        PerezSparring perez = null;
        PitikSparring pitik = null;
        int choice = 0;
    
        boolean usePerez = new Random().nextBoolean(); 
        if (usePerez) {
            opponent = generateRandomOpponent("Joaquin Perez");
            perez = new PerezSparring(player, opponent);
            pitik = null;
        } else {
            opponent = generateRandomOpponent("Lando Pitik");
            pitik = new PitikSparring(player, opponent);
            perez = null;
        }
    
        while (true) {
            world1.Tournament.showOpStats(opponent);
    
            System.out.println("Select an option: ");
            System.out.println("\t\t(1) Fight now");
            System.out.println("\t\t(2) Check your Stats");
            System.out.println("\t\t(3) Change Opponent Stats");
            System.out.println("\t\t(4) Go Back");
    
            choice = GameLogic.readInt("-> ", 1, 4); 
    
            if (choice == 1) {
                playerProgress.setRound(1);
                if(playerProgress.getShopStage() < 6){
                    playerProgress.setShopStage(playerProgress.getShopStage() + 1);
                }
                break;
            } else if (choice == 2) {
                GameLogic.printStats();
            } else if (choice == 3) {
                usePerez = new Random().nextBoolean(); 
                if (usePerez) {
                    opponent = generateRandomOpponent("Joaquin Perez"); 
                    perez = new PerezSparring(player, opponent);
                    pitik = null; 
                } else {
                    opponent = generateRandomOpponent("Lando Pitik"); 
                    pitik = new PitikSparring(player, opponent);
                    perez = null;
                }
                System.out.println("New opponent generated!");
            } else if(choice == 4){
                return;
            }
        }
    
        if (perez != null) {
            perez.fightLoop();
        } else if (pitik != null) {
            pitik.fightLoop();
        }
    }
    
    // Random Stats for Opponent
    private StreetFighter generateRandomOpponent(String name) {
        int hp = rand.nextInt(51) + player.getMaxHp() - 100; // HP between playersMaxHp
        int stamina = rand.nextInt(21) + player.getMaxStamina() + 10; // Stamina between playersMaxStamina
        double critChance = 0.3 + (rand.nextDouble() * 0.3); // Crit chance between 0.3 and 0.6
        double critMulti = 2 + (rand.nextDouble() * 1); // Crit multi between 2 and 3
        double dodgeChance = 0.3 + (rand.nextDouble() * 0.2); // Dodge chance between 0.3 and 0.5
    
        return new StreetFighter(name, hp, stamina, critChance, critMulti, dodgeChance, 3);
    }
    
}
