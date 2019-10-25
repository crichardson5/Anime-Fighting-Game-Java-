/**
 * SoulReaper - a class for for the soul reaper a normal type of warrior
 *
 * @author Caleb Richardson
 */
public class SoulReaper extends Warrior {
    /**
     * Saiyan - constructor for the soul reaper
     */
    public SoulReaper(){

        //sets the warriors specific attributes
        rawStrength = 40;
        strengthDev = 10;
        rawAgility = 45;
        agilDev = 15;

        strength = BattleSim.randInt(rawStrength - strengthDev, rawStrength + strengthDev);

        hp = 100;

        agility = BattleSim.randInt(rawAgility + agilDev, rawAgility + agilDev);

        movementSpeed = 10;
        attackSpeed = 8;

        range = 20;

        warriorID = "Soul Reaper";
        imageFile = "kurosaki.png";
        victoryImage = "soulWin.png";


        dodgeSound = "teleport.wav";
        hitSound = "punch4.wav";
    }
}
