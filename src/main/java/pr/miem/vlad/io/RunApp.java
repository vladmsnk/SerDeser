package pr.miem.vlad.io;

import pr.miem.vlad.entities.Street;

import java.io.IOException;
import java.util.Scanner;

public class RunApp {
    private int state;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleInput consoleInput = new ConsoleInput();
    private final ConsoleOutput consoleOutput = new ConsoleOutput();
    private final InputFromFile inputFromFile = new InputFromFile();
    private final OutputToFIle outputToFile = new OutputToFIle();
    private Street currentStreet;
    public void run() throws IOException {
        String option;
        switch (state) {
            case 0:
                System.out.println("Choose option");
                System.out.println("Standard input");
                System.out.println("Input from file");
                option = scanner.nextLine();
                if (option.equals("Standard input")) {
                    state = 1;
                } else if (option.equals("Input from file")) {
                    state = 2;
                }
            break;
            case 1:
                currentStreet = consoleInput.getStreetFromConsole();
                state = 3;
            break;
            case 2:
                String inputFileName = scanner.next();
                currentStreet = inputFromFile.parseFile(inputFileName);
                state = 3;
            break;
            case 3:
                System.out.println("Choose option");
                System.out.println("Print to console");
                System.out.println("Write in file");
                option = scanner.nextLine();
                if (option.equals("Print to console")) {
                    state = 4;
                } else if (option.equals("Write in file")) {
                    state = 5;
                }
            break;
            case 4:
                consoleOutput.printStreetToConsole(currentStreet);
                state = 6;
            break;
            case 5:
                String outputFileName = scanner.next();
                outputToFile.writeToFile(currentStreet, outputFileName);
                state = 6;
            break;
            case 6:
                System.out.println("Do you want to finish?");
                System.out.println("Yes");
                System.out.println("No");
                option = scanner.nextLine();
                if (option.equals("No")) {
                    state = 0;
                }
            break;
        }

    }
}
