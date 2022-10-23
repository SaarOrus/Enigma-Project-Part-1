package xmlFilesHandling.xmlFileExceptions;

public class RotorIdIsNotUniqueException extends Exception {
    private String EXCEPTION_MESSAGE;

    public RotorIdIsNotUniqueException(int idOfRotor){
        EXCEPTION_MESSAGE = "Rotor id: "+idOfRotor+" belongs to two different rotors!";
    }
    @Override
    public String getMessage(){
        return EXCEPTION_MESSAGE;
    }
}
