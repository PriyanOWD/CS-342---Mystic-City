import java.util.Scanner;

public class UI implements DecisionMaker {

    public Move getMove(Character c, Place p){
            //c.display();
            //p.print();	//gets the room information from place
            Scanner keyboard  = KeyboardScanner.getKeyboardScanner();
            String command = keyboard.nextLine();	//grabs the next input
            int commandLength = command.length();
                if(command.equalsIgnoreCase("QUIT") || command.equalsIgnoreCase("EXIT")) { //sanity check to exit the game
                    return new Move(Move.MoveType.QUIT, "");
                }
                else if(command.equalsIgnoreCase("LOOK")) {
                   return new Move(Move.MoveType.LOOK, "");	//displays the
                    // current room again
                }
                else if(commandLength >= 2 && command.substring(0,2).equalsIgnoreCase("GO")) { //goes to the direction specified
                    return new Move(Move.MoveType.GO, command.substring(3));
                }
                else if(commandLength >= 3 && command.substring(0,3).equalsIgnoreCase("GET")) {
                    //remove from current place and add to inventory
                    return new Move(Move.MoveType.GET, command.substring(4));
                }
                //returns drop with a specific object name to remove from
                // inventory
                else if(commandLength >= 4 && command.substring(0,4).equalsIgnoreCase("DROP")) {
                    return new Move(Move.MoveType.DROP, command.substring(5));
                }
                //returns use command
                else if(commandLength >= 3 && command.substring(0,3).equalsIgnoreCase("USE")) {
                   return new Move(Move.MoveType.USE, command.substring(4));
                }
                //returns Inventory command
                else if(commandLength >= 4 && command.substring(0,4).equalsIgnoreCase("INVE")) {
                   return new Move(Move.MoveType.INVE, "");
                }
                else {
                    //returns nothing for invalid command
                    return new Move(Move.MoveType.NOTHING, "");
                }
            }


    // request pathname to access game data when command line argument
    // is not provided by the user
    public static String requestPath() {
        System.out.printf("\n");
        for (;;) {
            System.out.printf("Enter pathname to read GDF.\n" +
                              ">> ");                           // request path
            String path = "";
            try {
                Scanner sc = KeyboardScanner.getKeyboardScanner();
                Scanner s  = new Scanner(sc.nextLine().trim()); // read input
                if (s.hasNextLine()) path = s.nextLine();       // get path
                s.close();                                      // close scanner
                if (path.matches(".*.gdf")) return path;        // return path
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end requestPath()


    // request number of players to support pre-4.0 backward compatibility
    public static int requestNumPlayers() {
        System.out.printf("\n");
        for (;;) {
            System.out.printf("Enter number of players.\n>> "); // request #
            int n = 0;
            try {
                Scanner sc = KeyboardScanner.getKeyboardScanner();
                Scanner s  = new Scanner(sc.nextLine().trim()); // read input
                if (s.hasNextInt()) n = s.nextInt();            // get #
                s.close();                                      // close scanner
                if (n > 0) return n;                            // return #
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end requestNumPlayers()


    // request player name to support pre-4.0 backward compatibility
    public static String requestPlayerName(int playerNum) {
        System.out.printf("\n");
        for (;;) {
            System.out.printf("Player %d, enter your name.\n" +
                              ">> ", playerNum);                // request name
            String name = "";
            try {
                Scanner sc = KeyboardScanner.getKeyboardScanner();
                Scanner s  = new Scanner(sc.nextLine().trim()); // read input
                if (s.hasNextLine()) name = s.nextLine();       // get name
                s.close();                                      // close scanner
                if (name.length() > 0) return name;             // return name
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end requestPlayerName()


    // request match number for potential matched Artifact objects
    // for use in "GET", "DROP" and "USE" commands
    public static int requestNumber(ArrayList<Artifact> matches, String str) {
        for (;;) {
            int n = 0;
            for (Artifact a : matches)                          // print matches
                System.out.printf("Enter \'%d\' to %s the %s.\n",
                                  ++n, str, a.name().toLowerCase());
            System.out.printf(">> ");                           // request #
            n = 0;
            try {
                Scanner sc = KeyboardScanner.getKeyboardScanner();
                Scanner s  = new Scanner(sc.nextLine().trim()); // read input
                if (s.hasNextInt()) n = s.nextInt();            // get #
                s.close();                                      // close scanner
                if (n > 0 && n <= matches.size()) return n;     // return #
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end requestNumber()


    // prompt user if they would like to view their inventory
    // for use in "GET" command
    public static void promptInventory(Player p) {
        for (;;) {
            System.out.printf("Would you like to view your inventory " +
                              "(yes/no)?\n>> ");                // request y/n
            String response = "";
            try {
                Scanner sc = KeyboardScanner.getKeyboardScanner();
                Scanner s  = new Scanner(sc.nextLine().trim()); // read input
                if (s.hasNext()) response = s.next();           // get response
                s.close();                                      // close scanner
                if      (response.equalsIgnoreCase("yes") ||    // return yes
                         response.equalsIgnoreCase("y"  )) { p.inve(); return; }
                else if (response.equalsIgnoreCase("no" ) ||
                         response.equalsIgnoreCase("n"  ) ||    // return no
                         response.length() == 0)           {           return; }
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end promptInventory()


    // print formatted header for use in display() and print() methods
    public static void printHeader(String str) {
        String header = "";                      // header string

        int num = (78 - str.length()) / 2;       // determine # of hashes
        for (int i = 0; i < num; i++)            // # of hashes :
            header += "#";                       //   add hash to header

        header += " " + str.toUpperCase() + " "; // add str to header

        while (header.length() < 80)             // while len < 80 :
            header += "#";                       //   add hash to header

        System.out.printf("%s\n", header);       // print header
    }//end printHeader()


    // print formatted boxed lines for use in display() and print() methods
    public static void printFormat(String str) {
        String lines[] = str.split("\\r?\\n"); // split lines
        for (String s : lines) {               // print formatted lines
            s = s.substring(0, Math.min(s.length(), 76));
            System.out.printf("%-79s#\n", s.replaceAll("(?m)^", "# "));
        }
    }//end printFormat()


    // print formatted divider for use in display() and print() methods
    public static void printDivider(int newlines) {
        String div = "########################################";
        System.out.printf("%s%s", div, div); // print divider

        for (int i = 0; i < newlines; i++)   // # of newlines :
            System.out.printf("\n");         //   print newline
    }//end printHeader()
        }
