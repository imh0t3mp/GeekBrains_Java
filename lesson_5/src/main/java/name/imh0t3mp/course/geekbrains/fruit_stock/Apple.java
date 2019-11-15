package name.imh0t3mp.course.geekbrains.fruit_stock;

public class Apple extends Fruit {
    public Apple() {
        this.weight = 1.0f;
        this.commonType = "Apple";
        this.concreteType = this.commonType + " #" + fruitGenome;
    }
}
