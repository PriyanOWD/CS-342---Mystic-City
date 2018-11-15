/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;


public abstract class Character
{
    // each players inventory
    protected Vector<Artifact> inventory;
    protected static TreeMap<Integer, Character> characterTree;
    protected String name = "";
    protected int CHARID;
    protected String description = "";
    protected DecisionMaker dm;
    protected Place currPlace;
    protected int attack;
    protected int defense;
    protected int currHP;
    protected int maxHP;
    protected int mp;

    static
    {
        characterTree = new TreeMap<Integer, Character>();
    }

    // scanner constructor, creates the character based off the file by using
    // StringTokenizer to parse each line
    public Character(Scanner in, int ver)
    {
        String line = in.nextLine();
        while (line.length() == 0 || line.startsWith("//")|| line.trim().startsWith("//")) 
        {	// skips empty and comments
            line = in.nextLine();
        }
        StringTokenizer st = new StringTokenizer(line);

        int indexCurrentPlace = Integer.valueOf(st.nextToken());
        line = in.nextLine();
        st = new StringTokenizer(line);
        CHARID = Integer.valueOf(st.nextToken());
        while (st.hasMoreTokens())
        {   // creates the name of the artifact
            String nameS = st.nextToken();
            if (name.equals(""))
                name = nameS;
            else
                name += " " + nameS;
        }
        line = in.nextLine();
        st = new StringTokenizer(line);
        int numLines = Integer.valueOf(st.nextToken());
        int i =0;
        while (i < numLines)
        {	// creates the description
            description += in.nextLine() + "\n";
            i++;
        }
        inventory = new Vector<Artifact>();
        
        if (indexCurrentPlace == 0)
            currPlace = Place.getRandomPlace();
        else
            currPlace = Place.getPlacebyID(indexCurrentPlace);

        currPlace.addCharacter(this);
        if (!characterTree.containsKey(CHARID))
            characterTree.put(CHARID, this);

        if (ver > 50) {
            // new stats here
            st      = new StringTokenizer(line);
            attack  = Integer.valueOf(st.nextToken());
            defense = Integer.valueOf(st.nextToken());
            currHP  = Integer.valueOf(st.nextToken());
            maxHP   = Integer.valueOf(st.nextToken());
            mp      = Integer.valueOf(st.nextToken());
            line    = in.nextLine();
            st      = new StringTokenizer(line);
            type    = st.nextToken();
        }
    }

    public Character(int _id, String _name, String _desc)
    {
        CHARID      = _id;
        name        = _name;
        description = _desc;
        attack      = 10;
        defense     = 10;
        currHP      = 10;
        maxHP       = 10;
        mp          = 10;
        type        = "Default";

        inventory = new Vector<Artifact>();

        currPlace = Place.getEntryPlace();
        
        currPlace.addCharacter(this);
        if (!characterTree.containsKey(CHARID))
            characterTree.put(CHARID, this);
    }


    public static Character getCharacterbyID(int ID)
    {
        return characterTree.get(ID);
    }

    public void display()
    {
        UI.printFormat(String.format("In your midst is %s.\n%s",
                       name.replace("The ", "the ").replace("A ", "a "), description));
    }

    public void stats()
    {
        UI.printFormat(String.format("Attack: %d, Defense: %d, HP: %d/%d, MP: %d\n ",
                       attack, defense, currHP, maxHP, mp));
    }

    public void print()
    {
        UI.printFormat(String.format("C%-5s%s\n%s", CHARID, name,
                       description.replaceAll("(?m)^", "      ")));
    }

    public String name()
    {
        return name;
    }

    public String placeName()
    {
        return currPlace.name();
    }

    public Place getCurrentPlace()
    {
        return currPlace;
    }

    public void setCurrentPlace(Place p)
    {
        currPlace = p;
    }

    public void addArtifact(Artifact a)
    {
        inventory.add(a);
    }

    public void removeArtifact(Artifact a)
    {
        inventory.remove(a);
    }

    public Artifact getArtifact()
    {
        if (inventory.size() > 0)
            return inventory.get(0);
        else
            return null;
    }

    public abstract boolean makeMove();
}
