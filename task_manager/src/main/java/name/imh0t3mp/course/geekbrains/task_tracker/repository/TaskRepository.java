package name.imh0t3mp.course.geekbrains.task_tracker.repository;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.RepositoryError;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.RepositoryIsFull;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskNotFound;

import java.util.List;

/**
 * Унифицированный интерфейс для работы с репозиторием
 */
public interface TaskRepository {
    void addTask(Task task) throws RepositoryIsFull, TaskAlreadyExists, RepositoryError;

    void deleteTask(Task task) throws TaskNotFound, RepositoryError;

    void deleteTask(int taskId) throws TaskNotFound, RepositoryError;

    void deleteTask(String taskName) throws TaskNotFound, RepositoryError;

    Task getTask(int taskId) throws TaskNotFound, RepositoryError;

    Task getTask(String taskName) throws TaskNotFound, RepositoryError;

    boolean hasTask(Task task);

    boolean hasTask(int taskId);

    boolean hasTask(String taskName);

    Task[] getAllTasks();

    List<Task> getTasksList();

    List<Task> getTasksByStatus(TaskStatus status) throws TaskNotFound, RepositoryError;
}