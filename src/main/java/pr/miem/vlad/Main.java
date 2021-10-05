package pr.miem.vlad;

import pr.miem.vlad.io.InputFromFile;
import pr.miem.vlad.entities.*;
import pr.miem.vlad.restrictions.Animal;
import pr.miem.vlad.serializations.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        InputFromFile inputFromFile = new InputFromFile("C:\\Users\\влад\\IdeaProjects\\prSchool\\src\\test\\java\\test.json");
        Pet pet = new Pet.Builder()
                .withPetName("Tomas")
                .withAnimalType(Animal.BIRD)
                .build();
        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(pet);
        Person person = new Person.Builder()
                .withPersonName("Vlad")
                .withPersonLastName("Egorov")
                .withMoney(123)
                .withPets(pets)
                .build();
        PetSerializer petSerializer = new PetSerializer();
        String petSerialized = petSerializer.objToJson(pet);
        PersonSerializer personSerializer = new PersonSerializer();
        String personSerialized = personSerializer.objToJson(person);
        System.out.println(personSerialized);

    }
}