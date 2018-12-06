/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Dec 6, 2018
 */

import java.util.ArrayList;
import java.util.Scanner;


// Player subclass inherits from Character class and encapsulates
// attributes and methods for Player objects
public class Player extends Character
{
    protected int playerNum;
    protected Armor headpiece;
    protected Armor upperbody;
    protected Armor lowerbody;
    protected Armor shoes;
    protected Armor accessory;
    protected Weapon primary;
    protected Weapon offhand;

    // constructor for Player class
    public Player(Scanner sc, int version, int num)
    {
        super(sc, version);   // call superclass primary constructor
        dm        = new UI(); // allocate UI decision maker
        playerNum = num;      // set player number
        headpiece = null;
        upperbody = null;
        lowerbody = null;
        shoes     = null;
        accessory = null;
        primary   = null;
        offhand   = null;
    }


    // overloaded constructor for Player class to support pre-4.0
    // backward compatibility
    public Player(int _id, String _name, String _desc, int _num)
    {
        super(_id, _name, _desc); // call superclass overloaded constructor
        dm        = new UI();     // allocate UI decision maker
        playerNum = _num;         // set player number
        headpiece = null;
        upperbody = null;
        lowerbody = null;
        shoes     = null;
        accessory = null;
        primary   = null;
        offhand   = null;
    }


    // return player #
    public int playerNum() { return playerNum; }


    // get next move from player and execute it, returning false for all
    // move executions except for "GO" (success returns true)
    public boolean makeMove()
    {
        Move m = dm.getMove(this); // get move
        return m.execute();        // execute move
    }//end makeMove()


    // check if player has valid artifact corresponding to passed string and,
    // if so, remove and return it for use in "DROP" and "USE" commands
    public ArrayList<Artifact> followArtifact(String str)
    {
        ArrayList<Artifact> matches = new ArrayList<Artifact>();

        // iterate thru Place object's collection of artifacts
        for (Artifact a : inventory) {
            if (a.match(str)) {     // exact match found :
                matches.add(a);     //   add it to list of matches,
                return matches;     //   and return it
            }
        }

        // iterate thru Place object's collection of artifacts
        for (Artifact a : inventory) {
            if (a.matchTokens(str)) // potential match found :
                matches.add(a);     //   add it to list of matches
        }

        return matches;             // return all matches
    }


    // check if player has reached exit for use in play() method's "GO" command
    public boolean reachedExit()
    {
        if (currPlace == Place.getPlacebyID(1)) {
            IO printIO = IO.getIO();
            printIO.display("You've reached the exit. Thanks for playing!\n");
            try { Thread.sleep(2000); } catch (Exception e) { }
            return true; // reached
        }
        return false;    // not reached
    }


    // display current place for use in "LOOK" command
    public void look(int newlines) {
        IO printIO = IO.getIO();
        for (int i = 0; i < newlines; i++) // # of newlines :
            printIO.display("\n");         //   print newline

        currPlace.display(this);           // display current place
    }//end look();


    // display player's possessions (collection of artifacts) for use in
    // "INVE" and "GET" commands
    public void inve()
    {
        IO printIO = IO.getIO();
        printIO.display("\n");
        UI.printDivider(1);

        if      (inventory.size() == 0)  // player doesn't possess artifacts
            UI.printFormat(String.format("%s, you currently possess no " +
                            "artifacts.\nUse the \'GET\' and \'DROP\' "  +
                            "commands to pick up and drop artifacts.",
                    name.replace("The ", "").replace("A ", "")));
        else if (inventory.size() == 1)  // player possesses one artifact
            UI.printFormat(String.format("%s, you currently possess the " +
                            "following artifact.",
                    name.replace("The ", "").replace("A ", "")));
        else                             // player possesses multiple artifacts
            UI.printFormat(String.format("%s, you currently possess the " +
                            "following artifacts.",
                    name.replace("The ", "").replace("A ", "")));

        int count         = 0;           // # of artifacts
        int totalValue    = 0;           // total value
        int totalMobility = 0;           // total weight
        for (Artifact a : inventory) {   // iterate thru artifacts :
            totalValue    += a.value();  //   add to total value
            totalMobility += a.weight(); //   add to total weight
            a.inventory(++count);        //   display inventory info
        }

        if (count > 0)
            UI.printFormat(String.format(" \nTotal value: %d, Total weight: %d",
                           totalValue, totalMobility));
        UI.printDivider(2);
    }


    public void updateStats(int a, int d, int mh, int m, boolean isEquip)
    {
        if (isEquip)
        {
            attack  += a;
            defense += d;
            maxHP   += mh;
            currHP  += mh;
            mp      += m;
        }
        else
        {
            attack  -= a;
            defense -= d;
            maxHP   -= mh;
            if (currHP > maxHP)
                currHP = maxHP;
            mp      -= m;
        }
    }


    public String getStats()
    {
        StringBuilder stats = new StringBuilder();
        stats.append("Health:");
        stats.append(currHP);
        stats.append("/");
        stats.append(maxHP);
        stats.append("\n");
        stats.append("Attack:");
        stats.append(attack);
        stats.append("Defense:");
        stats.append(defense);
        stats.append("Magic Points:");
        stats.append(mp);
        return stats.toString();
    }


    public void consume(Artifact art)
    {
        IO printIO = IO.getIO();
        if (art instanceof Food && inventory.contains(art))
        {
            Food f = (Food) art;
            if (!f.getConsumed())
                updateStats(attack, defense, f.getMaxHP(), f.getMP(), true);
            f.setConsumed();
            inventory.remove(f);
            printIO.display(String.format("You\'ve consumed the %s.\n", art.name().toLowerCase()));
        }
        else printIO.display("Sorry, the artifact can\'t be consumed.\n");
    }

    // Equips an equippable object to the player if the object passed in is
    // equippable. Else nothing happens. Turn should not be used.
    public void equip(Artifact art)
    {
        IO printIO = IO.getIO();
        if (art instanceof Armor)
        {
            Armor a  = (Armor) art;
            int type = a.getType();

            switch (type) {
                // headpiece
                case 0: if (headpiece != null)
                        {   // remove headpiece armor
                            Armor r   = headpiece;
                            headpiece = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip headpiece armor
                        headpiece = a;
                        updateStats(a.getAtk(),   a.getDef(),
                                    a.getMaxHP(), a.getMP(),  true);
                        inventory.remove(a);
                        printIO.display(String.format("You\'ve equipped the %s.\n", a.name().toLowerCase()));
                        break;

                // upper-body
                case 1: if (headpiece != null)
                        {   // remove upper-body armor
                            Armor r   = upperbody;
                            upperbody = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip upper-body armor
                        upperbody = a;
                        updateStats(a.getAtk(),   a.getDef(),
                                    a.getMaxHP(), a.getMP(),  true);
                        inventory.remove(a);
                        printIO.display(String.format("You\'ve equipped the %s.\n", a.name().toLowerCase()));
                        break;

                // lower-body
                case 2: if (lowerbody != null)
                        {   // remove lower-body armor
                            Armor r   = lowerbody;
                            lowerbody = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip lower-body armor
                        lowerbody = a;
                        updateStats(a.getAtk(),   a.getDef(),
                                    a.getMaxHP(), a.getMP(),  true);
                        inventory.remove(a);
                        printIO.display(String.format("You\'ve equipped the %s.\n", a.name().toLowerCase()));
                        break;

                // shoes
                case 3: if (shoes != null)
                        {   // remove shoes armor
                            Armor r = shoes;
                            shoes   = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip shoes armor
                        shoes = a;
                        updateStats(a.getAtk(),   a.getDef(),
                                    a.getMaxHP(), a.getMP(),  true);
                        inventory.remove(a);
                        printIO.display(String.format("You\'ve equipped the %s.\n", a.name().toLowerCase()));
                        break;

                // accessory
                case 4: if (shoes != null)
                        {   // remove accessory armor
                            Armor r   = accessory;
                            accessory = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip accessory armor
                        accessory = a;
                        updateStats(a.getAtk(),   a.getDef(),
                                    a.getMaxHP(), a.getMP(),  true);
                        inventory.remove(a);
                        printIO.display(String.format("You\'ve equipped the %s.\n", a.name().toLowerCase()));
                        break;
            }
        }//end armor equip

        // check for weapon
        // equips weapon to the specified slot, updating the stats of the
        // player as required, then removes the item from the inventory
        else if (art instanceof Weapon)
        {
            Weapon w = (Weapon) art;
            int type = w.getType();

            switch (type) {
                // primary weapon
                case 0: if (primary != null)
                        {   // remove primary weapon
                            Weapon r = primary;
                            primary  = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip primary weapon
                        primary = w;
                        updateStats(w.getAtk(),   w.getDef(),
                                    w.getMaxHP(), w.getMP(),  true);
                        inventory.remove(w);
                        printIO.display(String.format("You\'ve equipped the %s.\n", w.name().toLowerCase()));
                        break;

                // offhand weapon
                case 1: if (primary != null)
                        {   // remove offhand weapon
                            Weapon r  = offhand;
                            offhand = null;
                            updateStats(r.getAtk(),   r.getDef(),
                                        r.getMaxHP(), r.getMP(),  false);
                            inventory.add(r);
                        }
                        // equip secondary weapon
                        offhand = w;
                        updateStats(w.getAtk(),   w.getDef(),
                                    w.getMaxHP(), w.getMP(),  true);
                        inventory.remove(w);
                        printIO.display(String.format("You\'ve equipped the %s.\n", w.name().toLowerCase()));
                        break;
            }
        }

        else printIO.display("Sorry, the artifact can\'t be equipped.\n");
    }//end equip
}//end Player class
