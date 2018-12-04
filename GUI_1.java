/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import java.util.ArrayList;
import java.util.List;

// graphics user interface #1 that inherits JFrame
public class GUI_1 extends JFrame implements UserInterface {
    // private attributes
    private String line;
    private final TreeMap<String, JTextArea> allTextBox;
    private JTextArea currTextBox;
    
    
    // lock
    public static final Object syncLock;

    static { syncLock = new Object(); }

    static final String cardName = "textbox";
    static final String but1Name = "but1";
    static final String but2Name = "but2";
    
    // constructor for GUI_1 class
    public GUI_1() { 
        /*this.setResizable(false);
        this.setName("MAGICAL GIRL FORCE GO!!! (GUI #1)");
        this.setSize(2500, 1500);
        JPanel cards = new JPanel();
        cards.setSize(2500, 1500);
        cards.setLayout(new CardLayout());
        
        JButton buttonSD = new JButton("Self-Destruct");
        buttonSD.setBounds(160,430,200,200);
        buttonSD.setOpaque(false);
        buttonSD.setContentAreaFilled(false);
        buttonSD.setBorderPainted(false);
        
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
        
        textBox = new JTextArea();
        textBox.setBackground(new Color(106, 103, 102));
        DefaultCaret caret = (DefaultCaret)textBox.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(textBox);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(1000,0,1000,1100);
        scrollPane.setBackground(new Color(106, 103, 102));
        textBox.setEditable(false); 
        Font font = new Font("Courier New", Font.BOLD, 20);
        textBox.setFont(font);
        textBox.setForeground(Color.WHITE); 

        JPanel text = new JPanel();
        JPanel but1 = new JPanel();
        JPanel but2 = new JPanel();
        //text.setSize(1000,1100);
        text.setLayout(null);
        but1.setLayout(null);
        but2.setLayout(null);
        text.add(scrollPane);
        but1.add(buttonN);
        but2.add(buttonSD);
        but1.setVisible(true);
        but2.setVisible(true);
        text.setVisible(true);
        cards.add(text,textName);
        cards.add(but1,but1Name);
        cards.add(but2,but2Name);
        cards.setVisible(true);
        cards.revalidate();
        cards.repaint();
        this.getContentPane().add(cards);
        this.setVisible(true);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
        
        allTextBox = new TreeMap<String, JTextArea>();
        Set<Map.Entry<Integer,Character>> allChars = Character.characterTree.entrySet();
        this.setResizable(false);
        this.setName("MAGICAL GIRL FORCE GO!!! (GUI #1)");
        this.setSize(2100, 1500);
        JPanel cards = new JPanel();
        cards.setSize(2100, 1500);
        cards.setLayout(new CardLayout()); 
        
        for(Map.Entry<Integer,Character> tuple: allChars)
        {
            if (tuple.getValue() instanceof Player)
            { 
                JTextArea textBox = new JTextArea();
                //textBox.setBounds(1010,0,960,1200);
                textBox.setBackground(new Color(106, 103, 102));
                textBox.setEditable(false); 
                Font font = new Font("Courier New", Font.BOLD, 20);
                textBox.setFont(font);
                textBox.setForeground(Color.WHITE);
                DefaultCaret caret = (DefaultCaret)textBox.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                JScrollPane scrollPane = new JScrollPane(textBox);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                scrollPane.setBounds(1000,0,1000,1000);
                scrollPane.setBackground(new Color(106, 103, 102));
                allTextBox.put(tuple.getValue().name, textBox);

                JLabel label1;
                //Create the first label.
                label1 = new JLabel(tuple.getValue().name);
                //Create tool tips, for the heck of it.
                label1.setToolTipText(tuple.getValue().description);
                label1.setBounds(0, 0, 500, 100);
                label1.setBackground(new Color(0,100,100));
                label1.setFont(new Font("Courier New", Font.BOLD, 50));
                
                
                JButton buttonN = new JButton();
                buttonN.setBounds(0,1000,200,200);
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
                            buttonN.setText("North");
                            buttonN.setOpaque(true);
                            buttonN.setContentAreaFilled(true);
                            buttonN.setBorderPainted(true);
                            buttonN.setBackground(Color.GREEN);
                        } else {
                            buttonN.setOpaque(false);
                            buttonN.setContentAreaFilled(false);
                            buttonN.setBorderPainted(false);
                            buttonN.setText("");
                        }
                    }
                });
                
                JButton buttonSD = new JButton("Self-Destruct");
                buttonSD.setBounds(160,430,200,200);
                buttonSD.setOpaque(false);
                buttonSD.setContentAreaFilled(false);
                buttonSD.setBorderPainted(false);
                
    //            try {
    //                BufferedImage img = ImageIO.read(new File("TEST.png"));
    //                this.setContentPane(new JLabel(new ImageIcon(img)));
    //                this.setBackground(new Color(54, 57, 63));
    //            } catch (Exception e) { }
              JPanel card = new JPanel();
              //JPanel but1 = new JPanel();
              //JPanel but2 = new JPanel();
              //text.setSize(1000,1100);
              card.setLayout(null);
              //but1.setLayout(null);
              //but2.setLayout(null);
              //card.add(textBox);
              card.add(scrollPane);
              card.add(buttonN);
              card.add(buttonSD);
              card.add(label1);
              //card.setVisible(true);
              //but1.setVisible(true);
              //but2.setVisible(true);
              System.out.println("nnnn" + tuple.getValue().name);
              cards.add(card,tuple.getValue().name);
              //cards.setVisible(true);
            }
        }
        this.getContentPane().add(cards);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        currTextBox = allTextBox.firstEntry().getValue();
       
    }//end class constructor


    // display
    public void display(String message) {
        currTextBox.append(message);
        //this.getContentPane().getComponent(0);
    }
    
    public void switchCard(String name)
    {
        JPanel cards = (JPanel)(this.getContentPane().getComponent(0));
        CardLayout cl = (CardLayout)cards.getLayout();
        //CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
        //System.out.println("aaaaaaaaaaaaaaaaaaa" + name);
        cl.show(cards, name);
        currTextBox = allTextBox.get(name);
        cards.revalidate();
        cards.repaint();
        this.revalidate();
        this.repaint();
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
