package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
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
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory()) {
//            REad data
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println(session.get(Task.class, 1574184062));
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
