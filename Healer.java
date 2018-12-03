/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


public class Healer extends Player
{
    private double manaPointsMultiplier;
    private double attackMultiplier;

    //creates healer object
    public Healer(Scanner scn, int ver, int num)
    {
        super(scn, ver, num);
        manaPointsMultiplier = 2.5;
        attackMultiplier = 0.5;
    }

    //getters for healer multipliers
    public double getManaM()
    {
        return manaPointsMultiplier;
    }

    public double getAttackM()
    {
        return attackMultiplier;
    }
}//end Healer