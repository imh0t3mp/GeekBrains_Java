public class TasksArrayTracker {
    private Task[] taskList;
    private final static int CAPACITY = 10;
    private int freePos;

    /**
     * Дефолтный конструктор
     */
    public TasksArrayTracker() {
        this.freePos = 0;
        this.taskList = new Task[CAPACITY];
    }

    /**
     * ДОбавить задачу в список
     *
     * @param task
     */
    public void addTask(Task task) {
        if (this.getUsedCapacity() == CAPACITY) {
            System.err.println("ERROR: Список задач заполнен");
        } else if (-1 != this.getIndex(task)) {
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
     * Получить первую свободную ячейку в списке
     *
     * @return
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
     * Удалить задачу из списка
     *
     * @param task - Задача для удаления
     */
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
     * @param taskName
     */
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
     * @return
     */
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
     * @return
     */
    public Task getTask(String taskName) {
        int index = this.getIndex(taskName);
        if (-1 != index) {
            return taskList[index];
        }
        System.err.println("ERROR: Задача с имемен:" + taskName + " не найдена в списке");
        return null;
    }


    /**
     * Получить индекс записи задачи в списке
     *
     * @param task
     * @return
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
     * Проверка, есть ли такая задача в спискке по ID задачи
     *
     * @param taskId
     * @return
     */
    public boolean hasTask(int taskId) {
        return -1 != this.getIndex(taskId);
    }

    /**
     * Проверить, есть ли такая задача в списке по её имени
     *
     * @param taskName
     * @return
     */
    public boolean hasTask(String taskName) {
        return -1 != this.getIndex(taskName);
    }

    /**
     * Проверить, есть ли такая задача в списке
     *
     * @param task
     * @return
     */
    public boolean hasTask(Task task) {
        return -1 != this.getIndex(task);
    }


    /**
     * Получить индекс задачи вс писке по её ID
     *
     * @param taskId
     * @return
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
     * @param taskName
     * @return
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
