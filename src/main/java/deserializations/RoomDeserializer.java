package deserializations;

import entities.Person;
import entities.Room;
import java.util.ArrayList;
import tools.Tools;

public class RoomDeserializer implements Deserializer<Room> {


    public Room FromJsonToObj(String jsonStringOfRoom) {
        if (jsonStringOfRoom == null) {
            return null;
        }
        jsonStringOfRoom = jsonStringOfRoom.replaceAll("\\s+", "");
        int index1 = jsonStringOfRoom.indexOf(":") + 2;
        int index2 = jsonStringOfRoom.indexOf(",") - 1;
        int roomNumber = Integer.parseInt(jsonStringOfRoom.substring(index1, index2));
        jsonStringOfRoom = jsonStringOfRoom.substring(index2 + 1);
        index1 = jsonStringOfRoom.indexOf("[");
        if (Tools.countEntry(jsonStringOfRoom, "roomNumber") == 0) {
            index2 = jsonStringOfRoom.length() - 1;
        }
        else {
            index2 = jsonStringOfRoom.indexOf("roomNumber") - 4;
        }
        PersonDeserializer personDeserializer = new PersonDeserializer();
        ArrayList<Person> residents = personDeserializer.FromJsonToList(jsonStringOfRoom.substring(index1, index2));
        return new Room.Builder().withRoomNumber(roomNumber).withResidents(residents).build();
    }

    public ArrayList<Room> FromJsonToList(String jsonStringOfRooms) {
        jsonStringOfRooms = jsonStringOfRooms.replaceAll("\\s+", "");
        if (jsonStringOfRooms.isEmpty()) {
            return null;
        }
        ArrayList<Room> rooms = new ArrayList<>();
        jsonStringOfRooms = jsonStringOfRooms.substring(1, jsonStringOfRooms.length() - 1);
        while (!jsonStringOfRooms.isEmpty()) {
            if (Tools.countEntry(jsonStringOfRooms, "roomNumber") == 1) {
                rooms.add(FromJsonToObj(jsonStringOfRooms));
                break;
            }
            else {
                int doubleDotIndex = jsonStringOfRooms.indexOf(":");
                int roomNumberIndex = jsonStringOfRooms.indexOf("roomNumber",doubleDotIndex) - 3;
                rooms.add(FromJsonToObj(jsonStringOfRooms.substring(0, roomNumberIndex)));
                jsonStringOfRooms = jsonStringOfRooms.substring(roomNumberIndex + 2);
            }
        }
        return rooms;
    }
}
