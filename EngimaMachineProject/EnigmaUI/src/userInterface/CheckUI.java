package userInterface;

import Utilies.LogicUtilities;
import components.Keyboard;
import test.CheckLogic;

public class CheckUI {
    private static CheckLogic checkLogic;

    public CheckUI() {
        checkLogic = new CheckLogic();
    }

    public static boolean checkAmountOfCommas(String checkedRotorsNumbers) {
        boolean isValid = true;

        for (int i = 0; i < checkedRotorsNumbers.length() - 1; i++) {
            if (checkedRotorsNumbers.charAt(i) == ',' && checkedRotorsNumbers.charAt(i + 1) == ',') {
                isValid = false;
                break;
            }
        }

        if (checkedRotorsNumbers.charAt(checkedRotorsNumbers.length() - 1) == ',') {
            isValid = false;
        }

        return isValid;
    }
    public static boolean checkIfInputIsNumbers(String[] arrOfRotorsNumbers) {
        boolean isValid = true;

        for (int i = 0; i < arrOfRotorsNumbers.length; i++) {
            if (!LogicUtilities.isNumeric(arrOfRotorsNumbers[i])) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    public static boolean checkExtensionOfXmlFileInput(String path){
        if(path.isEmpty()) /// the user choose to go back to the menu
            return true;

        if(path.length()<=4)
            return false;

        String xmlFileName=path.substring(path.length() - 4);
        return xmlFileName.equals(".xml");
    }
    public static boolean checkXMLFilePath(String checkedFilePath){
        boolean isValid = true;

        if(checkedFilePath.isEmpty()) /// the user choose to go back to the menu
            return true;

        if(!checkExtensionOfXmlFileInput(checkedFilePath)){
            System.out.println(" ***** The file you entered is not a XML file! *****\n");
            isValid = false;
        }

        return isValid;
    }
    public static boolean checkNumbersOfRotorsInput(String checkedRotorsNumbers, int numberOfRotorsInRepository, int numberOfRotorsInUse) {
        boolean isValid = true;
        String[] arrOfRotorsNumbers;

        if(checkedRotorsNumbers.isEmpty())
            isValid=false;

        else if (!checkAmountOfCommas(checkedRotorsNumbers)) {
            System.out.println(" ***** You typed too many commas! *****\n");
            isValid = false;
        } else {
            arrOfRotorsNumbers = checkedRotorsNumbers.split(",");

            if (!checkIfInputIsNumbers(arrOfRotorsNumbers)) {
                System.out.println(" ***** You typed something other than a number! *****\n");
                isValid = false;
            } else if (!checkLogic.checkIdOfRotors(arrOfRotorsNumbers, numberOfRotorsInRepository)) {
                System.out.println(" ***** You entered number of rotor that doesn't exist! *****\n");
                isValid = false;
            } else if (!checkLogic.checkAmountOfRotors(arrOfRotorsNumbers,numberOfRotorsInUse)) {
                System.out.println(" ***** You entered wrong number of rotors in use! *****\n");
                isValid = false;
            } else if (!checkLogic.checkIfDuplicatedRotorsNumber(arrOfRotorsNumbers)) {
                System.out.println(" ***** You entered duplicates of rotor numbers! *****\n");
                isValid = false;
            }
        }
        return isValid;
    }
    public static boolean checkInitialPositionsOfRotors(String checkedInitialPositionsOfRotors, int numberOfRotors, Keyboard keyboard) {
        boolean isValid = true;

        if (!checkLogic.checkNumberOfInitialPositions(checkedInitialPositionsOfRotors, numberOfRotors)) {
            System.out.println(" ***** You entered a different number than " +numberOfRotors+ " positions! *****\n");
            isValid = false;
        } else if (!checkLogic.checkIfCharsIsInTheLanguage(checkedInitialPositionsOfRotors,keyboard)) {
            System.out.println(" ***** You entered char that is not in the language! *****\n");
            isValid = false;
        }

        return isValid;
    }
    public static boolean checkReflectorNumber(String checkedReflectorNumber,int numberOfReflectors) {
        boolean isValid = true;

        if(checkedReflectorNumber.isEmpty())
            isValid=false;

        else if (!LogicUtilities.isNumeric(checkedReflectorNumber)) {
            System.out.println(" ***** You entered invalid option! *****\n");
            isValid = false;
        } else {
            int reflectorNumber = Integer.parseInt(checkedReflectorNumber);

            if (!checkReflectorNumberInRange(reflectorNumber, numberOfReflectors)) {
                System.out.println(" ***** You entered invalid reflector number! *****\n");
                isValid=false;
            }
        }
        return isValid;
    }
    public static boolean checkReflectorNumberInRange(int checkedReflectorNumber,int numberOfReflectors) {
        return checkedReflectorNumber > 0 && checkedReflectorNumber <= numberOfReflectors;
    }
    public static boolean checkUserChoiceFromMenu(String userInput) {
        if (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("6") && !userInput.equals("7") && !userInput.equals("8")) {
            System.out.println(" ***** You entered option that is not in the menu! *****\n");
            return false;
        }
        return true;
    }
    public static boolean checkPlugsInUse(String checkedPlugInUse, Keyboard keyboard) {
        boolean isValid = true;

        if (!checkLogic.checkIfCharsIsInTheLanguage(checkedPlugInUse,keyboard)) {
            System.out.println(" ***** You entered char that is not in the language! *****\n");
            isValid = false;
        } else if (!checkLogic.checkNumbersOfPlugsInUse(checkedPlugInUse,keyboard.getNumberOfCharsInKeyboard()/2)) {
            System.out.println(" ***** You entered more than the total plugs numbers! *****\n");
            isValid = false;
        } else if (!checkLogic.checkIfNumberOfPlugsIsEven(checkedPlugInUse)) {
            System.out.println(" ***** You entered an odd number of plugs. The plugs must be in pairs! *****\n");
            isValid = false;
        } else if (!checkLogic.checkIfCharIsMappedToItself(checkedPlugInUse)) {
            System.out.println(" ***** You mapped a letter to itself! *****\n");
            isValid = false;
        } else if (!checkLogic.checkUniqueMappingPlugs(checkedPlugInUse)) {
            System.out.println(" ***** You used the same letter in more than one mapping pair of plugs! *****\n");
            isValid = false;
        }

        return isValid;
    }
    public static boolean checkIfStringToEncodeIsTheLanguage(String checkedString, Keyboard keyboard) {
        boolean isValid = true;

        if(checkedString.isEmpty()) /// the user choose to go back to the menu
            return true;

        if (!checkLogic.checkIfCharsIsInTheLanguage(checkedString,keyboard)) {
            System.out.println(" ***** You entered char that is not in the language! *****");
            isValid = false;
        }
        return isValid;
    }
}
