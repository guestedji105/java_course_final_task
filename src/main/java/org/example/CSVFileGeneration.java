package org.example;

import net.datafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVFileGeneration {
    Faker faker = new Faker();
    Scanner scanner = new Scanner(System.in);
    static ArrayList<ArrayList<String>> csvList = new ArrayList<>();
    static ArrayList<String> newHeader = new ArrayList<>();
    static boolean isGoOut;
    static int countOfTextSymbols;
    static int countOfTokenSymbols;

    public void chooseColumns() {
        int numberOfChoice;
        do {
            do {
                //основное меню выбора колонок таблицы и заполнение заголовков
                System.out.println("Choose the columns for CSV-file:\n" +
                        "1. Email\n" +
                        "2. First Name\n" +
                        "3. Last Name\n" +
                        "4. Phone\n" +
                        "5. Date Of Birth\n" +
                        "6. Token\n" +
                        "7. Text\n" +
                        "8. Exit from a choice\n");
                numberOfChoice = scanner.nextInt();
            } while (numberOfChoice <= 0);
            if (numberOfChoice == 1) {
                isGoOut = true;
                newHeader.add("Email");
            } else if (numberOfChoice == 2) {
                isGoOut = true;
                newHeader.add("First Name");
            } else if (numberOfChoice == 3) {
                isGoOut = true;
                newHeader.add("Last Name");
            } else if (numberOfChoice == 4) {
                isGoOut = true;
                newHeader.add("Phone");
            } else if (numberOfChoice == 5) {
                isGoOut = true;
                newHeader.add("Date Of Birth");
            } else if (numberOfChoice == 6) {
                isGoOut = true;
                newHeader.add("Token");
                do {
                    //выбор длины токена в символах
                    System.out.println("Choose length of token in symbols");
                    countOfTokenSymbols = scanner.nextInt();
                } while (countOfTokenSymbols <= 0);
            } else if (numberOfChoice == 7) {
                isGoOut = true;
                newHeader.add("Text");
                do {
                    //выбор длины текста в символах
                    System.out.println("Choose length of text in symbols");
                    countOfTextSymbols = scanner.nextInt();
                } while (countOfTextSymbols <= 0);
            } else if (numberOfChoice == 8) {
                System.out.println("See you again!");
                isGoOut = false;
            } else {
                isGoOut = true;
            }
        } while (isGoOut);
        //решение проблемы выбора пользователем одинаковых колонок
        Set<String> sortedHeader = new LinkedHashSet<>(newHeader);
        newHeader.clear();
        newHeader.addAll(sortedHeader);
    }

    public void chooseStrings(int countOfTextSymbols, int countOfTokenSymbols) {
        int countOfString;
        do {
            //выбор количества строк в таблице
            System.out.println("Choose a number of strings");
            countOfString = scanner.nextInt();
        } while (countOfString <= 0);
        for (int i = 0; i < countOfString; i++) {
            ArrayList<String> row = new ArrayList<>();
            //генерация данных
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String dateOfBirth = faker.date().birthday("yyyy-MM-dd");
            String email = faker.internet().emailAddress();
            String text = faker.lorem().fixedString(countOfTextSymbols);
            String token = String.valueOf(faker.text().text(countOfTokenSymbols, countOfTokenSymbols, true, false, true));
            String phone = faker.phoneNumber().phoneNumber();
            //запись данных в строки в соответствии с порядком и наименованиями, выбранными пользователем для заголовков
            for (int j = 0; j < newHeader.size(); j++) {
                if (newHeader.get(j).equals("Email")) {
                    row.add(email);
                } else if (newHeader.get(j).equals("First Name")) {
                    row.add(firstName);
                } else if (newHeader.get(j).equals("Last Name")) {
                    row.add(lastName);
                } else if (newHeader.get(j).equals("Phone")) {
                    row.add(phone);
                } else if (newHeader.get(j).equals("Date Of Birth")) {
                    row.add(dateOfBirth);
                } else if (newHeader.get(j).equals("Token")) {
                    row.add(token);
                } else if (newHeader.get(j).equals("Text")) {
                    row.add(text);
                }
            }
            csvList.add(row);
        }
    }

    public void writeCSVTable() {
        String csvTable = "table.csv";
        // Запись заголовков в файл
        try (FileWriter writer = new FileWriter(csvTable)) {
            for (int i = 0; i < newHeader.size(); i++) {
                writer.append(newHeader.get(i));
                if (i < newHeader.size() - 1) {
                    writer.append(", ");
                }
            }
            writer.append("\n");
            // Запись строк с данными в файл
            for (ArrayList<String> row : csvList) {
                for (int i = 0; i < row.size(); i++) {
                    writer.append(row.get(i));
                    if (i < row.size() - 1) {
                        writer.append(", ");
                    }
                }
                writer.append("\n");
            }
            System.out.println("CSV-file created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSVRandomEmails() {
        ArrayList<String> emails = new ArrayList<>();
        String csvEmails = "randomEmails.csv";
        int numberOfEmails;
        do {
            //выбор количества почтовых адресов и их генерация
            System.out.println("Choose number of emails");
            numberOfEmails = scanner.nextInt();
            for (int i = 0; i < numberOfEmails; i++) {
                String email = faker.internet().emailAddress();
                emails.add(email);
            }
        } while (numberOfEmails <= 0);
        //запись адресов в файл
        try (FileWriter writer = new FileWriter(csvEmails)) {
            for (int i = 0; i < emails.size(); i++) {
                writer.append(emails.get(i));
                if (i < emails.size() - 1) {
                    writer.append(", ");
                }
            }
            writer.append("\n");
            System.out.println("CSV-file created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSVRandomText() {
        String randomText = "randomText.txt";
        int countOfSymbols;
        do {
            //выбор количества символов для текста
            System.out.println("Choose length of text in symbols");
            countOfSymbols = scanner.nextInt();
        } while (countOfSymbols <= 0);
        //генерация текста и его запись в текстовый файл
        String text = faker.lorem().fixedString(countOfSymbols);
        try (FileWriter writer = new FileWriter(randomText)) {
            writer.append(text);
            System.out.println("txt-file created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


