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

    @Test
    public void shouldCreateArrayOfRoomObjects() {
        String jsonObject = "{\"roomNumber\": 34, \"residents\": [{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"moneyCount\": 12323, \"pets\": [{\"petName\": \"Musya\", \"animalType\": \"CAT\"}]}]}," +
                " {\"roomNumber\": 35, \"residents\": [{\"personName\": \"Anna\", \"personLastName\": \"Ivanova\", \"moneyCount\": 123, \"pets\": [{\"petName\": \"Tom\", \"animalType\": \"DOG\"}]}]}";
        ArrayList<Room> rooms = roomDeserializer.fromJsonToList(jsonObject);
        assertEquals(Integer.parseInt(rooms.get(0).getRoomNumber()), 34);
        assertEquals(Integer.parseInt(rooms.get(1).getRoomNumber()), 35);
        ArrayList<Person> residents1 = rooms.get(0).getResidents();
        ArrayList<Person> residents2 = rooms.get(1).getResidents();
        assertEquals(residents1.get(0).getPersonName(),"Bob");
        assertEquals(residents2.get(0).getPersonName(),"Anna");
        assertEquals(residents1.get(0).getPersonLastName(),"Ivanov");
        assertEquals(residents2.get(0).getPersonLastName(),"Ivanova");
        assertEquals(Integer.parseInt(residents1.get(0).getMoneyCount()),12323);
        assertEquals(Integer.parseInt(residents2.get(0).getMoneyCount()),123);
        ArrayList<Pet> residentPets1 = residents1.get(0).getPersonsPet();
        ArrayList<Pet> residentPets2 = residents2.get(0).getPersonsPet();
        assertEquals(residentPets1.get(0).getPetName(), "Musya");
        assertEquals(residentPets1.get(0).getAnimalType(), "CAT");
        assertEquals(residentPets2.get(0).getPetName(), "Tom");
        assertEquals(residentPets2.get(0).getAnimalType(), "DOG");
    }

}
