package serializations;


import entities.Room;

import java.util.ArrayList;

public class SerOfRoom implements Serializable<Room>{

        public String objToJson(Room obj) {
            SerOfPerson serOfPerson = new SerOfPerson();
            String people = "[" + serOfPerson.ListOfObjToJson(obj.getResidents()) + "]";
            return "{" +
                    "\"roomNumber\":" + '"' + obj.getRoomNumber() +
                    ", \"peopleCount\":" + '"' + obj.getPeopleCount() +
                    ", \"people\":" + people +
                    "}";
        }

        public String ListOfObjToJson(ArrayList<Room> listOfObj) {
            if (listOfObj.size() == 0) {
                return "";
            }
            String jsonString = (listOfObj.size() > 1) ? "{" : "";
            for (Room room : listOfObj) {
                jsonString = jsonString.concat(objToJson(room));
                jsonString += ", ";
            }
            jsonString = jsonString.substring(0, jsonString.length() - 2);
            if (listOfObj.size() > 1) {
                jsonString += "}";
            }
            return jsonString;
        }
}
