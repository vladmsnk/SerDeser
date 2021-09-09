package serializations;


import entities.Person;
import entities.Pet;

import java.util.ArrayList;

public class SerOfPerson implements Serializable<Person>{
    public String objToJson(Person obj) {
        String pets = "[" + (new SerOfPet()).ListOfObjToJson(obj.getPersonsPet()) + "]";
        return "{" +
                "\"personName\":" + '"' + obj.getPersonName() + '"' +
                ", \"personLastName\":" + '"' + obj.getPersonLastName() + '"' +
                ", \"moneyCount\":" + '"' + obj.getMoneyCount() + '"' +
                ", \"petCount\":" + '"' + obj.getPetCount() + '"' +
                ", \"pets\":" + pets +
                "}";
    }
    public String ListOfObjToJson(ArrayList<Person> listOfObj) {
        if (listOfObj.size() == 0) {
            return "";
        }
        String jsonString = (listOfObj.size() > 1) ? "{" : "";
        for (Person person : listOfObj) {
            jsonString = jsonString.concat(objToJson(person));
            jsonString += ", ";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 2);
        if (listOfObj.size() > 1) {
            jsonString += "}";
        }
        return jsonString;
    }

}
