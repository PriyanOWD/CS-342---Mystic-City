/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// GoNPC class that inherits Go abstract class and, in its execution,
// is used to allow an NPC to go from its current place to an adjacent place
public class GoNPC extends Go {
    // private attributes
    private NPC n; // NPC


    // constructor for GoNPC class
    public GoNPC(NPC n) { this.n = n; }


    // execute "GO" command : go to random direction
    public boolean execute() {
        IO printIO = IO.getIO();

        // remove NPC from current place
        n.getCurrentPlace().removeCharacter(n);

        // update current place to random unlocked adjacent place
        n.setCurrentPlace(n.getCurrentPlace().getRandomDestination());

        // add NPC to current place
        n.getCurrentPlace().addCharacter(n);

        if (n.getCurrentPlace().name().matches("Room.*"))  // place is room
            printIO.display(String.format("%s has moved to %s.\n",
                    n.name().replace("A ", "The "), n.getCurrentPlace().name()));
        else                                               // place isn't room
            printIO.display(String.format("%s has moved to the %s.\n",
                    n.name().replace("A ", "The "), n.getCurrentPlace().name()));

        try { Thread.sleep(1500); } catch (Exception e) { }
        return true;
    }//end execute()
}//end GoNPC class
