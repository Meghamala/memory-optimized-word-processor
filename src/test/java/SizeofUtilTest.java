/*
 * Copyright (c) 2011.  Peter Lawrey
 *
 * "THE BEER-WARE LICENSE" (Revision 128)
 * As long as you retain this notice you can do whatever you want with this stuff.
 * If we meet some day, and you think this stuff is worth it, you can buy me a beer in return
 * There is no warranty.
 */

import flyweight.*;
import org.junit.Test;
import java.awt.*;

public class SizeofUtilTest {
    @Test
    public void flyweightTest() throws Exception {
        System.out.printf("The average size of text using Flyweight is %.2f bytes%n", new SizeofUtil() {
            char [] charArray = null;
            CharacterFactory flyweightPool = null;
            FontFactory fontPool = null;
            RunArray runArray = null;
            Font fontA, fontB;

            @Override
            protected int create() {
                flyweightPool = CharacterFactory.getInstance();
                fontPool = FontFactory.getInstance();
                runArray = new RunArray();
                charArray = new char[356];

                // creating flyweight for 54 distinct characters
                for(int i=0; i<= 54; i++){
                    charArray[i] = flyweightPool.getCharacterFlyweight((char)i).getCharacterValue();
                }
                // remaining shared characters
                for(int i=55; i< 356; i++){
                    charArray[i] = flyweightPool.getCharacterFlyweight((char)(i%55)).getCharacterValue();
                }

                fontA = fontPool.getFontFlyweight(Font.SANS_SERIF, 12, Font.ITALIC);
                fontB = fontPool.getFontFlyweight(Font.SERIF, 10, Font.BOLD);

                runArray.addRun(0, 144, fontA);
                runArray.addRun(144, 212, fontB);

                return 1;
            }
        }.averageBytes());
    }

    @Test
    public void nonFlyweightTest() throws Exception {
        System.out.printf("The average size of text using Non-Flyweight is %.2f bytes%n", new SizeofUtil() {
            char [] charArray;
            Font[] fontArray;

            @Override
            protected int create() {
                charArray = new char[356];
                fontArray = new Font[356];

                for(int i=0;i< 356;i++)
                {
                    charArray[i] = new CharacterClass((char)i).getCharacter();
                    fontArray[i] = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                }
                return 1;
            }
        }.averageBytes());
    }
}
