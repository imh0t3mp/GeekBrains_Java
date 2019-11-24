package name.imh0t3mp.course.geekbrains.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"name.imh0t3mp.course.geekbrains.task_tracker"})
public class TaskTrackerConfig {
}
