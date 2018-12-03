/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


public class Attacker extends Player{

    private double damageMultiplier;
    private double healthMultiplier;

    // creates Attacker object
    public Attacker(Scanner scn, int ver, int num)
    {
        super(scn, ver, num);
        damageMultiplier = 2.0;
        healthMultiplier = 1.5;
    }

    // returns multipliers specific to Attacker
    public double getDamageM(Scanner scn, int ver)
    {
        return damageMultiplier;
    }

    public double getHealthM()
    {
        return healthMultiplier;
    }
}//end Attacker