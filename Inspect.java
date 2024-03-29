/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Inspect abstract class that inherits Move abstract class to implement Inspect
// which commands checks the footprints in each place
public class Inspect extends Move {
    Place charPlace;
    
    public Inspect(Place p) { charPlace = p; }

    // abstract method : execute "INSPECT" command
    public boolean execute()
    {
        charPlace.displayFPrints();
        return false;
    }
}//end Inspect class
