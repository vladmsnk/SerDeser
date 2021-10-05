package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.House;
import pr.miem.vlad.entities.Apartment;
import pr.miem.vlad.tools.Utils;

import java.util.ArrayList;
import java.util.Map;


public class HouseDeserializer implements Deserializer<House> {
    public House fromJsonToObj(String jsonStringOfHome) {
        JsonParser jsonParser = new JsonParser(jsonStringOfHome);
        Map<String, String> mapOfJson = jsonParser.jsonParse();
        ApartmentDeserializer apartmentDeserializer = new ApartmentDeserializer();
        ArrayList<Apartment> apartments = apartmentDeserializer.fromJsonToList(mapOfJson.get("apartments"));
        return new House.Builder()
                .withHouseNumber(Integer.parseInt(mapOfJson.get("houseNumber")))
                .withApartment(apartments)
                .build();

    }

    public ArrayList<House> fromJsonToList(String jsonStringOfHomes) {
        ArrayList<String> arrayOfJsonObjects = Utils.splitJsonString(jsonStringOfHomes);
        ArrayList<House> houses = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            House house = fromJsonToObj(jsonObject);
            houses.add(house);
        }
        return houses;
    }
}
