/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */


// DropNPC class that inherits Drop abstract class and, in its execution,
// is used to allow an NPC to attempt to drop one of its artifacts
public class DropNPC extends Drop {
    // private attributes
    private NPC n; // NPC


    // constructor for DropNPC class
    public DropNPC(NPC n) { this.n = n; }


    // execute "DROP" command : drop artifact from NPC's collection of
    // artifacts into current place
    public boolean execute() {
        Artifact a = n.getArtifact();
        if (a != null) {
            n.removeArtifact(a);                // remove from possessions
            n.getCurrentPlace().addArtifact(a); // add to place
        }
        return true;
    }//end execute()
}//end DropNPC class
