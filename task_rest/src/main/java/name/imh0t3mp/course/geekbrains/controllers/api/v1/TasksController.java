package name.imh0t3mp.course.geekbrains.controllers.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import name.imh0t3mp.course.geekbrains.entity.Task;
import name.imh0t3mp.course.geekbrains.entity.TaskStatus;
import name.imh0t3mp.course.geekbrains.entity.User;
import name.imh0t3mp.course.geekbrains.entity.specs.TaskSpecification;
import name.imh0t3mp.course.geekbrains.exception.UserNotFoundException;
import name.imh0t3mp.course.geekbrains.services.TaskService;
import name.imh0t3mp.course.geekbrains.services.UserService;
import name.imh0t3mp.geekbrains.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Небольшой REST контроллер, реализующий минимальный набор методов для работы с задачами
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/task")
@Api(tags = "Task API")
@Slf4j
public class TasksController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/",
            produces = {"application/json"}
    )
    @ApiOperation(value = "Получить список всех задач",
            response = TaskDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDTO> getAll(@RequestParam(value = "owner", required = false) Integer owner_id,
                                @RequestParam(value = "performer", required = false) Integer performer_id,
                                @RequestParam(value = "status", required = false) String status,
                                @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Specification<Task> spec = Specification.where(null);
        if (null != status) {
            try {
                spec = spec.and(TaskSpecification.statusEq(TaskStatus.valueOf(status)));
            } catch (IllegalArgumentException err) {
                log.warn(err.getMessage(), err);
            }
        }
        if (null != owner_id) {
            spec = spec.and(TaskSpecification.ownerEq(owner_id));
        }
        if (null != performer_id) {
            spec = spec.and(TaskSpecification.performerEq(performer_id));
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return taskService.findAll(spec, sort);
    }

    @PostMapping("/")
    @ApiOperation("Добавить задачу в список")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success"),
            @ApiResponse(code = 400, message = "params error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskDTO taskDTO) {
        assertUserExist(taskDTO.getOwnerId());
        assertUserExist(taskDTO.getPerformerId());
        return taskService.addTask(taskDTO);
    }

    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    @ApiOperation(value = "Получить данные о задаче по ID",
            response = TaskDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDTO> getById(@PathVariable("id") Integer id) {
        return taskService.getById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}",
            produces = {"application/json"})
    @ApiOperation("Обновить данные для задачи")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDTO> update(@PathVariable("id") Integer id,
                                          @Valid @RequestBody TaskDTO taskDTO) {
        assertUserExist(taskDTO.getOwnerId());
        assertUserExist(taskDTO.getPerformerId());
        return taskService.
                updateTask(id, taskDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation("Удалить задачу по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return taskService.removeTask(id).orElse(ResponseEntity.notFound().build());
    }

    private void assertUserExist(Integer userId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            throw new UserNotFoundException("id", userId);
        }
    }
}
