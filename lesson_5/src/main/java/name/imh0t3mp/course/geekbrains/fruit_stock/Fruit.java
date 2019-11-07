package name.imh0t3mp.course.geekbrains.fruit_stock;

public class Fruit extends AbstractFruit {
    @Override
    public float getWeight() {
        return Float.MIN_VALUE;
    }

    @Override
    public String getCommonType() {
        return this.commonType;
    }
}
