package world2.BetFight;

import java.util.Random;

import world1.GameLogic;
import world1.Player;
import world1.PlayerProgress;
import world1.StreetFighter;
import world2.SparringOpponents.PerezSparring;
import world2.SparringOpponents.PitikSparring;

public class BetFight {
    private StreetFighter opponent;
    private Random rand = new Random();
    private Player player = GameLogic.player;
    private PlayerProgress playerProgress = GameLogic.playerProgress;

    public void betFight(){
        System.out.println();
        GameLogic.clearConsole();
        System.out.println("🎲 Welcome to the Underground BetFight Arena 🎲");
        GameLogic.printSeparator(40);
        System.out.println("The dim glow of flickering neon lights illuminates the smoky hall. The crowd's cheers and jeers create a chaotic symphony of excitement.");
        System.out.println();
        GameLogic.printSeparator(40);
        System.out.println("[ A shadowy figure approaches, cloaked in mystery, their voice a raspy growl. ]");
        System.out.println("Bookie: \"Well, well, look who stumbled into the ring of fate. Got some points to burn?\"");
        System.out.println("Bookie: \"Here's how it works: pick your fighter, place your bet, and if your champ wins, you double your points! But if they lose... well, let's just say the house always gets its cut.\"");
        System.out.println();
        
        if (player.getPlayerPoints() < 10) {
            System.out.println("Bookie: \"Hah! You can't even afford a seat in this bloodbath. Come back when you've got at least 100 points to wager.\"");
            System.out.println("💡 Tip: Earn more points by completing quests or sparring matches. Then return to stake your claim in the chaos! 💡");
            GameLogic.pressAnything();
            GameLogic.gameData.saveGame();
        } else {
            System.out.println("Bookie: \"I see you've got the creds. Alright, place your bet! Who's it gonna be?\"");
            System.out.println();
            GameLogic.printSeparator(40);
            GameLogic.pressAnything();
    
            int randomNum = rand.nextInt(3) + 1;
            startSparring(randomNum);

        }
        
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
            System.out.println("Place your bet (minimum: 100 points): ");
            System.out.println("\t\t(1) Fight now");
            System.out.println("\t\t(2) Check your Stats");
            System.out.println("\t\t(3) Change Opponent Stats");
            System.out.println("\t\t(4) Go Back");
    
            choice = GameLogic.readInt("-> ", 1, 4); 
    
            if (choice == 1) {
                playerProgress.setRound(1);
                if(playerProgress.getAddStats() < 3){
                    playerProgress.setAddStats(playerProgress.getAddStats() + 1);
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

    private StreetFighter generateRandomOpponent(String name) {
        int hp = rand.nextInt(51) + player.getMaxHp() - 100; // HP between playersMaxHp
        int stamina = rand.nextInt(21) + player.getMaxStamina() + 10; // Stamina between playersMaxStamina
        double critChance = 0.3 + (rand.nextDouble() * 0.3); // Crit chance between 0.3 and 0.6
        double critMulti = 2 + (rand.nextDouble() * 1); // Crit multi between 2 and 3
        double dodgeChance = 0.3 + (rand.nextDouble() * 0.2); // Dodge chance between 0.3 and 0.5
    
        return new StreetFighter(name, hp, stamina, critChance, critMulti, dodgeChance, 3);
    }
    
}
