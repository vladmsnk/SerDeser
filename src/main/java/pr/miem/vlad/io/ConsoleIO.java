package pr.miem.vlad.io;

import pr.miem.vlad.entities.*;
import pr.miem.vlad.restrictions.Animal;
import pr.miem.vlad.tools.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleIO {
    private String decision;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Pet> pets = new ArrayList<>();
    private final ArrayList<Person> people = new ArrayList<>();
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Home> homes = new ArrayList<>();

    private void chooseYesOrNO() {
        System.out.println("1. Yes");
        System.out.println("2. No");
        decision = scanner.next();
        while (!Utils.checkCorrectDecision(decision)) {
            System.out.println("Wrong decision! Input again");
            decision = scanner.next();
        }
    }
    public Street constructStreetFromConsole() {
        System.out.println("Input Street Name");
        String streetName = scanner.nextLine();
        while (true) {
            System.out.println("Do you want to finish building street?");
            chooseYesOrNO();
            if (this.decision.equals("1") || this.decision.equals("Yes")) {
                System.out.println("Do you want to create new Street ?");
                chooseYesOrNO();
                if (decision.equals("2") || decision.equals("No")) {
                    break;
                }
                else {
                    continue;
                }
            }
            else {
                while (true) {
                    System.out.println("Input Home Number");
                    int homeNumber = scanner.nextInt();
                    while (homeNumber <= 0) {
                        System.out.println("Wrong Home Number format! Input again");
                        homeNumber = scanner.nextInt();
                    }
                    while (true) {
                        System.out.println("Input Room Number");
                        int roomNumber = scanner.nextInt();
                        while (roomNumber <= 0) {
                            System.out.println("Wrong Room Number format! Input again");
                            roomNumber = scanner.nextInt();
                        }
                        while (true) {
                            System.out.println("Input Person Name");
                            String personName = scanner.next();
                            System.out.println("Input person LastName");
                            String personLastName = scanner.next();
                            System.out.println("Input person's money count");
                            int moneyCount = scanner.nextInt();
                            while (true) {
                                System.out.println("Input Pet Name");
                                String petName = scanner.next();
                                System.out.println("Input Animal Type");
                                String animalType = scanner.next();
                                while (!Utils.isEnumContainsString(animalType)) {
                                    System.out.println("Inappropriate animal!");
                                    animalType = scanner.next();
                                }

                                Animal animal = Animal.valueOf(animalType);
                                pets.add(new Pet.Builder().withPetName(petName).withAnimalType(animal).build());
                                System.out.println("Do you want to add Pet?");
                                chooseYesOrNO();
                                if (decision.equals("No") || decision.equals("2")) {
                                    break;
                                }
                            }
                            people.add(new Person.Builder().withPersonName(personName).withPersonLastName(personLastName).withMoneyCount(moneyCount).withPets(pets).build());
                            System.out.println("Do you want to add Resident?");
                            chooseYesOrNO();
                            if (decision.equals("No") || decision.equals("2")) {
                                break;
                            }
                        }
                        rooms.add(new Room.Builder().withRoomNumber(roomNumber).withResidents(people).build());
                        System.out.println("Do you want to add Room?");
                        chooseYesOrNO();
                        if (decision.equals("No") || decision.equals("2")) {
                            break;
                        }
                    }
                    homes.add(new Home.Builder().withHomeNumber(homeNumber).withRooms(rooms).build());
                    System.out.println("Do you want to add Home?");
                    chooseYesOrNO();
                    if (decision.equals("No") || decision.equals("2")) {
                        break;
                    }
                }
            }
            System.out.println("The street has been built!");
            break;
        }
        return new Street.Builder().withStreetName(streetName).withHomes(homes).build();
    }

    public void printStreetToConsole(Street street) {
        System.out.println("Street name: " + street.getStreetName());
        for (Home home : street.getHomes()) {
            System.out.println("Home :" + home.getHomeNumber());
            for (Room room : home.getRooms()) {
                System.out.println(" Room :" + room.getRoomNumber());
                for (Person person : room.getResidents()) {
                    System.out.println("  Resident: " + person.getPersonName() + " " + person.getPersonLastName());
                    System.out.println("  Possesion: " + person.getMoneyCount());
                    for (Pet pet : person.getPersonsPet()) {
                        System.out.println("   Pets");
                        System.out.println("   PetName: " + pet.getPetName());
                        System.out.println("   AnimalType: " + pet.getAnimalType());
                    }
                }
            }
        }
    }
}
