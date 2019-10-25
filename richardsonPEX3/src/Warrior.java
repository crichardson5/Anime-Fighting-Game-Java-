import javax.swing.*;
import java.awt.*;

/**
 * Warrior - class for generating warriors to fight
 *
 * @author Caleb Richardson
 */
public class Warrior {
    //sets constants for size of warriors
    public static final int WARRIOR_WIDTH = 20;
    public static final int WARRIOR_HEIGHT = 30;

    //variable for the raw strength of the normal warriors (without random difference)
    public void setRawStrength(int rawStrength) {
        this.rawStrength = rawStrength;
    }
    public int rawStrength = 90;

    //variable for the potential difference in strength
    public static int strengthDev = 17;

    //variable for the raw agility of the normal warriors (without random difference)
    public void setRawAgility(int rawAgility) {
        this.rawAgility = rawAgility;
    }
    public int rawAgility = 57;

    //variable for potential difference in agility
    public static int agilDev = 17;

    //strength, hp, and agility are on a scale of 1-100 where higher is better
    protected int strength;

    public void setHp(int hp) {
        this.hp = hp;
    }

    protected int hp;

    public void setAgility(int agility) {
        this.agility = agility;
    }

    protected int agility;

    //speed and range are on a scale of 1-5 where higher is better
    protected double movementSpeed;

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    protected int attackSpeed;

    //all range will initially be 1, will play around with more values later
    protected int range;

    //id as a string to identify what type of warrior each is
    protected String warriorID;

    //stores xy coords
    protected Vector330 xyCoords = new Vector330();

    //string to store each warrior's image
    protected String imageFile;
    protected Image warriorImage;
    protected String victoryImage;

    //sets warrior appropiate audio files
    protected String dodgeSound;
    protected String hitSound;

    //stores the xy coords after a move has been made, but before their position is updated
    protected Vector330 nextxyCoords = new Vector330();

    //int to store distance to nearest enemy
    protected double nextEnemy;
    protected int nextEnemyIndex;
    protected int nextEnemyArmyIndex;

    //boolean to track whether theyve fought that turn yet
    boolean combat = false;

    /**
     * Warrior - constructor for a basic warrior
     */
    public Warrior() {
        strength = BattleSim.randInt(20, 25);
        hp = 75;

        agility = BattleSim.randInt(0, 10);

        movementSpeed = 10;
        attackSpeed = 2;

        range = 20;

        warriorID = "Normal Warrior";
        imageFile = "saiyan.png";

        dodgeSound = "teleport.wav";
        hitSound = "punch.wav";

    }

    /**
     * draw - draws the current warrior's image to the screen
     *
     * @param g - a graphics2D object
     * @param xPos - the x position at which to draw the warrior
     * @param yPos - the y position at which to draw the warrior
     */
    public void draw(Graphics2D g, int xPos, int yPos) {
        if (warriorImage == null){
            loadImage(g);
        }

        g.drawImage(warriorImage, xPos, yPos, WARRIOR_WIDTH, WARRIOR_HEIGHT, null);
    }

    /**
     * loadImage - loads the image for a warrior from its file
     * @param g - a graphics2D object
     */
    public void loadImage(Graphics2D g){
        ImageIcon image = new ImageIcon(imageFile);
        Image imagePrint = image.getImage().getScaledInstance(WARRIOR_WIDTH, WARRIOR_HEIGHT, Image.SCALE_SMOOTH);
        new ImageIcon(imagePrint);
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));

        warriorImage = imagePrint;
    }
}