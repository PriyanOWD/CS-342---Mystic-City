import java.util.*;

public class Character {
    private Vector<Artifact> inventory;
    private static TreeMap<Integer, Character> characterTree;
    private String name = "";
    private int CHARID;
    private String description = "";
    protected DecisionMaker dm;
    private int indexCurrentPlace;
    private boolean isPlayer;
    private boolean quit = false;

//scanner constructor, creates the character based off the file by using
// StringTokenizer to parse each line
    public Character(Scanner in, int ver){
        String line = in.nextLine();
        while (line.length() == 0 || line.startsWith("//")|| line.trim().startsWith("//")) {	//skips empty and comments
            line = in.nextLine();
        }
        StringTokenizer st = new StringTokenizer(line);
        
        indexCurrentPlace = Integer.valueOf(st.nextToken());
        line = in.nextLine();
        st = new StringTokenizer(line);
        CHARID = Integer.valueOf(st.nextToken());
        while( st.hasMoreTokens() ) {    //creates the name of the artifact
            String nameS = st.nextToken();

            if (name.equals("")) {
                name = nameS;
            } else {
                name += " " + nameS;
            }
        }
        line = in.nextLine();
        st = new StringTokenizer(line);
        int numLines = Integer.valueOf(st.nextToken());
        int i =0;
        while(i < numLines) {	//creates the description
            description += in.nextLine() + "\n";
            i++;
        }
        inventory = new Vector<Artifact>();
        if(indexCurrentPlace == 0){
            Random rand = new Random();
            boolean escape = false;
            int random = rand.nextInt(Place.getSizePlaces()) + 1;
            while(!escape) {

                int count = 1;
                for (Map.Entry<Integer, Place> p : Place.getPlaceDirectory().entrySet()) {
                    if (count == random) {
                        if(p.getKey() == 0 || p.getKey() == 1){
                            //should it
                            // be in a place that it shouldn't be in, redo
                            // random
                            random = rand.nextInt(Place.getSizePlaces()) + 1;
                            break;
                        }
                        indexCurrentPlace = p.getKey();
                        p.getValue().addCharacter(this);
                        escape = true;
                        break;
                    } else ++count;
                }
            }
        }
        else {
            Place.getPlacebyID(indexCurrentPlace).addCharacter(this);
        }
 }

 //parameter constructor, also accounts for a random starting place if 0
 // happens to be the first place read in
 public Character(int HP, String name, String description, int firstPlace,
                  String charType){
        this.HP = HP;
        this.name = name;
        this.description = description;
        indexCurrentPlace = firstPlace;
        if(charType.equalsIgnoreCase("NPC")){
            chooser = new AI();
        }
        else{
            chooser = new UI();
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
       if (characterTree.get(ID) != null){
           return characterTree.get(ID);
       }
       else return null;
    }
    public int getIndexCurrentPlace(){
        return indexCurrentPlace;
    }
    public void makeMove(){ //makes the move specified in the Move object
        Move nextMove = chooser.getMove(this,
                Place.getPlacebyID(this.indexCurrentPlace)); //actually make
        // the Move object
        String s = nextMove.getMoveType().toString();
        switch (s){
            case "GO":{//go to a specified place if possible
                Place next =
                        Place.getPlacebyID(this.indexCurrentPlace).followDirection(nextMove.getArgument());
                //next.print();
                Place.getPlacebyID(this.indexCurrentPlace).removeCharacter(this);
                next.addCharacter(this);
                indexCurrentPlace = next.getIndex();

                if( next.getIndex() == 1 || next.getIndex() == 0) { //exits the game if
                    // the player tries to go here, this is empty room and exit room.
                    return;
                }
                return;
                }
            case "QUIT":
            case "EXIT":{//exits the game
                if(isPlayer) {
                    quit = true;
                    System.out.println("Thank you for playing!");
                    return;
                }
                else{
                    return;
                }
            }
            case "LOOK":{//displays place information
                Place.getPlaceByID(this.indexCurrentPlace).print();
                return;
            }
            case "GET":{//tries to get the specified object
                getArtifact(this, nextMove.getArgument());
                return;
            }
            case "DROP":{//tries to drop the specified object
                for(Artifact a : inventory) {	//looks through the
                    // vector if it's there
                    if(a.name().equalsIgnoreCase(nextMove.getArgument())) {	//if the found,
                        // removes from inventory
                        removeArtifact(a);
                        break;
                    }
                }
                return;
            }
            case "USE":{//tries to use the specified object
                boolean change = false;
                for(Artifact a : inventory) {	//looks through the vector for the
                    // artifact
                    if(a.name().equalsIgnoreCase(nextMove.getArgument())) {
                        //if found tries to use the artifact on each direction
                        a.use(this, Place.getPlaceByID(this.indexCurrentPlace));
                        change = true;
                        break;
                    }
                }
                if(!change){
                    System.out.println("No doors could be opened.\n");
                }
                return;
            }
            case "INVENTORY":
            case "INVE":{//prints out player inventory
                if(inventory.size() == 0) {	//if the list is empty the player has
                    // nothing in the inventory
                    System.out.println("There is nothing in your inventory.");
                }
                else {
                    for(Artifact a : inventory) {
                        a.print();
                    }
                }
                return;
            }
            default: {//do nothing if no command matches
                //nothing
                return;
            }
        }
    }
    public void print(){
        System.out.println("Player name: " + name + " Current HP: " + HP +
                "\nDescription: " + description + "\nCurrent Place: " + indexCurrentPlace);
    }
    public void display(){
        System.out.println(name + "'s turn" + "\nCurrent HP: " + HP + "\n" +
                "--------------------------------------------");
    }
    public void getArtifact(Character c, String arti) {	//checks to see if the
        // artifact
        // is in the room, then adds to player inventory
        Artifact a = Place.getPlacebyID(c.indexCurrentPlace).getArtifact(arti);

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
    public int getHP(){
        return HP;
    }
    public boolean isPlayer(){ return  isPlayer;}   //player vs npc
    public Vector<Artifact> getInventory() {return  inventory;}
    public boolean quit(){ return quit;}//used to quit the game
}
