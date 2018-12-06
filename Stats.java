/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Stats class that inherits Move abstract class and, in its execution,
// is used to display list of player's stats
public class Stats extends Move {
    // private attributes
    private Player p;

    // constructor for Inventory class
    public Stats(Player p)   { this.p = p;              }

    // execute "INVE" command : display player's possessions
    public boolean execute() { p.stats(); return false; }
}//end Stats class
