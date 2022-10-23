package xmlFilesHandling.xmlFileExceptions;

public class RotorsCountBiggerThenMax extends Exception {
    private String EXCEPTION_MESSAGE;

    public RotorsCountBiggerThenMax(int rotorsCount) {
        EXCEPTION_MESSAGE = "The Rotors count is " + rotorsCount + " Bigger then 99";
    }
    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}