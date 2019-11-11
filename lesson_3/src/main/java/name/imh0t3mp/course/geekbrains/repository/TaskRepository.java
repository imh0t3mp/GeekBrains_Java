package name.imh0t3mp.course.geekbrains.repository;

import name.imh0t3mp.course.geekbrains.Task;

/**
 * Унифицированный интерфейс для работы с репозиторием
 */
public interface TaskRepository {
    void addTask(Task task);

    void deleteTask(Task task);

    void deleteTask(int taskId);

    void deleteTask(String taskName);

    Task getTask(int taskId);

    Task getTask(String taskName);

    boolean hasTask(Task task);

    boolean hasTask(int taskId);

    boolean hasTask(String taskName);
}