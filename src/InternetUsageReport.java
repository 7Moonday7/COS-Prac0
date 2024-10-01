import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class InternetUsageReport extends EmployeeDirectoryReport {
    private double internetTrafficUsage = 0.0;

    // Конструкторы
    public InternetUsageReport(String dirName, double internetTrafficUsage) {
        super(dirName);
        this.internetTrafficUsage = internetTrafficUsage;
    }

    public InternetUsageReport(String dirName, Instant modificationDate, double internetTrafficUsage) {
        super(dirName, LocalDateTime.ofInstant(modificationDate, ZoneId.systemDefault())); // Преобразование Instant в LocalDateTime
        this.internetTrafficUsage = internetTrafficUsage;
    }

    // Селектор и модификатор для нового поля
    public double getInternetTrafficUsage() {
        return internetTrafficUsage;
    }

    public double setInternetTrafficUsage(double internetTrafficUsage) {
        if (internetTrafficUsage < 0) {
            throw new IllegalArgumentException("Интернет-трафик не может быть отрицательным");
        }
        this.internetTrafficUsage = internetTrafficUsage;
        return this.internetTrafficUsage;
    }

    // Methods for the new field
    public void addInternetTrafficUsage(double usage) {
        if (usage < 0) {
            throw new IllegalArgumentException("Интернет-трафик не может быть отрицательным");
        }
        internetTrafficUsage += usage;
    }

    public void resetInternetTrafficUsage() {
        internetTrafficUsage = 0.0;
    }

    // Переопределить метод validateResponsibility
    @Override
    public boolean validateResponsibility() {
        return (getPersonalUsage() == 0.0 && internetTrafficUsage == 0.0);
    }

    // Переопределить метод printFullInformation
    @Override
    public void printFullInformation() {
        super.printFullInformation(); // Вызовите реализацию базового класса
        System.out.println("Использованно интернет-трафика: " + getInternetTrafficUsage() + " Гб");
    }

    // Переопределить метод printSummary
    @Override
    public void printSummary() {
        printFullInformation(); // Использую метод из базового класса
    }
}
