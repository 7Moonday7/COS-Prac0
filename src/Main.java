import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        try {
            // Создаем объект InternetUsageReport
            InternetUsageReport report = new InternetUsageReport("Директория1", Instant.now(), 1.0);

            // Вводим данные с клавиатуры
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Введите общий объём памяти: ");
                double totalDiskSpace = scanner.nextDouble();
                report.setTotalDiskSpace(totalDiskSpace);

                System.out.print("Введите, сколько вы хотите добавить расширений: ");
                int extensionsCount = scanner.nextInt();
                List<String> personalFileExtensions = new ArrayList<>();
                System.out.print("Введите расширения файлов: ");
                for (int i = 0; i < extensionsCount; i++) {
                    String extension = scanner.next();
                    personalFileExtensions.add(extension);
                }
                report.setPersonalFileExtensions(personalFileExtensions);
            } // Scanner будет автоматически закрыт здесь
            System.err.println("\n");
            // Добавление файлов для тестирования с обработкой ошибок
            try {
                report.addFile("image.jpg", 1.5, convertInstantToLocalDateTime(Instant.now()));
                report.printFullInformation(); 
                System.err.println("\n"); // Вывод информации после добавления первого файла
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка при добавлении image.jpg: " + e.getMessage() + "\n");
            }

            try {
                report.addFile("document.txt", 0.8, convertInstantToLocalDateTime(Instant.now()));
                report.printFullInformation();
                System.err.println("\n");  // Вывод информации после добавления второго файла
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка при добавлении document.txt: " + e.getMessage() + "\n");
            }

            try {
                report.addFile("video.mp4", 2.0, convertInstantToLocalDateTime(Instant.now()));
                report.printFullInformation();
                System.err.println("\n");  // Вывод информации после добавления третьего файла
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка при добавлении video.mp4: " + e.getMessage()  + "\n");
            }
            // Сохраняем объект в файл
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("report.dat"))) {
                out.writeObject(report);
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Метод для преобразования Instant в LocalDateTime
    private static LocalDateTime convertInstantToLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
