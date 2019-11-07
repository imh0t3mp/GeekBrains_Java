package name.imh0t3mp.course.geekbrains.fruit_stock;

import name.imh0t3mp.course.geekbrains.fruit_stock.errors.*;
import name.imh0t3mp.course.geekbrains.fruit_stock.impl.ArrayStockImpl;

/**
 * Наверно, здесь нужна фабрика
 */
public class StockService {
    private Stock stock;

    public StockService() {
        this.stock = new ArrayStockImpl();
    }

    public StockService(Stock stock) {
        this.stock = stock;
    }

    /**
     * Получить ящик со склада по его ID
     * Ящик по прежнему будет числаться на складе
     *
     * @param boxId - ID ящика
     * @return - нужный ящик или NULL
     */
    public Box getBox(String boxId) {
        try {
            return stock.getBox(boxId);
        } catch (BoxNotFound err) {
            System.err.println(err.getMessage());
            return null;
        }
    }


    /**
     * Пересыпать фрукты из коробки b1 в коробку b2
     * Если коробка b2 имеет ёмкосмть меньше чем b1, будет выброшено исключение
     * Если в коробке b2 не достаточно места для пересыпания фрутов, будет выброшего исключение.
     * Во всех прочих случаях, фрукты будут перемещены из b1 в b2 и b1 будет освобождена.
     *
     * @param b1 - коробка из которой нужно переложить фрукты
     * @param b2 - коробка в которую нужно переложить фрукты
     * @throws WrongBoxCapacity - если еёмкости коробки не достаточно
     * @throws NoFreeSpaceInBox - если в коробке есть фрукты и места не достаточно
     */
    public <T extends Fruit> void shiftBoxes(Box<T> b1, Box<T> b2)
            throws WrongBoxCapacity, NoFreeSpaceInBox, BoxIsFull {
        if (b1.getItemsCount() > b2.getCapacity())
            throw new WrongBoxCapacity("В коробке #" + b2.getBoxID() + " не достаточно места для " +
                    "размещения " + b1.getItemsCount() + " элементов из ящика #" + b1.getBoxID());
        if (0 != b2.getItemsCount() && b1.getItemsCount() > (b2.getCapacity() - b2.getItemsCount()))
            throw new NoFreeSpaceInBox("В ящике #" + b2.getBoxID() + " не хватает места для " +
                    "добавления " + b1.getItemsCount() + " элементов ящика #" + b1.getBoxID());
//        перемещение содержимого ящика в тот же ящик не имеет смысла
        if(b1.equals(b2)) return;
        for(T item: b1.getAllItems()){
            b2.putItem(item);
        }
        b1.cleanBox();
    }

    /**
     * Получить склад
     *
     * @return - склад для операций
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * ДОбавить ящик на склад
     *
     * @param box - ящик для добавления
     */
    public <T extends Fruit> void addBoxToStock(Box<T> box) {
        try {
            stock.addBox(box);
        } catch (StockIsFull | BoxInStock err) {
            System.err.println(err.getMessage());
        }
    }

    /**
     * Проверить, есть такой ящик на складе
     *
     * @param boxId - id ящика
     * @return - TRUE|FALSE
     */
    public boolean hasBoxAtStock(String boxId) {
        return stock.hasBox(boxId);
    }

    /**
     * Проверить, этот ящик со склада или нет
     *
     * @param box - искомый ящик
     * @return - TRUE|FALSE
     */
    public <T extends Fruit> boolean hasBoxAtStock(Box<T> box) {
        return stock.hasBox(box);
    }

    /**
     * Получить вес НЕТТО ящика
     *
     * @param box - ящик для взвешивания
     * @return - вес НЕТТО ящика
     * @throws BoxNotFound - ящик не найден на складе
     */
    public <T extends Fruit> float getWeight(Box<T> box) throws BoxNotFound {
        if (!stock.hasBox(box))
            throw new BoxNotFound("На складе не найден ящик #" + box.getBoxID() + "\n Возможно на " +
                    "складе ошибка сортамента");
        return 0 == box.getItemsCount() ? 0.0f : box.getWeight();
    }

    /**
     * Получить вес ящика по его ID
     *
     * @param boxId - ID ящика
     * @return - вес ящика
     * @throws BoxNotFound - если ящик не найден на складе
     */
    public float getWeight(String boxId) throws BoxNotFound {
        if (!stock.hasBox(boxId))
            throw new BoxNotFound("На складе не найден ящик #" + boxId);
        return 0 == stock.getBox(boxId).getItemsCount() ? 0.0f : stock.getBox(boxId).getWeight();
    }
}
