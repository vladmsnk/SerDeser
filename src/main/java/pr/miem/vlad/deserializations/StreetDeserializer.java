package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.House;
import pr.miem.vlad.entities.Street;
import pr.miem.vlad.tools.Utils;

import java.util.ArrayList;
import java.util.Map;

public class StreetDeserializer implements Deserializer<Street> {
    public Street fromJsonToObj(String jsonStringOfStreet) {
        JsonParser jsonParser = new JsonParser(jsonStringOfStreet);
        Map<String, String> mapOfJson = jsonParser.jsonParse();
        HouseDeserializer houseDeserializer = new HouseDeserializer();
        ArrayList<House> houses = houseDeserializer.fromJsonToList(mapOfJson.get("houses"));
        return new Street.Builder()
                .withStreetName(mapOfJson.get("streetName"))
                .withHouses(houses)
                .build();
    }

    public ArrayList<Street> fromJsonToList(String jsonStringOfStreets) {
        ArrayList<String> arrayOfJsonObjects = Utils.splitJsonString(jsonStringOfStreets);
        ArrayList<Street> streets = new ArrayList<>();
        for (String jsonObject: arrayOfJsonObjects) {
            Street street = fromJsonToObj(jsonObject);
            streets.add(street);
        }
        return streets;
    }
}
