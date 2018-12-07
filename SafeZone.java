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
           if(ch.isActive && ch instanceof Player)
           {
               Player p = (Player) ch;
               IO printIO = IO.getIO();
               if((p.currHP + val) > p.maxHP)
               {
                   p.currHP = p.maxHP;

                   if (PNAME.matches("Room.*"))
                       printIO.display(String.format("%s has fully restored %s\'s HP!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the ")));
                   else
                       printIO.display(String.format("The %s has fully restored %s\'s HP!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the ")));
               }
               else
               {
                   p.currHP += val;

                   if (PNAME.matches("Room.*"))
                       printIO.display(String.format("%s has recovered %s\'s HP by %d!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the "), val));
                   else
                       printIO.display(String.format("The %s has inflicted %s\'s HP by %d!\n", PNAME, p.name.replace("The ", "the ").replace("A ", "the "), val));
               }

               try { Thread.sleep(1500); } catch (Exception e) { }
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
