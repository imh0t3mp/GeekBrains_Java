package name.imh0t3mp.course.geekbrains.controllers.api.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import name.imh0t3mp.course.geekbrains.repo.TaskRepository;
import name.imh0t3mp.course.geekbrains.services.TaskService;
import name.imh0t3mp.geekbrains.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Небольшой REST контроллер, реализующий минимальный набор методов для работы с задачами
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/1.0/task")
@Api(tags = "Task API")
public class TasksController {
    Logger logger = LoggerFactory.getLogger(TasksController.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

//    @GetMapping(value = "/",
//            produces = {"application/json"}
//    )
//    @ApiOperation("Получить список всех задач")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "success"),
//    })
//    @ResponseStatus(HttpStatus.OK)
//    public List<Task> getAll(@RequestParam(value = "owner", required = false) Integer owner_id,
//                             @RequestParam(value = "performer", required = false) Integer performer_id,
//                             @RequestParam(value = "status", required = false) String status,
//                             @RequestParam(value = "page", defaultValue = "1") Integer page) {
//        Specification<Task> spec = Specification.where(null);
//        if (null != status) {
//            try {
//                spec = spec.and(TaskSpecification.statusEq(TaskStatus.valueOf(status)));
//            } catch (IllegalArgumentException err) {
//                logger.warn(err.getMessage(), err);
//            }
//        }
//        if (null != owner_id) {
//            spec = spec.and(TaskSpecification.ownerEq(owner_id));
//        }
//        if (null != performer_id) {
//            spec = spec.and(TaskSpecification.performerEq(performer_id));
//        }
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
//        return taskRepository.findAll(spec, sort);
//    }

//    @PostMapping("/")
//    @ApiOperation("Добавить задачу в список")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "success"),
//            @ApiResponse(code = 400, message = "params error")
//    })
//    @ResponseStatus(HttpStatus.CREATED)
//    public Task create(@Valid @RequestBody Task task) {
//        task.setStatus(TaskStatus.CREATED);
//        return taskRepository.save(task);
//    }

    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    @ApiOperation("Получить данные о задаче по ID")
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

//    @PutMapping(value = "/{id}",
//            produces = {"application/json"})
//    @ApiOperation("Обновить данные для задачи")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "success"),
//            @ApiResponse(code = 404, message = "not found")
//    })
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Task> update(@PathVariable("id") Integer id,
//                                       @Valid @RequestBody Task task) {
//        return taskRepository.findById(id)
//                .map(taskData -> {
//                    taskData.setStatus(task.getStatus());
//                    taskData.setOwner(task.getOwner());
//                    taskData.setPerformer(task.getPerformer());
//                    taskData.setName(task.getName());
//                    taskData.setDescription(task.getDescription());
//                    Task updatedTask = taskRepository.save(taskData);
//                    return ResponseEntity.ok().body(updatedTask);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//
//    @DeleteMapping(value = "/{id}")
//    @ApiOperation("Удалить задачу по ID")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "success"),
//            @ApiResponse(code = 404, message = "not found")
//    })
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
//        return taskRepository.findById(id)
//                .map(task -> {
//                    taskRepository.deleteById(id);
//                    return ResponseEntity.ok().build();
//                }).orElse(ResponseEntity.notFound().build());
//    }
}
