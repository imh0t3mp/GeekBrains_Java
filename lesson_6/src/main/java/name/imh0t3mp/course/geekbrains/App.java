package name.imh0t3mp.course.geekbrains;

import java.util.Map;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
        String text = "Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся)." +
                " Найти и вывести список уникальных слов, из которых состоит массив " +
                "(дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.";
        step1(text);
        step2();
    }

    /**
     * Вывести список уникальных слов в тексте,
     * подсчитать количество вхождений каждого слова в текст
     * <p>
     * Из текста формирвет список.
     * Проходи по списку и формирует пару слово:количество
     * <p>
     * После разбора выводит список слов и список пар слово:количество
     *
     * @param text - текст для разбора
     */
    private static void step1(String text) {
        System.out.println("Заданиие 1: слова");
        String[] words = text.toLowerCase().
                replaceAll("[^а-яa-z0-9- ]+", "").
                split(" ");
        Map<String, Integer> wordsCount = new TreeMap<>();
        for (String word : words) {
            wordsCount.put(word, (wordsCount.getOrDefault(word, 0)) + 1);
        }
        System.out.println("Уникальный набор слов:");
        System.out.println(wordsCount.keySet());
        System.out.println("Список слов и их количество:");
        System.out.println(wordsCount);
    }

    /**
     * Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и
     * телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять
     * записи, а с помощью метода get() искать номер телефона по фамилии. Следует учесть, что под
     * одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда при запросе
     * такой фамилии должны выводиться все телефоны.
     */
    private static void step2() {
        System.out.println("Задание 2: телефонная книга");
        System.out.println("Добавим несколько номеров:");
        PhoneBook pb = new PhoneBook();
        pb.add("12345", "Owner1");
        pb.add("12346", "Owner2");
        pb.add("12347", "Owner3");
        pb.add("12348", "Owner1");
        System.out.println("Показать всю книгу");
        System.out.println(pb.getPhoneBook());
        System.out.println("Найти все номера абонентка Owner1");
        System.out.println(pb.getByIterate("Owner1"));
        System.out.println(pb.getByStreamFilter("Owner1"));
    }
}
