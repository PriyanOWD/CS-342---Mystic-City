/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Nov 14, 2018
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
