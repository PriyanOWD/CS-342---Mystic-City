/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

import java.util.ArrayList;


// Consume class that inherits Move abstract class and, in its execution,
// enables the consumption of food artifacts
public class Consume extends Move {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for Equip class
    public Consume(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "CONSUME" command : consume artifact from player's collection
    public boolean execute() {
        if (arg.matches("EAT .*")) {
            arg = arg.replace("EAT ", "").trim();
            ArrayList<Artifact> matches = p.followArtifact(arg);

            if (!matches.isEmpty()) {
                int n = 1;
                if (matches.size() == 1) {                 // exact match :
                    Artifact a = matches.get(n - 1);       //   get   artifact
                    p.consume(a);                          //   consume artifact
                }
                else {                                     // possible matches :
                    n = UI.requestNumber(matches, "eat");  //   request match #
                    Artifact a = matches.get(n - 1);       //   get artifact
                    p.consume(a);                          //   consume artifact
                }
            }//end if...                                   // no match
            else System.out.printf("Sorry, you don\'t possess the artifact.\n");
        }//end if...                                       // invalid command
        else System.out.printf("Enter \'EAT\' or \'DRINK\' followed by the " +
                               "name of the food.\n");
        return false;
    }//end execute()
}//end Consume class
