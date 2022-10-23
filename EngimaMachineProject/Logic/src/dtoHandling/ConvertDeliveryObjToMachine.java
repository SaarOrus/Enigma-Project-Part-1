package dtoHandling;
import Utilies.LogicUtilities;
import components.*;
import enigmaMachine.Machine;
import repository.RepositoryOfComponents;

public class ConvertDeliveryObjToMachine {
    DeliveryInformation deliveryInformation;
    Machine enigmaMachine;
    RepositoryOfComponents repo;

    //ctor
    public ConvertDeliveryObjToMachine(DeliveryInformation deliveryInformation, RepositoryOfComponents repo) {
        this.repo=repo;
        this.deliveryInformation=deliveryInformation;
        this.enigmaMachine=new Machine(repo.getKeyboard(),InitializingPlugBoard(),InitializingReflector(), InitializingRotors());
    }
    //get functions
    public Machine getEnigmaMachine() {
        return enigmaMachine;
    }

    private RotorsComponent InitializingRotors() {
        int[] rotorsNumberId = reverseRotorsNumberId(deliveryInformation.getRotorsNumbersID());
        char[] rotorsInitialPosition = reverseInitialPositionsOfRotors(deliveryInformation.getInitialPositionsOfRotors());
        Rotor[] ChosenRotors = new Rotor[rotorsNumberId.length + 1];
        for (int i = 1; i < rotorsNumberId.length + 1; i++) {
            ChosenRotors[i] = repo.getRotorInIndexInd(rotorsNumberId[i - 1]);
            ChosenRotors[i].resetByStartPosition(rotorsInitialPosition[i - 1]);
        }
        return new RotorsComponent(ChosenRotors);
    }
    private Reflector InitializingReflector(){
        int idOfReflector= LogicUtilities.convertInputOfReflectorNumber(deliveryInformation.getReflectorGreekNumber());
        return repo.getReflectors()[idOfReflector];
    }
    private PlugBoard InitializingPlugBoard(){
        return new PlugBoard(deliveryInformation.getPlugsInUse());
    }
    private char[] reverseInitialPositionsOfRotors(char[] rotorsPositions) {
       return LogicUtilities.reverseArrayChar(rotorsPositions,0);
    }
    private int[] reverseRotorsNumberId (int[] rotorsOrder) {
        return LogicUtilities.reverseArrayInt(rotorsOrder,0);
    }
}