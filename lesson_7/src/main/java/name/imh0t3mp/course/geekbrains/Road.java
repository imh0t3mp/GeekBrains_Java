package name.imh0t3mp.course.geekbrains;

/**
 * Дорога
 * Объект гонки
 */
public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            System.out.println(c.getName() + " расчётное время прохождения этапа:" + ((float) ((length * 1000) / c.getSpeed())));
            Thread.sleep(length * 1000 / c.getSpeed());
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
