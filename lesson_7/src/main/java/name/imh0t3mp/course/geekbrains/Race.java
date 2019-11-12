package name.imh0t3mp.course.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

/**
 * Описание гонки
 */
public class Race {
    private static final int DEFAULT_CARS = 2;
    private ArrayList<Stage> stages;

    private CyclicBarrier startLine;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(DEFAULT_CARS);
        System.out.println("Подготовка к гонке. Ждём должно быть готово машин " + DEFAULT_CARS);
    }

    public Race(int carsOnBarier, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.startLine = new CyclicBarrier(carsOnBarier);
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
}
