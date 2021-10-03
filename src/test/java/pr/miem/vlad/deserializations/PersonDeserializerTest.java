package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PersonDeserializerTest {
    private final PersonDeserializer personDeserializer = new PersonDeserializer();

    @Test
    public void shouldParsePerson() {
        String personJson = "{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"moneyCount\": 123, \"pets\": [{\"petName\": \"Bob\", \"animalType\": \"CAT\"}]}";
        ParseJsonObject parseJsonObject = new ParseJsonObject(personJson);
        Map<String, String> expectedPerson = new HashMap<>();
        Map<String, String> parsedPerson = parseJsonObject.getMapOfJson();
        assertTrue(parsedPerson.containsKey("personName"));
        assertTrue(parsedPerson.containsKey("personLastName"));
        assertTrue(parsedPerson.containsKey("moneyCount"));
        assertTrue(parsedPerson.containsKey("pets"));
        expectedPerson.put("personName", "Bob");
        expectedPerson.put("personLastName", "Ivanov");
        expectedPerson.put("moneyCount", "123");
        expectedPerson.put("pets", "{\"petName\": \"Bob\", \"animalType\": \"CAT\"}");
        assertEquals(expectedPerson, parsedPerson);
    }

    @Test
    public void shouldCreatePersonObject() {
        String personJson = "{\"personName\": \"Andrey\", \"personLastName\": \"Ivanov\", \"moneyCount\": 123, \"pets\": [{\"petName\": \"Bob\", \"animalType\": \"CAT\"}, {\"petName\": \"Bob1\", \"animalType\": \"DOG\"}]}";
        Person parsedPerson = personDeserializer.fromJsonToObj(personJson);
        ArrayList<Pet> expectedPets = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withPetName("Bob").withAnimalType(AnimalType.valueOf("CAT")).build();
        Pet pet2 = new Pet.Builder().withPetName("Bob1").withAnimalType(AnimalType.valueOf("DOG")).build();
        expectedPets.add(pet1);
        expectedPets.add(pet2);
        Person expectPerson = new Person.Builder()
                .withPersonName("Andrey")
                .withPersonLastName("Ivanov")
                .withMoneyCount(123)
                .withPets(expectedPets)
                .build();
        assertEquals(parsedPerson, expectPerson);
    }

    @Test
    public void shouldCreateArrayOfPersonObjects() {
        String jsonPeople = "[{\"personName\": \"Andrey\", \"personLastName\": \"Ivanov\", \"moneyCount\": 1000, \"pets\": [{\"petName\": \"Bob\", \"animalType\": \"BIRD\"}]}, {\"personName\": \"Vova\", \"personLastName\": \"Gerasimov\", \"moneyCount\": 213, \"pets\": [{\"petName\": \"Tom\", \"animalType\": \"CAT\"}]}]";
        ArrayList<Person> parsedPeople = personDeserializer.fromJsonToList(jsonPeople);
        ArrayList<Person> expectedPeople = new ArrayList<>();
        ArrayList<Pet> expectedPets1 = new ArrayList<>();
        Pet pet1 = new Pet.Builder().withPetName("Bob").withAnimalType(AnimalType.valueOf("BIRD")).build();
        expectedPets1.add(pet1);
        Person person1 = new Person.Builder()
                .withPersonName("Andrey")
                .withPersonLastName("Ivanov")
                .withMoneyCount(1000)
                .withPets(expectedPets1)
                .build();

        ArrayList<Pet> expectedPets2 = new ArrayList<>();
        Pet pet2 = new Pet.Builder().withPetName("Tom").withAnimalType(AnimalType.valueOf("CAT")).build();
        expectedPets2.add(pet2);
        Person person2 = new Person.Builder()
                .withPersonName("Vova")
                .withPersonLastName("Gerasimov")
                .withMoneyCount(213)
                .withPets(expectedPets2)
                .build();

        expectedPeople.add(person1);
        expectedPeople.add(person2);
        assertEquals(parsedPeople, expectedPeople);
    }
}
