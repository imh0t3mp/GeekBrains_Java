package name.imh0t3mp.course.geekbrains.task_tracker.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String username;

    @NotBlank
    @Column(name = "user_email")
    @Size(min = 6)
    @Email
    private String userEmail;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;
}