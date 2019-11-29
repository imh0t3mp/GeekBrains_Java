import java.util.ArrayList;
import java.util.List;

public class TasksCollectionTracker {
    private List<Task> taskList;
    private final static int CAPACITY = 10;

    /**
     * Дефолтный конструктор
     */
    public TasksCollectionTracker() {
        taskList = new ArrayList<>();
    }

    /**
     * ДОбавить задачу в список
     *
     * @param task
     */
    public void addTask(Task task) {
        if (taskList.size() == CAPACITY) {
            System.err.println("ERROR: Список задач заполнен");
        } else if (taskList.contains(task)) {
            System.err.printf("ERROR: Задача " + task.getShortDescription() + " уже есть в списке");
        } else {
            taskList.add(task);
        }
    }

    /**
     * Удалить задачу из списка
     *
     * @param task - Задача для удаления
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Проверить, есть ли в списке задача с таким ID
     *
     * @param id - ID задачи
     * @return
     */
    public boolean hasTask(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверить, есть ли в списке задача с таким ID
     *
     * @param taskName - имя задачи
     * @return
     */
    public boolean hasTask(String taskName) {
        for (Task task : taskList) {
            if (task.getName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Получить задачу по ID
     *
     * @param id - ID Задачи
     * @return
     */
    public Task getTask(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Получить задачу по её имени
     * Если в списке есть более одной задачи с таким именем, тогда вернёт первую в списке
     *
     * @param taskName - имя задачи
     * @return
     */
    public Task getTask(String taskName) {
        for (Task task : taskList) {
            if (task.getName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    /**
     * Вывести список задач в строку
     *
     * @return - строка со списком задач
     */
    public String printTaskList() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task).append("\n");
        }
        return sb.toString();

    }
}
