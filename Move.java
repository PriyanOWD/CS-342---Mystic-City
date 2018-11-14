/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

// Move abstract class that utilizes the command design pattern to encapsulate
// all of the game's various commands
// subclasses : Commands, Drop, Get, Go, Inventory, Look, Quit, Use
public abstract class Move {
    // abstract method : execute command
    public abstract boolean execute();
}//end Move abstract class
