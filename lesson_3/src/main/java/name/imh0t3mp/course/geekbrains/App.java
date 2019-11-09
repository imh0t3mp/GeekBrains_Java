package name.imh0t3mp.course.geekbrains;

public class App {

    public static void main(String[] args) {

        System.out.println("На массиве");
        TasksService tasksServiceTracker = new TasksService();

        Task t1 = new Task("T1", "Owner1", "Executor1", "Descr1");
//        Task t12 =
//        Task t13 =
//        Task t14 =
        tasksServiceTracker.addTask(t1);
        tasksServiceTracker.addTask(new Task("T2", "Owner2", "Executor2", "Descr2"));
        tasksServiceTracker.addTask(new Task("T3", "Owner3", "Executor3", "Descr3"));
        tasksServiceTracker.addTask(new Task("T4", "Owner4", "Executor4", "Descr4"));
        System.out.println("TASK LIST: \n" + tasksServiceTracker);
        System.out.println("T1 EQUALS T1 IN LIST: " + t1.equals(tasksServiceTracker.getTask(1)));
        System.out.println("T1 EQUALS T2 IN LIST: " + t1.equals(tasksServiceTracker.getTask(2)));
        Task tt1 = tasksServiceTracker.getTask(2);
        System.out.println("FOUND TASK BY ID=2 : " + tt1);
        System.out.println("CHANGE TASK ID=2 STATUS");
        tt1.setStatus(TaskStatus.DECLINED);
        System.out.println("TASK LIST: \n" + tasksServiceTracker);
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
