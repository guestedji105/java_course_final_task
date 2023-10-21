package Kursach;

import java.util.Scanner;

public class Menu {
    final static String ITALIC_START = "\u001B[3m";
    final static String ITALIC_END = "\u001B[0m";
    Scanner scannerInt = new Scanner(System.in);

    MyFaKer myFaKer = new MyFaKer();

    void textMainMenu() {
        System.out.println("\n   Генерация данных:");
        System.out.println(ITALIC_START + "  1. Генерация CSV файла с таблицей с возможность выбора полей." + ITALIC_END);
        System.out.println(ITALIC_START + "  2. Генерация CSV файла со случайны e-mail." + ITALIC_END);
        System.out.println(ITALIC_START + "  3. Генерация txt файла " + ITALIC_END);
        System.out.println(ITALIC_START + "  4. Выход." + ITALIC_END);
        System.out.print("Выберите опцию - ");
    }

    void outputMainMenu() {
        int changeSelectionMainMenu;
        do {
            textMainMenu();
            changeSelectionMainMenu = scannerInt.nextInt();
            while ((changeSelectionMainMenu < 1 || changeSelectionMainMenu > 4)) {
                System.out.println("Сделайте правильный выбор");
                changeSelectionMainMenu = scannerInt.nextInt();
            }
            switch (changeSelectionMainMenu) {
                case 1:
                    requestDataAndFileCSVTableGeneration();
                    break;
                case 2:
                    requestEMailAndCSVFileGeneration();
                    break;
                case 3:
                    requestNumberStringAndTxtFileGeneration();
                    break;
                case 4:
                    System.out.println("Приятной Вам работы с данными");
                    break;
            }
        } while (changeSelectionMainMenu != 4);
    }

    void requestDataAndFileCSVTableGeneration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Генерация файла CSV с таблицей.");
        System.out.print("Введите количество строк: ");
        int numberOfLine = scanner.nextInt();

        System.out.print("Далее Вам необходимо будет выбрать в какие столбцы будут генерироваться данные\n 0-данный столбец не нужно формировать\n 1-формировать\n");

        System.out.print("\tПоле e-mail - ");
        int willMailboxes = scanner.nextInt();

        System.out.print("\tПоле First Name - ");
        int willFirstNameField = scanner.nextInt();

        System.out.print("\tПоле Last Name - ");
        int willLastNameField = scanner.nextInt();

        System.out.print("\tПоле Phone - ");
        int willPhoneField = scanner.nextInt();

        System.out.print("\tполе Date of Birth - ");
        int willDataOfBirthField = scanner.nextInt();

        System.out.print("\tполе token  - введите длину (если 0 то поле не будет генерироваться) - ");
        int lengthToken = scanner.nextInt();

        System.out.print("\tполе text  - введите длину (если 0 то поле не будет генерироваться) - ");
        int lengthText = scanner.nextInt();

        myFaKer.generatingCsvFileWithTable(myFaKer.outputCsvFileTable, numberOfLine, willMailboxes, willFirstNameField,
                willLastNameField, willPhoneField, willDataOfBirthField, lengthToken, lengthText);
    }

    void requestEMailAndCSVFileGeneration() {
        Scanner scanner = new Scanner(System.in);
        int totalNumberEMail;
        do {
            System.out.println("Генерация CSV файла со случайными e-mail");
            System.out.print("Введите количество e-mail - ");
            totalNumberEMail = scanner.nextInt();
            myFaKer.writingEMailToCsv(totalNumberEMail, myFaKer.fileCsvEmail);

        } while (totalNumberEMail < 0);
    }

    void requestNumberStringAndTxtFileGeneration() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Генерация txt файла с текстом произвольной длины.");

        System.out.println("""
                Существует два варианта генерации произвольного текста
                1 - генерация по количеству символов в тексте
                2 - генерация по количеству слов в тексте
                Сделайте ваш выбор\s""");
        choice = scanner.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.println("Сделайте правильный выбор");
            choice = scanner.nextInt();
        }
        if (choice == 1) {
            System.out.println("Введите количество символов в новом тексте");
            int totalCharactersInText = scanner.nextInt();
            myFaKer.writingTextToTxtFile(myFaKer.creationTextCharacters(totalCharactersInText));

        } else {
            System.out.print("Введите размер текста в словах -");
            int totalNumberWordsInText = scanner.nextInt();
            System.out.print("Если сгенерированный текст нужно отформатировать\n " +
                    "то укажите количество слов в строке, иначе введите 0 -");
            int numberWordsPerLine = scanner.nextInt();
            myFaKer.writingWordsToTxtFile(totalNumberWordsInText, numberWordsPerLine);
        }
    }
}