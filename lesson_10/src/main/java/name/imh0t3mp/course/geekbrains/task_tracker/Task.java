package name.imh0t3mp.course.geekbrains.task_tracker;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Task implements Externalizable {

    private int id;
    private String name;
    private String ownerName;
    private String executorName;
    private String description;
    private TaskStatus taskStatus;

    /**
     * Конструктор класса
     *
     * @param name         - название задачи
     * @param description  - описание задачи
     * @param ownerName    - имя владельца задачи
     * @param executorName - имя исполнителя задачи
     */
    public Task(String name, String description, String ownerName, String executorName) {
        this.name = name;
        this.ownerName = ownerName;
        this.executorName = executorName;
        this.description = description;
        this.taskStatus = TaskStatus.CREATED;
        this.id = TaskId.getNextId();
    }

    /**
     * Конструктор класса
     *
     * @param id           - id задачи
     * @param name         - наименование задачи
     * @param ownerName    - владелец задачи
     * @param executorName - исполнитель задачи
     * @param description  - описание задачи
     * @param taskStatus   - статус задачи±
     */
    public Task(int id, String name, String ownerName, String executorName, String description, TaskStatus taskStatus) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.executorName = executorName;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Task() {
    }

    /************************************** НАБОР GET-теров **************************************/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getOwnerName() {
        return ownerName;
    }


    public String getExecutorName() {
        return executorName;
    }


    public String getDescription() {
        return description;
    }

    public String getStatusDescription() {
        return taskStatus.toString();
    }

    public TaskStatus getStatus() {
        return taskStatus;
    }

    /**
     * Установить статус задачи
     *
     * @param taskStatus - дескриптор статуса задачи
     */
    public void setStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Объект в строку
     *
     * @return = строковое представление задачи
     */
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", executorName='" + executorName + '\'' +
                ", description='" + description + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }

    /**
     * Получить краткое строковое представление задачи.
     * Полезно, например, для выводе текста об ошибке
     *
     * @return - краткое описание
     */
    public String getShortDescription() {
        return "ID:" + this.id + " " + this.name + " (" + this.description + ")\n" +
                "владелец:" + this.ownerName + "\n" +
                "исполнитель:" + this.ownerName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!name.equals(task.name)) return false;
        if (!ownerName.equals(task.ownerName)) return false;
        if (!executorName.equals(task.executorName)) return false;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + ownerName.hashCode();
        result = 31 * result + executorName.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    /**
     * Сериализация объекта
     *
     * @param out - поток вывода данных
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeInt(count);
        out.writeObject(name);
        out.writeObject(ownerName);
        out.writeObject(executorName);
        out.writeObject(description);
        out.writeObject(taskStatus);
    }

    /**
     * Десериализация объекта
     *
     * @param in - входной поток данных
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        count = in.readInt();
        name = (String) in.readObject();
        ownerName = (String) in.readObject();
        executorName = (String) in.readObject();
        description = (String) in.readObject();
        taskStatus = (TaskStatus) in.readObject();
    }
}
