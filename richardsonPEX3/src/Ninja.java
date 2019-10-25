/**
 * Ninja -  a class for for the ninja a normal type of warrior
 *
 * @author Caleb Richardson
 */
public class Ninja extends Warrior{
    /**
     * Ninja - constructor for the ninja
     */
    public Ninja(){
        //sets the warriors specific attributes
        rawStrength = 40;
        strengthDev = 10;
        rawAgility = 45;
        agilDev = 15;

        strength = BattleSim.randInt(rawStrength - strengthDev, rawStrength + strengthDev);

        hp = 100;

        agility = BattleSim.randInt(rawAgility + agilDev, rawAgility + agilDev);

        movementSpeed = 10;
        attackSpeed = 7;

        range = 20;

        warriorID = "Hidden Leaf Ninja";
        imageFile = "ninja.png";
        victoryImage = "hiddenLeafWin.png";

        dodgeSound = "poof.wav";
        hitSound = "punch2.wav";
    }

}
