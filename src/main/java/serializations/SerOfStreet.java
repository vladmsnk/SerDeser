package serializations;


import entities.Street;

import java.util.ArrayList;


public class SerOfStreet implements Serializable<Street> {
    public String objToJson(Street obj) {
        SerOfHome serOfHome = new SerOfHome();
        String homes = "[" + serOfHome.ListOfObjToJson(obj.getHomes()) + "]";
        return "{" +
                "\"streetName\":" + '"' + obj.getStreetName() +
                "\"homes\":" + homes +
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
