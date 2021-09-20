package serializations;


import entities.Person;

import java.util.ArrayList;

public class PersonSerializer implements Serializer<Person> {
    public String objToJson(Person person) {
        if (person == null) {
            throw new NullPointerException("Person object does not exist!");
        }
        String pets = "[" + (new PetSerializer()).ListOfObjToJson(person.getPersonsPet()) + "]";
        return "{" +
                "\"personName\": " + '"' + person.getPersonName() + '"' +
                ", \"personLastName\": " + '"' + person.getPersonLastName() + '"' +
                ", \"moneyCount\": " + '"' + person.getMoneyCount() + '"' +
                ", \"pets\": " + pets +
                "}";
    }
    public String ListOfObjToJson(ArrayList<Person> listOfPeople) {
        if (listOfPeople == null) {
            return "";
        }
        String jsonStringOfPeople = (listOfPeople.size() > 1) ? "[" : "";
        for (Person person : listOfPeople) {
            jsonStringOfPeople = jsonStringOfPeople.concat(objToJson(person));
            jsonStringOfPeople += ", ";
        }
        jsonStringOfPeople = jsonStringOfPeople.substring(0, jsonStringOfPeople.length() - 2);
        if (listOfPeople.size() > 1) {
            jsonStringOfPeople += "]";
        }
        return jsonStringOfPeople;
    }

}
