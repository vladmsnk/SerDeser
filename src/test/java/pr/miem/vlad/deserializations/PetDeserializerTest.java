package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Pet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PetDeserializerTest {
    private final PetDeserializer petDeserializer = new PetDeserializer();

    @Test
    public void shouldParsePet() {
        String jsonPet = "{\"petName\":\"Bob\", \"animalType\":\"CAT\"}";
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonPet);
        Map<String, String> expectedPet = new HashMap<>();
        Map<String, String> parsedPet = parseJsonObject.getMapOfJson();
        expectedPet.put("petName", "Bob");
        expectedPet.put("animalType", "CAT");
        assertTrue(parsedPet.containsKey("petName"));
        assertTrue(parsedPet.containsKey("animalType"));
        assertEquals(expectedPet, parsedPet);
        Pet pet = petDeserializer.fromJsonToObj(jsonPet);
        assertEquals(pet.getPetName(), "Bob");
        assertEquals(pet.getAnimalType(), "CAT");
    }


}
