/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;


// Equip class that inherits Move abstract class and, in its execution,
// enables the equipment of armor and weapon artifacts
public class Equip extends Move {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for Equip class
    public Equip(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "EQUIP" command : equip artifact from player's collection
    // of artifacts (armor or weapon)
    public boolean execute() {
        if (arg.matches("EQUIP .*")) {
            arg = arg.replace("EQUIP ", "").trim();
            ArrayList<Artifact> matches = p.followArtifact(arg);

            if (!matches.isEmpty()) {
                int n = 1;
                if (matches.size() == 1) {                 // exact match :
                    Artifact a = matches.get(n - 1);       //   get   artifact
                    p.equip(a);                            //   equip artifact
                }
                else {                                     // possible matches :
                    n = UI.requestNumber(matches, "equip");//   request match #
                    Artifact a = matches.get(n - 1);       //   get artifact
                    p.equip(a);                            //   equip artifact
                }
            }//end if...                                   // no match
            else System.out.printf("Sorry, you don\'t possess the artifact.\n");
        }//end if...                                       // invalid command
        else System.out.printf("Enter \'EQUIP\' followed by the name of " +
                               "the armor or weapon.\n");
        return false;
    }//end execute()
}//end Equip class
