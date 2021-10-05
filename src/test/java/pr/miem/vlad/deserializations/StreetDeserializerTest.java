package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.*;
import pr.miem.vlad.restrictions.Animal;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StreetDeserializerTest {

    private final StreetDeserializer streetDeserializer = new StreetDeserializer();

    @Test
    public void shouldCreateArrayOfStreetObjects() {
        String jsonObject = "{\"streetName\": \"Lenin\", \"homes\": [{\"homeNumber\": 353, \"rooms\": [{\"roomNumber\": 34, \"residents\": [{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"moneyCount\": 123, \"pets\": [{\"petName\": \"Musya\", \"animalType\": \"CAT\"}, {\"petName\": \"Anna\", \"animalType\": \"BIRD\"}, {\"petName\": \"Egor\", \"animalType\": \"DOG\"}]}, {\"personName\": \"Bob1\", \"personLastName\": \"Ivanov1\", \"moneyCount\": 1233, \"pets\": [{\"petName\": \"Musya1\", \"animalType\": \"CAT\"}, {\"petName\": \"Anna1\", \"animalType\": \"BIRD\"}, {\"petName\": \"Egor1\", \"animalType\": \"DOG\"}]}]}]}]}";
        Street parsedStreet = streetDeserializer.fromJsonToObj(jsonObject);

        ArrayList<Pet> expectedPets1 = new ArrayList<>();
        ArrayList<Person> expectedPeople = new ArrayList<>();

        Pet pet = new Pet.Builder()
                .withPetName("Musya")
                .withAnimalType(Animal.valueOf("CAT"))
                .build();

        Pet pet1 = new Pet.Builder()
                .withPetName("Anna")
                .withAnimalType(Animal.valueOf("BIRD"))
                .build();

        Pet pet2 = new Pet.Builder()
                .withPetName("Egor")
                .withAnimalType(Animal.valueOf("DOG"))
                .build();

        expectedPets1.add(pet);
        expectedPets1.add(pet1);
        expectedPets1.add(pet2);

        ArrayList<Pet> expectedPets2 = new ArrayList<>();

        Pet pet01 = new Pet.Builder()
                .withPetName("Musya1")
                .withAnimalType(Animal.valueOf("CAT"))
                .build();

        Pet pet11 = new Pet.Builder()
                .withPetName("Anna1")
                .withAnimalType(Animal.valueOf("BIRD"))
                .build();

        Pet pet21 = new Pet.Builder()
                .withPetName("Egor1")
                .withAnimalType(Animal.valueOf("DOG"))
                .build();

        expectedPets2.add(pet01);
        expectedPets2.add(pet11);
        expectedPets2.add(pet21);

        Person person1 = new Person.Builder()
                .withPersonName("Bob")
                .withPersonLastName("Ivanov")
                .withMoneyCount(123)
                .withPets(expectedPets1)
                .build();

        Person person2 = new Person.Builder()
                .withPersonName("Bob1")
                .withPersonLastName("Ivanov1")
                .withMoneyCount(1233)
                .withPets(expectedPets2)
                .build();

        expectedPeople.add(person1);
        expectedPeople.add(person2);

        Room expectedRoom = new Room.Builder()
                .withRoomNumber(34)
                .withResidents(expectedPeople)
                .build();

        ArrayList<Room> expectedRooms = new ArrayList<>();
        expectedRooms.add(expectedRoom);
        Home expectedHome = new Home.Builder()
                .withHomeNumber(353)
                .withRooms(expectedRooms)
                .build();

        ArrayList<Home> expectedHomes = new ArrayList<>();
        expectedHomes.add(expectedHome);

        Street expectedStreet = new Street.Builder()
                .withStreetName("Lenin")
                .withHomes(expectedHomes)
                .build();
        assertEquals(parsedStreet, expectedStreet);
    }
}
