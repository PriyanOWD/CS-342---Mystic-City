/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
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
