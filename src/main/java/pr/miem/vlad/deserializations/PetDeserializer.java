package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.Animal;
import pr.miem.vlad.tools.Utils;
import java.util.ArrayList;
import java.util.Map;


public class PetDeserializer implements Deserializer<Pet> {

    public Pet fromJsonToObj(String jsonString) {
        JsonParser jsonParser = new JsonParser(jsonString);
        Map<String, String> jsonMap = jsonParser.jsonParse();
        if (!(jsonMap.containsKey("name") && jsonMap.containsKey("animal"))) {
            throw new IllegalArgumentException("Unknown key!");
        }
        return new Pet.Builder().withName(jsonMap.get("name")).withAnimal(Animal.valueOf(jsonMap.get("animal"))).build();
    }

    public ArrayList<Pet> fromJsonToList(String jsonStringOfPets) {
        ArrayList<String> arrayOfJsonObjects = Utils.splitJsonString(jsonStringOfPets);
        ArrayList<Pet> pets = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Pet pet = fromJsonToObj(jsonObject);
            pets.add(pet);
        }
        return pets;
    }
}
