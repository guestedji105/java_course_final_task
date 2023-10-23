import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CSVDataGenerator {
    public static void main(String[] args) {
        try {
            int numRows = 10; // Change this to the desired number of rows
            generateCSVTable(numRows);
            System.out.println("CSV table with random data generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateCSVTable(int numRows) throws IOException {
        FileWriter csvWriter = new FileWriter("data_table.csv");
        Random random = new Random();

        // Arrays of real first and last names
        String[] firstNames = {"John", "Alice", "Robert", "Emily", "David", "Sarah", "Michael", "Olivia", "Daniel", "Sophia"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Lee", "Taylor", "Williams", "Jones", "Davis", "Anderson", "Wilson"};

        // Write column headers
        csvWriter.append("Email,First Name,Last Name,Phone,Date Of Birth,Token,Text\n");

        // Generate and write random data for each column
        for (int i = 0; i < numRows; i++) {
            String email = "example" + random.nextInt(10000) + "@example.com";
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String phone = generateRandomPhoneNumber();
            String dateOfBirth = generateRandomDateOfBirth();
            String token = generateRandomToken(8);
            String text = generateRandomText(100);

            csvWriter.append(email + "," + firstName + "," + lastName + "," + phone + "," + dateOfBirth + "," + token + "," + text + "\n");
        }

        csvWriter.close();
    }

    private static String generateRandomText(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < length; i++) {
            text.append(characters.charAt(random.nextInt(characters.length())));
        }

        return text.toString();
    }

    private static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            phoneNumber.append(random.nextInt(10));
        }

        return phoneNumber.toString();
    }

    private static String generateRandomDateOfBirth() {
        Random random = new Random();
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        int year = random.nextInt(50) + 1950;

        return month + "/" + day + "/" + year;
    }

    private static String generateRandomToken(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < length; i++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }

        return token.toString();
    }
}

