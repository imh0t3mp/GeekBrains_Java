package name.imh0t3mp.course.geekbrains.fruit_stock;

import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxIsEmpty;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxIsFull;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Box<T extends Fruit> {
    private Deque<T> fruitsInBox;
    private int capacity;

    /**
     * Ящик определённой ёмкости
     *
     * @param capacity - емкость ящика в штуках
     */
    public Box(int capacity) {
        this.capacity = capacity;
        this.fruitsInBox = new ArrayDeque<>(capacity);
    }

    /**
     * Дефолтный ящик, в который нельзя поместить более 32х штук фруктов
     */
    public Box() {
        this.capacity = 32;
        this.fruitsInBox = new ArrayDeque<>(capacity);
    }

    /**
     * Взять один фрукт из ящика
     *
     * @return - один фрукт
     * @throws BoxIsEmpty - если ящик уже пустой
     */
    public T getOneItem() throws BoxIsEmpty {
        try {
            return fruitsInBox.poll();
        } catch (NoSuchElementException err) {
            throw new BoxIsEmpty("Коробка пустая");
        }
    }

    /**
     * Положить один фрукт в ящик
     *
     * @param item - фрукт
     * @throws BoxIsFull - если ящик уже заполнен
     */
    public void putOneItem(T item) throws BoxIsFull {
        if (fruitsInBox.size() == capacity) {
            throw new BoxIsFull("Коробка уже полная. Можно положить не больше " + capacity + " " +
                    "фруктов");
        }
        fruitsInBox.push(item);
    }

    /**
     * Простой метод для проверки что коробка пуста
     *
     * @return - TRUE|FALSE
     */
    public boolean boxIsEmpty() {
        return null == fruitsInBox.peek();
    }

    /**
     * Взять все фрукты из ящика
     *
     * @return - коллекцию с фруктами
     */
    public Collection<T> getAllItems() {
        return fruitsInBox;
    }
}
