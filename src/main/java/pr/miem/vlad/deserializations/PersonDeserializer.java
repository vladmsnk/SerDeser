package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.tools.Utils;
import java.util.ArrayList;
import java.util.Map;

public class PersonDeserializer implements Deserializer<Person> {
    public Person fromJsonToObj(String jsonStringOfPerson) {
        JsonParser jsonParser = new JsonParser(jsonStringOfPerson);
        Map<String, String> jsonMap = jsonParser.jsonParse();
        PetDeserializer petDeserializer = new PetDeserializer();
        ArrayList<Pet> pets = petDeserializer.fromJsonToList(jsonMap.get("pets"));
        return new Person.Builder()
                .withName(jsonMap.get("name"))
                .withLastName(jsonMap.get("lastName"))
                .withMoney(Integer.parseInt(jsonMap.get("money")))
                .withPets(pets)
                .build();
    }
    public ArrayList<Person> fromJsonToList(String jsonStringOfPeople) {
        ArrayList<String> arrayOfJsonObjects = Utils.splitJsonString(jsonStringOfPeople);
        ArrayList<Person> people = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Person person = fromJsonToObj(jsonObject);
            people.add(person);
        }
        return people;
    }
}
