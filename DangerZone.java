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
           if(ch.isActive && ch instanceof Player)
           {
               Player p = (Player) ch;
               IO printIO = IO.getIO();
               if(p.currHP > dmg)
               {
                   p.currHP -= dmg;

                   if (PNAME.matches("Room.*"))
                       printIO.display(String.format("%s has inflicted %s\'s HP by %d!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the "), dmg));
                   else
                       printIO.display(String.format("The %s has inflicted %s\'s HP by %d!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the "), dmg));
               }
               else
               {
                   p.currHP = 0;

                   if (PNAME.matches("Room.*"))
                       printIO.display(String.format("%s has killed %s!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the ")));
                   else
                       printIO.display(String.format("The %s has killed %s!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the ")));
               }

               try { Thread.sleep(1500); } catch (Exception e) { }
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
