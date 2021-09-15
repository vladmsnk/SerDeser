package deserializations;

import entities.Person;
import entities.Pet;

import java.util.ArrayList;

public class PersonDeserializer implements Deserializer<Person> {
    public Person FromJsonToObj(String jsonStringOfPerson) {
        if (jsonStringOfPerson.isEmpty()) {
            return null;
        }
        jsonStringOfPerson = jsonStringOfPerson.replaceAll("\\s+", "");
        int index1 = jsonStringOfPerson.indexOf(":");
        int index2 = jsonStringOfPerson.indexOf(",");
        String personName = jsonStringOfPerson.substring(index1 + 2, index2 - 1);
        jsonStringOfPerson = jsonStringOfPerson.substring(index2 + 1);
        index1 = jsonStringOfPerson.indexOf(":");
        index2 = jsonStringOfPerson.indexOf(",");
        String personLastName = jsonStringOfPerson.substring(index1 + 2, index2 - 1);
        jsonStringOfPerson = jsonStringOfPerson.substring(index2 + 1);
        index1 = jsonStringOfPerson.indexOf(":");
        index2 = jsonStringOfPerson.indexOf(",");
        int moneyCount = Integer.parseInt(jsonStringOfPerson.substring(index1 + 2, index2 - 1));
        jsonStringOfPerson = jsonStringOfPerson.substring(index2 + 1);
        index1 = jsonStringOfPerson.indexOf("[");
        index2 = jsonStringOfPerson.indexOf("]");
        PetDeserializer petDeserializer = new PetDeserializer();
        ArrayList<Pet> personsPets = petDeserializer.FromJsonToList(jsonStringOfPerson.substring(index1, index2 + 1));
        return new Person.Builder().withPersonName(personName).withPersonLastName(personLastName).withMoneyCount(moneyCount).withPets(personsPets).build();
    }

    public ArrayList<Person> FromJsonToList(String jsonStringOfPeople) {
        if (jsonStringOfPeople.isEmpty()) {
            return null;
        }
        jsonStringOfPeople = jsonStringOfPeople.replaceAll("\\s+", "");
        ArrayList<Person> people = new ArrayList<>();
        jsonStringOfPeople = jsonStringOfPeople.substring(1, jsonStringOfPeople.length() - 1);
        while (!jsonStringOfPeople.isEmpty()) {
            int goal = jsonStringOfPeople.indexOf("]") + 2;
            people.add(FromJsonToObj(jsonStringOfPeople.substring(0 , goal)));
            if (goal == jsonStringOfPeople.length()) {
                jsonStringOfPeople = "";
            }
            else {
                jsonStringOfPeople = jsonStringOfPeople.substring(goal + 3);
            }
        }
        return people;
    }
}
