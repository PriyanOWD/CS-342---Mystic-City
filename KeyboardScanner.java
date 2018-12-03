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


// KeyboardScanner class to return scanner connected to System.in
public final class KeyboardScanner {
    // private attribute
    private static Scanner keyboardScanner;

    // initialize static attribute
    static { keyboardScanner = new Scanner(System.in); }

    // return keyboard scanner
    public static Scanner getKeyboardScanner() { return keyboardScanner; }
}//end KeyboardScanner class
