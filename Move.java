/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Move abstract class that utilizes the command design pattern to encapsulate
// all of the game's various commands
// subclasses : Commands, Drop, Get, Go, Inventory, Look, Quit, Use
public abstract class Move {
    // abstract method : execute command
    public abstract boolean execute();
}//end Move abstract class
