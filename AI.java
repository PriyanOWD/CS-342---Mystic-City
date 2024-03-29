/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Dec 6, 2018
 */

import java.util.Random;


public class AI implements DecisionMaker {
    // select and return random "GET", "DROP" or "GO" move for NPC
    public Move getMove(Character c)
    {
        int random = new Random().nextInt(4);      // generate rand # from 0..2

        switch (random) {
            case 0 : return new GetNPC   ((NPC) c);  // "GET"  command
            case 1 : return new DropNPC  ((NPC) c);  // "DROP" command
            case 2 : return new GoNPC    ((NPC) c);  // "GO"   command
            case 3 : return new AttackNPC((NPC) c);  // "ATTACK"   command
        }
        return new GoNPC((NPC) c);
    }//end getMove()
}
