package Kursach;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFaKer {


    String[] nameDomen = new String[]{".com", ".org.net", ".edu", ".gov", ".mil", ".info", ".io", ".co", ".me", ".blog", ".app", ".store", ".tv", ".dev", ".design", ".io", ".online", ".app", ".ca"};
    char[] specialCharacter = new char[]{'-', '.', '_'};
    public static String fileName = "src/Kursach/name_english.txt";
    public static String fileSurName = "src/Kursach/surname_english.txt";
    public String fileCountryCode = "src/Kursach/country_code.txt";
    public static String fileText = "src/Kursach/Text_Book.txt";
    public String fileOutputText = "src/Kursach/outputText.txt";
    public String fileCsvEmail = "src/Kursach/email.csv";
    public String outputCsvFileTable = "src/Kursach/outputCsvTable.csv";
    Random random = new Random();


    //пункт 1 формирование и запись данных в csv файл (заголовки + значение)
    public void generatingCsvFileWithTable(String csvFilePath, int numberOfLine, int willMailboxes, int willFirstNameField, int willLastNameField,
                                           int willPhoneField, int willDataOfBirthField, int lengthToken, int lengthText) {

        try (FileWriter writer = new FileWriter(csvFilePath)) {
            List<String> header = new ArrayList<>();
            ArrayList<String> listTable = new ArrayList<>();
            int counterLine = 0;
            // Определите данные для записи в файл
            if (willMailboxes == 1) {
                header.add("email");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationOneEMail());
                }
            }
            if (willFirstNameField == 1) {
                header.add("First Name");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationFirstName());
                }
            }
            if (willLastNameField == 1) {
                header.add("Last Name");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationLastName());
                }
            }
            if (willPhoneField == 1) {
                header.add("Phone");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationPhoneNumber());
                }
            }
            if (willDataOfBirthField == 1) {
                header.add("Date of Birth");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationDataOfBirth()); //нужно дописать метод
                }
            }
            if (lengthToken > 0) {
                header.add("token");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationToken(lengthToken));
                }
            }
            if (lengthText > 0) {
                header.add("text");
                counterLine++;
                for (int i = 0; i < numberOfLine; i++) {
                    listTable.add(creationTextCharacters(lengthText).toString()); //нужно дописать метод
                }
            }
            //System.out.println(listTable);

            for (int i = 0; i < header.size(); i++) {
                writer.append(header.get(i));
                if (i < header.size() - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            for (int i = 0; i < numberOfLine; i++) {
                int n= i;
                for (int j = 0; j < counterLine; j++) {
                    writer.append(listTable.get(n));
                    n = n + numberOfLine;
                    if (j == counterLine - 1) {
                        writer.append("\n");
                    } else {
                        writer.append(",");
                    }
                }
            }
            System.out.println("CSV файл успешно создан. Он находиться по адресу "+csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //генерация дня рождения !!!ДОДЕЛАТЬ!!!
    public String creationDataOfBirth() {
        return "01.01.1978";
    }

    //запись слов из текстового файла в лист
    public List<String> writeFromFileTxtToCollection(String fileName) {
        ArrayList<String> listData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listData;
    }

    //генерация телефонного номера
    public String creationPhoneNumber() {
        StringBuilder numberPhone = new StringBuilder();
        List<String> listAllCodeCountry = writeFromFileTxtToCollection(fileName);
        int randomCode = random.nextInt(listAllCodeCountry.size() - 1);
        numberPhone.append("+");
        numberPhone.append(randomCode);
        int lenght = 14 - numberPhone.length();
        for (int i = 0; i < lenght; i++) {
            numberPhone.append(creationRandomNumber());
        }
        return numberPhone.toString();
    }

    //генерация одного Имени из файла
    public String creationFirstName() {
        ArrayList<String> listAllFirstNames = new ArrayList<>(writeFromFileTxtToCollection(fileSurName));
        int randomNameNumber = random.nextInt(listAllFirstNames.size());
        return listAllFirstNames.get(randomNameNumber);
    }

    //генерация одной фамилии из файла
    public String creationLastName() {
        ArrayList<String> listAllLastNames = new ArrayList<>(writeFromFileTxtToCollection(fileSurName));
        int randomNameNumber = random.nextInt(listAllLastNames.size());
        return listAllLastNames.get(randomNameNumber);
    }

    //генерация случайного числа от 0 до 9
    public int creationRandomNumber() {
        return random.nextInt(10);
    }

    //генерация одного случайного спецсимвола из списка
    public char creationSpecialCharacter() {
        return specialCharacter[random.nextInt(specialCharacter.length)];
    }

    //генерация случайного доменного имени для адреса почты из списка
    public String creationRandomNameDomen() {
        return nameDomen[random.nextInt(nameDomen.length)];
    }

    //создание токена
    public String creationToken(int lengthToken) {
        StringBuilder newWord = new StringBuilder();
        for (int i = 0; i < lengthToken; i++) {
            if (((random.nextDouble() < 0.8) ? 1 : 2) == 1) {
                if (((random.nextDouble() < 0.8) ? 1 : 2) == 1) {
                    newWord.append(creationLoverCaseLetter());
                } else {
                    newWord.append(creationUpperCaseLetter());
                }
            } else {
                newWord.append(creationRandomNumber());
            }
        }
        return new String(newWord);
    }

    //создание левой части адреса почты
    public String creationlocalNameEmail() {
        StringBuilder newWord = new StringBuilder();
        int lengthLocalNameEmail = random.nextInt(10) + 1;
        int randomNumber;

        for (int i = 0; i <= lengthLocalNameEmail; i++) {

            randomNumber = random.nextInt(100);

            if (randomNumber < 70) {
                newWord.append(creationLoverCaseLetter());
            } else if (randomNumber < 85) {
                newWord.append(creationRandomNumber());
            } else if (randomNumber < 95) {
                newWord.append(creationUpperCaseLetter());
            } else {
                newWord.append(creationSpecialCharacter());
            }
        }
        return new String(newWord);
    }

    //создание одного e-mail
    public String creationOneEMail() {
        return creationlocalNameEmail() + "@" + creationToken(random.nextInt(10) + 3) + creationRandomNameDomen();
    }

    //создание списка из e-mail
    public List<String> creationListEMail(int totalNumberEMail) {
        List<String> listEMail = new ArrayList<>();
        for (int i = 0; i < totalNumberEMail; i++) {
            listEMail.add(creationOneEMail());
        }
        return listEMail;
    }

    //запись сгенерированного количества e-mail в csv файл
    public void writingEMailToCsv(int totalNumberEMail, String csvFilePath) {
        List<String> listText = new ArrayList<>(creationListEMail(totalNumberEMail));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (int i = 0; i < totalNumberEMail; i++) {
                writer.write(listText.get(i));
                writer.write("\n");
            }
            System.out.println();
            System.out.println("Сгенерированные e-mail успешно записаны в файл - " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //генерация большой буквы
    public char creationUpperCaseLetter() {
        return (char) (random.nextInt(26) + 'A'); // Генерируем случайную букву верхнего регистра (A-Z)
    }

    //генерация маленькой буквы
    public char creationLoverCaseLetter() {
        return (char) (random.nextInt(26) + 'a'); // Генерируем случайную букву нижнего регистра (A-Z)
    }

    //генерация слова (для предложения)
    public String creationWord(int lengthWord) {
        char[] newWord = new char[lengthWord];
        for (int i = 0; i < lengthWord; i++) {
            newWord[i] = creationLoverCaseLetter();
        }
        return new String(newWord);
    }

    //генерация Первого слова (для предложения)
    public String creationFirstWord(int lengthWord) {
        char[] newWord = new char[lengthWord];
        for (int i = 0; i < lengthWord; i++) {
            if (i == 0) {
                newWord[i] = creationUpperCaseLetter();
            } else {
                newWord[i] = creationLoverCaseLetter();
            }
        }
        return new String(newWord);
    }

    //генерация последнего слова (для предложения)
    public String creationLastWord(int lengthWord) {
        return creationWord(lengthWord) + ".";
    }

    //создание предложения с указанием сколько слов в предложении
    public List<String> creationSentence(int numberWordsInSentence) {  //создание предложения
        ArrayList<String> sentence = new ArrayList<>();
        for (int i = 0; i <= numberWordsInSentence - 1; i++) {
            if (i == 0) {
                sentence.add(creationFirstWord(random.nextInt(10) + 1));
            } else if (i == numberWordsInSentence - 1) {
                sentence.add(creationLastWord(random.nextInt(8) + 1));
            } else {
                sentence.add(creationWord(random.nextInt(9) + 1));
            }
        }
        return sentence;
    }

    //создание текста который зависит от кол.слов
    public List<String> creationTextWords(int totalNumberWordsInText) { //создание всего текста
        ArrayList<String> allText = new ArrayList<>();
        int numberWordsInSentence;
        int minWordInSentence = 2;
        int maxWordSentence = 12;

        while (allText.size() < totalNumberWordsInText) {
            numberWordsInSentence = random.nextInt((maxWordSentence - minWordInSentence + 1) + maxWordSentence);
            if (numberWordsInSentence <= (totalNumberWordsInText - allText.size())) {
                allText.addAll(creationSentence(numberWordsInSentence));
            } else {
                allText.addAll(creationSentence(totalNumberWordsInText - allText.size()));
            }
        }
        return allText;
    }

    //создание рандомного текста (условие - колличество символов) !!!ДОПИСАТЬ!!!
    public List<String> creationTextCharacters(int totalCharactersInText) {
        List<String> allText = writeFromFileTxtToCollection(fileText);


        return allText;
    }

    //запись текста в txt файл с указанием количества слов в тексте и с указанием форматирования
    public void writingWordsToTxtFile (int totalNumberWordsInText, int numberWordsPerLine) {
        List<String> listText = new ArrayList<>(creationTextWords(totalNumberWordsInText));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutputText))) {
            if (numberWordsPerLine != 0) {
                for (int i = 0; i < listText.size(); i++) {

                    if ((i + 1) % numberWordsPerLine == 0) {
                        writer.write(listText.get(i));
                        writer.write(" ");
                        writer.newLine();
                    } else {
                        writer.write(listText.get(i));
                        writer.write(" ");
                    }
                }
            } else {
                for (int i = 0; i < listText.size(); i++) {
                    if (i != listText.size() - 1) {
                        writer.write(listText.get(i));
                        writer.write(" ");
                    } else {
                        writer.write(listText.get(i));
                    }
                }
            }
            System.out.println();
            System.out.println("Данные успешно записаны в файл "+ fileOutputText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}









        


