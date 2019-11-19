package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskDatabaseRepoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws InterruptedException {

        testRepository();
    }

    private static void testRepository() throws InterruptedException {
        System.out.println("Трекер задач на в БД с исключениями и обрабокой оныхъ");

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:main.db")) {
                TasksService tasksServiceTracker =
                        new TasksService(new TaskDatabaseRepoImpl(connection));
//                Task t1 = new Task("T1", "Task1", "Owner1", "Exec1");
//                tasksServiceTracker.addTask(t1);
//                tasksServiceTracker.addTask(new Task("T2", "Task2", "Owner2", "Exec2"));
//                tasksServiceTracker.addTask(new Task("T3", "Task3", "Owner3", "Exec3"));
//                tasksServiceTracker.addTask(new Task("T4", "Task4", "Owner4", "Exec4"));
//                tasksServiceTracker.addTask(new Task("T5", "Task5", "Owner2", "Exec1"));
//                tasksServiceTracker.addTask(new Task("T6", "Task6", "Owner3", "Exec2"));
                System.out.println("TASK LIST: \n" + tasksServiceTracker);
                System.out.println("Получить задачу T2 из базы");
                System.out.println(tasksServiceTracker.getTask("T2"));
                System.out.println("Изменить статус задачи T2");
                tasksServiceTracker.changeTaskStatus("T2", TaskStatus.DECLINED);
                System.out.println("TASK LIST: \n" + tasksServiceTracker);
                System.out.println("Удалить задачу T2");
                tasksServiceTracker.deleteTask("T2");
                System.out.println("TASK LIST: \n" + tasksServiceTracker);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
