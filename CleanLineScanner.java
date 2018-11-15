/* CS342 Term Project Part IV: Combination and Extension
 *
 * Name:   Shyam Patel
 * NetID:  spate54
 *
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 *
 * Name:   Priyan Sureshkumar
 * NetID:  psures5
 *
 * Date:   Nov 14, 2018
 */

import java.util.Scanner;
import java.util.regex.Pattern;


// CleanLineScanner class to parse thru and return clean lines
public final class CleanLineScanner {
    // return next clean line from scanner
    public static String getCleanLine(Scanner sc) {
        String str = "";                      // empty line

        while (str.length() == 0) {           // get next line until empty
            if (sc.hasNextLine()) {           //   has next line :
                str = sc.nextLine();          //     get next line

                if (str.matches(".*//.*"))    //     has comments :
                    str = str.split("//")[0]; //       remove comments

                str = str.trim();             //     trim whitespace
            }//end if...
        }//end loop

        return str;                           // return clean line
    }//end getCleanLine()
    
    public static String gameFileParser(Scanner gFP_scanner, Pattern objP)
    {
        gFP_scanner.useDelimiter(objP);

        String dataBlock = gFP_scanner.next();

        // System.out.println("BEFORE STR :" + dataBlock + "||||");
        dataBlock = dataBlock.replaceAll("(//.*)", "");
        dataBlock = dataBlock.replaceAll("(?m)^(\r?\n)", "");
        // System.out.println("\nAFTER STR :" + dataBlock + "||||");

        if (dataBlock.matches("^\\s*$"))
            return gameFileParser(gFP_scanner, objP);

        gFP_scanner.reset();
        while (gFP_scanner.hasNext(Pattern.compile("//")))
        {
            // System.out.print("\nHERE************************ START->");
            gFP_scanner.nextLine();
            // System.out.println("<-HERE ***************************END");
        }
        // System.out.println("END CLEANUP");
        return dataBlock;
    }
}//end CleanLineScanner class
