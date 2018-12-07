/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name: Joey Voorhees
 * netid: svoorh2
 * Date: Dec 6th, 2018
 */


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
public class GUI_3 extends JFrame implements UserInterface {
    private String    line;
    private Player    currPlayer;
    private JTextArea messageBox;

    private final TreeMap<String, JTextArea> messages;
    private final TreeMap<String, JButton>   btnN,   btnS,   btnE,
            btnW,   btnU,   btnD,
            btnNE,  btnNW,  btnSE,
            btnSW,  btnNNE, btnNNW,
            btnENE, btnWNW, btnESE,
            btnWSW, btnSSE, btnSSW;
    public static final Object syncLock = new Object();
//constructor
    public GUI_3(){
        Set<Map.Entry<Integer,Character>> characters
                = Character.characterTree.entrySet();
        messages = new TreeMap<String, JTextArea>();
        btnN = new TreeMap<String, JButton>();
        btnS = new TreeMap<String, JButton>();
        btnE = new TreeMap<String, JButton>();
        btnW = new TreeMap<String, JButton>();
        btnU = new TreeMap<String, JButton>();
        btnD = new TreeMap<String, JButton>();
        btnNE = new TreeMap<String, JButton>();
        btnNW = new TreeMap<String, JButton>();
        btnSE = new TreeMap<String, JButton>();
        btnSW = new TreeMap<String, JButton>();
        btnNNE = new TreeMap<String, JButton>();
        btnNNW = new TreeMap<String, JButton>();
        btnENE = new TreeMap<String, JButton>();
        btnWNW = new TreeMap<String, JButton>();
        btnESE = new TreeMap<String, JButton>();
        btnWSW = new TreeMap<String, JButton>();
        btnSSE = new TreeMap<String, JButton>();
        btnSSW = new TreeMap<String, JButton>();

        try{
                this.setTitle("MAGICAL GIRL FORCE GO!!! (GUI #3)");
                this.setSize(1200, 800);
                this.setResizable(false);
                this.setBackground(new Color(147, 117, 196));

                JPanel cards = new JPanel();
                cards.setSize(1200, 800);
                cards.setLayout(new CardLayout());
                cards.setOpaque(true);
                cards.setBackground(Color.black);

                for (Map.Entry<Integer,Character> c : characters) {
                    if (c.getValue() instanceof Player) {
                        currPlayer = (Player) c.getValue();
//label for player name
                        JLabel playerName;
                        playerName = new JLabel(currPlayer.name);
                        playerName.setToolTipText(currPlayer.description);
                        playerName.setBounds(875, 18, 314, 40);
                        playerName.setForeground(Color.WHITE);
                        //playerName.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        playerName.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
//displays attack
                        JLabel attack = new JLabel("Attack:");
                        attack.setBounds(875, 72, 80, 20);
                        attack.setForeground(new Color(220, 221, 222));
                        //attack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        attack.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                        attack.setHorizontalAlignment(SwingConstants.LEFT);

                        JLabel attackStat = new JLabel() {
                            @Override public void paintComponent(Graphics g) {
                                g.drawString(String.valueOf(currPlayer.attack), 4, 16);
                            }
                        };
                        attackStat.setBounds(955, 72, 64, 20);
                        attackStat.setForeground(new Color(220, 221, 222));
                        //attackStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        attackStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
//displays defense
                        JLabel defense = new JLabel("Defense:");
                        defense.setBounds(875, 92, 80, 20);
                        defense.setForeground(new Color(220, 221, 222));
                        //defense.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        defense.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                        defense.setHorizontalAlignment(SwingConstants.LEFT);

                        JLabel defenseStat = new JLabel() {
                            @Override public void paintComponent(Graphics g) {
                                g.drawString(String.valueOf(currPlayer.defense), 4, 16);
                            }
                        };
                        defenseStat.setBounds(955, 92, 64, 20);
                        defenseStat.setForeground(new Color(220, 221, 222));
                        //defenseStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        defenseStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
//displays HP
                        JLabel hp = new JLabel("HP:");
                        hp.setBounds(875, 112, 80, 20);
                        hp.setForeground(new Color(220, 221, 222));
                        //hp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        hp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                        hp.setHorizontalAlignment(SwingConstants.LEFT);

                        JLabel hpStat = new JLabel() {
                            @Override public void paintComponent(Graphics g) {
                                g.drawString(currPlayer.currHP + "/" + currPlayer.maxHP, 4, 16);
                            }
                        };
                        hpStat.setBounds(955, 112, 64, 20);
                        hpStat.setForeground(new Color(220, 221, 222));
                        //hpStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        hpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
//displays MP
                        JLabel mp = new JLabel("MP:");
                        mp.setBounds(875, 132, 80, 20);
                        mp.setForeground(new Color(220, 221, 222));
                        //mp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        mp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                        mp.setHorizontalAlignment(SwingConstants.LEFT);

                        JLabel mpStat = new JLabel() {
                            @Override public void paintComponent(Graphics g) {
                                g.drawString(String.valueOf(currPlayer.mp), 4, 16);
                            }
                        };
                        mpStat.setBounds(955, 132, 64, 20);
                        mpStat.setForeground(new Color(220, 221, 222));
                        //mpStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        mpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

//Game display
                        messageBox = new JTextArea();
                        messageBox.setBackground(new Color(47, 49, 54));
                        messageBox.setForeground(Color.WHITE);
                        messageBox.setFont(new Font("Bauhaus", Font.PLAIN, 15));
                        messageBox.setEditable(false);
                        messageBox.setMargin(new Insets(0, 40, 0, 0));
                        DefaultCaret caret = (DefaultCaret) messageBox.getCaret();
                        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                        JScrollPane scrollPane = new JScrollPane(messageBox);
                        scrollPane.setBounds(50, 22, 804, 659);
                        scrollPane.setBorder(BorderFactory.createEmptyBorder());
                        messages.put(currPlayer.name, messageBox);
//command text field
                        JTextField textBox = new JTextField("Please enter a command " + currPlayer.name);
                        textBox.setBounds(50, 700, 804, 50);
                        textBox.setBackground(new Color(72, 75, 81));
                        textBox.setForeground(new Color(127, 129, 133));
                        Border border =
                                BorderFactory.createLineBorder(Color.WHITE);
                        textBox.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder()));
                        textBox.setFont(new Font("Bauhaus", Font.PLAIN
                                , 28));
                        textBox.setBorder(BorderFactory.createEmptyBorder());
                        textBox.addFocusListener(new FocusListener() {
                            @Override public void focusGained(FocusEvent e) {
                                textBox.setText("");
                                textBox.setForeground(new Color(200, 201, 203));
                            }
                            @Override public void focusLost(FocusEvent e) {
                                textBox.setText("Please enter a command " + currPlayer.name);
                                textBox.setForeground(new Color(127, 129, 133));
                            }
                        });
                        textBox.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = textBox.getText();
                                textBox.setText("");
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });

//create directional buttons 
                        JButton btn_N = new JButton("N");
                        btn_N.setBounds(1000, 427, 40, 40);
                        btn_N.setFont(new Font("Helvetica Neue", Font.BOLD,
                                36));
                        btn_N.setForeground(Color.WHITE);
                        btn_N.setBorder(BorderFactory.createEmptyBorder());
                        btn_N.setOpaque(true);
                        btn_N.setContentAreaFilled(true);
                        btn_N.setBackground(Color.MAGENTA);
                        btn_N.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_N.setToolTipText("NORTH");
                        btn_N.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO N";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnN.put(currPlayer.name, btn_N);
//south button
                        JButton btn_S = new JButton("S");
                        btn_S.setBounds(1000, 626, 40, 40);
                        btn_S.setFont(new Font("Helvetica Neue", Font.BOLD,
                                36));
                        btn_S.setForeground(Color.WHITE);
                        btn_S.setBorder(BorderFactory.createEmptyBorder());
                        btn_S.setOpaque(true);
                        btn_S.setContentAreaFilled(true);
                        btn_S.setBackground(Color.MAGENTA);
                        btn_S.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_S.setToolTipText("SOUTH");
                        btn_S.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO S";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnS.put(currPlayer.name, btn_S);
//east button
                        JButton btn_E = new JButton("E");
                        btn_E.setBounds(1100, 525, 40, 40);
                        btn_E.setFont(new Font("Helvetica Neue", Font.BOLD,
                                36));
                        btn_E.setForeground(Color.WHITE);
                        btn_E.setBorder(BorderFactory.createEmptyBorder());
                        btn_E.setOpaque(true);
                        btn_E.setContentAreaFilled(true);
                        btn_E.setBackground(Color.MAGENTA);
                        btn_E.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_E.setToolTipText("EAST");
                        btn_E.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO E";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnE.put(currPlayer.name, btn_E);
//west button
                        JButton btn_W = new JButton("W");
                        btn_W.setBounds(900, 525, 40, 40);
                        btn_W.setFont(new Font("Helvetica Neue", Font.BOLD,
                                36));
                        btn_W.setForeground(Color.WHITE);
                        btn_W.setBorder(BorderFactory.createEmptyBorder());
                        btn_W.setOpaque(true);
                        btn_W.setContentAreaFilled(true);
                        btn_W.setBackground(Color.MAGENTA);
                        btn_W.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_W.setToolTipText("WEST");
                        btn_W.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO W";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnW.put(currPlayer.name, btn_W);
//up button
                        JButton btn_U = new JButton("U");
                        btn_U.setBounds(975, 525, 40, 40);
                        btn_U.setFont(new Font("Helvetica Neue", Font.BOLD,
                                36));
                        btn_U.setForeground(Color.WHITE);
                        btn_U.setBorder(BorderFactory.createEmptyBorder());
                        btn_U.setOpaque(true);
                        btn_U.setContentAreaFilled(true);
                        btn_U.setBackground(Color.blue);
                        btn_U.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_U.setToolTipText("UP");
                        btn_U.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO U";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnU.put(currPlayer.name, btn_U);
//down button
                        JButton btn_D = new JButton("D");
                        btn_D.setBounds(1025, 525, 40, 40);
                        btn_D.setFont(new Font("Helvetica Neue", Font.BOLD,
                                36));
                        btn_D.setForeground(Color.WHITE);
                        btn_D.setBorder(BorderFactory.createEmptyBorder());
                        btn_D.setOpaque(true);
                        btn_D.setContentAreaFilled(true);
                        btn_D.setBackground(Color.blue);
                        btn_D.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_D.setToolTipText("DOWN");
                        btn_D.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO D";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnD.put(currPlayer.name, btn_D);
//NE
                        JButton btn_NE = new JButton("NE");
                        btn_NE.setBounds(1100, 427, 40, 40);
                        btn_NE.setFont(new Font("Helvetica Neue", Font.BOLD,
                                24));
                        btn_NE.setForeground(Color.WHITE);
                        btn_NE.setBorder(BorderFactory.createEmptyBorder());
                        btn_NE.setOpaque(true);
                        btn_NE.setContentAreaFilled(true);
                        btn_NE.setBackground(Color.MAGENTA);
                        btn_NE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_NE.setToolTipText("NORTHEAST");
                        btn_NE.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO NE";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnNE.put(currPlayer.name, btn_NE);
//NW
                        JButton btn_NW = new JButton("NW");
                        btn_NW.setBounds(900, 427, 40, 40);
                        btn_NW.setFont(new Font("Helvetica Neue", Font.BOLD,
                                24));
                        btn_NW.setForeground(Color.WHITE);
                        btn_NW.setBorder(BorderFactory.createEmptyBorder());
                        btn_NW.setOpaque(true);
                        btn_NW.setContentAreaFilled(true);
                        btn_NW.setBackground(Color.MAGENTA);
                        btn_NW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_NW.setToolTipText("NORTHWEST");
                        btn_NW.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO NW";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnNW.put(currPlayer.name, btn_NW);
//SE
                        JButton btn_SE = new JButton("SE");
                        btn_SE.setBounds(1100, 626, 40, 40);
                        btn_SE.setFont(new Font("Helvetica Neue", Font.BOLD,
                                28));
                        btn_SE.setForeground(Color.WHITE);
                        btn_SE.setBorder(BorderFactory.createEmptyBorder());
                        btn_SE.setOpaque(true);
                        btn_SE.setContentAreaFilled(true);
                        btn_SE.setBackground(Color.MAGENTA);
                        btn_SE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_SE.setToolTipText("SOUTHEAST");
                        btn_SE.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO SE";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnSE.put(currPlayer.name, btn_SE);
//SW
                        JButton btn_SW = new JButton("SW");
                        btn_SW.setBounds(900, 626, 40, 40);
                        btn_SW.setFont(new Font("Helvetica Neue", Font.BOLD,
                                24));
                        btn_SW.setForeground(Color.WHITE);
                        btn_SW.setBorder(BorderFactory.createEmptyBorder());
                        btn_SW.setOpaque(true);
                        btn_SW.setContentAreaFilled(true);
                        btn_SW.setBackground(Color.MAGENTA);
                        btn_SW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_SW.setToolTipText("SOUTHWEST");
                        btn_SW.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO SW";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnSW.put(currPlayer.name, btn_SW);
//NNE
                        JButton btn_NNE = new JButton("NNE");
                        btn_NNE.setBounds(1050, 427, 40, 40);
                        btn_NNE.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_NNE.setForeground(Color.WHITE);
                        btn_NNE.setBorder(BorderFactory.createEmptyBorder());
                        btn_NNE.setOpaque(true);
                        btn_NNE.setContentAreaFilled(true);
                        btn_NNE.setBackground(Color.MAGENTA);
                        btn_NNE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_NNE.setToolTipText("NORTH-NORTHEAST");
                        btn_NNE.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO NNE";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnNNE.put(currPlayer.name, btn_NNE);
//NNW
                        JButton btn_NNW = new JButton("NNW");
                        btn_NNW.setBounds(950, 427, 40, 40);
                        btn_NNW.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_NNW.setForeground(Color.WHITE);
                        btn_NNW.setBorder(BorderFactory.createEmptyBorder());
                        btn_NNW.setOpaque(true);
                        btn_NNW.setContentAreaFilled(true);
                        btn_NNW.setBackground(Color.MAGENTA);
                        btn_NNW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_NNW.setToolTipText("NORTH-NORTHWEST");
                        btn_NNW.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO NNW";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnNNW.put(currPlayer.name, btn_NNW);
//ENE
                        JButton btn_ENE = new JButton("ENE");
                        btn_ENE.setBounds(1100, 476, 40, 40);
                        btn_ENE.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_ENE.setForeground(Color.WHITE);
                        btn_ENE.setBorder(BorderFactory.createEmptyBorder());
                        btn_ENE.setOpaque(true);
                        btn_ENE.setContentAreaFilled(true);
                        btn_ENE.setBackground(Color.MAGENTA);
                        btn_ENE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_ENE.setToolTipText("EAST-NORTHEAST");
                        btn_ENE.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO ENE";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnENE.put(currPlayer.name, btn_ENE);
//WNW
                        JButton btn_WNW = new JButton("WNW");
                        btn_WNW.setBounds(900, 476, 40, 40);
                        btn_WNW.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_WNW.setForeground(Color.WHITE);
                        btn_WNW.setBorder(BorderFactory.createEmptyBorder());
                        btn_WNW.setOpaque(true);
                        btn_WNW.setContentAreaFilled(true);
                        btn_WNW.setBackground(Color.MAGENTA);
                        btn_WNW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_WNW.setToolTipText("WEST-NORTHWEST");
                        btn_WNW.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO WNW";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnWNW.put(currPlayer.name, btn_WNW);
//ESE
                        JButton btn_ESE = new JButton("ESE");
                        btn_ESE.setBounds(1100, 576, 40, 40);
                        btn_ESE.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_ESE.setForeground(Color.WHITE);
                        btn_ESE.setBorder(BorderFactory.createEmptyBorder());
                        btn_ESE.setOpaque(true);
                        btn_ESE.setContentAreaFilled(true);
                        btn_ESE.setBackground(Color.MAGENTA);
                        btn_ESE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_ESE.setToolTipText("EAST-SOUTHEAST");
                        btn_ESE.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO ESE";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnESE.put(currPlayer.name, btn_ESE);
//WSW
                        JButton btn_WSW = new JButton("WSW");
                        btn_WSW.setBounds(900, 576, 40, 40);
                        btn_WSW.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_WSW.setForeground(Color.WHITE);
                        btn_WSW.setBorder(BorderFactory.createEmptyBorder());
                        btn_WSW.setOpaque(true);
                        btn_WSW.setContentAreaFilled(true);
                        btn_WSW.setBackground(Color.MAGENTA);
                        btn_WSW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_WSW.setToolTipText("WEST-SOUTHWEST");
                        btn_WSW.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO WSW";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnWSW.put(currPlayer.name, btn_WSW);
//SSE
                        JButton btn_SSE = new JButton("SSE");
                        btn_SSE.setBounds(1050, 626, 40, 40);
                        btn_SSE.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_SSE.setForeground(Color.WHITE);
                        btn_SSE.setBorder(BorderFactory.createEmptyBorder());
                        btn_SSE.setOpaque(true);
                        btn_SSE.setContentAreaFilled(true);
                        btn_SSE.setBackground(Color.MAGENTA);
                        btn_SSE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_SSE.setToolTipText("SOUTH-SOUTHEAST");
                        btn_SSE.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO SSE";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnSSE.put(currPlayer.name, btn_SSE);
//SSW
                        JButton btn_SSW = new JButton("SSW");
                        btn_SSW.setBounds(950, 626, 40, 40);
                        btn_SSW.setFont(new Font("Helvetica Neue", Font.BOLD,
                                16));
                        btn_SSW.setForeground(Color.WHITE);
                        btn_SSW.setBorder(BorderFactory.createEmptyBorder());
                        btn_SSW.setOpaque(true);
                        btn_SSW.setContentAreaFilled(true);
                        btn_SSW.setBackground(Color.MAGENTA);
                        btn_SSW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        btn_SSW.setToolTipText("SOUTH-SOUTHWEST");
                        btn_SSW.addActionListener(new ActionListener() {
                            @Override public void actionPerformed(ActionEvent e) {
                                line = "GO SSW";
                                synchronized(syncLock) { syncLock.notifyAll(); }
                            }
                        });
                        btnSSW.put(currPlayer.name, btn_SSW);
//adds everything to the window
                        JPanel card = new JPanel();
                        card.setLayout(null);
                        card.setOpaque(false);
                        card.add(scrollPane);
                        card.add(textBox);
                        card.add(playerName);
                        card.add(attack);
                        card.add(attackStat);
                        card.add(defense);
                        card.add(defenseStat);
                        card.add(hp);
                        card.add(hpStat);
                        card.add(mp);
                        card.add(mpStat);
                        card.add(btn_N);
                        card.add(btn_S);
                        card.add(btn_E);
                        card.add(btn_W);
                        card.add(btn_U);
                        card.add(btn_D);
                        card.add(btn_NE);
                        card.add(btn_NW);
                        card.add(btn_SE);
                        card.add(btn_SW);
                        card.add(btn_NNE);
                        card.add(btn_NNW);
                        card.add(btn_ENE);
                        card.add(btn_WNW);
                        card.add(btn_ESE);
                        card.add(btn_WSW);
                        card.add(btn_SSE);
                        card.add(btn_SSW);

                        cards.add(card, c.getValue().name);
                    }//end if...
                }//end for...

                this.getContentPane().add(cards);
                this.setVisible(true);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                messageBox = messages.firstEntry().getValue();
            }

            catch(Exception e){e.printStackTrace();}
    }

    @Override
    //displays the current game area to 
    //the player
    public void display(String message) {
        messageBox.append(message);
    }

    @Override
    
    public String getLine() {
        try {
            synchronized(syncLock) {
                syncLock.wait();
                return line;
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    @Override
    //switches the game mode between players
    public void switchCard(Player p, Place place) {
        JPanel cards    = (JPanel) this.getContentPane().getComponent(0);
        CardLayout card = (CardLayout) cards.getLayout();
        currPlayer      = p;
        messageBox      = messages.get(currPlayer.name);

        card.show(cards, currPlayer.name);
        cards.revalidate();
        cards.repaint();
        this.revalidate();
        this.repaint();
    }
}
