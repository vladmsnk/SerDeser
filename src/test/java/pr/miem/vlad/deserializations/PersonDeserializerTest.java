package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.Animal;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonDeserializerTest {
    private final PersonDeserializer personDeserializer = new PersonDeserializer();


    @Test
        public void shouldCreatePersonObject() {
        String personJson = "{\"personName\": \"Andrey\", \"personLastName\": \"Ivanov\", \"money\": 123, \"pets\": [{\"petName\": \"Bob\", \"animalType\": \"CAT\"}, {\"petName\": \"Bob1\", \"animalType\": \"DOG\"}]}";
        Person parsedPerson = personDeserializer.fromJsonToObj(personJson);
        ArrayList<Pet> expectedPets = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withName("Bob").withAnimal(Animal.valueOf("CAT")).build();
        Pet pet2 = new Pet.Builder().withName("Bob1").withAnimal(Animal.valueOf("DOG")).build();
        expectedPets.add(pet1);
        expectedPets.add(pet2);
        Person expectPerson = new Person.Builder()
                .withName("Andrey")
                .withLastName("Ivanov")
                .withMoney(123)
                .withPets(expectedPets)
                .build();
        assertEquals(parsedPerson, expectPerson);
    }

    @Test
    public void shouldCreateArrayOfPersonObjects() {
        String jsonPeople = "[{\"personName\": \"Andrey\", \"personLastName\": \"Ivanov\", \"money\": 1000, \"pets\": [{\"petName\": \"Bob\", \"animalType\": \"BIRD\"}]}, {\"personName\": \"Vova\", \"personLastName\": \"Gerasimov\", \"money\": 213, \"pets\": [{\"petName\": \"Tom\", \"animalType\": \"CAT\"}]}]";
        ArrayList<Person> parsedPeople = personDeserializer.fromJsonToList(jsonPeople);
        ArrayList<Person> expectedPeople = new ArrayList<>();
        ArrayList<Pet> expectedPets1 = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withName("Bob").withAnimal(Animal.valueOf("BIRD")).build();
        expectedPets1.add(pet1);
        Person person1 = new Person.Builder()
                .withName("Andrey")
                .withLastName("Ivanov")
                .withMoney(1000)
                .withPets(expectedPets1)
                .build();

        ArrayList<Pet> expectedPets2 = new ArrayList<>();
        Pet pet2 = new Pet.Builder().withName("Tom").withAnimal(Animal.valueOf("CAT")).build();
        expectedPets2.add(pet2);
        Person person2 = new Person.Builder()
                .withName("Vova")
                .withLastName("Gerasimov")
                .withMoney(213)
                .withPets(expectedPets2)
                .build();

        expectedPeople.add(person1);
        expectedPeople.add(person2);
        assertEquals(parsedPeople, expectedPeople);
    }
}
