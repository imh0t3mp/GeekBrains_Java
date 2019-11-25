package name.imh0t3mp.course.geekbrains;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
        log.debug(">>>> START");
        testSpringDatabase();
        log.debug("<<<< STOP");
    }

    private static void testSpringDatabase() {
//        try (AnnotationConfigApplicationContext context =
//                     new AnnotationConfigApplicationContext(TaskTrackerConfig.class)) {
//            Connection con = context.getBean("jdbcConnection", Connection.class);
        SessionFactory factory = new Configuration()
                .configure("config/hibernate_h2db.cfg.xml")
                .buildSessionFactory();
//            EntityManagerFactory factory = context.getBean("hibernateFactory",
//                    EntityManagerFactory.class);
        log.debug(factory);
//            log.info("CONTEXT:" + context);

//            TasksService service = context.getBean("tasksService", TasksService.class);
//            log.info("SERVICE:" + service);
//            service.setRepository(new TaskDatabaseRepoImpl(con));
//
//            service.addTask(new Task("T1", "Task1", "Owner1", "Executor1"));
//            service.addTask(new Task("T2", "Task2", "Owner1", "Executor2"));
//            service.addTask(new Task("T3", "Task3", "Owner2", "Executor1"));
//            service.addTask(new Task("T4", "Task4", "Owner2", "Executor2"));
//
//            log.info("ALL TASKS:");
//            log.info(service.getAllTasks());

//            log.info("CHANGE TASK STATUS");
//            service.changeTaskStatus("T1", TaskStatus.DECLINED);
//
//            log.info("ALL TASKS:");
//            log.info(service.getAllTasks());
//
//            log.info("ALL TASKS WITH STATUS:" + TaskStatus.DECLINED);
//            log.info(service.searchByStatus(TaskStatus.DECLINED));
//
//            log.info("DELETE TASK:" + service.getTask("T2"));
//            service.deleteTask("T2");
//
//            log.info("ALL TASKS:");
//            log.info(service.getAllTasks());
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
    }
}
