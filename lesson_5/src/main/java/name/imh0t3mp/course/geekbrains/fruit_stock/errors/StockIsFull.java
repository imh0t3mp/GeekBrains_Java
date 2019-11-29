package name.imh0t3mp.course.geekbrains.fruit_stock.errors;

public class StockIsFull extends Throwable {
    public StockIsFull(String message) {
        super(message);
    }
}
