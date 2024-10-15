public class StreetFighter extends Boxer {

    public StreetFighter(String name, int hp, int stamina, double critChance, double critMultiplier,
            double dodgeChance) {
        super(name, hp, stamina, critChance, critMultiplier, dodgeChance);
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
    public void block() {
        throw new UnsupportedOperationException("Unimplemented method 'block'");
    }
    
}
