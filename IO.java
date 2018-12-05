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
    private static final int TEXT;
    private static final int GUI_1;
    private static final int GUI_2;
    private static final int GUI_3;
    private static IO ourIO;
    private UserInterface ui;


    // initialize static attribute
    static {
        TEXT  = 0;
        GUI_1 = 1;
        GUI_2 = 2;
        GUI_3 = 3;
    }


    // return IO
    public static IO getIO() {
        if(ourIO == null)
            ourIO = new IO();
        return ourIO; 
    }


    // constructor
    private IO() {
        selectInterface(0);
    }


    //
    public void display(String message) {
        ui.display(message);
    }


    //
    public String getLine() {
        return ui.getLine();
    }

    public void switchCard(String name,Place place)
    {
       //((GUI_1)ui).switchCard(name);
        ui.switchCard(name,place);
    }

    //
    public void selectInterface(int i) {
        if      (i == TEXT ) ui = new GUI_1();
        else if (i == GUI_1) ui = new GUI_1();
        else if (i == GUI_2) ui = new GUI_1();
        else if (i == GUI_3) ui = new GUI_1();
        else                 ui = new GUI_1();
    }
}//end IO class
