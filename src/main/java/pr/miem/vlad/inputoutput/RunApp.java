package pr.miem.vlad.inputoutput;

import pr.miem.vlad.entities.Street;

import java.io.IOException;
import java.util.Scanner;

public class RunApp {
    private Street street;
    private String inputFileName;
    private String outputFileName;
    public void run() throws IOException {
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
                System.out.println("Input file Name");
                inputFileName = scanner.next();
                InputFromFile inputFromFile = new InputFromFile(inputFileName);
                street = inputFromFile.parseFile();
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
            option = scanner.next();
            if (option.equals("Output to console") || option.equals("1")) {
                consoleIO.printStreetToConsole(street);
                break;
            }
            else if (option.equals("Output to file") || option.equals("2")) {
                System.out.println("Input file Name");
                outputFileName = scanner.next();
                OutputToFIle outputToFIle = new OutputToFIle(outputFileName, street);
                break;
            }
            else {
                System.out.println("Wrong! Choose option again");
            }
        }

    }
}
