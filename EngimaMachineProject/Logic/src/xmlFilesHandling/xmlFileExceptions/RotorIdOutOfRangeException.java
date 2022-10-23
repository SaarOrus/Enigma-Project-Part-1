package xmlFilesHandling.xmlFileExceptions;

public class RotorIdOutOfRangeException extends Exception{
    private String EXCEPTION_MESSAGE;

    public RotorIdOutOfRangeException(int idOfRotor, int numberOfRotors){
        EXCEPTION_MESSAGE = "Rotor id: "+idOfRotor+" is not between "+"1 - "+numberOfRotors+"!";
    }
    @Override
    public String getMessage(){
        return EXCEPTION_MESSAGE;
    }
}
