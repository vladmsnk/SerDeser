
package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.Home;
import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.entities.Room;
import pr.miem.vlad.restrictions.Animal;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class HomeDeserializerTest {

    private final HomeDeserializer homeDeserializer = new HomeDeserializer();
    @Test
    public void shouldCreateHomeObject() {
        String jsonObject = "{\"homeNumber\": 353, \"rooms\": [{\"roomNumber\": 34, \"residents\": [{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"money\": 123, \"pets\": [{\"petName\": \"Musya\", \"animalType\": \"CAT\"}, {\"petName\": \"Anna\", \"animalType\": \"BIRD\"}, {\"petName\": \"Egor\", \"animalType\": \"DOG\"}]}, {\"personName\": \"Bob1\", \"personLastName\": \"Ivanov1\", \"money\": 1233, \"pets\": [{\"petName\": \"Musya1\", \"animalType\": \"CAT\"}, {\"petName\": \"Anna1\", \"animalType\": \"BIRD\"}, {\"petName\": \"Egor1\", \"animalType\": \"DOG\"}]}]}]}";
        Home parsedHome = homeDeserializer.fromJsonToObj(jsonObject);

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
                .withName("Bob")
                .withLastName("Ivanov")
                .withMoney(123)
                .withPets(expectedPets1)
                .build();

        Person person2 = new Person.Builder()
                .withName("Bob1")
                .withLastName("Ivanov1")
                .withMoney(1233)
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
        assertEquals(expectedHome, parsedHome);
    }

    @Test
    public void shouldCreateArrayOfHoomObjects() {
        String jsonObject = "[{\"homeNumber\": 343, \"rooms\": [{\"roomNumber\": 34, \"residents\": [{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"money\": 12323, \"pets\": [{\"petName\": \"Musya\", \"animalType\": \"CAT\"}]}]}]}," +
                "{\"homeNumber\": 347, \"rooms\":  [{\"roomNumber\": 35, \"residents\": [{\"personName\": \"Anna\", \"personLastName\": \"Ivanova\", \"money\": 123, \"pets\": [{\"petName\": \"Tom\", \"animalType\": \"DOG\"}]}]}]}]";

        ArrayList<Home> parsedHomes = homeDeserializer.fromJsonToList(jsonObject);

        ArrayList<Pet> expectedPets1 = new ArrayList<>();
        ArrayList<Person> expectedPeople1 = new ArrayList<>();
        ArrayList<Person> expectedPeople2 = new ArrayList<>();

        Pet pet = new Pet.Builder()
                .withPetName("Musya")
                .withAnimalType(Animal.valueOf("CAT"))
                .build();

        expectedPets1.add(pet);


        ArrayList<Pet> expectedPets2 = new ArrayList<>();

        Pet pet01 = new Pet.Builder()
                .withPetName("Tom")
                .withAnimalType(Animal.valueOf("DOG"))
                .build();

        expectedPets2.add(pet01);

        Person person1 = new Person.Builder()
                .withName("Bob")
                .withLastName("Ivanov")
                .withMoney(12323)
                .withPets(expectedPets1)
                .build();

        Person person2 = new Person.Builder()
                .withName("Anna")
                .withLastName("Ivanova")
                .withMoney(123)
                .withPets(expectedPets2)
                .build();

        expectedPeople1.add(person1);
        expectedPeople2.add(person2);

        Room expectedRoom1 = new Room.Builder()
                .withRoomNumber(34)
                .withResidents(expectedPeople1)
                .build();

        Room expectedRoom2 = new Room.Builder()
                .withRoomNumber(35)
                .withResidents(expectedPeople2)
                .build();

        ArrayList<Room> expectedRooms1 = new ArrayList<>();
        expectedRooms1.add(expectedRoom1);

        ArrayList<Room> expectedRooms2 = new ArrayList<>();
        expectedRooms2.add(expectedRoom2);

        Home expectedHome1 = new Home.Builder()
                .withHomeNumber(343)
                .withRooms(expectedRooms1)
                .build();
        Home expectedHome2 = new Home.Builder()
                .withHomeNumber(347)
                .withRooms(expectedRooms2)
                .build();
        ArrayList<Home> expectedHomes = new ArrayList<>();
        expectedHomes.add(expectedHome1);
        expectedHomes.add(expectedHome2);
        assertEquals(expectedHomes, parsedHomes);
    }
}
