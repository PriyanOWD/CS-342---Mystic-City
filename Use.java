/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.util.ArrayList;


// Use class that inherits Move abstract class and, in its execution,
// enables the use of key artifacts to unlock locked directions
public class Use extends Move {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for Use class
    public Use(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "USE" command : use key artifact from player's collection
    // of artifacts to unlock door
    public boolean execute() {
        if (arg.matches("USE .*")) {
            arg = arg.replace("USE ", "").trim();
            ArrayList<Artifact> matches = p.followArtifact(arg);

            if (!matches.isEmpty()) {
                int n = 1;
                if (matches.size() == 1) {                // exact match :
                    Artifact a = matches.get(n - 1);      //   get artifact
                    a.use(p.getCurrentPlace());           //   use artifact
                }
                else {                                    // possible matches :
                    n = UI.requestNumber(matches, "use"); //   request match #
                    Artifact a = matches.get(n - 1);      //   get artifact
                    a.use(p.getCurrentPlace());           //   use artifact
                }
            }//end if...                                  // no match
            else System.out.printf("Sorry, you don\'t possess the artifact.\n");
        }//end if...                                      // invalid command
        else System.out.printf("Enter \'USE\' followed by the name of " +
                               "the key.\n");
        return false;
    }//end execute()
}//end Use class
