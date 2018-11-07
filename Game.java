/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.util.ArrayList;
import java.util.Scanner;


// Game class to encapsulate attributes and methods for Game object
public class Game {
    // private attributes
    private String               name;               // game name
    private int                  ver;                // game version
    private int                  numPlayers;         // # of players
    private ArrayList<Character> characters;         // collection of characters


    // constructor for Game class
    public Game(Scanner sc, int num) {
        try {
            characters = new ArrayList<Character>(); // allocate characters
            numPlayers = num;                        // set # of players

            Scanner s  = new Scanner(CleanLineScanner.getCleanLine(sc));
            ver        = (int) s.skip("GDF").nextDouble();  // set version
            name       = CleanLineScanner.getCleanLine(s);  // set name
            s.close();                                      // close scanner

                                                            // allocate :
            new Place(0, "nowhere", "");                    //   nowhere
            new Place(1, "exit",    "");                    //   exit
            allocateObjects(sc, "PLACES"    );              //   places
            allocateObjects(sc, "DIRECTIONS");              //   directions
            if (ver > 3) allocateObjects(sc, "CHARACTERS"); //   characters
            else {
                if (numPlayers < 1) numPlayers = UI.requestNumPlayers();
                allocatePlayers(0);                         //   (manual entry)
            }
            if (ver > 2) allocateObjects(sc, "ARTIFACTS" ); //   artifacts

            System.out.printf("\nWelcome to %s!\n",         // print name
                              name().replace("\t", " ").replace("!", ""));
        } catch (Exception e) { e.printStackTrace(); }      // exception
    }//end class constructor


    // return game name
    public String name()                    { return name;       }
    // return game version
    public int    version()                 { return ver;        }
    // add character to collection of characters
    public void   addCharacter(Character c) { characters.add(c); }


    // main infinite loop to play game
    public void play() {
        for(;;) {                             // infinite loop :
            for (Character c : characters) {  //   iterate thru characters :
                if (c instanceof Player)
                    ((Player) c).look(2);     //     display place if player

                while (!c.makeMove());        //     make move until "GO"
            }//end for...
        }//end for(;;)...
    }//end play()


    // parse header + allocate places, directions, characters or artifacts
    private void allocateObjects(Scanner sc, String str) {
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            int num   = s.skip(str).nextInt();         // get # of objects
            s.close();                                 // close scanner

            int playerNum = 0;                         // player #
            for (int i = 0; i < num; i++) {            // allocate objects :
                if      (str.equals("PLACES"    )) new Place    (sc, version());
                else if (str.equals("DIRECTIONS")) new Direction(sc, version());
                else if (str.equals("ARTIFACTS" )) new Artifact (sc, version());
                else if (str.equals("CHARACTERS")) {
                    Character c;
                    if (sc.next().equals("NPC"))       //   type is NPC
                        c = new NPC   (sc, version());
                    else                               //   type is player
                        c = new Player(sc, version(), ++playerNum);

                    addCharacter(c);                   //   add to collection
                }//end else if...
            }//end for...
            if (str.equals("CHARACTERS") && playerNum < numPlayers)
                allocatePlayers(playerNum);            // allocate remaining
        } catch (Exception e) { e.printStackTrace(); } // exception
    }//end allocateObjects()


    // allocate remaining players and support pre-4.0 backward compatibility
    private void allocatePlayers(int num) {
        while (num < numPlayers) {
            // request player name
            String playerName = UI.requestPlayerName(++num);

            // allocate and add player to collection
            addCharacter(new Player(num + 100, playerName, "", num));
        }//end loop
    }//end allocatePlayers()
}//end Game class
