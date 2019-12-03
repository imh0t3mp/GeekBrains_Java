package name.imh0t3mp.course.geekbrains.task_tracker.entity;

import name.imh0t3mp.course.geekbrains.task_tracker.StatusConverter;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "task_repo")
public class Task implements Serializable {
    private static final long serialVersionUID = -31415926535L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Имя задачи обязательно")
    @Column(name = "task_name", unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Владелец обязателен")
    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @NotBlank(message = "Исполнитель обязателен")
    @Column(name = "executor_name", nullable = false)
    private String executorName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "task_status", nullable = false)
    @Convert(converter = StatusConverter.class)
    private TaskStatus taskStatus;

    /**
     * Дефолтный конструктор
     */
    public Task() {
        this.taskStatus = TaskStatus.CREATED;
    }

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

    /************************************** НАБОР GET-теров **************************************/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
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

    /*
     * Сеттеры ну ооочень нужны для spring-mvc
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
