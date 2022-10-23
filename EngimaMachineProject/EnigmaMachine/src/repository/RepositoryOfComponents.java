package repository;

import components.Keyboard;
import components.Reflector;
import components.Rotor;

public class RepositoryOfComponents {
    private final Reflector[] reflectors;
    private final Rotor[] rotors;
    private final Keyboard keyboard;
    private int numberOfRotorsInUse;

    //ctor
    public RepositoryOfComponents(Keyboard keyboard,Reflector[] reflectors, Rotor[] rotors,int numberOfRotorsInUse) {
        this.keyboard=keyboard;
        this.reflectors = reflectors;
        this.rotors = rotors;
        this.numberOfRotorsInUse =numberOfRotorsInUse;
    }
    // get functions
    public Rotor getRotorInIndexInd(int index) {
        return rotors[index];
    }
    public Keyboard getKeyboard() {
        return keyboard;
    }
    public Rotor[] getRotors() {
        return rotors;
    }
    public Reflector[] getReflectors() {
        return reflectors;
    }
    public int getNumberOfRotors(){
        return rotors.length;
    }
    public int getNumberOfReflectors(){
        return reflectors.length-1;
    }
    public int getNumberOfRotorsInUse() {
        return numberOfRotorsInUse;
    }
}
