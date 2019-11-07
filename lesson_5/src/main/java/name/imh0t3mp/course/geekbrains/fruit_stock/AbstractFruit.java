package name.imh0t3mp.course.geekbrains.fruit_stock;

import java.util.UUID;

/**
 * Абстрактный класс для создания всех последующих фруктов
 * Просто некий абстрактный фрукт.
 * Думаю что создавать интерфейс не имеет смысла, потому что сами фрукты меняться не будут но все
 * фрукты будут обладать неким общим набором свойств
 */
public abstract class AbstractFruit {
    float weight;
    String commonType;
    String concreteType;
    String fruitGenome = UUID.randomUUID().toString();

    AbstractFruit() {
        this.weight = Float.MIN_VALUE;
        this.commonType = "I'm a some kind of fruit or maybe not, i'm not sure. May be i'm " +
                "poisoned or " +
                "may be not. You can eat me and we'll check it. Or, may be, i will eat you. " +
                "Mua-ha-ha-ha-ha!!!";
    }

    /**
     * Абстрактный метод для получения веса фрукта.
     * Ведь фрукты могут усыхать.
     *
     * @return - вес фрукта
     */
    abstract public float getWeight();

    abstract public String getCommonType();

    @Override
    public String toString() {
        return String.format("%s and weight %.02f", concreteType, weight);
    }

}
