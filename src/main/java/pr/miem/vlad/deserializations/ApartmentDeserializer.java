package pr.miem.vlad.deserializations;

import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Apartment;
import java.util.ArrayList;
import java.util.Map;

import pr.miem.vlad.tools.Utils;

public class ApartmentDeserializer implements Deserializer<Apartment> {


    public Apartment fromJsonToObj(String jsonStringOfRoom) {
        JsonParser jsonParser = new JsonParser(jsonStringOfRoom);
        Map<String, String> mapOfJson = jsonParser.jsonParse();
        PersonDeserializer personDeserializer = new PersonDeserializer();
        ArrayList<Person> residents = personDeserializer.fromJsonToList(mapOfJson.get("residents"));
        return new Apartment.Builder()
                .withApartmentNumber(Integer.parseInt(mapOfJson.get("apartmentNumber")))
                .withResidents(residents)
                .build();
    }

    public ArrayList<Apartment> fromJsonToList(String jsonStringOfRooms) {

        ArrayList<String> arrayOfJsonObjects = Utils.splitJsonString(jsonStringOfRooms);
        ArrayList<Apartment> apartments = new ArrayList<>();
        for (String jsonObject : arrayOfJsonObjects) {
            Apartment apartment = fromJsonToObj(jsonObject);
            apartments.add(apartment);
        }
        return apartments;
    }
}
