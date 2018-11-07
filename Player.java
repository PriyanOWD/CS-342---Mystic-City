/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.util.ArrayList;
import java.util.Scanner;


// Player subclass inherits from Character class and encapsulates
// attributes and methods for Player objects
public class Player extends Character {
    private int playerNum;    // player #

    // constructor for Player class
    public Player(Scanner sc, int version, int num) {
        super(sc, version);   // call superclass primary constructor
        dm        = new UI(); // allocate UI decision maker
        playerNum = num;      // set player number
    }//end class constructor


    // overloaded constructor for Player class to support pre-4.0
    // backward compatibility
    public Player(int _id, String _name, String _desc, int _num) {
        super(_id, _name, _desc); // call superclass overloaded constructor
        dm        = new UI();     // allocate UI decision maker
        playerNum = _num;         // set player number
    }//end overloaded constructor


    // return player #
    public int playerNum() { return playerNum; }


    // get next move from player and execute it, returning false for all
    // move executions except for "GO" (success returns true)
    public boolean makeMove() {
        Move m = dm.getMove(this); // get move
        return m.execute();        // execute move
    }//end makeMove()


    // check if player has valid artifact corresponding to passed string and,
    // if so, remove and return it for use in "DROP" and "USE" commands
    public ArrayList<Artifact> followArtifact(String str) {
        ArrayList<Artifact> matches = new ArrayList<Artifact>();

        // iterate thru Place object's collection of artifacts
        for (Artifact a : artifacts) {
            if (a.match(str)) {     // exact match found :
                matches.add(a);     //   add it to list of matches,
                return matches;     //   and return it
            }//end if...
        }//end for...

        // iterate thru Place object's collection of artifacts
        for (Artifact a : artifacts) {
            if (a.matchTokens(str)) // potential match found :
                matches.add(a);     //   add it to list of matches
        }//end for...

        return matches;             // return all matches
    }//end followArtifact()


    // check if player has reached exit for use in play() method's "GO" command
    public boolean reachedExit() {
        if (currPlace == Place.getPlaceByID(1)) {
            System.out.printf("You've reached the exit. Thanks for playing!\n");
            return true; // reached
        }
        return false;    // not reached
    }//end reachedExit()


    // display current place for use in "LOOK" command
    public void look(int newlines) {
        for (int i = 0; i < newlines; i++) // # of newlines :
            System.out.printf("\n");       //   print newline

        currPlace.display(this);           // display current place
    }//end look();


    // display player's possessions (collection of artifacts) for use in
    // "INVE" and "GET" commands
    public void inve() {
        System.out.printf("\n");                                 // newline
        UI.printHeader(String.format("%s's Inventory", name())); // print header

        if      (artifacts.size() == 0)  // player doesn't possess artifacts
            UI.printFormat(String.format("%s, you currently possess no " +
                           "artifacts.\nUse the \'GET\' and \'DROP\' "   +
                           "commands to pick up and drop artifacts.",
                           name().replace("The ", "").replace("A ", "")));
        else if (artifacts.size() == 1)  // player possesses one artifact
            UI.printFormat(String.format("%s, you currently possess the " +
                           "following artifact.",
                           name().replace("The ", "").replace("A ", "")));
        else                             // player possesses multiple artifacts
            UI.printFormat(String.format("%s, you currently possess the " +
                           "following artifacts.",
                           name().replace("The ", "").replace("A ", "")));

        int count         = 0;           // # of artifacts
        int totalValue    = 0;           // total value
        int totalMobility = 0;           // total weight
        for (Artifact a : artifacts) {   // iterate thru artifacts :
            totalValue    += a.value();  //   add to total value
            totalMobility += a.weight(); //   add to total weight
            a.inventory(++count);        //   display inventory info
        }//end for...

        if (count > 0)                   // print total value and total weight
            UI.printFormat(String.format(" \nTotal value: %d, Total weight: %d",
                           totalValue, totalMobility));
        UI.printDivider(2);              // print divider
    }//end display()
}//end Player class
