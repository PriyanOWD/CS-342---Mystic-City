/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


public class Food extends Artifact {
    // private attributes
    private int hp;            // health point
    private int mp;            // mana   point
    private boolean consumed;  // consumed status


    // constructor for Armor class
    public Food(Scanner sc, int ver) {
        super(sc, ver);
        if (ver < 51) return;                           // unsupported ver
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            hp        = s.nextInt();                    // set hp
            mp        = s.nextInt();                    // set mp
            consumed  = false;                          // set non-consumed
            s.close();                                  // close scanner
        } catch (Exception e) { e.printStackTrace(); }  // exception
    }//end class constructor


    // return max health point
    public int getMaxHP()        { return hp;       }
    // return mana point
    public int getMP()           { return mp;       }
    // set consumed
    public boolean getConsumed() { return consumed; }
    // set consumed
    public void    setConsumed() { consumed = true; }
}//end Food class
