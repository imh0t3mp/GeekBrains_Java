package name.imh0t3mp.geekbrains.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class UserDTO {

    @JsonIgnore
    private Integer id;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9_-]*$")
    @Size(min = 1, max = 50)
    private String username;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    /*******************************************************************************************/
    public UserDTO() {
    }

    public UserDTO(Integer id,
                   @NotBlank @Pattern(regexp = "^[a-z0-9_-]*$") @Size(min = 1, max = 50) String username,
                   @NotBlank @Email @Size(max = 100) String email,
                   @Size(max = 50) String firstName,
                   @Size(max = 50) String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    /*******************************************************************************************/
    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /********************************************************************************************/
    public void setId(Integer id) {
        this.id = id;
    }
}
