package deserializations;

import entities.Pet;
import restrictions.Animals;

import java.util.ArrayList;



public class PetDeserializer implements Deserializer<Pet> {
    public Pet FromJsonToObj(String jsonStringOfPet) {
        if (jsonStringOfPet.isEmpty()) {
            return null;
        }
        int index1 = jsonStringOfPet.indexOf(":");
        int index2  = jsonStringOfPet.indexOf(",");
        String petName = jsonStringOfPet.substring(index1 + 2, index2 - 1);
        jsonStringOfPet = jsonStringOfPet.substring(index2 + 1, jsonStringOfPet.length());
        index1 = jsonStringOfPet.indexOf(":");
        index2 = jsonStringOfPet.indexOf("}");
        String animalType = jsonStringOfPet.substring(index1 + 2, index2 - 1);
        jsonStringOfPet = jsonStringOfPet.substring(index2 + 1);
        return new Pet.Builder().withPetName(petName).withAnimalType(Animals.valueOf(animalType)).build();
    }
//  example  {{"petName":"Bob", "petType":"Cat"}, {"petName":"Bob", "petType":"Cat"}}
    public ArrayList<Pet> FromJsonToList(String jsonStringOfPets) {
        if (jsonStringOfPets.isEmpty()) {
            return null;
        }
        ArrayList<Pet> pets = new ArrayList<>();
        jsonStringOfPets = jsonStringOfPets.substring(1, jsonStringOfPets.length() - 1);
        while (!jsonStringOfPets.isEmpty()) {
           int goal = jsonStringOfPets.indexOf("}") + 1;
           pets.add(FromJsonToObj(jsonStringOfPets.substring(0 , goal)));
           if (goal == jsonStringOfPets.length()) {
               jsonStringOfPets = "";
           }
           else {
               jsonStringOfPets = jsonStringOfPets.substring(goal + 3);
           }
        }
        return pets;
    }
}
