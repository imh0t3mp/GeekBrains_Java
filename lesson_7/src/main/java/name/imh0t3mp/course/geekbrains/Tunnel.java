package name.imh0t3mp.course.geekbrains;

/**
 * Туннель
 * Объект гонки
 */
public class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                System.out.println(c.getName() + " расчётное время прохождения " + description + ":" + ((length * 1000) / c.getSpeed()));
                c.setCarInTunnel();
                Thread.sleep((length * 1000) / c.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                c.setCarNotInInTunnel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
