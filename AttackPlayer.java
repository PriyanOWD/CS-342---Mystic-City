/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;


// Use class that inherits Move abstract class and, in its execution,
// enables the use of key artifacts to unlock locked directions
public class AttackPlayer extends Move {
    // private attributes
    private Player p;   // player
    private String arg; // argument(s)

    // constructor for Use class
    public AttackPlayer(Player p, String str) {
        this.p = p;   // set player
        arg    = str; // set argument(s)
    }//end class constructor


    // execute "USE" command : use key artifact from player's collection
    // of artifacts to unlock door
    public boolean execute() {
        IO printIO = IO.getIO();
        if (arg.matches("ATTACK .*")) {
            arg = (arg.replace("ATTACK ", "")).trim();
            
            Character target = null;
            for (Character c : p.currPlace.placeCharacters) {
                if(c.name.toUpperCase().equals(arg.toUpperCase())) {
                    target = c;
                    break;
                }
            }

            if (target == null) {
                if (p.getCurrentPlace().name().matches("Room.*"))  // place is room
                    IO.getIO().display(String.format("Sorry, %s isn\'t in %s.",
                            arg.replace("The ", "the ").replace("A ", "the "), p.currPlace.name()));
                else                                               // place isn't room
                    IO.getIO().display(String.format("Sorry, %s isn\'t in the %s.",
                            arg.replace("The ", "the ").replace("A ", "the "), p.currPlace.name()));
                return false;
            }

            else if (p.name.equalsIgnoreCase(arg)) {
                IO.getIO().display("Sorry, you can\'t damage yourself...\n");
                return false;
            }

            else {
                if (target.defense/2 > p.attack) {
                    target.currHP--;
                    IO.getIO().display(String.format("%s\'s defense is too high! Or you\'re too weak...\nYou damaged %s by 1 HP.\n", arg.replace("A ", "The "), arg.replace("A ", "The ")));
                    Game.addTicket(target.name, String.format("%s, %s has damaged you by 1 HP.\n", target.name().replace("A ", "The "), p.name().replace("The ", "the ").replace("A ", "the ")));
                }
                else {
                    target.currHP -= p.attack/2;
                    IO.getIO().display(String.format("You damaged %s by %d HP.\n", arg.replace("The ", "the ").replace("A ", "the "), p.attack/2));
                    Game.addTicket(target.name, String.format("%s, %s has damaged you by %d HP.\n", target.name().replace("A ", "The "), p.name().replace("The ", "the ").replace("A ", "the "), p.attack/2));
                }
            }
        }
        else printIO.display("Enter \'ATTACK\' followed by the name of a .\n");

        try { Thread.sleep(1000); } catch (Exception e) { }
        return true;
    }//end execute()
}//end Use class
