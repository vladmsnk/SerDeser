
package pr.miem.vlad.deserializations;

import org.junit.jupiter.api.Test;
import pr.miem.vlad.entities.House;
import pr.miem.vlad.entities.Person;
import pr.miem.vlad.entities.Pet;
import pr.miem.vlad.entities.Apartment;
import pr.miem.vlad.restrictions.Animal;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class HouseDeserializerTest {

    private final HouseDeserializer houseDeserializer = new HouseDeserializer();
    @Test
    public void shouldCreateHomeObject() {
        String jsonObject = "{\"houseNumber\": 353, \"apartments\": [{\"apartmentNumber\": 34, \"residents\": [{\"name\": \"Bob\", \"lastName\": \"Ivanov\", \"money\": 123, \"pets\": [{\"name\": \"Musya\", \"animal\": \"CAT\"}, {\"name\": \"Anna\", \"animal\": \"BIRD\"}, {\"name\": \"Egor\", \"animal\": \"DOG\"}]}, {\"name\": \"Bob1\", \"lastName\": \"Ivanov1\", \"money\": 1233, \"pets\": [{\"name\": \"Musya1\", \"animal\": \"CAT\"}, {\"name\": \"Anna1\", \"animal\": \"BIRD\"}, {\"name\": \"Egor1\", \"animal\": \"DOG\"}]}]}]}";
        House parsedHouse = houseDeserializer.fromJsonToObj(jsonObject);
        House expectedHouse = createHouse();
        assertEquals(expectedHouse, parsedHouse);
    }

    @Test
    public void shouldCreateArrayOfHouseObjects() {
        String jsonObject = "[{\"houseNumber\": 343, \"apartments\": [{\"apartmentNumber\": 34, \"residents\": [{\"name\": \"Bob\", \"lastName\": \"Ivanov\", \"money\": 12323, \"pets\": [{\"name\": \"Musya\", \"animal\": \"CAT\"}]}]}]}," +
                "{\"houseNumber\": 347, \"apartments\":  [{\"apartmentNumber\": 35, \"residents\": [{\"name\": \"Anna\", \"lastName\": \"Ivanova\", \"money\": 123, \"pets\": [{\"name\": \"Tom\", \"animal\": \"DOG\"}]}]}]}]";

        ArrayList<House> parsedHouses = houseDeserializer.fromJsonToList(jsonObject);
        ArrayList<House> expectedHouses = createHouseList();
        assertEquals(expectedHouses, parsedHouses);
    }


   private House createHouse() {
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
        return new House.Builder()
                .withHouseNumber(353)
                .withApartment(expectedApartments)
                .build();
    }

    private ArrayList<House> createHouseList() {
        ArrayList<Pet> expectedPets1 = new ArrayList<>();
        ArrayList<Person> expectedPeople1 = new ArrayList<>();
        ArrayList<Person> expectedPeople2 = new ArrayList<>();

        Pet pet = new Pet.Builder()
                .withName("Musya")
                .withAnimal(Animal.valueOf("CAT"))
                .build();

        expectedPets1.add(pet);


        ArrayList<Pet> expectedPets2 = new ArrayList<>();

        Pet pet01 = new Pet.Builder()
                .withName("Tom")
                .withAnimal(Animal.valueOf("DOG"))
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

        Apartment expectedApartment1 = new Apartment.Builder()
                .withApartmentNumber(34)
                .withResidents(expectedPeople1)
                .build();

        Apartment expectedApartment2 = new Apartment.Builder()
                .withApartmentNumber(35)
                .withResidents(expectedPeople2)
                .build();

        ArrayList<Apartment> expectedApartments1 = new ArrayList<>();
        expectedApartments1.add(expectedApartment1);

        ArrayList<Apartment> expectedApartments2 = new ArrayList<>();
        expectedApartments2.add(expectedApartment2);

        House expectedHouse1 = new House.Builder()
                .withHouseNumber(343)
                .withApartment(expectedApartments1)
                .build();
        House expectedHouse2 = new House.Builder()
                .withHouseNumber(347)
                .withApartment(expectedApartments2)
                .build();
        ArrayList<House> expectedHouses = new ArrayList<>();
        expectedHouses.add(expectedHouse1);
        expectedHouses.add(expectedHouse2);
        return expectedHouses;
    }


}
