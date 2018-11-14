/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;


public class Armor extends Artifact {
    // private attributes
    private int attack;   // attack  multiplier
    private int defense;  // defense denominator
    private int hp;       // health point
    private int mp;       // mana   point
    private int type;     // type   (0-headpiece,  1-upper body,
                          //         2-lower body, 3-shoes,      4-accessory)

    // constructor for Armor class
    public Armor(Scanner sc, int ver) {
        super(sc, ver);
        if (ver < 5) return;                                 // unsupported ver
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            attack    = s.nextInt();                         // set attack
            defense   = s.nextInt();                         // set defense
            hp        = s.nextInt();                         // set hp
            mp        = s.nextInt();                         // set mp
            type      = s.nextInt();                         // set type
            s.close();                                       // close scanner
        } catch (Exception e) { e.printStackTrace(); }       // exception
    }//end class constructor
}//end Armor class
