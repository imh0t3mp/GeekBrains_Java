package name.imh0t3mp.course.geekbrains.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@JsonIgnoreProperties(value = {"createdAt", "id", "owner", "performer", "status"},
        allowGetters = true)
public class  TaskDTO {
    @Id
    @JsonIgnore
    private Integer id;

    @NotBlank(message = "Имя задачи обязательно")
    private String name;

    @NotNull(message = "Владелец обязателен")
    @JsonManagedReference
    @JsonIgnore
    private UserDTO owner;


    @NotNull(message = "Исполнитель обязателен")
    @JsonManagedReference
    @JsonIgnore
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
