package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.errors.MyArrayDataException;
import name.imh0t3mp.course.geekbrains.errors.MyArraySizeException;

public class App {

    public static void main(String[] args) {
//        System.out.println("Тест работы с \"неправильным\" массивом:");
//        try {
//            String[][] arr = new String[][]{
//                    {"1", "s", "1", "1",},
//                    {"1", "1", "1", "1",},
//                    {"1", "1", "1", "1",},
//                    {"1", "1", "1", "1",}};
//            System.out.println("Сумма значений: " + summArrayElems(arr));
//        } catch (MyArrayDataException | MyArraySizeException e) {
//            System.err.println(e.getMessage());
//        }

        System.out.println("Трекер задач на массиве с исключениями и обрабокой оныхъ");
        TasksService tasksServiceTracker = new TasksService();

        Task t1 = new Task("T1", "Owner1", "Executor1", "Descr1");
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

    public static int summArrayElems(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (4 != arr.length || 4 != arr[0].length)
            throw new MyArraySizeException("Массив должен быть " +
                    "размера 4х4, получили " + arr.length + "x" + arr[0].length);
        int summ = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    summ += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Значение " + arr[i][j] + " в ячейке " +
                            "[" + i + "][" + j + "] не может быть преобразовано к INT");
                }
            }
        }
        return summ;
    }
}
