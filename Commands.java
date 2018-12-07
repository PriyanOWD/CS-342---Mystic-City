/* CS342 Term Project Part V: Wrap Up and Graphical User Interface
 * Name:   Shyam Patel
 * NetID:  spate54
 * Date:   Dec 6, 2018
 */


// Commands class that inherits Move abstract class and, in its execution,
// is used to display list of commands that can be used in the game
public class Commands extends Move {
    // execute "COMMANDS" command : display all commands used in the game
    public boolean execute() {
        IO printIO = IO.getIO();
        printIO.display("\n");    // newline
        UI.printDivider(1);       // print divider
        UI.printFormat("                 \'QUIT\' : quit game\n"             +
                       "                 \'LOOK\' : display current place\n" +
                       "              \'INSPECT\' : check footprints\n"      +
                       "       \'GET <ARTIFACT>\' : get artifact\n"          +
                       "      \'DROP <ARTIFACT>\' : drop artifact\n"         +
                       "       \'USE <ARTIFACT>\' : use key\n"               +
                       "     \'EQUIP <ARTIFACT>\' : equip armor or weapon\n" +
                       "   \'CONSUME <ARTIFACT>\' : consume food or potion\n"+
                       "          \'BUY <[0,1]>\' : win a prize\n"           +
                       "              \'INSPECT\' : display footprints\n"    +
                       "                 \'INVE\' : check inventory\n"       +
                       "                \'STATS\' : check stats\n"           +
                       "  \'ATTACK   <CHARACTER>\': attack player\n"         + 
                       "       \'GO <DIRECTION>\' : go to direction "        +
                       "(e.g., N, S, E, W, U, D)");
        UI.printDivider(2);       // print divider
        return false;
    }//end execute()
}//end Commands class
