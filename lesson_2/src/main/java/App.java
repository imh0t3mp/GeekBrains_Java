
public class App {

    public static void main(String[] args) {

//        System.out.println("С коллекциями");
//        TasksCollectionTracker collectTracker = new TasksCollectionTracker();
//
//        Task t01 = new Task("T01", "Owner1", "Executor1", "Descr1");
//        Task t02 = new Task("T02", "Owner2", "Executor2", "Descr2");
//        Task t03 = new Task("T03", "Owner3", "Executor3", "Descr3");
//        Task t04 = new Task("T04", "Owner4", "Executor4", "Descr4");
//        collectTracker.addTask(t01);
//        collectTracker.addTask(t02);
//        collectTracker.addTask(t03);
//        collectTracker.addTask(t04);
//        System.out.println("TASK LIST: \n" + collectTracker.printTaskList());
//        System.out.println("T1 EQUALS T1 IN LIST: " + t01.equals(collectTracker.getTask(1)));
//        System.out.println("T1 EQUALS T2 IN LIST: " + t01.equals(collectTracker.getTask(2)));
//        Task tt0 = collectTracker.getTask(3);
//        System.out.println("FOUND TASK: " + tt0);
//
//        System.out.println("CHANGE TASK STATUS");
//        tt0.setStatus(TaskStatus.TODO);
//        System.out.println("TASK LIST: \n" + collectTracker.printTaskList());
//        System.out.println("DELETE TASK");
//        collectTracker.deleteTask(tt0);
//        System.out.println("SEARCH TASK");
//        System.out.println("FOUND TASK: " + collectTracker.getTask(3));
//        System.out.println("TASK LIST: \n" + collectTracker.printTaskList());
//        collectTracker.addTask(new Task("T5", "Owner5", "Executor5", "Descr5"));
//        System.out.println("TASK LIST: \n" + collectTracker.printTaskList());

        System.out.println("На массиве");
        TasksArrayTracker arrTracker = new TasksArrayTracker();

        Task t11 = new Task("T11", "Owner1", "Executor1", "Descr1");
        Task t12 = new Task("T12", "Owner2", "Executor2", "Descr2");
        Task t13 = new Task("T13", "Owner3", "Executor3", "Descr3");
        Task t14 = new Task("T14", "Owner4", "Executor4", "Descr4");
        arrTracker.addTask(t11);
        arrTracker.addTask(t12);
        arrTracker.addTask(t13);
        arrTracker.addTask(t14);
        System.out.println("TASK LIST: \n" + arrTracker.printTaskList());
        System.out.println("T1 EQUALS T1 IN LIST: " + t11.equals(arrTracker.getTask(1)));
        System.out.println("T1 EQUALS T2 IN LIST: " + t11.equals(arrTracker.getTask(2)));
        Task tt1 = arrTracker.getTask(2);
        System.out.println("FOUND TASK BY ID=2 : " + tt1);//
        System.out.println("CHANGE TASK ID=2 STATUS");
        tt1.setStatus(TaskStatus.DECLINED);
        System.out.println("TASK LIST: \n" + arrTracker.printTaskList());
        System.out.println("DELETE TASK ID=2");
        arrTracker.deleteTask(tt1);
        System.out.println("SEARCH TASK ID=2");
        System.out.println("FOUND TASK: " + arrTracker.getTask(3));
        System.out.println("TASK LIST: \n" + arrTracker.printTaskList());
        System.out.println("ADD NEW TASK");
        arrTracker.addTask(new Task("T5", "Owner5", "Executor5", "Descr5"));
        System.out.println("TASK LIST: \n" + arrTracker.printTaskList());
    }
}
