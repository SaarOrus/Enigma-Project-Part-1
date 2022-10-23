package userInterface;

import Utilies.LogicUtilities;
import components.Keyboard;
import dtoHandling.CodeConfiguration;
import repository.RepositoryOfComponents;

import java.util.HashMap;
import java.util.Scanner;

public class UI {

    public static String getFullPathOfXMLFileToLoad() {
        String userInput;
        do {
            System.out.println("Please enter full path of XML file which you wish to load. ");
            System.out.println("(Press ENTER to go back to the menu)\n");
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
        } while (!CheckUI.checkXMLFilePath(userInput));

        return userInput;
    }
    public static void notReadXml() {
        System.out.println(" ***** You must enter file path first (option 1 in menu)! *****\n");
    }
    public static void presentManuallyCode(String currentCodeString){
        System.out.println("The machine initialized succesfully");
        System.out.println("The code is: \n" + currentCodeString);
    }
    public static void presentAutomaticCode(String currentCodeString){
        System.out.println("The automatic code is: \n" + currentCodeString);
    }
    public static void presentWelcomeMessage(){
        System.out.println("\nWELCOME TO THE ENIGMA MACHINE !!!\n");
    }
    public static void presentFileDidntReadMessage(){
        System.out.println("You moved to the menu, your file right now is your previous valid file.");
    }
    public static void MachineIsNotInitialize() {
        System.out.println(" ***** You must enter initialize machine code first (option 3 or 4 in menu)! *****\n");
    }
    public static void presentGoodbyeMessage(){
        System.out.println("Bye, See You Later :) ");
    }
    public static int getUserChoice() {
        String userInput;

        do {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
        } while (!CheckUI.checkUserChoiceFromMenu(userInput));

        return Integer.parseInt(userInput);
    }
    public static void fileReadSuccessfullyMessage() {
        System.out.println("The file loaded succesfully ! ! !");
    }
    public static void resetMessage(String initialCodeString) {
        String stringToPrint = "done reset to the machine's code . . .\n" +
                "The update code now is: " + initialCodeString;
        System.out.println(stringToPrint);
    }
    public static void displayingTheMachineSpecifications(RepositoryOfComponents repositoryOfComponents, CodeConfiguration initializeCode, CodeConfiguration currentCode, int numberOfEncoding) {
        System.out.println(presentPossibleAmountOfWheelsVSinUse(repositoryOfComponents));
        System.out.println(presentAmountOfReflectors(repositoryOfComponents));
        System.out.println(presentCurrentAmountOfMessagesProcessed(numberOfEncoding));
        System.out.println(presentDescriptionOfInitializeCodeConfiguration(initializeCode));
        System.out.println(presentDescriptionOfCurrentCodeConfiguration(currentCode));
    }
    public static StringBuilder presentDescriptionOfInitializeCodeConfiguration(CodeConfiguration initializeCode) {
        StringBuilder printString = new StringBuilder();
        printString.append("Description of initialize machine code configuration :");
        printString.append(initializeCode.toString());

        return printString;
    }
    public static StringBuilder presentDescriptionOfCurrentCodeConfiguration(CodeConfiguration currentCode) {
        StringBuilder printString = new StringBuilder();

        printString.append("Description of current code: ");
        printString.append(currentCode.toString());
        return printString;
    }
    public static StringBuilder presentCurrentAmountOfMessagesProcessed(int numberOfEncoding) {
        StringBuilder printString = new StringBuilder();
        printString.append("The amount of messages that have been processed: ");
        printString.append(numberOfEncoding);

        return printString;
    }
    public static StringBuilder presentPossibleAmountOfWheelsVSinUse(RepositoryOfComponents repositoryOfComponents) {
        StringBuilder printString = new StringBuilder();
        printString.append("Amount of rotors in use/Possible amount of rotors: ");
        printString.append(repositoryOfComponents.getNumberOfRotorsInUse());
        printString.append("/");
        printString.append(repositoryOfComponents.getRotors().length - 1);

        return printString;
    }
    public static StringBuilder presentAmountOfReflectors(RepositoryOfComponents repositoryOfComponents) {
        StringBuilder printString = new StringBuilder();
        printString.append("Total amount of reflectors: ");
        printString.append(repositoryOfComponents.getNumberOfReflectors());

        return printString;
    }
    public static String getStringToEncodeInTheMachine(Keyboard keyboard) {
        String userInput;

        do {
            StringBuilder stringToPrint=new StringBuilder();
            stringToPrint.append("Please enter string to encode in the Enigma Machine. ");
            stringToPrint.append("The ABC is: ").append(keyboard.toString()).append("\n");
            stringToPrint.append("(Press ENTER to go back to the menu)\n");
            System.out.println(stringToPrint);
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
        } while (!CheckUI.checkIfStringToEncodeIsTheLanguage(userInput,keyboard));

        return userInput.toUpperCase();
    }
    public static void presentEncodedString(String encodedString) {
        System.out.println("The encoded string is:\n" + encodedString + "\n");
    }
    public static int[] getNumbersOfRotors(int totalNumberOfRotors, int numberOfRotorsInUse) {
        String userInput;

        do {
            System.out.println("Please enter " +numberOfRotorsInUse+" rotors ID's. (rotors ID's options: 1 - " + totalNumberOfRotors+"). separated by a comma (for example: 1,2,3). \nEnter the numbers by the order of the rotors. \nPlease do not enter the same number twice\n");
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
        } while (!CheckUI.checkNumbersOfRotorsInput(userInput,totalNumberOfRotors,numberOfRotorsInUse));

        String[] arrOfRotorsNumbers = userInput.split(",");
        int[] rotorsNumbers = new int[arrOfRotorsNumbers.length];

        for (int i = 0; i < arrOfRotorsNumbers.length; i++) {
            rotorsNumbers[i] = Integer.parseInt(arrOfRotorsNumbers[i]);
        }

        return rotorsNumbers;
    }
    public static char[] getInitialPositionsOfRotors(int amountOfRotorsInRepositories, Keyboard keyboard) {
        String userInput;
        do {
            System.out.println("\nPlease enter " +amountOfRotorsInRepositories+" initial position for each rotor.\nA series of valid characters from the alphabet: (" +  keyboard.toString() + ") without separation between them.");
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
        } while (!CheckUI.checkInitialPositionsOfRotors(userInput, amountOfRotorsInRepositories,keyboard));

        char[] initialPositionsOfRotors = new char[userInput.length()];

        for (int i = 0; i < userInput.length(); i++) {
            initialPositionsOfRotors[i] = userInput.charAt(i);
            initialPositionsOfRotors[i] = Character.toUpperCase(initialPositionsOfRotors[i]);
        }

        return initialPositionsOfRotors;
    }
    public static String getReflector(int numberOfReflectors) {
        String reflectorNumber;
        do {
            System.out.println(presentReflectorOptions(numberOfReflectors));
            Scanner sc = new Scanner(System.in);
            reflectorNumber = sc.nextLine();
        } while (!CheckUI.checkReflectorNumber(reflectorNumber,numberOfReflectors));

        int intReflectorNumber = Integer.parseInt(reflectorNumber);

        return LogicUtilities.convertNumberToReflectorId(intReflectorNumber);
    }
    public static StringBuilder presentReflectorOptions(int numberOfReflectors) {
        StringBuilder strToPrint = new StringBuilder();
        strToPrint.append("Please enter chosen reflector number: \n");

        for (int i = 1; i <= numberOfReflectors; i++) {
            strToPrint.append(i).append(". ").append(LogicUtilities.convertNumberToReflectorId(i)).append("\n");
        }

        return strToPrint;
    }
    public static HashMap<Character, Character> getPlugsInUse(Keyboard keyboard) {
        HashMap<Character, Character> plugsInUse = new HashMap<>();
        String userInput;
        boolean isEmpty = false;

        do {
            System.out.println("Please enter a continuous string of characters from the alphabet :("+keyboard.toString()+") of all the pairs of plugs (without any separator).\nYou can enter at most " + keyboard.getNumberOfCharsInKeyboard()/2 +"\nPress ENTER to end input (or if you don't hava plugs in use).");
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            if (userInput.isEmpty()) {
                isEmpty = true;
                break;
            }

        } while (!CheckUI.checkPlugsInUse(userInput,keyboard));

        if (!isEmpty) {

            for (int i = 0; i < userInput.length(); i+=2) {
                plugsInUse.put(Character.toUpperCase(userInput.charAt(i)), Character.toUpperCase(userInput.charAt(i+1)));
            }
        }

        return plugsInUse;
    }
}
