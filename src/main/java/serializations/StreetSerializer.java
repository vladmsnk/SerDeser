package serializations;


import entities.Street;

import java.util.ArrayList;


public class StreetSerializer implements Serializable<Street> {
    public String objToJson(Street street) {
        if (street == null) {
            throw new NullPointerException("Street object does not exist!");
        }
        HomeSerializer homeSerializer = new HomeSerializer();
        String homes = "[" + homeSerializer.ListOfObjToJson(street.getHomes()) + "]";
        return "{" +
                "\"streetName\":" + '"' + street.getStreetName() +
                ", \"homes\":" + homes +
                "}";
    }
    public String ListOfObjToJson(ArrayList<Street> listOfObj) {
        if (listOfObj.size() == 0) {
            return "";
        }
        String jsonString = (listOfObj.size() > 1) ? "{" : "";
        for (Street street : listOfObj) {
            jsonString = jsonString.concat(objToJson(street));
            jsonString += ", ";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 2);
        if (listOfObj.size() > 1) {
            jsonString += "}";
        }
        return jsonString;
    }
}
