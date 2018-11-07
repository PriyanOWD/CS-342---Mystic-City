/* CS342 Term Project Part III: Addition of Characters and Inheritance
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Oct 24, 2018
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
                       "       \'GET <ARTIFACT>\' : get artifact\n"          +
                       "      \'DROP <ARTIFACT>\' : drop artifact\n"         +
                       "       \'USE <ARTIFACT>\' : use key\n"               +
                       "                 \'INVE\' : check inventory\n"       +
                       "       \'GO <DIRECTION>\' : go to direction "        +
                       "(e.g., N, S, E, W, U, D)");
        UI.printDivider(2);         // print divider
        return false;
    }//end execute()
}//end Commands class
