package name.imh0t3mp.course.geekbrains;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PhoneBook {
    Map<String, String> phoneBook;

    public PhoneBook(Map<String, String> phoneBook) {
        this.phoneBook = new HashMap<>();
    }

    /**
     * Добавить запись в телефонную книгу
     *
     * @param phoneNum   - нгомер телефона
     * @param phoneOwner - владелец
     */
    public void add(String phoneNum, String phoneOwner) {
        phoneBook.put(phoneNum, phoneOwner);
    }

    /**
     * Получить все номера телефонов владельца
     *
     * @param phoneOwner - имя владельца номер
     * @return - уникальный набор номеров телефонов
     */
    public Set<String> get(String phoneOwner) {
        return phoneBook.entrySet().
                stream().
                filter(x -> x.getValue().equals(phoneOwner)).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).keySet();
    }
}
