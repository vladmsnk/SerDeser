package pr.miem.vlad.serializations;


import pr.miem.vlad.entities.Person;

import java.util.ArrayList;

public class PersonSerializer implements Serializer<Person> {
    public String objToJson(Person person) {
        if (person == null) {
            throw new NullPointerException("Person object does not exist!");
        }
        String pets = (new PetSerializer()).ListOfObjToJson(person.getPets());
        return "{" +
                "\"personName\": " + '"' + person.getName() + '"' +
                ", \"personLastName\": " + '"' + person.getLastName() + '"' +
                ", \"money\": " + '"' + person.getMoney() + '"' +
                ", \"pets\": " + pets +
                "}";
    }
    public String ListOfObjToJson(ArrayList<Person> listOfPeople) {
        if (listOfPeople == null) {
            return "[]";
        }
        String jsonStringOfPeople = "[";
        for (Person person : listOfPeople) {
            jsonStringOfPeople = jsonStringOfPeople.concat(objToJson(person));
            jsonStringOfPeople += ", ";
        }
        jsonStringOfPeople = jsonStringOfPeople.substring(0, jsonStringOfPeople.length() - 2);
        jsonStringOfPeople += "]";
        return jsonStringOfPeople;
    }

}
