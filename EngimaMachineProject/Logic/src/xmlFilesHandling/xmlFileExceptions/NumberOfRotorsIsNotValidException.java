package xmlFilesHandling.xmlFileExceptions;

public class NumberOfRotorsIsNotValidException extends Exception {
    private String EXCEPTION_MESSAGE;

    public NumberOfRotorsIsNotValidException(int rotorsCount, int realNumberOfRotorInXml) {
             EXCEPTION_MESSAGE = "The Rotors count is " + rotorsCount + " Bigger then " + realNumberOfRotorInXml;

    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
