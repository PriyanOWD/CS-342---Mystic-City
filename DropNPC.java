/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
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
            IO printIO = IO.getIO();
            n.removeArtifact(a);                // remove from possessions
            n.getCurrentPlace().addArtifact(a); // add to place

            if (n.getCurrentPlace().name().matches("Room.*"))// place is room
                printIO.display(String.format("%s has dropped the %s in %s.\n",
                        n.name().replace("A ", "The "), a.name().toLowerCase(),
                        n.getCurrentPlace().name()));
            else                                             // place isn't room
                printIO.display(String.format("%s has dropped the %s in the %s.\n",
                        n.name().replace("A ", "The "), a.name().toLowerCase(),
                        n.getCurrentPlace().name()));

            try { Thread.sleep(1500); } catch (Exception e) { }
        }
        return true;
    }//end execute()
}//end DropNPC class
