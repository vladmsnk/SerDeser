package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PetDeserializerTest {

    private final PetDeserializer petDeserializer = new PetDeserializer();

    @Test
    public void shouldCreatePetObject() {
        String jsonPet = "{\"petName\":\"Bill\", \"animalType\":\"DOG\"}";
        Pet parsedPet = petDeserializer.fromJsonToObj(jsonPet);
        Pet expectedPet = new Pet.Builder()
                .withPetName("Bill")
                .withAnimalType(AnimalType.valueOf("DOG"))
                .build();
        assertEquals(expectedPet, parsedPet);
    }

    @Test
    public void shouldCreateArrayOfPetObjects() {
        String jsonPets = "[{\"petName\": \"Bob\", \"animalType\": \"DOG\"}, {\"petName\": \"Bill\", \"animalType\": \"CAT\"}, {\"petName\": \"Tom\", \"animalType\": \"BIRD\"}]";
        ArrayList<Pet> parsedPets = petDeserializer.fromJsonToList(jsonPets);
        ArrayList<Pet> exptectedPets = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withPetName("Bob").withAnimalType(AnimalType.valueOf("DOG")).build();
        Pet pet2 = new Pet.Builder().withPetName("Bill").withAnimalType(AnimalType.valueOf("CAT")).build();
        exptectedPets.add(pet1);
        exptectedPets.add(pet2);
        for (int i = 0 ; i < exptectedPets.size(); i++) {
            assertEquals(exptectedPets.get(i),parsedPets.get(i));
        }

    }



}
