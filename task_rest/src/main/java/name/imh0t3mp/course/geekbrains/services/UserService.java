package name.imh0t3mp.course.geekbrains.services;

import name.imh0t3mp.course.geekbrains.entity.Role;
import name.imh0t3mp.course.geekbrains.entity.User;
import name.imh0t3mp.course.geekbrains.repo.RolesRepository;
import name.imh0t3mp.course.geekbrains.repo.UserRepository;
import name.imh0t3mp.course.geekbrains.services.mapper.UserMapper;
import name.imh0t3mp.geekbrains.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    UserMapper userMapper;

    public Optional<UserDTO> getById(Integer id) {
        return this.userRepository.findById(id).map(userMapper::toDto);
    }

    public User getUserById(Integer id) {
        return this.userRepository.findById(id).get();
    }

    public Optional<UserDTO> getByUsername(String username) {
        return this.userRepository.getUserByUsername(username).map(userMapper::toDto);
    }

    public User getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username).get();
    }


    public List<UserDTO> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        return userMapper.toDto(userRepository.findAll(sort));
    }

    public List<String> getRoles() {
        return rolesRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
    }

    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                .findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    user.setUsername(userDTO.getUsername().toLowerCase());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setEmail(userDTO.getEmail().toLowerCase());
                    return user;
                })
                .map(userMapper::toDto);
    }
}