package test;

import components.Keyboard;

public class CheckLogic {
    //ctor
    public CheckLogic(){}

    public boolean checkIdOfRotors(String [] idOfRotors, int numberOfRotorsInRepository){
        boolean isValid=true;

        for(String id: idOfRotors){
            if((Integer.parseInt(id)>numberOfRotorsInRepository)){
                isValid=false;
                break;
            }
        }

        return isValid;
    }
    public boolean checkAmountOfRotors(String[] arrOfRotorsNumbers, int numberOfRotorsInUse) {
        return arrOfRotorsNumbers.length == numberOfRotorsInUse;
    }
    public boolean checkNumberOfInitialPositions(String checkedInitialPositionsOfRotors, int numberOfRotors){
        return checkedInitialPositionsOfRotors.length() == numberOfRotors;
    }
    public boolean checkIfCharsIsInTheLanguage(String stringOfPlugInUse, Keyboard keyboard){
        boolean isValid=true;

        for (int i = 0; i < stringOfPlugInUse.length(); i++) {
            if (!keyboard.findInKeyboard(Character.toUpperCase(stringOfPlugInUse.charAt(i)))) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
    public boolean checkUniqueMappingPlugs(String checkedString){
       boolean isValid=true;

        for (int i = 0; i < checkedString.length() - 1; i++) {
            for (int j = i + 1; j < checkedString.length(); j++) {
                if (checkedString.charAt(i) == checkedString.charAt(j)) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }
    public boolean checkIfCharIsMappedToItself(String checkedString) {
        boolean isValid = true;

        for (int i = 0; i < checkedString.length() - 1; i++) {
            if (checkedString.charAt(i) == checkedString.charAt(i + 1)) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    public boolean checkIfNumberOfPlugsIsEven(String checkedInitialPositionsOfRotors){
        return checkedInitialPositionsOfRotors.length()%2==0;
    }
    public boolean checkNumbersOfPlugsInUse(String checkedPlugInUse, int totalNumberOfPlugs){
        int counterNumberOfCommas=0;
        boolean isValid=true;

        for (int i = 0; i < checkedPlugInUse.length(); i++) {
            if(checkedPlugInUse.charAt(i)==',')
                counterNumberOfCommas++;
        }

        if(counterNumberOfCommas>=totalNumberOfPlugs) {
            isValid = false;
        }

        return isValid;
    }
    public boolean checkIfDuplicatedRotorsNumber(String[] arrOfRotorsNumbers) {
        boolean isValid = true;

        for (int i = 0; i < arrOfRotorsNumbers.length; i++) {
            for (int j = i+1; j < arrOfRotorsNumbers.length; j++) {
                if ((i!=j) && (arrOfRotorsNumbers[j].equals(arrOfRotorsNumbers[i]))) {
                    isValid = false;
                    break;
                }
            }
        }

        return isValid;
    }
}
