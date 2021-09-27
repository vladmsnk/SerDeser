package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;

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
        Person person = personDeserializer.fromJsonToObj(personJson);
        assertEquals(person.getPersonName(), "Andrey");
        assertEquals(person.getPersonLastName(), "Ivanov");
        assertEquals(person.getMoneyCount(), "123");
        ArrayList<Pet> pets = person.getPersonsPet();
        assertEquals(pets.get(0).getPetName(), "Bob");
        assertEquals(pets.get(0).getAnimalType(), "CAT");
        assertEquals(pets.get(1).getPetName(), "Bob1");
        assertEquals(pets.get(1).getAnimalType(), "DOG");
    }
}