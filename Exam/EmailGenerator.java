package Exam;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EmailGenerator {

    private static String generateRandomEmail(Integer number) {
        String[] domains = {"example.com", "test.com", "domain.com", "mail.com", "gmail.com"};
        String[] names = {"user", "admin", "info", "support", "webmaster"};

        Random random = new Random();

        String randomName = names[random.nextInt(names.length)];
        String randomDomain = domains[random.nextInt(domains.length)];

        String emailNum = "";
        if(number != null) {
            emailNum = number.toString();
        }

        return randomName + emailNum + "@" + randomDomain;
    }

    static void generateCsvEmails() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите количество email'ов: ");
            int numEmails = scanner.nextInt();

            FileWriter writer = new FileWriter("emails.csv");

            ArrayList<String> emails = new ArrayList<String>();

            Random random = new Random();

            for (int i = 0; i < numEmails; i++) {
                String email = generateRandomEmail(null);
                while (emails.contains(email)) {
                    email = generateRandomEmail(random.nextInt(emails.size() * 100));
                }
                emails.add(email);
            }

            for (int i = 0; i < emails.size(); i++) {
                writer.write(emails.get(i) + "\n");
            }

            writer.close();

            System.out.println("CSV файл с email'ами создан: emails.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
