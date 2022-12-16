package flyweight;

public class FlyweightCharacter{

    int unicodeCodePoint;

    public FlyweightCharacter(char c){
        unicodeCodePoint = c;
    }

    public int getUnicodeCodePoint() {
        return unicodeCodePoint;
    }

    public char getCharacterValue(){
        return (char) unicodeCodePoint;
    }

}