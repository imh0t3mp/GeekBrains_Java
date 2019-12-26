package name.imh0t3mp.course.geekbrains.controllers.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import name.imh0t3mp.course.geekbrains.config.Constants;
import name.imh0t3mp.course.geekbrains.entity.User;
import name.imh0t3mp.course.geekbrains.exception.EmailAlreadyUsedException;
import name.imh0t3mp.course.geekbrains.exception.UsernameAlreadyUsedException;
import name.imh0t3mp.course.geekbrains.repo.UserRepository;
import name.imh0t3mp.course.geekbrains.services.UserService;
import name.imh0t3mp.geekbrains.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/users")
@Api(tags = "User API")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/",
            produces = {"application/json"}
    )
    @ApiOperation("Получить список всех пользователей системы")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/ids/{id}",
            produces = {"application/json"})
    @ApiOperation("Получить данные пользователя по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Integer id) {
        return userService.getById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/logins/{login:" + Constants.LOGIN_REGEX + "}",
            produces = {"application/json"})
    @ApiOperation("Получить данные пользователя по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        return userService.getByUsername(login)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/roles",
            produces = {"application/json"})
    @ApiOperation("Получить список ролей")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public List<String> getAuthorities() {
        return userService.getRoles();
    }


    @PutMapping("")
//    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = userRepository.findOneByUsernameIgnoreCase(userDTO.getUsername().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new UsernameAlreadyUsedException();
        }
        return userService.updateUser(userDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
