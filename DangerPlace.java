import java.util.Scanner;

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
 */

public class DangerPlace extends Place
{
   int dmg = 5;
   public DangerPlace(Scanner scn, int ver)
   {
       super(scn,ver);
       footPrintLife = 5;
   }
   
   public void inflictDMG()
   {
       for(Character ch: placeCharacters)
       {
           if(ch.currHP > dmg)
               ch.currHP -= dmg;
           else
               ch.currHP = 1;
       }
   }
}
