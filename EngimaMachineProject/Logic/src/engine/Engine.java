package engine;

import Utilies.LogicUtilities;
import components.Rotor;
import dtoHandling.CodeConfiguration;
import dtoHandling.ConvertDeliveryObjToMachine;
import dtoHandling.DeliveryInformation;
import enigmaMachine.Machine;
import historyAndStatistic.EncodeHistory;
import historyAndStatistic.EncodingsOfCodeConfiguration;
import historyAndStatistic.HistoryAndStatistic;
import repository.RepositoryOfComponents;
import xmlFilesHandling.CheckXMLFile;
import xmlFilesHandling.ReadXmlFile;
import xmlFilesHandling.convertedCte.ConvertCteToComponents;
import xmlFilesHandling.xmlFileExceptions.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Engine implements Executable {
    private String pathOfXml;
    private EncodeHistory currEncodeHistory;
    private EncodingsOfCodeConfiguration currEncodingsOfCodeConfiguration;
    private CodeConfiguration initializeCode;
    private CodeConfiguration currentCode;
    private String encodedString;
    private String stringToEncode;
    private RepositoryOfComponents repository;
    private DeliveryInformation deliveryInformation;
    private int encodingCounter;
    private Machine machine;
    private final HistoryAndStatistic historyAndStatistic;

    //ctor
    public Engine(){
        this.deliveryInformation=new DeliveryInformation();
        this.encodingCounter =0;
        this.historyAndStatistic=new HistoryAndStatistic();
        this.pathOfXml=null;
    }
    //interface
    @Override
    public void initialCodeManually(){
        createMachines();
        initialCodeConfigurationByMachine();
        this.currEncodingsOfCodeConfiguration = new EncodingsOfCodeConfiguration();
        currEncodingsOfCodeConfiguration.setCodeConfiguration(new StringBuilder(currentCode.toString()));
        historyAndStatistic.getMachineHistoryAndStatisticList().add(currEncodingsOfCodeConfiguration);
    }
    @Override
    public void initialCodeAutomatically(){
        createCodeAutomatically();
        initialCodeConfigurationByMachine();
        currEncodingsOfCodeConfiguration = new EncodingsOfCodeConfiguration();
        currEncodingsOfCodeConfiguration.setCodeConfiguration(new StringBuilder(currentCode.toString()));
        historyAndStatistic.getMachineHistoryAndStatisticList().add(currEncodingsOfCodeConfiguration);
    }
    @Override
    public void resetCode() {
        ConvertDeliveryObjToMachine convertDeliveryObjToMachine = new ConvertDeliveryObjToMachine(initializeCode.getDtoCode(), repository);
        machine = convertDeliveryObjToMachine.getEnigmaMachine();
        initialCodeConfigurationByMachine();
    }
    @Override
    public void encode(){
        encodeStringInEnigma(stringToEncode);
        updateCodeConfiguration();
    }

    //get functions
    public String getPathOfXml() {
        return pathOfXml;
    }
    public int getEncodingCounter() {
        return encodingCounter;
    }
    public CodeConfiguration getCurrentCode() {
        return currentCode;
    }
    public CodeConfiguration getInitializeCode() {
        return initializeCode;
    }
    public String getEncodedString() {
        return encodedString;
    }
    public HistoryAndStatistic getHistoryAndStatistic() {
        return historyAndStatistic;
    }
    public RepositoryOfComponents getRepository() {
        return repository;
    }
    //set functions
    public void setStringToEncode(String stringToEncode) {
        this.stringToEncode = stringToEncode;
    }
    public void setPathOfXml(String pathOfXml) {
        this.pathOfXml = pathOfXml;
    }
    public void setRepository(RepositoryOfComponents repository) {
        this.repository = repository;
    }
    public void setDeliveryInformation(DeliveryInformation deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }
    //automatic initialize
    public void createCodeAutomatically() {
        int numberOfRotors= repository.getNumberOfRotorsInUse();

        randomRotorsConfiguration(numberOfRotors);
        randomReflector();
        randomPlugsConfiguration();

        createMachines();
    }
    public void randomPlugsConfiguration(){
        int numberOfPlugsInUse= LogicUtilities.getRandomNumber(0,repository.getKeyboard().getNumberOfCharsInKeyboard()/2);
        HashMap<Character,Character> plugsInUse=new HashMap<>();
        char firstCharPlug,secondCharPlug;

        for (int i = 0; i < numberOfPlugsInUse; i++) {
            do {
                int randomFirstPlug = LogicUtilities.getRandomNumber(1, repository.getKeyboard().getNumberOfCharsInKeyboard());
                int randomSecondPlug;

                do {
                    randomSecondPlug = LogicUtilities.getRandomNumber(1, repository.getKeyboard().getNumberOfCharsInKeyboard());
                } while (randomFirstPlug == randomSecondPlug);

                firstCharPlug = repository.getKeyboard().getKeyboardChars()[randomFirstPlug];
                secondCharPlug = repository.getKeyboard().getKeyboardChars()[randomSecondPlug];

            }while(!isExistDuplicatesInRandomPlugs(firstCharPlug,secondCharPlug,plugsInUse));

            plugsInUse.put(firstCharPlug,secondCharPlug);
        }
        deliveryInformation.setPlugsInUse(plugsInUse);
    }
    public boolean isExistDuplicatesInRandomPlugs(char firstCharPlug,char secondCharPlug,HashMap<Character,Character> plugsInUse){
        boolean isValid=true;

        for(Map.Entry<Character, Character> entry : plugsInUse.entrySet()) {
            if(entry.getValue().equals(firstCharPlug) || entry.getKey().equals(firstCharPlug) || entry.getValue().equals(secondCharPlug)|| entry.getKey().equals(secondCharPlug)) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
    public void randomReflector(){
        int numberOfReflection= LogicUtilities.getRandomNumber(1,repository.getNumberOfReflectors());
        String reflectorGreekNumber= LogicUtilities.convertNumberToReflectorId(numberOfReflection);

        deliveryInformation.setReflectorGreekNumber(reflectorGreekNumber);
    }
    public void randomRotorsConfiguration(int numberOfRotors){
        int []  rotorsID= new int[numberOfRotors];
        char []  initialRotorsPosition= new char[numberOfRotors];
        boolean [] chosenIdOfRotors=new boolean[repository.getNumberOfRotors()];

        for (int i = 0; i < numberOfRotors; i++) {
            int randomRotorNumber;

            do {
                randomRotorNumber = LogicUtilities.getRandomNumber(1,repository.getNumberOfRotors()-1);
            } while (chosenIdOfRotors[randomRotorNumber]);

            chosenIdOfRotors[randomRotorNumber]=true;
            rotorsID[i] =randomRotorNumber;

            int indexOfCharInKeyboard = LogicUtilities.getRandomNumber(1,repository.getKeyboard().getNumberOfCharsInKeyboard());
            initialRotorsPosition[i] = repository.getKeyboard().getKeyboardChars()[indexOfCharInKeyboard];
        }

        deliveryInformation.setRotorsNumbersID(rotorsID);
        deliveryInformation.setInitialPositionsOfRotors(initialRotorsPosition);
    }

    public void encodeStringInEnigma(String stringToEncode) {
        long startTime = System.nanoTime();
        String encodedString=machine.encodeString(stringToEncode);
        long endTime = System.nanoTime();
        long timeOfEncoding = (endTime - startTime);

        currEncodeHistory=new EncodeHistory(stringToEncode,encodedString,timeOfEncoding);
        currEncodingsOfCodeConfiguration.getEncodeHistoryList().add(currEncodeHistory);
        this.encodedString=encodedString;
        encodingCounter++;
    }
    public void updateCodeConfiguration() {
        Rotor[] rotors = machine.getRotorsComponents().getRotors();
        int[] notchPlaceInRotor = new int[rotors.length - 1];
        char[] charAppearInWindows = new char[rotors.length - 1];

        for (int i = 1; i < rotors.length; i++) {
            notchPlaceInRotor[i - 1] = rotors[i].getIndexOfNotch();
            charAppearInWindows[i - 1] = rotors[i].getCurrentCharInWindow();
        }

        notchPlaceInRotor=LogicUtilities.reverseArrayInt(notchPlaceInRotor,0);
        charAppearInWindows=LogicUtilities.reverseArrayChar(charAppearInWindows,0);

        this.currentCode.setNotchLocationInRotor(notchPlaceInRotor);
        this.currentCode.setInitialPositionsOfRotors(charAppearInWindows);
    }
    public void initialCodeConfigurationByMachine() {
        Rotor[] rotors = machine.getRotorsComponents().getRotors();
        int[] notchPlaceInRotor = new int[rotors.length - 1];

        for (int i = 1; i < rotors.length; i++) {
            notchPlaceInRotor[i - 1] = rotors[i].getIndexOfNotch();
        }

        this.initializeCode = new CodeConfiguration(deliveryInformation, notchPlaceInRotor);
        this.currentCode = new CodeConfiguration(new DeliveryInformation(deliveryInformation), notchPlaceInRotor);

    }
    public void createRepository() throws ReflectorIdIsMissingException, ReflectorIdIsNotUniqueException, RotorIdIsNotUniqueException, NumberOfRotorsIsNotValidException, JAXBException, ReflectorIdIsOutOfRangeException, RotorIdIsMissingException, FileNotFoundException, NotchPlaceOutOfRangeException, NumberOfRotorsIsLessThenTwoException, NumberOfCharIsZeroException, RotorIdOutOfRangeException, NumberOfCharactersNotEvenException, ReflectCharToTheSameCharException, DoubleMappingInRotorException, RotorsCountBiggerThenMax {
        createRepositoryFromXML();
        historyAndStatistic.resetHistoryAndStatistic();
        encodingCounter=0;
    }
    public void createMachines(){
        this.machine =new Machine(convertedChoiceToMachine());

    }
    public Machine convertedChoiceToMachine() {
        ConvertDeliveryObjToMachine convertDeliveryObjToMachine = new ConvertDeliveryObjToMachine(deliveryInformation, repository);
        return convertDeliveryObjToMachine.getEnigmaMachine();
    }
    public void createRepositoryFromXML() throws ReflectorIdIsMissingException, ReflectorIdIsNotUniqueException, RotorIdIsNotUniqueException, NumberOfRotorsIsNotValidException, ReflectorIdIsOutOfRangeException, RotorIdIsMissingException, NotchPlaceOutOfRangeException, NumberOfRotorsIsLessThenTwoException, RotorIdOutOfRangeException, NumberOfCharactersNotEvenException, ReflectCharToTheSameCharException, DoubleMappingInRotorException, JAXBException, FileNotFoundException, NumberOfCharIsZeroException, RotorsCountBiggerThenMax {
        ReadXmlFile readXml= new ReadXmlFile(pathOfXml);
        CheckXMLFile checkXMLFile= new CheckXMLFile(readXml.getCteEnigma());
        ConvertCteToComponents convertedCte= new ConvertCteToComponents(readXml.getCteEnigma());
        this.repository =convertedCte.getRepository();
    }
}
