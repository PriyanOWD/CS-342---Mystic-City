/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;


public class Tank extends Player
{
    private double defenseMultiplier;
    private double healthMultiplier;

    // creates Tank object
    public Tank(Scanner scn, int ver, int num)
    {
        super(scn, ver, num);
        defenseMultiplier = 3.0;
        healthMultiplier = 2.0;
    }

    // returns tank multipliers
    public double getDefenseM()
    {
        return defenseMultiplier;
    }

    public double getHealthM()
    {
        return healthMultiplier;
    }
}//end Tank