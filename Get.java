/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */


// Get abstract class that inherits Move abstract class and utilizes the
// command design pattern to encapsulate Get commands (GetNPC and GetPlayer)
public abstract class Get extends Move {
    // abstract method : execute "GET" command
    public abstract boolean execute();
}//end Get class
