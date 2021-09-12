package deserializations;

import entities.Person;
import entities.Room;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import tools.Tools;

//{"roomNumber":"42", peple:[{ ...,  "pets":[   ]}]}, {"roomNumber")
//room string ends with double ], therefore, to find the end we should find index of first ] and add two




public class RoomDeserializer implements Deserializable<Room>{


    public Room FromJsonToObj(String jsonStringOfRoom) {
        if (jsonStringOfRoom == null) {
            return null;
        }
        int tmp1 = jsonStringOfRoom.indexOf(":") + 2;
        int tmp2 = jsonStringOfRoom.indexOf(",") - 1;
        int roomNumber = Integer.parseInt(jsonStringOfRoom.substring(tmp1, tmp2));
        jsonStringOfRoom = jsonStringOfRoom.substring(tmp2 + 1);
        tmp1 = jsonStringOfRoom.indexOf("[");
        if (Tools.countEntry(jsonStringOfRoom, "roomNumber") == 0) {
            tmp2 = jsonStringOfRoom.length() - 1;
        }
        else {
            tmp2 = jsonStringOfRoom.indexOf("roomNumber") - 5;
        }
        PersonDeserializer personDeserializer = new PersonDeserializer();
        ArrayList<Person> people = personDeserializer.FromJsonToList(jsonStringOfRoom.substring(tmp1, tmp2));
        Room room = new Room(roomNumber);
        for (Person person : people) {
            room.assignResident(person);
        }
        return room;
    }

    public ArrayList<Room> FromJsonToList(String jsonStringOfRooms) {
        if (jsonStringOfRooms.isEmpty()) {
            return null;
        }
        ArrayList<Room> rooms = new ArrayList<>();
        jsonStringOfRooms = jsonStringOfRooms.substring(1, jsonStringOfRooms.length() - 1);
        while (!jsonStringOfRooms.isEmpty()) {
            if (Tools.countEntry(jsonStringOfRooms, "roomNumber") == 1) {
                rooms.add(FromJsonToObj(jsonStringOfRooms));
                jsonStringOfRooms = "";
                break;
            }
            else {
                int doubleDotIndex = jsonStringOfRooms.indexOf(":");
                int tmp = jsonStringOfRooms.indexOf("roomNumber",doubleDotIndex) - 4;
                rooms.add(FromJsonToObj(jsonStringOfRooms.substring(0, tmp)));
                jsonStringOfRooms = jsonStringOfRooms.substring(tmp + 3);
            }
        }
        return rooms;
    }
}
