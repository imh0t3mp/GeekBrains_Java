package name.imh0t3mp.course.geekbrains.task_tracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
@Table(name = "task_repo")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Task implements Serializable {
    private static final long serialVersionUID = -31415926535L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Имя задачи обязательно")
    @Column(name = "task_name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @NotNull(message = "Владелец обязателен")
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonManagedReference
    private User owner;


    @ManyToOne
    @NotNull(message = "Исполнитель обязателен")
    @JoinColumn(name = "performer_id", nullable = false)
    @JsonManagedReference
    private User performer;

    @CreatedDate
    @NotNull
    @Column(name = "date_add", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt = Calendar.getInstance();

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.CREATED;


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
                ", performer='" + performer + '\'' +
                ", description='" + description + '\'' +
                ", taskStatus=" + status +
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
        if (!performer.equals(task.performer)) return false;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + performer.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
