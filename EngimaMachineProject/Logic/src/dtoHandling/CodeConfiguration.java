package dtoHandling;

import Utilies.LogicUtilities;

import java.util.Map;

public class CodeConfiguration {
    private final DeliveryInformation dtoCode;
    private int[] notchLocationInRotor;

    //ctor
    public CodeConfiguration(DeliveryInformation dto, int[] notchLocationInRotor) {
        this.dtoCode=dto;
        this.notchLocationInRotor=reverseNotchLocationInRotor(notchLocationInRotor);
    }
    //get functions
    public DeliveryInformation getDtoCode() {
        return dtoCode;
    }
    //set functions
    public void setNotchLocationInRotor(int[] notchLocationInRotor) {
        this.notchLocationInRotor = notchLocationInRotor;
    }
    public void setInitialPositionsOfRotors(char[] initialPositionsOfRotors){
        this.dtoCode.setInitialPositionsOfRotors(initialPositionsOfRotors);
    }

    private int[] reverseNotchLocationInRotor(int[] notchLocationInRotor) {
        return LogicUtilities.reverseArrayInt(notchLocationInRotor,0);
    }
    @Override
    public String toString() {
        StringBuilder printString = new StringBuilder();

        printString.append("<"); ///rotorsNumber
        for (int i = 0; i < dtoCode.getLengthOfRotorsNumber(); i++) {
            printString.append(dtoCode.getRotorsNumbersID()[i]);
            printString.append("(").append(notchLocationInRotor[i]).append(")");
            if (i != dtoCode.getLengthOfRotorsNumber() - 1) {
                printString.append(",");
            }
        }
        printString.append(">");

        printString.append("<"); ///initial position
        for (int i = 0; i < dtoCode.getInitialPositionsOfRotors().length; i++) {
            printString.append(dtoCode.getInitialPositionsOfRotors()[i]);
        }
        printString.append(">");

        printString.append("<"); ///number of reflection
        printString.append(dtoCode.getReflectorGreekNumber());
        printString.append(">");

        if (dtoCode.getPlugsInUse().size() > 0) {
            printString.append("<"); ///plugs
            for (Map.Entry<Character, Character> entry : dtoCode.getPlugsInUse().entrySet()) {
                printString.append(entry.getKey()).append("|").append(entry.getValue());
                printString.append(",");
            }
            printString.delete(printString.length() - 1, printString.length());
            printString.append(">");
        }

        return String.valueOf(printString);
    }
}
