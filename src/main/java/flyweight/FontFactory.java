package flyweight;

import java.awt.*;
import java.util.ArrayList;

public class FontFactory{

    private ArrayList<Font> fontList;

    private FontFactory(){
        fontList = new ArrayList<>();
    }

    // static class for creating single FontFactory instance
    private static class SingletonFont{
        private final static FontFactory Instance = new FontFactory();
    }

    public static FontFactory getInstance(){
        return SingletonFont.Instance;
    }

    public Font getFontFlyweight(String fontName, int pointSize, int fontStyle) {
        for(Font font : fontList){
            if(font.getFontName().equals(fontName) && pointSize == font.getSize() &&
                    fontStyle == font.getStyle()){
                    return font;
            }
        }
        Font flyweightFont = new Font(fontName, fontStyle, pointSize);
        fontList.add(flyweightFont);
        return flyweightFont;
    }
}