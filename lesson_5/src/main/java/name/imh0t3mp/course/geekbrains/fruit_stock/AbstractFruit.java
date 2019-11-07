package name.imh0t3mp.course.geekbrains.fruit_stock;

/**
 * Абстрактный класс для создания всех последующих фруктов
 */
public abstract class AbstractFruit {
    float weight;
    String type;

    AbstractFruit() {
        this.weight = Float.MIN_VALUE;
        this.type = "I'm a some kind of fruit or maybe not, i'm not sure. May be i'm poisoned or " +
                "may be not. You can eat me and we'll check it. Or, may be, i will eat you. " +
                "Mua-ha-ha-ha-ha!!!";
    }

    public float getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s and weight %.02f", type, weight);
    }
}
