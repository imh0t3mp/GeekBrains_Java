package name.imh0t3mp.course.geekbrains.task_tracker;

import name.imh0t3mp.course.geekbrains.task_tracker.errors.RepositoryIsFull;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskNotFound;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.TaskRepository;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskArrayRepositoryImpl;

/**
 * Унифицированный класс для работы с разными типами репозиториев
 */
public class TasksService {

    private TaskRepository repository;

    /**
     * Инициализация сервиса определённым типом репозитория
     */
    public TasksService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Дефолтный конструктор
     * <p>
     * По умолчанию будем считать, что данные храним в репозитории массива
     */
    TasksService() {
        this.repository = new TaskArrayRepositoryImpl();
    }

    /**
     * Добавить задачу в список
     *
     * @param task - задача для добавления
     */
    public void addTask(Task task) {
        try {
            this.repository.addTask(task);
        } catch (RepositoryIsFull | TaskAlreadyExists err) {
            System.err.println(err.toString());
        }

    }

    /**
     * Получить задачу по ID
     *
     * @param id - ID задачи
     * @return - найденная задача или NULL
     */
    public Task getTask(int id) {
        try {
            return this.repository.getTask(id);
        } catch (TaskNotFound err) {
            System.err.println(err.toString());
            return null;
        }
    }

    /**
     * Получить задачу по её имени
     *
     * @param taskName - имя задачи
     * @return - найдненная задача или NULL
     */
    public Task getTask(String taskName) {
        try {
            return this.repository.getTask(taskName);
        } catch (TaskNotFound err) {
            System.err.println(err.toString());
            return null;
        }
    }

    /**
     * Удалить задачу из списка
     *
     * @param task - объект задачи для удаления
     */
    public void deleteTask(Task task) {
        try {
            this.repository.deleteTask(task);
        } catch (TaskNotFound err) {
            System.err.println(err.toString());
        }
    }

    /**
     * Удалить задачу по её ID
     *
     * @param id - ID удаляемой задачи
     */
    public void deleteTask(int id) {
        try {
            this.repository.deleteTask(id);
        } catch (TaskNotFound err) {
            System.err.println(err.toString());
        }
    }

    /**
     * Удалить задачу по её имени
     *
     * @param taskName - имя задачи для удаления
     */
    public void deleteTask(String taskName) {
        try {
            this.repository.deleteTask(taskName);
        } catch (TaskNotFound err) {
            System.err.println(err.toString());
        }
    }

    /**
     * Преобразовать список задач в репозитории в строку.
     *
     * @return - строковое представление списка задач
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : repository.getAllTasks()) {
            sb.append(task).append("\n");
        }
        return sb.toString();
    }
}
