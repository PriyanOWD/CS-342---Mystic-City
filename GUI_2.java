/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private String                           line;
    private JTextArea                        messageBox;
    private final TreeMap<String, JTextArea> messages;
    private Player                           currPlayer;


    // lock
    public static final Object syncLock = new Object();


    // constructor for GUI_2 class
    public GUI_2() {
        Set<Map.Entry<Integer,Character>> characters
                 = Character.characterTree.entrySet();
        messages = new TreeMap<String, JTextArea>();

        try {
            Image img = ImageIO.read(new File("TEST.png")).getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
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

                    JLabel attack = new JLabel();
                    attack.setBounds(50, 72, 80, 20);
                    attack.setForeground(new Color(220, 221, 222));
                    //attack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    attack.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    attack.setHorizontalAlignment(SwingConstants.RIGHT);
                    attack.setText("Attack:");

                    JLabel attackStat = new JLabel() {
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(String.valueOf(currPlayer.attack), 4, 16);
                        }
                    };
                    attackStat.setBounds(140, 72, 64, 20);
                    attackStat.setForeground(new Color(220, 221, 222));
                    //attackStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    attackStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    JLabel defense = new JLabel();
                    defense.setBounds(50, 92, 80, 20);
                    defense.setForeground(new Color(220, 221, 222));
                    //defense.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    defense.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    defense.setHorizontalAlignment(SwingConstants.RIGHT);
                    defense.setText("Defense:");

                    JLabel defenseStat = new JLabel() {
                        Player p = (Player) c.getValue();
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(String.valueOf(p.defense), 4, 16);
                        }
                    };
                    defenseStat.setBounds(140, 92, 64, 20);
                    defenseStat.setForeground(new Color(220, 221, 222));
                    //defenseStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    defenseStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    JLabel hp = new JLabel();
                    hp.setBounds(50, 112, 80, 20);
                    hp.setForeground(new Color(220, 221, 222));
                    //hp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    hp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    hp.setHorizontalAlignment(SwingConstants.RIGHT);
                    hp.setText("HP:");

                    JLabel hpStat = new JLabel() {
                        Player p = (Player) c.getValue();
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(p.currHP + "/" +p.maxHP, 4, 16);
                        }
                    };
                    hpStat.setBounds(140, 112, 64, 20);
                    hpStat.setForeground(new Color(220, 221, 222));
                    //hpStat.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    hpStat.setFont(new Font("Helvetica Neue", Font.PLAIN, 17));

                    JLabel mp = new JLabel();
                    mp.setBounds(50, 132, 80, 20);
                    mp.setForeground(new Color(220, 221, 222));
                    //mp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    mp.setFont(new Font("Helvetica Neue", Font.BOLD, 17));
                    mp.setHorizontalAlignment(SwingConstants.RIGHT);
                    mp.setText("MP:");

                    JLabel mpStat = new JLabel() {
                        Player p = (Player) c.getValue();
                        @Override public void paintComponent(Graphics g) {
                            g.drawString(String.valueOf(p.mp), 4, 16);
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
                    messages.put(c.getValue().name, messageBox);

                    JTextField textBox = new JTextField();
                    textBox.setBounds(366, 705, 804, 50);
                    textBox.setBackground(new Color(72, 75, 81));
                    textBox.setForeground(new Color(200, 201, 203));
                    textBox.setFont(new Font("Helvetica Neue", Font.PLAIN, 32));
                    textBox.setBorder(BorderFactory.createEmptyBorder());
                    textBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            line = textBox.getText();
                            textBox.setText("");
                            synchronized(syncLock) { syncLock.notifyAll(); }
                        }
                    });

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


    // switch card
    public void switchCard(String name, Place place) {
        JPanel cards = (JPanel) this.getContentPane().getComponent(0);
        CardLayout card = (CardLayout) cards.getLayout();
        card.show(cards, name);

        for (Component c : cards.getComponents())
            messageBox = messages.get(name);

        cards.revalidate();
        cards.repaint();
        this.revalidate();
        this.repaint();
    }//end switchCard()
}//end GUI_2 class
