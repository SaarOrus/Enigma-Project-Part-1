package enigmaMachine;
import components.Keyboard;
import components.PlugBoard;
import components.Reflector;
import components.RotorsComponent;

public class Machine {
    private final Keyboard keyboard;
    private final PlugBoard plugboard;
    private final Reflector reflector;
    private final RotorsComponent rotors;

    //ctor
    public Machine(Keyboard keyboard,PlugBoard plugboard, Reflector reflector, RotorsComponent rotors) {
        this.keyboard=keyboard;
        this.plugboard = plugboard;
        this.reflector = reflector;
        this.rotors = rotors;
    }
    public Machine(Machine enigmaMachine) {
        this.keyboard=enigmaMachine.keyboard;
        this.plugboard = enigmaMachine.plugboard;
        this.reflector = enigmaMachine.reflector;
        this.rotors = enigmaMachine.rotors;
    }
    //get functions
    public RotorsComponent getRotorsComponents() {
        return rotors;
    }
    //encoding options
    public char encodeChar(char enteredChar) {
        char exitChar = plugboard.matchConnectedPlug(enteredChar);
        int indexOfChar =keyboard.getIndexOfChar(exitChar);
        indexOfChar = rotors.RotorsAction(indexOfChar);
        indexOfChar = reflector.reflectIndex(indexOfChar);
        indexOfChar = rotors.RotorsAction(indexOfChar);
        exitChar = plugboard.matchConnectedPlug(keyboard.getCharInKeyboardByIndex(indexOfChar));

        return exitChar;
    }
    public String encodeString(String enteredString) {
        String encodedString="";

        for (int i = 0; i < enteredString.length(); i++) {
            encodedString+=encodeChar(enteredString.charAt(i));
        }

        return encodedString;
    }
}
