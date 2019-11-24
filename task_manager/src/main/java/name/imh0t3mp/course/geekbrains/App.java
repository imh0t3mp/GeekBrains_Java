package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.config.TaskRepository;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
        log.debug(">>>> START");
        testSpringDatabase();
        log.debug("<<<< STOP");
    }

    private static void testSpringDatabase() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(TaskRepository.class)) {
            log.info(context);

        }
    }
}
