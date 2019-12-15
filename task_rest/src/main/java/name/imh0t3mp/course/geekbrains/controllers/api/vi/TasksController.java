package name.imh0t3mp.course.geekbrains.controllers.api.vi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/tasks")
@Api(tags = "Task API")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/",
            produces = {"application/json"}
    )
    @ApiOperation("Получить список всех задач")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        return taskRepository.findAll(sort);
    }

    @PostMapping("/")
    @ApiOperation("Добавить задачу в список")
    @ApiResponses({
            @ApiResponse(code = 201, message = "success"),
            @ApiResponse(code = 400, message = "params error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody Task task) {
        task.setStatus(TaskStatus.CREATED);
        return taskRepository.save(task);
    }

    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    @ApiOperation("Получить данные о задаче по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getById(@PathVariable("id") Integer id) {
        return taskRepository.findById(id)
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
    public ResponseEntity<Task> update(@PathVariable("id") Integer id,
                                       @Valid @RequestBody Task task) {
        return taskRepository.findById(id)
                .map(taskData -> {
                    taskData.setStatus(task.getStatus());
                    Task updatedTask = taskRepository.save(taskData);
                    return ResponseEntity.ok().body(updatedTask);
                }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation("Удалить задачу по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
