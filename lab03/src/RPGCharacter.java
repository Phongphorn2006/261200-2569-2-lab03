public class RPGCharacter {
    //instance variable
    String name;

    //class variable
    static int level = 1;
    static int runSpeed = 20;
    static double hp = 100;
    static double mn = 100;
    static Sword sword = null;
    static Shield shield = null;

    public RPGCharacter(String name){
        this.name = name;
    }
}
