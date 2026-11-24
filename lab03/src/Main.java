public class Main {
    void main(){
        RPGCharacter json = new RPGCharacter("Json Huang");
        RPGCharacter elon = new RPGCharacter("Elon Mask");

        Sword sw1 = new Sword("Excalibur", 20.0);
        Sword sw2 = new Sword("Arondight", 18.0);
        Shield sh1 = new Shield("Aegis", 10.0);

        json.equipWeapon(sw1);
        json.equipWeapon(sh1);
        elon.equipWeapon(sw2);

        json.charStatus();
        IO.println("/*---------------------------------------*/");
        elon.charStatus();
        IO.println("/*---------------------------------------*/");

        IO.println(elon.getName() + " attack " + json.getName());
        json.getAttacked(elon.attack());

        IO.println(json.getName() + " attack " + elon.getName());
        elon.getAttacked(json.attack());

        IO.println("Elon HP : " + elon.getCurrentHP() + " | Json HP : " + json.getCurrentHP());
        IO.println("/*---------------------------------------*/");

        IO.println(elon.getName() + "use Slash Attack!");
        json.getAttacked(elon.slash());
        IO.println("Elon mana left : " + elon.getCurrentMN() + " | Json HP: " + json.getCurrentHP());
        IO.println("/*---------------------------------------*/");

        IO.println("---------Elon level up and weapon level up!---------");
        elon.levelUP();
        sw1.levelUP();
        elon.charStatus();

        IO.println("---------unequip the shield---------");
        json.unequipWeapon(sh1);
        json.unequipWeapon(sh1);

        IO.println("---------" + elon.getName() + " use Slash Attack!---------");
        json.getAttacked(elon.slash());
        json.getAttacked(elon.slash());
        json.getAttacked(elon.slash());
        json.charStatus();
    }
}
