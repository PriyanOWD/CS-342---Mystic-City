/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */


// Idle class that inherits Move abstract class and, in its execution,
// is used to do nothing/terminate a move without doing anything
public class Idle extends Move {
    // private attributes
    private String charName;

    // constructor for Idle class
    Idle(String name) { charName = name; }

    // execute "IDLE" command
    public boolean execute() {
        System.out.printf(charName + " does nothing\n");
        return true;
    }//end execute()
}//end Idle class
