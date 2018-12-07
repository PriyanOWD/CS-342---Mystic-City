/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class Place
{

    protected class footPrint
    {
        private String charName;
        private int remTurns;
        private int ttlTurns;
        
        footPrint(String name, int turns)
        {
            remTurns = turns;
            ttlTurns = turns;
            charName = name;
        }
        
        @Override
        public boolean equals(Object ft)
        {
            if(ft instanceof footPrint)
                return ((footPrint)ft).match(charName); 
            return false;
        }
        
        public boolean equals(footPrint fp)
        {
            return this.charName.equals(fp.charName);
        }
        
        public boolean wither()
        {
            remTurns--;
            return remTurns == 0;
        }
        
        public boolean match(String str)
        {
            return charName.equals(str);
        }
        
        public void display()
        {
            IO fpIO = IO.getIO();
            if (ttlTurns == remTurns )
            {
                fpIO.display("A fresh pair of tracks is on the floor");
                fpIO.display("You believe it belongs to " + charName);
            }
            else if (remTurns > 1)
            {
                Random rndBool = new Random();
                fpIO.display("A faint trail of footprints line the floor");
                fpIO.display("You somewhat remember who they belong to -> ");
                
                for(char ch: charName.toCharArray())
                {
                    if( rndBool.nextBoolean() )
                        fpIO.display(String.valueOf(ch));
                    else
                        fpIO.display("_");
                }
                System.out.println();
            }
            
            else if (remTurns == 1)
            {
                Random rndBool = new Random();
                fpIO.display("A very faint trail of footprints line the floor");
                fpIO.display("You can't really recall who they belong to -> _");
                for(char ch: charName.substring(1).toCharArray())
                {
                    if( rndBool.nextBoolean() )
                        fpIO.display(String.valueOf(ch));
                    else
                        fpIO.display("_");
                }
                System.out.println();
            }
            
            else
                fpIO.display("FootPrint decayed");
        }
    }
    
    static final TreeMap<Integer, Place> allPlacesMap;
    static final Set<Map.Entry<Integer,Place>> allPlacesSet;
    static Place entryPlace;
    
    static
    {
        allPlacesMap = new TreeMap<Integer, Place>();
        entryPlace   = null;
        allPlacesSet = allPlacesMap.entrySet();
    }

    public static Place getPlacebyID(int id)
    {
        return allPlacesMap.get(id);
    }

    // Place variables. Final values.
    protected String PNAME;
    protected String PDESCRIPTION;
    protected int PID;
    protected List<Direction> paths;
    protected List<Artifact> placeArtifacts;
    protected List<Character> placeCharacters;
    protected Queue<footPrint> footPrints;
    protected int footPrintLife;
    
    public void displayFPrints()
    {
        IO fpIO = IO.getIO();
        if(footPrints.size() == 0)
        {
            fpIO.display("No footprints discovered here");
            return;
        }
        int count = 1;
        for(footPrint ft: footPrints)
        {
            fpIO.display(count++ + ":");
            ft.display();
        }
    }

    static public void updatePlaces()
    {
        for(Map.Entry<Integer,Place> tuple: allPlacesSet)
        {
           Place p = tuple.getValue();
           p.update();
        }
    }
    
    protected void update()
    {
        updatePrints();
    }

    protected void updatePrints()
    {
        LinkedList<footPrint> toRem = new LinkedList<footPrint>();
        for(footPrint ft: footPrints)
        {
            if(ft.wither())
                toRem.add(ft);
        }

        footPrints.removeAll(toRem);
    }

    public Place(int ID, String name, String description)
    {
        paths = new ArrayList<Direction>();
        placeArtifacts = new ArrayList<Artifact>();
        placeCharacters = new ArrayList<Character>();
        footPrints = new LinkedList<footPrint>();
        
        footPrintLife = 3;
        PID = ID;
        PNAME = name;
        PDESCRIPTION = description;
        if (!allPlacesMap.containsKey(PID))
            allPlacesMap.put(PID, this);
    }
    
      public Place(Scanner sc, int ver)
      {
        if (ver < 1) return;                              // unsupported version
        try {

            footPrintLife = 3;
            paths = new ArrayList<Direction>();           // allocate directions
            placeCharacters = new ArrayList<Character>(); // allocate characters
            placeArtifacts  = new ArrayList<Artifact>();  // allocate artifacts
            footPrints      = new LinkedList<footPrint>();// allocate footprints

            Scanner s  = new Scanner(CleanLineScanner.getCleanLine(sc));
            PID        = s.nextInt();                     // set ID
            PNAME      = CleanLineScanner.getCleanLine(s);// set name

            s.close();                                    // close scanner

            s          = new Scanner(CleanLineScanner.getCleanLine(sc));
            int num    = s.nextInt();                     // get # of lines
            s.close();                                    // close scanner

            PDESCRIPTION  = "";
            for (int i = 0; i < num; i++) {               // set desc :
                if (i < num - 1)                          //   preceding line
                    PDESCRIPTION += CleanLineScanner.getCleanLine(sc) + "\n";
                else                                      //   last line
                    PDESCRIPTION += CleanLineScanner.getCleanLine(sc);
            }

            if (!allPlacesMap.containsKey(PID))           // non-duplicate :
                allPlacesMap.put(PID, this);              //   add to collection
            if (entryPlace == null) entryPlace = this;    // set entry place

        } catch (Exception e) { e.printStackTrace(); }    // exception
    }//end primary constructor
    
    public boolean checkID(int idToCheck) // Function used to cross check the Place object's ID with value passed
    { // although it allows a person to just check every number against PID until the return value is true
        return PID == idToCheck;
    }

    public String name()
    {
        return PNAME;
    }

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
            {
                return tmp.follow(); // Always returns first Match found in the places Direction Vector
            // return (result = tmp.follow());
            }
        }
        IO printIO = IO.getIO();
        printIO.display(String.format("Sorry, can't go %s.\n", Direction.matchDirection(pToGo)));
        return this;
        // return result;
    }

    public void addArtifact(Artifact item)
    {
        placeArtifacts.add(item);
    }

    public boolean useKey(Artifact arti)
    {
        boolean hasLock = false;

        for (Direction tmp : paths)
        {
            if (hasLock)
                tmp.useKey(arti);
            else
                hasLock = tmp.useKey(arti);
        }

        return hasLock;
    }

    public void addCharacter(Character charVar)
    {
        placeCharacters.add(charVar);
    }

    public void removeCharacter(Character charVar)
    {
        footPrints.add(new footPrint(charVar.name, footPrintLife));
        placeCharacters.remove(charVar);
    }

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

    public void display(Character ch)
    {
//        UI.printHeader(String.format("PLAYER %d: %s",  // name + player #
//                       ((Player) ch).playerNum(), ch.name()));
//
//        ch.stats();
        UI.printDivider(1);

        if (name().matches("Room.*"))                  // place is room
            UI.printFormat(String.format("%s, you\'re in %s!\n%s",
                           ch.name().replace("The ", "").replace("A ", ""),
                           PNAME, PDESCRIPTION));
        else                                           // place isn't room
            UI.printFormat(String.format("%s, you\'re in the %s!\n%s",
                           ch.name().replace("The ", "").replace("A ", ""),
                           PNAME, PDESCRIPTION));

        for (Character c : placeCharacters)  // iterate thru characters :
            if (c != ch) c.display();        //   display
        for (Artifact  a : placeArtifacts)   // iterate thru artifacts  :
            a.display();                     //   display

        UI.printDivider(2);                  // print divider
    }

    protected void print()
    {
        UI.printDivider(1);

        UI.printFormat(String.format("%s", PDESCRIPTION));

        for (Direction d : paths)
            d.print();
        for (Character c : placeCharacters)
            c.print();
        for (Artifact  a : placeArtifacts)
            a.print();

        UI.printFormat(" ");
    }

    public static void printAll()
    {
        System.out.printf("\n\n");

        for (Map.Entry<Integer, Place> pair : allPlacesMap.entrySet())
        {
            Integer ID = pair.getKey();
            if (ID != 0 && ID != 1)
                pair.getValue().print();
        }

        UI.printDivider(1);
    }

    public static Place getEntryPlace() { return entryPlace; }

    // return random place other than nowhere and exit
    public static Place getRandomPlace() {
        // make list of place IDs
        ArrayList<Integer> IDs = new ArrayList<Integer>(allPlacesMap.keySet());
        // get random ID from list (excluding nowhere and exit)
        int randomID = IDs.get(2 + new Random().nextInt(IDs.size() - 2));

        return allPlacesMap.get(randomID);   // return place
    }//end getRandomPlace()

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
