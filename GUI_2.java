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
    private JTextArea message;
    private JFrame    frame;
    private final TreeMap<String, JTextArea> messages;
    private final TreeMap<String, JLabel>    attackStats, defenseStats,
                                             hpStats,     mpStats;
    private final TreeMap<String, JComboBox> getMenus,   dropMenus,    useMenus,
                                             equipMenus, consumeMenus, buyMenus,
                                             attackMenus;
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
        attackStats  = new TreeMap<String, JLabel>();
        defenseStats = new TreeMap<String, JLabel>();
        hpStats      = new TreeMap<String, JLabel>();
        mpStats      = new TreeMap<String, JLabel>();
        getMenus     = new TreeMap<String, JComboBox>();
        dropMenus    = new TreeMap<String, JComboBox>();
        useMenus     = new TreeMap<String, JComboBox>();
        equipMenus   = new TreeMap<String, JComboBox>();
        consumeMenus = new TreeMap<String, JComboBox>();
        buyMenus     = new TreeMap<String, JComboBox>();
        attackMenus  = new TreeMap<String, JComboBox>();
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
            frame = this;
            frame.setTitle("MAGICAL GIRL FORCE GO!!! (GUI #2)");
            frame.setSize(1200, 800);
            frame.setResizable(false);
            frame.setBackground(new Color(54, 57, 63));
            frame.setContentPane(new JLabel(new ImageIcon(img)));

            JPanel cards = new JPanel();
            cards.setSize(1200, 800);
            cards.setLayout(new CardLayout());
            cards.setOpaque(false);

            for (Map.Entry<Integer,Character> c : characters) {
                if (c.getValue() instanceof Player) {
                    currPlayer = (Player) c.getValue();

                    // player name
                    JLabel playerName;
                    playerName = new JLabel(currPlayer.name);
                    playerName.setBounds(22, 18, 314, 40);
                    playerName.setForeground(Color.WHITE);
                    playerName.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
                    playerName.setToolTipText(currPlayer.description.toUpperCase());

                    // attack stat
                    JLabel attack = new JLabel("Attack:");
                    attack.setBounds(30, 72, 110, 20);
                    attack.setForeground(new Color(220, 221, 222));
                    attack.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    attack.setHorizontalAlignment(SwingConstants.RIGHT);
                    JLabel attackStat = new JLabel();
                    attackStat.setBounds(148, 72, 178, 20);
                    attackStat.setForeground(new Color(220, 221, 222));
                    attackStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
                    attackStats.put(currPlayer.name, attackStat);

                    // defense stat
                    JLabel defense = new JLabel("Defense:");
                    defense.setBounds(30, 92, 110, 20);
                    defense.setForeground(new Color(220, 221, 222));
                    defense.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    defense.setHorizontalAlignment(SwingConstants.RIGHT);
                    JLabel defenseStat = new JLabel();
                    defenseStat.setBounds(148, 92, 178, 20);
                    defenseStat.setForeground(new Color(220, 221, 222));
                    defenseStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
                    defenseStats.put(currPlayer.name, defenseStat);

                    // hp stat
                    JLabel hp = new JLabel("HP:");
                    hp.setBounds(30, 112, 110, 20);
                    hp.setForeground(new Color(220, 221, 222));
                    hp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    hp.setHorizontalAlignment(SwingConstants.RIGHT);
                    JLabel hpStat = new JLabel();
                    hpStat.setBounds(148, 112, 178, 20);
                    hpStat.setForeground(new Color(220, 221, 222));
                    hpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
                    hpStats.put(currPlayer.name, hpStat);

                    // mp stat
                    JLabel mp = new JLabel("MP:");
                    mp.setBounds(30, 132, 110, 20);
                    mp.setForeground(new Color(220, 221, 222));
                    mp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    mp.setHorizontalAlignment(SwingConstants.RIGHT);
                    JLabel mpStat = new JLabel();
                    mpStat.setBounds(148, 132, 178, 20);
                    mpStat.setForeground(new Color(220, 221, 222));
                    mpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));
                    mpStats.put(currPlayer.name, mpStat);

                    // output message box
                    message = new JTextArea();
                    message.setBackground(new Color(47, 49, 54));
                    message.setForeground(new Color(131, 148, 150));
                    message.setFont(new Font("Menlo", Font.PLAIN, 15));
                    message.setEditable(false);
                    message.setMargin(new Insets(0, 40, 0, 0));
                    DefaultCaret caret = (DefaultCaret) message.getCaret();
                    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                    JScrollPane messagePane = new JScrollPane(message);
                    messagePane.setBounds(359, 22, 818, 659);
                    messagePane.setBorder(BorderFactory.createEmptyBorder());
                    messages.put(currPlayer.name, message);

                    // command input box
                    JTextField entryBox = new JTextField("Command @" + currPlayer.name);
                    entryBox.setBounds(366, 705, 804, 50);
                    entryBox.setBackground(new Color(72, 75, 81));
                    entryBox.setForeground(new Color(127, 129, 133));
                    entryBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 32));
                    entryBox.setBorder(BorderFactory.createEmptyBorder());
                    entryBox.addFocusListener(new FocusListener() {
                        @Override public void focusGained(FocusEvent e) {
                            entryBox.setText("");
                            entryBox.setForeground(new Color(200, 201, 203));
                        }
                        @Override public void focusLost(FocusEvent e) {
                            entryBox.setText("Command @" + currPlayer.name);
                            entryBox.setForeground(new Color(127, 129, 133));
                        }
                    });
                    entryBox.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = entryBox.getText();
                            entryBox.setText("");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });

                    // 'GET' command menu
                    JComboBox getMenu = new JComboBox();
                    getMenu.setBounds(18, 176, 323, 28);
                    getMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    getMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GET" + String.valueOf(getMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    getMenus.put(currPlayer.name, getMenu);

                    // 'DROP' command menu
                    JComboBox dropMenu = new JComboBox();
                    dropMenu.setBounds(18, 204, 323, 28);
                    dropMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    dropMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "DROP" + String.valueOf(dropMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    dropMenus.put(currPlayer.name, dropMenu);

                    // 'USE' command menu
                    JComboBox useMenu = new JComboBox();
                    useMenu.setBounds(18, 232, 323, 28);
                    useMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    useMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "USE" + String.valueOf(useMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    useMenus.put(currPlayer.name, useMenu);

                    // 'EQUIP' command menu
                    JComboBox equipMenu = new JComboBox();
                    equipMenu.setBounds(18, 260, 323, 28);
                    equipMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    equipMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "EQUIP" + String.valueOf(equipMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            updateStats();
                            populateAllMenus();
                        }
                    });
                    equipMenus.put(currPlayer.name, equipMenu);

                    // 'CONSUME' command menu
                    JComboBox consumeMenu = new JComboBox();
                    consumeMenu.setBounds(18, 288, 323, 28);
                    consumeMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    consumeMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "CONSUME" + String.valueOf(consumeMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            updateStats();
                            populateAllMenus();
                        }
                    });
                    consumeMenus.put(currPlayer.name, consumeMenu);

                    // 'BUY' command menu
                    JComboBox buyMenu = new JComboBox();
                    buyMenu.setBounds(18, 316, 323, 28);
                    buyMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    buyMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "BUY" + String.valueOf(buyMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    buyMenus.put(currPlayer.name, buyMenu);

                    // 'ATTACK' command menu
                    JComboBox attackMenu = new JComboBox();
                    attackMenu.setBounds(18, 344, 323, 28);
                    attackMenu.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    attackMenu.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "ATTACK" + String.valueOf(attackMenu.getSelectedItem()).replaceAll("■", "");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                            try { Thread.sleep(10); } catch (Exception s) { }
                            populateAllMenus();
                        }
                    });
                    attackMenus.put(currPlayer.name, attackMenu);

                    // 'INVE' command button
                    JButton btn_inve = new JButton("INVENTORY");
                    btn_inve.setBounds(24, 388, 140, 28);
                    btn_inve.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    btn_inve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_inve.setForeground(Color.WHITE);
                    btn_inve.setBorder(BorderFactory.createEmptyBorder());
                    btn_inve.setOpaque(false);
                    btn_inve.setContentAreaFilled(false);
                    btn_inve.setToolTipText("VIEW INVENTORY");
                    btn_inve.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "INVE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });

                    // 'INSPECT' command button
                    JButton btn_insp = new JButton("INSPECT");
                    btn_insp.setBounds(181, 388, 120, 28);
                    btn_insp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    btn_insp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    btn_insp.setForeground(Color.WHITE);
                    btn_insp.setBorder(BorderFactory.createEmptyBorder());
                    btn_insp.setOpaque(false);
                    btn_insp.setContentAreaFilled(false);
                    btn_insp.setToolTipText("INSPECT FOOTPRINTS");
                    btn_insp.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "INSPECT";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });

                    // N direction button
                    JButton btn_N = new JButton("N");
                    btn_N.setBounds(161, 427, 46, 46);
                    btn_N.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_N.setForeground(Color.WHITE);
                    btn_N.setBorder(BorderFactory.createEmptyBorder());
                    btn_N.setOpaque(false);
                    btn_N.setContentAreaFilled(false);
                    btn_N.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO N";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_N.put(currPlayer.name, btn_N);

                    // S direction button
                    JButton btn_S = new JButton("S");
                    btn_S.setBounds(161, 725, 46, 46);
                    btn_S.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_S.setForeground(Color.WHITE);
                    btn_S.setBorder(BorderFactory.createEmptyBorder());
                    btn_S.setOpaque(false);
                    btn_S.setContentAreaFilled(false);
                    btn_S.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO S";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_S.put(currPlayer.name, btn_S);

                    // E direction button
                    JButton btn_E = new JButton("E");
                    btn_E.setBounds(305, 573, 46, 46);
                    btn_E.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_E.setForeground(Color.WHITE);
                    btn_E.setBorder(BorderFactory.createEmptyBorder());
                    btn_E.setOpaque(false);
                    btn_E.setContentAreaFilled(false);
                    btn_E.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO E";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_E.put(currPlayer.name, btn_E);

                    // W direction button
                    JButton btn_W = new JButton("W");
                    btn_W.setBounds(10, 573, 46, 46);
                    btn_W.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_W.setForeground(Color.WHITE);
                    btn_W.setBorder(BorderFactory.createEmptyBorder());
                    btn_W.setOpaque(false);
                    btn_W.setContentAreaFilled(false);
                    btn_W.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO W";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_W.put(currPlayer.name, btn_W);

                    // U direction button
                    JButton btn_U = new JButton("U");
                    btn_U.setBounds(121, 725, 46, 46);
                    btn_U.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_U.setForeground(Color.WHITE);
                    btn_U.setBorder(BorderFactory.createEmptyBorder());
                    btn_U.setOpaque(false);
                    btn_U.setContentAreaFilled(false);
                    btn_U.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO U";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_U.put(currPlayer.name, btn_U);

                    // D direction button
                    JButton btn_D = new JButton("D");
                    btn_D.setBounds(201, 725, 46, 46);
                    btn_D.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
                    btn_D.setForeground(Color.WHITE);
                    btn_D.setBorder(BorderFactory.createEmptyBorder());
                    btn_D.setOpaque(false);
                    btn_D.setContentAreaFilled(false);
                    btn_D.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO D";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_D.put(currPlayer.name, btn_D);

                    // NE direction button
                    JButton btn_NE = new JButton("NE");
                    btn_NE.setBounds(239, 505, 48, 42);
                    btn_NE.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_NE.setForeground(Color.WHITE);
                    btn_NE.setBorder(BorderFactory.createEmptyBorder());
                    btn_NE.setOpaque(false);
                    btn_NE.setContentAreaFilled(false);
                    btn_NE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NE.put(currPlayer.name, btn_NE);

                    // NW direction button
                    JButton btn_NW = new JButton("NW");
                    btn_NW.setBounds(76, 505, 48, 42);
                    btn_NW.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_NW.setForeground(Color.WHITE);
                    btn_NW.setBorder(BorderFactory.createEmptyBorder());
                    btn_NW.setOpaque(false);
                    btn_NW.setContentAreaFilled(false);
                    btn_NW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NW.put(currPlayer.name, btn_NW);

                    // SE direction button
                    JButton btn_SE = new JButton("SE");
                    btn_SE.setBounds(239, 649, 48, 42);
                    btn_SE.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_SE.setForeground(Color.WHITE);
                    btn_SE.setBorder(BorderFactory.createEmptyBorder());
                    btn_SE.setOpaque(false);
                    btn_SE.setContentAreaFilled(false);
                    btn_SE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SE.put(currPlayer.name, btn_SE);

                    // SW direction button
                    JButton btn_SW = new JButton("SW");
                    btn_SW.setBounds(76, 649, 48, 42);
                    btn_SW.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
                    btn_SW.setForeground(Color.WHITE);
                    btn_SW.setBorder(BorderFactory.createEmptyBorder());
                    btn_SW.setOpaque(false);
                    btn_SW.setContentAreaFilled(false);
                    btn_SW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SW.put(currPlayer.name, btn_SW);

                    // NNE direction button
                    JButton btn_NNE = new JButton("NNE");
                    btn_NNE.setBounds(206, 506, 30, 26);
                    btn_NNE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_NNE.setForeground(Color.WHITE);
                    btn_NNE.setBorder(BorderFactory.createEmptyBorder());
                    btn_NNE.setOpaque(false);
                    btn_NNE.setContentAreaFilled(false);
                    btn_NNE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NNE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NNE.put(currPlayer.name, btn_NNE);

                    // NNW direction button
                    JButton btn_NNW = new JButton("NNW");
                    btn_NNW.setBounds(130, 506, 34, 26);
                    btn_NNW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_NNW.setForeground(Color.WHITE);
                    btn_NNW.setBorder(BorderFactory.createEmptyBorder());
                    btn_NNW.setOpaque(false);
                    btn_NNW.setContentAreaFilled(false);
                    btn_NNW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO NNW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_NNW.put(currPlayer.name, btn_NNW);

                    // ENE direction button
                    JButton btn_ENE = new JButton("ENE");
                    btn_ENE.setBounds(254, 548, 30, 26);
                    btn_ENE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_ENE.setForeground(Color.WHITE);
                    btn_ENE.setBorder(BorderFactory.createEmptyBorder());
                    btn_ENE.setOpaque(false);
                    btn_ENE.setContentAreaFilled(false);
                    btn_ENE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO ENE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_ENE.put(currPlayer.name, btn_ENE);

                    // WNW direction button
                    JButton btn_WNW = new JButton("WNW");
                    btn_WNW.setBounds(76, 548, 36, 26);
                    btn_WNW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_WNW.setForeground(Color.WHITE);
                    btn_WNW.setBorder(BorderFactory.createEmptyBorder());
                    btn_WNW.setOpaque(false);
                    btn_WNW.setContentAreaFilled(false);
                    btn_WNW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO WNW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_WNW.put(currPlayer.name, btn_WNW);

                    // ESE direction button
                    JButton btn_ESE = new JButton("ESE");
                    btn_ESE.setBounds(254, 622, 30, 26);
                    btn_ESE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_ESE.setForeground(Color.WHITE);
                    btn_ESE.setBorder(BorderFactory.createEmptyBorder());
                    btn_ESE.setOpaque(false);
                    btn_ESE.setContentAreaFilled(false);
                    btn_ESE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO ESE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_ESE.put(currPlayer.name, btn_ESE);

                    // WSW direction button
                    JButton btn_WSW = new JButton("WSW");
                    btn_WSW.setBounds(76, 622, 36, 26);
                    btn_WSW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_WSW.setForeground(Color.WHITE);
                    btn_WSW.setBorder(BorderFactory.createEmptyBorder());
                    btn_WSW.setOpaque(false);
                    btn_WSW.setContentAreaFilled(false);
                    btn_WSW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO WSW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_WSW.put(currPlayer.name, btn_WSW);

                    // SSE direction button
                    JButton btn_SSE = new JButton("SSE");
                    btn_SSE.setBounds(206, 665, 30, 26);
                    btn_SSE.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_SSE.setForeground(Color.WHITE);
                    btn_SSE.setBorder(BorderFactory.createEmptyBorder());
                    btn_SSE.setOpaque(false);
                    btn_SSE.setContentAreaFilled(false);
                    btn_SSE.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SSE";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SSE.put(currPlayer.name, btn_SSE);

                    // SSW direction button
                    JButton btn_SSW = new JButton("SSW");
                    btn_SSW.setBounds(130, 665, 34, 26);
                    btn_SSW.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
                    btn_SSW.setForeground(Color.WHITE);
                    btn_SSW.setBorder(BorderFactory.createEmptyBorder());
                    btn_SSW.setOpaque(false);
                    btn_SSW.setContentAreaFilled(false);
                    btn_SSW.addActionListener(new ActionListener() {
                        @Override public void actionPerformed(ActionEvent e) {
                            line = "GO SSW";
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });
                    btns_SSW.put(currPlayer.name, btn_SSW);

                    // add components to player's card
                    JPanel card = new JPanel();
                    card.setLayout(null);
                    card.setOpaque(false);
                    card.add(messagePane);
                    card.add(entryBox);
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
                    card.add(attackMenu);
                    card.add(btn_inve);
                    card.add(btn_insp);
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

                    // add player's card to cards collection
                    cards.add(card, c.getValue().name);
                }//end if...
            }//end for...

            frame.getContentPane().add(cards);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            message = messages.firstEntry().getValue();
        } catch (Exception e) { e.printStackTrace(); }
    }//end class constructor


    // display message
    public void display(String m) { message.append(m); }


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


    // disable all direction buttons
    private void disableAllDirections() {
        String n = currPlayer.name;

        btns_N.get(n).setEnabled(false);
        btns_N.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_N.get(n).setForeground(new Color(60, 65, 72));
        btns_N.get(n).setToolTipText("");

        btns_S.get(n).setEnabled(false);
        btns_S.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_S.get(n).setForeground(new Color(60, 65, 72));
        btns_S.get(n).setToolTipText("");

        btns_E.get(n).setEnabled(false);
        btns_E.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_E.get(n).setForeground(new Color(60, 65, 72));
        btns_E.get(n).setToolTipText("");

        btns_W.get(n).setEnabled(false);
        btns_W.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_W.get(n).setForeground(new Color(60, 65, 72));
        btns_W.get(n).setToolTipText("");

        btns_U.get(n).setEnabled(false);
        btns_U.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_U.get(n).setForeground(new Color(60, 65, 72));
        btns_U.get(n).setToolTipText("");

        btns_D.get(n).setEnabled(false);
        btns_D.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_D.get(n).setForeground(new Color(60, 65, 72));
        btns_D.get(n).setToolTipText("");

        btns_NE.get(n).setEnabled(false);
        btns_NE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NE.get(n).setForeground(new Color(60, 65, 72));
        btns_NE.get(n).setToolTipText("");

        btns_NW.get(n).setEnabled(false);
        btns_NW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NW.get(n).setForeground(new Color(60, 65, 72));
        btns_NW.get(n).setToolTipText("");

        btns_SE.get(n).setEnabled(false);
        btns_SE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SE.get(n).setForeground(new Color(60, 65, 72));
        btns_SE.get(n).setToolTipText("");

        btns_SW.get(n).setEnabled(false);
        btns_SW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SW.get(n).setForeground(new Color(60, 65, 72));
        btns_SW.get(n).setToolTipText("");

        btns_NNE.get(n).setEnabled(false);
        btns_NNE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NNE.get(n).setForeground(new Color(60, 65, 72));
        btns_NNE.get(n).setToolTipText("");

        btns_NNW.get(n).setEnabled(false);
        btns_NNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_NNW.get(n).setForeground(new Color(60, 65, 72));
        btns_NNW.get(n).setToolTipText("");

        btns_ENE.get(n).setEnabled(false);
        btns_ENE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_ENE.get(n).setForeground(new Color(60, 65, 72));
        btns_ENE.get(n).setToolTipText("");

        btns_WNW.get(n).setEnabled(false);
        btns_WNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_WNW.get(n).setForeground(new Color(60, 65, 72));
        btns_WNW.get(n).setToolTipText("");

        btns_ESE.get(n).setEnabled(false);
        btns_ESE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_ESE.get(n).setForeground(new Color(60, 65, 72));
        btns_ESE.get(n).setToolTipText("");

        btns_WSW.get(n).setEnabled(false);
        btns_WSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_WSW.get(n).setForeground(new Color(60, 65, 72));
        btns_WSW.get(n).setToolTipText("");

        btns_SSE.get(n).setEnabled(false);
        btns_SSE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SSE.get(n).setForeground(new Color(60, 65, 72));
        btns_SSE.get(n).setToolTipText("");

        btns_SSW.get(n).setEnabled(false);
        btns_SSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        btns_SSW.get(n).setForeground(new Color(60, 65, 72));
        btns_SSW.get(n).setToolTipText("");
    }//end disableDirectionButtons()


    // enable direction button corresponding to Direction d
    private void enableDirection(Direction d) {
        String n = currPlayer.name;

        if      (d.toString().equals("NORTH")) {
            btns_N.get(n).setEnabled(true);
            btns_N.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_N.get(n).setForeground(Color.WHITE);
            btns_N.get(n).setToolTipText("NORTH TO " + d.destinationName());
        }
        else if (d.toString().equals("SOUTH")) {
            btns_S.get(n).setEnabled(true);
            btns_S.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_S.get(n).setForeground(Color.WHITE);
            btns_S.get(n).setToolTipText("SOUTH TO " + d.destinationName());
        }
        else if (d.toString().equals("EAST")) {
            btns_E.get(n).setEnabled(true);
            btns_E.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_E.get(n).setForeground(Color.WHITE);
            btns_E.get(n).setToolTipText("EAST TO " + d.destinationName());
        }
        else if (d.toString().equals("WEST")) {
            btns_W.get(n).setEnabled(true);
            btns_W.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_W.get(n).setForeground(Color.WHITE);
            btns_W.get(n).setToolTipText("WEST TO " + d.destinationName());
        }
        else if (d.toString().equals("UP")) {
            btns_U.get(n).setEnabled(true);
            btns_U.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_U.get(n).setForeground(Color.WHITE);
            btns_U.get(n).setToolTipText("UP TO " + d.destinationName());
        }
        else if (d.toString().equals("DOWN")) {
            btns_D.get(n).setEnabled(true);
            btns_D.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_D.get(n).setForeground(Color.WHITE);
            btns_D.get(n).setToolTipText("DOWN TO " + d.destinationName());
        }
        else if (d.toString().equals("NORTHEAST")) {
            btns_NE.get(n).setEnabled(true);
            btns_NE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NE.get(n).setForeground(Color.WHITE);
            btns_NE.get(n).setToolTipText("NORTHEAST TO " + d.destinationName());
        }
        else if (d.toString().equals("NORTHWEST")) {
            btns_NW.get(n).setEnabled(true);
            btns_NW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NW.get(n).setForeground(Color.WHITE);
            btns_NW.get(n).setToolTipText("NORTHWEST TO " + d.destinationName());
        }
        else if (d.toString().equals("SOUTHEAST")) {
            btns_SE.get(n).setEnabled(true);
            btns_SE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SE.get(n).setForeground(Color.WHITE);
            btns_SE.get(n).setToolTipText("SOUTHEAST TO " + d.destinationName());
        }
        else if (d.toString().equals("SOUTHWEST")) {
            btns_SW.get(n).setEnabled(true);
            btns_SW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SW.get(n).setForeground(Color.WHITE);
            btns_SW.get(n).setToolTipText("SOUTHWEST TO " + d.destinationName());
        }
        else if (d.toString().equals("NORTH-NORTHEAST")) {
            btns_NNE.get(n).setEnabled(true);
            btns_NNE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NNE.get(n).setForeground(Color.WHITE);
            btns_NNE.get(n).setToolTipText("NORTH-NORTHEAST TO " + d.destinationName());
        }
        else if (d.toString().equals("NORTH-NORTHWEST")) {
            btns_NNW.get(n).setEnabled(true);
            btns_NNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_NNW.get(n).setForeground(Color.WHITE);
            btns_NNW.get(n).setToolTipText("NORTH-NORTHWEST TO " + d.destinationName());
        }
        else if (d.toString().equals("EAST-NORTHEAST")) {
            btns_ENE.get(n).setEnabled(true);
            btns_ENE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_ENE.get(n).setForeground(Color.WHITE);
            btns_ENE.get(n).setToolTipText("EAST-NORTHEAST TO " + d.destinationName());
        }
        else if (d.toString().equals("WEST-NORTHWEST")) {
            btns_WNW.get(n).setEnabled(true);
            btns_WNW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_WNW.get(n).setForeground(Color.WHITE);
            btns_WNW.get(n).setToolTipText("WEST-NORTHWEST TO " + d.destinationName());
        }
        else if (d.toString().equals("EAST-SOUTHEAST")) {
            btns_ESE.get(n).setEnabled(true);
            btns_ESE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_ESE.get(n).setForeground(Color.WHITE);
            btns_ESE.get(n).setToolTipText("EAST-SOUTHEAST TO " + d.destinationName());
        }
        else if (d.toString().equals("WEST-SOUTHWEST")) {
            btns_WSW.get(n).setEnabled(true);
            btns_WSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_WSW.get(n).setForeground(Color.WHITE);
            btns_WSW.get(n).setToolTipText("WEST-SOUTHWEST TO " + d.destinationName());
        }
        else if (d.toString().equals("SOUTH-SOUTHEAST")) {
            btns_SSE.get(n).setEnabled(true);
            btns_SSE.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SSE.get(n).setForeground(Color.WHITE);
            btns_SSE.get(n).setToolTipText("SOUTH-SOUTHEAST TO " + d.destinationName());
        }
        else if (d.toString().equals("SOUTH-SOUTHWEST")) {
            btns_SSW.get(n).setEnabled(true);
            btns_SSW.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns_SSW.get(n).setForeground(Color.WHITE);
            btns_SSW.get(n).setToolTipText("SOUTH-SOUTHWEST TO " + d.destinationName());
        }
    }//end enableDirectionButton()


    private void updateStats() {
        String n = currPlayer.name;
        attackStats.get(n).setText(String.valueOf(currPlayer.attack));
        defenseStats.get(n).setText(String.valueOf(currPlayer.defense));
        hpStats.get(n).setText(currPlayer.currHP + "/" + currPlayer.maxHP);
        mpStats.get(n).setText(String.valueOf(currPlayer.mp));
    }//end updateStats()


    // populate get menu
    private void populateGetMenu() {
        String n                    = currPlayer.name;
        List<Artifact>    artifacts = currPlayer.currPlace.placeArtifacts;
        ArrayList<String> names     = new ArrayList<String>();
        names.add("GET");

        for (Artifact a : artifacts)
            names.add("■ " + a.name().toUpperCase());

        getMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));

        if (getMenus.get(n).getItemCount() > 1) {
            getMenus.get(n).setEnabled(true);
            getMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            getMenus.get(n).setToolTipText("GET ARTIFACT");
        }
        else {
            getMenus.get(n).setEnabled(false);
            getMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            getMenus.get(n).setToolTipText("");
        }
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

        if (dropMenus.get(n).getItemCount() > 1) {
            dropMenus.get(n).setEnabled(true);
            dropMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            dropMenus.get(n).setToolTipText("DROP ARTIFACT");
        }
        else {
            dropMenus.get(n).setEnabled(false);
            dropMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            dropMenus.get(n).setToolTipText("");
        }
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

        if (useMenus.get(n).getItemCount() > 1) {
            useMenus.get(n).setEnabled(true);
            useMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            useMenus.get(n).setToolTipText("USE KEY");
        }
        else {
            useMenus.get(n).setEnabled(false);
            useMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            useMenus.get(n).setToolTipText("");
        }
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

        if (equipMenus.get(n).getItemCount() > 1) {
            equipMenus.get(n).setEnabled(true);
            equipMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            equipMenus.get(n).setToolTipText("EQUIP ARMOR OR WEAPON");
        }
        else {
            equipMenus.get(n).setEnabled(false);
            equipMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            equipMenus.get(n).setToolTipText("");
        }
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

        if (consumeMenus.get(n).getItemCount() > 1) {
            consumeMenus.get(n).setEnabled(true);
            consumeMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            consumeMenus.get(n).setToolTipText("CONSUME FOOD");
        }
        else {
            consumeMenus.get(n).setEnabled(false);
            consumeMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            consumeMenus.get(n).setToolTipText("");
        }
    }//end populateConsumeMenu()


    // populate consume menu
    private void populateBuyMenu() {
        String n                = currPlayer.name;
        ArrayList<String> names = new ArrayList<String>();
        names.add("BUY");

        for (int i = 1; i < 3; i++)
            names.add("■ " + i);

        buyMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));

        if (currPlayer.currPlace instanceof Market) {
            buyMenus.get(n).setEnabled(true);
            buyMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buyMenus.get(n).setToolTipText("BUY MARKET PRIZE");
        }
        else {
            buyMenus.get(n).setEnabled(false);
            buyMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            buyMenus.get(n).setToolTipText("");
        }
    }//end populateConsumeMenu()


    // populate attack menu
    public void populateAttackMenu() {
        String n                     = currPlayer.name;
        List<Character>   characters = currPlayer.currPlace.placeCharacters;
        ArrayList<String> names      = new ArrayList<String>();
        names.add("ATTACK");

        for (Character c : characters)
            if (!n.equalsIgnoreCase(c.name()))
                names.add("■ " + c.name().toUpperCase());

        attackMenus.get(n).setModel(new DefaultComboBoxModel(names.toArray()));

        if (attackMenus.get(n).getItemCount() > 1) {
            attackMenus.get(n).setEnabled(true);
            attackMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            attackMenus.get(n).setToolTipText("ATTACK CHARACTER");
        }
        else {
            attackMenus.get(n).setEnabled(false);
            attackMenus.get(n).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            attackMenus.get(n).setToolTipText("");
        }
    }


    // populate all menus
    private void populateAllMenus() {
        populateGetMenu();
        populateDropMenu();
        populateUseMenu();
        populateEquipMenu();
        populateConsumeMenu();
        populateBuyMenu();
        populateAttackMenu();
    }//end populateAllMenus()


    // check zone + flash window if danger zone
    private void checkZones() {
        if (currPlayer.currPlace instanceof DangerZone) {
            try { Thread.sleep(500); } catch (Exception e) { }
            frame.setBackground(new Color(128, 20, 21));
            try { Thread.sleep(150); } catch (Exception e) { }
            frame.setBackground(new Color(54, 57, 63));
            try { Thread.sleep(120); } catch (Exception e) { }
            frame.setBackground(new Color(128, 20, 21));
            try { Thread.sleep(150); } catch (Exception e) { }
            frame.setBackground(new Color(54, 57, 63));
        }
        else if (currPlayer.currPlace instanceof SafeZone) {
            try { Thread.sleep(250); } catch (Exception e) { }
            frame.setBackground(new Color(59, 128, 39));
            try { Thread.sleep(150); } catch (Exception e) { }
            frame.setBackground(new Color(54, 57, 63));
            try { Thread.sleep(120); } catch (Exception e) { }
            frame.setBackground(new Color(59, 128, 39));
            try { Thread.sleep(150); } catch (Exception e) { }
            frame.setBackground(new Color(54, 57, 63));
        }
    }//end checkZones()


    // switch card
    public void switchCard(Player p, Place place) {
        JPanel cards    = (JPanel) this.getContentPane().getComponent(0);
        CardLayout card = (CardLayout) cards.getLayout();
        currPlayer      = p;
        message         = messages.get(currPlayer.name);

        disableAllDirections();

        List<Direction> directions = place.paths;
        for (Direction d : directions)
            enableDirection(d);

        updateStats();
        populateAllMenus();

        card.show(cards, currPlayer.name);
        cards.revalidate();
        cards.repaint();
        this.revalidate();
        this.repaint();

        checkZones();
    }//end switchCard()
}//end GUI_2 class
