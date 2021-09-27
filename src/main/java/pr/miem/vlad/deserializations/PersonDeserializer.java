package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;

import java.util.ArrayList;
import java.util.Map;

public class PersonDeserializer implements Deserializer<Person> {
    public Person fromJsonToObj(String jsonStringOfPerson) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonStringOfPerson);
        Map<String, String> mapOfJson = parseJsonObject.getMapOfJson();
        for (String value : mapOfJson.values()) {
            System.out.println(value);
        }
        return null;
    }

    public ArrayList<Person> fromJsonToList(String jsonStringOfPeople) {
        return new ArrayList<Person>();
    }
}
