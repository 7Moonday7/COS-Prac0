import java.io.Serializable;

public abstract class SummaryData implements Serializable {

    private static final long serialVersionUID = 1L;

    // Вывод информации
    public abstract void printSummary();

    // Валидация сотрудников
    public abstract boolean validateResponsibility();
}