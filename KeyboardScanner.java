/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
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
