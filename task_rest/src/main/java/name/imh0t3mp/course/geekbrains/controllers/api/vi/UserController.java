package name.imh0t3mp.course.geekbrains.controllers.api.vi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.User;
import name.imh0t3mp.course.geekbrains.task_tracker.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/users")
@Api(tags = "User API")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/",
            produces = {"application/json"}
    )
    @ApiOperation("Получить список всех пользователей системы")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        return userRepository.findAll(sort);
    }

    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    @ApiOperation("Получить данные пользователя по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Удалить пользователя по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
