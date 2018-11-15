/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
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
            System.out.printf("You've reached the exit. Thanks for playing!\n");
            return true; // reached
        }
        return false;    // not reached
    }


    // display current place for use in "LOOK" command
    public void look(int newlines) {
        for (int i = 0; i < newlines; i++) // # of newlines :
            System.out.printf("\n");       //   print newline

        currPlace.display(this);           // display current place
    }//end look();


    // display player's possessions (collection of artifacts) for use in
    // "INVE" and "GET" commands
    public void inve()
    {
        System.out.printf("\n");
        UI.printHeader(String.format("%s's Inventory", name));

        if      (inventory.size() == 0)  // player doesn't possess artifacts
            UI.printFormat(String.format("%s, you currently possess no " +
                            "artifacts.\nUse the \'GET\' and \'DROP\' "   +
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

    public void eat(Artifact art)
    {
        if (art instanceof Food)
        {
            Food f = (Food) art;

        }
    }

    // Equips an equippable object to the player if the object passed in is
    // equippable. Else nothing happens. Turn should not be used.
    public void equip(Artifact art)
    {
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
                        break;
            }
        }

        else System.out.printf("This artifact cannot be equipped.\n");
    }//end equip
}//end Player class

