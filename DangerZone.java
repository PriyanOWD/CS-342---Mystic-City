/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Dec 6, 2018
 */

import java.util.Scanner;


public class DangerZone extends Place
{
   private int dmg = 5;
   public DangerZone(Scanner scn, int ver)
   {
       super(scn,ver);
       footPrintLife = 5;
       Scanner s = new Scanner(CleanLineScanner.getCleanLine(scn));
       dmg = s.nextInt();
   }
   
   public void inflictDMG()
   {
       for(Character ch: placeCharacters)
       {
           if(ch.currHP > dmg)
           {
               ch.currHP -= dmg;
               System.out.println(PNAME + " inflicted " + dmg + " to " + ch.name);
           }
           else
           {
               ch.currHP = 1;
               System.out.println(PNAME + " reduced " + ch.name + "'s HP to 1!");
           }
       }
   }
   
   @Override
   public void update()
   {
       super.update();
       inflictDMG();
   }
}
