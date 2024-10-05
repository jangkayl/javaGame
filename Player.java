public class Player extends Boxer {
    private String[] traits = {"Immovable Fury (High HP)", "Phantom Boxer (Agility)", "Bonecrusher Brawl (Strength)"};
    private String[] worlds = {"Urban Gym", "Training Facility", "Champ Arena"};
    private int currentWorld;

    public Player(String name, int hp, int stamina) {
        super(name, hp, stamina);
        this.currentWorld = 0;
    }

    @Override
    public void jab() {
        throw new UnsupportedOperationException("Unimplemented method 'jab'");
    }

    @Override
    public void hook() {
        throw new UnsupportedOperationException("Unimplemented method 'hook'");
    }

    @Override
    public void uppercut() {
        throw new UnsupportedOperationException("Unimplemented method 'uppercut'");
    }

    @Override
    public void defend() {
        throw new UnsupportedOperationException("Unimplemented method 'defend'");
    }

    public void chooseTrait(){
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a trait:");
        for(int i = 0; i < traits.length; i++){
            System.out.println("(" + (i+1) + ") " + traits[i]);
        }
        
        int input = GameLogic.readInt("-> ", 3);
        if(input != -1){
            GameLogic.printHeading("You chose " + traits[input-1] + "!");
            if(input == 1){
                this.setHp(100);
                this.setMaxHp(100);
            }
            else if(input == 2) this.setStamina(50);
        } 
        System.out.println();
    }

    public int getCurrentWorld(){
        return this.currentWorld;
    }

    public void setCurrentWorld(int currentWorld){
        this.currentWorld = currentWorld;
    }


}
