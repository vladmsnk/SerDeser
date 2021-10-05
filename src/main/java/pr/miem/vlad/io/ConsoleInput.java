package pr.miem.vlad.io;

import pr.miem.vlad.entities.*;
import pr.miem.vlad.restrictions.Animal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInput {
    private int state;
    private final Scanner scanner = new Scanner(System.in);
    private String currentInput;

    private final Street.Builder streetBuilder = new Street.Builder();

    private final ArrayList<House> houses = new ArrayList<>();
    private final ArrayList<Apartment> apartments = new ArrayList<>();
    private final ArrayList<Person> residents = new ArrayList<>();
    private final ArrayList<Pet> pets = new ArrayList<>();

    private final House.Builder houseBuilder = new House.Builder();
    private final Apartment.Builder apartmentBuilder = new Apartment.Builder();
    private final Person.Builder personBuilder = new Person.Builder();
    private final Pet.Builder petBuilder = new Pet.Builder();

    public void runConsoleInput() {
        while (state != 14) {
            transmit();
        }
    }

    private void transmit() {
        switch (state) {
            case 0:
                if (currentInput.equals("Input from Console")) {
                    state = 2;
                }
                break;
            case 2:
                System.out.println("Enter Street Name");
                currentInput = scanner.next();
                if (!currentInput.isEmpty()) {
                    state = 3;
                    streetBuilder.withStreetName(currentInput);
                }
            case 3:
                System.out.println("Enter House Number");
                currentInput = scanner.next();
                if (isStrShowsAmount(currentInput)) {
                    state = 4;
                    int houseNumber = Integer.parseInt(currentInput);
                    houseBuilder.withHouseNumber(houseNumber);
                }
            case 4:
                System.out.println("Enter Apartment Number");
                currentInput = scanner.next();
                if (isStrShowsAmount(currentInput)) {
                    state = 5;
                    int apartmentNumber = Integer.parseInt(currentInput);
                    apartmentBuilder.withApartmentNumber(apartmentNumber);
                }
            case 5:
                System.out.println("Enter Person's Name");
                currentInput = scanner.next();
                if (!currentInput.isEmpty()) {
                    personBuilder.withName(currentInput);
                    state = 6;
                }
            case 6:
                System.out.println("Enter Person's LastName");
                currentInput = scanner.next();
                if (!currentInput.isEmpty()) {
                    personBuilder.withLastName(currentInput);
                    state = 7;
                }
            case 7:
                System.out.println("Enter Person's money");
                currentInput = scanner.next();
                if (isStrShowsAmount(currentInput)) {
                    int money = Integer.parseInt(currentInput);
                    personBuilder.withMoney(money);
                    state = 8;
                }
            case 8:
                System.out.println("Enter Pet's name");
                currentInput = scanner.next();
                if (!currentInput.isEmpty()) {
                    petBuilder.withName(currentInput);
                    state = 9;
                }
            case 9:
                System.out.println("Enter Animal type");
                currentInput = scanner.next();
                if (isStrBelongsAnimal(currentInput)) {
                    petBuilder.withAnimal(Animal.valueOf(currentInput));
                    state = 10;
                    pets.add(petBuilder.build());
                }
            case 10:
                System.out.println("Do you want to add something?");
                String decision = scanner.next();
                if (isRightFormat(decision)) {
                    if (decision.equals("Yes")) {
                        System.out.println("Pet\nPerson\nApartment\nHouse");

                    }
                }

        }
    }

    private boolean isStrShowsAmount(String str) {
        if (!str.isEmpty()) {
            Pattern allDigits = Pattern.compile("[0-9]+");
            Matcher matcher = allDigits.matcher(str);
            if (matcher.matches()) {
                return Integer.parseInt(str) > 0;
            }
        }
        return false;
    }

    private boolean isRightFormat(String str) {
        if (!str.isEmpty()) {
            return (str.equals("Yes") || )
        }
        return false;
    }
    private boolean isStrBelongsAnimal(String str) {
        for (Animal animal : Animal.values()) {
            if (str.equals(animal.name())) {
                return true;
            }
        }
        return false;
    }
}
