/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// GoPlayer class that inherits Go abstract class and, in its execution,
// is used to allow a player to go from its current place to an adjacent place
public class GoPlayer extends Go {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)


    // constructor for GoPlayer class
    public GoPlayer(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "GO" command : go to given direction
    public boolean execute() {
        IO printIO = IO.getIO();
        if (arg.equals("GO")) {                      // no directions
            printIO.display("Enter \'GO\' followed by the " +
                            "direction (e.g., N, S, E, W, U, D).\n");
            return false;
        }
        else if (arg.matches("GO .*"))               // starts with "GO" :
            arg = arg.replace("GO ", "").trim();     //   remove "GO"

        Place prevPlace = p.getCurrentPlace();             // reference place
        prevPlace.removeCharacter(p);                      // remove from place
        p.setCurrentPlace(prevPlace.followDirection(arg)); // update place
        p.getCurrentPlace().addCharacter(p);               // add to place
        if (p.reachedExit()) 
        {
            new RemovePlayer(p); 
            return true;         // exit if reached exit
        }

        else if (p.getCurrentPlace() != prevPlace) { // match found :
            if (p.placeName().matches("Room.*"))     //   place is room
                printIO.display(String.format("Headed %s to %s...\n",
                                Direction.matchDirection(arg), p.placeName()));
            else                                     //   place isn't room
                printIO.display(String.format("Headed %s to the %s...\n",
                                Direction.matchDirection(arg), p.placeName()));
            try { Thread.sleep(1500); } catch (Exception e) { }
            return true;
        }//end else if...
        return false;
    }//end execute()
}//end GoPlayer class
