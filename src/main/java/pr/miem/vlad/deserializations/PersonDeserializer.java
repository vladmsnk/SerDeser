package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.tools.Tools;

import java.util.ArrayList;
import java.util.Map;

public class PersonDeserializer implements Deserializer<Person> {
    public Person fromJsonToObj(String jsonStringOfPerson) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonStringOfPerson);
        Map<String, String> mapOfJson = parseJsonObject.jsonParse();
        PetDeserializer petDeserializer = new PetDeserializer();
        ArrayList<Pet> pets = petDeserializer.fromJsonToList(mapOfJson.get("pets"));
        return new Person.Builder()
                .withPersonName(mapOfJson.get("personName"))
                .withPersonLastName(mapOfJson.get("personLastName"))
                .withMoneyCount(Integer.parseInt(mapOfJson.get("moneyCount")))
                .withPets(pets)
                .build();
    }
    public ArrayList<Person> fromJsonToList(String jsonStringOfPeople) {
        ArrayList<String> arrayOfJsonObjects = Tools.splitJsonString(jsonStringOfPeople);
        ArrayList<Person> people = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Person person = fromJsonToObj(jsonObject);
            people.add(person);
        }
        return people;
    }
}
