package name.imh0t3mp.course.geekbrains;

import java.util.Map;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
        String text = "Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся)." +
                " Найти и вывести список уникальных слов, из которых состоит массив " +
                "(дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.";
        step1(text);
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
}
