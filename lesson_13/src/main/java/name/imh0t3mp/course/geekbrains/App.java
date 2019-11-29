package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.config.TaskTrackerConfig;
import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskHibernateRepoImpl;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        testSpringDatabase();
    }

    private static void testSpringDatabase() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(TaskTrackerConfig.class)) {
            SessionFactory factory = context.getBean("hibernateFactory",
                    SessionFactory.class);

            TasksService service = context.getBean("tasksService", TasksService.class);
            log.info("SERVICE:" + service);
            service.setRepository(new TaskHibernateRepoImpl(factory));

//            service.addTask(new Task("T1", "Task1", "Owner1", "Executor1"));
//            service.addTask(new Task("T2", "Task2", "Owner1", "Executor2"));
//            service.addTask(new Task("T3", "Task3", "Owner2", "Executor1"));
//            service.addTask(new Task("T4", "Task4", "Owner2", "Executor2"));
//            service.addTask(new Task("T5", "Task5", "Owner1", "Executor1"));
//            service.addTask(new Task("T6", "Task6", "Owner2", "Executor2"));
//
            log.info("ALL TASKS:");
            log.info("{}", service.getAllTasks());
//
            log.info("CHANGE TASK STATUS");
            service.changeTaskStatus("T6", TaskStatus.DECLINED);

            log.info("ALL TASKS:");
            log.info("{}", service.getAllTasks());

            log.info("ALL TASKS WITH STATUS:" + TaskStatus.DECLINED);
            log.info("{}", service.searchByStatus(TaskStatus.DECLINED));

            log.info("DELETE TASK:" + service.getTask("T5"));
            service.deleteTask("T5");

            log.info("ALL TASKS:");
            log.info("{}", service.getAllTasks());
        }
    }
}
