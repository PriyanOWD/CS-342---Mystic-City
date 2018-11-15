import java.util.Scanner;

public class Bruiser extends Player{

    private double attackMultiplier = 1.5;
    private double defenseMultiplier = 1.5;
    private double healthMultiplier = 0.5;

    //creates Bruiser object
    public Bruiser(Scanner scn, int ver, int num){
        super(scn, ver, num);
        attackMultiplier = 1.5;
        defenseMultiplier = 1.5;
        healthMultiplier = 0.5;
    }

    //getters for the multipliers
    public double getAttackM() {
        return attackMultiplier;
    }
    public double getDefenseM() {
        return defenseMultiplier;
    }
    public double getHealthM() {
        return healthMultiplier;
    }
}