import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;


enum armyPresets{
    SAIYAN, NINJA, SHINIGAMI, HERO
}
/**
 * BattleSim - runs the battle simulation
 *
 * @author Caleb Richardson
 */
public class BattleSim {
    //sets constants for the size of the window
    protected static final int BATTLE_WINDOW_WIDTH = 800;
    protected static final int WINDOW_HEIGHT = 700;
    //a second width for displaying the sidebar to the right of the screen
    protected static final int WINDOW_WIDTH = BATTLE_WINDOW_WIDTH + 400;

    //sets a constant for the size of the armies
    private static final int ARMY_SIZE = 10;

    //sets constants for the background and sidebar colors
    private static final Color BACKGROUND_COLOR = Color.BLUE;
    private static final Color SIDEBAR_COLOR = Color.BLACK;

    //sets the number of armys
    public static int numArmys = 0;
    private static int initialNumArmies = 0;
    LinkedList<armyPresets> initEnum = new LinkedList<>();
    LinkedList<Integer> initAlly = new LinkedList<>();
    LinkedList<String> initString = new LinkedList<>();

    private boolean simBegun;

    public void setSimBegun(boolean simBegun) {
        this.simBegun = simBegun;
    }

    //tracks whether the program is muted
    private boolean muted;

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    private boolean game = false;
    public void setGame(boolean game){
        this.game = game;
    }

    public boolean isGame(){
        return game;
    }

    //tracks whether the program updates visuals regularly (true) or sparsely (false)
    private boolean visuals;
    public void setVisuals(boolean visuals) {
        this.visuals = visuals;
    }

    //variables for the army
    private LinkedList<String> armyNames = new LinkedList<>();
    private LinkedList<String> victoryMusic = new LinkedList<>();
    private LinkedList<String> warriorType = new LinkedList<>();
    private LinkedList<String> specialWarrior = new LinkedList<>();

    private LinkedList<Integer> allianceID = new LinkedList<>();
    private int numAlliances = 0;


    private JavaDrawingPanel w;

    public LinkedList<Army> getArmies() {
        return armies;
    }

    private LinkedList<Army> armies = new LinkedList<>();

    private MainMenu mainMenu = new MainMenu(this);

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    private boolean pause = false;



    //seeds random number
    private static Random random = new Random(System.currentTimeMillis());

    //list to hold updates on the battle to display to the user
    protected LinkedList<String> battleUpdates = new LinkedList<>();

    public void launchMainMenu(){

    }

    /**
     * runSim - runs the battle simulation
     */
    public void runSim() {
        //makes a new window
        w = new JavaDrawingPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
        w.setBackground(BACKGROUND_COLOR, WINDOW_WIDTH, WINDOW_HEIGHT);

        //creates a graphics2D object to be used for graphics
        Graphics2D g = w.getGraphics();

        //draws the main menu text
        g.setFont(new Font("High Tower Text", Font.PLAIN, 50));
        //sets the font color to orange
        g.setColor(Color.ORANGE);

        //sets the intro text; the title of the program and version number
        String introString = "Anime War";
        g.drawString(introString, (WINDOW_WIDTH / 2)  - (introString.length()/2 * 30), (WINDOW_HEIGHT / 2) - 50);
        String versionString = "Version 2.4";
        g.drawString(versionString, (WINDOW_WIDTH / 2) - (versionString.length()/2 * 24), (WINDOW_HEIGHT / 2) + 25);
        w.copyGraphicsToScreen();

        //plays the intro music
        Audio intro = new Audio("intro.wav");
        intro.play();

        //sets values for preset armies
        armyNames.add("The Saiyan Army");
        victoryMusic.add("saiyanWin.wav");
        warriorType.add("Saiyan");
        specialWarrior.add("The Super Saiyan");

        armyNames.add("The Hidden Leaf Village");
        victoryMusic.add("ninjaWin.wav");
        warriorType.add("Ninja");
        specialWarrior.add("The Jinchuriki");

        armyNames.add("The Hero Association");
        victoryMusic.add("heroWin.wav");
        warriorType.add("Hero");
        specialWarrior.add("The One Punch Man");

        armyNames.add("The Soul Society");
        victoryMusic.add("bleachWin.wav");
        warriorType.add("Soul Reaper");
        specialWarrior.add("The Substitute Shinigami");

        addArmy(armyPresets.SAIYAN, 1);
        initEnum.add(armyPresets.SAIYAN);
        initAlly.add(1);
        initString.add(armies.get(armies.size()-1).armyID);

        addArmy(armyPresets.NINJA, 2);
        initEnum.add(armyPresets.NINJA);
        initAlly.add(2);
        initString.add(armies.get(armies.size()-1).armyID);

        addArmy(armyPresets.HERO, 3);
        initEnum.add(armyPresets.HERO);
        initAlly.add(3);
        initString.add(armies.get(armies.size()-1).armyID);

        addArmy(armyPresets.SHINIGAMI, 4);
        initEnum.add(armyPresets.SHINIGAMI);
        initAlly.add(4);
        initString.add(armies.get(armies.size()-1).armyID);

        JFrame frame = new JFrame("MainMenu");
        frame.setContentPane(mainMenu.getMainMenu());
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        //waits until the user begins the simulation
        while(!simBegun){
            sleep(100);
        }

        //stops playing the intro music
        intro.stop();

        //lets the user know the sim is beginning
        battleUpdates.add("Let the battle begin");

        //uses linked lists for the following variables for extensibility

        //holds the starting position of each army
        int[] positionBounds =
                {50, 150, 100, WINDOW_HEIGHT - 100, //army 1
                BATTLE_WINDOW_WIDTH - 150, BATTLE_WINDOW_WIDTH - 50, 100, WINDOW_HEIGHT - 100, //army 2
                100, BATTLE_WINDOW_WIDTH -100, 100, 200, //army 3
                100, BATTLE_WINDOW_WIDTH -100, WINDOW_HEIGHT - 200, WINDOW_HEIGHT - 100}; //army 4

        //sets which alliance each army is a part of


        //records the number of alliances for determining endgame based on the number of unique alliance numbers

        for (int i = 0; i < allianceID.size(); i++){
            boolean unique = true;
            for(int j = 0; j < i; j++){
                //checks to see if the current alliance id has been seen before in the array
                if (allianceID.get(i).equals(allianceID.get(j))){
                    unique = false;
                }
            }
            //if the current index represents a unique alliance id, increments the array
            if (unique){
                numAlliances++;
            }
        }

        //fills each armies opposingArmies array
        for (int i = 0; i < numArmys; i++) {
            //cycles through all other armies
            for (int j = 0; j < numArmys; j++) {
                //if the alliance id is different and it is not the same army
                if (armies.get(j).allianceID != armies.get(i).allianceID && i != j) {
                    armies.get(i).opposingArmies.add(armies.get(j));
                }
            }
        }

        //sets the initial position of the warriors
        for (int i = 0; i < numArmys; i++) {
            for (int j = 0; j < ARMY_SIZE; j++) {
                //sets the initial coordinates of each warrior based on the position bounds array
                armies.get(i).warriors.get(j).xyCoords = getXY(positionBounds[(4 * i)], positionBounds[1 + (4 * i)], positionBounds[2 + (4 * i)], positionBounds[3 + (4 * i)]);
                //makes each initial position is unique
                boolean unique;
                do {
                    unique = true;
                    for (int k = 0; k < j; k++) {
                        //if another warrior in the same army has the same x and y position
                        if (armies.get(i).warriors.get(j).xyCoords.equals(armies.get(i).warriors.get(k).xyCoords)) {
                            //finds a new x and y position
                            armies.get(i).warriors.get(j).xyCoords = getXY(positionBounds[(4 * i)], positionBounds[1 + (4 * i)], positionBounds[2 + (4 * i)], positionBounds[3 + (4 * i)]);
                            unique = false;
                        }
                    }
                    //runs until a unique position has been found
                } while (!unique);
            }
        }

        //bool to mark when only one alliance is left
        boolean battleOver = false;

        //starts the fight music
        Audio fightMusic = new Audio("fightMusic.wav");
        if (!muted) {
            fightMusic.setVolume(0.3);
            fightMusic.play();
        }


        //records the initial number of armies
        initialNumArmies = numArmys;
        //runs the simulation until only one alliance is left
        while (!battleOver && numArmys > 1) {

            //set the title  and background color of the window
            w.setWindowTitle("Anime War!");
            w.setBackground(SIDEBAR_COLOR, WINDOW_WIDTH, WINDOW_HEIGHT);
            w.setBackground(BACKGROUND_COLOR, BATTLE_WINDOW_WIDTH, WINDOW_HEIGHT);
            drawSidebar(g, armies);

            //draw the positions of the warriors
            for (int i = 0; i < numArmys; i++) {
                armies.get(i).drawArmy(g);
            }

            //updates the screen
            w.copyGraphicsToScreen();

            //moves both armys
            for (int i = 0; i < numArmys; i++) {
                armies.get(i).moveArmy();
            }

            //after all armys have moved
            for (int i = 0; i < numArmys; i++) {
                //update their xy positions and remove any warriors who have exited the battlefield
                armies.get(i).updatePos();
                armies.get(i).removeLost();

            }

            //runs through every army to check its warriors for potential combat
            for (int i = 0; i < numArmys; i++) {
                //runs through every warrior in the given army
                for (int j = 0; j < armies.get(i).warriors.size(); j++) {

                    //because combat can run so long also checks for user mouse/keyboard input here
                    if (w.mouseClickHasOccurred(JavaDrawingPanel.LEFT_BUTTON)) {
                        w.closeWindow();
                        System.exit(0);
                    } else if (w.keyHasBeenHit(JavaDrawingPanel.SPACE_KEY) | pause) {
                        pause();
                    }

                    //sets the current warrior
                    Warrior currentWarrior = armies.get(i).warriors.get(j);

                    //sets the attacking and defending army to make the combat code easier to read
                    Army attackingArmy = armies.get(i);
                    Army defendingArmy = attackingArmy.opposingArmies.get(currentWarrior.nextEnemyArmyIndex);

                    //sees if the nearest enemy is in range
                    if(currentWarrior.nextEnemy < currentWarrior.range && !currentWarrior.combat && !defendingArmy.warriors.get(currentWarrior.nextEnemyIndex).combat){
                        //gets the loser from the fight
                        int loser = runCombat(currentWarrior, defendingArmy.warriors.get(currentWarrior.nextEnemyIndex), g, w ,armies);

                        //indicates that both warriors have been in combat this turn
                        currentWarrior.combat = true;
                        defendingArmy.warriors.get(currentWarrior.nextEnemyIndex).combat = true;

                        //adds the appropriate losing warrior to the graveyard
                        if (loser == 1) {
                            attackingArmy.graveyard.add(j);
                            attackingArmy.graveyardIndex++;
                        } else if (loser == 2) {
                            defendingArmy.graveyard.add(currentWarrior.nextEnemyIndex);
                            defendingArmy.graveyardIndex++;
                        } else {
                            System.out.print("Wack stuff returned fom runCombat");
                            System.exit(-2);
                        }

                        refreshWindow(w, g, armies);
                    }
                }
            }

            //runs throught all of the armies to account for dead warriors
            for (int i = 0; i < numArmys; i++){
                //makes sure the graveyard array is sorted from least to greatest
                sort(armies.get(i).graveyard);
                //removes appropriate warriors from each army
                int listNum;
                for (int j = 0; j < armies.get(i).graveyardIndex; j++){
                    listNum = armies.get(i).graveyard.get(0);
                    armies.get(i).warriors.remove(listNum - j);
                    armies.get(i).graveyard.remove(0);
                }
                //resets each armies graveyard index
                armies.get(i).graveyardIndex = 0;
            }

            refreshWindow(w, g, armies);

            //check to see if an army has been wiped out
            for (int i = 0; i < numArmys; i++) {
                if (armies.get(i).warriors.size() <= 0) {
                    //check to make sure they have no allies who are still alive
                    boolean liveAllies = false;
                    for (int j = 0; j < numArmys; j++) {
                        //if they have the same alliance but are not the same army
                        if ((armies.get(i).allianceID == armies.get(j).allianceID) && i != j) {
                            liveAllies = true;
                        }
                    }

                    if (!liveAllies) {
                        //if they have no live allies decrement the number of alliances
                        numAlliances--;
                    }

                    //remove the army from the game
                    numArmys--;
                    armies.remove(i);
                }
            }

            //if there is only one alliance left the battle is over
            if (numAlliances == 1) {
                battleOver = true;
            }

            //resets combat bool for all warriors
            for (int i = 0; i < numArmys; i++){
                for (int j = 0; j < armies.get(i).warriors.size(); j++){
                    armies.get(i).warriors.get(j).combat = false;
                }
            }

        }
        //stops the fight music
        if (fightMusic.isPlaying()) {
            fightMusic.stop();
        }

        //if the battle ended cleanly
        if (numAlliances == 1) {
            //sets the default music to the intro music
            Audio winMusic = new Audio("intro.wav");

            //tracks the number of winners, currently no option for tie because warriors will fight till the last man standing
            //if there is an alliance, just picks the last winning army in the array
            int numWinners = 0;
            Army victor = armies.get(0);
            for (int i = 0; i < numArmys; i++) {
                if (armies.get(i).warriors.size() > 0) {
                    //if an army won make their music the win music and add their victory to battleUpdates
                    winMusic = armies.get(i).victory(battleUpdates, this);
                    numWinners++;
                    victor = armies.get(i);
                }
            }

            //closes the gui
            frame.dispose();

            //plays the victory music (plays even if main simulation is muted
            winMusic.play();

            //draws the exit screen
            w.setBackground(Color.BLUE, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setFont(new Font("High Tower Text", Font.PLAIN, 40));
            g.setColor(Color.ORANGE);

            //prints the winners from battleUpdates
            for (int i = 0; i < numWinners; i++) {
                g.drawString(battleUpdates.get(battleUpdates.size() - (i + 1)), (WINDOW_WIDTH/2) - (battleUpdates.get(battleUpdates.size() - (i +1)).length() * 9), (WINDOW_HEIGHT / 2) - 250 - (40 * i));
            }

            //draw victory image
            victor.drawVictory(g, 400, (WINDOW_HEIGHT / 2) -225);
            w.copyGraphicsToScreen();

            //if music is playing stop at the end screen
            while (!w.mouseClickHasOccurred(JavaDrawingPanel.LEFT_BUTTON) && winMusic.isPlaying()) {
                sleep(100);
            }
        }

        //closes the drawing panel window
        w.closeWindow();
    }

    /**
     * runCombat - runs combat between two fighters, regularly updating the screen to let the user know what is happening (hence all the graphical parameters)
     *
     * @param fighter1 - the first combatant
     * @param fighter2 - the second combatant
     * @param g - the Graphics2D object
     * @param w - the drawing panel window
     * @param armies - the array of armies in the fight
     * @return - the loser of the fight
     */
    public int runCombat(Warrior fighter1, Warrior fighter2, Graphics2D g, JavaDrawingPanel w, LinkedList<Army> armies) {

        //does the roll of a 20 sided die to set the attack speed value for the first fighter
        double AS1 = fighter1.attackSpeed * getMultiplier();

        //does the same for the second fighter
        double AS2 = fighter2.attackSpeed * getMultiplier();

        //higher attack speed attacks first
        Warrior currentFighter;
        Warrior currentTarget;
        if (AS1 > AS2) { //if the first fighter has a higher attack speed, set them as the current fighter
            currentFighter = fighter1;
            currentTarget = fighter2;
        } else if (AS2 > AS1) { //if the second fighter's is greater, set them as the current fighter
            currentFighter = fighter2;
            currentTarget = fighter1;
        } else { //if they are equal randomly select
            int chooseRandFighter = randInt(1, 2);
            if (chooseRandFighter == 1) {
                currentFighter = fighter1;
                currentTarget = fighter2;
            } else {
                currentFighter = fighter2;
                currentTarget = fighter1;
            }
        }


        boolean fightersAlive = true;
        Warrior deadWarrior = new Warrior();
        //while neither of the fighters has no hitpoints
        while (fightersAlive) {
            //because combat can run so long also checks for user mouse/keyboard input here to ensure program responds to user input rapidly
            if (w.mouseClickHasOccurred(JavaDrawingPanel.LEFT_BUTTON)) {
                w.closeWindow();
                System.exit(0);
            } else if (w.keyHasBeenHit(JavaDrawingPanel.SPACE_KEY) | pause) {
                pause();
            }

            //does an agility check on the current target to determine if hit or miss
            //rolls a 20 sided die and gets a multiplier
            double agilityCheck = currentTarget.agility * getMultiplier();

            //dodges if agility check is 80 or better
            if (agilityCheck >= 80) {
                //attack was missed and continues on to next fighters turn

                //adds to battle updates
                battleUpdates.add(currentTarget.warriorID + " dodges the " + currentFighter.warriorID + "'s attack");
                refreshWindow(w, g, armies);

                //plays the appropriate dodge sound based on the fighter
                if(!muted) {
                    Audio dodge = new Audio(currentTarget.dodgeSound);
                    dodge.play();
                    sleep(500);
                }
            } else {
                //plays the appropriate punch sound based on the fighter
                if (!muted) {
                    Audio punch = new Audio(currentFighter.hitSound);
                    punch.play();
                    sleep(250);
                }
                //if hit, does a damage check on current fighter to determine damage
                int damageCheck = (int) (currentFighter.strength * getMultiplier());

                //subtracts hp from current target
                currentTarget.hp -= damageCheck;

                //adds to battle updates
                battleUpdates.add(currentFighter.warriorID + " hits " + currentTarget.warriorID + " for " + damageCheck + " damage");
                refreshWindow(w, g, armies);

                //checks to make sure fighter 2 is not dead
                if (currentTarget.hp <= 0) {
                    deadWarrior = currentTarget;
                    fightersAlive = false;
                    battleUpdates.add(currentFighter.warriorID + " beats " + currentTarget.warriorID);
                }
            }

            //switches the current fighter and the current target
            Warrior temp = currentFighter;
            currentFighter = currentTarget;
            currentTarget = temp;
        }

        //plays the death sound
        if (!muted) {
            Audio death = new Audio("death.wav");
            death.play();
        }

        //returns the losing fighter
        if (deadWarrior == fighter1) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * randInt - returns a random integer between two passed in boundaries
     *
     * @param lowerBound - lower bound integer of the number to be generated (inclusive)
     * @param upperBound - upper bound integer  of the number to be generated (inclusive)
     * @return - returns the random integer generated
     */
    public static int randInt(int lowerBound, int upperBound) {
        return random.nextInt((upperBound - lowerBound) + 1) + lowerBound;
    }

    /**
     * randDouble - returns a random double between two passed in boundaries
     *
     * @param lowerBound - lower bound double of the number to be generated (inclusive)
     * @param upperBound - upper bound double  of the number to be generated (inclusive)
     * @return - returns the random double generated
     */
    public static double randDouble(double lowerBound, double upperBound){
        return random.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }

    /**
     * getMultiplier - returns a new multiplier for an action based on a roll of a 20 sided dice
     *
     * @return - the generated multiplier
     */
    public static double getMultiplier() {
        //rolls a 20 sided dice
        double newMultiplier = randInt(1, 20);
        //generates a multiplier between 0.0 and 2.0 in increments of .1 based on the roll of the dice
        newMultiplier /= 10;

        return newMultiplier;
    }

    /**
     * sleep - sets the program to sleep for a certain measure of time in ms
     *
     * @param length - time to sleep in ms
     */
    public static void sleep(int length) {
        try {
            Thread.sleep(length);
        } catch (InterruptedException e) {
            System.out.print("Error: " + e);
        }
    }

    /**
     * getXY - creates a vector with random x and y coordinates
     *
     * @param leftBound - the lower bound of the x coordinate to be generated
     * @param rightBound - the upper bound of the x coordinate to be generated
     * @param upperBound - the lower bound of the y coordinate to be generated
     * @param lowerBound - the upper bound of the y coordinate to be generated
     * @return - the randomly generated vector
     */
    public static Vector330 getXY(int leftBound, int rightBound, int upperBound, int lowerBound) {

        //gets a random x and y position
        int xPos = randInt(leftBound, rightBound);
        int yPos = randInt(upperBound, lowerBound);


        return new Vector330(xPos, yPos);
    }

    /**
     * drawSidebar - draws the sidebar on the screen with battle updates
     *
     */
    public void drawSidebar(Graphics2D g, LinkedList<Army> armies) {
        //sets the font and color for the sidebar
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.GREEN);

        //determines how far from the edge of the sidebar window text should be drawn
        int drawWidth = BATTLE_WINDOW_WIDTH + 10;

        //draws the header for the sidebar
        g.drawString("Anime War", drawWidth, 50);

        //displays the remaining warriors for each army
        g.drawString("Warriors Remaining:", drawWidth, 100);
        for (int i = 0; i < numArmys; i++) {
            Army currentArmy = armies.get(i);
            g.drawString(currentArmy.armyID + ": " + (currentArmy.warriors.size() - currentArmy.graveyardIndex), drawWidth, 115 + (15 * i));
        }

        //displays the header for battle updates
        g.drawString("Battle Updates:", drawWidth, 180);

        //sets how many battle updates should be drawn
        int numMessagesToDisplay = 40;

        //sets the index of the loop to however many messages are in battle updates
        int indexCheck = battleUpdates.size();

        //adjusts the index that the loops starts at in order to only draw the most recent messages and only as many as specified
        //gives a scrolling effect when printed
        int indexVar = 0;
        if (indexCheck >= numMessagesToDisplay) {
            indexVar = indexCheck - numMessagesToDisplay;
        }

        //changes the color of the battle updates based on what type of update it is
        for (int i = indexVar; i < indexCheck; i++) {
            if (battleUpdates.get(i).contains("beats")) {
                g.setColor(Color.RED);
            } else if (battleUpdates.get(i).contains("dodges")) {
                g.setColor(Color.WHITE);
            } else if (battleUpdates.get(i).contains("hits")) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            //draws the update at its appropriate position, changes in accordance with index var
            g.drawString(battleUpdates.get(i), drawWidth, 195 + (15 * (i - indexVar)));
        }

        //gives user instructions on how to exit
        g.setColor(Color.GREEN);
        //displays the instructions on how to exit in accordance to how many battle updates are being drawn
        g.drawString("Click the left mouse button at any time to exit", drawWidth, 210 + (15 * numMessagesToDisplay));

    }

    /**
     * pause - pauses the the visuals of the simulation until the spacebar is pressed again while music continues to run in the background
     *
     */
    public void pause() {
        //dims the background with a transparent black overlay until the game is un-paused to give the user
        w.setBackground(new Color(0, 0, 0, (float) 0.5), WINDOW_WIDTH, WINDOW_HEIGHT);
        w.copyGraphicsToScreen();

        pause = true;
        //waits until the space bar is pressed again
        while (pause) {
            sleep(100);
        }

    }

    /**
     * refreshWindow - refreshes the graphics window
     *
     * @param w - the window to be refreshed
     * @param g - a Graphics2D object
     * @param armies - the array of armies to print on the window
     */
    public void refreshWindow(JavaDrawingPanel w, Graphics2D g, LinkedList<Army> armies) {
        //only refreshes the window if the user requests normal mode, in quick mode visuals are only updated a select number of times in the main simulation function
        if (visuals) {
            w.setBackground(SIDEBAR_COLOR, WINDOW_WIDTH, WINDOW_HEIGHT);
            w.setBackground(BACKGROUND_COLOR, BATTLE_WINDOW_WIDTH, WINDOW_HEIGHT);
            drawSidebar(g, armies);
            for (int i = 0; i < numArmys; i++) {
                armies.get(i).drawArmy(g);
            }
            w.copyGraphicsToScreen();
        }
    }

    /**
     * sort - sorts intended for the graveyard array to ensure that warriors are removed from the army list in ascending numerical order
     *
     * @param array - the linked list of integers to be sorted
     */
    public void sort(LinkedList<Integer> array) {
        boolean sorted = false;
        int temp;
        //runs until the array is sorted
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.size()- 1; i++) {
                //checks to see if the next index is greater than the current one, if so swaps them
                if (array.get(i) > array.get(i+1)) {
                    temp = array.get(i);
                    array.set(i, array.get(i+1));
                    array.set(i+1, temp);
                    sorted = false;
                }
            }
        }
    }

    /**
     * addArmy - adds a new army to the simulation
     *
     * @param presetArmy - the army preset to add
     * @param allianceNum - the alliance the army is a part of
     */
    public void addArmy(armyPresets presetArmy, int allianceNum){

        int addNum = 0;
        switch(presetArmy){

            case SAIYAN:
                addNum = 0;
                break;
            case NINJA:
                addNum = 1;
                break;
            case HERO:
                addNum = 2;
                break;
            case SHINIGAMI:
                addNum = 3;
                break;
        }

        allianceID.add(allianceNum);


        armies.add(new Army(ARMY_SIZE, armyNames.get(addNum),
                victoryMusic.get(addNum),
                warriorType.get(addNum),
                specialWarrior.get(addNum),
                allianceID.get(allianceID.size()-1)));
        mainMenu.getArmySelect().addItem(new ArmySelectComboBox( armyNames.get(addNum), armies.get(numArmys)));
        numArmys++;


    }

    /**
     * deletes an army from the simulation
     * @param army - the army to delete
     */
    public void deleteArmy(Army army){
        for(int i = 0; i < numArmys; i++){
            if(army == armies.get(i)){
                //check to make sure they have no allies who are still alive
                boolean liveAllies = false;
                for (int j = 0; j < numArmys; j++) {
                    //if they have the same alliance but are not the same army
                    if ((armies.get(i).allianceID == armies.get(j).allianceID) && i != j) {
                        liveAllies = true;
                    }
                }

                if (!liveAllies) {
                    //if they have no live allies decrement the number of alliances
                    numAlliances--;
                }

                armies.remove(i);
                numArmys--;
            }
        }

    }

    //resets the simulation to the way it was when it begun
    public void reset(){
        int removeNum = battleUpdates.size();
        for(int i = 0; i < removeNum; i++){
            battleUpdates.remove(0);
        }


        //removes the armies that are left before completely resetting them
        for(int i =0; i< numArmys; i++){
            armies.remove(0);
        }

        numArmys = 0;

        for(int i = 0; i <initialNumArmies; i++){
            addArmy(initEnum.get(i), initAlly.get(i));
        }



        //lets the user know the sim is beginning
        battleUpdates.add("Let the battle begin");

        //holds the starting position of each army
        int[] positionBounds =
                {50, 150, 100, WINDOW_HEIGHT - 100, //army 1
                        BATTLE_WINDOW_WIDTH - 150, BATTLE_WINDOW_WIDTH - 50, 100, WINDOW_HEIGHT - 100, //army 2
                        100, BATTLE_WINDOW_WIDTH -100, 100, 200, //army 3
                        100, BATTLE_WINDOW_WIDTH -100, WINDOW_HEIGHT - 200, WINDOW_HEIGHT - 100}; //army 4

        //sets which alliance each army is a part of

        //records the number of alliances for determining endgame based on the number of unique alliance numbers

        numAlliances = 0;
        for (int i = 0; i < allianceID.size(); i++){
            boolean unique = true;
            for(int j = 0; j < i; j++){
                //checks to see if the current alliance id has been seen before in the array
                if (allianceID.get(i).equals(allianceID.get(j))){
                    unique = false;
                }
            }
            //if the current index represents a unique alliance id, increments the array
            if (unique){
                numAlliances++;
            }
        }

        //fills each armies opposingArmies array
        for (int i = 0; i < numArmys; i++) {
            //cycles through all other armies
            for (int j = 0; j < numArmys; j++) {
                //if the alliance id is different and it is not the same army
                if (armies.get(j).allianceID != armies.get(i).allianceID && i != j) {
                    armies.get(i).opposingArmies.add(armies.get(j));
                }
            }
        }

        //sets the initial position of the warriors
        for (int i = 0; i < numArmys; i++) {
            for (int j = 0; j < ARMY_SIZE; j++) {
                //sets the initial coordinates of each warrior based on the position bounds array
                armies.get(i).warriors.get(j).xyCoords = getXY(positionBounds[(4 * i)], positionBounds[1 + (4 * i)], positionBounds[2 + (4 * i)], positionBounds[3 + (4 * i)]);
                //makes each initial position is unique
                boolean unique;
                do {
                    unique = true;
                    for (int k = 0; k < j; k++) {
                        //if another warrior in the same army has the same x and y position
                        if (armies.get(i).warriors.get(j).xyCoords.equals(armies.get(i).warriors.get(k).xyCoords)) {
                            //finds a new x and y position
                            armies.get(i).warriors.get(j).xyCoords = getXY(positionBounds[(4 * i)], positionBounds[1 + (4 * i)], positionBounds[2 + (4 * i)], positionBounds[3 + (4 * i)]);
                            unique = false;
                        }
                    }
                    //runs until a unique position has been found
                } while (!unique);
            }
        }

        //bool to mark when only one alliance is left
        boolean battleOver = false;

        //starts the fight music
        Audio fightMusic = new Audio("fightMusic.wav");
        if (!muted) {
            fightMusic.setVolume(0.3);
            fightMusic.play();
        }
    }

}