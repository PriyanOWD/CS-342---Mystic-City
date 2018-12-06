/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Quit class that inherits Move abstract class and, in its execution,
// is used to quit or exit from the game
public class Quit extends Move {
    // execute "QUIT" command : quit game
    public boolean execute() {
        IO printIO = IO.getIO();
        printIO.display("Thanks for playing. Bye!\n");
        try { Thread.sleep(2000); } catch (Exception e) { }
        System.exit(0);
        return true;
    }//end execute()
}//end Quit class
