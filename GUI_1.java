/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// graphics user interface #1 that inherits JFrame
public class GUI_1 extends JFrame implements UserInterface {
    // private attributes
    private String line;
    private JTextArea textBox;

    // lock
    public static final Object syncLock;

    static { syncLock = new Object(); }


    // constructor for GUI_1 class
    public GUI_1() {
        this.setTitle("MAGICAL GIRL FORCE GO!!! (GUI #1)");
        this.setSize(2100, 1200);
        this.getContentPane().setLayout(null);

        textBox = new JTextArea();
        textBox.setBounds(1010,0,970,1200);
        JScrollPane scrollPane = new JScrollPane(textBox);
        textBox.setEditable(false); 
        Font font = new Font("Courier New", Font.BOLD, 20);
        textBox.setFont(font);
        textBox.setForeground(Color.BLUE);
        
        
        JButton buttonN = new JButton("GO N!");
        buttonN.setBounds(0,0,200,200);
        buttonN.setOpaque(false);
        buttonN.setContentAreaFilled(false);
        buttonN.setBorderPainted(false);
        buttonN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line = "GO N";
                synchronized (syncLock) { syncLock.notifyAll(); }
            }
        });
        
        buttonN.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    buttonN.setOpaque(true);
                    buttonN.setContentAreaFilled(true);
                    buttonN.setBorderPainted(true);
                    buttonN.setBackground(Color.GREEN);
                } else {
                    buttonN.setOpaque(false);
                    buttonN.setContentAreaFilled(false);
                    buttonN.setBorderPainted(false);

                }
            }
        });
        
        JButton buttonSD = new JButton("Self-Destruct");
        buttonSD.setBounds(160,430,200,200);
        buttonSD.setOpaque(false);
        buttonSD.setContentAreaFilled(false);
        buttonSD.setBorderPainted(false);
        //JPanel  panel  = new JPanel();
        
        try {
            BufferedImage img = ImageIO.read(new File("TEST.png"));
            this.setContentPane(new JLabel(new ImageIcon(img)));
            this.setBackground(new Color(54, 57, 63));
        } catch (Exception e) { }

        this.add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(textBox);
        this.getContentPane().add(buttonN); 
        this.getContentPane().add(buttonSD);
        //this.getContentPane().add(panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//end class constructor


    // display
    public void display(String message) {
        textBox.append(message);
        //this.getContentPane().getComponent(0);
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
