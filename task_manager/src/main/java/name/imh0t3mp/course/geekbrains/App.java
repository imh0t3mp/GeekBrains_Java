package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskListInFileRepoImpl;

public class App {

    public static void main(String[] args) {
        testRepository();
    }

    private static void testRepository() {
        System.out.println("Трекер задач на массиве с исключениями и обрабокой оныхъ");
        TasksService tasksServiceTracker =
                new TasksService(new TaskListInFileRepoImpl("data", "task_list.dat"));
        Task t1 = new Task("T1", "Task1", "Owner1", "Exec1");
        tasksServiceTracker.addTask(t1);
        tasksServiceTracker.addTask(new Task("T2", "Task2", "Owner2", "Exec2"));
        tasksServiceTracker.addTask(new Task("T3", "Task3", "Owner3", "Exec3"));
        tasksServiceTracker.addTask(new Task("T4", "Task4", "Owner4", "Exec4"));
        tasksServiceTracker.addTask(new Task("T5", "Task5", "Owner2", "Exec1"));
        tasksServiceTracker.addTask(new Task("T6", "Task6", "Owner3", "Exec2"));
        System.out.println("FIRST REPO: \n" + tasksServiceTracker);
        System.out.println("Записать список в файл:");
        tasksServiceTracker.saveTasks();
        System.out.println("Содаём новый репозиторий");
        TasksService tasksServiceTracker1 =
                new TasksService(new TaskListInFileRepoImpl("data", "task_list.dat"));
        System.out.println("Перед чтением, список задач в репозитории:");
        System.out.println(tasksServiceTracker1);
        System.out.println("Читаем данные из файла в новом репозитории:");
        tasksServiceTracker1.loadTasks();
        System.out.println("Полученные данные:");
        System.out.println("NEW REPO:");
        System.out.println(tasksServiceTracker1);
        System.out.println("Сравнить два репозитория:");
        System.out.println(tasksServiceTracker.repositoryEquals(tasksServiceTracker1.getRepository()));
        System.out.println("Меняем статус однй из задач");
        tasksServiceTracker.getTask("T1").setStatus(TaskStatus.DECLINED);
        System.out.println("FIRST REPO: \n" + tasksServiceTracker);
        System.out.println("NEW REPO: \n" + tasksServiceTracker1);
        System.out.println("Сравнить два репозитория:");
        System.out.println(tasksServiceTracker.repositoryEquals(tasksServiceTracker1.getRepository()));
    }

}
