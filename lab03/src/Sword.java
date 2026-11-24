public class Sword {
    private int level = 1;
    private String name;
    private double damage;

    public Sword(String name,double damage){
        this.name = name;
        this.damage = damage;
    }
    public int getLevel() {
        return level;
    }
    public void levelUP(){
        ++level;
        damage = damage *  ( 1 + 0.1 * level);
    }
    public double runSpeedDecrease(double runSpeed) {
        return runSpeed * ( 0.1  + 0.04 * level);
    }
    public double getDamage() {
        return damage;
    }
    public String getName() {
        return name;
    }
}
