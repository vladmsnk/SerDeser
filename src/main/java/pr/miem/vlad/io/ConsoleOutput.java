package pr.miem.vlad.io;

import pr.miem.vlad.entities.*;

public class ConsoleOutput {
    public void printStreetToConsole(Street street) {
        System.out.println("Street name: " + street.getStreetName());
        for (House house : street.getHouses()) {
            System.out.println("House :" + house.getHouseNumber());
            for (Apartment apartment : house.getApartments()) {
                System.out.println(" Apartment :" + apartment.getApartmentNumber());
                for (Person person : apartment.getResidents()) {
                    System.out.println("  Person: " + person.getName() + " " + person.getLastName());
                    System.out.println("    money: " + person.getMoney());
                    for (Pet pet : person.getPets()) {
                        System.out.println("    Pets");
                        System.out.println("    name: " + pet.getName());
                        System.out.println("    animal: " + pet.getAnimal());
                    }
                }
            }
        }
    }
}
