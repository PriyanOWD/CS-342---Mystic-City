/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */


// Inspect abstract class that inherits Move abstract class to implement Inspect
// which commands checks the footprints in each place
public class Inspect extends Move {
    // private attributes
    Place charPlace;


    // constructor for GoNPC class
    public Inspect(Place p) { charPlace = p; }


    // abstract method : execute "INSPECT" command
    public boolean execute() {
        charPlace.displayFPrints();
        return false;
    }
}//end Inspect class
