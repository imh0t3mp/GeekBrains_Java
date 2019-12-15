package name.imh0t3mp.course.geekbrains.task_tracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task_repo")
public class Task implements Serializable {
    private static final long serialVersionUID = -31415926535L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Имя задачи обязательно")
    @Column(name = "task_name", unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @NotBlank(message = "Владелец обязателен")
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonManagedReference
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @NotBlank(message = "Исполнитель обязателен")
    @JoinColumn(name = "executor_id", nullable = false)
    @JsonManagedReference
    private User executor;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
//    @Convert(converter = StatusConverter.class)
    private TaskStatus taskStatus = TaskStatus.CREATED;

    public void setOwner(User owner) {
        System.err.println("OWNER: ");
        System.err.println(owner);
        this.owner = owner;
    }

    public void setExecutor(User executor) {
        System.err.println("EXECUTOR");
        System.err.println(executor);
        this.executor = executor;
    }

    //    /**
//     * Конструктор класса
//     *
//     * @param name        - название задачи
//     * @param description - описание задачи
//     * @param owner       - владелец задачи
//     * @param executor    - исполнитель задачи
//     */
//    public Task(String name, String description, User owner, User executor) {
//        this.name = name;
//        this.owner = owner;
//        this.executor = executor;
//        this.description = description;
//        this.taskStatus = TaskStatus.CREATED;
//    }


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
                ", owner='" + owner + '\'' +
                ", executor='" + executor + '\'' +
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
        if (!owner.equals(task.owner)) return false;
        if (!executor.equals(task.executor)) return false;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + executor.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
