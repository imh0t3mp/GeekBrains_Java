package name.imh0t3mp.course.geekbrains.controllers;

import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping({"", "/get_tasks"})
    public String index(
            @RequestParam(value = "owner", required = false) String owner,
            @RequestParam(value = "executor", required = false) String executor,
            @RequestParam(value = "status", required = false) String status,
            Model model) {
        List<Task> taskList;
        TaskStatus taskStatus = null;
        if (!owner.isEmpty() ||
                executor.isEmpty() ||
                null != status) {
            if (null != status) {
                try {
                    taskStatus = TaskStatus.valueOf(status);
                } catch (IllegalArgumentException err) {
                    logger.warn(err.getMessage(), err);
                }
            }
//            taskList =
        } else {
//            taskList = tasksService.getAllTasks();
        }
//        model.addAttribute("taskList", taskList);
//        logger.debug("TASK LIST:{}", taskList);

        return "index";
    }
}
