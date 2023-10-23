package Exam;

import static Exam.TextGenerator.generateRandomText;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TextFileGenerator {
    public static void generateTextFile() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите длину текста (в символах): ");
            int textLength = scanner.nextInt();

            FileWriter writer = new FileWriter("text.txt");

            writer.write(generateRandomText(textLength));

            writer.close();

            System.out.println("Текстовый файл создан: text.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

