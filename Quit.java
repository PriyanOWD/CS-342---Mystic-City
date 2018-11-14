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


// Quit class that inherits Move abstract class and, in its execution,
// is used to quit or exit from the game
public class Quit extends Move {
    // execute "QUIT" command : quit game
    public boolean execute() {
        System.out.printf("Thanks for playing. Bye!\n");
        System.exit(0);
        return true;
    }//end execute()
}//end Quit class
