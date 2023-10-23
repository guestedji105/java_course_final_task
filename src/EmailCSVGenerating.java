import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EmailCSVGenerating {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Email CSV Generator");
        System.out.print("Enter the number of email addresses to generate: ");
        int numEmails = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String csvFilePath = "generated_emails.csv";

        try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
            // Create header row
            csvWriter.append("Email");
            csvWriter.append("\n");

            for (int i = 0; i < numEmails; i++) {
                String email = generateRandomEmail();
                csvWriter.append(email);
                csvWriter.append("\n");
            }

            System.out.println("CSV file with random email addresses generated successfully at " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};
        String[] users = {"user1", "user2", "user3", "user4", "user5"};
        String domain = domains[(int) (Math.random() * domains.length)];
        String user = users[(int) (Math.random() * users.length)];
        return user + "@" + domain;
    }
}
