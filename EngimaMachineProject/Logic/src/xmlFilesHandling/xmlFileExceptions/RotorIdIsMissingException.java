package xmlFilesHandling.xmlFileExceptions;

public class RotorIdIsMissingException extends Exception {
    private String EXCEPTION_MESSAGE;

    public RotorIdIsMissingException(int rotorId) {
        EXCEPTION_MESSAGE = "Rotor : " + rotorId + " Does not exist!";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
