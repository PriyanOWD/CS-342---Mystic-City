import java.util.Scanner;

public class Tank extends Player{

    private double defenseMultiplier = 3.0;
    private double healthMultiplier = 2.0;

    //Creates Tank object
    public Tank(Scanner scn, int ver, int num){
        super(scn, ver, num);
        defenseMultiplier = 3.0;
        healthMultiplier = 2.0;
    }

    //Returns tank multipliers
    public double getDefenseM(){
        return defenseMultiplier;
    }
    public double getHealthM(){
        return healthMultiplier;
    }
}//end Tank