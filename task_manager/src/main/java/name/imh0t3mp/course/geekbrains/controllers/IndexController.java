package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private TasksService tasksService;

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("tasksList", tasksService.getAllTasks());

        return "tasks";
    }
}
