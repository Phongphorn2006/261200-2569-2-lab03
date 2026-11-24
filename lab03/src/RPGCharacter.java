public class RPGCharacter {
    //class variable

    //instance variable
    public String name;

    private int level = 1;
    private double mn = 50;
    private double hp = 100;

    private double runSpeed = 20;
    private double damage = 5;
    private double defense = 2;

    private Sword sword = null;
    private Shield shield = null;


    //constructor
    public RPGCharacter(String name){
        this.name = name;
    }

    //instance method
    public String getName() {
        return name;
    }
    public double getDefense() {
        double totalDefense = defense;
        if (shield != null) {
            totalDefense += shield.getDefense(); // ดึงค่าล่าสุดจาก shield เสมอ
        }
        return totalDefense;
    }
    public double getDamage(){
        double totalDamage = damage;
        if (sword != null){
            totalDamage += sword.getDamage();
        }
        return totalDamage;
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
        double rawMaxSpeed = runSpeed * (1 + 0.05 * level);
        if (sword != null) {
            rawMaxSpeed -= sword.runSpeedDecrease(runSpeed);
        }
        if (shield != null) {
            rawMaxSpeed -= shield.runSpeedDecrease(runSpeed);
        }
        return Math.max(0, rawMaxSpeed);
    }

    public double getRunSpeed(){
        double currentSpeed = runSpeed;
        if (sword != null) {
            currentSpeed -= sword.runSpeedDecrease(runSpeed);
        }
        if (shield != null) {
            currentSpeed -= shield.runSpeedDecrease(runSpeed);
        }
        return Math.max(0, currentSpeed);
    }


    //method overloading
    public void equipWeapon(Sword sword){
        this.sword = sword;
    }
    public void equipWeapon(Shield shield){
        this.shield = shield;
    }
    public void unequipWeapon(Sword sword){
        if ( this.sword != null){
            this.sword = null;
        }else IO.println("The sword is not equipped. ");
    }
    public void unequipWeapon(Shield shield){
        if ( this.shield != null){
            this.shield = null;
        }else IO.println("The sword is not equipped. ");
    }

    public double attack(){
        return getDamage();
    }
    public double slash(){
        double mnCost = 10;
        if (mn >= 10){
            mn -= mnCost;
            return attack() * 1.25;
        }else {
            IO.println("need more mana to use slash attack");
            return attack();
        }
    }

    public void getAttacked(double damage){
        double getDamaged = Math.max(damage - getDefense(),0);
        hp -= getDamaged;
        if (getCurrentHP() <= 0){
            hp = 0;
            IO.println(getName() + " is dead!!");
        }
    }

    public void  charStatus(){
        IO.println("name :" + getName() + "| level :" + getLevel() + "| hp :" + getCurrentHP() + "| mn :" + getCurrentMN());

        String swordInfo = (sword != null) ? sword.getName() + " (lev :" + sword.getLevel() + ")" : "null";
        String shieldInfo = (shield != null) ? shield.getName() + " (lev :" + shield.getLevel() + ")" : "null";
        IO.println("have sword :" + swordInfo + " | shield :" + shieldInfo);

        IO.println("run speed :" + getRunSpeed() + "| max run speed :" + getMaxRunSpeed());
        IO.println("damage :" + getDamage() + "| defense :" + getDefense());
    }
}
