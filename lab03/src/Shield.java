public class Shield {
    private int level = 1;
    private String name;
    private double defense;

    public Shield(String name, double defense){
        this.name = name;
        this.defense = defense;
    }
    public int getLevel() {
        return level;
    }
    public void levelUP(){
        ++level;
        defense = defense * ( 1 + 0.05 * level);
    }
    public double runSpeedDecrease(double runSpeed) {
        return runSpeed * ( 0.1  + 0.08 * level);
    }
    public double getDefense() {
        return defense;
    }
    public String getName() {
        return name;
    }
}
