# java-course-final-task

## Финальное задание:
##### У приложения должно быть несколько режимов работы:
1. Генерация CSV файла с таблицей. Пользователь должен выбрать кол-во строк и колонки -  email, First Name, Last Name, Phone, Date Of Birth, token (строка со случайными буквами в верхнем\\нижнем регистре и цифрами - пользователь должен выбрать длину токена), text  - (просто рандомный текст - пользователь должен выбрать длину текста в символах)
2. Генерация CSV файла со случайнымы email - Пользователь должен выбрать количество email'ов
3. Генерация txt файла с текстом произвольной длины - Пользователь должен выбрать длину текста в символах или словах

Пример кода с записью в CSV:

```java
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
```

Пример кода работы с датами:

```java
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class DateExample {
    public static void main(String[] args) {
        // Объявляем формат даты, который будем использовать для приведения дат "из" и "в" нужный вид
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        // Тут главное помнить:
        // 1. D это день года (1-365) а d это день месяца (1-31)
        // 2. M это месяц, а m это минута - не перепутайте =)

        // Пример строки, которая совпадает с нашим нужным форматом
        String courseStartString = "06 06 2023";
        // Пример невалидной строки даты
        String invalidString = "36 56 9999";
        // Устанавлиаем значение "по умолчанию" - пусть будет начало эпохи
        LocalDate courseStartDate, invalidStringDate =  courseStartDate = LocalDate.EPOCH;

        // Всё-таки он выбрасывает исключение, просто оно теперь unchecked
        try {
            courseStartDate = LocalDate.parse(courseStartString, dtf);
            invalidStringDate = LocalDate.parse(invalidString, dtf);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка парсинга даты!");
        }

        // Начало эпохи, если вам интересно - можете почитать про эпохокалипсис - https://en.wikipedia.org/wiki/Year_2038_problem
        System.out.println("Неправильная строка в итоге имеет вид: " + invalidStringDate);

        // Вытаскиваем количество 24-часовых дней между нашей датой и сегодня
        long daysBetween = ChronoUnit.DAYS.between(courseStartDate, LocalDate.now());
        System.out.println("Дней с начала курса Java прошло: " + daysBetween);

        // Добавляем любое целочисленное значение (long) к дате
        LocalDate newDate = courseStartDate.plusDays(7);

        System.out.println("Дата начала курса: " + courseStartDate);
        System.out.println("Дата начала второй недели курса: " + newDate);

        // Можем отнимать значения как в днях, так и в неделях, месяцах, годах...
        LocalDate oldDate = LocalDate.now().minusMonths(1);
        LocalDate veryOldDate = LocalDate.now().minusYears(1);

        // Формат даты по умолчанию берётся из настроек ОС
        System.out.println("Месяц назад было: " + oldDate);
        // Через вызов метода format класса LocalDate можем привести дату к нужному формату
        System.out.println("А год назад (в формате строки): " + veryOldDate.format(dtf));
    }
}
```
