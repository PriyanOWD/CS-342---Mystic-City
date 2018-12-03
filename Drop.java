/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Drop abstract class that inherits Move abstract class and utilizes the
// command design pattern to encapsulate Drop commands (DropNPC and DropPlayer)
public abstract class Drop extends Move {
    // abstract method : execute "DROP" command
    public abstract boolean execute();
}//end Drop abstract class
