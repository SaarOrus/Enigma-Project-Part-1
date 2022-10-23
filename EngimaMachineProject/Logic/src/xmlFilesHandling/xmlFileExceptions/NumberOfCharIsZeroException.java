package xmlFilesHandling.xmlFileExceptions;

public class NumberOfCharIsZeroException extends Exception{
    private final String EXCEPTION_MESSAGE= "There are no characters in the machine!";

    @Override
    public String getMessage(){
        return EXCEPTION_MESSAGE;
    }
}


