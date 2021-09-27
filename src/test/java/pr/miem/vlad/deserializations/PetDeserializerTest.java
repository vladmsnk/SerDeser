package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Pet;

import java.util.ArrayList;
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
    }
    public void shouldParsePets() {
        String jsonPets = "[{\"petName\": \"Bob\", \"animalType\": \"DOG\"}, {\"petName\": \"Bill\", \"animalType\": \"CAT\"}, {\"petName\": \"Tom\", \"animalType\": \"BIRD\"}]";

    }
    @Test
    public void shouldCreatePetObject() {
        String jsonPet = "{\"petName\":\"Bill\", \"animalType\":\"DOG\"}";
        Pet pet = petDeserializer.fromJsonToObj(jsonPet);
        assertEquals(pet.getPetName(), "Bill");
        assertEquals(pet.getAnimalType(), "DOG");
    }

    @Test
    public void shouldCreateArrayOfPetObjects() {
        String jsonPets = "[{\"petName\": \"Bob\", \"animalType\": \"DOG\"}, {\"petName\": \"Bill\", \"animalType\": \"CAT\"}, {\"petName\": \"Tom\", \"animalType\": \"BIRD\"}]";
        ArrayList<Pet> pets = petDeserializer.fromJsonToList(jsonPets);
        assertEquals(pets.size(), 3);
        assertEquals(pets.get(0).getPetName(), "Bob");
        assertEquals(pets.get(0).getAnimalType(), "DOG");
        assertEquals(pets.get(1).getPetName(), "Bill");
        assertEquals(pets.get(1).getAnimalType(), "CAT");
        assertEquals(pets.get(2).getPetName(), "Tom");
        assertEquals(pets.get(2).getAnimalType(), "BIRD");
    }



}
