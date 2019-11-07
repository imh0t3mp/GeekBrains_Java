package name.imh0t3mp.course.geekbrains;

import name.imh0t3mp.course.geekbrains.fruit_stock.Apple;
import name.imh0t3mp.course.geekbrains.fruit_stock.Box;
import name.imh0t3mp.course.geekbrains.fruit_stock.Orange;
import name.imh0t3mp.course.geekbrains.fruit_stock.StockService;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxNotFound;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.WrongTypeOfFruitInBox;
import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.TasksService;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.impl.TaskArrayListRepositoryImpl;

public class App {

    public static void main(String[] args) {
//        testRepository();
        testFruitsBoxes();
    }

    private static void testFruitsBoxes() {
        System.out.println("Склад с ящиками фруктов");
        try {
            System.out.println("/************************************************************/");
            System.out.println("Заполним два ящика:");
            Box<Apple> appleBox = new Box<>(2);
            appleBox.putOneItem(new Apple());
            appleBox.putOneItem(new Apple());
            Box<Orange> orangeBox = new Box<>(2);
            orangeBox.putOneItem(new Orange());
            System.out.println("Ящик яблок:" + appleBox);
            System.out.println("Ящик апельсинов:" + orangeBox);
            System.out.println("/************************************************************/");
            System.out.println("Сравнить вес двух ящиков:");
            if (orangeBox.compareTo(orangeBox))
                System.out.println("Ящики имеют одинаковый вес");
            else
                System.out.println("Ящики имеют разный вес");
//
        } catch (Exception err) {System.out.println("/************************************************************/");
//            System.out.println("Поместить ящики на склад:");
//            StockService stockService = new StockService();
//            stockService.addBoxToStock(appleBox);
//            stockService.addBoxToStock(orangeBox);
//            System.out.println("Ящики на складе:\n" + stockService.getStock());
//            try {
//                System.out.println("/************************************************************/");
//                System.out.println("Пересыпать содержимое одного ящика в другой");
//                stockService.shiftBoxes(appleBox, orangeBox);
//            } catch (WrongTypeOfFruitInBox err) {
//                System.err.println(err.getMessage());
//            }
//            try {
//                System.out.println("/************************************************************/");
//                System.out.println("Получить вес ящика #" + appleBox.getBoxID());
//                System.out.println(stockService.getNetto(appleBox));
//                System.out.println("Получить вес ящика #" + appleBox.getBoxID());
//                System.out.println(stockService.getNetto(orangeBox));
//            } catch (BoxNotFound err) {
//                System.err.println(err.getMessage());
//            }
            err.printStackTrace();
        }
    }


    private static void testRepository() {
        System.out.println("Трекер задач на массиве с исключениями и обрабокой оныхъ");
        TasksService tasksServiceTracker = new TasksService(new TaskArrayListRepositoryImpl());

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

}
