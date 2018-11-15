import java.util.Scanner;
import java.util.regex.Pattern;

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
 */

public class SafeZone extends Place
{
   private int val;
   public SafeZone(Scanner scn, int ver)
   {
       super(scn,ver);
       
       footPrintLife = 5;
       Scanner s = new Scanner(CleanLineScanner.getCleanLine(scn));
       val = s.nextInt();
   }
   
   public void healAll()
   {
       for(Character ch: placeCharacters)
       {
           if( (ch.currHP + val) > ch.maxHP)
               ch.currHP = ch.maxHP;
           else
               ch.currHP += val;
           System.out.println(PNAME + " recovered " + val + " health for " + ch.name);
       }
   }
}
