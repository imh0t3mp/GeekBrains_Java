package name.imh0t3mp.course.geekbrains;

<<<<<<< HEAD:task_manager/src/main/java/name/imh0t3mp/course/geekbrains/App.java
import name.imh0t3mp.course.geekbrains.config.TaskTrackerConfig;
=======
import name.imh0t3mp.course.geekbrains.task_tracker.Task;
>>>>>>> lesson-9:lesson_9/src/main/java/name/imh0t3mp/course/geekbrains/App.java
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskHibernateRepoImpl;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
        log.debug(">>>> START");
        testSpringDatabase();
        log.debug("<<<< STOP");
    }

<<<<<<< HEAD:task_manager/src/main/java/name/imh0t3mp/course/geekbrains/App.java
    private static void testSpringDatabase() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(TaskTrackerConfig.class)) {
//            Connection con = context.getBean("jdbcConnection", Connection.class);

            SessionFactory factory = context.getBean("hibernateFactory",
                    SessionFactory.class);

            TasksService service = context.getBean("tasksService", TasksService.class);
            log.info("SERVICE:" + service);
//            service.setRepository(new TaskDatabaseRepoImpl(con));
            service.setRepository(new TaskHibernateRepoImpl(factory));
//
//            service.addTask(new Task("T1", "Task1", "Owner1", "Executor1"));
//            service.addTask(new Task("T2", "Task2", "Owner1", "Executor2"));
//            service.addTask(new Task("T3", "Task3", "Owner2", "Executor1"));
//            service.addTask(new Task("T4", "Task4", "Owner2", "Executor2"));
//
            log.info("ALL TASKS:");
            log.info(service.getAllTasks());
//
            log.info("CHANGE TASK STATUS");
            service.changeTaskStatus("T6", TaskStatus.DECLINED);

            log.info("ALL TASKS:");
            log.info(service.getAllTasks());
=======
    private static void testRepository() {
        System.out.println("Трекер задач на массиве с исключениями и обрабокой оныхъ");
        TasksService tasksServiceTracker =
                new TasksService(new TaskListInFileRepoImpl("data", "task_list.dat"));
        Task t1 = new Task("T1", "Task1", "Owner1", "Exec1");
        tasksServiceTracker.addTask(t1);
        tasksServiceTracker.addTask(new Task("T2", "Task2", "Owner2", "Exec2"));
        tasksServiceTracker.addTask(new Task("T3", "Task3", "Owner3", "Exec3"));
        tasksServiceTracker.addTask(new Task("T4", "Task4", "Owner4", "Exec4"));
        tasksServiceTracker.addTask(new Task("T5", "Task5", "Owner2", "Exec1"));
        tasksServiceTracker.addTask(new Task("T6", "Task6", "Owner3", "Exec2"));
        System.out.println("FIRST REPO: \n" + tasksServiceTracker);
        System.out.println("Записать список в файл:");
        tasksServiceTracker.saveTasks();
        System.out.println("Содаём новый репозиторий");
        TasksService tasksServiceTracker1 =
                new TasksService(new TaskListInFileRepoImpl("data", "task_list.dat"));
        System.out.println("Перед чтением, список задач в репозитории:");
        System.out.println(tasksServiceTracker1);
        System.out.println("Читаем данные из файла в новом репозитории:");
        tasksServiceTracker1.loadTasks();
        System.out.println("Полученные данные:");
        System.out.println("NEW REPO:");
        System.out.println(tasksServiceTracker1);
        System.out.println("Сравнить два репозитория:");
        System.out.println(tasksServiceTracker.repositoryEquals(tasksServiceTracker1.getRepository()));
        System.out.println("Меняем статус однй из задач");
        tasksServiceTracker.getTask("T1").setStatus(TaskStatus.DECLINED);
        System.out.println("FIRST REPO: \n" + tasksServiceTracker);
        System.out.println("NEW REPO: \n" + tasksServiceTracker1);
        System.out.println("Сравнить два репозитория:");
        System.out.println(tasksServiceTracker.repositoryEquals(tasksServiceTracker1.getRepository()));
    }
>>>>>>> lesson-9:lesson_9/src/main/java/name/imh0t3mp/course/geekbrains/App.java

            log.info("ALL TASKS WITH STATUS:" + TaskStatus.DECLINED);
            log.info(service.searchByStatus(TaskStatus.DECLINED));

            log.info("DELETE TASK:" + service.getTask("T5"));
            service.deleteTask("T5");

            log.info("ALL TASKS:");
            log.info(service.getAllTasks());
        }
    }
}
