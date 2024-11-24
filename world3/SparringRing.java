package world3;

import java.util.Random;

import world1.GameLogic;
import GlobalClasses.Inventory;
import GlobalClasses.Player;
import GlobalClasses.PlayerProgress;
import GlobalClasses.StreetFighter;
import world3.SparringOpponents.ArcegaSparring;
import world3.SparringOpponents.MagsilosSparring;

public class SparringRing {
    private StreetFighter opponent;
    private Random rand = new Random();
    private Player player = GameLogic.player;
    private PlayerProgress playerProgress = GameLogic.playerProgress;
    private Inventory inventory = GameLogic.inventory;

    public void start() {
        GameLogic.clearConsole();
        String title = "SPARRING RING";
        System.out.println(GameLogic.centerBox(title, 60));
    
        String prompt = "The Sparring Ring is your training ground to refine your skills and build strength for the Champs Arena." +
                        "\nEngage in practice matches to boost your stats, test your strategies, and prepare for tougher challenges." +
                        "\nWinning matches not only hones your abilities but also rewards you with cash - greater risks mean greater rewards." +
                        "\nStep into the ring, face opponents of varying difficulty, and emerge stronger and ready to conquer!";
        System.out.print(GameLogic.centerBox(prompt, 130));
        System.out.println();
        GameLogic.pressAnything();
    
        int randomNum = rand.nextInt(3) + 1;
        startSparring(randomNum);
    }

    private void startSparring(int opponentIndex) {
        ArcegaSparring arcega = null;
        MagsilosSparring magsilos = null;
        int choice = 0;

        if(!inventory.checkSlotsValid()){
            System.out.println();
            System.out.print(GameLogic.centerBox("✋ Please UNEQUIP all items from Underworld Rumble before fighting", 75));
            GameLogic.pressAnything();
            return;
        }
    
        boolean usePerez = new Random().nextBoolean(); 
        if (usePerez) {
            opponent = generateRandomOpponent("Junjun Arcega");
            arcega = new ArcegaSparring(player, opponent);
            magsilos = null;
        } else {
            opponent = generateRandomOpponent("Kargado Magsilos");
            magsilos = new MagsilosSparring(player, opponent);
            arcega = null;
        }
    
        while (true) {
            world1.Tournament.showOpStats(opponent);
            System.out.println();
            System.out.print(GameLogic.centerText(50,"Select an option: "));
            System.out.print(GameLogic.centerText(50,"(1) Fight now"));
            System.out.print(GameLogic.centerText(50,"(2) Check your Stats"));
            System.out.print(GameLogic.centerText(50,"(3) Change Opponent Stats"));
            System.out.print(GameLogic.centerText(50,"(4) Go Back"));
    
            choice = GameLogic.readInt(GameLogic.centerText("", 97) + "-> ", 1, 4); 
    
            if (choice == 1) {
                playerProgress.setRound(1);
                break;
            } else if (choice == 2) {
                GameLogic.printStats();
            } else if (choice == 3) {
                usePerez = new Random().nextBoolean(); 
                if (usePerez) {
                    opponent = generateRandomOpponent("Junjun Arcega");
                    arcega = new ArcegaSparring(player, opponent);
                    magsilos = null;
                } else {
                    opponent = generateRandomOpponent("Kargado Magsilos");
                    magsilos = new MagsilosSparring(player, opponent);
                    arcega = null;
                }
                System.out.println(GameLogic.centerBox("New opponent generated!", 50));
            } else if(choice == 4){
                return;
            }
        }
    
        if (arcega != null) {
            arcega.fightLoop();
        } else if (magsilos != null) {
            magsilos.fightLoop();
        }
    }
    
    // Random Stats for Opponent
    private StreetFighter generateRandomOpponent(String name) {
        int hp = rand.nextInt(51) + player.getMaxHp() - 50; // HP between playersMaxHp
        int stamina = rand.nextInt(21) + player.getMaxStamina() + 10; // Stamina between playersMaxStamina
        double critChance = 0.3 + (rand.nextDouble() * 0.3); // Crit chance between 0.3 and 0.6
        double critMulti = 2 + (rand.nextDouble() * 1); // Crit multi between 2 and 3
        double dodgeChance = 0.3 + (rand.nextDouble() * 0.2); // Dodge chance between 0.3 and 0.5
    
        return new StreetFighter(name, hp, stamina, critChance, critMulti, dodgeChance, 4);
    }
    
}
