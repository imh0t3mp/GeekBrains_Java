package name.imh0t3mp.course.geekbrains.fruit_stock;

public class Orange extends Fruit {
    public Orange() {
        this.weight = 1.5f;
//        this.commonType = this.getClass().getSimpleName();
        this.commonType = "Япельсины";
        this.concreteType = this.commonType + " #" + fruitGenome;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public String getCommonType() {
        return this.commonType;
    }
}
