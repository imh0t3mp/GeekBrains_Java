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
    private int carsInRace;
    private ArrayList<Stage> stages;

    private CyclicBarrier startLine;

    private CountDownLatch readyCars;
    private CountDownLatch finishedCars;


    private Semaphore carInTunnel = new Semaphore(1);

    public ArrayList<Stage> getStages() {
        return stages;
    }

    private ArrayList<Car> carsRank;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(DEFAULT_CARS);
        this.readyCars = new CountDownLatch(DEFAULT_CARS);
        this.finishedCars = new CountDownLatch(DEFAULT_CARS);
        this.carsRank = new ArrayList<>(DEFAULT_CARS);
        this.carsInRace = DEFAULT_CARS;

        System.out.println("Подготовка к гонке. Ждём должно быть готово машин:" + DEFAULT_CARS);
    }

    public Race(int carsInRace, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(carsInRace);
        this.readyCars = new CountDownLatch(carsInRace);
        this.finishedCars = new CountDownLatch(carsInRace);
        this.carsRank = new ArrayList<>(carsInRace);
        this.carsInRace = carsInRace;
        System.out.println("Подготовка к гонке. Ждём должно бы ть готово машин:" + carsInRace);
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
     * Счётчик машин готовых к гонке
     *
     * @return
     */
    public CountDownLatch getReadyCars() {
        return readyCars;
    }

    /**
     * Счётчик машин закончивших гонку
     *
     * @return
     */
    public CountDownLatch getFinishedCars() {
        return finishedCars;
    }

    //    /**
//     * Получить семафор для работы в туннеле
//     * TODO: а может тут проще Lock?
//     *
//     * @return
//     */
//    public void setCarInTunnel() {
//        try {
//            this.carInTunnel.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setCarIsNotInTunnel() {
//        this.carInTunnel.release();
//    }


//
//    /**
//     * ДОбавить финишера в рейтинг
//     *
//     * @param car - объект машина
//     */
//    public synchronized void raceFinished(Car car) {
////        this.carsRank.add(car);
//        finishedCars.countDown();
//        System.out.println(car.getName() + " финишировал");
//    }
//
//    /**
//     * Получить список участников гонки в порядке их финиширования
//     *
//     * @return
//     */
//    public ArrayList<Car> getCarsRank() {
//        return carsRank;
//    }
//
//    /**
//     * Получить указатель на автомобиль победитель
//     *
//     * @return - первую запись из таблицы финиша
//     */
//    public Car getWinner() {
//        return 0 != carsRank.size() ? carsRank.get(0) : null;
//    }
//
//    public void waitAllFinished() throws InterruptedException {
//        this.finishedCars.await();
//    }
}
