/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;


// DropPlayer class that inherits Drop abstract class and, in its execution,
// is used to drop an artifact from player's collection of artifacts
public class DropPlayer extends Drop {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for DropPlayer class
    public DropPlayer(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "DROP" command : drop artifact from player's collection
    // of artifacts into current place
    public boolean execute() {
        IO printIO = IO.getIO();
        if (arg.matches("DROP .*")) {
            arg = arg.replace("DROP ", "").trim();
            ArrayList<Artifact> matches = p.followArtifact(arg);

            if (!matches.isEmpty()) {
                int n = 1;                                 // default match
                if (matches.size() > 1)                    // possible matches :
                    n = UI.requestNumber(matches, "drop"); //   request match #

                Artifact a = matches.get(n - 1);    // get artifact
                p.removeArtifact(a);                // remove from possessions
                p.getCurrentPlace().addArtifact(a); // add to place
                printIO.display(String.format("You\'ve dropped the %s.\n",
                                a.name().toLowerCase()));
            }//end if...                            // no match
            else printIO.display("Sorry, you don\'t possess the artifact.\n");
        }//end if...                                // invalid command
        else printIO.display("Enter \'DROP\' followed by the name of " +
                             "the artifact.\n");
        return false;
    }//end execute()
}//end DropPlayer class
