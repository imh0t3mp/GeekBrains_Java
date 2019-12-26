package name.imh0t3mp.course.geekbrains.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = {"name.imh0t3mp.course.geekbrains.task_tracker"})
public class TaskTrackerConfig {

 @Bean(name = "hibernateFactory")
    public SessionFactory hibernateFactory() throws NoClassDefFoundError, HibernateException {
        return new org.hibernate.cfg.Configuration()
                .configure("config/hibernate_sqlite.cfg.xml")
                .buildSessionFactory();
    }
}
