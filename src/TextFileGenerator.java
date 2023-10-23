import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TextFileGenerator {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the text length (in characters): ");
            int textLength = scanner.nextInt();
            generateTextFile(textLength);
            System.out.println("Text file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateTextFile(int textLength) throws IOException {
        FileWriter textWriter = new FileWriter("text_data.txt");
        Random random = new Random();

        String characters = "ab cdefghijk lmnopqrstuv wxyzABC DEFGH IJKLMN OPQRSTUV WXYZ01 23456 789 ";

        for (int i = 0; i < textLength; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            textWriter.write(randomChar);
        }

        textWriter.close();
    }
}