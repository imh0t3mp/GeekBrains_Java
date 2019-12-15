package name.imh0t3mp.course.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    private static Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        log.debug(">>>>> APP STARTED");
        SpringApplication.run(WebApplication.class, args);
    }

}
