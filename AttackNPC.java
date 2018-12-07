/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.Random;


// AttackNPC class that inherits Move abstract class and, in its execution,
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
            Place currPlace = n.currPlace;
            int size = currPlace.placeCharacters.size();
            Character target = null;
            if (size > 1) {
                target = currPlace.placeCharacters.get(new Random().nextInt(currPlace.placeCharacters.size()));
                while(target.name.equals(n.name) && target != null) {
                    target = currPlace.placeCharacters.get(new Random().nextInt(currPlace.placeCharacters.size()));
                }
            }

            if (target == null) {
                // couldn't find anyone
                //IO.getIO().display(String.format("%s wanted to attack, but couldn\'t find anyone.\n", n.name().replace("A ", "The ")));
                return true;
            }

            if (n.name.equalsIgnoreCase(target.name)) {
                // cannot damage self
                return false;
            }

            else {
                if (target.defense/2 > n.attack) {
                    target.currHP--;
                    IO.getIO().display(String.format("%s has damaged %s by 1 HP.\n", n.name().replace("A ", "The "), target.name().replace("The ", "the ").replace("A ", "the ")));
                    Game.addTicket(target.name, String.format("%s, %s has damaged you by 1 HP.\n", target.name().replace("A ", "The "), n.name().replace("The ", "the ").replace("A ", "the ")));
                }
                else {
                    target.currHP -= n.attack/2;
                    IO.getIO().display(String.format("%s has damaged %s by %d HP.\n", n.name().replace("A ", "The "), target.name().replace("The ", "the ").replace("A ", "the "), n.attack/2));
                    Game.addTicket(target.name, String.format("%s, %s has damaged you by %d HP.\n", target.name().replace("A ", "The "), n.name().replace("The ", "the ").replace("A ", "the "), n.attack/2));
                }
            }

        try { Thread.sleep(1500); } catch(Exception e) { }
        return true;
    }//end execute()
}//end Use class
