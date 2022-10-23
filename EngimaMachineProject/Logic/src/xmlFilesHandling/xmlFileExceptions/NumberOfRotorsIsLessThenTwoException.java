package xmlFilesHandling.xmlFileExceptions;

public class NumberOfRotorsIsLessThenTwoException extends Exception {
    private String EXCEPTION_MESSAGE;

    public NumberOfRotorsIsLessThenTwoException(int rotorsCount){
            EXCEPTION_MESSAGE = "The number of rotors is "+rotorsCount+" less than 2!";
    }
    @Override
    public String getMessage(){
        return EXCEPTION_MESSAGE;
    }
}
