package name.imh0t3mp.course.geekbrains.fruit_stock;

public class Orange extends Fruit {
    public Orange() {
        this.weight = 1.5f;
        this.commonType = "Orange";
        this.concreteType = this.commonType + " #" + fruitGenome;
    }
}
