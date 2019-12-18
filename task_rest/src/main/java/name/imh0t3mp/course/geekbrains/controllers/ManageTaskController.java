package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.common.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.services.TaskService;
import name.imh0t3mp.course.geekbrains.task_tracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class ManageTaskController {
    private static Logger logger = LoggerFactory.getLogger(ManageTaskController.class);

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public void setTaskService(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String vewTask(@PathVariable("id") int taskId, Model model) {
        model.addAttribute("task", taskService.getTask(taskId));
        return "view_task";
    }

    @GetMapping("/add")
    public String taskForm(@ModelAttribute("task") Task task, Model model) {
        model.addAttribute("task", task);
        model.addAttribute("users", userService.getAll());
        return "add_task";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute("task") Task task, BindingResult result) {
        logger.debug("FORM_TASK:{}", task);
        logger.debug("RESULT:{}", result);
        System.err.println(task);
        System.err.println(result);
//        System.out.println(task);
        if (result.hasErrors()) {
            logger.warn(String.valueOf(result.getAllErrors()));
//            return "add_task";
        }
        taskService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") int taskId) {
        taskService.deleteTask(taskService.getTask(taskId));
        return "redirect:/";
    }

    //
    @GetMapping("/change/{id}")
    public String changeStatus(
            @PathVariable(value = "id", required = true) int taskId,
            Model model) {
        Task task = taskService.getTask(taskId);
        model.addAttribute("task", task);
        logger.debug("TASK:{}", task);
        return "change_status";
    }

    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable(value = "id", required = true) int taskId,
                               @RequestParam(value = "taskStatus", required = true) String taskStatus,
                               Model model) {
        taskService.changeTaskStatus(taskId, TaskStatus.valueOf(taskStatus));
        return "redirect:/";
    }
}
