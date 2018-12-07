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
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
    private Place currPlace;
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
        death ("death.png"),
        win ("win.png"),
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
        
        ImageIcon statIcon = null;
        try{
            statIcon = new ImageIcon(javax.imageio.ImageIO.read(new File("statsIcon.png")).getScaledInstance(1000, 90, Image.SCALE_SMOOTH));
        } catch(Exception e){System.out.println("Image not found");}
        
        ribIcon = null;
        try{
            ribIcon = new ImageIcon(javax.imageio.ImageIO.read(new File("ribbon.png")).getScaledInstance(800, 200, Image.SCALE_SMOOTH));
        } catch(Exception e){System.out.println("Image not found");}
        
        allTextBox = new TreeMap<String, JTextArea>();
        Set<Map.Entry<Integer,Character>> allChars = Character.characterTree.entrySet();
        this.setResizable(false);
        this.setName("MAGICAL GIRL FORCE GO!!! (GUI #1)");
        this.setSize(1995, 1400);
        
        JPanel cards = new JPanel();
        cards.setOpaque(false);
        cards.setSize(2000, 1500);
        cards.setLayout(new CardLayout());

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
                buttonNorths.add(createChildButton("NORTH-NORTHEAST","GO NNE",650,600,buttonNorths,Color.WHITE ));
                buttonNorths.add(createChildButton("NORTHEAST","GO NE",500,600,buttonNorths,Color.WHITE  ));
                buttonNorths.add(createChildButton("NORTHWEST","GO NW",350,600,buttonNorths,Color.WHITE ));
                buttonNorths.add(createChildButton("NORTH-NORTHWEST","GO NNW",200,600,buttonNorths,Color.WHITE ));
                JButton buttonN = createParentButton("NORTH","GO N",buttonNorths,450,700,Color.WHITE);

                
                List<JButton> buttonSouths =  new ArrayList<JButton>();
                buttonSouths.add(createChildButton("SOUTH-SOUTHEAST", "GO SSE",650,1000,buttonSouths,Color.WHITE));
                buttonSouths.add(createChildButton("SOUTHEAST", "GO SE",500,1000,buttonSouths,Color.WHITE));
                buttonSouths.add(createChildButton("SOUTH-WEST", "GO SW",350,1000,buttonSouths,Color.WHITE));
                buttonSouths.add(createChildButton("SOUTH-SOUTHWEST", "GO SSW",200,1000,buttonSouths,Color.WHITE));
                JButton buttonS = createParentButton("SOUTH","GO S",buttonSouths,450,900,Color.WHITE);
                
                List<JButton> buttonEasts =  new ArrayList<JButton>(3);
                for(int i = 0; i < 3; ++i)
                    buttonEasts.add(new JButton());
                buttonEasts.add(createChildButton("EAST-SOUTHEAST", "GO ESE",650,750,buttonEasts,Color.WHITE));
                buttonEasts.add(createChildButton("EAST-SOUTHWEST", "GO ENE",650,850,buttonEasts,Color.WHITE));
                JButton buttonE = createParentButton("EAST","GO E",buttonEasts,550,800,Color.WHITE);
                
                List<JButton> buttonWests =  new ArrayList<JButton>(3);
                for(int i = 0; i < 3; ++i)
                    buttonWests.add(new JButton());
                buttonWests.add(createChildButton("WEST-SOUTHWEST", "GO WSW",200,750,buttonWests,Color.WHITE));
                buttonWests.add(createChildButton("WEST-NORTHWEST", "GO WNW",200,850,buttonWests,Color.WHITE));
                JButton buttonW = createParentButton("WEST","GO W",buttonWests,350,800,Color.WHITE);
                
                JButton quitButton = new JButton("QUIT");
                quitButton.setBounds(1875,1225,100,100);
                quitButton.setOpaque(true);
                quitButton.setContentAreaFilled(true);
                quitButton.setBackground(Color.WHITE);
                quitButton.setBorderPainted(true);
                quitButton.setVisible(true);
                quitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        line = "QUIT";
                        synchronized (syncLock) { syncLock.notifyAll(); }
                    }
                });
                
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
                
                JButton inspButton = new JButton("INSPECT");
                inspButton.setBounds(450,1100,100,100);
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
                
                JButton lookButton = new JButton("LOOK");
                lookButton.setBounds(450,400,100,100);
                lookButton.setOpaque(true);
                lookButton.setContentAreaFilled(true);
                lookButton.setBackground(Color.WHITE);
                lookButton.setVisible(true);
                lookButton.setBorderPainted(true);
                lookButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        line = "LOOK";
                        synchronized (syncLock) { syncLock.notifyAll(); }
                    }
                });
                
                    JPopupMenu buyStuff = new JPopupMenu();
                    buyStuff.add(new JMenuItem(new AbstractAction("Guess 0") {
                        public void actionPerformed(ActionEvent e) {
                            line = "BUY 0";
                            synchronized (syncLock) { syncLock.notifyAll();}
                        }
                        }));
                    buyStuff.add(new JMenuItem(new AbstractAction("Guess 1") {
                        public void actionPerformed(ActionEvent e) {
                            line = "BUY 1";
                            synchronized (syncLock) { syncLock.notifyAll();}
                        }
                        }));
                JButton buyButton = new JButton("BUY");
                buyButton.setBounds(850,800,100,100);
                buyButton.setBackground(new Color(237,188,66));
                buyButton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        buyStuff.show(e.getComponent(), e.getX(), e.getY());
                    }
                });
                
                currPlace = currPlayer.currPlace;
                JButton useButton = createDDButton("USE","USE", false,false,50,500,Color.WHITE);
                JButton consButton = createDDButton("CONSUME","CONSUME", false,false,50,800,Color.WHITE);
                JButton getButton = createDDButton("GET","GET", true,false,850,500,Color.WHITE);
                JButton equipButton = createDDButton("EQUIP","EQUIP", false,false,50,1100,Color.WHITE);
                JButton dropButton = createDDButton("DROP","DROP", false,false,850,1100,Color.WHITE);
                JButton attackButton = createDDButton("ATTACK","ATTACK", false,true,450,1200,new Color(178,0,29));
                
                card.add(quitButton);
                card.add(buyButton);
                card.add(consButton);
                card.add(attackButton);
                card.add(lookButton);
                card.add(equipButton);
                card.add(dropButton);
                card.add(useButton);
                card.add(getButton);
                card.add(inspButton);
                card.add(inveButton);
                card.add(upButton);
                card.add(downButton);
                card.add(outputBox);
                
                for(JButton but: buttonNorths)
                    card.add(but);
                card.add(buttonN);
                
                for(JButton but: buttonSouths)
                    card.add(but);
                card.add(buttonS);
                
                for(JButton but: buttonEasts)
                    card.add(but);
                card.add(buttonE);
                
                for(JButton but: buttonWests)
                    card.add(but);
                card.add(buttonW);
                
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
        currPlace = place;
        JPanel cards = (JPanel)(this.getContentPane().getComponent(0));        
        CardLayout cl = (CardLayout)cards.getLayout();
        currTextBox = allTextBox.get(currPlayer.name);
        cl.show(cards, currPlayer.name); 
        changeBG();
//        cards.revalidate();
//        cards.repaint();
//        this.validate();
//        this.repaint();
    }
    
    private void changeBG()
    {
        this.remove(backGround);
        try{
            if(currPlayer.currHP <= 0)
            {
                backGround = bgType.death.getBG();
            }
            else if(Game.numActive < 2)
            {
                backGround = bgType.win.getBG();
            }
            else if(currPlace instanceof Market)
            {
//              img = ImageIO.read(new File("market.png")); 
//              panel.setContentPane(new JLabel(new ImageIcon(img)));
//              panel.setBackground(new Color(150,150,150));
//              backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
                backGround = bgType.mkt.getBG();
            }
            else if(currPlace instanceof DangerZone)
            {
//              img = ImageIO.read(new File("market.png")); 
//              panel.setContentPane(new JLabel(new ImageIcon(img)));
//              panel.setBackground(new Color(150,150,150));
//              backgroundImage  = javax.imageio.ImageIO.read(new File("default.jpg"));
                backGround = bgType.DZ.getBG();
            }
            else if(currPlace instanceof SafeZone)
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
    
    public void update()
    {
        //System.out.println("UPDATING GUI_1");
        JPanel cards = (JPanel)(this.getContentPane().getComponent(0)); 
        cards.validate();
        cards.repaint();
        this.validate();
        this.repaint();
    }
    
    private JButton createDDButton(String name,String command,boolean PlayorPlac ,boolean InveorChar, int x, int y,Color col)
    {
        JButton button = new JButton(name);
        button.setBounds(x,y,100,100);
        button.setBackground(col);
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JPopupMenu popup = new JPopupMenu();
                if(InveorChar)
                {
                    for(Character c: currPlace.placeCharacters)
                    {
                        if(c.isActive && !c.name.equals(currPlayer.name))
                        {
                             popup.add(new JMenuItem(new AbstractAction(c.name()) {
                             public void actionPerformed(ActionEvent e) {
                                 line = command + " "+ c.name();
                                 synchronized (syncLock) { syncLock.notifyAll();}
                             }
                             }));
                        }
                    }
                    popup.show(e.getComponent(), e.getX(), e.getY());
                    
                }
                else
                {
                    for(Artifact arti: (PlayorPlac)?currPlace.placeArtifacts:currPlayer.inventory)
                    {
                     popup.add(new JMenuItem(new AbstractAction(arti.name()) {
                         public void actionPerformed(ActionEvent e) {
                             line = command + " "+ arti.name();
                             synchronized (syncLock) { syncLock.notifyAll();}
                         }
                         }));
                    }
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        return button;
    }
    
    private JButton createChildButton(String name,String command, int x , int y,List<JButton> list,Color col )
    {
        JButton childButton = new JButton(name);
        childButton.setBounds(x,y,150,100);
        childButton.setOpaque(true);
        childButton.setContentAreaFilled(true);
        childButton.setBackground(col);
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
    private JButton createParentButton(String name,String command, List<JButton> list,int x, int y,Color col)
    {
        JButton parentButton= new JButton();
        parentButton.setBounds(x,y,100,100);
        parentButton.setOpaque(true);
        parentButton.setBackground(col);
        parentButton.setText(name);
        parentButton.setHorizontalAlignment(SwingConstants.CENTER);
        parentButton.setVerticalAlignment(SwingConstants.CENTER);
        parentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line = command;
                synchronized (syncLock) { syncLock.notifyAll(); }
            }
        });
        parentButton.addMouseListener(new MouseAdapter() {
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
        return parentButton;
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
