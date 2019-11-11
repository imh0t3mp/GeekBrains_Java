package name.imh0t3mp.course.geekbrains.repository;

import name.imh0t3mp.course.geekbrains.Task;
import name.imh0t3mp.course.geekbrains.errors.RepositoryIsFull;
import name.imh0t3mp.course.geekbrains.errors.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.errors.TaskNotFound;

/**
 * Унифицированный интерфейс для работы с репозиторием
 */
public interface TaskRepository {
    void addTask(Task task) throws RepositoryIsFull, TaskAlreadyExists;

    void deleteTask(Task task) throws TaskNotFound;

    void deleteTask(int taskId) throws TaskNotFound;

    void deleteTask(String taskName) throws TaskNotFound;

    Task getTask(int taskId) throws TaskNotFound;

    Task getTask(String taskName) throws TaskNotFound;

    boolean hasTask(Task task);

    boolean hasTask(int taskId);

    boolean hasTask(String taskName);

    Task[] getAllTasks();
}