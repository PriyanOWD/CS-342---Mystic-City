/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;
import java.util.Random;


// Use class that inherits Move abstract class and, in its execution,
// enables the use of key artifacts to unlock locked directions
public class AttackNPC extends Move {
    // private attributes
    private NPC n;   // player

    // constructor for Use class
    public AttackNPC(NPC np) {
        this.n = np;   
    }//end class constructor


    // execute "USE" command : use key artifact from player's collection
    // of artifacts to unlock door
    public boolean execute() {
            IO printIO = IO.getIO();
            Place currPlace = n.currPlace;
            int size = currPlace.placeCharacters.size();
            Character target = null;
            if(size>1)
            {
                target = currPlace.placeCharacters.get(new Random().nextInt(currPlace.placeCharacters.size()));
                while(target.name.equals(n.name) && target != null)
                {
                    target = currPlace.placeCharacters.get(new Random().nextInt(currPlace.placeCharacters.size()));
                }
            }
            if (target == null) {
                IO.getIO().display("\n"+n.name + " wanted to attack but couldn't find anyone.\n");
                return true;
            }
           if( n.name.toUpperCase().equals(target.name.toUpperCase()))
            {
                IO.getIO().display("\n Cannot damage yourself...\n");
                return false;
            }
                
            else {                                    // possible matches :
                if(target.defense/2 > n.attack)
                {
                    target.currHP--;
                    IO.getIO().display("\n"+target.name + " defense is too high! ...or you're too weak...\nYou did 1 damage\n");
                    Game.addTicket(target.name,"\n" +n.name + " has damaged you for 1 point(s)\n");
                }
                else
                {
                    target.currHP -= n.attack/2;
                    IO.getIO().display("\nYou did " +n.attack/2 +" damage to " + target.name+ "\n");
                    Game.addTicket(target.name,"\n"+target.name+ ": " +n.name + " has damaged you for "  + n.attack/2 + " point(s)\n");
                }
        }      
        try{Thread.sleep(1500);} catch(Exception e){};
        return true;
        
    }//end execute()
}//end Use class