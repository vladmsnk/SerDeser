package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.AnimalType;
import pr.miem.vlad.tools.Tools;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//[{\"petName\": \"Bob\", \"animalType\": \"DOG\"}, {\"petName\": \"Bill\", \"animalType\": \"CAT\"}]

public class PetDeserializer implements Deserializer<Pet> {

    public Pet fromJsonToObj(String jsonString) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonString);
        Map<String, String> mapOfJson = parseJsonObject.getMapOfJson();
        return new Pet.Builder().withPetName(mapOfJson.get("petName")).withAnimalType(AnimalType.valueOf(mapOfJson.get("animalType"))).build();
    }

    public ArrayList<Pet> fromJsonToList(String jsonStringOfPets) {
        ArrayList<String> arrayOfJsonObjects = Tools.splitJsonString(jsonStringOfPets);
        ArrayList<Pet> pets = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Pet pet = fromJsonToObj(jsonObject);
            pets.add(pet);
        }
        return pets;
    }
}
