package dtoHandling;

import java.util.HashMap;

public class DeliveryInformation {
    private int[] rotorsNumbersID;
    private char[] initialPositionsOfRotors;
    private String reflectorGreekNumber;
    private HashMap<Character,Character> plugsInUse;

    //ctor
    public DeliveryInformation(){}
    public DeliveryInformation(DeliveryInformation deliveryInformation) {
        this.plugsInUse=new HashMap<>(deliveryInformation.plugsInUse);
        this.reflectorGreekNumber= deliveryInformation.reflectorGreekNumber;
        this.rotorsNumbersID =new int[deliveryInformation.rotorsNumbersID.length];
        this.initialPositionsOfRotors=new char[deliveryInformation.initialPositionsOfRotors.length];

        for (int i = 0; i < rotorsNumbersID.length; i++) {
            rotorsNumbersID[i]=deliveryInformation.rotorsNumbersID[i];
        }

        for (int i = 0; i < initialPositionsOfRotors.length; i++) {
            initialPositionsOfRotors[i]=deliveryInformation.initialPositionsOfRotors[i];
        }
    }
    //get functions
    public int[] getRotorsNumbersID(){
        return rotorsNumbersID;
    }
    public int getLengthOfRotorsNumber(){
        return rotorsNumbersID.length;
    }
    public char[] getInitialPositionsOfRotors() {
        return initialPositionsOfRotors;
    }
    public String getReflectorGreekNumber() {
        return reflectorGreekNumber;
    }
    public HashMap<Character, Character> getPlugsInUse() {
        return plugsInUse;
    }
    //set functions
    public void setInitialPositionsOfRotors(char[] initialPositionsOfRotors){
        this.initialPositionsOfRotors=initialPositionsOfRotors;
    }
    public void setReflectorGreekNumber(String reflectorGreekNumber){
        this.reflectorGreekNumber=reflectorGreekNumber;
    }
    public void setPlugsInUse(HashMap<Character,Character> plugsInUse){
        this.plugsInUse=plugsInUse;
    }
    public void setRotorsNumbersID(int[] rotorsNumbersID) {
        this.rotorsNumbersID = rotorsNumbersID;
    }
}
