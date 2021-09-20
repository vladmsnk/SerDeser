package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.restrictions.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



//{"petName":"name", "animalType":"type"}

public class PetDeserializer implements Deserializer<Pet> {

    private static class Validation {
        private String jsonString;

        Validation(String jsonString) {
            this.jsonString = cleanStrFromSeparators(jsonString);
        }

        public String getJsonString() {
            return jsonString;
        }

        public void isCorrectFormat(String jsonString) {
            if (jsonString == null) {
                throw new NullPointerException("json string is null!");
            }
            else if (!jsonString.contains("petName") || !jsonString.contains("animalType") ||
                    !jsonString.startsWith("{") || !jsonString.endsWith("}")) {
                throw new IllegalArgumentException("Wrong json format!");
            }
        }

        public String cleanStrFromSeparators(String jsonString) {
            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1);
            }
            return  jsonString.replaceAll("\\s+", "");
        }

        public Map<String, String> convertJsonToMap(String jsonString) {
            Map<String, String> mapOfPet = new HashMap<>();
            int indexOfPetName = jsonString.indexOf("\"petName\"");
            int indexOfAnimalType = jsonString.indexOf("\"animalType\"");
            int indexOfComa = jsonString.indexOf(",");
            String petName = jsonString.substring(indexOfPetName + 11, indexOfComa - 1);
            String animalType = jsonString.substring(indexOfAnimalType + 14, jsonString.length() - 2);
            mapOfPet.put("petName", petName);
            mapOfPet.put("animalType", animalType);
            return mapOfPet;
        }

        public String parseSubJson() {
            int elementsFromSearch = 10;
            int indexOfPetName = jsonString.indexOf("petName", elementsFromSearch);
            String jsonStringOfPet = "";
            if (indexOfPetName != -1) {
                jsonStringOfPet = jsonString.substring(0, indexOfPetName - 3);
                jsonString = jsonString.substring(indexOfPetName - 2);
                System.out.println(jsonStringOfPet);

            }
            else {
                jsonStringOfPet = jsonString;
                jsonString = "";
            }
            return  jsonStringOfPet;
        }
    }

    public Pet fromJsonToObj(String jsonString) {
        Validation validation = new Validation(jsonString);
        Map<String, String> mapOfPet = new HashMap<>();
        try {
            validation.isCorrectFormat(jsonString);
            mapOfPet = validation.convertJsonToMap(jsonString);
        }
        catch (Exception ignored) {
        }
        return new Pet.Builder().withPetName(mapOfPet.get("petName")).withAnimalType(AnimalType.valueOf(mapOfPet.get("animalType"))).build();
    }

    public ArrayList<Pet> fromJsonToList(String jsonStringOfPets) {
        ArrayList<Pet> pets = new ArrayList<>();
        Validation validation = new Validation(jsonStringOfPets);
        while (!validation.getJsonString().isEmpty()) {
            String jsonString = validation.parseSubJson();
            validation.isCorrectFormat(jsonString);
            Map<String, String> mapOfPet = validation.convertJsonToMap(jsonString);
            Pet pet = new Pet.Builder().withPetName(mapOfPet.get("petName")).withAnimalType(AnimalType.valueOf(mapOfPet.get("animalType"))).build();
            pets.add(pet);
        }
        return pets;
    }
}
