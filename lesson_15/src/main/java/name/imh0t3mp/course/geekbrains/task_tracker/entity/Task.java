package name.imh0t3mp.course.geekbrains.task_tracker.entity;

import lombok.Data;
import name.imh0t3mp.course.geekbrains.task_tracker.StatusConverter;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
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
}
