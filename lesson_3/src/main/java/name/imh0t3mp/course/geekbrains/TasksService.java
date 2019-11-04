package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.repository.TaskRepository;
import name.imh0t3mp.course.geekbrains.repository.impl.TaskArrayRepositoryImpl;

/**
 * Унифицированный класс для работы с разными типами репозиториев
 */
public class TasksService {

    private TaskRepository repository;

    /**
     * Инициализация сервиса определённым типом репозитория
     */
    TasksService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Дефолтный конструктор
     *
     * По умолчанию будем считать, что данные храним в репозитории массива
     */
    TasksService() {
        this.repository = new TaskArrayRepositoryImpl();
    }


    public void addTask(Task task) {
        this.repository.addTask(task);
    }

    public Task getTask(int id) {
        return this.repository.getTask(id);
    }

    public Task getTask(String taskName) {
        return this.repository.getTask(taskName);
    }

    public void deleteTask(Task task) {
        this.repository.deleteTask(task);
    }

    public void deleteTask(int id) {
        this.repository.deleteTask(id);
    }

    public void deleteTask(String taskName) {
        this.repository.deleteTask(taskName);
    }

    public String toString() {
        return this.repository.toString();
    }
}
