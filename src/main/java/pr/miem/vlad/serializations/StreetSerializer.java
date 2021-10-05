package pr.miem.vlad.serializations;


import pr.miem.vlad.entities.Street;

import java.util.ArrayList;


public class StreetSerializer implements Serializer<Street> {
    public String objToJson(Street street) {
        if (street == null) {
            throw new NullPointerException("Street object does not exist!");
        }
        HouseSerializer houseSerializer = new HouseSerializer();
        String homes = houseSerializer.ListOfObjToJson(street.getHouses());
        return "{" +
                "\"streetName\": " + '"' + street.getStreetName() + '"' +
                ", \"homes\": " + homes +
                "}";
    }
    public String ListOfObjToJson(ArrayList<Street> listOfObj) {
        if (listOfObj.size() == 0) {
            return "";
        }
        String jsonString = "[";
        for (Street street : listOfObj) {
            jsonString = jsonString.concat(objToJson(street));
            jsonString += ", ";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 2);
        jsonString += "]";
        return jsonString;
    }
}
