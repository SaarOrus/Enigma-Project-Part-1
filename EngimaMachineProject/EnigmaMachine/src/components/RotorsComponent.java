package components;

public class RotorsComponent {
    private final Rotor[] rotors;// array that starts from index 1
    boolean rotorRightToLeft = true;

    //ctor
    public RotorsComponent(Rotor[] rotors) {
        this.rotors = rotors;
    }
    //get functions
    public Rotor[] getRotors() {
        return rotors;
    }

    public int RotorsAction(int enteredIndex) {
        int exitIndex = enteredIndex;

        if (rotorRightToLeft) {
            for (int i = 1; i < rotors.length; i++) {
                if (i == 1 || rotors[i - 1].isNotchInFirstPlace())
                    rotors[i].rollForward();
                else
                    break;
            }

            for (int i = 1; i < rotors.length; i++) {
                exitIndex = rotors[i].actionRightToLeft(exitIndex);
            }
            rotorRightToLeft = false;
        } else {
            for (int i = rotors.length - 1; i >= 1; i--) {
                exitIndex = rotors[i].actionLeftToRight(exitIndex);
            }
            rotorRightToLeft = true;
        }
        return exitIndex;
    }
}