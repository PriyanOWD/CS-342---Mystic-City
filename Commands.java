/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Nov 14, 2018
 */

// Commands class that inherits Move abstract class and, in its execution,
// is used to display list of commands that can be used in the game
public class Commands extends Move {
    // execute "COMMANDS" command : display all commands used in the game
    public boolean execute() {
        System.out.printf("\n");    // newline
        UI.printHeader("COMMANDS"); // print commands header
        UI.printFormat("                 \'QUIT\' : quit game\n"             +
                       "                 \'LOOK\' : display current place\n" +
                       "              \'INSPECT\' : Check FootPrints\n"      +
                       "       \'GET <ARTIFACT>\' : get artifact\n"          +
                       "      \'DROP <ARTIFACT>\' : drop artifact\n"         +
                       "       \'USE <ARTIFACT>\' : use key\n"               +
                       "     \'EQUIP <ARTIFACT>\' : equip armor or weapon\n" +
                       "   \'CONSUME <ARTIFACT>\' : consume food or potion\n"+
                       "   \'BUY <NUMBER [1,5]>\' : win a prize\n"           +
                       "              \'INSPECT\' : display footprints\n"    +
                       "                 \'INVE\' : check inventory\n"       +
                       "       \'GO <DIRECTION>\' : go to direction "        +
                       "(e.g., N, S, E, W, U, D)");
        UI.printDivider(2);         // print divider
        return false;
    }//end execute()
}//end Commands class
