package components;

public class Rotor {
    private char [] rightCol;
    private char [] leftCol;
    private int indexOfNotch;
    private final int numberOfChars;

    //ctor
    public Rotor(char[] rightCol, char[] leftCol, int indexOfNotch) {
        this.rightCol = rightCol;
        this.leftCol = leftCol;
        this.indexOfNotch = indexOfNotch;
        this.numberOfChars = rightCol.length-1;
    }
    public Rotor(Rotor rotor){
        this.indexOfNotch=rotor.indexOfNotch;
        System.arraycopy(rotor.leftCol,0,this.leftCol,0,rotor.leftCol.length);
        System.arraycopy(rotor.rightCol,0,this.rightCol,0,rotor.rightCol.length);
        this.numberOfChars=rotor.numberOfChars;
    }
    //get functions
    public char getCurrentCharInWindow() {
        return rightCol[1];
    }
    public int getIndexOfNotch() {
        return indexOfNotch;
    }

    public void resetByStartPosition(char startPosition) {
        int numberOfRotorRoll = 0;
        for (int i = 1; i < rightCol.length; i++) {
            if (rightCol[i] == startPosition) {
                numberOfRotorRoll = i - 1;
                break;
            }
        }
        for (int i = 0; i < numberOfRotorRoll; i++) {
            rollForward();
        }
    }
    public boolean isNotchInFirstPlace() {
        return indexOfNotch == 1;
    }
    public void rollForward() {
        RotateRotorByOneStep();
        moveForwardNotch();
    }
    public int actionRightToLeft(int enteredIndex) {
        int exitIndex = 0;
        char searchChar = rightCol[enteredIndex];

        for (int i = 1; i < leftCol.length; i++) {
            if (leftCol[i] == searchChar) {
                exitIndex = i;
                break;
            }
        }

        return exitIndex;
    }
    public int actionLeftToRight(int enteredIndex) {
        int exitIndex = 0;
        char searchChar = leftCol[enteredIndex];

        for (int i = 1; i < rightCol.length; i++) {
            if (rightCol[i] == searchChar) {
                exitIndex = i;
                break;
            }
        }

        return exitIndex;
    }
    private void moveForwardNotch() {
        if (indexOfNotch == 1) {
            indexOfNotch = numberOfChars;
        } else {
            indexOfNotch--;
        }
    }
    // To rotate left one by one
    private void RotateRotorByOneStep() {
        int i;
        char tempLeft, tempRight;
        tempLeft = leftCol[1];
        tempRight = rightCol[1];
        for (i = 1; i < numberOfChars; i++) {
            leftCol[i] = leftCol[i + 1];
            rightCol[i] = rightCol[i + 1];
        }
        leftCol[i] = tempLeft;
        rightCol[i] = tempRight;
    }
}

