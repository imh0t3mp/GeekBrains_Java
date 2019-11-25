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
    //    @Bean
//    public EntityManagerFactory entityManagerFactory(){
//        return new org.hibernate.cfg.Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//    }
    @Bean(name = "jdbcConnection")
    public Connection jdbcConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:main.db");
        return con;
    }
}
