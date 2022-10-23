package Utilies;

import java.util.concurrent.ThreadLocalRandom;

public class LogicUtilities {

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int getRandomNumber(int min, int max) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        return tlr.nextInt(min, max + 1);
    }
    public static String convertNumberToReflectorId(int reflectorNumber) {
        String reflectorGreekNumber = "";

        switch (reflectorNumber) {
            case 1:
                reflectorGreekNumber = "I";
                break;
            case 2:
                reflectorGreekNumber = "II";
                break;
            case 3:
                reflectorGreekNumber = "III";
                break;
            case 4:
                reflectorGreekNumber = "IV";
                break;
            case 5:
                reflectorGreekNumber = "V";
                break;
        }
        return reflectorGreekNumber;
    }
    public static int convertInputOfReflectorNumber(String reflectorNumber) {
        int numberOfReflector;

        switch (reflectorNumber) {
            case "I":
                numberOfReflector = 1;
                break;
            case "II":
                numberOfReflector = 2;
                break;
            case "III":
                numberOfReflector = 3;
                break;
            case "IV":
                numberOfReflector = 4;
                break;
            case "V":
                numberOfReflector = 5;
                break;
            default:
                numberOfReflector = -1;

        }
        return numberOfReflector;
    }
    public static int[] reverseArrayInt(int[] arrayToReverse, int startIndex) {
        int[] reverseArray = new int[arrayToReverse.length];
        int j = arrayToReverse.length;

        for (int i = startIndex; i < arrayToReverse.length; i++) {
            reverseArray[j - 1] = arrayToReverse[i];
            j--;
        }
        return reverseArray;
    }
    public static char[] reverseArrayChar(char[] arrayToReverse, int startIndex) {
        char[] reverseArray = new char[arrayToReverse.length];
        int j = arrayToReverse.length;

        for (int i = startIndex; i < arrayToReverse.length; i++) {
            reverseArray[j - 1] = arrayToReverse[i];
            j--;
        }
        return reverseArray;
    }
}
