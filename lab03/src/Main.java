public class Main {
    void main(){
        RPGCharacter ch1 = new RPGCharacter("bob the killer");
        Sword sw1 = new Sword("Excalibur", 15);
        ch1.equipWeapon(sw1);
        ch1.charStatus();
        sw1.levelUP();
        ch1.charStatus();

        IO.println(sw1.getLevel());
    }
}
