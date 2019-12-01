package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private TasksService tasksService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("taskList", tasksService.getAllTasks());
        logger.debug("TASK LIST:{}", tasksService.getAllTasks());

        return "tasks";
    }
}
