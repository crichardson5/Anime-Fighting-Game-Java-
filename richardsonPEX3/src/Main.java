/**
 * Documentation and Notes
 * Referenced the Java API. A lot. For Audio, JOptionPane, Color, and UIManager
 *
 * Made my own copy of the DrawingPanel class to make my own version of draw background
 * at line 630 in order to implement a two-toned background
 *
 * Altered the vector330 class by adding a distanceBetween function that calculates the distance
 * between two vectors and modifying the equals function by changing its tolerance value to 15 to
 * ensure two warriors are not spawned directly on top of each other
 *
 * Implemented four different types of warriors and four different types of "special" warriors
 * Dragons didn't fit the theme so I came up with one type of special warrior for each army
 *
 * Warriors don't "run away" as it didn't fit the battle royale setup that was created and allowed for
 * more exploration with how other attributes affect combat
 *
 * NEW STUFF FOR PEX 3:
 * Worked with C2C Lantigua, C2C Dalton and C2C Smith to learn about how combo boxes/sliders work
 */

/**
 * Main - just tells battleSim to go and do its thing
 *
 * @author Caleb Richardson
 */
public class Main {

    public static void main (String[] args){
        BattleSim miniMassive = new BattleSim();

        miniMassive.runSim();

        //exits cleanly
        System.exit(0);


    }
}