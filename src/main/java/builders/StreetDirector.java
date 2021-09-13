package builders;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import restrictions.Animals;
import tools.Tools;


public class StreetDirector {
    private String decision;;
    private Scanner scanner = new Scanner(System.in) ;

    private void chooseYesOrNO() {
        System.out.println("1. Yes");
        System.out.println("2. No");
        decision = scanner.next();
        while (!Tools.checkCorrectDecision(decision)) {
            System.out.println("Wrong decision! Input again");
            decision = scanner.next();
        }
    }

    public void constructStreetFromConsole(Builder builder) {
        while (true) {
            System.out.println("Input Street Name");
            String streetName = scanner.nextLine();
            builder.establishStreet(streetName);
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
                    builder.buildHome(homeNumber);
                    while (true) {
                        System.out.println("Input Room Number");
                        int roomNumber = scanner.nextInt();
                        while (roomNumber <= 0) {
                            System.out.println("Wrong Room Number format! Input again");
                            roomNumber = scanner.nextInt();
                        }
                        builder.buildRoom(roomNumber);
                        while (true) {
                            System.out.println("Input Person Name");
                            String personName = scanner.next();
                            System.out.println("Input person LastName");
                            String personLastName = scanner.next();
                            System.out.println("Input person's money count");
                            int moneyCount = scanner.nextInt();
                            builder.addResident(personName, personLastName, moneyCount);
                            while (true) {
                                System.out.println("Input Pet Name");
                                String petName = scanner.next();
                                System.out.println("Input Animal Type");
                                String animalType = scanner.next();
                                while (!Tools.isEnumContainsString(animalType)) {
                                    System.out.println("Inappropriate animal!");
                                    animalType = scanner.next();
                                }
                                Animals animal = Animals.valueOf(animalType);
                                builder.assignPetToResident(petName, animal);
                                System.out.println("Do you want to add Pet?");
                                chooseYesOrNO();
                                if (decision.equals("No") || decision.equals("2")) {
                                    break;
                                }
                            }
                            System.out.println("Do you want to add Resident?");
                            chooseYesOrNO();
                            if (decision.equals("No") || decision.equals("2")) {
                                break;
                            }
                        }
                        System.out.println("Do you want to add Room?");
                        chooseYesOrNO();
                        if (decision.equals("No") || decision.equals("2")) {
                            break;
                        }
                    }
                    System.out.println("Do you want ot add Home");
                    chooseYesOrNO();
                    if (decision.equals("No") || decision.equals("2")) {
                        break;
                    }
                }
            }
            System.out.println("The street has been built!");
            break;
        }
    }
}
