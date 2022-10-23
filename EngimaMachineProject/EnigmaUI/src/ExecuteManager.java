import dtoHandling.DeliveryInformation;
import engine.Engine;
import repository.RepositoryOfComponents;
import userInterface.CheckUI;
import userInterface.UI;
import userInterface.menu.Menu;
import userInterface.menu.menuOptions;
import xmlFilesHandling.xmlFileExceptions.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class ExecuteManager {
    private boolean isReadXML;
    private boolean isMachineInitialized;
    private final Engine engine;
    private final CheckUI checkUI;

    public ExecuteManager() {
        this.engine = new Engine();
        this.isReadXML=false;
        this.isMachineInitialized=false;
        this.checkUI=new CheckUI();
    }

    public void execute() {
        boolean runMachine = true;
        UI.presentWelcomeMessage();

        do {
            Menu.showMenu();
            int userChoice = UI.getUserChoice();
            menuOptions menuChoice = menuOptions.values()[userChoice];

            switch (menuChoice) {
                case READ_FILE_PATH:
                    readFilePath();
                    break;
                case DISPLAY_DETAILS:
                    displayDetails();
                    break;
                case MANUAL_CONFIGURATION:
                    manualConfiguration();
                    break;
                case AUTO_CONFIGURATION:
                    autoConfiguration();
                    break;
                case ENCODE:
                    encode();
                    break;
                case RESET_MACHINE:
                    resetMachine();
                    break;
                case HISTORY_STATISTICS:
                    historyStatistics();
                    break;
                case EXIT:
                    exit();
                    runMachine = false;
                    break;
            }
        } while (runMachine);
    }
    //options functions
    public void readFilePath(){
        if(inputXmlFilePath().isEmpty()){
            UI.presentFileDidntReadMessage();
        }
        else {
            UI.fileReadSuccessfullyMessage();
        }
    }
    public void displayDetails() {
        if (!isReadXML)
            UI.notReadXml();
        else if (!isMachineInitialized)
            UI.MachineIsNotInitialize();
        else
            UI.displayingTheMachineSpecifications(engine.getRepository(), engine.getInitializeCode(), engine.getCurrentCode(), engine.getEncodingCounter());
    }
    public void manualConfiguration(){
        if (!isReadXML) {
            UI.notReadXml();
        } else {
            createCodeManually();
            engine.initialCodeManually();
            UI.presentManuallyCode(engine.getCurrentCode().toString());
            this.isMachineInitialized = true;
        }
    }
    public void autoConfiguration(){
        if (!isReadXML) {
            UI.notReadXml();
        } else {
            engine.initialCodeAutomatically();
            UI.presentAutomaticCode(engine.getCurrentCode().toString());
            this.isMachineInitialized = true;
        }
    }
    public void encode() {
        if (!isReadXML)
            UI.notReadXml();
        else if (!isMachineInitialized)
            UI.MachineIsNotInitialize();
        else {
            String stringToEncode = UI.getStringToEncodeInTheMachine(engine.getRepository().getKeyboard());
            if (!stringToEncode.isEmpty()) {
                engine.setStringToEncode(stringToEncode);
                engine.encode();
                UI.presentEncodedString(engine.getEncodedString());
            }
        }
    }
    public void resetMachine(){
        if (!isReadXML)
            UI.notReadXml();
        else if (!isMachineInitialized)
            UI.MachineIsNotInitialize();
        else {
            engine.resetCode();
            UI.resetMessage(engine.getInitializeCode().toString());
        }
    }
    public void historyStatistics(){
        if (!isReadXML) {
            UI.notReadXml();
        } else {
            System.out.println(engine.getHistoryAndStatistic().toString());
        }
    }
    public void exit(){
        UI.presentGoodbyeMessage();
    }

    public void createCodeManually() {
        DeliveryInformation deliveryInformation = new DeliveryInformation();
        deliveryInformation.setRotorsNumbersID(UI.getNumbersOfRotors(engine.getRepository().getNumberOfRotors()-1, engine.getRepository().getNumberOfRotorsInUse()));
        deliveryInformation.setInitialPositionsOfRotors(UI.getInitialPositionsOfRotors(engine.getRepository().getNumberOfRotorsInUse(),engine.getRepository().getKeyboard()));
        deliveryInformation.setReflectorGreekNumber(UI.getReflector(engine.getRepository().getNumberOfReflectors()));
        deliveryInformation.setPlugsInUse(UI.getPlugsInUse(engine.getRepository().getKeyboard()));
        engine.setDeliveryInformation(deliveryInformation);
    }
    public String inputXmlFilePath(){
        String pathOfXml;
        boolean newFileIsValid=false;
        RepositoryOfComponents prevRepo=engine.getRepository();
        String prevPathOfXml=engine.getPathOfXml();

        do {
            pathOfXml = UI.getFullPathOfXMLFileToLoad();
            if (pathOfXml.isEmpty()) {
                if(!newFileIsValid && isReadXML){
                    engine.setPathOfXml(prevPathOfXml);
                    engine.setRepository(prevRepo);
                }
                break;
            }

            engine.setPathOfXml(pathOfXml);
            newFileIsValid=readingSystemInformationFile();
        }while(!newFileIsValid);

        return pathOfXml;
    }
    public boolean readingSystemInformationFile() {
        boolean newFileIsValid=false;

        try {
            engine.createRepository();
            this.isMachineInitialized=false;
            this.isReadXML=true;
            newFileIsValid = true;
        } catch (ReflectorIdIsMissingException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (ReflectorIdIsNotUniqueException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (RotorIdIsNotUniqueException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (NumberOfRotorsIsNotValidException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (ReflectorIdIsOutOfRangeException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (RotorIdIsMissingException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (NotchPlaceOutOfRangeException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (NumberOfRotorsIsLessThenTwoException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (RotorIdOutOfRangeException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (NumberOfCharactersNotEvenException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (ReflectCharToTheSameCharException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (DoubleMappingInRotorException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (NumberOfCharIsZeroException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (RotorsCountBiggerThenMax e) {
            System.out.println(e.getMessage() + "\n");
        } catch (JAXBException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + "\n");
        }

        return newFileIsValid;
    }
}
