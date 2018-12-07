/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 * Date:   Dec 6, 2018
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import java.util.ArrayList;
import java.util.List;

// graphics user interface #1 that inherits JFrame
public class GUI_1 extends JFrame implements UserInterface {
    // private attributes
    private String line;
    private Player currPlayer;
    private final TreeMap<String, JTextArea> allTextBox;
    private JTextArea currTextBox; 
    private JLabel backGround;
    ImageIcon ribIcon;
    //private JPanel bgPanel;
    
    public enum bgType
    {
        def ("default.jpg"),
        DZ  ("DZ.png"),
        SZ  ("SZ.jpg"),
        mkt ("market.png");
        
        private JLabel bg;

        private bgType(String name)
        {
            JLabel tmp = null;
            try {
              tmp = new JLabel( new ImageIcon(javax.imageio.ImageIO.read(new File(name)).getScaledInstance(2000, 1500, Image.SCALE_SMOOTH)));
          } catch (IOException e) { System.out.println("IIIIIImage not Found"); }       
            bg = tmp;
            if(bg == null)
                System.out.println("Background Enum is NULL");
        }
        public JLabel getBG()
        {
            return bg;
        }
    }
       
    // lock
    public static final Object syncLock;
    static { syncLock = new Object(); }
    
    // constructor for GUI_1 class
    public GUI_1() { 
        
        allTextBox = new TreeMap<String, JTextArea>();
        Set<Map.Entry<Integer,Character>> allChars = Character.characterTree.entrySet();
        this.setResizable(false);
        this.setName("MAGICAL GIRL FORCE GO!!! (GUI #1)");
        this.setSize(1995, 1400);
        
        JPanel cards = new JPanel();
        cards.setOpaque(false);
        cards.setSize(2000, 1500);
        cards.setLayout(new CardLayout());
        ImageIcon statIcon = null;
        try{
            statIcon = new ImageIcon(javax.imageio.ImageIO.read(new File("statsIcon.png")).getScaledInstance(1000, 90, Image.SCALE_SMOOTH));
        } catch(Exception e){System.out.println("Image not found");}
        
        ribIcon = null;
        try{
            ribIcon = new ImageIcon(javax.imageio.ImageIO.read(new File("ribbon.png")).getScaledInstance(800, 200, Image.SCALE_SMOOTH));
        } catch(Exception e){System.out.println("Image not found");}
        
        for(Map.Entry<Integer,Character> tuple: allChars)
        {
            if (tuple.getValue() instanceof Player)
            { 
                currPlayer = (Player) tuple.getValue();
                Font fontCN = new Font("Courier New", Font.BOLD, 20);
                
                JPanel card = new JPanel();
                card.setOpaque(false);
                card.setLayout(null);
                
                JLabel playerLabel = new JLabel(){
                    @Override public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawString(currPlayer.name, (getWidth()/2) - (currPlayer.name.length()*15),60);
                    }
                };
                playerLabel.setBounds(0, 0, 1000, 100);
                playerLabel.setOpaque(true);
                playerLabel.setForeground(Color.WHITE);
                playerLabel.setBorder(BorderFactory.createMatteBorder(15, 15, 0, 15, new Color(50,50,50)));
                playerLabel.setIcon(ribIcon);
                playerLabel.setFont(new Font("Courier New", Font.BOLD, 50));
                playerLabel.setToolTipText(tuple.getValue().description);
                playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                playerLabel.setVerticalAlignment(SwingConstants.CENTER);
                
                JLabel stats = new JLabel()  {
                    @Override public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawString(currPlayer.getStats(), 15, 85);
                        ;
                    }
                };
                stats.setIcon(statIcon);
                stats.setBounds(0, 100, 1000, 100);
                stats.setFont(new Font("Courier New", Font.BOLD, 30));
                stats.setIconTextGap(0);
                stats.setForeground(Color.BLACK);
                stats.setOpaque(true);
                stats.setBackground(Color.WHITE);
                stats.setBorder(BorderFactory.createMatteBorder(0, 15, 15, 15, new Color(50,50,50)));
                
                JTextArea textBox = new JTextArea();
                textBox.setBackground(new Color(106, 103, 102));
                textBox.setEditable(false); 
                textBox.setFont(fontCN);
                //textBox.setBorder();
                textBox.setForeground(Color.WHITE);
                DefaultCaret caret = (DefaultCaret)textBox.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                JScrollPane scrollPane = new JScrollPane(textBox);
                scrollPane.setBounds(1000,0,990,1100);
                scrollPane.setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, new Color(50,50,50)));
                scrollPane.setBackground(new Color(106, 103, 102));
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                allTextBox.put(tuple.getValue().name, textBox);
                
                JTextField outputBox = new JTextField("Enter command");
                outputBox.setBounds(1000,1100,990,100);
                outputBox.setBackground(new Color(106, 103, 102)); 
                outputBox.setFont(fontCN);
                outputBox.setForeground(Color.WHITE);
                outputBox.setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, new Color(50,50,50)));
                outputBox.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        line = outputBox.getText();
                        outputBox.setText("");
                        synchronized(syncLock) { syncLock.notifyAll(); }
                    }
                });
                outputBox.addFocusListener(new FocusListener() {
                    @Override public void focusGained(FocusEvent e) {
                        outputBox.setText("");
                        outputBox.setForeground(Color.WHITE);
                    }
                    @Override public void focusLost(FocusEvent e) {
                        outputBox.setText("Enter command");
                        outputBox.setForeground(Color.WHITE);
                    }
                });
                 
                List<JButton> buttonNorths =  new ArrayList<JButton>();
                buttonNorths.add(createChildButton("North-NorthEast","GO NNE",125,600,buttonNorths ));
                buttonNorths.add(createChildButton("NorthEast","GO NE",275,600,buttonNorths  ));
                buttonNorths.add(createChildButton("North","GO N",425,600,buttonNorths ));
                buttonNorths.add(createChildButton("NorthWest","GO NW",575,600,buttonNorths ));
                buttonNorths.add(createChildButton("North-NorthWest","GO NNW",725,600,buttonNorths ));
                JLabel buttonNLabel = createButtonLabel("North",buttonNorths,450,700);
//              buttonN.getModel().addChangeListener(new ChangeListener() {
//              @Override
//              public void stateChanged(ChangeEvent e) {
//                  ButtonModel model = (ButtonModel) e.getSource();
//                  if (model.isRollover()) {
//                      buttonNNE.setVisible(true);
//                  } else {}
//              }});
                
                List<JButton> buttonSouths =  new ArrayList<JButton>(5);
                for(int i = 0; i < 5; ++i)
                    buttonSouths.add(new JButton());
                buttonSouths.add(createChildButton("South-SouthEast", "GO SSE",125,1000,buttonSouths));
                buttonSouths.add(createChildButton("SouthEast", "GO SE",275,1000,buttonSouths));
                buttonSouths.add(createChildButton("South", "GO S",425,1000,buttonSouths));
                buttonSouths.add(createChildButton("South-West", "GO SW",575,1000,buttonSouths));
                buttonSouths.add(createChildButton("South-SouthWest", "GO SSW",725,1000,buttonSouths));
                JLabel buttonSLabel = createButtonLabel("South",buttonSouths,450,900);
                
                List<JButton> buttonEasts =  new ArrayList<JButton>(3);
                for(int i = 0; i < 3; ++i)
                    buttonEasts.add(new JButton());
                buttonEasts.add(createChildButton("East-SouthEast", "GO ESE",650,700,buttonEasts));
                buttonEasts.add(createChildButton("East", "GO E",650,800,buttonEasts));
                buttonEasts.add(createChildButton("East-SouthWest", "GO ESW",650,900,buttonEasts));
                JLabel buttonELabel = createButtonLabel("East",buttonEasts,550,800);
                
                List<JButton> buttonWests =  new ArrayList<JButton>(3);
                for(int i = 0; i < 3; ++i)
                    buttonWests.add(new JButton());
                buttonWests.add(createChildButton("West-SouthEast", "GO WSE",200,700,buttonWests));
                buttonWests.add(createChildButton("West", "GO W",200,800,buttonWests));
                buttonWests.add(createChildButton("West-SouthWest", "GO WSW",200,900,buttonWests));
                JLabel buttonWLabel = createButtonLabel("West",buttonWests,350,800);
                
                JButton upButton = new JButton("UP");
                upButton.setBounds(450,800,100,50);
                upButton.setOpaque(true);
                upButton.setContentAreaFilled(true);
                upButton.setBackground(Color.WHITE);
                upButton.setBorderPainted(true);
                upButton.setVisible(true);
                upButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        line = "GO UP";
                        synchronized (syncLock) { syncLock.notifyAll(); }
                    }
                });
                
                JButton downButton = new JButton("DOWN");
                downButton.setBounds(450,850,100,50);
                downButton.setOpaque(true);
                downButton.setContentAreaFilled(true);
                downButton.setBackground(Color.WHITE);
                downButton.setVisible(true);
                downButton.setBorderPainted(true);
                downButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        line = "GO DOWN";
                        synchronized (syncLock) { syncLock.notifyAll(); }
                    }
                });
                
                JButton inveButton = new JButton("Inventory");
                inveButton.setBounds(450,500,100,100);
                inveButton.setOpaque(true);
                inveButton.setContentAreaFilled(true);
                inveButton.setBackground(Color.WHITE);
                inveButton.setVisible(true);
                inveButton.setBorderPainted(true);
                inveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        line = "INVENTORY";
                        synchronized (syncLock) { syncLock.notifyAll(); }
                    }
                });
                
                JButton inspButton = new JButton("Inspect");
                inspButton.setBounds(450,500,100,100);
                inspButton.setOpaque(true);
                inspButton.setContentAreaFilled(true);
                inspButton.setBackground(Color.WHITE);
                inspButton.setVisible(true);
                inspButton.setBorderPainted(true);
                inspButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        line = "INSPECT";
                        synchronized (syncLock) { syncLock.notifyAll(); }
                    }
                });
                
                card.add(inspButton);
                card.add(inveButton);
                card.add(upButton);
                card.add(downButton);
                card.add(outputBox);
                
                for(JButton but: buttonNorths)
                    card.add(but);
                card.add(buttonNLabel);
                
                for(JButton but: buttonSouths)
                    card.add(but);
                card.add(buttonSLabel);
                
                for(JButton but: buttonEasts)
                    card.add(but);
                card.add(buttonELabel);
                
                for(JButton but: buttonWests)
                    card.add(but);
                card.add(buttonWLabel);
                
                card.add(scrollPane);
                card.add(playerLabel);
                card.add(stats);
                cards.add(card,tuple.getValue().name);
            }
        }
        this.getContentPane().add(cards);
        
/***************FINICKY BACKGROUND CODE**************************************************************************************************************/        
/*      try {
      backgroundImage = javax.imageio.ImageIO.read(new File("default.jpg").getScaledInstance(1200, 800, Image.SCALE_SMOOTH););
      bgPanel = new JPanel(new BorderLayout())  {
          @Override public void paintComponent(Graphics g) {
              g.drawImage(backgroundImage, 0, 0, null);
          }
      } ;
      setContentPane(bg);
  } catch (IOException e) {
      throw new RuntimeException(e);
  }*/

//  try{
//  this.setIconImage(ImageIO.read(new File("default.jpg")));
//  this.getContentPane().setLayout( new BorderLayout() );
//  this.setContentPane( contentPane );
//  this.setIconImage(Toolkit.getDefaultToolkit().getImage("default.jpg"));
//  }
//  catch (Exception e){         
//  }
//  setContentPane(bg);
  
        try {
            backGround = new JLabel();
            //this.add(backGround);
            backGround = bgType.def.getBG();
            backGround.setOpaque(true);  
            this.add(backGround);
        } 
        catch (Exception e) {System.out.println("JPaaaaaaaaaaaaaanel: Image not Found"); e.printStackTrace();}
/************************************************************************************************************************/ 

        this.validate();
        this.repaint();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        currTextBox = allTextBox.firstEntry().getValue();
    }//end class constructor

    public void switchCard(Player p, Place place)
    {
        currPlayer = p; 
        JPanel cards = (JPanel)(this.getContentPane().getComponent(0));        
        CardLayout cl = (CardLayout)cards.getLayout();
        currTextBox = allTextBox.get(currPlayer.name);
        cl.show(cards, currPlayer.name); 
        changeBG(place);
//        cards.revalidate();
//        cards.repaint();
//        this.validate();
//        this.repaint();
    }
    
    private void changeBG(Place place)
    {
        this.remove(backGround);
        try{
            if(place instanceof Market)
            {
//              img = ImageIO.read(new File("market.png")); 
//              panel.setContentPane(new JLabel(new ImageIcon(img)));
//              panel.setBackground(new Color(150,150,150));
//              backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
                backGround = bgType.mkt.getBG();
            }
            else if(place instanceof DangerZone)
            {
//              img = ImageIO.read(new File("market.png")); 
//              panel.setContentPane(new JLabel(new ImageIcon(img)));
//              panel.setBackground(new Color(150,150,150));
//              backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
                backGround = bgType.DZ.getBG();
            }
            else if(place instanceof SafeZone)
            {
//              img = ImageIO.read(new File("market.png")); 
//              panel.setContentPane(new JLabel(new ImageIcon(img)));
//              panel.setBackground(new Color(150,150,150));
//              backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
                backGround = bgType.SZ.getBG();
            }
            else 
            {
//              img = ImageIO.read(new File("market.png")); 
//              panel.setContentPane(new JLabel(new ImageIcon(img)));
//              panel.setBackground(new Color(150,150,150));
//              backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
                backGround = bgType.def.getBG();
            } 
        }
        catch (Exception e){System.out.println("Background Panel error");}
        this.add(backGround);
    }
    
    private JButton createChildButton(String name,String command, int x , int y,List<JButton> list  )
    {
        JButton childButton = new JButton(name);
        childButton.setBounds(x,y,150,100);
        childButton.setOpaque(true);
        childButton.setContentAreaFilled(true);
        childButton.setBackground(Color.WHITE);
        childButton.setBorderPainted(true);
        childButton.setVisible(false);
        //button.setFont(new Font("Courier New", Font.BOLD, 14));
        childButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line = command;
                synchronized (syncLock) { syncLock.notifyAll(); }
            }
        });
        childButton.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    for(JButton but: list)
                    {
                        but.setVisible(true);
                    }
                } else {
                    for(JButton but: list)
                    {
                        but.setVisible(false);
                    }
                }
            }
        });
        return childButton;
        
    }
    private JLabel createButtonLabel(String name,List<JButton> list,int x, int y)
    {
        JLabel parentLabel= new JLabel();
        parentLabel.setBounds(x,y,100,100);
        parentLabel.setOpaque(true);
        parentLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        parentLabel.setBackground(Color.WHITE);
        parentLabel.setText(name);
        parentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        parentLabel.setVerticalAlignment(SwingConstants.CENTER);
        parentLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                for(JButton but: list)
                  but.setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                for(JButton but: list)
                    but.setVisible(false);
            }
        });
        return parentLabel;
    }

    // display
    public void display(String message) {
        currTextBox.append(message);
        //this.getContentPane().getComponent(0);
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
