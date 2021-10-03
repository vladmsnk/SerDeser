package pr.miem.vlad.serializations;


import java.util.ArrayList;
import pr.miem.vlad.entities.Pet;

public class PetSerializer implements Serializer<Pet> {

    public String objToJson(Pet pet) {
        if (pet == null) {
            throw new NullPointerException("Pet object does not exist!");
        }
        return "{" +
                "\"petName\": " + '"' + pet.getPetName() + '"' +
                ", \"animalType\": " + '"' + pet.getAnimalType() + '"' +
                "}";
    }

    public String ListOfObjToJson(ArrayList<Pet> listOfPets) {
        if (listOfPets == null) {
            return "";
        }
        String jsonStringOfPets = (listOfPets.size() > 1) ? "[" : "";
        for (Pet pet : listOfPets) {
            jsonStringOfPets = jsonStringOfPets.concat(objToJson(pet));
            jsonStringOfPets += ", ";
        }
        jsonStringOfPets = jsonStringOfPets.substring(0, jsonStringOfPets.length() - 2);
        if (listOfPets.size() > 1) {
            jsonStringOfPets += "]";
        }
        return  jsonStringOfPets;
    }
}
