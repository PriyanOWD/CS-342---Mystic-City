/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Dec 6, 2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Market extends Place
{
    public static Market getMarketbyID(int id)
    {
        Place m = Place.allPlacesMap.get(id);
        if(m instanceof Market)
            return (Market) m;
        return null;
    }
    
   private boolean active = true;
   private ArrayList<Artifact> mInventory = new ArrayList<Artifact>();
   
   public Market(Scanner scn, int ver)
   {
       super(scn,ver);  
       footPrintLife = 1;
       
       Scanner sc;
       try {
           sc = new Scanner(new File("marketItems.gdf"));
       } 
       catch (FileNotFoundException e) {               // not found
           System.err.printf("\nFile \'%s\' could not be found.\n", "marketItems.gdf");
           return;
       } 
       catch (Exception e) { e.printStackTrace(); return;}    // exception
      
       allocateMArtifacts(sc, ver);
       sc.close();
   }
   
   public void addToInve(Artifact item)
   {
       mInventory.add(item);
   }
   
   public boolean soldOut()
   {
       return mInventory.size() == 0;
   }
   
   public Artifact winItem(int num)
   {
       if(!active)
       {
          System.out.println("Unfortunately, The market is closed"); 
          return null;
       }
       
       Random rand = new Random();
       int roll = rand.nextInt(5);
       if( (roll+1) == num && (mInventory.size() > roll))
       {
           active = !active;    //Close the market during a win
           return mInventory.get(roll);
       }   
       //active = !active; //Close the market win or lose
       return null;
   }
   
//   @Override
//   public void removeCharacter(Character charVar)
//   {
//       super.removeCharacter(charVar);
//       active = !active;
//   }
   
   @Override()
   public void update()
   {
       super.update();
     //If the market is closed at the end of a round. Open it for business
       if(!active)  
           active = !active;
   }
   
   private void allocateMArtifacts(Scanner sc, int ver) {
       try {
           Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
           int num   = s.skip("MARKETITEMS").nextInt(); // get # of Martifacts
           int mID = Integer.parseInt(sc.nextLine());
           
           Market m = Market.getMarketbyID(mID);
           
           s.close();                                 // close scanner

           for (int i = 0; i < num; i++) {            // allocate artifacts :
               String type = CleanLineScanner.getCleanLine(sc);
               if      (type.equals("ARMOR" )) m.addToInve(new Armor   (sc, ver));
               else if (type.equals("WEAPON")) m.addToInve(new Weapon  (sc, ver));
               else if (type.equals("FOOD"  )) m.addToInve(new Food    (sc, ver));
               else                            m.addToInve(new Artifact(sc, ver));
           }//end for...
       } catch (Exception e) {  System.out.println("Market error");e.printStackTrace(); } // exception
   }//end allocateArtifacts()
   
   
}
