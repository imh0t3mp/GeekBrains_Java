package name.imh0t3mp.course.geekbrains.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

public class TaskCreateUpdateDTO {


    @NotBlank(message = "Имя задачи обязательно")
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

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskStatus status = TaskStatus.CREATED;
}
