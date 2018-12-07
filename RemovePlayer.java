/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


import java.util.List;

// RemovePlayer class that inherits Move abstract class and, in its execution,
// is used to remove a dead player from the game
public class RemovePlayer extends Move {
    // private attributes
    private Player p;   // player


    // constructor for RemovePlayer class
    public RemovePlayer(Player p) {
        this.p = p;   // set player
    }//end class constructor


    // execute command : remove player from game
    public boolean execute() {
        IO printIO = IO.getIO();
        p.isActive = false;

        List<Artifact> artifacts = p.inventory;
        for (Artifact a : artifacts)
            p.getCurrentPlace().addArtifact(a);

        printIO.display(String.format("\n%s, YOU\'RE DEAD!\n",
                p.name().toUpperCase()));
        Game.numActive--;
        try { Thread.sleep(2000); } catch (Exception e) { }

        return true;
    }//end execute()
}//end RemovePlayer class
