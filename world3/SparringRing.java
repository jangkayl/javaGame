package world3;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world2.SparringOpponents.PerezSparring;
import world2.SparringOpponents.PitikSparring;

public class SparringRing {
    private StreetFighter opponent;
    private Random rand = new Random();
    private Player player = GameLogic.player;
    private PlayerProgress playerProgress = GameLogic.playerProgress;

    public void start() {
        GameLogic.clearConsole();
        String title = "SPARRING RING";
        System.out.println(GameLogic.centerBox(title, 130));
        System.out.println();
    
        String prompt = "The Sparring Ring is your training ground to refine your skills and build strength for the Champs Arena." +
                        "\nEngage in practice matches to boost your stats, test your strategies, and prepare for tougher challenges." +
                        "\nWinning matches not only hones your abilities but also rewards you with cash - greater risks mean greater rewards." +
                        "\nStep into the ring, face opponents of varying difficulty, and emerge stronger and ready to conquer!";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
    
        int randomNum = rand.nextInt(3) + 1;
        startSparring(randomNum);
    }

    private void startSparring(int opponentIndex) {
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
