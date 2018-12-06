/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
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
import javax.swing.text.DefaultCaret;


// graphics user interface #2 that inherits JFrame
public class GUI_2 extends JFrame implements UserInterface {
    // private attributes
    private String    line;
    private Player    currPlayer;
    private JTextArea messageBox;
    private final TreeMap<String, JTextArea> messages;
    private final TreeMap<String, JComboBox> getMenus,   dropMenus,    useMenus,
                                             equipMenus, consumeMenus, buyMenus;
    private final TreeMap<String, JButton>   btns_N,   btns_S,   btns_E,
                                             btns_W,   btns_U,   btns_D,
                                             btns_NE,  btns_NW,  btns_SE,
                                             btns_SW,  btns_NNE, btns_NNW,
                                             btns_ENE, btns_WNW, btns_ESE,
                                             btns_WSW, btns_SSE, btns_SSW;


    // lock
    public static final Object syncLock = new Object();


    // constructor for GUI_2 class
    public GUI_2() {
        Set<Map.Entry<Integer,Character>> characters
                     = Character.characterTree.entrySet();
        messages     = new TreeMap<String, JTextArea>();
        getMenus     = new TreeMap<String, JComboBox>();
        dropMenus    = new TreeMap<String, JComboBox>();
        useMenus     = new TreeMap<String, JComboBox>();
        equipMenus   = new TreeMap<String, JComboBox>();
        consumeMenus = new TreeMap<String, JComboBox>();
        buyMenus     = new TreeMap<String, JComboBox>();
        btns_N       = new TreeMap<String, JButton>();
        btns_S       = new TreeMap<String, JButton>();
        btns_E       = new TreeMap<String, JButton>();
        btns_W       = new TreeMap<String, JButton>();
        btns_U       = new TreeMap<String, JButton>();
        btns_D       = new TreeMap<String, JButton>();
        btns_NE      = new TreeMap<String, JButton>();
        btns_NW      = new TreeMap<String, JButton>();
        btns_SE      = new TreeMap<String, JButton>();
        btns_SW      = new TreeMap<String, JButton>();
        btns_NNE     = new TreeMap<String, JButton>();
        btns_NNW     = new TreeMap<String, JButton>();
        btns_ENE     = new TreeMap<String, JButton>();
        btns_WNW     = new TreeMap<String, JButton>();
        btns_ESE     = new TreeMap<String, JButton>();
        btns_WSW     = new TreeMap<String, JButton>();
        btns_SSE     = new TreeMap<String, JButton>();
        btns_SSW     = new TreeMap<String, JButton>();

        try {
            Image img = ImageIO.read(new File("template.png")).getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
            this.setTitle("MAGICAL GIRL FORCE GO!!! (GUI #2)");
            this.setSize(1200, 800);
            this.setResizable(false);
            this.setBackground(new Color(54, 57, 63));
            this.setContentPane(new JLabel(new ImageIcon(img)));

            JPanel cards = new JPanel();
            cards.setSize(1200, 800);
            cards.setLayout(new CardLayout());
            cards.setOpaque(false);

            for (Map.Entry<Integer,Character> c : characters) {
                if (c.getValue() instanceof Player) {
                    currPlayer = (Player) c.getValue();

                    JLabel playerName;
                    playerName = new JLabel(currPlayer.name);
                    playerName.setToolTipText(currPlayer.description);
                    playerName.setBounds(22, 18, 314, 40);
                    playerName.setForeground(Color.WHITE);
                    //playerName.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    playerName.setFont(new Font("Helvetica Neue", Font.BOLD, 30));

                    JLabel attack = new JLabel("Attack:");
                    attack.setBounds(50, 72, 80, 20);
                    attack.setForeground(new Color(220, 221, 222));
                    //attack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    attack.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    attack.setHorizontalAlignment(SwingConstants.RIGHT);

                    JLabel attackStat = new JLabel() {
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(String.valueOf(currPlayer.attack), 4, 16);
                        }
                    };
                    attackStat.setBounds(140, 72, 64, 20);
                    attackStat.setForeground(new Color(220, 221, 222));
                    //attackStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    attackStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    JLabel defense = new JLabel("Defense:");
                    defense.setBounds(50, 92, 80, 20);
                    defense.setForeground(new Color(220, 221, 222));
                    //defense.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    defense.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    defense.setHorizontalAlignment(SwingConstants.RIGHT);

                    JLabel defenseStat = new JLabel() {
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(String.valueOf(currPlayer.defense), 4, 16);
                        }
                    };
                    defenseStat.setBounds(140, 92, 64, 20);
                    defenseStat.setForeground(new Color(220, 221, 222));
                    //defenseStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    defenseStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    JLabel hp = new JLabel("HP:");
                    hp.setBounds(50, 112, 80, 20);
                    hp.setForeground(new Color(220, 221, 222));
                    //hp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    hp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    hp.setHorizontalAlignment(SwingConstants.RIGHT);

                    JLabel hpStat = new JLabel() {
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(currPlayer.currHP + "/" + currPlayer.maxHP, 4, 16);
                        }
                    };
                    hpStat.setBounds(140, 112, 64, 20);
                    hpStat.setForeground(new Color(220, 221, 222));
                    //hpStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    hpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    JLabel mp = new JLabel("MP:");
                    mp.setBounds(50, 132, 80, 20);
                    mp.setForeground(new Color(220, 221, 222));
                    //mp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    mp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    mp.setHorizontalAlignment(SwingConstants.RIGHT);

                    JLabel mpStat = new JLabel() {
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(String.valueOf(currPlayer.mp), 4, 16);
                        }
                    };
                    mpStat.setBounds(140, 132, 64, 20);
                    mpStat.setForeground(new Color(220, 221, 222));
                    //mpStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    mpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    messageBox = new JTextArea();
                    messageBox.setBackground(new Color(47, 49, 54));
                    messageBox.setForeground(new Color(131, 148, 150));
                    messageBox.setFont(new Font("Menlo", Font.PLAIN, 15));
                    messageBox.setEditable(false);
                    messageBox.setMargin(new Insets(0, 40, 0, 0));
                    DefaultCaret caret = (DefaultCaret) messageBox.getCaret();
                    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                    JScrollPane scrollPane = new JScrollPane(messageBox);
                    scrollPane.setBounds(359, 22, 818, 659);
                    scrollPane.setBorder(BorderFactory.createEmptyBorder());
                    messages.put(currPlayer.name, messageBox);

                    JTextField textBox = new JTextField("Command @" + currPlayer.name);
                    textBox.setBounds(366, 705, 804, 50);
                    textBox.setBackground(new Color(72, 75, 81));
                    textBox.setForeground(new Color(127, 129, 133));
                    textBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 32));
                    textBox.setBorder(BorderFactory.createEmptyBorder());
                    textBox.addFocusListener(new FocusListener() {
                        @Override public void focusGained(FocusEvent e) {
                            textBox.setText("");
                            textBox.setForeground(new Color(200, 201, 203));
                        }
                        @Override public void focusLost(FocusEvent e) {
                            textBox.setText("Command @" + currPlayer.name);
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

                    JComboBox getMenu = new JComboBox();
                    getMenu.setBounds(18, 176, 323, 34);
                    getMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    //getMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    getMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GET" + String.valueOf(getMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    getMenus.put(currPlayer.name, getMenu);

                    JComboBox dropMenu = new JComboBox();
                    dropMenu.setBounds(18, 210, 323, 34);
                    dropMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    //dropMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    dropMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "DROP" + String.valueOf(dropMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    dropMenus.put(currPlayer.name, dropMenu);

                    JComboBox useMenu = new JComboBox();
                    useMenu.setBounds(18, 244, 323, 34);
                    useMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    //useMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    useMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "USE" + String.valueOf(useMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    useMenus.put(currPlayer.name, useMenu);

                    JComboBox equipMenu = new JComboBox();
                    equipMenu.setBounds(18, 278, 323, 34);
                    equipMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    //equipMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    equipMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "EQUIP" + String.valueOf(equipMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    equipMenus.put(currPlayer.name, equipMenu);

                    JComboBox consumeMenu = new JComboBox();
                    consumeMenu.setBounds(18, 312, 323, 34);
                    consumeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    //consumeMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    consumeMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "CONSUME" + String.valueOf(consumeMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    consumeMenus.put(currPlayer.name, consumeMenu);

                    JComboBox buyMenu = new JComboBox();
                    buyMenu.setBounds(18, 346, 323, 34);
                    buyMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    //buyMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    buyMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "BUY" + String.valueOf(buyMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    buyMenus.put(currPlayer.name, buyMenu);

                    JButton btn_N = new JButton("N");
                    btn_N.setBounds(161, 427, 46, 46);
                    btn_N.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_N.setForeground(Color.WHITE);
                    btn_N.setBorder(BorderFactory.createEmptyBorder());
                    btn_N.setOpaque(false);
                    btn_N.setContentAreaFilled(false);
                    btn_N.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_N.setToolTipText("NORTH");
                    btn_N.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO N";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_N.put(currPlayer.name, btn_N);

                    JButton btn_S = new JButton("S");
                    btn_S.setBounds(161, 725, 46, 46);
                    btn_S.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_S.setForeground(Color.WHITE);
                    btn_S.setBorder(BorderFactory.createEmptyBorder());
                    btn_S.setOpaque(false);
                    btn_S.setContentAreaFilled(false);
                    btn_S.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_S.setToolTipText("SOUTH");
                    btn_S.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO S";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_S.put(currPlayer.name, btn_S);

                    JButton btn_E = new JButton("E");
                    btn_E.setBounds(305, 573, 46, 46);
                    btn_E.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_E.setForeground(Color.WHITE);
                    btn_E.setBorder(BorderFactory.createEmptyBorder());
                    btn_E.setOpaque(false);
                    btn_E.setContentAreaFilled(false);
                    btn_E.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_E.setToolTipText("EAST");
                    btn_E.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO E";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_E.put(currPlayer.name, btn_E);

                    JButton btn_W = new JButton("W");
                    btn_W.setBounds(10, 573, 46, 46);
                    btn_W.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_W.setForeground(Color.WHITE);
                    btn_W.setBorder(BorderFactory.createEmptyBorder());
                    btn_W.setOpaque(false);
                    btn_W.setContentAreaFilled(false);
                    btn_W.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_W.setToolTipText("WEST");
                    btn_W.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO W";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_W.put(currPlayer.name, btn_W);

                    JButton btn_U = new JButton("U");
                    btn_U.setBounds(121, 725, 46, 46);
                    btn_U.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_U.setForeground(Color.WHITE);
                    btn_U.setBorder(BorderFactory.createEmptyBorder());
                    btn_U.setOpaque(false);
                    btn_U.setContentAreaFilled(false);
                    btn_U.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_U.setToolTipText("UP");
                    btn_U.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO U";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_U.put(currPlayer.name, btn_U);

                    JButton btn_D = new JButton("D");
                    btn_D.setBounds(201, 725, 46, 46);
                    btn_D.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_D.setForeground(Color.WHITE);
                    btn_D.setBorder(BorderFactory.createEmptyBorder());
                    btn_D.setOpaque(false);
                    btn_D.setContentAreaFilled(false);
                    btn_D.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_D.setToolTipText("DOWN");
                    btn_D.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO D";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_D.put(currPlayer.name, btn_D);

                    JButton btn_NE = new JButton("NE");
                    btn_NE.setBounds(239, 505, 48, 42);
                    btn_NE.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_NE.setForeground(Color.WHITE);
                    btn_NE.setBorder(BorderFactory.createEmptyBorder());
                    btn_NE.setOpaque(false);
                    btn_NE.setContentAreaFilled(false);
                    btn_NE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_NE.setToolTipText("NORTHEAST");
                    btn_NE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NE.put(currPlayer.name, btn_NE);

                    JButton btn_NW = new JButton("NW");
                    btn_NW.setBounds(76, 505, 48, 42);
                    btn_NW.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_NW.setForeground(Color.WHITE);
                    btn_NW.setBorder(BorderFactory.createEmptyBorder());
                    btn_NW.setOpaque(false);
                    btn_NW.setContentAreaFilled(false);
                    btn_NW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_NW.setToolTipText("NORTHWEST");
                    btn_NW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NW.put(currPlayer.name, btn_NW);

                    JButton btn_SE = new JButton("SE");
                    btn_SE.setBounds(239, 649, 48, 42);
                    btn_SE.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_SE.setForeground(Color.WHITE);
                    btn_SE.setBorder(BorderFactory.createEmptyBorder());
                    btn_SE.setOpaque(false);
                    btn_SE.setContentAreaFilled(false);
                    btn_SE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_SE.setToolTipText("SOUTHEAST");
                    btn_SE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SE.put(currPlayer.name, btn_SE);

                    JButton btn_SW = new JButton("SW");
                    btn_SW.setBounds(76, 649, 48, 42);
                    btn_SW.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_SW.setForeground(Color.WHITE);
                    btn_SW.setBorder(BorderFactory.createEmptyBorder());
                    btn_SW.setOpaque(false);
                    btn_SW.setContentAreaFilled(false);
                    btn_SW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_SW.setToolTipText("SOUTHWEST");
                    btn_SW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SW.put(currPlayer.name, btn_SW);

                    JButton btn_NNE = new JButton("NNE");
                    btn_NNE.setBounds(206, 506, 30, 26);
                    btn_NNE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_NNE.setForeground(Color.WHITE);
                    btn_NNE.setBorder(BorderFactory.createEmptyBorder());
                    btn_NNE.setOpaque(false);
                    btn_NNE.setContentAreaFilled(false);
                    btn_NNE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_NNE.setToolTipText("NORTH-NORTHEAST");
                    btn_NNE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NNE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NNE.put(currPlayer.name, btn_NNE);

                    JButton btn_NNW = new JButton("NNW");
                    btn_NNW.setBounds(130, 506, 34, 26);
                    btn_NNW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_NNW.setForeground(Color.WHITE);
                    btn_NNW.setBorder(BorderFactory.createEmptyBorder());
                    btn_NNW.setOpaque(false);
                    btn_NNW.setContentAreaFilled(false);
                    btn_NNW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_NNW.setToolTipText("NORTH-NORTHWEST");
                    btn_NNW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NNW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NNW.put(currPlayer.name, btn_NNW);

                    JButton btn_ENE = new JButton("ENE");
                    btn_ENE.setBounds(254, 548, 30, 26);
                    btn_ENE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_ENE.setForeground(Color.WHITE);
                    btn_ENE.setBorder(BorderFactory.createEmptyBorder());
                    btn_ENE.setOpaque(false);
                    btn_ENE.setContentAreaFilled(false);
                    btn_ENE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_ENE.setToolTipText("EAST-NORTHEAST");
                    btn_ENE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO ENE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_ENE.put(currPlayer.name, btn_ENE);

                    JButton btn_WNW = new JButton("WNW");
                    btn_WNW.setBounds(76, 548, 36, 26);
                    btn_WNW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_WNW.setForeground(Color.WHITE);
                    btn_WNW.setBorder(BorderFactory.createEmptyBorder());
                    btn_WNW.setOpaque(false);
                    btn_WNW.setContentAreaFilled(false);
                    btn_WNW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_WNW.setToolTipText("WEST-NORTHWEST");
                    btn_WNW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO WNW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_WNW.put(currPlayer.name, btn_WNW);

                    JButton btn_ESE = new JButton("ESE");
                    btn_ESE.setBounds(254, 622, 30, 26);
                    btn_ESE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_ESE.setForeground(Color.WHITE);
                    btn_ESE.setBorder(BorderFactory.createEmptyBorder());
                    btn_ESE.setOpaque(false);
                    btn_ESE.setContentAreaFilled(false);
                    btn_ESE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_ESE.setToolTipText("EAST-SOUTHEAST");
                    btn_ESE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO ESE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_ESE.put(currPlayer.name, btn_ESE);

                    JButton btn_WSW = new JButton("WSW");
                    btn_WSW.setBounds(76, 622, 36, 26);
                    btn_WSW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_WSW.setForeground(Color.WHITE);
                    btn_WSW.setBorder(BorderFactory.createEmptyBorder());
                    btn_WSW.setOpaque(false);
                    btn_WSW.setContentAreaFilled(false);
                    btn_WSW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_WSW.setToolTipText("WEST-SOUTHWEST");
                    btn_WSW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO WSW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_WSW.put(currPlayer.name, btn_WSW);

                    JButton btn_SSE = new JButton("SSE");
                    btn_SSE.setBounds(206, 665, 30, 26);
                    btn_SSE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_SSE.setForeground(Color.WHITE);
                    btn_SSE.setBorder(BorderFactory.createEmptyBorder());
                    btn_SSE.setOpaque(false);
                    btn_SSE.setContentAreaFilled(false);
                    btn_SSE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_SSE.setToolTipText("SOUTH-SOUTHEAST");
                    btn_SSE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SSE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SSE.put(currPlayer.name, btn_SSE);

                    JButton btn_SSW = new JButton("SSW");
                    btn_SSW.setBounds(130, 665, 34, 26);
                    btn_SSW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_SSW.setForeground(Color.WHITE);
                    btn_SSW.setBorder(BorderFactory.createEmptyBorder());
                    btn_SSW.setOpaque(false);
                    btn_SSW.setContentAreaFilled(false);
                    btn_SSW.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_SSW.setToolTipText("SOUTH-SOUTHWEST");
                    btn_SSW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SSW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SSW.put(currPlayer.name, btn_SSW);

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
                    card.add(getMenu);
                    card.add(dropMenu);
                    card.add(useMenu);
                    card.add(equipMenu);
                    card.add(consumeMenu);
                    card.add(buyMenu);
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
        } catch (Exception e) { e.printStackTrace(); }
    }//end class constructor


    // display message
    public void display(String message) { messageBox.append(message); }


    // get line
    public String getLine() {
        try {
            synchronized(syncLock) {
                syncLock.wait();
                return line;
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }//end getLine()


    // disable direction buttons
    private void disableAllDirections() {
        String n = currPlayer.name;

        btns_N.get(n).setEnabled(false);
        btns_N.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_N.get(n).setForeground(new Color(60, 65, 72));

        btns_S.get(n).setEnabled(false);
        btns_S.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_S.get(n).setForeground(new Color(60, 65, 72));

        btns_E.get(n).setEnabled(false);
        btns_E.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_E.get(n).setForeground(new Color(60, 65, 72));

        btns_W.get(n).setEnabled(false);
        btns_W.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_W.get(n).setForeground(new Color(60, 65, 72));

        btns_U.get(n).setEnabled(false);
        btns_U.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_U.get(n).setForeground(new Color(60, 65, 72));

        btns_D.get(n).setEnabled(false);
        btns_D.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_D.get(n).setForeground(new Color(60, 65, 72));

        btns_NE.get(n).setEnabled(false);
        btns_NE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NE.get(n).setForeground(new Color(60, 65, 72));

        btns_NW.get(n).setEnabled(false);
        btns_NW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NW.get(n).setForeground(new Color(60, 65, 72));

        btns_SE.get(n).setEnabled(false);
        btns_SE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SE.get(n).setForeground(new Color(60, 65, 72));

        btns_SW.get(n).setEnabled(false);
        btns_SW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SW.get(n).setForeground(new Color(60, 65, 72));

        btns_NNE.get(n).setEnabled(false);
        btns_NNE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NNE.get(n).setForeground(new Color(60, 65, 72));

        btns_NNW.get(n).setEnabled(false);
        btns_NNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NNW.get(n).setForeground(new Color(60, 65, 72));

        btns_ENE.get(n).setEnabled(false);
        btns_ENE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_ENE.get(n).setForeground(new Color(60, 65, 72));

        btns_WNW.get(n).setEnabled(false);
        btns_WNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_WNW.get(n).setForeground(new Color(60, 65, 72));

        btns_ESE.get(n).setEnabled(false);
        btns_ESE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_ESE.get(n).setForeground(new Color(60, 65, 72));

        btns_WSW.get(n).setEnabled(false);
        btns_WSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_WSW.get(n).setForeground(new Color(60, 65, 72));

        btns_SSE.get(n).setEnabled(false);
        btns_SSE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SSE.get(n).setForeground(new Color(60, 65, 72));

        btns_SSW.get(n).setEnabled(false);
        btns_SSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SSW.get(n).setForeground(new Color(60, 65, 72));
    }//end disableDirectionButtons()


    // enable direction button
    private void enableDirection(String dir) {
        String n = currPlayer.name;

        if      (dir.equals("NORTH")) {
            btns_N.get(n).setEnabled(true);
            btns_N.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_N.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("SOUTH")) {
            btns_S.get(n).setEnabled(true);
            btns_S.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_S.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("EAST")) {
            btns_E.get(n).setEnabled(true);
            btns_E.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_E.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("WEST")) {
            btns_W.get(n).setEnabled(true);
            btns_W.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_W.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("UP")) {
            btns_U.get(n).setEnabled(true);
            btns_U.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_U.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("DOWN")) {
            btns_D.get(n).setEnabled(true);
            btns_D.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_D.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("NORTHEAST")) {
            btns_NE.get(n).setEnabled(true);
            btns_NE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NE.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("NORTHWEST")) {
            btns_NW.get(n).setEnabled(true);
            btns_NW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NW.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("SOUTHEAST")) {
            btns_SE.get(n).setEnabled(true);
            btns_SE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SE.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("SOUTHWEST")) {
            btns_SW.get(n).setEnabled(true);
            btns_SW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SW.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("NORTH-NORTHEAST")) {
            btns_NNE.get(n).setEnabled(true);
            btns_NNE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NNE.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("NORTH-NORTHWEST")) {
            btns_NNW.get(n).setEnabled(true);
            btns_NNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NNW.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("EAST-NORTHEAST")) {
            btns_ENE.get(n).setEnabled(true);
            btns_ENE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_ENE.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("WEST-NORTHWEST")) {
            btns_WNW.get(n).setEnabled(true);
            btns_WNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_WNW.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("EAST-SOUTHEAST")) {
            btns_ESE.get(n).setEnabled(true);
            btns_ESE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_ESE.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("WEST-SOUTHWEST")) {
            btns_WSW.get(n).setEnabled(true);
            btns_WSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_WSW.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("SOUTH-SOUTHEAST")) {
            btns_SSE.get(n).setEnabled(true);
            btns_SSE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SSE.get(n).setForeground(Color.WHITE);
        }
        else if (dir.equals("SOUTH-SOUTHWEST")) {
            btns_SSW.get(n).setEnabled(true);
            btns_SSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SSW.get(n).setForeground(Color.WHITE);
        }
    }//end enableDirectionButton()


    // populate get menu
    private void populateGetMenu() {
        String n                    = currPlayer.name;
        List<Artifact>    artifacts = currPlayer.currPlace.placeArtifacts;
        ArrayList<String> names     = new ArrayList<String>();
        names.add("GET");
        for (Artifact a : artifacts)
            names.add("■ " + a.name().toUpperCase());

        getMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));
    }//end populateGetMenu()


    // populate drop menu
    private void populateDropMenu() {
        String n                    = currPlayer.name;
        List<Artifact>    artifacts = currPlayer.inventory;
        ArrayList<String> names     = new ArrayList<String>();
        names.add("DROP");
        for (Artifact a : artifacts)
            names.add("■ " + a.name().toUpperCase());

        dropMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));
    }//end populateDropMenu()


    // populate use menu
    private void populateUseMenu() {
        String n                    = currPlayer.name;
        List<Artifact>    artifacts = currPlayer.inventory;
        ArrayList<String> names     = new ArrayList<String>();
        names.add("USE");
        for (Artifact a : artifacts)
            if (a.pattern() > 0)
                names.add("■ " + a.name().toUpperCase());

        useMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));
    }//end populateUseMenu()


    // populate equip menu
    private void populateEquipMenu() {
        String n                    = currPlayer.name;
        List<Artifact>    artifacts = currPlayer.inventory;
        ArrayList<String> names     = new ArrayList<String>();
        names.add("EQUIP");
        for (Artifact a : artifacts)
            if (a instanceof Armor || a instanceof Weapon)
                names.add("■ " + a.name().toUpperCase());

        equipMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));
    }//end populateEquipMenu()


    // populate consume menu
    private void populateConsumeMenu() {
        String n                    = currPlayer.name;
        List<Artifact>    artifacts = currPlayer.inventory;
        ArrayList<String> names     = new ArrayList<String>();
        names.add("CONSUME");
        for (Artifact a : artifacts)
            if (a instanceof Food)
                names.add("■ " + a.name().toUpperCase());

        consumeMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));
    }//end populateConsumeMenu()


    // populate consume menu
    private void populateBuyMenu() {
        String n                = currPlayer.name;
        ArrayList<String> names = new ArrayList<String>();
        names.add("BUY");

        for (int i = 1; i < 6; i++)
            names.add("■ " + i);

        buyMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));
    }//end populateConsumeMenu()


    // populate all menus
    private void populateAllMenus() {
        populateGetMenu();
        populateDropMenu();
        populateUseMenu();
        populateEquipMenu();
        populateConsumeMenu();
        populateBuyMenu();
    }//end populateAllMenus()


    // switch card
    public void switchCard(Player p, Place place) {
        JPanel cards    = (JPanel) this.getContentPane().getComponent(0);
        CardLayout card = (CardLayout) cards.getLayout();
        currPlayer      = p;
        messageBox      = messages.get(currPlayer.name);

        disableAllDirections();

        List<Direction> directions = place.paths;
        for (Direction d : directions)
            enableDirection(d.toString());

        populateAllMenus();

        card.show(cards, currPlayer.name);
        cards.revalidate();
        cards.repaint();
        this.revalidate();
        this.repaint();
    }//end switchCard()
}//end GUI_2 class
