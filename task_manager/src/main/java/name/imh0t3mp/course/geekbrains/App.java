package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskHibernateRepoImpl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
        log.debug(">>>> START");
        testHibernateRepo();
        log.debug("<<<< STOP");
    }

    private static void testHibernateRepo() throws InterruptedException {
        log.debug("Трекер задач на в БД с Hibername");
        Session session = null;
        try (SessionFactory factory = new Configuration()
                .configure("config/hibernate_h2db.cfg.xml")
                .buildSessionFactory()) {
            TasksService service = new TasksService(new TaskHibernateRepoImpl(factory));
            System.out.println("TASK LIST:");
            System.out.println(service.getAllTasks());
            service.addTask(new Task("T1","TASK1","OWNER1","EXECUTOR1"));
            service.addTask(new Task("T2","TASK2","OWNER2","EXECUTOR2"));
            service.addTask(new Task("T3","TASK3","OWNER3","EXECUTOR3"));
            service.addTask(new Task("T4","TASK4","OWNER4","EXECUTOR4"));
            System.out.println("TASK LIST:");
            System.out.println(service.getAllTasks());
            System.out.println("GET TASK 'T1'");
            System.out.println(service.getTask("T1"));
            Task tt = new Task("TT1",
                    "ANOTHER_ONE_TASK1",
                    "SomeOwner",
                    "SomeExecutor");
            if (!service.hasTask(tt)) {
                System.out.println("ADD TASK: " + tt);
                service.addTask(tt);
            } else {
                System.out.println("Задача " + tt + " уже есть в базе");
                System.out.println("Удалить задачу " + tt);
                service.deleteTask(tt);
            }
            System.out.println("TASK LIST:");
            System.out.println(service.getAllTasks().toString());
            System.out.println("Изменить данные задачи T1");
            service.changeTaskStatus("T1", TaskStatus.CLOSED);
            System.out.println("TASK LIST:");
            System.out.println(service.getAllTasks());
            System.out.println("Задачи со статусом " + TaskStatus.CLOSED);
            System.out.println(service.searchByStatus(TaskStatus.CLOSED));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
