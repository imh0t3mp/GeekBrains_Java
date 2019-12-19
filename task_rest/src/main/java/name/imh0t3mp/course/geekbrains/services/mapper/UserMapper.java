package name.imh0t3mp.course.geekbrains.services.mapper;

import name.imh0t3mp.course.geekbrains.entity.Role;
import name.imh0t3mp.course.geekbrains.entity.User;
import name.imh0t3mp.geekbrains.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Конвертор User <-> UserDTO
 */
@Service
public class UserMapper {

    public List<UserDTO> toDto(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()));
    }

    public List<User> toEntity(List<UserDTO> userDTOs) {
        return userDTOs.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setUsername(userDTO.getUsername());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            Set<Role> roles = this.rolesFromString(userDTO.getRoles());
            user.setRoles(roles);

            return user;
        }
    }

    private Set<Role> rolesFromString(Set<String> rolesString) {
        Set<Role> roles = new HashSet<>();

        if (rolesString != null) {
            roles = rolesString.stream().map(string -> {
                Role role = new Role();
                role.setName(string);
                return role;
            }).collect(Collectors.toSet());
        }

        return roles;
    }
}
