package builders;


import java.util.Scanner;
import tools.Tools;


public class StreetDirector {

    public void constructStreetFromConsole(Builder builder) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input Street Name");
            String streetName = scanner.nextLine();
            builder.establishStreet(streetName);
            System.out.println("Do you want to end building street?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            String decision = scanner.nextLine();
            while (Tools.checkCorrectDecision(decision)) {
                System.out.println("Wrong decision! Input again");
                decision = scanner.nextLine();
            }
            if (decision.equals("1") || decision.equals("Yes")) {
                System.out.println("Do you want to create new Street ?");
                System.out.println("1. Yes");
                System.out.println("2. No");

                String decision1 = scanner.nextLine();
                while (Tools.checkCorrectDecision(decision1)) {
                    System.out.println("Wrong decision! Input again");
                    decision1 = scanner.nextLine();
                }
                if (decision1.equals("2") || decision1.equals("No")) {
                    break;
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
                }
            }

        }
    }
}
