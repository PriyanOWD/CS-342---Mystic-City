/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// graphics user interface #1 that inherits JFrame
public class GUI_1 extends JFrame implements UserInterface {
    // private attributes
    private String line;


    // lock
    public static final Object syncLock;

    static { syncLock = new Object(); }


    // constructor for GUI_1 class
    public GUI_1() {
        this.setTitle("MAGICAL GIRL FORCE GO!!! (GUI #1)");
        this.setSize(720, 512);

        JButton button = new JButton("GO N!");
        JPanel  panel  = new JPanel();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line = "GO N";
                synchronized (syncLock) { syncLock.notifyAll(); }
            }
        });

        panel.add(button);
        this.getContentPane().add(panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//end class constructor


    // display
    public void display(String message) {
        //
    }


//    private void testButtonClick() {
//        testButton.addActionListener(e -> {
//            synchronized (lock) {
//                lock.notifyAll();
//            }
//        });
//    }


    // get line
    public String getLine() {
        try {
            synchronized (syncLock) {
                syncLock.wait();
                return line;
            }
        } catch (Exception e) { e.printStackTrace(); }       // exception

        return null;
    }
}//end GUI_1 class
