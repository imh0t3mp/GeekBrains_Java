package name.imh0t3mp.course.geekbrains;

import org.apache.log4j.Logger;
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
        try (SessionFactory factory = new Configuration()
                .configure("config/hibernate.cfg.xml")
                .buildSessionFactory()) {
            log.debug(factory);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
