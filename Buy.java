/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */


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
        if (!(p.currPlace instanceof Market)) {
            System.out.printf("Sorry, %s isn\'t a market. " +
                              "Buy things at a market.\n", p.currPlace.name());
            return false;
        }
        else {
            Market m = (Market) p.currPlace;
            if (m.soldOut()) {
                System.out.printf("Sorry, everything at the %s is sold out!\n",
                                  p.currPlace.name());
                return false;
            }
            
            int userRoll = Integer.valueOf(arg.replace("BUY ", "").trim());
            Artifact prize = m.winItem(userRoll);
            
            if (prize == null)
                System.out.printf("Sorry, you guessed the wrong number! " +
                                  "Try again.\n");
            else {
                p.addArtifact(prize);
                System.out.printf("Congratulations! You won the %.\n",
                                  prize.name().toLowerCase());
            }
            
        }  
        return false;
    }//end execute()
}//end Use class