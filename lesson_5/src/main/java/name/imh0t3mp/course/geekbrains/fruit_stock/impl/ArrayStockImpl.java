package name.imh0t3mp.course.geekbrains.fruit_stock.impl;


import name.imh0t3mp.course.geekbrains.fruit_stock.Box;
import name.imh0t3mp.course.geekbrains.fruit_stock.Stock;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxInStock;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxNotFound;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.StockIsFull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Можно сделать класс наследником от общего интерфейса Stock, в котором описать типисеский набор
 * операций на складе, но усложнять пока не стал.
 */
public class ArrayStockImpl implements Stock {

    private List<Box> stockBoxes;
    private int stockCapacity;

    /**
     * Создать сервис для оперированния складом с ящиками на складе
     *
     * @param stockCapacity - емкость склада
     */
    public ArrayStockImpl(int stockCapacity) {
        this.stockCapacity = stockCapacity;
        this.stockBoxes = new ArrayList<>();
    }

    /**
     * Создать сервис для оперирования складом с тремя ящиками
     * Да, вот такой маленьнкий склад
     */
    public ArrayStockImpl() {
        this.stockCapacity = 3;
        this.stockBoxes = new ArrayList<>();
    }


    /**
     * Проверить, что коробка с таким ID уже есть на складе
     *
     * @param boxId - ID коробки
     */
    public boolean hasBox(String boxId) {
        if (0 != stockBoxes.size()) {
            for (Box box : stockBoxes) {
                if (box.getBoxID().equals(boxId)) return true;
            }
        }
        return false;
    }

    /**
     * Проверить что такая коробка уже есть на складе
     *
     * @param box - искомая коробка
     * @return - TRUE - если такая коробка уже есть, FALSE - если на складе нет коробок или если
     * такой коробки еще нет
     */
    public boolean hasBox(Box box) {
        return 0 != stockBoxes.size() && stockBoxes.contains(box);
    }


    /**
     * Получить коробку по её ID
     *
     * @param boxId - ID коробки
     * @return - Нужная коробка
     * @throws BoxNotFound - если корбка не найдена в списках
     */
    public Box getBox(String boxId) throws BoxNotFound {
        if (hasBox(boxId)) {
            for (Box box : stockBoxes) {
                if (box.getBoxID().equals(boxId))
                    return box;
            }
            return null;
        } else {
            throw new BoxNotFound("Коробка #" + boxId + " на найдена на складе");
        }
    }

    /**
     * Получить ёмкость склада
     *
     * @return - ёмкость склада в ящиках
     */
    public int getStockCapacity() {
        return stockCapacity;
    }

    /**
     * Получить количество ящиков на складе
     *
     * @return - количество ящиков размещённых на складе
     */
    public int getTotalBoxes() {
        return stockBoxes.size();
    }

    /**
     * ДОбавить ящик на склад
     *
     * @param box - ящик с фруктами
     * @throws StockIsFull - если склад уже полный
     */
    @Override
    public void addBox(Box box) throws StockIsFull, BoxInStock {
        if (hasBox(box)) throw new BoxInStock("Ящик #" + box.getBoxID() + " уже есть на складе");
        if (stockCapacity == stockBoxes.size()) throw new StockIsFull("Склад заполнен");
        stockBoxes.add(box);
    }

    /**
     * Убрать ящик со склада
     * Просто убирает, уничтожает ящик на складе
     *
     * @param box - ящик для удаления
     * @throws BoxNotFound - если ящик не найден
     */
    @Override
    public void removeBox(Box box) throws BoxNotFound {
        if (!hasBox(box))
            throw new BoxNotFound("Коробка #" + box.getBoxID() + " не найдена на складе");
        stockBoxes.remove(box);
    }

    /**
     * Убрать ящик со склада по его ID
     *
     * @param boxId - ID ящика
     * @throws BoxNotFound - если ящик не найден
     */
    @Override
    public void removeBox(String boxId) throws BoxNotFound {
        if (!hasBox(boxId))
            throw new BoxNotFound("Коробка #" + boxId + " не найдена на складе");
        for (Box box : stockBoxes) {
            if (box.getBoxID().equals(boxId)) stockBoxes.remove(box);
        }

    }

    /**
     * Забрать ящик со склада
     * Возвращает содержимое ящика и удаляет объект из списка ящиков склада
     *
     * @param boxId - ID ящика
     * @return - нужный ящик
     * @throws BoxNotFound - если ящик не найден
     */
    @Override
    public Box seizeBox(String boxId) throws BoxNotFound {
        if (!hasBox(boxId)) throw new BoxNotFound("Ящик #" + boxId + " не найден на складе");
        Box seizeBox = getBox(boxId);
        stockBoxes.remove(seizeBox);
        return seizeBox;
    }


    /**
     * Получить список всех ящиков на складе
     *
     * @return - список всех ящиков
     */
    @Override
    public Collection<Box> getBoxes() {
        return stockBoxes;
    }

    /**
     * Полностью, тотально зачистить весь склад.
     */
    @Override
    public void burnThemAll() {
        stockBoxes.clear();
    }

    /**
     * Получить описание склада
     *
     * @return - строковое описание склада
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (0 == this.getTotalBoxes()) {
            sb.append("Склад пуст");
        } else {
            for (Box box : this.getBoxes()) {
                sb.append(box).append("\n");
            }
        }
        return sb.toString();
    }
}
