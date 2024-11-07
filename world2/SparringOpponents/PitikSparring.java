package world2.SparringOpponents;

import world1.GameLogic;
import world1.Player;
import world1.StreetFighter;
import world2.SparringLogic.SparFightLogic;
import world2.SparringLogic.VsPitik;

public class PitikSparring extends SparFightLogic{
    public static String[] opponentAttacks = {"Jab", "Hook", "Block", "Uppercut", "Cross", "Rear Uppercut", "Lead Hook", "Elbow Strike", "Head Butt", "Low Blow"};
    static VsPitik vsPitik;
    
    public PitikSparring(Player player, StreetFighter opponent) {
        super(player, opponentAttacks);
        setOpponent(opponent);
        vsPitik = new VsPitik(player, opponent);
    }

    @Override
    public String getOpponentName() {
        return "Pitik";
    }

    @Override
    protected void printFight(int[] choices, int[] opponentChoices) {
        for(int i = 0; i < 3; i++){
            int countered = isCounter(opponentChoices[i], choices[i]);
            if(countered == 1){
                System.out.println(player.getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName());
                vsPitik.playerSuccessAction(choices[i], opponentChoices[i], false);
                vsPitik.opponentFailedAction(opponentChoices[i]);
            } else if(countered == 2){
                System.out.println(player.getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName());
                vsPitik.opponentSuccessAction(opponentChoices[i], choices[i], false);
                vsPitik.playerFailedAction(choices[i]);
            } else {
                System.out.println(player.getName() + " throws a " + playerAttacks[choices[i]] + " to " + opponent.getName());
                System.out.println(opponent.getName() + " draws " + player.getName() + " with " + opponentAttacks[choices[i]]);
                vsPitik.drawAction(choices[i], opponentChoices[i]);
            }
            if(player.getHp() <= 0 || opponent.getHp() <= 0){
                return;
            }
            GameLogic.printSeparator(50);
        }
    }

    @Override
    protected int isCounter(int opponentMove, int playerMove) {
        switch (opponentMove) {
            case 0:
                if(playerMove == 1 || playerMove == 5 || playerMove == 4 || playerMove == 7) return 1;
                if(playerMove == 3 || playerMove == 6) return 2;
                break;
            case 1:
                if(playerMove == 2 || playerMove == 6 || playerMove == 5 || playerMove == 7) return 1;
                if(playerMove == 0 || playerMove == 4 || playerMove == 9) return 2;
                break;
            case 2:
                if(playerMove == 3 || playerMove == 4 || playerMove == 8 || playerMove == 9) return 1;
                if(playerMove == 1 || playerMove == 6 || playerMove == 5 || playerMove == 7) return 2;
                break;
            case 3:
                if(playerMove == 0 || playerMove == 4 || playerMove == 6) return 1;
                if(playerMove == 2) return 2;
                break;
            case 4:
                if(playerMove == 3) return 1;
                if(playerMove == 0 || playerMove == 1 || playerMove == 8) return 2;
                break;
            case 5:
                if(playerMove == 2) return 1;
                if(playerMove == 3 || playerMove == 0) return 2;
                break;
            case 6:
                if(playerMove == 0) return 1;
                if(playerMove == 1 || playerMove == 2) return 2;
                break;
            case 7:  // Elbow Strike
                if(playerMove == 2) return 1;
                if(playerMove == 1 || playerMove == 0 || playerMove == 4 || playerMove == 6) return 2;
                break;
            case 8:  // Head Butt
                if(playerMove == 3) return 1;
                if(playerMove == 2) return 2;
                break;
            case 9:  // Low Blow
                if(playerMove == 1) return 1;
                if(playerMove == 2) return 2;
                break;
            default:
                break;
        }
        return 0;
    }

    @Override
    protected void opponentValid(int[] opponentChoice) {
        int tempStamina = opponent.getStamina();
        
        for (int i = 0; i < opponentChoice.length; i++) {
            int staminaCost = 0;
            boolean validChoice = false;

            while (!validChoice) {
                switch (opponentChoice[i]) {
                        case 1: // Jab
                        staminaCost = 5;
                        break;
                    case 2: // Hook
                        staminaCost = 7;
                        break;
                    case 3: // Block (assuming no stamina cost)
                        staminaCost = 0;
                        break;
                    case 4:  // Uppercut 
                        staminaCost = 10;
                        break;
                    case 5:
                        staminaCost = 7;
                        break;
                    case 6:
                        staminaCost = 9;
                        break;
                    case 7:
                        staminaCost = 14;
                        break;
                    case 8:
                        staminaCost = 25;
                        break;
                    case 9:
                        staminaCost = 20;
                        break;
                    case 10:
                        staminaCost = 25;
                        break;
                }
    
                if (tempStamina - staminaCost >= 0) {
                    validChoice = true;
                } else {
                    if(rand.nextDouble() > 0.3)
                        opponentChoice[i] = rand.nextInt(10);
                        if (opponentChoice[i] >= 4 && opponentChoice[i] <= 6) {
                            if(tempStamina - 30 < 0){
                                opponentChoices = new int[]{2, 2, 2}; 
                                return;
                            } else {
                                opponentChoices = new int[]{4, 5, 6}; 
                                return;
                            }
                        }
                    else 
                        opponentChoice[i] = 2;
                }
            }
            tempStamina -= staminaCost;
        }
    }

}