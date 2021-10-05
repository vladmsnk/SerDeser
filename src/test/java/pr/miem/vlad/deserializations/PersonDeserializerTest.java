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
        String personJson = "{\"name\": \"Andrey\", \"lastName\": \"Ivanov\", \"money\": 123, \"pets\": [{\"name\": \"Bob\", \"animal\": \"CAT\"}, {\"name\": \"Bob1\", \"animal\": \"DOG\"}]}";
        Person parsedPerson = personDeserializer.fromJsonToObj(personJson);
        Person expectPerson = createPerson();
        assertEquals(parsedPerson, expectPerson);
    }

    @Test
    public void shouldCreateArrayOfPersonObjects() {
        String jsonPeople = "[{\"name\": \"Andrey\", \"lastName\": \"Ivanov\", \"money\": 1000, \"pets\": [{\"name\": \"Bob\", \"animal\": \"BIRD\"}]}, {\"name\": \"Vova\", \"lastName\": \"Gerasimov\", \"money\": 213, \"pets\": [{\"name\": \"Tom\", \"animal\": \"CAT\"}]}]";
        ArrayList<Person> parsedPeople = personDeserializer.fromJsonToList(jsonPeople);
        ArrayList<Person> expectedPeople = createPersonList();
        assertEquals(parsedPeople, expectedPeople);
    }

    private Person createPerson() {
        ArrayList<Pet> expectedPets = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withName("Bob").withAnimal(Animal.valueOf("CAT")).build();
        Pet pet2 = new Pet.Builder().withName("Bob1").withAnimal(Animal.valueOf("DOG")).build();
        expectedPets.add(pet1);
        expectedPets.add(pet2);
        return new Person.Builder()
                .withName("Andrey")
                .withLastName("Ivanov")
                .withMoney(123)
                .withPets(expectedPets)
                .build();
    }

    private ArrayList<Person> createPersonList() {
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
        return expectedPeople;
    }
}
