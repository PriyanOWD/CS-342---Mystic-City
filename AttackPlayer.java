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
            for(Character c: p.currPlace.placeCharacters)
            {
                if(c.name.toUpperCase().equals(arg.toUpperCase()))
                {
                    target = c;
                    break;
                }
            }
             
            if (target == null) {
                IO.getIO().display("\n"+arg + " isn't in " + p.currPlace.name()+"\n");
                return false;
            }
            else if( p.name.toUpperCase().equals(arg.toUpperCase()))
            {
                IO.getIO().display("\n Cannot damage yourself...\n");
                return false;
            }
                
            else {                                    // possible matches :
                if(target.defense/2 > p.attack)
                {
                    target.currHP--;
                    IO.getIO().display("\n"+arg + " defense is too high! ...or you're too weak...\nYou did 1 damage\n");
                    Game.addTicket(target.name,"\n" +p.name + " has damaged you for 1 point(s)\n");
                }
                else
                {
                    target.currHP -= p.attack/2;
                    IO.getIO().display("\nYou did " +p.attack/2 +" damage to " + target.name+ "\n");
                    Game.addTicket(target.name,"\n"+target.name+ ": " +p.name + " has damaged you for "  + p.attack/2 + " point(s)\n");
                }
            }                          // no match
        }
        else printIO.display("\nEnter \'ATTACK\' followed by the name of a.\n");
        
        try{Thread.sleep(1000);} catch(Exception e){};
        return true;
        
    }//end execute()
}//end Use class