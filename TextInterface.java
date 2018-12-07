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

import java.util.Scanner;


// text interface
public class TextInterface implements UserInterface {
    public void display(String message) {
        System.out.printf(message);
    }//end display()

    public String getLine() {
        Scanner s = KeyboardScanner.getKeyboardScanner();
        return s.nextLine().trim();
    }//end getLine()

    public void switchCard(Player p, Place place) { }
}//end TextInterface class
