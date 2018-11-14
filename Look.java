/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

// Look class that inherits Move abstract class and, in its execution,
// is used to display player's current place
public class Look extends Move {
    // private attributes
    private Player p;

    // constructor for Look class
    public Look(Player p)    { this.p = p;              }

    // execute "LOOK" command : display player's current place
    public boolean execute() { p.look(1); return false; }
}//end Look class
