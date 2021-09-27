package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PetDeserializer implements Deserializer<Pet> {



    public Pet fromJsonToObj(String jsonString) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonString);
        Map<String, String> mapOfJson = parseJsonObject.getMapOfJson();
        return new Pet.Builder().withPetName(mapOfJson.get("petName")).withAnimalType(AnimalType.valueOf(mapOfJson.get("animalType"))).build();
    }

    public ArrayList<Pet> fromJsonToList(String jsonStringOfPets) {

        return new ArrayList<Pet>();
    }
}
