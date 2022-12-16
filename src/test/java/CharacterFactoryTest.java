import flyweight.CharacterFactory;
import flyweight.FlyweightCharacter;
import flyweight.FontFactory;
import flyweight.RunArray;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterFactoryTest {

    @Test
    public void TestGetFlyweightCharacter(){
        FlyweightCharacter charObjA, charObjB, charObjC, charObjD;
        CharacterFactory charInstanceA = CharacterFactory.getInstance();
        CharacterFactory charInstanceB = CharacterFactory.getInstance();
        assertSame(charInstanceA, charInstanceB);

        charObjA = charInstanceA.getCharacterFlyweight('a');
        charObjB = charInstanceA.getCharacterFlyweight('M');
        charObjC = charInstanceA.getCharacterFlyweight('0');
        charObjD = charInstanceA.getCharacterFlyweight('a');
        assertEquals('a', charObjA.getUnicodeCodePoint());
        assertEquals('M', charObjB.getCharacterValue());
        assertSame(charObjA, charObjD);
        assertNotSame(charObjC, charObjB);
    }

    @Test
    public void TestGetFlyweightFont(){
        Font flyweightFontA, flyweightFontB;
        FontFactory fontInstanceA = FontFactory.getInstance();
        flyweightFontA = fontInstanceA.getFontFlyweight(Font.DIALOG_INPUT, 12, Font.ITALIC);
        flyweightFontB = fontInstanceA.getFontFlyweight("Ariel", 14, Font.BOLD );
        assertFalse(flyweightFontA.equals(flyweightFontB));
    }

    @Test
    public void TestRunArray(){
        RunArray test = new RunArray();
        FontFactory fontInstance = FontFactory.getInstance();
        Font fontA, fontB;
        fontA = fontInstance.getFontFlyweight(Font.SANS_SERIF, 12, Font.ITALIC);
        fontB = fontInstance.getFontFlyweight(Font.SERIF, 10, Font.BOLD);

        assertTrue(test.addRun(0, 250, fontA));
        assertTrue(test.addRun(250, 10, fontB));
        assertTrue(test.appendRun(320, fontA));

        assertEquals(fontA, test.getFont((int)Math.floor(Math.random()*250)));
        assertEquals(fontB, test.getFont((int)Math.floor(Math.random()*(11)+250)));
        assertEquals(fontA, test.getFont((int)Math.floor(Math.random()*(321)+260)));
        assertNull(test.getFont(600));
    }
}