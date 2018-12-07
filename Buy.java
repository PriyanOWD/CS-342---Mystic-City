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
                printIO.display(String.format("\nSorry, the %s isn\'t a market. " +
                        "Buy things at a market.\n", p.currPlace.name()));
                return false;
            } else {
                Market m = (Market) p.currPlace;
                if (m.soldOut()) {
                    printIO.display(String.format("Sorry, everything at the %s is sold out!\n",
                            p.currPlace.name()));
                    return false;
                }

                Food prize = new Food();
                boolean gotPrize = false;
                int userRoll = Integer.valueOf(arg.replace("BUY ", "").trim());
                if(userRoll<0 || userRoll>1)
                {
                    printIO.display("Only enter either 0 or 1 to win a prize\n");
                    return false;
                }
                gotPrize = m.winItem(userRoll,prize);

                if(gotPrize)
                {
                    if (prize.name() == "")
                        printIO.display("Sorry, you guessed the wrong number! " +
                                "Come back next time.\n");
                    else {
                        p.addArtifact(prize);
                        printIO.display(String.format("Congratulations! You won the %s. Come back again next time!\n",
                                prize.name().toLowerCase()));
                    }
                }
                else
                    printIO.display(String.format("Sorry, the %s is closed\n",
                            m.name().toLowerCase()));   
            }
        }
        else printIO.display("Enter \'BUY\' followed by a random number [1,5].\n");
        return false;
    }//end execute()
}//end Use class
