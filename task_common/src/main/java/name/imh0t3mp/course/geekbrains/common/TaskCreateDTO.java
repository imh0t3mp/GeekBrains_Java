package name.imh0t3mp.course.geekbrains.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
public class TaskCreateDTO {
    @NotBlank(message = "Имя задачи обязательно")
    @Column(name = "task_name", unique = true, nullable = false)
    private String name;

    @NotNull(message = "Владелец обязателен")
    @JsonManagedReference
    private UserDTO owner;


    @NotNull(message = "Исполнитель обязателен")
    @JsonManagedReference
    private UserDTO performer;

    @CreatedDate
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Calendar createdAt = Calendar.getInstance();

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.CREATED;
}
