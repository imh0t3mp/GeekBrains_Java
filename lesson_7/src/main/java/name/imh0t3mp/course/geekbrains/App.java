package name.imh0t3mp.course.geekbrains;

/**
 * Основной класс приложения
 */
public class App {
    public static final int CARS_COUNT = 2;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(CARS_COUNT, new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
//        final int THREADS_COUNT = 5;
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS_COUNT);
//        for (int i = 0; i < THREADS_COUNT; i++) {
//            int w = i;
//            new Thread(() -> {
//                try {
//                    System.out.println("Подготавливается " + w);
//                    Thread.sleep(2000 + 500 * (int) (Math.random() * 10));
//                    System.out.println("Готов " + w);
//                    cyclicBarrier.await();
//                    System.out.println("Поехал " + w);
//                } catch (InterruptedException | BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }
}
