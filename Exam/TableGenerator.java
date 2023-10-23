package Exam;

import static Exam.TextGenerator.generateRandomText;
import static Exam.TokenGenerator.generateRandomToken;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TableGenerator {

    static void generateCsvTable() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите количество строк: ");
            int numRows = Integer.parseInt(scanner.nextLine());

            String[] rowNames = new String[]{"Введите e-mail:", "Введите имя:", "Введите фамилию:",
                    "Введите телефон:", "Введите дату рождения:", "Введите длину токена:", "Введите длину текста:"};
            String[][] tableData = new String[numRows][rowNames.length];

            for (int i = 0; i < numRows; i++) {
                for (int q = 0; q < rowNames.length; q++) {
                    System.out.print(rowNames[q]);
                    tableData[i][q] = scanner.nextLine();
                }
            }

            FileWriter writer = new FileWriter("table.csv");

            String[] header = {
              "E-mail", "Имя", "Фамилия", "Телефон", "Дата рождения", "Токен", "Текст"
            };

            // Запись заголовков
            for (int i = 0; i < header.length; i++) {
              writer.append(header[i] + " ");
            }
            writer.append("\n");

            // Запись строк с данными
            if (tableData.length > 0) {
                for (String[] row : tableData) {
                    for (int i = 0; i < row.length; i++) {
                        switch (i) {
                            case 5: {
                                writer.append(generateRandomToken(Integer.parseInt(row[i])));
                                break;
                            }
                            case 6: {
                                writer.append(generateRandomText(Integer.parseInt(row[i])));
                                break;
                            }
                            default: {
                                writer.append(row[i]);
                                break;
                            }
                        }
                        if (i < row.length - 1) {
                            writer.append(",");
                        }
                    }
                    writer.append("\n");
                }
            }

            writer.close();

            System.out.println("CSV файл создан: table.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
