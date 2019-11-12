package name.imh0t3mp.course.geekbrains;

/**
 * Основной класс приложения
 */
public class App {
    public static final int CARS_COUNT = 5;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(CARS_COUNT,
                new Road(60),
                new Tunnel(),
                new Road(100),
                new Tunnel(),
                new Road(60),
                new Tunnel(),
                new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 100));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            race.getReadyCars().await();
            System.out.println("ОБЪЯВЛЕНИЕ >>> ГОНКА НАЧАТА!!!!");
            race.getFinishedCars().await();
            System.out.println("ОБЪЯВЛЕНИЕ >>> ГОНКА ЗАКОНЧЕНА!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Рейтинг победителей");
        System.out.println(race.getCarsRank());
        System.out.println("Победитель гонки:");
        System.out.println(race.getWinner());
    }
}
