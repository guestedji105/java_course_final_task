package org.example;

import java.util.Scanner;

import static org.example.CSVFileGeneration.*;

public class Main {
    static boolean isRunAway;

    public static void main(String[] args) {
        CSVFileGeneration fileGeneration = new CSVFileGeneration();
        Scanner scanner = new Scanner(System.in);
        int numberOfChoice;
        do {
            do {
                //функционал основного меню
                System.out.println("Menu:\n" +
                        "1. Write CSV-file with a table\n" +
                        "2. Write CSV-file with random emails\n" +
                        "3. Write txt-file with a random text\n" +
                        "4. Exit\n" +
                        "Choose the option: ");
                numberOfChoice = scanner.nextInt();
            } while (numberOfChoice <= 0);
            if (numberOfChoice == 1) {
                fileGeneration.chooseColumns();
                fileGeneration.chooseStrings(countOfTextSymbols, countOfTokenSymbols);
                fileGeneration.writeCSVTable();
                isRunAway = true;
            } else if (numberOfChoice == 2) {
                fileGeneration.writeCSVRandomEmails();
                isRunAway = true;
            } else if (numberOfChoice == 3) {
                fileGeneration.writeCSVRandomText();
                isRunAway = true;
            } else if (numberOfChoice == 4) {
                System.out.println("See you again!");
                isRunAway = false;
            } else {
                isRunAway = true;
            }
        } while (isRunAway);
    }
}