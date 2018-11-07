/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.util.ArrayList;


// GetPlayer class that inherits Get abstract class and, in its execution,
// is used to add an artifact to player's collection of artifacts
public class GetPlayer extends Get {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for GetPlayer class
    public GetPlayer(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "GET" command : get artifact from player's current place and
    // store it in player's collection of artifacts
    public boolean execute() {
        if (arg.matches("GET .*")) {
            arg = arg.replace("GET ", "").trim();
            Place currPlace = p.getCurrentPlace();
            ArrayList<Artifact> matches = currPlace.followArtifact(arg);

            if (!matches.isEmpty()) {
                int n = 1;                                // default match
                if (matches.size() > 1)                   // potential matches :
                    n = UI.requestNumber(matches, "get"); //   request match #

                Artifact a = matches.get(n - 1); // get artifact
                currPlace.removeArtifact(a);     // remove from place
                p.addArtifact(a);                // add to possessions
                System.out.printf("You now possess the %s.\n",
                                  a.name().toLowerCase());
                UI.promptInventory(p);           // prompt inventory
            }//end if...                         // no match
            else System.out.printf("Sorry, the artifact wasn\'t found " +
                                   "or is locked.\n");
        }//end if...                             // invalid command
        else System.out.printf("Enter \'GET\' followed by the name of " +
                               "the artifact.\n");
        return false;
    }//end execute()
}//end GetPlayer class
