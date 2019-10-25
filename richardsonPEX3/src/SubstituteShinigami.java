/**
 * SubstituteShinigami -  a class for for the substitute shinigami a special type of warrior
 *
 * @author Caleb Richardson
 */
public class SubstituteShinigami extends Warrior {
    /**
     * Saiyan - constructor for the substitute shinigami
     */
    public SubstituteShinigami(){
        //sets the warriors specific attributes
        strength = BattleSim.randInt(55, 100);
        hp = 200;

        agility = BattleSim.randInt(60, 100);

        movementSpeed = 10;
        attackSpeed = 8;

        range = 20;

        warriorID = "The Substitute Shinigami";
        imageFile = "ichigo.png";
        victoryImage = "soulWin.png";

        dodgeSound = "teleport.wav";
        hitSound = "punch4.wav";
    }
}
