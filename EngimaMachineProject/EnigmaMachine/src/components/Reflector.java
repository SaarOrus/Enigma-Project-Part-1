package components;

public class Reflector {
    //the value in index i is the reflection of index i
    private int reflection[];

    //ctor
    public Reflector(int reflectionIndex[]) {
        this.reflection =reflectionIndex;
    }
    public Reflector(Reflector reflector){
        System.arraycopy(reflector.reflection,0,this.reflection,0,reflector.reflection.length);
    }

    public int reflectIndex(int enteredPosition) {
        return reflection[enteredPosition];
    }
}
