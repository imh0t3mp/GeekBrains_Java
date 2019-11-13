package name.imh0t3mp.course.geekbrains;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Основной класс приложения
 */
public class App {
    public static final int CARS_COUNT = 5;
    public static final int CARS_IN_TUNNEL = (int) Math.ceil(CARS_COUNT / 2);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);
//        Подготовим трассу для гонки
        Race race = new Race(CARS_COUNT,
                new Road(60),
                new Tunnel(CARS_IN_TUNNEL),
                new Road(100),
                new Tunnel(CARS_IN_TUNNEL),
                new Road(10),
                new Tunnel(CARS_IN_TUNNEL),
                new Road(40));
        Car[] cars = new Car[CARS_COUNT];
//        Подготовим машины к гонке
        for (int i = 0; i < CARS_COUNT; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 100));
        }
//        Стартуем подготовку к гонке
        for (Car car : cars) {
            executorService.execute(car);
        }
        try {
            race.getReadyCars().await();
            System.out.println("ОБЪЯВЛЕНИЕ >>> ГОНКА НАЧАТА!!!!");
            race.getFinishedCars().await();
            System.out.println("ОБЪЯВЛЕНИЕ >>> ГОНКА ЗАКОНЧЕНА!!!!");
            System.out.println("Рейтинг победителей");
            System.out.println(race.getCarsRank());
            System.out.println("Победитель гонки:");
            System.out.println(race.getWinner());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
