import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Army - class for creating and managing the armies in their simulated battle
 *
 * @author Caleb Richardson
 */
public class Army {
    //determines how many special warriors are in an army
    private static final int NUM_SPECIAL = 1;

    //attributes of an army
    protected int size;
    protected String armyID;
    protected String victoryMusic;
    protected String warriorType;
    protected String specialWarrior;

    //sets which alliance it is a part of
    protected int allianceID;

    //an array for opposing armies
    LinkedList<Army> opposingArmies = new LinkedList<>();

    //makes a list to store the warriors in each army
    protected LinkedList<Warrior> warriors = new LinkedList<>();

    //array to store warriors who have died but not yet been removed from linked list
    LinkedList<Integer> graveyard = new LinkedList<>();
    int graveyardIndex = 0;


    /**
     * Army - army constructor
     *
     * @param armySize - size of the army to be created
     * @param armyName - name of the army to be constructed
     * @param music - music to play upon victory for this army
     * @param warType - type of warriors within the army
     * @param special - type of special warrior(s) in the army
     * @param alliance - sets which alliance the members of the army are apart of
     */
    public Army(int armySize, String armyName, String music, String warType, String special, int alliance) {
        //sets attributes
        size = armySize;
        armyID = armyName;
        victoryMusic = music;
        warriorType = warType;
        specialWarrior = special;
        allianceID = alliance;

        Warrior arrayWarriors[] = new Warrior[size];
        //goes through for the size of the entire army and adds the appropriate warrior type
        for (int i = 0; i < armySize - NUM_SPECIAL; i++){
            if (warriorType.equals("Saiyan")) {
                this.warriors.add(new Saiyan());
            } else if (warriorType.equals("Ninja")) {
                this.warriors.add(new Ninja());
            } else if (warriorType.equals("Soul Reaper")) {
                this.warriors.add(new SoulReaper());
            } else if (warriorType.equals("Hero")) {
                this.warriors.add(new Hero());
            } else {
                //if no valid warrior type is specified just adds a generic warrior
                this.warriors.add(new Warrior());
            }
        }

        //adds the special warrior(s)
        for (int i = (armySize - NUM_SPECIAL); i < armySize; i++){
            if(specialWarrior.contains("Saiyan")){
                this.warriors.add(new SuperSaiyan());
            } else if (specialWarrior.contains("Jinchuriki")) {
                this.warriors.add(new Jinchuriki());
            } else if (specialWarrior.contains("Shinigami")) {
                this.warriors.add(new SubstituteShinigami());
            } else if (specialWarrior.contains("Punch")) {
                this.warriors.add(new OnePunch());
            } else {
                //if no valid special warrior type is specified just adds a generic warrior
                this.warriors.add(new Warrior());
            }
        }

    }

    public void remake(int armySize){
        Warrior arrayWarriors[] = new Warrior[size];
        //goes through for the size of the entire army and adds the appropriate warrior type
        for (int i = 0; i < armySize - NUM_SPECIAL - warriors.size() - graveyard.size(); i++){
            if (warriorType.equals("Saiyan")) {
                this.warriors.add(new Saiyan());
            } else if (warriorType.equals("Ninja")) {
                this.warriors.add(new Ninja());
            } else if (warriorType.equals("Soul Reaper")) {
                this.warriors.add(new SoulReaper());
            } else if (warriorType.equals("Hero")) {
                this.warriors.add(new Hero());
            } else {
                //if no valid warrior type is specified just adds a generic warrior
                this.warriors.add(new Warrior());
            }
        }

        boolean special = true;
        for (int i = 0; i < (warriors.size() + graveyard.size()); i++){
            if (warriors.get(i).warriorID.contains("The")){
                special = false;
            }
        }
        if (!special) {
            //adds the special warrior(s)
            for (int i = (armySize - NUM_SPECIAL); i < armySize; i++) {
                if (specialWarrior.contains("Saiyan")) {
                    this.warriors.add(new SuperSaiyan());
                } else if (specialWarrior.contains("Jinchuriki")) {
                    this.warriors.add(new Jinchuriki());
                } else if (specialWarrior.contains("Shinigami")) {
                    this.warriors.add(new SubstituteShinigami());
                } else if (specialWarrior.contains("Punch")) {
                    this.warriors.add(new OnePunch());
                } else {
                    //if no valid special warrior type is specified just adds a generic warrior
                    this.warriors.add(new Warrior());
                }
            }
        }
    }
    /**
     * drawArmy - draws the current army to the screen
     *
     * @param g - a graphics2D object
     */
    public void drawArmy(Graphics2D g) {
        for (int i = 0; i < this.warriors.size(); i++) {
            this.warriors.get(i).draw(g, this.warriors.get(i).xyCoords.getVectorXint(), this.warriors.get(i).xyCoords.getVectorYint());
        }
    }

    /**
     * updatePos - updates the x and y position of the current army with the nextXYCoords list
     */
    public void updatePos() {
        for (int i = 0; i < this.warriors.size(); i++) {
            this.warriors.get(i).xyCoords = this.warriors.get(i).nextxyCoords;
        }
    }

    /**
     * removeLost - adds any warriors who have fallen off the map to the graveyard and removes them from the warriors list
     */
    public void removeLost() {
        //makes an array to store the perished warriors
        int[] deadWarriors = new int[this.warriors.size()];
        int deadWarriorIndex = 0;

        for (int i = 0; i < this.warriors.size(); i++) {
            //adds any warrior who has fallen off the map to the dead warrior list
            if (this.warriors.get(i).xyCoords.getVectorX() > BattleSim.BATTLE_WINDOW_WIDTH | this.warriors.get(i).xyCoords.getVectorX() < 0 | this.warriors.get(i).xyCoords.getVectorY() > BattleSim.WINDOW_HEIGHT | this.warriors.get(i).xyCoords.getVectorY() < 0) {
                deadWarriors[deadWarriorIndex] = i;
                deadWarriorIndex++;
            }
        }

        for (int i = 0; i < deadWarriorIndex; i++) { //removes all warriors in the dead warrior list
            this.warriors.remove(deadWarriors[i]);
        }

    }

    /**
     * moveArmy - moves each member of the given army towards the closest enemy
     */
    public void moveArmy() {
        //move army
        for (int i = 0; i < this.warriors.size(); i++) {
            Warrior currentWarrior = this.warriors.get(i);

            double closestDistance = BattleSim.BATTLE_WINDOW_WIDTH;

            //find the nearest enemy
            //cycles through all opposing armies
            for (int j = 0; j < opposingArmies.size(); j++) {
                for (int k = 0; k < opposingArmies.get(j).warriors.size(); k++) {

                    //get the distance to the next enemy
                    double nextEnemyDistance = currentWarrior.xyCoords.distanceBetween(opposingArmies.get(j).warriors.get(k).xyCoords);

                    //determine if it is closer than anything it has seen yet, if so update
                    if (nextEnemyDistance < closestDistance) {
                        closestDistance = nextEnemyDistance;
                        currentWarrior.nextEnemyArmyIndex = j;
                        currentWarrior.nextEnemyIndex = k;
                    }
                }
            }

            //move toward closest enemy
            Vector330 moveVector = currentWarrior.xyCoords.subtract(opposingArmies.get(currentWarrior.nextEnemyArmyIndex).warriors.get(currentWarrior.nextEnemyIndex).xyCoords);

            //sets the distance to the next enemy as the magnitude of this move vector
            currentWarrior.nextEnemy = moveVector.magnitude();

            //normalize the vector and scale it by the speed of the warrior
            moveVector = moveVector.normalize();

            //to make the movement more random, scale the movement speed by a percentage between 0.8 and 1
            double randMove = BattleSim.randDouble(0.8, 1.0);
            moveVector = moveVector.scale(currentWarrior.movementSpeed * randMove);

            //subtract the move vector from the position vector to get the new position of the warrior
            currentWarrior.nextxyCoords = currentWarrior.xyCoords.subtract(moveVector);
        }
    }

    /**
     * victory - executes a victory sequence that includes displaying a victory message and playing the corresponding victory music for each army
     *
     * @param battleUpdates - the list of text updates that are being shared with the user
     * @return - the audio file created so the BattleSim class can mute it and stop it accordingly
     */
    public Audio victory(LinkedList<String> battleUpdates, BattleSim battle){
        String pluralCheck;
        //checks to see if it should print an 's' after the warrior type or not
        if (this.warriors.size() > 1){
            pluralCheck = "s ";
        } else {
            pluralCheck = " ";
        }

        String warriorName;
        //sets the warrior name to display and displays an appropiate message based on the number and type of warrior(s) remaining
        if (this.warriors.size() > 1){
            warriorName = this.warriors.size() + " " + this.warriorType;
        } else if (this.warriors.get(0).warriorID.contains("The")){
            warriorName = "only " + this.specialWarrior;
        } else {
            warriorName =  "only " + this.warriors.size() + " " + this.warriorType;
        }

        //adds the appropriate message to the battleUpdates list
        if(battle.isGame() && this.armyID == "The Hero Association"){//if the game is playing and the user created team wins
            battleUpdates.add("Your army wins with " + warriorName + pluralCheck + "standing!");
        } else {
            battleUpdates.add(this.armyID + " wins with " + warriorName + pluralCheck + "standing!");
        }

        //sets the music to the victory music of the winning army
        Audio win = new Audio(this.victoryMusic);

        return win;
    }

    /**
     * drawVictory - draws the appropriate victory image for the current army
     *
     * @param g - a graphics2D object
     * @param xPos - the x position at which to draw the image
     * @param yPos - the y position at which to draw the image
     */
    public void drawVictory(Graphics2D g, int xPos, int yPos) {
        ImageIcon image = new ImageIcon(warriors.get(0).victoryImage);
        Image imagePrint = image.getImage().getScaledInstance(image.getIconWidth()/2, image.getIconHeight()/2, Image.SCALE_SMOOTH);
        new ImageIcon(imagePrint);
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g.drawImage(imagePrint,  (BattleSim.WINDOW_WIDTH/2) - image.getIconWidth()/4, yPos, image.getIconWidth()/2, image.getIconHeight()/2, null);
    }
}