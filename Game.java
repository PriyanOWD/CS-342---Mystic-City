/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 *
 * Name:   Shyam Patel
 * NetID:  spate54
 *
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 *
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 *
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;
import java.util.Scanner;


// Game class to encapsulate attributes and methods for Game object
public class Game {
    // private attributes
    private String               name;               // game name
    private int                  ver;                // game version
    public  int                  numPlayers;         // # of players
    private ArrayList<Character> characters;         // collection of characters


    // constructor for Game class
    public Game(Scanner sc, int num) {
        try {
            characters = new ArrayList<Character>(); // allocate characters
            numPlayers = num;                        // set # of players

            Scanner s  = new Scanner(CleanLineScanner.getCleanLine(sc));
            ver        = Integer.parseInt(s.skip("GDF").next().replace(".",""));
            name       = CleanLineScanner.getCleanLine(s);   // set name
            s.close();                                       // close scanner

                                                             // allocate :
            new Place(0, "nowhere", "");                     //   nowhere
            new Place(1, "exit",    "");                     //   exit
            if (ver > 50) allocatePlaces(sc);                //   places
            else          allocateObjects(sc, "PLACES");
            allocateObjects(sc, "DIRECTIONS");               //   directions
            if      (ver > 50) allocateCharacters(sc);       //   characters
            else if (ver > 39) allocateObjects(sc, "CHARACTERS");
            else {
                if (numPlayers < 1) numPlayers = UI.requestNumPlayers();
                allocatePlayers(0);                          //   (manual entry)
            }
            if      (ver > 50) allocateArtifacts(sc);        //   artifacts
            else if (ver > 29) allocateObjects(sc, "ARTIFACTS");

            System.out.printf("\nWelcome to %s!\n",          // print name
                              name().replace("\t", " ").replace("!", ""));

            int n = UI.requestInterface();
            IO ourIO = IO.getIO();
            ourIO.selectInterface(n);
        } catch (Exception e) { e.printStackTrace(); }       // exception
    }//end class constructor


    // return game name
    public String name()                    { return name;       }
    // return game version
    public int    version()                 { return ver;        }
    // add character to collection of characters
    public void   addCharacter(Character c) { characters.add(c); }


    // main infinite loop to play game
    public void play() {
        IO gameIO = IO.getIO();
        for(;;) {                             // infinite loop :
            for (Character c : characters) {  //   iterate thru characters :
                checkWinner();                //   check winner
                if (c.isActive && c instanceof Player) {
                    Player p = (Player) c;
                    gameIO.switchCard(p, p.currPlace);
                    if (p.currHP > 0) p.look(2);
                }
                while (!c.makeMove())        //     make move until "GO"
                {
                    IO.getIO().update();
                }
            }//end for...

            Place.updatePlaces();
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

    
    // allocate characters according to type (v5.1+)
    private void allocateCharacters(Scanner sc) {
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            int num   = s.skip("CHARACTERS").nextInt(); // get # of characters
            s.close();                                  // close scanner

            int playerNum = 0;                          // player #
            for (int i = 0; i < num; i++) {             // allocate artifacts :
                String type = CleanLineScanner.getCleanLine(sc);
                Character c;
                if      (type.equals("NPC"     ))
                    c = new NPC     (sc, version());
                else if (type.equals("ATTACKER"))
                    c = new Attacker(sc, version(), ++playerNum);
                else if (type.equals("TANK"    ))
                    c = new Tank    (sc, version(), ++playerNum);
                else if (type.equals("HEALER"  ))
                    c = new Healer  (sc, version(), ++playerNum);
                else if (type.equals("BRUISER" ))
                    c = new Bruiser (sc, version(), ++playerNum);
                else
                    c = new Player  (sc, version(), ++playerNum);
                
                addCharacter(c);                        // add to collection
            }//end for...
        } catch (Exception e) { e.printStackTrace(); }  // exception
    }//end allocateCharacters()


    // allocate artifacts according to type (v5.1+)
    private void allocateArtifacts(Scanner sc) {
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            int num   = s.skip("ARTIFACTS").nextInt(); // get # of artifacts
            s.close();                                 // close scanner

            for (int i = 0; i < num; i++) {            // allocate artifacts :
                String type = CleanLineScanner.getCleanLine(sc);
                if      (type.equals("ARMOR" )) new Armor   (sc, version());
                else if (type.equals("WEAPON")) new Weapon  (sc, version());
                else if (type.equals("FOOD"  )) new Food    (sc, version());
                else                            new Artifact(sc, version());
            }//end for...
        } catch (Exception e) { e.printStackTrace(); } // exception
    }//end allocateArtifacts()


    private void allocatePlaces(Scanner sc) {
        try {
            Scanner s = new Scanner(CleanLineScanner.getCleanLine(sc));
            int num   = s.skip("PLACES").nextInt();    // get # of artifacts
            s.close();                                 // close scanner

            for (int i = 0; i < num; i++) {            // allocate artifacts :
                String type = CleanLineScanner.getCleanLine(sc);
                if      (type.equals("DEFAULT"   )) new Place     (sc, version());
                else if (type.equals("DANGERZONE")) new DangerZone(sc, version());
                else if (type.equals("SAFEZONE"  )) new SafeZone  (sc, version());
                else if (type.equals("MARKET"    )) new Market    (sc, version());
                else                                new Place     (sc, version());
            }//end for...
        } catch (Exception e) { e.printStackTrace(); } // exception
    }//end allocateArtifacts()


    // allocate remaining players and support pre-4.0 backward compatibility
    private void allocatePlayers(int num) {
        while (num < numPlayers) {
            // request player name
            String playerName = UI.requestPlayerName(++num);

            // allocate and add player to collection
            addCharacter(new Player(num + 100, playerName, "", num));
        }//end loop
    }//end allocatePlayers()


    // declare last player standing
    private void checkWinner() {
        IO printIO = IO.getIO();
        int numActivePlayers = 0;
        Player p = null;

        for (Character c : characters) {
            if (c.isActive && c instanceof Player) {
                p = (Player) c;
                numActivePlayers++;
            }
        }

        if (numActivePlayers < 2) {
            printIO.display(String.format("%s, YOU WIN. CONGRATULATIONS!\n",
                    p.name.toUpperCase()));
            try { Thread.sleep(4000); } catch (Exception e) { }
            System.exit(0);
        }
    }//end declareWinner()
}//end Game class
