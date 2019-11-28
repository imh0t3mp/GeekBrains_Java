package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class AddTaskController {
    private static Logger logger = LoggerFactory.getLogger(AddTaskController.class);
    @Autowired
    private TasksService tasksService;

    @ModelAttribute
    public Task formBackingObject() {
        return new Task();
    }

    @GetMapping("/")
    public String taskForm(Task task) {
        return "add_task";
    }

    @GetMapping("/add")
    public String addTask(@ModelAttribute("task") @Valid Task task,
//                          BindingResult result,
                          Model model) {
        logger.info("FORM_TASK:{}", task);
//        logger.info("RESULT:{}", result.getModel().toString());
//        tasksService.addTask(task);
        return "add_task";
    }
//
//    @GetMapping("/delete")
//    public String deleteTask(@RequestParam(name = "id", required = true) int taskId,
//                             Model model) {
//        tasksService.deleteTask(taskId);
//        return "redirect:/";
//    }
//
//    @GetMapping("/change")
//    public String changeTaskStatus(
//            @RequestParam(name = "id", required = true) int taskId,
//            @RequestParam(name = "status", required = true) String taskStatus,
//            Model model) {
//        tasksService.changeTaskStatus(taskId, TaskStatus.valueOf(taskStatus));
//        return "redirect:/";
//    }
}
