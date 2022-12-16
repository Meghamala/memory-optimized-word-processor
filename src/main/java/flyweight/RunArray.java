package flyweight;

import java.awt.*;
import java.util.ArrayList;

public class RunArray{

    private ArrayList< ArrayList<Object> > arrayList = new ArrayList<>();
    private int index;
    ArrayList<Object> innerArray;

    public RunArray(){}

    // creates innerArray to store font information and adds to arrayList
    public boolean addRun(int startIndex, int length, Font value){
        innerArray = new ArrayList<>(3);
        innerArray.add(startIndex);
        innerArray.add(length);
        innerArray.add(value);
        arrayList.add(innerArray);
        index += length;
        return true;
    }

    public boolean appendRun(int length, Font value){
        innerArray = new ArrayList<>(3);
        innerArray.add(index);
        innerArray.add(length);
        innerArray.add(value);
        arrayList.add(innerArray);
        index += length;
        return true;
    }

    // returns font information at the given index else null
    public Font getFont(int i) {
        Font font = null;
        for (ArrayList<Object> objects : arrayList) {
            if((int)objects.get(0) <= i && ((int)objects.get(0) + (int)objects.get(1))  >= i){
                return (Font) objects.get(2);
            }
        }
        return font;
    }
}