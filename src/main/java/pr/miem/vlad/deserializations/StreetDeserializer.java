package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Home;
import pr.miem.vlad.entities.Room;
import pr.miem.vlad.entities.Street;
import pr.miem.vlad.tools.Tools;

import java.util.ArrayList;
import java.util.Map;

public class StreetDeserializer implements Deserializer<Street> {
    public Street fromJsonToObj(String jsonStringOfStreet) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonStringOfStreet);
        Map<String, String> mapOfJson = parseJsonObject.getMapOfJson();
        HomeDeserializer homeDeserializer = new HomeDeserializer();
        ArrayList<Home> homes = homeDeserializer.fromJsonToList(mapOfJson.get("homes"));
        return new Street.Builder()
                .withStreetName(mapOfJson.get("streetName"))
                .withHomes(homes)
                .build();
    }

    public ArrayList<Street> fromJsonToList(String jsonStringOfStreets) {
        ArrayList<String> arrayOfJsonObjects = Tools.splitJsonString(jsonStringOfStreets);
        ArrayList<Street> streets = new ArrayList<>();
        for (String jsonObject: arrayOfJsonObjects) {
            Street street = fromJsonToObj(jsonObject);
            streets.add(street);
        }
        return streets;
    }
}
