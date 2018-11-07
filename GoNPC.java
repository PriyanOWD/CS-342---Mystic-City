/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */


// GoNPC class that inherits Go abstract class and, in its execution,
// is used to allow an NPC to go from its current place to an adjacent place
public class GoNPC extends Go {
    // private attributes
    private NPC n; // NPC


    // constructor for GoNPC class
    public GoNPC(NPC n) { this.n = n; }


    // execute "GO" command : go to random direction
    public boolean execute() {
        // remove NPC from current place
        n.getCurrentPlace().removeCharacter(n);

        // update current place to random unlocked adjacent place
        n.setCurrentPlace(n.getCurrentPlace().getRandomDestination());

        // add NPC to current place
        n.getCurrentPlace().addCharacter(n);

        return true;
    }//end execute()
}//end GoNPC class
