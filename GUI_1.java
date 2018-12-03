/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import javax.swing.JFrame;


// GUI_1
public class GUI_1 extends JFrame implements UserInterface {
    // constructor
    protected GUI_1() {
        this.setTitle("Shyam's GUI : MAGICAL GIRL FORCE GO!!!");
        this.setSize(720, 512);
        this.setLocation(256, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // display
    public void display(String message) {

    }


    // get line
    public String getLine() {
        return null;
    }
}//end GUI_1 class
