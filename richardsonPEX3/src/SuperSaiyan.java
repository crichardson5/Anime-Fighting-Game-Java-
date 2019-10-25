/**
 * SuperSaiyan -  a class for for the super Saiyan, a special type of warrior
 *
 * @author Caleb Richardson
 */
public class SuperSaiyan  extends Warrior{
    /**
     * SuperSaiyan - constructor fort the super Saiyan
     */
    public SuperSaiyan(){
        //sets the warriors specific attributes
        strength = BattleSim.randInt(90, 150);
        hp = 125;

        agility = BattleSim.randInt(60, 100);

        movementSpeed = 10;
        attackSpeed = 15;

        range = 20;

        warriorID = "The Super Saiyan";
        imageFile = "superSaiyan.png";
        victoryImage = "saiyanWin.png";

        dodgeSound = "teleport.wav";
        hitSound = "punch.wav";
    }
}
