package inputoutput;


import builders.ConstructStreetDirector;
import entities.Street;

import java.util.Scanner;

public class InputStreet {
    private Street street;

    public void run() {
        System.out.println("Choose option");
        System.out.println("1. Standard input");
        System.out.println("2. Input from file");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        ConsoleIO consoleIO = new ConsoleIO();
        while (true) {
            if (option.equals("Standard input") || option.equals("1")) {
                street = consoleIO.constructStreetFromConsole();
                break;
            }
            else if (option.equals("Input from file") || option.equals("2")) {
                street = consoleIO.constructStreetFromFile();
                break;
            }
            else {
                System.out.println("Wrong! Choose option again");
            }
        }
        System.out.println("Choose output way");
        System.out.println("1. Output to console");
        System.out.println("2. Output to file");
        while (true) {
            OutputStreet outputStreet = new OutputStreet();

        }

    }
}
