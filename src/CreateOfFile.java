import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CreateOfFile {

    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> lastNames = new ArrayList<>();
    private ArrayList<String> codsCountry = new ArrayList<>();
    private String wordUpperCase = "ABCDEFHIJKLMNOPQRSTVWXYZ";
    private String wordLowCase = "abcdefghijklmnopqrstvwxyz";
    private String numbers = "1234567890";
    private String[] punktMarks = {",\s", ".\s", "\s", "\s", "\s", "\s"};
    private String allCharacters = wordLowCase + wordUpperCase + numbers;
    private String emailAllCharacters = wordLowCase + numbers;
    private String[] emailTails = {"@gmail.com", "@mail.com", "@yandex.ru", "@gmx.de", "@yahoo.com", "@outlook.com",
            "@google.com", "@hushmail.com", "@icloud.com"};

    public CreateOfFile() {
        try {
            this.firstNames = readFile("firstname.txt");
            this.lastNames = readFile("LastName.txt");
            this.codsCountry = readFile("codscountry.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private ArrayList<String> readFile(String filePath) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> buf = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                buf.add(line);
            }
            return buf;
        } catch (IOException e) {
            throw new IOException("Error reading file '" + filePath + "': " + e.getMessage());
        }
    }

    public String generateFirstName() {
        Random random = new Random();
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    public String generateLastName() {
        Random random = new Random();
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public String generateEmail() {
        Random random = new Random();
        int length = 3 + random.nextInt(4);
        StringBuilder nik = new StringBuilder();
        nik.append(generateFirstName().toLowerCase()).append(".");
        for (int i = 0; i < length; i++) {
            nik.append(emailAllCharacters.charAt(random.nextInt(emailAllCharacters.length())));
        }
        nik.append(emailTails[random.nextInt(emailTails.length)]);
        return nik.toString();
    }

    public String generateNumberPhone(){
        Random random = new Random();
        String code = codsCountry.get(random.nextInt(codsCountry.size()));
        int areaCode = random.nextInt(100,999);
        int localPart =  random.nextInt(100000,999999);

        return "+" + code + " " + areaCode + " " + localPart;
    }

    public String generateToken(int length){
            Random random = new Random();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < length; i++) {
                result.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
            }
            return result.toString();
        }

    public String generateText ( int length, boolean inWords){
            Random random = new Random();
            StringBuilder str = new StringBuilder();
            if (inWords) {
                for (int i = 0; i < length; i++) {  // генерируем текст по заданному количеству слов
                    str.append(generateWord()).append(punktMarks[random.nextInt(punktMarks.length)]);
                }
            } else {
                while (str.length() < length) {  // генерируем текст по заданному количеству сисволов
                    str.append(generateWord()).append(punktMarks[random.nextInt(punktMarks.length)]);
                }
            }

            return inWords
                    ? str.toString()
                    : str.substring(0, length);
        }

    private String generateWord () {
            Random random = new Random();
            StringBuilder result = new StringBuilder();
            int length = 3 + random.nextInt(7);
            String alphabet = wordLowCase + wordUpperCase;
            result.append(alphabet.charAt(random.nextInt(alphabet.length()))); // добавим первую букву слова
            for (int i = 0; i < length; i++) {
                result.append(wordLowCase.charAt(random.nextInt(wordLowCase.length())));
            }
            return result.toString();
        }

    public String generateDateOfBirth ()  {
       Random random = new Random();
       int day = random.nextInt(1,28);
       int month = random.nextInt(1,12);
       int year = 1965 + random.nextInt(35);
       return day + "/" + month + "/" + year;
    }
}
