package world3.PassiveSkill;

import world1.GameLogic;

public class PassiveSkill {
    private String name;
    private String description;
    private int roundsActive;

    // Constructor
    public PassiveSkill(String name, String description, int roundsActive) {
        this.name = name;
        this.description = description;
        this.roundsActive = roundsActive;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRoundActive(){
        return roundsActive;
    }

    public void setRoundActive(int rounds){
        this.roundsActive = rounds;
    }

    public void activatePassive(String name){
        String message = name + "'s " + this.getName() + " activated!";
        System.out.println(GameLogic.centerBox(message, 50));
    }

    public void deactivatePassive(String name){
        roundsActive--;
        if (roundsActive == 0) {
            String message = name + "'s " + this.getName() + " has worn out!";
            System.out.println(GameLogic.centerBox(message, 50));
        }
    }

}
