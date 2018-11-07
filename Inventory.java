/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.util.ArrayList;


// Inventory class that inherits Move abstract class and, in its execution,
// is used to display list of player's collection of artifacts
public class Inventory extends Move {
    // private attributes
    private Player p;

    // constructor for Inventory class
    public Inventory(Player p) { this.p = p;             }

    // execute "INVE" command : display player's possessions
    public boolean execute()   { p.inve(); return false; }
}//end Inventory class
