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
        Pet expectedPet = createPet();
        assertEquals(expectedPet, parsedPet);
    }

    @Test
    public void shouldCreateArrayOfPetObjects() {
        String jsonPets = "[{\"name\": \"Bob\", \"animal\": \"DOG\"}, {\"name\": \"Bill\", \"animal\": \"CAT\"}, {\"name\": \"Tom\", \"animal\": \"BIRD\"}]";
        ArrayList<Pet> parsedPets = petDeserializer.fromJsonToList(jsonPets);
        ArrayList<Pet> expectedPets = createPetList();
        assertEquals(parsedPets, expectedPets);
    }

    private Pet createPet() {
        return new Pet.Builder()
                .withName("Bill")
                .withAnimal(Animal.valueOf("DOG"))
                .build();
    }

    private ArrayList<Pet> createPetList() {
        Pet pet1 = new Pet.Builder().withName("Bob").withAnimal(Animal.valueOf("DOG")).build();
        Pet pet2 = new Pet.Builder().withName("Bill").withAnimal(Animal.valueOf("CAT")).build();
        Pet pet3 = new Pet.Builder().withName("Tom").withAnimal(Animal.valueOf("BIRD")).build();
        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
        return pets;
    }
}
