package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskListAndStreamRepositoryImpl;

import java.util.Comparator;

public class App {

    public static void main(String[] args) {
        testRepository();
    }

    private static void testRepository() {
        System.out.println("Трекер задач на массиве с исключениями и обрабокой оныхъ");
        TasksService tasksServiceTracker = new TasksService(new TaskListAndStreamRepositoryImpl());

        Task t1 = new Task("T1", "Task1", "Owner1", "Exec1");
        tasksServiceTracker.addTask(t1);
        tasksServiceTracker.addTask(new Task("T2", "Task2", "Owner2", "Exec2"));
        tasksServiceTracker.addTask(new Task("T3", "Task3", "Owner3", "Exec3"));
        tasksServiceTracker.addTask(new Task("T4", "Task4", "Owner4", "Exec4"));
        tasksServiceTracker.addTask(new Task("T5", "Task5", "Owner2", "Exec1"));
        tasksServiceTracker.addTask(new Task("T6", "Task6", "Owner3", "Exec2"));
        System.out.println("TASK LIST: \n" + tasksServiceTracker);
        System.out.println("T1 EQUALS T1 IN LIST: " + t1.equals(tasksServiceTracker.getTask(1)));
        System.out.println("T1 EQUALS T2 IN LIST: " + t1.equals(tasksServiceTracker.getTask(2)));
        Task tt1 = tasksServiceTracker.getTask(2);
        System.out.println("FOUND TASK BY ID=2 : " + tt1);
        tasksServiceTracker.getTask("T2").setStatus(TaskStatus.TODO);
        tasksServiceTracker.getTask("T3").setStatus(TaskStatus.OPENED);
        tasksServiceTracker.getTask("T4").setStatus(TaskStatus.IN_PROGRESS);
        tasksServiceTracker.getTask("T5").setStatus(TaskStatus.DONE);
        System.out.println("FULL TASK LIST: \n" + tasksServiceTracker);
        System.out.println("Все задачи со статусом " + TaskStatus.DECLINED);
        System.out.println(tasksServiceTracker.searchByStatus(TaskStatus.DECLINED));
        System.out.println("Все задачи со статусом " + TaskStatus.DONE);
        System.out.println(tasksServiceTracker.searchByStatus(TaskStatus.DONE));
        System.out.println("Вывести сортрованный список задач");
        System.out.println("Сортровка по имени:");
        System.out.println(tasksServiceTracker.sortTasksBy(Comparator.comparing(Task::getName)));
        System.out.println("Сортировка по имени в обратном порядке:");
        System.out.println(tasksServiceTracker.sortTasksBy((x, y) -> -x.getName().compareTo(y.getName())));
        System.out.println("Сортировка по статусу:");
        System.out.println(tasksServiceTracker.sortTasksBy(Comparator.comparing(Task::getStatus)));
        System.out.println("Подсчёт задач:");
        System.out.println("Подсчёт задач со статусом \"" + TaskStatus.TODO + "\"");
        System.out.println(tasksServiceTracker.getTaskCount(TaskStatus.TODO));
        System.out.println("Подсчёт задач со статусом \"" + TaskStatus.OPENED + "\"");
        System.out.println(tasksServiceTracker.getTaskCount(TaskStatus.OPENED));
        System.out.println("Подсчёт задач исполнителя \"Exec1\"");
        System.out.println(tasksServiceTracker.getTaskCount(t -> t.getExecutorName().equals("Exec1")));

        System.out.println("DELETE TASK ID=2");
        tasksServiceTracker.deleteTask(tt1);
        System.out.println("SEARCH TASK ID=2");
        System.out.println("FOUND TASK ID=2 : " + tasksServiceTracker.getTask(2));
        System.out.println("FOUND TASK ID=3 : " + tasksServiceTracker.getTask(3));
        System.out.println("TASK LIST: \n" + tasksServiceTracker);
        System.out.println("ADD NEW TASK");
        tasksServiceTracker.addTask(new Task("T5", "Owner5", "Executor5", "Descr5"));
        System.out.println("TASK LIST: \n" + tasksServiceTracker);
    }

}
