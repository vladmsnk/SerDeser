package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Pet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PetDeserializerTest {

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
    }
    @Test
    public void shouldCreatePetObject() {
        String jsonPet = "{\"petName\":\"Bill\", \"animalType\":\"DOG\"}";
        PetDeserializer petDeserializer = new PetDeserializer();
        Pet pet = petDeserializer.fromJsonToObj(jsonPet);
        assertEquals(pet.getPetName(), "Bill");
        assertEquals(pet.getAnimalType(), "DOG");
    }


}
