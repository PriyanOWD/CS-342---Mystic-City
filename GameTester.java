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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// GameTester class with main() method to run and test game
public class GameTester {
    public static void main(String[] args) {
        System.out.printf("CS342 Term Project Part V\n"          +
                          "Group 48\n"                          +
                          "GUI_1: psures5, Priyan Sureshkumar\n" +
                          "GUI_2: spate54, Shyam Patel\n"        +
                          "GUI_3: svoorh2, Joey Voorhees\n");

        String pathname = "";                             // pathname
        int    num      = 0;                              // # of players
        try {                                             // get pathname :
            if (args.length > 0) pathname = args[0];              // arg given
            else                 pathname = UI.requestPath();     // request
                                                          // get # of players :
            if (args.length > 1) num = Integer.parseInt(args[1]); // arg given

            Scanner sc = new Scanner(new File(pathname)); // open thru scanner
            Game g     = new Game(sc, num);               // allocate game
            sc.close();                                   // close scanner

            // debugging : print all places, directions, characters + artifacts
            //Place.printAll();

            g.play();                                     // play game
        } catch (FileNotFoundException e) {               // not found
            System.err.printf("\nFile \'%s\' could not be found.\n", pathname);
        } catch (Exception e) { e.printStackTrace(); }    // exception
    }//end main()
}//end GameTester class
