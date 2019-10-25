/**
 * Jinchuriki -  a class for for the Jinchuriki, a special type of warrior
 *
 * @author Caleb Richardson
 */
public class Jinchuriki extends Warrior{
    /**
     * Jinchuriki - constructor for the Jinchuriki
     */
    public Jinchuriki(){
        //sets the warriors specific attributes
        strength = BattleSim.randInt(75, 90);
        hp = 125;

        agility = BattleSim.randInt(70, 120);

        movementSpeed = 10;
        attackSpeed = 10;

        range = 20;

        warriorID = "The Jinchuriki ";
        imageFile = "jinchuriki.png";
        victoryImage = "hiddenLeafWin.png";

        dodgeSound = "poof.wav";
        hitSound = "punch2.wav";

    }

}
