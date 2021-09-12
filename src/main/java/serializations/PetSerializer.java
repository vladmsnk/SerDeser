package serializations;


import java.util.ArrayList;
import entities.Pet;

public class SerOfPet implements Serializable<Pet>{


    public String objToJson(Pet pet) {
        if (pet == null) {
            throw new NullPointerException("Pet object does not exist!");
        }
        return "{" +
                "\"petName\":" + '"' + pet.getPetName() + '"' +
                ", \"animalType\":" + '"' + pet.getAnimalType() + '"' +
                "}";
    }

    public String ListOfObjToJson(ArrayList<Pet> listOfPets) {
        if (listOfPets == null) {
            throw new NullPointerException("List of Pet objects does not exist!");
        }
        String jsonStringOfPets = (listOfPets.size() > 1) ? "{" : "";
        for (Pet pet : listOfPets) {
            jsonStringOfPets = jsonStringOfPets.concat("{" +
                    "\"petName\":" + '"' + pet.getPetName() + '"' +
                    ", \"petType\":" + '"' + pet.getAnimalType() + '"' +
                    "}, ");
        }
        jsonStringOfPets = jsonStringOfPets.substring(0, jsonStringOfPets.length() - 2);
        if (listOfPets.size() > 1) {
            jsonStringOfPets += "}";
        }
        return  jsonStringOfPets;
    }
}
