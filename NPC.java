/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


// NPC subclass inherits from Character class and encapsulates
// attributes and methods for NPC objects
public class NPC extends Character {
    // constructor for NPC class
    public NPC(Scanner sc, int version) {
        super(sc, version);   // call superclass constructor
        dm = new AI();        // allocate AI decision maker
    }//end class constructor


    // for now, NPCs simply utilize the GO command in random directions
    public boolean makeMove() {
        Move m = dm.getMove(this); // get move
        return m.execute();        // execute move
    }//end makeMove()
}//end NPC class
