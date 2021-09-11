package deserializations;

import entities.Pet;
import java.util.ArrayList;
import java.util.List;


public class DesOfPet implements Deserializable<Pet>{
    public Pet FromJsonToObj(String jsonString) {
        if (jsonString.isEmpty()) {
            return null;
        }
        String name;
        String lastName;
        int tmp1 = jsonString.indexOf(":");
        int tmp2 = jsonString.indexOf(",");
        name = jsonString.substring(tmp1 + 2, tmp2 - 1);
        jsonString = jsonString.substring(tmp2 + 1, jsonString.length());
        tmp1 = jsonString.indexOf(":");
        tmp2 = jsonString.indexOf("}");
        lastName = jsonString.substring(tmp1 + 2, tmp2 - 1);
        jsonString = jsonString.substring(tmp2 + 1, jsonString.length());
        return new Pet(name, lastName);
    }
//    {"petName":"Bob", "petType":"Cat"}, {"petName":"Bob", "petType":"Cat"}
    public ArrayList<Pet> FromJsonToList(String jsonString) {
        if (jsonString.isEmpty()) {
            return null;
        }
        ArrayList<Pet> pets = new ArrayList<>();
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        while (!jsonString.isEmpty()) {
           int goal = jsonString.indexOf("}") + 1;
           pets.add(FromJsonToObj(jsonString.substring(0 , goal)));
           if (goal == jsonString.length()) {
               jsonString = "";
           }
           else {
               jsonString = jsonString.substring(goal + 3);
           }
        }
        return pets;
    }
}
