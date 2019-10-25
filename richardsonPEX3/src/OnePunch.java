/**
 * OnePunch - a class for for the one punch man a special type of warrior
 *
 * @author Caleb Richardson
 */
public class OnePunch extends Warrior {
    /**
     * Saiyan - constructor for the one punch man
     */
    public OnePunch(){
        //sets the warriors specific attributes
        //will finish a normal full health opponent in one punch 95% - 100% of the time
        strength = BattleSim.randInt(800, 1000);
        hp = 100;

        agility = BattleSim.randInt(80, 115);

        movementSpeed = 10;
        attackSpeed = 14;

        range = 20;

        warriorID = "The One Punch Man";
        imageFile = "onePunch.png";
        victoryImage = "heroWin.png";

        dodgeSound = "teleport.wav";
        hitSound = "punch3.wav";
    }
}
