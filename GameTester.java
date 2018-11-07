/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// GameTester class with main() method to run and test game
public class GameTester {
    public static void main(String[] args) {
        System.out.printf("CS342 Term Project Part III\n" +
                          "Name:\t"  + "Shyam Patel\n"    +
                          "NetID:\t" + "spate54\n");      // name + NetID

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
