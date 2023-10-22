import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DataEngine {

    public static void makeDataCSV (List<String> selectedColumns, int numberOfRow, CreateOfFile generator){
        try (FileWriter writer = new FileWriter("out/Data.csv")){
            for(String column: selectedColumns){
                if(column.contains(":")){
                    writer.append(column.split(":")[0]);
                }else {
                    writer.append(column);
                }
                writer.append(",");
            }
            writer.append("\n");
            for(int i = 0; i<numberOfRow; i ++){
                for (String column: selectedColumns) {
                    if(column.contains("First name")){
                        writer.append(generator.generateFirstName());
                    } else if (column.contains("Last name")) {
                        writer.append(generator.generateLastName());
                    }else if (column.contains("DayOfBirth")) {
                        writer.append(generator.generateDateOfBirth());
                    }else if (column.contains("E-mail")) {
                        writer.append(generator.generateEmail());
                    }else if (column.contains("PhoneNumber")) {
                        writer.append(generator.generateNumberPhone());
                    }else if (column.contains("Token")) {
                       int tokenLength = Integer.parseInt(column.split(":")[1]);
                       writer.append(generator.generateToken(tokenLength));
                    }else if (column.contains("Text")) {
                       int textLength = Integer.parseInt(column.split(":")[1]);
                       writer.append("\"")
                             .append(generator.generateText(textLength, false))
                             .append("\"");
                    }
                    writer.append(",");
                }
                writer.append("\n");
            }
            System.out.println("CSV файл 'Data' успешно создан и находится в папке out");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void makeEmailsCSV (int numberOfMail,CreateOfFile generator){
        try (FileWriter writer = new FileWriter("out/Email.csv")) {
            for (int i = 0; i < numberOfMail; i++) {
                writer.append(generator.generateEmail());
                writer.append("\n");
            }
            System.out.println("CSV файл 'Email' успешно создан и находится в папке out");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void makeText(int length, boolean param, CreateOfFile generator){
        try (FileWriter writer = new FileWriter("out/Text.txt")) {
           writer.append(generator.generateText(length, param));
           System.out.println("TXT файл 'Text' успешно создан и находится в папке out");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "First name");
        map.put(2, "Last name");
        map.put(3, "DayOfBirth");
        map.put(4, "E-mail");
        map.put(5, "PhoneNumber");
        map.put(6, "Token");
        map.put(7, "Text");
        CreateOfFile generator = new CreateOfFile();

        System.out.println("Выберете один из режимов : 1-генерация таблицы, 2- генерация E-mail, 3- генерация текста");
        int mode = scanner.nextInt();
        if (mode == 2) {
            System.out.println("Выберете количество строк/e-mail");
            int numberOfMail = scanner.nextInt();
            DataEngine.makeEmailsCSV(numberOfMail, generator);

        } else if (mode == 3) {
            System.out.println("Генерировать в словах или символах? (слово/символ):");
            String x = scanner.next();
            System.out.println("Выберете длину теста в символах или словах");
            int abc = scanner.nextInt();
            if (x.equals("слово")){
                DataEngine.makeText(abc, true, generator);
                System.out.println(x);
            } else if (x.equals("символ")) {
                DataEngine.makeText(abc, false, generator);
                System.out.println(x);
            }

        } else if (mode == 1) {
            List<String> selectedColumns = new ArrayList<>();
            System.out.println("Введите количество строк таблицы :");
            int numberOfRow = scanner.nextInt();
            System.out.println("Укажите номер необходимых полей из перечисленных: \n" +
                    "1-First name,\n2-Last name,\n3-DayOfBirth,\n4-E-mail,\n" +
                    "5-PhoneNumber,\n6-token, \n7-text.\nПо завершении ввода введите 50");
            int typeOfParametr = 0;
            while (typeOfParametr != 50) {
                typeOfParametr = scanner.nextInt();
                if (typeOfParametr == 50) break;
                if (typeOfParametr == 6 || typeOfParametr == 7) {
                    System.out.println("Введите количество символов в " + map.get(typeOfParametr));
                    int length = scanner.nextInt();
                    selectedColumns.add(map.get(typeOfParametr) + ":" + length);
                } else selectedColumns.add(map.get(typeOfParametr));
            }
            System.out.println(selectedColumns);
            DataEngine.makeDataCSV(selectedColumns,numberOfRow,generator);
        }


    }
}
