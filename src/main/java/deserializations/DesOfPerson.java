package deserializations;

import entities.Person;
import entities.Pet;

import java.util.ArrayList;

//{"personName":"Name", "personLastName":"lastName", "moneyCount":"count", "petCount":"count", "pets":[]}
public class DesOfPerson implements Deserializable<Person> {
    public Person FromJsonToObj(String jsonString) {
        if (jsonString.isEmpty()) {
            return null;
        }
        int tmp1 = jsonString.indexOf(":");
        int tmp2 = jsonString.indexOf(",");
        String personName = jsonString.substring(tmp1 + 2, tmp2 - 1);
        jsonString = jsonString.substring(tmp2 + 1);
        tmp1 = jsonString.indexOf(":");
        tmp2 = jsonString.indexOf(",");
        String personLastName = jsonString.substring(tmp1 + 2, tmp2 - 1);
        jsonString = jsonString.substring(tmp2 + 1);
        tmp1 = jsonString.indexOf(":");
        tmp2 = jsonString.indexOf(",");
        int moneyCount = Integer.parseInt(jsonString.substring(tmp1 + 2, tmp2 - 1));
        jsonString = jsonString.substring(tmp2 + 1);
        tmp1 = jsonString.indexOf("[");
        tmp2 = jsonString.indexOf("]");
        //when have jsonString that looks like something this [{...}, {...}]}
        //we are able to implement desirialisation for the json string of pets and get the array of pets
        DesOfPet desOfPet = new DesOfPet();
        //jsonString.substring(tmp1, tmp2 + 1) == [{...}, {...}], then [ ] will be deleted
        ArrayList<Pet> pets = desOfPet.FromJsonToList(jsonString.substring(tmp1, tmp2 + 1));
        Person person = new Person(personName, personLastName, moneyCount);
        for (Pet pet : pets) {
            person.assignPet(pet);
        }
        return person;
    }

    public ArrayList<Person> FromJsonToList(String jsonString) {
        if (jsonString.isEmpty()) {
            return null;
        }
        ArrayList<Person> people = new ArrayList<>();
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        while (!jsonString.isEmpty()) {
            int goal = jsonString.indexOf("]") + 2;
            people.add(FromJsonToObj(jsonString.substring(0 , goal)));
            if (goal == jsonString.length()) {
                jsonString = "";
            }
            else {
                jsonString = jsonString.substring(goal + 3);
            }
        }
        return people;
    }
}
