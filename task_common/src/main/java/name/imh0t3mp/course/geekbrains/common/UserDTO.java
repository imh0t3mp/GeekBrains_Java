package name.imh0t3mp.course.geekbrains.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class UserDTO {
    @Id
    @JsonIgnore
    private Integer id;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9_-]*$")
    @Size(min = 1, max = 50)
    private String username;

    @Email
    @NotBlank
    @Size(min = 6, max = 100)
    private String email;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;
}
