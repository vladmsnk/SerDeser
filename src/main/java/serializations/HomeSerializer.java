package serializations;


import entities.Home;

import java.util.ArrayList;


public class HomeSerializer implements Serializable<Home>{

    public String objToJson(Home home) {
        if (home == null) {
            throw new NullPointerException("Home object does not exist!");
        }
        RoomSerializer roomSerializer = new RoomSerializer();
        String rooms = "[" + roomSerializer.ListOfObjToJson(home.getRoomes()) + "]";
        return "{" +
                "\"homeNumber\":" + '"' + home.getHomeNumber() + '"' +
                ", \"rooms\":" + '"' + rooms +
                "}";
    }

    public String ListOfObjToJson(ArrayList<Home> listOfHomes) {
        if (listOfHomes.size() == 0) {
            return "";
        }
        String jsonStringOfHomes = (listOfHomes.size() > 1) ? "{" : "";
        for (Home home : listOfHomes) {
            jsonStringOfHomes = jsonStringOfHomes.concat(objToJson(home));
            jsonStringOfHomes += ", ";
        }
        jsonStringOfHomes = jsonStringOfHomes.substring(0, jsonStringOfHomes.length() - 2);
        if (listOfHomes.size() > 1) {
            jsonStringOfHomes += "}";
        }
        return jsonStringOfHomes;
    }
}
