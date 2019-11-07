package name.imh0t3mp.course.geekbrains.fruit_stock;

import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxIsEmpty;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxIsFull;

import java.util.*;

public class Box<T extends Fruit> {
    private Deque<T> fruitsInBox;
    private int capacity;
    private String boxID = UUID.randomUUID().toString();

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
            return fruitsInBox.pop();
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

    /**
     * Получить ID коробки
     *
     * @return - ID коробки
     */
    public String getBoxID() {
        return boxID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Box<?> box = (Box<?>) o;

        if (capacity != box.capacity) return false;
        return boxID.equals(box.boxID);
    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + boxID.hashCode();
        return result;
    }

    /**
     * Получить количество фруктов в коробке
     *
     * @return - количество фруктов в коробке
     */
    public int getItemsCount() {
        return fruitsInBox.size();
    }

    /**
     * Узнать ёмкость коробки
     *
     * @return - ёмкость коробки
     */
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Box #" + boxID + "\n" +
                "ёмкость:" + capacity + "\n" +
                "фруктов в корбке:" + fruitsInBox.size();
    }


}
