public class Player extends Boxer {

    public String[] traits = {"Immovable Fury (High HP)", "Phantom Boxer (Agility)", "Bonecrusher Brawl (Strength)"};

    public Player(String name, int hp, int stamina) {
        super(name, hp, stamina);
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
            if(input == 1) this.setMaxHp(100);
            else if(input == 2) this.setStamina(50);
        } 
        
        System.out.println("Name: " + this.getName());
        System.out.println("HP: " + this.getMaxHp());
        System.out.println("Stamina: " + this.getStamina());

        GameLogic.pressAnything();
    }


}
