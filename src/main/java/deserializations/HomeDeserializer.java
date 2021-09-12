package deserializations;

import entities.Home;
import entities.Person;
import entities.Room;
import tools.Tools;

import java.util.ArrayList;


public class HomeDeserializer implements Deserializable<Home>{
    public Home FromJsonToObj(String jsonStringOfHome) {
        if (jsonStringOfHome == null) {
            return null;
        }
        int tmp1 = jsonStringOfHome.indexOf(":") + 2;
        int tmp2 = jsonStringOfHome.indexOf(",") - 1;
        int homeNumber = Integer.parseInt(jsonStringOfHome.substring(tmp1, tmp2));
        jsonStringOfHome = jsonStringOfHome.substring(tmp2 + 1);
        tmp1 = jsonStringOfHome.indexOf("[");
        if (Tools.countEntry(jsonStringOfHome, "homeNumber") == 0) {
            tmp2 = jsonStringOfHome.length() - 1;
        }
        else {
            tmp2 = jsonStringOfHome.indexOf("homeNumber") - 5;
        }
        RoomDeserializer roomDeserializer = new RoomDeserializer();
        ArrayList<Room> rooms = roomDeserializer.FromJsonToList(jsonStringOfHome.substring(tmp1, tmp2));
        Home home = new Home(homeNumber);
        for (Room room : rooms) {
            home.addRoom(room);
        }
        return home;
    }


    public ArrayList<Home> FromJsonToList(String jsonStringOfHomes) {
        if (jsonStringOfHomes.isEmpty()) {
            return null;
        }
        ArrayList<Home> homes = new ArrayList<>();
        jsonStringOfHomes = jsonStringOfHomes.substring(1, jsonStringOfHomes.length() - 1);
        while (!jsonStringOfHomes.isEmpty()) {
            if (Tools.countEntry(jsonStringOfHomes, "homeNumber") == 1) {
                homes.add(FromJsonToObj(jsonStringOfHomes));
                jsonStringOfHomes = "";
                break;
            }
            else {
                int doubleDotIndex = jsonStringOfHomes.indexOf(":");
                int tmp = jsonStringOfHomes.indexOf("homeNumber",doubleDotIndex) - 4;
                homes.add(FromJsonToObj(jsonStringOfHomes.substring(0, tmp)));
                jsonStringOfHomes = jsonStringOfHomes.substring(tmp + 3);
            }
        }
        return homes;
    }
}
