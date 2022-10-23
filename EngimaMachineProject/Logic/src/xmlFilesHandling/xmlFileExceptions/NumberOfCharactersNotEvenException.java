package xmlFilesHandling.xmlFileExceptions;

public class NumberOfCharactersNotEvenException extends Exception{
    private final String EXCEPTION_MESSAGE= "Number of characters in keyboard is not even!";

    @Override
    public String getMessage(){
        return EXCEPTION_MESSAGE;
    }
}
