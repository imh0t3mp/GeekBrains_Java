package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.specs.TaskSpecification;
import name.imh0t3mp.course.geekbrains.task_tracker.services.TaskService;
import name.imh0t3mp.course.geekbrains.task_tracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public void setTaskService(TaskService taskService,
                               UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }


    @GetMapping({"", "/get_tasks"})
    public String index(
            @RequestParam(value = "owner", required = false) Integer owner_id,
            @RequestParam(value = "executor", required = false) Integer executor_id,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            Model model) {
        if (page < 1L) page = 1;
        Specification<Task> spec = Specification.where(null);
        if (null != status) {
            try {
                spec = spec.and(TaskSpecification.statusEq(TaskStatus.valueOf(status)));
            } catch (IllegalArgumentException err) {
                logger.warn(err.getMessage(), err);
            }

        }
        if (null != owner_id) {
            spec = spec.and(TaskSpecification.ownerEq(owner_id));
        }
        if (null != executor_id) {
            spec = spec.and(TaskSpecification.ownerEq(executor_id));
        }
        Page<Task> taskList = taskService.getTasks(spec,
                PageRequest.of(page - 1, 100500, Sort.Direction.ASC, "id"));
        model.addAttribute("taskList", taskList);
        model.addAttribute("stat", status);
        model.addAttribute("owner_id", owner_id);
        model.addAttribute("executor_id", executor_id);
        model.addAttribute("users", userService.getAll());
        logger.debug("TASK LIST:{}", taskList);

        return "index";
    }
}
