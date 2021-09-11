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
        String personName;
        String personLastName;
        int moneyCount;
        ArrayList<Pet> pets;
        int tmp1 = jsonString.indexOf(":");
        int tmp2 = jsonString.indexOf(",");
        personName = jsonString.substring(tmp1 + 2, tmp2 - 1);
        jsonString = jsonString.substring(tmp2 + 1);
        tmp1 = jsonString.indexOf(":");
        tmp2 = jsonString.indexOf(",");
        personLastName = jsonString.substring(tmp1 + 2, tmp2 - 1);
        jsonString = jsonString.substring(tmp2 + 1);
        tmp1 = jsonString.indexOf(":");
        tmp2 = jsonString.indexOf(",");
        moneyCount = Integer.parseInt(jsonString.substring(tmp1 + 2, tmp2 - 1));
        jsonString = jsonString.substring(tmp2 + 1);
        tmp1 = jsonString.indexOf("[");
        tmp2 = jsonString.indexOf("]");
        DesOfPet desOfPet = new DesOfPet();
        pets = desOfPet.FromJsonToList(jsonString.substring(tmp1, tmp2 + 1));
        Person person = new Person(personName, personLastName, moneyCount);
        for (Pet pet : pets) {
            person.assignPet(pet);
        }
        return person;
    }

    public ArrayList<Person> FromJsonToList(String jsonString) {
        return new ArrayList<>();
    }
}
