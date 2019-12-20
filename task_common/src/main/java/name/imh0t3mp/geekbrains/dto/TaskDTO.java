package name.imh0t3mp.geekbrains.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class TaskDTO {

    private Integer id;

    @NotBlank(message = "Имя задачи обязательно")
    private String name;

    @JsonIgnore
    @NotNull(message = "Владелец обязателен")
    private Integer ownerId;

    @JsonIgnore
    @NotNull(message = "Исполнитель обязателен")
    private Integer performerId;

    private UserDTO owner;
    private UserDTO performer;

    @CreatedDate
    @NotNull
    private Calendar createdAt = Calendar.getInstance();

    private String description;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPerformerId() {
        return performerId;
    }

    public void setPerformerId(Integer performerId) {
        this.performerId = performerId;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public UserDTO getPerformer() {
        return performer;
    }

    public void setPerformer(UserDTO performer) {
        this.performer = performer;
    }
}
