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

// Get abstract class that inherits Move abstract class to implement Inspect 
//which commands checks the footprints in each place (GetNPC and GetPlayer)
public class Inspect extends Move {
    // abstract method : execute "GET" command
    
    Place charPlace;
    
    public Inspect(Place p)
    {
        charPlace = p;
    }
    
    public boolean execute()
    {
        charPlace.displayFPrints();
        return false;
    }
}//end Get class
