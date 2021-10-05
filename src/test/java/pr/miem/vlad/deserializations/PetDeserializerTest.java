package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.Animal;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PetDeserializerTest {

    private final PetDeserializer petDeserializer = new PetDeserializer();

    @Test
    public void shouldCreatePetObject() {
        String jsonPet = "{\"name\":\"Bill\", \"animal\":\"DOG\"}";
        Pet parsedPet = petDeserializer.fromJsonToObj(jsonPet);
        Pet expectedPet = new Pet.Builder()
                .withName("Bill")
                .withAnimal(Animal.valueOf("DOG"))
                .build();
        assertEquals(expectedPet, parsedPet);
    }

    @Test
    public void shouldCreateArrayOfPetObjects() {
        String jsonPets = "[{\"name\": \"Bob\", \"animal\": \"DOG\"}, {\"name\": \"Bill\", \"animal\": \"CAT\"}]";
        ArrayList<Pet> parsedPets = petDeserializer.fromJsonToList(jsonPets);
        ArrayList<Pet> expectedPets = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withName("Bob").withAnimal(Animal.valueOf("DOG")).build();
        Pet pet2 = new Pet.Builder().withName("Bill").withAnimal(Animal.valueOf("CAT")).build();
        expectedPets.add(pet1);
        expectedPets.add(pet2);
        assertEquals(parsedPets, expectedPets);
    }
}
