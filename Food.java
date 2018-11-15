/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;


public class Food extends Artifact {
    // private attributes
    private int hp;       // health point
    private int mp;       // mana   point

    // constructor for Armor class
    public Food(Scanner sc, int ver) {
        super(sc, ver);
        if (ver < 5) return;                                 // unsupported ver
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            hp        = s.nextInt();                         // set hp
            mp        = s.nextInt();                         // set mp
            s.close();                                       // close scanner
        } catch (Exception e) { e.printStackTrace(); }       // exception
    }//end class constructor
}//end Food class
