/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    //private JPanel bg;
    BufferedImage backgroundImage;
    ImageIcon bgIcon;
    JLabel bg;
    
    /*private enum bgType
    {
        def ("default",   new BufferedImage   ),
        DZ  ("DZ",           "N"   ),
        SZ  ("SZ",           "S"   ),
        mkt ("market",            "E"   );

        private final String text;
        private final String abbreviation;

        bgType(String text, BufferedImage img)
        {
            this.text = text;
            this.abbreviation = abbreviation;
        }

        public String toString()
        {
            return text;
        }
        public String toAbbreviation()
        {
            return abbreviation;
        }

        public boolean match(String str)
        {
            str = str.toLowerCase();
            return (str.equals(text.toLowerCase()) || str.equals(abbreviation.toLowerCase()));
        }
    }*/
    
    
    // lock
    public static final Object syncLock;

    static { syncLock = new Object(); }

    static final String cardName = "textbox";
    static final String but1Name = "but1";
    static final String but2Name = "but2";
    
    // constructor for GUI_1 class
    public GUI_1() { 
//        try {
//            backgroundImage = javax.imageio.ImageIO.read(new File("default.jpg"));
//            bg = new JPanel(new BorderLayout())  {
//                @Override public void paintComponent(Graphics g) {
//                    g.drawImage(backgroundImage, 0, 0, null);
//                }
//            } ;
//            setContentPane(bg);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        //try{
        //this.setIconImage(ImageIO.read(new File("default.jpg")));
        //this.getContentPane().setLayout( new BorderLayout() );
        //this.setContentPane( contentPane );
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage("default.jpg"));
       // }
       // catch (Exception e){         
        //}
        //setContentPane(bg);
        
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
                
                
                JLabel stats = new JLabel()  {
                    Player p = (Player)tuple.getValue();
                    @Override public void paintComponent(Graphics g) {
                        //setText(p.getStats());
                        g.drawString(p.getStats(), 20, 20);
                    }
                } ;
                
                stats.setBounds(600, 1300, 1000, 200);
                stats.setBackground(new Color(0,100,100));
                stats.setFont(new Font("Courier New", Font.BOLD, 20));
                
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
                
                  JPanel card = new JPanel();
                  card.setOpaque(false);
                  card.setLayout(null);
               
                  card.add(scrollPane);
                  card.add(buttonN);
                  card.add(buttonSD);
                  card.add(label1);
                  card.add(stats);
                  cards.add(card,tuple.getValue().name);
            }
        }
        
        this.getContentPane().add(cards);
        try {
            backgroundImage = javax.imageio.ImageIO.read(new File("default.jpg"));
            bgIcon = new ImageIcon(backgroundImage);
            bg =  new JLabel(bgIcon);
            bg.setOpaque(true);
            this.add(bg);
            cards.setOpaque(false);     
        } 
        catch (IOException e) {throw new RuntimeException(e);}
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        currTextBox = allTextBox.firstEntry().getValue();
       
    }//end class constructor


    // display
    public void display(String message) {
        currTextBox.append(message);
        //this.getContentPane().getComponent(0);
    }
    
    public void switchCard(String name, Place place)
    {
        JPanel cards = (JPanel)(this.getContentPane().getComponent(0));        
        CardLayout cl = (CardLayout)cards.getLayout();
        //CardLayout cl = (CardLayout)(this.getContentPane().getLayout());
        //System.out.println("aaaaaaaaaaaaaaaaaaa" + name);
        cl.show(cards, name);
        for(Component c: cards.getComponents())
        currTextBox = allTextBox.get(name);
        changeBG(place);
        cards.revalidate();
        cards.repaint();
        this.revalidate();
        this.repaint();
    }
    
    private void changeBG( Place place)
    {
        BufferedImage img;
        if(place instanceof Market)
        {
            
            try { //img = ImageIO.read(new File("market.png")); 
                  //panel.setContentPane(new JLabel(new ImageIcon(img)));
                 // panel.setBackground(new Color(150,150,150));
                backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
            }
            catch (Exception e) { } 
        }
        else if(place instanceof DangerZone)
        {
            try { 
                backgroundImage  = javax.imageio.ImageIO.read(new File("DZ.png"));
                bgIcon = new ImageIcon(backgroundImage);
                bg.setIcon(bgIcon);
            }
            catch (Exception e) { } 
        }
        else if(place instanceof SafeZone)
        {
            try { backgroundImage  = javax.imageio.ImageIO.read(new File("SZ.png"));
                  bgIcon = new ImageIcon(backgroundImage);
                  bg.setIcon(bgIcon);
            }
            catch (Exception e) { } 
        }
        else
        {
            try { //img = ImageIO.read(new File("default.jpg")); 
                  //this.setContentPane(new JLabel(new ImageIcon(img)));
                 // panel.setBackground(new Color(54, 57, 63));
                backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
            }
            catch (Exception e) { System.out.println("Image not found");} 
        } 
        this.revalidate();
        this.repaint();
        
    }

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
