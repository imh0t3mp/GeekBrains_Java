/**
 * Перечень возможных статусов задачи
 */
public enum TaskStatus {
    CREATED("Создана"),
    TODO("Сделать"),
    OPENED("Открыта"),
    DECLINED("Отклонена"),
    IN_PROGRESS("В работе"),
    DONE("Сделано"),
    CLOSED("Закрыто");

    private final String description;

    TaskStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
