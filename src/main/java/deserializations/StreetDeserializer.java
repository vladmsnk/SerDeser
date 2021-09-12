package deserializations;

import entities.Home;
import entities.Room;
import entities.Street;
import tools.Tools;

import java.util.ArrayList;

public class StreetDeserializer implements Deserializable<Street>{
    public Street FromJsonToObj(String jsonStringOfStreet) {
        if (jsonStringOfStreet == null) {
            return null;
        }
        int tmp1 = jsonStringOfStreet.indexOf(":") + 2;
        int tmp2 = jsonStringOfStreet.indexOf(",") - 1;
        String streetName = jsonStringOfStreet.substring(tmp1, tmp2);
        jsonStringOfStreet = jsonStringOfStreet.substring(tmp2 + 1);
        tmp1 = jsonStringOfStreet.indexOf("[");
        if (Tools.countEntry(jsonStringOfStreet, "streetName") == 0) {
            tmp2 = jsonStringOfStreet.length() - 1;
        }
        else {
            tmp2 = jsonStringOfStreet.indexOf("streetName") - 5;
        }
        HomeDeserializer homeDeserializer = new HomeDeserializer();
        ArrayList<Home> homes = homeDeserializer.FromJsonToList(jsonStringOfStreet.substring(tmp1, tmp2));
        Street street = new Street(streetName);
        for (Home home : homes) {
            street.addHome(home);
        }
        return street;
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
                int tmp = jsonStringOfStreet.indexOf("streetName",doubleDotIndex) - 4;
                streets.add(FromJsonToObj(jsonStringOfStreet.substring(0, tmp)));
                jsonStringOfStreet = jsonStringOfStreet.substring(tmp + 3);
            }
        }
        return streets;
    }
}
