package name.imh0t3mp.course.geekbrains.fruit_stock;

import name.imh0t3mp.course.geekbrains.fruit_stock.errors.BoxNotFound;
import name.imh0t3mp.course.geekbrains.fruit_stock.errors.StockIsFull;

import java.util.Collection;

/**
 * Набор действий с коробками на складе
 */
public interface Stock {

    public Box getBox(String boxId) throws BoxNotFound;

    public void addBox(Box box) throws StockIsFull;

    public void removeBox(Box box) throws BoxNotFound;

    public void removeBox(String boxId) throws BoxNotFound;

    public Box seizeBox(String boxId) throws BoxNotFound;

    public boolean hasBox(String boxId);

    public boolean hasBox(Box box);

    public int getStockCapacity();

    public int getTotalBoxes();

    public Collection getBoxes();

    public void burnThemAll();

}
