package inputoutput;


import java.util.Scanner;

public class InputStreet {
    public static void run() {
        System.out.println("Choose option");
        System.out.println("1. Standard input");
        System.out.println("2. Input from file");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String option = scanner.nextLine();
            if (option.equals("Standard input") || option.equals("1")) {
                break;
            }
            else if (option.equals("Input from file") || option.equals("2")) {
                break;
            }
            else {
                System.out.println("Wrong! Choose option again");
            }
        }
    }
}
