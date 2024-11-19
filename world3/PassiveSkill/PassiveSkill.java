package world3.PassiveSkill;

public class PassiveSkill {
    private String name;
    private String description;
    private boolean isCrit;
    private boolean isDodge;
    private boolean isActive;        
    private boolean revealEnemyMoves; 

    // Constructor
    public PassiveSkill(String name, String description, boolean isCrit, boolean isDodge, boolean revealEnemyMoves) {
        this.name = name;
        this.description = description;
        this.isCrit = isCrit;
        this.isDodge = isDodge;
        this.revealEnemyMoves = revealEnemyMoves;
        this.isActive = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsCrit() {
        return isCrit;
    }

    public void setIsCrit(boolean isCrit) {
        this.isCrit = isCrit;
    }

    public boolean getIsDodge() {
        return isDodge;
    }

    public void setIsDodge(boolean isDodge) {
        this.isDodge = isDodge;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setRevealEnemyMoves(boolean revealEnemyMoves){
        this.revealEnemyMoves = revealEnemyMoves;
    }

    public boolean getRevealEnemyMoves(){
        return revealEnemyMoves;
    }

    // Methods
    public void activate() {
        if (!isActive) {
            System.out.println(name + " is now active!");
            isActive = true;
        }
    }

    public void deactivate() {
        if (isActive) {
            System.out.println(name + " has worn off.");
            isActive = false;
        }
    }
}
