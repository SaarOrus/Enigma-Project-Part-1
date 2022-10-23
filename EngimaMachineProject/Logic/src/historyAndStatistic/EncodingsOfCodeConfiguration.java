package historyAndStatistic;
import java.util.ArrayList;
import java.util.List;

public class EncodingsOfCodeConfiguration {
    private StringBuilder codeConfiguration;
    private List<EncodeHistory> encodeHistoryList;

    //ctor
    public EncodingsOfCodeConfiguration(){
        this.codeConfiguration=new StringBuilder();
        this.encodeHistoryList = new ArrayList<>();
    }
    //set functions
    public void setCodeConfiguration(StringBuilder codeConfiguration) {
        this.codeConfiguration = codeConfiguration;
    }
    //get functions
    public List<EncodeHistory> getEncodeHistoryList() {
        return encodeHistoryList;
    }

    @Override
    public String toString() {
       StringBuilder toString= new StringBuilder();
       int counter=1;

       toString.append(codeConfiguration).append("\n");

       for (EncodeHistory encodeHistory:encodeHistoryList){
           toString.append("   ").append(counter).append(". ");
           toString.append(encodeHistory.toString()).append("\n");
           counter++;
       }

        return String.valueOf(toString);
    }
}

