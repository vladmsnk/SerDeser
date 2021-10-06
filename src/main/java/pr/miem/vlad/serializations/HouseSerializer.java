package pr.miem.vlad.serializations;


import pr.miem.vlad.entities.House;

import java.util.ArrayList;


public class HouseSerializer implements Serializer<House> {

    public String objToJson(House house) {
        if (house == null) {
            throw new NullPointerException("Home object does not exist!");
        }
        ApartmentSerializer apartmentSerializer = new ApartmentSerializer();
        String rooms = apartmentSerializer.ListOfObjToJson(house.getApartments());
        return "{" +
                "\"homeNumber\": " + '"' + house.getHouseNumber() + '"' +
                ", \"rooms\": " + rooms +
                "}";
    }

    public String ListOfObjToJson(ArrayList<House> listOfHouses) {
        if (listOfHouses.size() == 0) {
            return "[]";
        }
        String jsonStringOfHomes = "[";
        for (House house : listOfHouses) {
            jsonStringOfHomes = jsonStringOfHomes.concat(objToJson(house));
            jsonStringOfHomes += ", ";
        }
        jsonStringOfHomes = jsonStringOfHomes.substring(0, jsonStringOfHomes.length() - 2);
        jsonStringOfHomes += "]";
        return jsonStringOfHomes;
    }
}
