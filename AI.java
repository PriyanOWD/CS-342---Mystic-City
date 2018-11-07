/* CS342 Term Project Part IV: Combination and Extension
 * Name:   Joey Voorhees
 * NetID:  svoorh2
 * Date:   Nov 14, 2018
 */

import java.util.Random;

public class AI implements DecisionMaker {
    Random rand = new Random();

    //randomizes the move choice, then decides what to do based on the random
    // value
    public Move getMove(Character c, Place p){
        int move = rand.nextInt(4) + 1;
        //c.display();
        //p.print();  //debugger statements
        switch(move){
            case 1: { //AI GO case
                int x = rand.nextInt(p.getDirection().size() - 1);
                Direction dir;
                if( x <= 0) { //if there is no way out do nothing
                    return new Move(Move.MoveType.NOTHING, "");
                }
                else{
                    dir = p.getDirection(x);
                }
                //if the direction leads to an exit room do nothing
                if(dir.getTo().getIndex() == 1 || dir.getTo().getIndex() == 0){
                    return new Move(Move.MoveType.NOTHING, "");
                }
                return new Move(Move.MoveType.GO, dir.getDirTypeText());
            }
            case 2: {//AI GET
                //checks if there are even artifacts to get, else do nothing
                if(p.getArtifactList().size() == 0){
                    return new Move(Move.MoveType.NOTHING, "");
                }
                //if more than one artifact, chooses one at random to get
                int x = rand.nextInt(p.getArtifactList().size());
                //--x;
                Artifact a = p.getArtifactbyIndex(x);
                return new Move(Move.MoveType.GET, a.name());
            }
            case 3:{//AI DROP
                //checks to see if anything is in the inventory
                if(c.getInventory().size() == 0){
                    return new Move(Move.MoveType.NOTHING, "");
                }
                //drops a random object from inventory
                int x = rand.nextInt(c.getInventory().size());
                //--x;
                Artifact a = c.getInventory().get(x);
                return new Move(Move.MoveType.DROP, a.name());
            }
            case 4: {//AI USE
                //checks for empty inventory
                if(c.getInventory().size() == 0){
                    return new Move(Move.MoveType.NOTHING, "");
                }
                //tries to use an object in the inventory at random
                int x = rand.nextInt(c.getInventory().size());
                Artifact a = c.getInventory().get(x);
                //--x;
                return new Move(Move.MoveType.USE, "");
            }
        }
        //default do nothing
    return new Move(Move.MoveType.NOTHING, "");
    }

}
