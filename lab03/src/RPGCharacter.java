public class RPGCharacter {
    //class variable

    //instance variable
    public String name;

    private int level = 1;
    private double mn = 50;
    private double hp = 100;
    private double runSpeed = 20;
    private double maxRunSpeed = runSpeed * ( 1 + 0.05 * level);
    private Sword sword = null;
    private Shield shield = null;
    private double damage = 10;
    private double defense = 5;

    //constructor
    public RPGCharacter(String name){
        this.name = name;
    }

    //instance method
    public String getName() {
        return name;
    }
    public double getDefense() {
        return defense;
    }
    public double getDamage() {
        return damage;
    }

    public void levelUP(){
        level++;
        mn = mn + 2 * level;
        hp = hp + 10 * level;
    }
    public int getLevel(){
        return level;
    }

    public double getCurrentHP() {
        return hp;
    }
    public double getCurrentMN() {
        return mn;
    }
    public double getMaxRunSpeed(){
        return maxRunSpeed;
    }
    private double getRunSpeed(){
        return runSpeed;
    }

    //method overloading
    public void equipWeapon(Sword sword){
        runSpeed = runSpeed - sword.runSpeedDecrease(runSpeed);
        maxRunSpeed = maxRunSpeed - sword.runSpeedDecrease(runSpeed);
        damage = damage + sword.getDamage();
        this.sword = sword;
    }
    public void equipWeapon(Shield shield){
        runSpeed = runSpeed - shield.runSpeedDecrease(runSpeed);
        maxRunSpeed = maxRunSpeed - shield.runSpeedDecrease(runSpeed);
        defense = defense + shield.getDefense();
        this.shield = shield;
    }

    public void  charStatus(){
        IO.println("name :" + getName() + "| level :" + getLevel() + "| hp :" + getCurrentHP() + "| mn :" + getCurrentMN());

        String swordName = "null";
        int swordLev;
        String shieldName = "null";
        int shieldLev;
        if (sword != null){
            swordName = sword.getName();
            swordLev = sword.getLevel();
            IO.print("have sword :" + swordName + "lev :" + swordLev);
        }if (shield != null){
            shieldName = shield.getName();
            shieldLev = shield.getLevel();
            IO.print("| shield :" + shieldName + "lev :" + shieldLev);
        }else IO.println("have sword :" + swordName + "| shield :" + shieldName);


        IO.println("run speed :" + getRunSpeed() + "| max run speed :" + getMaxRunSpeed());
        IO.println("damage :" + getDamage() + "| defense :" + getDefense());
    }
}
