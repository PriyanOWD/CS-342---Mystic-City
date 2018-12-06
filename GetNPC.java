/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// GetNPC class that inherits Get abstract class and, in its execution,
// is used to allow an NPC to attempt to get an artifacts
public class GetNPC extends Get {
    // private attributes
    private NPC n; // NPC


    // constructor for GetNPC class
    public GetNPC(NPC n) { this.n = n; }


    // execute "GET" command : get artifact from current place and
    // store it in NPC's collection of artifacts
    public boolean execute() {
        Artifact a = n.getCurrentPlace().getArtifact();
        if (a != null) {
            IO printIO = IO.getIO();
            n.getCurrentPlace().removeArtifact(a); // remove from place
            n.addArtifact(a);                      // add to possessions

            printIO.display(String.format("%s now possesses the %s.\n",
                            n.name(), a.name().toLowerCase()));

            try { Thread.sleep(2000); } catch (Exception e) { }
        }
        return true;
    }//end execute()
}//end GetNPC class
