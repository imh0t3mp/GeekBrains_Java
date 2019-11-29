package name.imh0t3mp.course.geekbrains.task_tracker.repository.impl;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.errors.RepositoryError;
import name.imh0t3mp.course.geekbrains.errors.RepositoryIsFull;
import name.imh0t3mp.course.geekbrains.errors.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.errors.TaskNotFound;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Реализация репозитория задач на массиве
 */
@Repository
public class TaskArrayRepositoryImpl implements TaskRepository {

    private Task[] taskList;
    private final static int CAPACITY = 1024;
    private int freePos;

    /**
     * Конструктор класса
     */
    public TaskArrayRepositoryImpl() {
        this.freePos = 0;
        this.taskList = new Task[CAPACITY];
    }

    /**
     * Добавить задачу в список
     *
     * @param task - задача для добавления в список
     */
    @Override
    public void addTask(Task task) throws RepositoryIsFull, TaskAlreadyExists {
        if (getUsedCapacity() == CAPACITY) {
            throw new RepositoryIsFull("Репозиторий заполнен. " +
                    " Максимально допустимое количество записей: " + CAPACITY);
        } else if (hasTask(task)) {
            throw new TaskAlreadyExists("Задача " + task.getShortDescription() + " уже есть в списке");
        } else {
            freePos = getFreePos();
            taskList[freePos] = task;
            freePos = getFreePos();
        }
    }

    /**
     * Удалить задачу из списка
     *
     * @param task - Задача для удаления
     */
    @Override
    public void deleteTask(Task task) throws TaskNotFound {
        int index = getIndex(task);
        if (-1 != index) {
            taskList[index] = null;
            freePos = getFreePos();
        } else {
            throw new TaskNotFound("Задача " + task.getShortDescription() + " не найдена в списке");
        }
    }

    /**
     * Удалить задачу по её ID
     *
     * @param taskId - ID задачи
     */
    @Override
    public void deleteTask(int taskId) throws TaskNotFound {
        int index = getIndex(taskId);
        if (-1 != index) {
            taskList[index] = null;
            freePos = getFreePos();
        } else {
            throw new TaskNotFound("Задача с ID:" + taskId + " не найдена в списке");
        }
    }

    /**
     * Удалить задачу по её имени
     *
     * @param taskName - название задачи
     */
    @Override
    public void deleteTask(String taskName) throws TaskNotFound {
        int index = getIndex(taskName);
        if (-1 != index) {
            taskList[index] = null;
            freePos = getFreePos();
        } else {
            throw new TaskNotFound("Задача с именем:" + taskName + " не найдена в списке");
        }
    }

    /**
     * Получить задапчи по её ID
     *
     * @param taskId - ID задачи
     * @return - найденная задача или null
     */
    @Override
    public Task getTask(int taskId) throws TaskNotFound {
        int index = getIndex(taskId);
        if (0 > index)
            throw new TaskNotFound("Задача с ID:" + taskId + " не найдена в списке");
        return taskList[index];
    }

    /**
     * Получить задачу по имени
     *
     * @param taskName - имя задачи
     * @return - найденная задача или null
     */
    @Override
    public Task getTask(String taskName) throws TaskNotFound {
        int index = getIndex(taskName);
        if (0 > index)
            throw new TaskNotFound("Задача с имемен:" + taskName + " не найдена в списке");
        return taskList[index];

    }

    /**
     * Изменить статус задачи по ID
     *
     * @param taskId
     * @param status
     * @throws TaskNotFound
     * @throws RepositoryError
     */
    @Override
    public void changeTaskStatus(int taskId, TaskStatus status) throws TaskNotFound {
        getTask(taskId).setStatus(status);
    }

    /**
     * Изменить статус задачи по её имени
     *
     * @param taskName
     * @param status
     * @throws TaskNotFound
     * @throws RepositoryError
     */
    @Override
    public void changeTaskStatus(String taskName, TaskStatus status) throws TaskNotFound {
        getTask(taskName).setStatus(status);
    }

    /**
     * Проверка, есть ли такая задача в спискке по ID задачи
     *
     * @param taskId - ID задачи
     * @return - найденная задача или null
     */
    @Override
    public boolean hasTask(int taskId) {
        return -1 != getIndex(taskId);
    }

    /**
     * Проверить, есть ли такая задача в списке по её имени
     *
     * @param taskName - имя задачи
     * @return - найденная задача или null
     */
    @Override
    public boolean hasTask(String taskName) {
        return -1 != getIndex(taskName);
    }

    /**
     * Проверить, есть ли такая задача в списке
     *
     * @param task - задача для поиска
     * @return - найденная задача или null
     */
    @Override
    public boolean hasTask(Task task) {
        return -1 != getIndex(task);
    }

    /**
     * Вернёт список задач в репозитории
     *
     * @return - массив со списком задач
     */
    @Override
    @Deprecated
    public Task[] getAllTasks() {
        return taskList;
    }

    /**
     * Поучить список задач
     *
     * @return - List задач
     */
    @Override
    public List<Task> getTasksList() {
        return Arrays.asList(taskList);
    }

    /**
     * Получить список задач, который имеют определённый статус
     *
     * @param status - статус задачи
     * @return - список найденных задач
     * @throws TaskNotFound - задачи не найдены
     */
    @Override
    public List<Task> getTasksByStatus(TaskStatus status) throws TaskNotFound {
        List<Task> tasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getStatus().equals(status))
                tasks.add(task);
        }
        if (0 == tasks.size())
            throw new TaskNotFound("Задач со статусом:" + status + " нет в списке");
        return tasks;
    }
    // ***************************************************************************************** //

    /**
     * Получить первую свободную ячейку в списке
     *
     * @return - индекс первой свободной ячейки
     */
    private int getFreePos() {
        for (int i = 0; i < CAPACITY; i++) {
            if (null == taskList[i]) return i;
        }
        return -1;
    }

    /**
     * Получить количество занятых ячеек в списке
     *
     * @return - количество занятых ячеек
     */
    private int getUsedCapacity() {
        int capacity = 0;
        for (Task t : taskList) {
            if (null != t) {
                capacity++;
            }
        }
        return capacity;
    }

    /**
     * Получить индекс записи задачи в списке
     *
     * @param task - искомая задача
     * @return - индекс ячейки где хранится искомая задача
     */
    private int getIndex(Task task) {
        for (int index = 0; index < CAPACITY; index++) {
            Task inList = taskList[index];
            if (null != inList && inList.equals(task)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Получить индекс задачи вс писке по её ID
     *
     * @param taskId - ID искомой задачи
     * @return - индекс ячейки где хранится задача
     */
    private int getIndex(int taskId) {
        for (int index = 0; index < CAPACITY; index++) {
            Task inList = taskList[index];
            if (null != inList && inList.getId() == taskId) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Получить индекс задачи в списке по имени задачи
     *
     * @param taskName - имя задачи
     * @return - индекс ячейки в которой хранится запись задаи
     */
    private int getIndex(String taskName) {
        for (int index = 0; index < CAPACITY; index++) {
            Task inList = taskList[index];
            if (null != inList && inList.getName().equals(taskName)) {
                return index;
            }
        }
        return -1;
    }
}
