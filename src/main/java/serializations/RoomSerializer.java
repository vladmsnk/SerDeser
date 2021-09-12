package serializations;


import entities.Room;

import java.util.ArrayList;

public class RoomSerializer implements Serializable<Room>{

        public String objToJson(Room room) {
            if (room == null) {
                throw new NullPointerException("Room object does not exist!");
            }
            PersonSerializer personSerializer = new PersonSerializer();
            String people = "[" + personSerializer.ListOfObjToJson(room.getResidents()) + "]";
            return "{" +
                    "\"roomNumber\":" + '"' + room.getRoomNumber() + '"' +
                    ", \"people\":" + people +
                    "}";
        }

        public String ListOfObjToJson(ArrayList<Room> listOfRooms) {
            if (listOfRooms.size() == 0) {
                return "";
            }
            String jsonStringOfRooms = (listOfRooms.size() > 1) ? "{" : "";
            for (Room room : listOfRooms) {
                jsonStringOfRooms = jsonStringOfRooms.concat(objToJson(room));
                jsonStringOfRooms += ", ";
            }
            jsonStringOfRooms = jsonStringOfRooms.substring(0, jsonStringOfRooms.length() - 2);
            if (listOfRooms.size() > 1) {
                jsonStringOfRooms += "}";
            }
            return jsonStringOfRooms;
        }
}
