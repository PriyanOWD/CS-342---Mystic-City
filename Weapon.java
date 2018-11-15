/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;


public class Weapon extends Artifact {
    // private attributes
    private int attack;   // attack  multiplier
    private int defense;  // defense denominator
    private int hp;       // health point
    private int mp;       // mana   point
    private int type;     // type   (0 for primary, 1 for offhand)


    // constructor for Armor class
    public Weapon(Scanner sc, int ver) {
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
}//end Weapon class
