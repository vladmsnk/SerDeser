package deserializations;

import entities.Home;
import entities.Street;
import tools.Tools;

import java.util.ArrayList;

public class StreetDeserializer implements Deserializer<Street> {
    public Street FromJsonToObj(String jsonStringOfStreet) {
        if (jsonStringOfStreet == null) {
            return null;
        }
        jsonStringOfStreet = jsonStringOfStreet.replaceAll("\\s+", "");
        int index1 = jsonStringOfStreet.indexOf(":") + 2;
        int index2 = jsonStringOfStreet.indexOf(",") - 1;
        String streetName = jsonStringOfStreet.substring(index1, index2);
        jsonStringOfStreet = jsonStringOfStreet.substring(index2 + 1);
        index1 = jsonStringOfStreet.indexOf("[");
        if (Tools.countEntry(jsonStringOfStreet, "streetName") == 0) {
            index2 = jsonStringOfStreet.length() - 1;
        }
        else {
            index2 = jsonStringOfStreet.indexOf("streetName") - 4;
        }
        HomeDeserializer homeDeserializer = new HomeDeserializer();
        ArrayList<Home> homes = homeDeserializer.FromJsonToList(jsonStringOfStreet.substring(index1, index2));
        return new Street.Builder().withStreetName(streetName).withHomes(homes).build();
    }

    public ArrayList<Street> FromJsonToList(String jsonStringOfStreets) {
        if (jsonStringOfStreets.isEmpty()) {
            return null;
        }
        jsonStringOfStreets = jsonStringOfStreets.replaceAll("\\s+", "");
        ArrayList<Street> streets = new ArrayList<>();
        jsonStringOfStreets = jsonStringOfStreets.substring(1, jsonStringOfStreets.length() - 1);
        while (!jsonStringOfStreets.isEmpty()) {
            if (Tools.countEntry(jsonStringOfStreets, "streetName") == 1) {
                streets.add(FromJsonToObj(jsonStringOfStreets));
                jsonStringOfStreets = "";
                break;
            }
            else {
                int doubleDotIndex = jsonStringOfStreets.indexOf(":");
                int indexOfStreetName = jsonStringOfStreets.indexOf("streetName",doubleDotIndex) - 3;
                streets.add(FromJsonToObj(jsonStringOfStreets.substring(0, indexOfStreetName)));
                jsonStringOfStreets = jsonStringOfStreets.substring(indexOfStreetName + 3);
            }
        }
        return streets;
    }
}
