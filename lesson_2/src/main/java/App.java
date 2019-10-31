
public class App {

    public static void main(String[] args) {
        TaskCollectionTracker tracker = new TaskCollectionTracker();

        Task t1 = new Task("T1", "Owner1", "Executor1", "Descr1");
        Task t2 = new Task("T2", "Owner2", "Executor2", "Descr2");
        Task t3 = new Task("T3", "Owner3", "Executor3", "Descr3");
        Task t4 = new Task("T4", "Owner4", "Executor4", "Descr4");
        tracker.addTask(t1);
        tracker.addTask(t2);
        tracker.addTask(t3);
        tracker.addTask(t4);
        System.out.println("TASK LIST: \n" + tracker.printTaskList());
        Task t = tracker.getTask(3);
        System.out.println("FOUND TASK: " + t);

        System.out.println("CHANGE TASK STATUS");
        t.setStatus(TaskStatus.TODO);
        System.out.println("TASK LIST: \n" + tracker.printTaskList());
        System.out.println("DELETE TASK");
        tracker.deleteTask(t);
        System.out.println("SEARCH TASK");
        System.out.println("FOUND TASK: " + tracker.getTask(3));
        System.out.println("TASK LIST: \n" + tracker.printTaskList());
        tracker.addTask(new Task("T5", "Owner5", "Executor5", "Descr5"));
        System.out.println("TASK LIST: \n" + tracker.printTaskList());
    }
}
