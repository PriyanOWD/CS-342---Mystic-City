/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
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
        IO printIO = IO.getIO();
        if (arg.matches("BUY .*")) {
            if (!(p.currPlace instanceof Market)) {
                printIO.display(String.format("Sorry, the %s isn\'t a market." +
                        " Buy things at a market.\n", p.currPlace.name()));
                return false;
            } else {
                Market m = (Market) p.currPlace;
                if (m.soldOut()) {
                    printIO.display(String.format("Sorry, everything at the " +
                            "%s is sold out!\n", m.name()));
                    return false;
                }

                Artifact prize = new Artifact();
                boolean gotPrize = false;
                int userRoll = Integer.valueOf(arg.replace("BUY ", "").trim());

                gotPrize = m.winItem(userRoll,prize);

                if(gotPrize)
                {
                    if (prize.name() == "")
                        printIO.display("Sorry, you guessed the wrong number!" +
                                " Try again.\n");
                    else {
                        p.addArtifact(prize);
                        printIO.display(String.format("Congratulations! " +
                                "You won the %s.\n", prize.name().toLowerCase()));
                    }
                }
                else
                    printIO.display(String.format("Sorry, the %s is closed.\n",
                            m.name()));
            }
        }
        else printIO.display("Enter \'BUY\' followed by a random number [1,2].\n");
        return false;
    }//end execute()
}//end Use class