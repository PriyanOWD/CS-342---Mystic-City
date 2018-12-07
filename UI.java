/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;
import java.util.Scanner;


public class UI implements DecisionMaker {
    // private attribute
    private static StringBuilder output;
    static { output = new StringBuilder(); }

    public Move getMove(Character c) {
        // REMOVE PLAYER     : dead
        if (c.currHP == 0 && c.isActive && c instanceof Player)
            return new RemovePlayer((Player) c);

        IO printIO = IO.getIO();
        String a   = printIO.getLine().toUpperCase();

        // "QUIT"    command : quit game
        if (a.matches(".*QUIT.*") || a.matches(".*EXIT.*") || a.equals("Q"))
            return new Quit();

        // "COMMANDS"        : display list of commands
        else if (a.matches(".*COMMANDS.*"))
            return new Commands();

        // "LOOK"    command : redisplay current place
        else if (a.matches(".*LOOK.*"))
            return new Look((Player) c);

        // "GET"     command : get artifact from current place
        else if (a.matches("GET.*"))
            return new GetPlayer((Player) c, a);

        // "DROP"    command : drop artifact
        else if (a.matches("DROP.*"))
            return new DropPlayer((Player) c, a);

        // "USE"     command : use artifact to unlock door
        else if (a.matches("USE.*"))
            return new Use((Player) c, a);

        // "EQUIP"   command : equip artifact
        else if (a.matches("EQUIP.*"))
            return new Equip((Player) c, a);

        // "CONSUME" command : equip artifact
        else if (a.matches("CONSUME.*"))
            return new Consume((Player) c, a);

        // "INVE"    command : display player's possessions
        else if (a.matches(".*INVE.*"))
            return new Inventory((Player) c);

        // "STATS"   command : display player's stats
        else if (a.matches(".*STAT.*"))
            return new Stats((Player) c);

        // "INSPECT" command : display the footprints in the current Place
        else if (a.matches(".*INSPECT.*"))
            return new Inspect(c.currPlace);

        // "BUY"     command : buy market item
        else if (a.matches(".*BUY.*"))
            return new Buy((Player) c, a);

        // "GO"      command : go to direction(s)
        else if (a.matches("GO.*") || Direction.isDirection(a))
            return new GoPlayer((Player) c, a);

        // unknown command
        else {
            printIO.display("Sorry, the command wasn\'t recognized. " +
                            "Try one of the following.\n");
            return new Commands();
        }
    }//end getMove()


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


    // request game interface interface
    public static int requestInterface() {
        System.out.printf("\n");
        for (;;) {
            System.out.printf("\nPlease select an interface.\n" +
                              "0 : Text Interface\n"            +
                              "1 : GUI #1\n"                    +
                              "2 : GUI #2\n"                    +
                              "3 : GUI #3\n"                    +
                              ">> ");                           // request inter
            int n = 0;
            try {
                Scanner sc = KeyboardScanner.getKeyboardScanner();
                Scanner s = new Scanner(sc.nextLine().trim());  // read input
                if (s.hasNextInt()) n = s.nextInt();            // get #
                s.close();                                      // close scanner
                if (n > -1 && n < 4) return n;                  // return #
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end requestInterface()


    // request match number for potential matched Artifact objects
    // for use in "GET", "DROP" and "USE" commands
    public static int requestNumber(ArrayList<Artifact> matches, String str) {
        IO printIO = IO.getIO();
        for (;;) {
            int n = 0;
            for (Artifact a : matches)                          // print matches
                printIO.display(String.format("Enter \'%d\' to %s the %s.\n",
                                ++n, str, a.name().toLowerCase()));
            n = 0;
            try {
                Scanner s = new Scanner(printIO.getLine().trim());// read input
                if (s.hasNextInt()) n = s.nextInt();            // get #
                s.close();                                      // close scanner
                if (n > 0 && n <= matches.size()) return n;     // return #
            } catch (Exception e) { e.printStackTrace(); }      // exception
        }//end for (;;)...
    }//end requestNumber()


    // print formatted boxed lines for use in display() and print() methods
    public static void printFormat(String str) {
        output.setLength(0);
        StringBuilder output = new StringBuilder();
        String lines[] = str.split("\\r?\\n");  // split lines
  
        for (String s : lines) {                // append formatted lines
            s = s.substring(0, Math.min(s.length(), 76));
            output.append(String.format("%-79s#\n", s.replaceAll("(?m)^", "# ")));
        }

        IO printIO = IO.getIO();
        printIO.display(output.toString());     // print formatted output
    }//end printFormat()


    // print formatted divider for use in display() and print() methods
    public static void printDivider(int newlines) {
        String div = "########################################";
        output.setLength(0);
        output.append(div);
        output.append(div);

        for (int i = 0; i < newlines; i++)    // # of newlines :
            output.append("\n");              //   append

        IO printIO = IO.getIO();
        printIO.display(output.toString());   // print divider
    }//end printHeader()
}
