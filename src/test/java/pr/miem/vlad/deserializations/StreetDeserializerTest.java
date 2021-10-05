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
        String jsonObject = "{\"streetName\": \"Lenin\", \"homes\": [{\"homeNumber\": 353, \"rooms\": [{\"roomNumber\": 34, \"residents\": [{\"personName\": \"Bob\", \"personLastName\": \"Ivanov\", \"money\": 123, \"pets\": [{\"petName\": \"Musya\", \"animalType\": \"CAT\"}, {\"petName\": \"Anna\", \"animalType\": \"BIRD\"}, {\"petName\": \"Egor\", \"animalType\": \"DOG\"}]}, {\"personName\": \"Bob1\", \"personLastName\": \"Ivanov1\", \"money\": 1233, \"pets\": [{\"petName\": \"Musya1\", \"animalType\": \"CAT\"}, {\"petName\": \"Anna1\", \"animalType\": \"BIRD\"}, {\"petName\": \"Egor1\", \"animalType\": \"DOG\"}]}]}]}]}";
        Street parsedStreet = streetDeserializer.fromJsonToObj(jsonObject);

        ArrayList<Pet> expectedPets1 = new ArrayList<>();
        ArrayList<Person> expectedPeople = new ArrayList<>();

        Pet pet = new Pet.Builder()
                .withName("Musya")
                .withAnimal(Animal.valueOf("CAT"))
                .build();

        Pet pet1 = new Pet.Builder()
                .withName("Anna")
                .withAnimal(Animal.valueOf("BIRD"))
                .build();

        Pet pet2 = new Pet.Builder()
                .withName("Egor")
                .withAnimal(Animal.valueOf("DOG"))
                .build();

        expectedPets1.add(pet);
        expectedPets1.add(pet1);
        expectedPets1.add(pet2);

        ArrayList<Pet> expectedPets2 = new ArrayList<>();

        Pet pet01 = new Pet.Builder()
                .withName("Musya1")
                .withAnimal(Animal.valueOf("CAT"))
                .build();

        Pet pet11 = new Pet.Builder()
                .withName("Anna1")
                .withAnimal(Animal.valueOf("BIRD"))
                .build();

        Pet pet21 = new Pet.Builder()
                .withName("Egor1")
                .withAnimal(Animal.valueOf("DOG"))
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

        Apartment expectedApartment = new Apartment.Builder()
                .withApartmentNumber(34)
                .withResidents(expectedPeople)
                .build();

        ArrayList<Apartment> expectedApartments = new ArrayList<>();
        expectedApartments.add(expectedApartment);
        House expectedHouse = new House.Builder()
                .withHouseNumber(353)
                .withApartment(expectedApartments)
                .build();

        ArrayList<House> expectedHouses = new ArrayList<>();
        expectedHouses.add(expectedHouse);

        Street expectedStreet = new Street.Builder()
                .withStreetName("Lenin")
                .withHouses(expectedHouses)
                .build();
        assertEquals(parsedStreet, expectedStreet);
    }
}
