package xmlFilesHandling.xmlFileExceptions;

import Utilies.LogicUtilities;

public class ReflectorIdIsOutOfRangeException extends Exception {
    private String EXCEPTION_MESSAGE;

    public ReflectorIdIsOutOfRangeException(String idOfReflector, int numberOfReflectors){
        EXCEPTION_MESSAGE = "Reflector id: "+idOfReflector+" is not between "+"I - "+ LogicUtilities.convertNumberToReflectorId(numberOfReflectors)+"!";
    }
    @Override
    public String getMessage(){
        return EXCEPTION_MESSAGE;
    }

}
