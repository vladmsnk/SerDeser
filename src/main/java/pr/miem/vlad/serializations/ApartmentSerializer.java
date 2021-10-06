package pr.miem.vlad.serializations;


import pr.miem.vlad.entities.Apartment;

import java.util.ArrayList;

public class ApartmentSerializer implements Serializer<Apartment> {

        public String objToJson(Apartment apartment) {
            if (apartment == null) {
                throw new NullPointerException("Room object does not exist!");
            }
            PersonSerializer personSerializer = new PersonSerializer();
            String people = personSerializer.ListOfObjToJson(apartment.getResidents());
            return "{" +
                    "\"roomNumber\": " + '"' + apartment.getApartmentNumber() + '"' +
                    ", \"people\": " + people +
                    "}";
        }

        public String ListOfObjToJson(ArrayList<Apartment> listOfApartments) {
            if (listOfApartments.size() == 0) {
                return "[]";
            }
            String jsonStringOfRooms = "[";
            for (Apartment apartment : listOfApartments) {
                jsonStringOfRooms = jsonStringOfRooms.concat(objToJson(apartment));
                jsonStringOfRooms += ", ";
            }
            jsonStringOfRooms = jsonStringOfRooms.substring(0, jsonStringOfRooms.length() - 2);

            jsonStringOfRooms += "]";

            return jsonStringOfRooms;
        }
}
