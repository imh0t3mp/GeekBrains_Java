package name.imh0t3mp.course.geekbrains.fruit_stock;

import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxIsEmpty;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxIsFull;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.NoItemInBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Box<T extends Fruit> {

    private static final float THRESHOLD = 0.000001f;
    private List<T> fruitsInBox;
    private int capacity;
    private String boxID = UUID.randomUUID().toString();

    /**
     * Ящик определённой ёмкости
     *
     * @param capacity - емкость ящика в штуках
     */
    public Box(int capacity) {
        this.capacity = capacity;
        this.fruitsInBox = new ArrayList<>(capacity);
    }

    /**
     * Дефолтный ящик, в который нельзя поместить более 32х штук фруктов
     */
    public Box() {
        this.capacity = 32;
        this.fruitsInBox = new ArrayList<>(capacity);
    }

    /**
     * Взять один фрукт из ящика
     *
     * @return - один фрукт
     * @throws NoItemInBox - если в ячейке нет фрукта
     */
    public T getItem(int index) throws NoItemInBox {
        if (index <= getItemsCount())
            return fruitsInBox.get(index);
        throw new NoItemInBox("Ячейка " + index + " ящика пуста");
    }

    /**
     * Получить фрукт из самой первой ячейки
     *
     * @return - фрукт из ячейки
     * @throws BoxIsEmpty - если коробка пуста
     */
    public T getItem() throws BoxIsEmpty {
        if (0 == getItemsCount()) throw new BoxIsEmpty("Коробка пустая");
        return fruitsInBox.get(0);
    }

    /**
     * Положить один фрукт в ящик
     *
     * @param item - фрукт
     * @throws BoxIsFull - если ящик уже заполнен
     */
    public void putItem(T item) throws BoxIsFull {
        if (getItemsCount() == capacity) {
            throw new BoxIsFull("Коробка уже полная. Можно положить не больше " +
                    capacity + " фруктов в коробку");
        }
        fruitsInBox.add(item);
    }

    /**
     * Простой метод для проверки что коробка пуста
     *
     * @return - TRUE|FALSE
     */
    public boolean boxIsEmpty() {
        return 0 == getItemsCount();
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

    /**
     * Получить вес НЕТТО ящика
     *
     * @return - вес НЕТТО ящика
     */
    public float getWeight() {
        return (0 == getItemsCount()) ?
                0.0f :
                getItemsCount() * fruitsInBox.get(0).getWeight();
    }

    /**
     * Сравнить вес двух ящиков безотностительно содержимого
     * Будем сравнивать вес фруктов как таковых
     *
     * @param box - ящик для сравнения
     * @return - TRUE|FALSE Одинаковы ли ящики по весу
     */
    public boolean compareTo(Box box) {
        return Math.abs(box.getWeight() - getWeight()) <= THRESHOLD;
    }

    /**
     * Зачистить содержимое коробки
     */
    public void cleanBox() {
        fruitsInBox.clear();
    }

    @Override
    public String toString() {
        return "Ящик #" + boxID + "\n" +
                "содержит: " +
                (0 != getItemsCount() ? fruitsInBox.get(0).getCommonType() : "пустоту") + "\n" +
                "ёмкость ящика:" + capacity + "\n" +
                "фруктов в коробке:" + fruitsInBox.size();
    }
}
