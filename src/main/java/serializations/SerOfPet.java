package serializations;


import java.util.List;
import entities.Pet;

public class SerOfPet implements Srializable<Pet>{
    public String objToJson(Pet obj) {
        return "{" +
                "\"petName\":" + '"' + obj.getPetName() + '"' +
                ", \"petType\":" + '"' + obj.getPetType() + '"' +
                "}";
    }
    public String ListOfObjToJson(List<Pet> listOfObj) {
        String jsonString = "{";
        for (Pet pet : listOfObj) {
            jsonString = jsonString.concat("{" +
                    "\"petName\":" + '"' + pet.getPetName() + '"' +
                    ", \"petType\":" + '"' + pet.getPetType() + '"' +
                    "}, ");
        }
        jsonString = jsonString.substring(0, jsonString.length() - 1);
        jsonString += "}";
        return  jsonString;
    }
}
