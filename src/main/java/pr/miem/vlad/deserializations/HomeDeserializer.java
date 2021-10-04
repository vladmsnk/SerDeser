package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Home;
import pr.miem.vlad.entities.Room;
import pr.miem.vlad.tools.Tools;

import java.util.ArrayList;
import java.util.Map;


public class HomeDeserializer implements Deserializer<Home> {
    public Home fromJsonToObj(String jsonStringOfHome) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonStringOfHome);
        Map<String, String> mapOfJson = parseJsonObject.getMapOfJson();
        RoomDeserializer roomDeserializer = new RoomDeserializer();
        ArrayList<Room> rooms = roomDeserializer.fromJsonToList(mapOfJson.get("rooms"));
        return new Home.Builder()
                .withHomeNumber(Integer.parseInt(mapOfJson.get("homeNumber")))
                .withRooms(rooms)
                .build();

    }

    public ArrayList<Home> fromJsonToList(String jsonStringOfHomes) {
        ArrayList<String> arrayOfJsonObjects = Tools.splitJsonString(jsonStringOfHomes);
        ArrayList<Home> homes = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Home home = fromJsonToObj(jsonObject);
            homes.add(home);
        }
        return homes;
    }
}
