/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
 */

import java.util.Scanner;


// Place class to encapsulate attributes and methods for Artifact objects
public class Artifact {
    // private attributes
    private int    ID;          // artifact ID
    private String name;        // artifact name
    private String description; // artifact description
    private int    value;       // artifact value
    private int    mobility;    // artifact weight
    private int    keyPattern;  // artifact key pattern (0 if non-key)


    // constructor for Artifact class
    public Artifact(Scanner sc, int ver) {
        if (ver < 3) return;                                 // unsupported ver
        try {
            Scanner s   = new Scanner(CleanLineScanner.getCleanLine(sc));
            int source  = s.nextInt();                       // get source ID
            s.close();                                       // close scanner

            s           = new Scanner(CleanLineScanner.getCleanLine(sc));
            ID          = s.nextInt();                       // set ID
            value       = s.nextInt();                       // set value
            mobility    = s.nextInt();                       // set weight
            keyPattern  = s.nextInt();                       // set key pattern
            name        = CleanLineScanner.getCleanLine(s);  // set name
            s.close();                                       // close scanner

            s           = new Scanner(CleanLineScanner.getCleanLine(sc));
            int num     = s.nextInt();                       // get # of lines
            s.close();                                       // close scanner

            description = "";
            for (int i = 0; i < num; i++) {                  // set desc :
                if (i < num - 1)                             //   preceding line
                    description += CleanLineScanner.getCleanLine(sc) + "\n";
                else                                         //   last line
                    description += CleanLineScanner.getCleanLine(sc);
            }//end for...

            if (ver < 4 || source > 0)                       // source > 0 :
                Place.getPlacebyID(source).addArtifact(this);//   given  place
            else if (source == 0)                            // source = 0 :
                Place.getRandomPlace().addArtifact(this);    //   random place
            else {                                           // source < 0 :
                Character c = Character.getCharacterbyID(-source);
                c.addArtifact(this);                         //   character
            }
        } catch (Exception e) { e.printStackTrace(); }       // exception
    }//end class constructor


    // return artifact ID
    public int     ID()          { return ID;           }
    // return artifact name
    public String  name()        { return name;         }
    // return artifact description
    public String  description() { return description;  }
    // return artifact value
    public int     value()       { return value;        }
    // return artifact weight
    public int     weight()      { return mobility;     }
    // return artifact key pattern
    private int    pattern()     { return keyPattern;   }
    // return true if artifact is movable
    public boolean isMovable()   { return weight() > 0; }


    // return true if passed string exactly matches stored artifact
    public boolean match(String str) {
        if (isMovable())                             // movable artifact :
            return str.equals(name().toUpperCase()); //   return match

        return false;                                // no match
    }//end match()


    // return true if passed string matches tokenized stored artifact
    public boolean matchTokens(String str) {
        if (isMovable()) {                        // movable artifact :
                                                  //   tokenize name
            String[] tokens = name().toUpperCase().split("\\s+");
            for (String n : tokens)               //   iterate thru tokens :
                if (str.matches(".*" + n + ".*")) //     match found :
                    return true;                  //       return true

            return false;                         //   no match
        }//end if...
        return false;                             // immovable artifact
    }//end matchTokens()


    // return true if artifact's key pattern matches direction's lock pattern
    public boolean matchKey(int lockPattern) {
        if (pattern() > 0)                   // key artifact :
            return lockPattern == pattern(); //   return match

        return false;                        // non-key artifact
    }//end matchKey()


    // Shyam's use artifact
//    public void use(Place currPlace) {
//        if (pattern() > 0) {                            // key artifact :
//            if (!currPlace.useKey(this)) {              //   can't use :
//                if (currPlace.name().matches("Room.*")) //     place is room
//                    System.out.printf("Sorry, there are no locked doors " +
//                                      "in %s.\n",     currPlace.name());
//                else                                    //     place isn't room
//                    System.out.printf("Sorry, there are no locked doors " +
//                                      "in the %s.\n", currPlace.name());
//            }//end if...
//        }//end if...                                    // non-key artifact :
//        else System.out.printf("Sorry, the %s is not a key.\n",
//                               name().toLowerCase());
//    }//end use()
    
    //Priyan's Use
    
    public void use(Character charVar, Place charPlaceVar)
    {
        if (keyPattern <= 0)
            System.out.println("\nNot a key\n");
//        else if (A_COMBINATION == 0)
//            System.out.println("\nNot a key\n");
//        else  (A_COMBINATION > 0)
//            charPlaceVar.useKey(this);
//        else
//        {
//        }
        else
            charPlaceVar.useKey(this);
        return;
    }


    // display artifact information for use in "LOOK" command
    public void display() {
        String vowels = "AEIOU";
        if      (name().endsWith("s"))                   // plural
            UI.printFormat(String.format("You\'ve come across %s.\n%s",
                           name().toLowerCase(), description()));
        else if (vowels.indexOf(name().charAt(0)) != -1) // vowel
            UI.printFormat(String.format("You\'ve come across an %s.\n%s",
                           name().toLowerCase(), description()));
        else                                             // consonant
            UI.printFormat(String.format("You\'ve come across a %s.\n%s",
                           name().toLowerCase(), description()));
    }//end display()


    // print artifact information for use in "GET" and "INVE" commands
    public void inventory(int count) {
        UI.printFormat(String.format(" \n%2d. %s\n    Value:   %d\n    " +
                       "Weight:  %d kg\n%s", count, name(), value(), weight(),
                       description().replaceAll("(?m)^", "    ")));
    }//end inventory()


    // debugging : print artifact information
    public void print() {
        if (keyPattern == 0) // ID, name, value, weight + desc
            UI.printFormat(String.format("A%-5d%s, value %d, mobility %d\n%s",
                           ID(), name(), value(), weight(),
                           description().replaceAll("(?m)^", "      ")));
        else                 // +key pattern
            UI.printFormat(String.format("A%-5d%s (%d), value %d, " +
                           "mobility %d\n%s",
                           ID(), name(), pattern(), value(), weight(),
                           description().replaceAll("(?m)^", "      ")));
    }//end print()
    
    public boolean Master()
    {
        return keyPattern == -1;
    }
    
    public boolean Match(Integer toMatch)
    {
        return keyPattern == toMatch;
    }
}//end Artifact class
