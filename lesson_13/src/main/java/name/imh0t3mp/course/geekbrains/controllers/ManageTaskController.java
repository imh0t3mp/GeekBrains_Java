package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class ManageTaskController {
    private static Logger logger = LoggerFactory.getLogger(ManageTaskController.class);
    @Autowired
    private TasksService tasksService;


    @GetMapping("/add")
    public String taskForm(@ModelAttribute("task") Task task, Model model) {
        model.addAttribute("task", task);
        return "add_task";
    }

    @PostMapping("/add")
    public String addTask(@Valid Task task, BindingResult result, Model model) {
        logger.debug("FORM_TASK:{}", task);
        if (result.hasErrors()) {
            return "add_task";
        }
        tasksService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") int taskId, Model model) {
        tasksService.deleteTask(tasksService.getTask(taskId));
        return "redirect:/";
    }

    //
    @GetMapping("/change/{id}")
    public String changeStatus(
            @PathVariable(value = "id", required = true) int taskId,
            Model model) {
        Task task = tasksService.getTask(taskId);
        model.addAttribute("task", task);
        logger.debug("TASK:{}", task);
        return "change_status";
    }

    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable(value = "id", required = true) int taskId,
                               @RequestParam(value = "taskStatus", required = true) String taskStatus,
                               Model model) {
        tasksService.changeTaskStatus(taskId, TaskStatus.valueOf(taskStatus));
        return "redirect:/";
    }
}
