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


//
public class IO {
    // private attributes
    private static IO ourIO;
    private static final int TEXT;
    private static final int GUI_1;
    private static final int GUI_2;
    private static final int GUI_3;
    private UserInterface ui;


    // initialize static attribute
    static {
        ourIO = new IO();
        TEXT  = 0;
        GUI_1 = 1;
        GUI_2 = 2;
        GUI_3 = 3;
    }


    // return IO
    public static IO getIO() { return ourIO; }


    // constructor
    private IO() {

    }


    //

}//end IO class
