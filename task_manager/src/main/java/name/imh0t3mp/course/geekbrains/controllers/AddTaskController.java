package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/task")
public class AddTaskController {
    @Autowired
    private TasksService tasksService;

    @ModelAttribute
    public Task formBackingObject() {
        return new Task();
    }

    @GetMapping("/")
    public String userForm(Locale locale, Model model) {
        return "add_task";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute("task") @Valid Task task,
                          BindingResult result,
                          Model model) {

        tasksService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam(name = "id", required = true) int taskId,
                             Model model) {
        tasksService.deleteTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/change")
    public String changeTaskStatus(
            @RequestParam(name = "id", required = true) int taskId,
            @RequestParam(name = "status", required = true) String taskStatus,
            Model model) {
        tasksService.changeTaskStatus(taskId, TaskStatus.valueOf(taskStatus));
        return "redirect:/";
    }
}
