/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;
import java.util.regex.Pattern;


public class Direction
{
    private enum dirType
    {
        NONE ("NONE",            "NONE"),
        N    ("NORTH",           "N"   ),
        S    ("SOUTH",           "S"   ),
        E    ("EAST",            "E"   ),
        W    ("WEST",            "W"   ),
        U    ("UP",              "U"   ),
        D    ("DOWN",            "D"   ),
        NE   ("NORTHEAST",       "NE"  ),
        NW   ("NORTHWEST",       "NW"  ),
        SE   ("SOUTHEAST",       "SE"  ),
        SW   ("SOUTHWEST",       "SW"  ),
        NNE  ("NORTH-NORTHEAST", "NNE" ),
        NNW  ("NORTH-NORTHWEST", "NNW" ),
        ENE  ("EAST-NORTHEAST",  "ENE" ),
        WNW  ("WEST-NORTHWEST",  "WNW" ),
        ESE  ("EAST-SOUTHEAST",  "ESE" ),
        WSW  ("WEST-SOUTHWEST",  "WSW" ),
        SSE  ("SOUTH-SOUTHEAST", "SSE" ),
        SSW  ("SOUTH-SOUTHWEST", "SSW" );

        private final String text;
        private final String abbreviation;

        private dirType(String text, String abbreviation)
        {
            this.text = text;
            this.abbreviation = abbreviation;
        }

        public String toString()
        {
            return text;
        }
        public String   toAbbreviation()  
        {
            return abbreviation; 
        }

        public boolean match(String str)
        {
            str = str.toLowerCase();
            return (str.equals(text.toLowerCase()) || str.equals(abbreviation.toLowerCase()));
        }
    }

    private final int D_ID;
    private final dirType DIRECTION;
    private final Pattern d_delim_Pattern = Pattern.compile("\r?\n");
    private final Place SOURCE;
    private final Place DESTINATION;
    private final int lockPattern;
    private boolean secure;

    // private Vector<String> NORTH = new Vector<String>(); // = {"N"}; //Unused old implementation of mapping used
    // input and directions

    public Direction(int ID, Place from, Place to, String dir)
    {
        D_ID = ID;
        SOURCE = from;
        DESTINATION = to;
        secure = false;
        DIRECTION = dirType.NONE;
        lockPattern = 1;
    }

    public Direction(Scanner scn, int ver)
    {
        Scanner sc = new Scanner(CleanLineScanner.gameFileParser(scn, d_delim_Pattern));

        D_ID = Integer.valueOf(sc.next(("\\d+")));
        SOURCE = Place.getPlacebyID(Integer.valueOf(sc.next(("\\d+"))));

        DIRECTION = dirType.valueOf(sc.next(("\\w+")));

        Integer LorUL_destID = Integer.valueOf(sc.next(("-?\\d+")));
        if (LorUL_destID < 0)
            secure = true;
        else
            secure = false;

        DESTINATION = Place.getPlacebyID(Math.abs(LorUL_destID));
        lockPattern = Integer.valueOf(sc.next(("\\d+")));
        SOURCE.addDirection(this);
        sc.close();
    }

    public boolean match(String s)
    {

        return DIRECTION.match(s);
    }

    public boolean leadsToNowhere() // Function that checks if direction leads to nowhere
    {
        return this.DESTINATION.checkID(0);
    }

    public void lock()
    {
        if (isLocked())
            System.out.println("Already Locked");
        else
            System.out.println("Locking");
        secure = true;
        return;
    }

    public void unlock()
    {
        if (!isLocked())
            System.out.println("Already Unlocked");
        else
            System.out.println("Unlocking");
        secure = false;
        return;
    }

    public boolean isLocked()
    {
        // return secure;
        return secure == true;
    }

    public Place follow()
    {
        if (!isLocked())
            return DESTINATION;

        System.out.println("Attempted Location:  " + DESTINATION.name() + "   ...is Locked\n");
        return SOURCE;
    }

    public void display()
    {
        System.out.print(DIRECTION + ": leads to ->" + DESTINATION.name());
        if (isLocked())
            System.out.println(" LOCKED");
        else
            System.out.println(" UNLOCKED");
        return;
    }

    public void useKey(Artifact dirArtifact)
    {
        if (lockPattern == 0)
        {
            System.out.println("Path to " + DESTINATION.name() + " won't change \n");
            return;
        }

        if (dirArtifact.Master())
        {
            if (isLocked())
                unlock();
            else
                lock();
            return;
        }

        if (dirArtifact.Match(this.lockPattern))
        {
            if (isLocked())
                unlock();
            else
                lock();
            System.out.println(DESTINATION.name() + "!\n");
        } else
        {
            System.out.println(dirArtifact.name() + " not applicable when going " + DIRECTION + " from " + SOURCE.name()
                    + " to " + DESTINATION.name() + "\n");
        }
        return;
    }

    public void print()
    {
        System.out.println("Direction ID:" + D_ID + "Cardinal Direction:" + DIRECTION + "Name of origination Place:"
                + SOURCE.name() + "Name of target Place:" + DESTINATION.name());
        return;
    }
    
    
    public Place getTo() { return DESTINATION; }
    public boolean isValid() { return !DESTINATION.checkID(0) && !DESTINATION.checkID(1); }
    
    public static String matchDirection(String str) {
        // iterate thru DirType enumerator constants
        for (dirType dt : dirType.values())         // iterate thru DirTypes :
            if (str.equals(dt.toString()) || str.equals(dt.toAbbreviation()))
                return dt.toString().toLowerCase(); //   return match

        return "\'" + str.toLowerCase() + "\'";     // return no match
    }//end matchDirection()
    
    // return true if passed string has valid direction
    public static boolean isDirection(String str) {
        // iterate thru DirType enumerator constants
        for (dirType dt : dirType.values()) // iterate thru DirTypes :
            if (str.equals(dt.toString()) || str.equals(dt.toAbbreviation()))
                return true;                //   return true

        return false;                       // return false
    }//end isDirection()
}
