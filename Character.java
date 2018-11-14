/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;


public abstract class Character{
  //Each players inventory
    protected Vector<Artifact> inventory;
    protected static TreeMap<Integer, Character> characterTree;
    protected String name = "";
    protected int CHARID;
    protected String description = "";
    protected DecisionMaker dm;
    protected int indexCurrentPlace;
    protected Place currPlace;
    protected boolean isPlayer;
    protected boolean quit = false;
    
    static{
        characterTree = new TreeMap<Integer, Character>();
    }

    //scanner constructor, creates the character based off the file by using
    // StringTokenizer to parse each line
    public Character(Scanner in, int ver)
    {
        String line = in.nextLine();
        while (line.length() == 0 || line.startsWith("//")|| line.trim().startsWith("//")) 
        {	//skips empty and comments
            line = in.nextLine();
        }
        StringTokenizer st = new StringTokenizer(line);

        indexCurrentPlace = Integer.valueOf(st.nextToken());
        line = in.nextLine();
        st = new StringTokenizer(line);
        CHARID = Integer.valueOf(st.nextToken());
        while( st.hasMoreTokens() )
        {    //creates the name of the artifact
            String nameS = st.nextToken();

            if (name.equals("")) 
            {
                name = nameS;
            } else 
            {
                name += " " + nameS;
            }
        }
        line = in.nextLine();
        st = new StringTokenizer(line);
        int numLines = Integer.valueOf(st.nextToken());
        int i =0;
        while(i < numLines) 
        {	//creates the description
            description += in.nextLine() + "\n";
            i++;
        }
        inventory = new Vector<Artifact>();
        
        if(indexCurrentPlace == 0)
        {
            indexCurrentPlace = Place.getRandomPlace().getIndex();
            Place.getPlacebyID(indexCurrentPlace).addCharacter(this);
        }
        else 
        {
            Place.getPlacebyID(indexCurrentPlace).addCharacter(this);
        }
        
        currPlace = Place.getPlacebyID(indexCurrentPlace);
        if (!characterTree.containsKey(CHARID))
            characterTree.put(CHARID, this);
    }

    public Character(int _id, String _name, String _desc){
        CHARID = _id;
        name = _name;
        description = _desc;

        inventory = new Vector<Artifact>();

        Random rand = new Random();

        int rndPlaceID = rand.nextInt(Place.getSizePlaces()-2) + 2;
        currPlace = Place.getPlacebyID(rndPlaceID);
}



//parameter constructor, also accounts for a random starting place if 0
// happens to be the first place read in
public Character(int id, String name, String description, int firstPlace,
        String charType){
    this.CHARID = id;
    this.name = name;
    this.description = description;
    indexCurrentPlace = firstPlace;
    if(charType.equalsIgnoreCase("NPC")){
        dm = new AI();
    }
    else{
        dm = new UI();
    }
    if(indexCurrentPlace == 0){
        Random rand = new Random();
        int random = rand.nextInt(Place.getSizePlaces()) + 1;
        int count = 1;
        for(Map.Entry<Integer,Place> p : Place.getPlaceDirectory().entrySet()){
            if(count == random){
                p.getValue().addCharacter(this);
                break;
            }
            else ++count;
        }
    }
    else {
        Place.getPlacebyID(indexCurrentPlace).addCharacter(this);
    }
    inventory = new Vector<>();
}

public static Character getCharacterbyID(int ID){   
        return characterTree.get(ID); 
}

public int getIndexCurrentPlace(){
    return indexCurrentPlace;
}

public abstract boolean makeMove();

public void print(){
    System.out.println("Player name: " + name + " ID: " + CHARID +
            "\nDescription: " + description + "\nCurrent Place: " + indexCurrentPlace);
}

public void display() {
    // print name + desc
    UI.printFormat(String.format("In your midst is %s.\n%s",
                   name.replace("The ", "the ").replace("A ", "a "),
                   description));
}

public void getArtifact(Character c, String arti) {	//checks to see if the
    // artifact
    // is in the room, then adds to player inventory
    Artifact a = Place.getPlacebyID(c.indexCurrentPlace).removeArtifactByName(arti);

    if (a == null) {	//if not in room
        if(isPlayer)
            System.out.println("Item is not in this room.");
    }
    else {
        inventory.add(a);	//adds to player inventory
        if(isPlayer)
            System.out.println(a.name() + " was added to your inventory.");
    }
}
public void removeArtifact(Artifact a) {    //removes
    // artifact from player inventory and adds to place artifacts
    String name = a.name();
    Place.getPlacebyID(this.indexCurrentPlace).addArtifact(a);
    inventory.remove(a);
    if (isPlayer) {//makes sure the player gets the message, npc does not
        // need it
        System.out.println(name + " was dropped in this room.\n");
    }
}
public void addArtifact(Artifact a){
    inventory.add(a);
}

public Artifact getArtifact()             
{
    if (inventory.size() > 0) 
        return inventory.get(0);
    else                      
        return null;
}

// return character's current place name
public String   placeName()              { return currPlace.name(); }
// return character's current place
public Place    getCurrentPlace()        { return currPlace;        }
// set    character's current place
public void     setCurrentPlace(Place p) { currPlace = p;           }
// return character name
public String   name()                   { return name;             }
// return player vs NPC
public boolean isPlayer()                {  return  isPlayer;       }
// return inventory
public Vector<Artifact> getInventory()   { return  inventory;       }
}
