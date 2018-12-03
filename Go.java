/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Go abstract class that inherits Move abstract class and utilizes the
// command design pattern to encapsulate Go commands (GoNPC and GoPlayer)
public abstract class Go extends Move {
    // abstract method : execute "GO" command
    public abstract boolean execute();
}//end Go abstract class
