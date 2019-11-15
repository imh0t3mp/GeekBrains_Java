package name.imh0t3mp.course.geekbrains.repository.impl;

import name.imh0t3mp.course.geekbrains.Task;
import name.imh0t3mp.course.geekbrains.repository.TaskRepository;

/**
 * Реализация репозитория задач на массиве
 */
public class TaskArrayRepositoryImpl implements TaskRepository {

    private Task[] taskList;
    private final static int CAPACITY = 10;
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
    public void addTask(Task task) {
        if (this.getUsedCapacity() == CAPACITY) {
            System.err.println("ERROR: Список задач заполнен");
        } else if (this.hasTask(task)) {
            System.err.println("ERROR: Задача " + task.getShortDescription() + " уже есть в списке");
        } else {
            freePos = this.getFreePos();
            if (-1 == freePos) {
                System.err.println("ERR: Ошибка вставки. Нет свободных ячеек");
            }
            taskList[freePos] = task;
            freePos = this.getFreePos();
        }
    }

    /**
     * Удалить задачу из списка
     *
     * @param task - Задача для удаления
     */
    @Override
    public void deleteTask(Task task) {
        int index = this.getIndex(task);
        if (-1 != index) {
            taskList[index] = null;
            freePos = this.getFreePos();
        } else {
            System.err.println("ERROR: Задача " + task.getShortDescription() + " не найдена в списке");
        }
    }

    /**
     * Удалить задачу по её ID
     *
     * @param taskId - ID задачи
     */
    @Override
    public void deleteTask(int taskId) {
        int index = this.getIndex(taskId);
        if (-1 != index) {
            taskList[index] = null;
            freePos = this.getFreePos();
        } else {
            System.err.println("ERROR: Задача с ID:" + taskId + " не найдена в списке");
        }
    }

    /**
     * Удалить задачу по её имени
     *
     * @param taskName - название задачи
     */
    @Override
    public void deleteTask(String taskName) {
        int index = this.getIndex(taskName);
        if (-1 != index) {
            taskList[index] = null;
            freePos = this.getFreePos();
        } else {
            System.err.println("ERROR: Задача с именем:" + taskName + " не найдена в списке");
        }
    }

    /**
     * Получить задапчи по её ID
     *
     * @param taskId - ID задачи
     * @return - найденная задача или null
     */
    @Override
    public Task getTask(int taskId) {
        int index = this.getIndex(taskId);
        if (-1 != index) {
            return taskList[index];
        }
        System.err.println("ERROR: Задача с ID:" + taskId + " не найдена в списке");
        return null;
    }

    /**
     * Получить задачу по имени
     *
     * @param taskName - имя задачи
     * @return - найденная задача или null
     */
    @Override
    public Task getTask(String taskName) {
        int index = this.getIndex(taskName);
        if (-1 != index) {
            return taskList[index];
        }
        System.err.println("ERROR: Задача с имемен:" + taskName + " не найдена в списке");
        return null;
    }


    /**
     * Проверка, есть ли такая задача в спискке по ID задачи
     *
     * @param taskId - ID задачи
     * @return - найденная задача или null
     */
    @Override
    public boolean hasTask(int taskId) {
        return -1 != this.getIndex(taskId);
    }

    /**
     * Проверить, есть ли такая задача в списке по её имени
     *
     * @param taskName - имя задачи
     * @return - найденная задача или null
     */
    @Override
    public boolean hasTask(String taskName) {
        return -1 != this.getIndex(taskName);
    }

    /**
     * Проверить, есть ли такая задача в списке
     *
     * @param task - задача для поиска
     * @return - найденная задача или null
     */
    @Override
    public boolean hasTask(Task task) {
        return -1 != this.getIndex(task);
    }

    /**
     * Вывести список задач в строку
     *
     * @return - строка со списком задач
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        В дейчствительности, NPE здесь не получим, в конструкторе массив уже
//        прединициализирован
        for (Task task : taskList) {
            if (null != task)
                sb.append(task).append("\n");
        }
        return sb.toString();
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
