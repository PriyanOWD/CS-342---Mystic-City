import java.util.Scanner;

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
 */

public class DangerZone extends Place
{
    private int dmg;
   public DangerZone(Scanner scn, int ver)
   {
       super(scn,ver);
       footPrintLife = 5;
       dmg = scn.nextInt();
   }
   
   public void inflictDMG()
   {
       for(Character ch: placeCharacters)
       {
           if(ch.currHP > dmg)
               ch.currHP -= dmg;
           else
               ch.currHP = 1;
           System.out.println(PNAME + " inflicted " + dmg + "  to " + ch.name);
       }
   }
}
