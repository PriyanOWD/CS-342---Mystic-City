/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;


public class Bruiser extends Player
{
    private double attackMultiplier;
    private double defenseMultiplier;
    private double healthMultiplier;

    // creates Bruiser object
    public Bruiser(Scanner scn, int ver, int num) {
        super(scn, ver, num);
        attackMultiplier = 1.5;
        defenseMultiplier = 1.5;
        healthMultiplier = 0.5;
    }

    // getters for the multipliers
    public double getAttackM()
    {
        return attackMultiplier;
    }

    public double getDefenseM()
    {
        return defenseMultiplier;
    }

    public double getHealthM()
    {
        return healthMultiplier;
    }
}//end Bruiser