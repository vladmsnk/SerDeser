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
        int index1 = jsonStringOfStreet.indexOf(":") + 2;
        int index2 = jsonStringOfStreet.indexOf(",") - 1;
        String streetName = jsonStringOfStreet.substring(index1, index2);
        jsonStringOfStreet = jsonStringOfStreet.substring(index2 + 1);
        index1 = jsonStringOfStreet.indexOf("[");
        if (Tools.countEntry(jsonStringOfStreet, "streetName") == 0) {
            index2 = jsonStringOfStreet.length() - 1;
        }
        else {
            index2 = jsonStringOfStreet.indexOf("streetName") - 5;
        }
        HomeDeserializer homeDeserializer = new HomeDeserializer();
        ArrayList<Home> homes = homeDeserializer.FromJsonToList(jsonStringOfStreet.substring(index1, index2));
        return new Street.Builder().withStreetName(streetName).withHomes(homes).build();
    }

    public ArrayList<Street> FromJsonToList(String jsonStringOfStreet) {
        if (jsonStringOfStreet.isEmpty()) {
            return null;
        }
        ArrayList<Street> streets = new ArrayList<>();
        jsonStringOfStreet = jsonStringOfStreet.substring(1, jsonStringOfStreet.length() - 1);
        while (!jsonStringOfStreet.isEmpty()) {
            if (Tools.countEntry(jsonStringOfStreet, "streetName") == 1) {
                streets.add(FromJsonToObj(jsonStringOfStreet));
                jsonStringOfStreet = "";
                break;
            }
            else {
                int doubleDotIndex = jsonStringOfStreet.indexOf(":");
                int indexOfStreetName = jsonStringOfStreet.indexOf("streetName",doubleDotIndex) - 4;
                streets.add(FromJsonToObj(jsonStringOfStreet.substring(0, indexOfStreetName)));
                jsonStringOfStreet = jsonStringOfStreet.substring(indexOfStreetName + 3);
            }
        }
        return streets;
    }
}
