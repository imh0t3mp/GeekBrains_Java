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
     * Если в коробке b2 уже есть фрукты другого типа что и в b1 или если в b2 не достаточно
     * места для пересыпания фрутов, будет выброшего исключение.
     * Во всех прочих случаях, фрукты будут перемещены из b1 в b2 и b1 будет освобождена.
     *
     * @param b1 - коробка из которой нужно переложить фрукты
     * @param b2 - коробка в которую нужно переложить фрукты
     * @throws WrongTypeOfFruitInBox - если в коробке уже есть фрукты другого типа
     * @throws WrongBoxCapacity      - если еёмкости коробки не достаточно
     * @throws NoFreeSpaceInBox      - если в коробке есть фрукты и места не достаточно
     */
    public void shiftBoxes(Box b1, Box b2)
            throws WrongTypeOfFruitInBox, WrongBoxCapacity, NoFreeSpaceInBox, BoxIsEmpty { System.out.println(b2.getClass());
        if (b1.getItemsCount() > b2.getCapacity())
            throw new WrongBoxCapacity("В коробке #" + b2.getBoxID() + " не достаточно места для " +
                    "размещения " + b1.getItemsCount() + " элементов из ящика #" + b1.getBoxID());
        if (0 != b2.getItemsCount() && b1.getItemsCount() > (b2.getCapacity() - b2.getItemsCount()))
            throw new NoFreeSpaceInBox("В ящике #" + b2.getBoxID() + " не хватает места для " +
                    "добавления " + b1.getItemsCount() + " элементов ящика #" + b1.getBoxID());
        try {
            b2.shiftItems(b1);
        } catch (ClassCastException err) {
            throw new WrongTypeOfFruitInBox("Ящики содержат разные типы фруктов.");//\n" +
//                    "Ящик 1 содержит " + b1.getOneItem().getCommonType() + "\n" +
//                    "Ящик 2 содержит " + b2.getOneItem().getCommonType() + "\n"
//            );
        }
    }

    /**
     * Получить строковое описание склада
     *
     * @return - описание склада.
     */
    public String getStock() {
        StringBuilder sb = new StringBuilder();
        if (0 == stock.getTotalBoxes()) {
            sb.append("Склад пуст");
        } else {
            for (Box box : stock.getBoxes()) {
                sb.append(box).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * ДОбавить ящик на склад
     *
     * @param box - ящик для добавления
     */
    public void addBoxToStock(Box<?> box) {
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
    public boolean hasBoxAtStock(Box<?> box) {
        return stock.hasBox(box);
    }

    /**
     * Получить вес НЕТТО ящика
     *
     * @param box - ящик для взвешивания
     * @return - вес НЕТТО ящика
     * @throws BoxNotFound - ящик не найден на складе
     */
    public float getWeight(Box<?> box) throws BoxNotFound {
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
