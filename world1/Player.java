package world1;
import world1.interfaces.PlayerInterface;

public class Player extends Boxer implements PlayerInterface {
    private String[] traits = {"Immovable Fury (High HP)", "Phantom Boxer (Agility)", "Bonecrusher Brawl (Strength)"};
    private String[] worlds = {"Urban Gym", "Training Facility", "Champ Arena"};
    private String[] ranking = {"ROOKIE", "AMATEUR", "CONTENDER", "CHALLENGER", "PRO FIGHTER", "CHAMPION", "WORLD CHAMPION", "LEGEND", "HALL OF FAMER"};
    private int currentWorld;
    private int currentRank;
    private int playerPoints;
    private boolean isLose;
    private int stage;
    static StreetFighter opponent;

    public Player(String name, int hp, int stamina, double critChance, double critMultiplier, double dodgeChance, int playerPoints, int currentWorld, int currentRank, int stage, boolean isLose) {
        super(name, hp, stamina, critChance, critMultiplier, dodgeChance);
        this.playerPoints = playerPoints;
        this.currentWorld = currentWorld;
        this.currentRank = currentRank;
        this.stage = stage;
        this.isLose = isLose;
    }

    public void setOpponent(StreetFighter opponent) {
        Player.opponent = opponent;
    }
    
    @Override
    public void jab() {
        int damage = (int)Math.floor(10 * getDamageSetter());
        int staminaReduced = 5;
        opponent.setHp(opponent.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " jabs " + opponent.getName() + " for " + damage + " damage!");
    }
    
    @Override
    public void hook() {
        int damage = (int)Math.floor(15 * getDamageSetter());
        int staminaReduced = 7;
        opponent.setHp(opponent.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " hooks " + opponent.getName() + " for " + damage + " damage!");
    }
    
    @Override
    public void block() {
        int newStamina = this.getStamina() + 5;
        if (newStamina > this.getMaxStamina()) {
            newStamina = this.getMaxStamina();
        } else {
            System.out.println(this.getName() + " blocks and gains 5 stamina!");
        }
        this.setStamina(newStamina);
    }
    
    @Override
    public void uppercut() {
        int damage = (int)Math.floor(20 * getDamageSetter());
        int staminaReduced = 10;
        opponent.setHp(opponent.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " uppercuts " + opponent.getName() + " for " + damage + " damage!");
    }


    // Additional Combos
    public void leadBodyShot() {
        int damage = (int)Math.floor(15 * getDamageSetter());
        int staminaReduced = 7;
        opponent.setHp(opponent.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " lead body shots " + opponent.getName() + " for " + damage + " damage!");
    }

    public void crossToTheRibs() {
        int damage = (int)Math.floor(20 * getDamageSetter());
        int staminaReduced = 9;
        opponent.setHp(opponent.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " Cross to the Ribs " + opponent.getName() + " for " + damage + " damage!");
    }

    public void finishingUppercut() {
        int damage = (int)Math.floor(25 * getDamageSetter());
        int staminaReduced = 14;
        opponent.setHp(opponent.getHp() - damage);
        this.setStamina(this.getStamina() - staminaReduced);
        System.out.println(this.getName() + " Cross to the Ribs " + opponent.getName() + " for " + damage + " damage!");
    }

    public void chooseTrait(){
        String[][] bonus = {{"+50% HP", "-10% Stamina", "15% Critical Hit Chance", "-5% Dodge Chance"}, 
        {"+30% Stamina", "+10% Dodge Chance", "20% Critical Hit Chance", "-10% HP"},
        {"20% Critical Hit Chance", "+50% Crit Damage Multiplier", "-10% Stamina", "-5% Dodge Chance"}};
        
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a trait:");
        for(int i = 0; i < traits.length; i++){
            System.out.print("(" + (i+1) + ") " + traits[i] + " - ");
            if(i == 2) System.out.println("\t" + bonus[i][0]);
            else System.out.println("\t\t" + bonus[i][0]);
            for(int j = 1; j < 4; j++){
                System.out.println("\t\t\t\t\t" + bonus[i][j]);
            }
        }
        
        int input = GameLogic.readInt("-> ", 1, 3);
        GameLogic.printHeading("You chose " + traits[input-1] + "!");
        if(input == 1){
            int newHp = getMaxHp() + (int)Math.floor(getMaxHp() * 0.5);
            int newStamina = getStamina() - (int)Math.floor(getStamina() * 0.1);
            double newDodge = getDodgeChance() - (getDodgeChance() * 0.05);
            this.setHp(newHp);
            this.setMaxHp(newHp);
            this.setStamina(newStamina);
            this.setMaxStamina(newStamina);
            this.setCritChance(0.15);
            this.setDodgeChance(newDodge);
        } else if(input == 2) {
            int newHp = getMaxHp() - (int)Math.floor(getMaxHp() * 0.1);
            int newStamina = getStamina() + (int)Math.floor(getStamina() * 0.3);
            double newDodge = getDodgeChance() + (getDodgeChance() * 0.1);
            this.setStamina(newStamina);
            this.setHp(newHp);
            this.setMaxHp(newHp);
            this.setDodgeChance(newDodge);
            this.setCritChance(0.2);
        } else if(input == 3) {
            int newStamina = getStamina() - (int)Math.floor(getStamina() * 0.1);
            double newDodge = getDodgeChance() - (getDodgeChance() * 0.05);
            double newCritMulti = getCritMultiplier() + (getCritMultiplier() * 0.5);
            this.setCritChance(0.2);
            this.setCritMultiplier(newCritMulti);
            this.setStamina(newStamina);
            this.setDodgeChance(newDodge);
        }
        GameLogic.pressAnything();
    }

    public boolean hasEnoughStamina(int requiredStamina) {
        return this.getStamina() >= requiredStamina;
    }

    public void rankUp(){
        if(currentRank > ranking.length - 1){
            currentRank++;
            System.out.println("na rankup naka!");
        } else {
            System.out.println("Nana kas pinakataas yati ra");
        }
    }

    public void setCurrentRank(int currentRank){
        this.currentRank = currentRank;
    }

    public int getRank(){
        return currentRank;
    }
    
    public String getCurrentRank() {
        return ranking[currentRank];
    }
    
    public int getCurrentWorld(){
        return this.currentWorld;
    }
    
    public void setCurrentWorld(int currentWorld){
        this.currentWorld = currentWorld;
    }
    
    public String[] getWorlds(){
        return this.worlds;
    }
    
    public void setPlayerPoints(int playerPoints){
        this.playerPoints = playerPoints;
    }
   
    public int getPlayerPoints(){
       return playerPoints;
    }
   
    public void addPlayerPoints(int points) {
       this.playerPoints += points;
       System.out.println("You earned " + points + " points! Total points: " + playerPoints);
    }    

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public boolean getIsLose() {
        return isLose;
    }

    public void setIsLose(boolean isLose) {
        this.isLose = isLose;
    }

}

