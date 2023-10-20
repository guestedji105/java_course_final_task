package Kursach;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVExample {
    public static void main(String[] args) {
        // Укажите путь и имя CSV файла
        String csvFilePath = "sample.csv";

        try (FileWriter writer = new FileWriter(csvFilePath)) {
            // Определите данные для записи в файл
            String[] header = {"Name", "Age", "City"};
            String[] row1 = {"Alice", "30", "New York"};
            String[] row2 = {"Bob", "25", "San Francisco"};
            String[] row3 = {"Charlie", "35", "Los Angeles"};

            // Запись заголовков
            for (int i = 0; i < header.length; i++) {
                writer.append(header[i]);
                if (i < header.length - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            // Запись строк с данными
            String[][] data = {row1, row2, row3};
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    writer.append(row[i]);
                    if (i < row.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }

            System.out.println("CSV файл успешно создан.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
