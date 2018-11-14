/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
 */


// Idle class that inherits Move abstract class and, in its execution,
// is used to do nothing/terminate a move without doing anything
public class Idle extends Move {
    private String charName;
    // execute "Idle" command : do nothing
    Idle(String name)
    {
        super();
        charName = name;
    }
    public boolean execute() {
        System.out.printf(charName + " does nothing\n");
        return true;
    }//end execute()
}//end Quit class