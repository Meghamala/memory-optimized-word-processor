package flyweight;
import java.util.HashMap;

public class CharacterFactory{

    private static HashMap<Character, FlyweightCharacter> characterPool;

    private CharacterFactory(){
        characterPool = new HashMap<>();
    }

    // static class for creating single CharacterFactory instance
    private static class SingletonCharacter{
        private final static CharacterFactory Instance = new CharacterFactory();
    }

    public static CharacterFactory getInstance(){
        return SingletonCharacter.Instance;
    }

    public FlyweightCharacter getCharacterFlyweight(char character){

        if(!characterPool.containsKey(character)){
            characterPool.put(character, new FlyweightCharacter(character));
        }
        return characterPool.get(character);
    }

}