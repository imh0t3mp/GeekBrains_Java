package name.imh0t3mp.course.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Описание гонки
 */
public class Race {
    private static final int DEFAULT_CARS = 2;
    private int carsInRace;
    private ArrayList<Stage> stages;

    private CyclicBarrier startLine;
    private CountDownLatch finishedCars;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    private ArrayList<Car> carsRank;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(DEFAULT_CARS);
        this.finishedCars = new CountDownLatch(DEFAULT_CARS);
        this.carsRank = new ArrayList<>(DEFAULT_CARS);
        this.carsInRace = DEFAULT_CARS;

        System.out.println("Подготовка к гонке. Ждём должно быть готово машин " + DEFAULT_CARS);
    }

    public Race(int carsOnBarier, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(carsOnBarier);
        this.finishedCars = new CountDownLatch(carsOnBarier);
        this.carsRank = new ArrayList<>(carsOnBarier);
        this.carsInRace = carsOnBarier;
        System.out.println("Подготовка к гонке. Ждём должно быть готово машин " + carsOnBarier);
    }

    /**
     * Получить ссылку на объект синхронизации потоков
     *
     * @return - обект барьер, который будет ждать готовность потоков
     */
    public CyclicBarrier getStartLine() {
        return startLine;
    }

    /**
     * ДОбавить финишера в рейтинг
     *
     * @param car - объект машина
     */
    public synchronized void raceFinished(Car car) {
//        this.carsRank.add(car);
        finishedCars.countDown();
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
     * Получить указатель на автомобиль победитель
     *
     * @return - первую запись из таблицы финиша
     */
    public Car getWinner() {
        return 0 != carsRank.size() ? carsRank.get(0) : null;
    }

    public void waitAllFinished() throws InterruptedException {
        this.finishedCars.await();
    }
}
