package name.imh0t3mp.course.geekbrains.fruit_stock;

import java.util.UUID;

public class Fruit {
    protected float weight;
    protected String commonType;
    protected String concreteType;
    protected String fruitGenome;

    public Fruit() {
        this.fruitGenome = UUID.randomUUID().toString();
        this.weight = Float.MIN_VALUE;
        this.commonType = "I'm a some kind of fruit or maybe not, i'm not sure. May be i'm " +
                "poisoned or " +
                "may be not. You can eat me and we'll check it. Or, may be, i will eat you. " +
                "Mua-ha-ha-ha-ha!!!";
    }

    public float getWeight() {
        return this.weight;
    }

    public String getCommonType() {
        return this.commonType;
    }

    @Override
    public String toString() {
        return String.format("%s and weight %.02f", concreteType, weight);
    }

}
