/**
 * Hero - a class for for the hero a normal type of warrior
 *
 * @author Caleb Richardson
 */
public class Hero extends Warrior{
    /**
     * Hero - constructor for the hero
     */
    public Hero(){
        //sets the warriors specific attributes
        rawStrength = 67;
        strengthDev = 10;
        rawAgility = 25;
        agilDev =  13;

        strength = BattleSim.randInt(rawStrength - strengthDev, rawStrength + strengthDev);

        hp = 100;

        agility = BattleSim.randInt(rawAgility + agilDev, rawAgility + agilDev);

        movementSpeed = 10;
        attackSpeed = 4;

        range = 20;

        warriorID = "Hero";
        imageFile = "hero.png";
        victoryImage = "heroWin.png";

        dodgeSound = "poof.wav";
        hitSound = "punch5.wav";
    }
}
