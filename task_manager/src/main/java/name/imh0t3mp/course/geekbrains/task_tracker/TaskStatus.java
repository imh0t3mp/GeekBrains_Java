package name.imh0t3mp.course.geekbrains.task_tracker;

/**
 * Перечень возможных статусов задачи
 */
public enum TaskStatus {
    CREATED("Создана"),
    TODO("Сделать"),
    OPENED("Открыта"),
    IN_PROGRESS("В работе"),
    DECLINED("Отклонена"),
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
