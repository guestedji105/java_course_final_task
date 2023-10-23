package Exam;

import static Exam.EmailGenerator.generateCsvEmails;
import static Exam.TableGenerator.generateCsvTable;
import static Exam.TextFileGenerator.generateTextFile;

import java.util.Scanner;

/*import static Exam.TextFileGenerator.generateRandomText;
import static Exam.TextFileGenerator.generateTextFile;*/

public class GeneratorCsvTxtt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите действие:");
        System.out.println("1. Генерация CSV файла с таблицей");
        System.out.println("2. Генерация CSV файла с email'ами");
        System.out.println("3. Генерация txt файла с текстом");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                generateCsvTable();
                break;
            case 2:
                generateCsvEmails();
                break;
            case 3:
                generateTextFile();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
}

