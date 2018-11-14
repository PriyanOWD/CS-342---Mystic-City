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
        public String toAbbreviation()
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
        secure = true;
    }

    public void unlock()
    {
        secure = false;
    }

    private boolean toggle()
    {
        if (isLocked())
        {
            unlock();
            return true;
        }
        else
        {
            lock();
            return false;
        }
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
        else {
            if (DESTINATION == Place.getPlacebyID(0))
                System.out.printf("Sorry, the door to nowhere is locked forever.\n");
            else if (DESTINATION.name().matches("Room.*"))
                System.out.printf("Sorry, the door to %s is locked.\n", DESTINATION.name());
            else
                System.out.printf("Sorry, the door to the %s is locked.\n", DESTINATION.name());
        }
        return SOURCE;
    }

    public boolean useKey(Artifact dirArtifact)
    {
        if (lockPattern != 0)
        {
            if (dirArtifact.matchKey(lockPattern))
            {
                if (toggle())
                    System.out.printf("You\'ve used the %s to unlock the door ",
                            dirArtifact.name().toLowerCase());
                else
                    System.out.printf("You\'ve used the %s to lock the door ",
                            dirArtifact.name().toLowerCase());
                if (DESTINATION.name().matches("Room.*"))
                    System.out.printf("to %s.\n",     DESTINATION.name());
                else
                    System.out.printf("to the %s.\n", DESTINATION.name());
            }
            else
            {
                System.out.printf("Sorry, the %s doesn\'t unlock the door ",
                        dirArtifact.name().toLowerCase());
                if (DESTINATION.name().matches("Room.*"))
                    System.out.printf("to %s.\n",     DESTINATION.name());
                else
                    System.out.printf("to the %s.\n", DESTINATION.name());
            }
            return true;
        }
        else if (isLocked() && DESTINATION == Place.getPlacebyID(0))
        {
            System.out.printf("Sorry, the %s can\'t unlock the door to " +
                    "nowhere.\n", dirArtifact.name().toLowerCase());
            return true;
        }
        return false;
    }

    public void print()
    {
        if (!isLocked())
            UI.printFormat(String.format("D%-5s%3s to %s",
                    D_ID, DIRECTION.toAbbreviation(), DESTINATION.name()));
        else if (lockPattern == 0)
            UI.printFormat(String.format("D%-5s%3s to %s, locked",
                    D_ID, DIRECTION.toAbbreviation(), DESTINATION.name()));
        else
            UI.printFormat(String.format("D%-5s%3s to %s, locked (%d)",
                    D_ID, DIRECTION.toAbbreviation(), DESTINATION.name(), lockPattern));
    }

    public Place getTo() { return DESTINATION; }
    public boolean isValid() { return !DESTINATION.checkID(0) && !DESTINATION.checkID(1); }

    public static String matchDirection(String str)
    {
        for (dirType dt : dirType.values())
            if (str.equals(dt.toString()) || str.equals(dt.toAbbreviation()))
                return dt.toString().toLowerCase();

        return "\'" + str.toLowerCase() + "\'";
    }

    public static boolean isDirection(String str)
    {
        for (dirType dt : dirType.values())
            if (str.equals(dt.toString()) || str.equals(dt.toAbbreviation()))
                return true;

        return false;
    }
}
