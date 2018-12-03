/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


public class Armor extends Artifact {
    // private attributes
    private int attack;   // attack  multiplier
    private int defense;  // defense denominator
    private int hp;       // health point
    private int mp;       // mana   point
    private int type;     // type   (0 for headpiece,  1 for upper-body,
                          //         2 for lower-body, 3 for shoes,
                          //         4 for accessory)

    // constructor for Armor class
    public Armor(Scanner sc, int ver) {
        super(sc, ver);
        if (ver < 51) return;                                // unsupported ver
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


    // return attack multiplier
    public int getAtk()   { return attack;  }
    // return defense denominator
    public int getDef()   { return defense; }
    // return max health point
    public int getMaxHP() { return hp;      }
    // return mana point
    public int getMP()    { return mp;      }
    // return type
    public int getType()  { return type;    }
}//end Armor class
