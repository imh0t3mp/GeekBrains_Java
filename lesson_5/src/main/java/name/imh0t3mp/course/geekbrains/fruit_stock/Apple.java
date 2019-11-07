package name.imh0t3mp.course.geekbrains.fruit_stock;

public class Apple extends Fruit {
    public Apple() {
        this.weight = 1.0f;
//        this.commonType = this.getClass().getSimpleName();
        this.commonType = "Яблоки";
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
