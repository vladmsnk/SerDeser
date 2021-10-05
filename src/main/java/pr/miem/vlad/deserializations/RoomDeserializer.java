package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Room;
import java.util.ArrayList;
import java.util.Map;

import pr.miem.vlad.tools.Utils;

public class RoomDeserializer implements Deserializer<Room> {


    public Room fromJsonToObj(String jsonStringOfRoom) {
        ParseJsonObject parseJsonObject = new ParseJsonObject(jsonStringOfRoom);
        Map<String, String> mapOfJson = parseJsonObject.jsonParse();
        PersonDeserializer personDeserializer = new PersonDeserializer();
        ArrayList<Person> people = personDeserializer.fromJsonToList(mapOfJson.get("residents"));
        return new Room.Builder()
                .withRoomNumber(Integer.parseInt(mapOfJson.get("roomNumber")))
                .withResidents(people)
                .build();
    }

    public ArrayList<Room> fromJsonToList(String jsonStringOfRooms) {

        ArrayList<String> arrayOfJsonObjects = Utils.splitJsonString(jsonStringOfRooms);
        ArrayList<Room> rooms = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Room room = fromJsonToObj(jsonObject);
            rooms.add(room);
        }
        return rooms;
    }
}
