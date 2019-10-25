/**
 * Saiyan -  a class for for the saiyan a normal type of warrior
 *
 * @author Caleb Richardson
 */
public class Saiyan extends Warrior {

    /**
     * Saiyan - constructor for the Saiyan
     */
    public Saiyan(){
        //sets the warriors specific attributes
        rawStrength = 90;
        strengthDev = 17;
        rawAgility = 57;
        agilDev = 17;

        strength = BattleSim.randInt(rawStrength - strengthDev, rawStrength + strengthDev);

        hp = 100;

        agility = BattleSim.randInt(rawAgility + agilDev, rawAgility + agilDev);

        movementSpeed = 10;
        attackSpeed = 5;

        range = 20;

        warriorID = "Saiyan";
        imageFile = "saiyan.png";
        victoryImage = "saiyanWin.png";

        dodgeSound = "teleport.wav";
        hitSound = "punch.wav";
    }

}
