/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

import java.util.ArrayList;


// Use class that inherits Move abstract class and, in its execution,
// enables the use of key artifacts to unlock locked directions
public class Buy extends Move {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for Use class
    public Buy(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "USE" command : use key artifact from player's collection
    // of artifacts to unlock door
    public boolean execute() {
        if(! (p.currPlace instanceof Market))
        {
            System.out.println("Sorry, " + p.currPlace.name() + " is not a Market. "
                    + "You may only buy things at a Market");
            
            return false;
        }
        else
        {          
            Market m = (Market) p.currPlace;
            if(m.soldOut())
            {
                System.out.println("Sorry, everything at " + p.currPlace.name() + " is sold out!.");
                return false;
            }
            
            int userRoll = Integer.valueOf(arg.replace("BUY ", "").trim());
            Artifact prize = m.winItem(userRoll);
            
            if(prize == null)
                System.out.println("Sorry, didn't get anything!");
            else
            {
                p.addArtifact(prize);
                System.out.println("Congratulations! You won " + prize.name());
            }
            
        }  
        return false;
    }//end execute()
}//end Use class