package club.theexperiment.diex;

import android.net.Uri;

import java.util.Random;

public abstract class Die {
    //Declare variables
    private int sides;
    private int numberOfDice;
    private Random rand = new Random();
    private int total;
    private int firstRoll;
    private int[] rolls;
    private Uri[] uriList;
    //Create constructor
    public Die() {
        sides = 0;
        numberOfDice = 0;
    }


    //Getters and Setters

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public int getTotal() {return total;}

    public int[] getRolls() {return rolls;}

    public int getFirstRoll() {return firstRoll;}

    public void setUriList(Uri[] uriList) {this.uriList = uriList;}

    public Uri getUri(int roll) {return uriList[roll-1];}

    //Generate array of random ints when rolled

    public void roll(int r) {
        boolean cont = true;
        rolls = new int[this.sides];
        for (int x : rolls) {
            rolls[x] = 0;
        }

        for (int i = 0; i < r; i++) {
            int n = rand.nextInt(this.sides) + 1;
            if(cont){
                firstRoll = n;
                cont = false;
            }
            total += n;
            rolls[n - 1]++;
        }
    }
    public void roll() {
        boolean cont = true;
        rolls = new int[this.sides];

        for(int x : rolls){
            rolls[x] = 0;
        }

        for (int i = 0; i < this.getNumberOfDice(); i++) {
            int n = rand.nextInt(this.sides) + 1;
            if(cont){
                firstRoll = n;
                cont = false;
            }
            total += n;
            rolls[n-1]++;
        }
    }
}
