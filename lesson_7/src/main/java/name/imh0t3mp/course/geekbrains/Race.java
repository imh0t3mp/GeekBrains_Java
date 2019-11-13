package name.imh0t3mp.course.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Описание гонки
 */
public class Race {
    private static final int DEFAULT_CARS = 2;
    private ArrayList<Stage> stages;

    //    Барьер для запуска всех машин сразу
    private CyclicBarrier startLine;

    //    Счетчики готовых к заезду машин и машин пришедших на финиш
    private CountDownLatch readyCars;
    private CountDownLatch finishedCars;


    //    Список машин пришедших на финиш в порядке их финишированиЯ
    private ArrayList<Car> carsRank;

    /**
     * Конструктор гонки по умолчанию
     * По умолчанию считаем что в гонке может быть не больше двух машин
     *
     * @param stages - перечень этапов гонки
     */
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(DEFAULT_CARS);
        this.readyCars = new CountDownLatch(DEFAULT_CARS);
        this.finishedCars = new CountDownLatch(DEFAULT_CARS);
        this.carsRank = new ArrayList<>(DEFAULT_CARS);
        System.out.println("Подготовка к гонке. Ждём должно быть готово машин:" + DEFAULT_CARS);
    }

    /**
     * Конструктор гонки.
     * Подготавливает гонку для соревнования carsInRace машин по перечню этапов
     *
     * @param carsInRace - количество машин которые будут участвовать в гонке
     * @param stages     - спиок этапов гонки
     */
    public Race(int carsInRace, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(carsInRace);
        this.readyCars = new CountDownLatch(carsInRace);
        this.finishedCars = new CountDownLatch(carsInRace);
        this.carsRank = new ArrayList<>(carsInRace);
        System.out.println("Подготовка к гонке. Ждём должно бы ть готово машин:" + carsInRace);
    }

    /**
     * Список этапов гонки
     *
     * @return - список этапов гонки
     */
    public ArrayList<Stage> getStages() {
        return stages;
    }

    /**
     * Получить ссылку на объект синхронизации потоков
     *
     * @return - объект барьер, который будет ждать готовность потоков
     */
    public CyclicBarrier getStartLine() {
        return startLine;
    }

    /**
     * Счётчик машин готовых к гонке
     *
     * @return - указатель на счетчик
     */
    public CountDownLatch getReadyCars() {
        return readyCars;
    }

    /**
     * Счётчик машин закончивших гонку
     *
     * @return - указатель на счётчик
     */
    public CountDownLatch getFinishedCars() {
        return finishedCars;
    }

    /**
     * ДОбавить финишера в рейтинг
     * Метод синхронизируется для того, чтобы в рейтинг попадали
     * машины по порядку финиширования
     *
     * @param car - объект машина
     */
    public synchronized void raceFinished(Car car) {
        this.carsRank.add(car);
        System.out.println(car.getName() + " финишировал");
    }

    /**
     * Получить список участников гонки в порядке их финиширования
     *
     * @return
     */
    public ArrayList<Car> getCarsRank() {
        return carsRank;
    }

    /**
     * Получить автомобиль победитель
     *
     * @return - первую запись из таблицы финиша
     */
    public Car getWinner() {
        return 0 != carsRank.size() ? carsRank.get(0) : null;
    }

}
