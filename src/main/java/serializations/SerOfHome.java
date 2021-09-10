package serializations;


import entities.Home;
import entities.Room;

import java.util.ArrayList;


public class SerOfHome implements Serializable<Home>{

    public String objToJson(Home obj) {
        SerOfRoom serOfRoom = new SerOfRoom();
        String rooms = "[" + serOfRoom.ListOfObjToJson(obj.getRoomes()) + "]";
        return "{" +
                "\"homeNumber\":" + '"' + obj.getHomeNumber() + '"' +
                " \"rooms\":" + '"' + rooms +
                "}";
    }

    public String ListOfObjToJson(ArrayList<Home> listOfObj) {
        if (listOfObj.size() == 0) {
            return "";
        }
        String jsonString = (listOfObj.size() > 1) ? "{" : "";
        for (Home home : listOfObj) {
            jsonString = jsonString.concat(objToJson(home));
            jsonString += ", ";
        }
        jsonString = jsonString.substring(0, jsonString.length() - 2);
        if (listOfObj.size() > 1) {
            jsonString += "}";
        }
        return jsonString;
    }
}
