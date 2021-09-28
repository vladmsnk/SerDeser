package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.entities.Room;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomDeserializerTest {
    private final RoomDeserializer roomDeserializer = new RoomDeserializer();

    @Test
    public void shouldCreateRoomObject() {
        String jsonObject = "{\"roomNumber\": 34, \"residents\": [{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"moneyCount\": 123, \"pets\": [{\"petName\": \"Musya\", \"animalType\": \"CAT\"}]}]}";
        Room room = roomDeserializer.fromJsonToObj(jsonObject);
        assertEquals(Integer.parseInt(room.getRoomNumber()), 34);
        ArrayList<Person> residents = room.getResidents();
        assertEquals(residents.size(), 1);
        assertEquals(residents.get(0).getPersonName(), "Bob");
        assertEquals(residents.get(0).getPersonLastName(), "Ivanov");
        assertEquals(Integer.parseInt(residents.get(0).getMoneyCount()), 123);
        ArrayList<Pet> pets = residents.get(0).getPersonsPet();
        assertEquals(pets.size(), 1);
        assertEquals(pets.get(0).getPetName(), "Musya");
        assertEquals(pets.get(0).getAnimalType(), "CAT");
    }

}
