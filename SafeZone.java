/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


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
           if( (ch.currHP + val) > ch.maxHP) {
               ch.currHP = ch.maxHP;
               System.out.println(PNAME + " fully restored " + ch.name + "'s HP!");
           }
           else {
               ch.currHP += val;
               System.out.println(PNAME + " recovered " + val + " health for " + ch.name);
           }
       }
   }
   
   @Override
   public void update()
   {
       super.update();
       healAll();
   }
}
