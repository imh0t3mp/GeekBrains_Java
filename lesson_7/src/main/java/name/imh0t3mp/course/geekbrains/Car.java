package name.imh0t3mp.course.geekbrains;

/**
 * Машина
 * Субьект гонки
 */
public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    /**
     * Запустить поток подготовки к гонке
     */
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов к старту");
//            Добавим машину в очередь готовых к гонке для вывода сообщения о старте гонки
            race.getReadyCars().countDown();
//            Ожидаем готовности других водителей
            race.getStartLine().await();
            System.out.println(name + " стартовал на скорости " + speed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
//        Машина прошла все этапы и закончила гонку
//        Сообщим что машина закончила гонку и добавим ее в таблицу рейтинга
        race.getFinishedCars().countDown();
        race.raceFinished(this);
    }

    /**
     * Сообщить что машина въезжает в туннель
     *
     * @throws InterruptedException
     */
    public void setCarInTunnel() throws InterruptedException {
        race.getSemaphore().acquire();
    }

    /**
     * Сообщить что машина покинула туннель
     */
    public void setCarNotInInTunnel() {
        race.getSemaphore().release();
    }


    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
