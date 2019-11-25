package name.imh0t3mp.course.geekbrains.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = {"name.imh0t3mp.course.geekbrains.task_tracker"})
public class TaskTrackerConfig {

    @Bean(name = "jdbcConnection")
    public Connection jdbcConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:main.db");
        return con;
    }

//    @Bean(name = "hibernateFactory")
//    public SessionFactory hibernateFactory() throws NoClassDefFoundError, HibernateException {
//        try {
//            return new org.hibernate.cfg.Configuration()
//                    .configure("config/hibernate_h2db.cfg.xml")
//                    .buildSessionFactory();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    @Bean(name = "hibernateFactory")
//    public EntityManagerFactory entityManagerFactory() {
//        return new org.hibernate.cfg.Configuration()
//                .configure("config/hibernate_sqlite.cfg.xml")
//                .buildSessionFactory();
//    }
}
