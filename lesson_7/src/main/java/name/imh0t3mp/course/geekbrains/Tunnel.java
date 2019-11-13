package name.imh0t3mp.course.geekbrains;

import java.util.concurrent.Semaphore;

/**
 * Туннель
 * Объект гонки
 */
public class Tunnel extends Stage {
    //    Семафор для ограниченияпропускной способности участка дороги
    private Semaphore semaphore;

    public Tunnel(int carsInTunnel) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(carsInTunnel);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                System.out.println(c.getName() + " расчётное время прохождения " + description + ":" + ((length * 1000) / c.getSpeed()));
//                Машина в туннеле
                semaphore.acquire();
                Thread.sleep((length * 1000) / c.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
//                машина покинула туннель
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
