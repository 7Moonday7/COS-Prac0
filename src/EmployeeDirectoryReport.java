import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectoryReport extends SummaryData {

    private static final long serialVersionUID = 1L;

    private String directoryName;
    private double totalDiskSpace = 5.0;  // в ГБ
    private double personalUsage = 0.0;   // в ГБ
    private int personalFilesCount = 0;
    private List<String> personalFileExtensions = new ArrayList<>();
    private LocalDateTime lastModificationDate = LocalDateTime.now();

    // Конструкторы
    public EmployeeDirectoryReport(String dirName) {
        if (dirName == null || dirName.isEmpty()) {
            throw new IllegalArgumentException("Неверное имя директории");
        }
        this.directoryName = dirName;
        this.personalFileExtensions.add(".jpg");
        this.personalFileExtensions.add(".jpeg");
        this.personalFileExtensions.add(".png");
        this.personalFileExtensions.add(".mp4");
    }

    public EmployeeDirectoryReport(String dirName, LocalDateTime modificationDate) {
        if (dirName == null || dirName.isEmpty()) {
            throw new IllegalArgumentException("Неверное имя директории");
        }

        // Инициализация полей
        this.directoryName = dirName;
        this.lastModificationDate = modificationDate;

        // Инициализация списка с расширениями файлов
        this.personalFileExtensions = new ArrayList<>();
        personalFileExtensions.add(".jpg");
        personalFileExtensions.add(".jpeg");
        personalFileExtensions.add(".png");
        personalFileExtensions.add(".mp4");
    }

    public EmployeeDirectoryReport() {}

    // Селекторы для всех полей
    public String getDirectoryName() {
        return directoryName;
    }

    public double getTotalDiskSpace() {
        return totalDiskSpace;
    }

    public double getPersonalUsage() {
        return personalUsage;
    }

    public int getPersonalFilesCount() {
        return personalFilesCount;
    }

    public List<String> getPersonalFileExtensions() {
        return new ArrayList<>(personalFileExtensions);
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    // Модификаторы
    public void setTotalDiskSpace(double totalDiskSpace) {
        this.totalDiskSpace = totalDiskSpace;
    }

    public void setPersonalUsage(double personalUsage) {
        this.personalUsage = personalUsage;
    }

    public void setPersonalFileExtensions(List<String> personalFileExtensions) {
        this.personalFileExtensions = new ArrayList<>(personalFileExtensions);
    }

    // Метод вывода полной информации на консоль
    public void printFullInformation() {
        System.out.println("Имя директории: " + getDirectoryName());
        System.out.println("Объем занятого дискового пространства: " + getTotalDiskSpace() + " Гб");
        System.out.println("Объем занятого дискового пространства на личные нужды: " + getPersonalUsage() + " Гб");
        System.out.println("Количество личных файлов: " + getPersonalFilesCount());
        System.out.println("Дата последнего изменения файлов: " + getLastModificationDate());
    }

    // Метод добавления нового файла в статистику
    public void addFile(String fileName, double fileSize, LocalDateTime fileModificationDate) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Неверное имя файла");
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (!personalFileExtensions.contains(fileExtension)) {
            throw new IllegalArgumentException("Неверное расширение файла");
        }

        if (fileModificationDate.isBefore(lastModificationDate)) {
            throw new IllegalArgumentException("Неверно указано время");
        }

        personalFilesCount++;
        personalUsage += fileSize;

        if (personalUsage > totalDiskSpace) {
            throw new IllegalArgumentException("Невозможно, чтобы вес файлов на персональные нужды превышел общий объём памяти");
        }

        lastModificationDate = fileModificationDate;
    }

    // Реализация метода для валидации сотрудников
    @Override
    public boolean validateResponsibility() {
        return personalUsage == 0.0;
    }

    // Реализация метода интерфейса
    @Override
    public void printSummary() {
        printFullInformation();
    }
}
