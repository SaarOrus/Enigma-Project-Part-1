package xmlFilesHandling.xmlFileExceptions;

public class XmlFileDoesNotExistsException extends Exception {
    private String EXCEPTION_MESSAGE;
    public XmlFileDoesNotExistsException(String xmlFileName){
        EXCEPTION_MESSAGE="The file "+ xmlFileName+ "does not exist!";
    }
    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}


