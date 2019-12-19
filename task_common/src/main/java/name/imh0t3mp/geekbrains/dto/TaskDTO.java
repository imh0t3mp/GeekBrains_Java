package name.imh0t3mp.geekbrains.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@JsonIgnoreProperties(value = {"id,"}, allowGetters = true)
public class TaskDTO {

    @JsonIgnore
    private Integer id;

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
    @JsonIgnore
    private Calendar createdAt = Calendar.getInstance();


    private String description;

    private String status;
}
