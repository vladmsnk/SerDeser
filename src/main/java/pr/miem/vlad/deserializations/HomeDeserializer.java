package deserializations;

import entities.Home;
import entities.Room;
import tools.Tools;

import java.util.ArrayList;


public class HomeDeserializer implements Deserializer<Home> {
    public Home FromJsonToObj(String jsonStringOfHome) {
        if (jsonStringOfHome == null) {
            return null;
        }
        jsonStringOfHome = jsonStringOfHome.replaceAll("\\s+", "");
        int index1 = jsonStringOfHome.indexOf(":") + 2;
        int index2 = jsonStringOfHome.indexOf(",") - 1;
        int homeNumber = Integer.parseInt(jsonStringOfHome.substring(index1, index2));
        jsonStringOfHome = jsonStringOfHome.substring(index2 + 1);
        index1 = jsonStringOfHome.indexOf("[");
        if (Tools.countEntry(jsonStringOfHome, "homeNumber") == 0) {
            index2 = jsonStringOfHome.length() - 1;
        }
        else {
            index2 = jsonStringOfHome.indexOf("homeNumber") - 4;
        }
        RoomDeserializer roomDeserializer = new RoomDeserializer();
        ArrayList<Room> rooms = roomDeserializer.FromJsonToList(jsonStringOfHome.substring(index1, index2));
        return new Home.Builder().withHomeNumber(homeNumber).withRooms(rooms).build();
    }


    public ArrayList<Home> FromJsonToList(String jsonStringOfHomes) {
        if (jsonStringOfHomes.isEmpty()) {
            return null;
        }
        jsonStringOfHomes = jsonStringOfHomes.replaceAll("\\s+", "");
        ArrayList<Home> homes = new ArrayList<>();
        jsonStringOfHomes = jsonStringOfHomes.substring(1, jsonStringOfHomes.length() - 1);
        while (!jsonStringOfHomes.isEmpty()) {
            if (Tools.countEntry(jsonStringOfHomes, "homeNumber") == 1) {
                homes.add(FromJsonToObj(jsonStringOfHomes));
                break;
            }
            else {
                int doubleDotIndex = jsonStringOfHomes.indexOf(":");
                int indexOfHomeNumber = jsonStringOfHomes.indexOf("homeNumber",doubleDotIndex) - 3;
                homes.add(FromJsonToObj(jsonStringOfHomes.substring(0, indexOfHomeNumber)));
                jsonStringOfHomes = jsonStringOfHomes.substring(indexOfHomeNumber + 3);
            }
        }
        return homes;
    }
}
