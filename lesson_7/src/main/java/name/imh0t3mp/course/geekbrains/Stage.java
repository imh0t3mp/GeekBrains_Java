package name.imh0t3mp.course.geekbrains;

/**
 * Абтстрактный класс
 * Описание состояния объекта
 */
public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
