package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Person;

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
}
