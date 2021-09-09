package serializations;


import java.util.ArrayList;
import entities.Pet;

public class SerOfPet implements Serializable<Pet>{
    public String objToJson(Pet obj) {
        return "{" +
                "\"petName\":" + '"' + obj.getPetName() + '"' +
                ", \"petType\":" + '"' + obj.getPetType() + '"' +
                "}";
    }
    public String ListOfObjToJson(ArrayList<Pet> listOfObj) {

        String jsonString = (listOfObj.size() > 1) ? "{" : "";

        for (Pet pet : listOfObj) {
            jsonString = jsonString.concat("{" +
                    "\"petName\":" + '"' + pet.getPetName() + '"' +
                    ", \"petType\":" + '"' + pet.getPetType() + '"' +
                    "}, ");
        }
        jsonString = jsonString.substring(0, jsonString.length() - 2);
        if (listOfObj.size() > 1) {
            jsonString += "}";
        }
        return  jsonString;
    }
}
