package deserializations;

import entities.Person;
import entities.Pet;

import java.util.ArrayList;

public class PersonDeserializer implements Deserializer<Person> {
    public Person FromJsonToObj(String jsonStringOfPerson) {
        if (jsonStringOfPerson.isEmpty()) {
            return null;
        }
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

    public ArrayList<Person> FromJsonToList(String jsonStrinOfPeople) {
        if (jsonStrinOfPeople.isEmpty()) {
            return null;
        }
        ArrayList<Person> people = new ArrayList<>();
        jsonStrinOfPeople = jsonStrinOfPeople.substring(1, jsonStrinOfPeople.length() - 1);
        while (!jsonStrinOfPeople.isEmpty()) {
            int goal = jsonStrinOfPeople.indexOf("]") + 2;
            people.add(FromJsonToObj(jsonStrinOfPeople.substring(0 , goal)));
            if (goal == jsonStrinOfPeople.length()) {
                jsonStrinOfPeople = "";
            }
            else {
                jsonStrinOfPeople = jsonStrinOfPeople.substring(goal + 3);
            }
        }
        return people;
    }
}
