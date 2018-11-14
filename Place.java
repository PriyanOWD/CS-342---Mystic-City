/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Place
{

    static final HashMap<Integer, Place> allPlacesMap; // Can optionally make hashmap public --- use
                                                       // DoubleBraceInitialization??
    static
    {
        allPlacesMap = new HashMap<Integer, Place>();
    }

    public static Place getPlacebyID(int id)
    {
        return allPlacesMap.get(id);
    }

    // Place variables. Final values.
    private final Pattern g_delim_Pattern = Pattern.compile("\r?\n\r?\n");
    private final String PNAME;
    private final String PDESCRIPTION;
    private final int PID;
    private List<Direction> paths;
    private List<Artifact> placeArtifacts;
    private List<Character> placeCharacters;
    private Queue<String> footprints;

    public Place(int ID, String name, String description)
    {
        paths = new ArrayList<Direction>();
        placeArtifacts = new ArrayList<Artifact>();
        placeCharacters = new ArrayList<Character>();
        footprints = new LinkedList<String>();
        
        PID = ID;
        PNAME = name;
        PDESCRIPTION = description;
        if (!allPlacesMap.containsKey(PID))
            allPlacesMap.put(PID, this);
    }

    public Place(Scanner scn, int ver)
    {
        paths = new ArrayList<Direction>();
        placeArtifacts = new ArrayList<Artifact>();
        placeCharacters = new ArrayList<Character>();
        footprints = new LinkedList<String>();
         
        StringBuilder desc = new StringBuilder();
        Scanner sc = new Scanner(CleanLineScanner.gameFileParser(scn, g_delim_Pattern));

        PID = Integer.valueOf(sc.next(("\\d+")));
        PNAME = sc.nextLine().trim();
        sc.nextLine();
        while (sc.hasNext())
            desc.append(sc.nextLine() + "\n");
        PDESCRIPTION = desc.toString();

        if (!allPlacesMap.containsKey(PID))
            allPlacesMap.put(PID, this);
        sc.close();
    }

    public boolean checkID(int idToCheck) // Function used to cross check the Place object's ID with value passed
    { // although it allows a person to just check every number against PID until the return value is true
        return PID == idToCheck;
    }

    public String name()
    {
        return PNAME;
    }
/*
    public String description()
    {
        return PDESCRIPTION;
    }*/

    public void addDirection(Direction dir)
    {
        if (dir.leadsToNowhere()) // Function to check if target place that direction leads to is a "Nowhere" and then
                                  // auto locks it.
            dir.lock();

        paths.add(dir); // Adds Direction object to vector within Place object
        return;
    }

    public Place followDirection(String pToGo)
    {
        // Place result = this; //Sets place to calling object in case no matches are found
        for (Direction tmp : paths) // checks passed string value against every direction object in Place's vector
                                    // member
        {
            if (tmp.match(pToGo)) // calls Directions match method to determine what to do
                return tmp.follow(); // Always returns first Match found in the places Direction Vector
            // return (result = tmp.follow());
        }
        return this;
        // return result;
    }

    public void addArtifact(Artifact item)
    {
        placeArtifacts.add(item);
    }

    public void useKey(Artifact arti)
    {
        for (Direction tmp : paths)
        {
            tmp.useKey(arti);
        }
    }
    
//    public boolean useKey(Artifact a) {
//        boolean hasLock = false;         // lock status
//
//        for (Direction d : paths) { // iterate thru directions :
//            if (hasLock)                 //   has lock :
//                d.useKey(a);             //     use key
//            else                         //   doesn't have lock :
//                hasLock = d.useKey(a);   //     use key + update status
//        }//end for...
//
//        return hasLock;                  // return status
//    }//end useKey()
    
    public void addCharacter(Character charVar)
    {
        placeCharacters.add(charVar);
    }

    public void removeCharacter(Character charVar)
    {
        placeCharacters.remove(charVar);
    }
    
    /*
     * public Artifact get(String itemName) 
     * { 
     *      for (Artifact tmp: placeArtifacts ) 
     *      {
     *          if((itemName.trim()).equals(tmp.name())) 
     *          { 
     *          Artifact retrieve = tmp; placeArtifacts.remove(tmp); 
     *          return retrieve;
     *          } 
     *      } 
     *      return null; 
     * }
     */

    public Artifact removeArtifactByName(String itemName)
    {
        for (Artifact tmp : placeArtifacts)
        {
            if ((itemName.trim()).equals(tmp.name()))
            {
                Artifact retrieve = tmp;
                placeArtifacts.remove(tmp);
                return retrieve;
            }
        }
        return null;
    }
    
    public Artifact removeArtifact(Artifact item)
    {
        for (Artifact tmp : placeArtifacts)
        {
            if ((item.name().trim()).equals(tmp.name()))
            {
                Artifact retrieve = tmp;
                placeArtifacts.remove(tmp);
                return retrieve;
            }
        }
        return null;
    }
    
    public void display()
    {
        int count = 1;
        System.out.println("\nCurrent Location ->" + PNAME + ":\n" + PDESCRIPTION);
        if (!PNAME.equals("Exit"))
        {
            System.out.println(PNAME + " Directions:");
            for (Direction tmp : paths)
                tmp.display();
            System.out.println("\n" + PNAME + " Artifacts:");
            for (Artifact tmp : placeArtifacts)
            {
                System.out.print(count++ + ":");
                tmp.display();
            }
            count = 1;
            System.out.println("\nCharacters in " + PNAME + ":");
            for (Character charVar : placeCharacters)
            {
                if(!Game.checkcurrPlayerbyName(charVar.name))
                {
                    System.out.print(count++ + ":");
                    charVar.display();
                }
            }
        }
        System.out.println("\n");
        return;
    }

    public void print()
    {

        System.out.println("Place Name: " + PNAME + "\nDescription:" + PDESCRIPTION + "\nID:" + PID);
        System.out.println("Directions:");
        for (Direction tmp : paths)
            tmp.display();
        return;
    }

    public void printAll()
    {
        int count = 1;
        Collection<Place> everyPlace = Place.allPlacesMap.values();
        for(Place tmpPlace: everyPlace)
        {
            tmpPlace.print();
            System.out.println(PNAME + " Directions:");
            for (Direction tmp : paths)
                tmp.print();
            System.out.println("\n" + PNAME + " Artifacts:");
            for (Artifact tmp : placeArtifacts)
            {
                System.out.println(count++ + ":");
                tmp.print();
            }
            count = 0;
            System.out.println("\nCharacters in " + PNAME + ":");
            for (Character charVar : placeCharacters)
            {
                System.out.print(count++ + ":");
                charVar.print();
            }
        
            System.out.println("\n");
        }
    }
    
    public static int getSizePlaces()
    {
        return allPlacesMap.size();
    }
    
    public static HashMap<Integer,Place> getPlaceDirectory(){ return allPlacesMap;}
    
    // return random place other than nowhere and exit
    public static Place getRandomPlace() {
        // make list of place IDs
        ArrayList<Integer> IDs = new ArrayList<Integer>(allPlacesMap.keySet());
        // get random ID from list (excluding nowhere and exit)
        int randomID = IDs.get(2 + new Random().nextInt(IDs.size() - 2));

        return allPlacesMap.get(randomID);   // return place
    }//end getRandomPlace()
    
    public int pathsCount()
    {
        return paths.size();
    }
    
    public Direction getDirection(int index)
    {
        return paths.get(index);
    }
    
    public int getIndex()
    {
        return PID;
    }
    public int artiCount()
    {
        return placeArtifacts.size();
    }
    
    public Artifact getArtifactbyIndex(int index)
    {
        return placeArtifacts.get(index);
    }
    
    // return artifact from collection of artifacts
    public Artifact getArtifact()            {
        if (placeArtifacts.size() > 0) return placeArtifacts.get(0);
        else                      return null;
    }//end getArtifact()
    
    // check if place has valid artifact corresponding to passed string
    // and, if so, return list of matches
    public ArrayList<Artifact> followArtifact(String str) {
        ArrayList<Artifact> matches = new ArrayList<Artifact>();

        // iterate thru place's collection of artifacts
        for (Artifact a : placeArtifacts) {
            if (a.match(str)) {     // exact match found :
                matches.add(a);     //   add it to list of matches,
                return matches;     //   and return it
            }//end if...
        }//end for...

        // iterate thru place's collection of artifacts
        for (Artifact a : placeArtifacts) {
            if (a.matchTokens(str)) // potential match found :
                matches.add(a);     //   add it to list of matches
        }//end for...

        return matches;             // return all matches
    }//end followArtifact()
    
    // return random valid unlocked direction
    public Place getRandomDestination() {
        Collections.shuffle(paths);      // shuffle directions
        for (Direction d : paths)        // iterate thru directions :
            if (!d.isLocked() && d.isValid()) //   unlocked :
                return d.follow();            //     return dest

        return this;                          // none found : return this
    }//end getRandomDestination()
    
}
