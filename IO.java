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


// IO class
public class IO {
    // private attributes
    private static final int TEXT  = 0;
    private static final int GUI_1 = 1;
    private static final int GUI_2 = 2;
    private static final int GUI_3 = 3;
    private static IO ourIO;
    private UserInterface ui;

    // constructor
    private IO()                               { selectInterface(GUI_1); }
    // display
    public void display(String message)        { ui.display(message);    }
    // get line
    public String getLine()                    { return ui.getLine();    }
    // switch card
    public void switchCard(Player pl, Place p) { ui.switchCard(pl, p);   }


    // return IO instance
    public static IO getIO() {
        if (ourIO == null) ourIO = new IO();

        return ourIO;
    }//end getIO()


    // select interface
    public void selectInterface(int i) {
        if      (i == TEXT ) ui = new TextInterface();
        else if (i == GUI_1) ui = new GUI_1();
        else if (i == GUI_2) ui = new GUI_2();
        else if (i == GUI_3) ui = new GUI_1();
        else                 ui = new GUI_2();
    }
}//end IO class
