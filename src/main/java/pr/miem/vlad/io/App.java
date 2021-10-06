package pr.miem.vlad.io;

import pr.miem.vlad.entities.Street;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private int state;
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleInput consoleInput = new ConsoleInput();
    private final ConsoleOutput consoleOutput = new ConsoleOutput();
    private final InputFromFile inputFromFile = new InputFromFile();
    private final OutputToFIle outputToFile = new OutputToFIle();
    private final String fullPath = System.getProperty("user.dir") + "\\src\\main\\java\\pr\\miem\\vlad\\";
    private Street currentStreet;
    public void runApp() throws IOException {
        String option;
        while (state != 7) {
            switch (state) {
                case 0 -> {
                    System.out.println("Choose option");
                    System.out.println("Standard input");
                    System.out.println("Input from file");
                    option = scanner.nextLine();
                    if (option.equals("Standard input")) {
                        state = 1;
                    } else if (option.equals("Input from file")) {
                        state = 2;
                    }
                }
                case 1 -> {
                    currentStreet = consoleInput.getStreetFromConsole();
                    state = 3;
                }
                case 2 -> {
                    System.out.println("Input file Name");
                    String inputFileName = scanner.nextLine();
                    currentStreet = inputFromFile.parseFile((fullPath + inputFileName));
                    state = 3;
                }
                case 3 -> {
                    System.out.println("Choose option");
                    System.out.println("Print to console");
                    System.out.println("Write in file");
                    option = scanner.nextLine();
                    if (option.equals("Print to console")) {
                        state = 4;
                    } else if (option.equals("Write in file")) {
                        state = 5;
                    }
                }
                case 4 -> {
                    consoleOutput.printStreetToConsole(currentStreet);
                    state = 6;
                }
                case 5 -> {
                    System.out.println("Input output File Name");
                    String outputFileName = scanner.nextLine();
                    outputToFile.writeToFile(currentStreet, fullPath + outputFileName);
                    state = 6;
                }
                case 6 -> {
                    System.out.println("Do you want to finish?");
                    System.out.println("Yes");
                    System.out.println("No");
                    option = scanner.nextLine();
                    if (option.equals("Yes")) {
                        state = 7;
                    } else if (option.equals("No")) {
                        state = 0;
                    }
                }
            }
        }
    }
}
