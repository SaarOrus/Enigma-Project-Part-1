package components;

import java.util.HashMap;

public class Keyboard {
    private final int numberOfCharsInKeyboard;
    private final char[] keyboardChars; // start from 1
    private  HashMap<Character, Integer> indexForChar ; //receive number (key) and return char (value)

    //ctor
    public Keyboard(char[] keyboardChars){
        this.keyboardChars=keyboardChars;
        this.numberOfCharsInKeyboard=keyboardChars.length-1;
        this.indexForChar=buildIndexForChar();
    }
    //get functions
    public char[] getKeyboardChars() {
        return keyboardChars;
    }
    public int getIndexOfChar(char letter) {
        return indexForChar.get(letter);
    }
    public int getNumberOfCharsInKeyboard(){
        return numberOfCharsInKeyboard;
    }
    public char getCharInKeyboardByIndex(int index){
        return keyboardChars[index];}
    private HashMap buildIndexForChar(){
        indexForChar= new HashMap();
        int counter =1;
        for (int i = 1; i <=numberOfCharsInKeyboard ; i++) {
            indexForChar.put(keyboardChars[i], counter);
            counter++;
        }
        return indexForChar;
    }
    public boolean findInKeyboard(char charToSearch){
        boolean isFound=false;

        for (int i = 0; i < keyboardChars.length; i++) {
            if(keyboardChars[i]==charToSearch) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 1; i < keyboardChars.length; i++) {
            stringBuilder.append(keyboardChars[i]);
        }
        return String.valueOf(stringBuilder);
    }
}
