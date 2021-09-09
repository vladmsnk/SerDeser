
import serializations.SerOfPerson;
import serializations.SerOfPet;
import entities.Person;
import entities.Pet;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Pet pet = new Pet("bob", "Cat");
        Pet pet1 = new Pet("John", "Dog");
        Pet pet2 = new Pet("Ann", "Cat");

        Person person = new Person("Vlad", "Egorov", 100);
        Person person1 = new Person("Andrey", "Bobrov", 240);
        ArrayList<Person> people = new ArrayList<>();
        person.assignPet(pet);
        person.assignPet(pet1);
        person1.assignPet(pet2);
        people.add(person);
        people.add(person1);

        SerOfPerson serOfPerson = new SerOfPerson();
        String str = serOfPerson.ListOfObjToJson(people);
        System.out.println(str);
    }
}
