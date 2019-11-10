package name.imh0t3mp.course.geekbrains;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PhoneBook {
    Map<String, String> phoneBook;

    public PhoneBook() {
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
    public Set<String> getByStreamFilter(String phoneOwner) {
        return phoneBook.entrySet().
                stream().
                filter(x -> x.getValue().equals(phoneOwner)).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).keySet();
    }

    /**
     * Получить все номера владельца перебором
     *
     * @param phoneOwner - имя владельца номер
     * @return - уникальный набор номеров телефонов
     */
    public Set<String> getByIterate(String phoneOwner) {
        Set<String> ownerPhones = new TreeSet<>();
        for (String phone : phoneBook.keySet()) {
            String bookPhone = phoneBook.get(phone);
            if (bookPhone.equals(phoneOwner)) {
                ownerPhones.add(phone);
            }
        }
        return ownerPhones;
    }

    public Map<String, String> getPhoneBook() {
        return phoneBook;
    }
}
