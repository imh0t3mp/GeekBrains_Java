package name.imh0t3mp.course.geekbrains.task_tracker;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Конвертер для преобразования Enum класса
 */
@Converter
public class StatusConverter implements AttributeConverter<TaskStatus, String> {

    /**
     * Преобразовать в значение для таблицы
     *
     * @param value
     * @return - строковое представление
     */
    public String convertToDatabaseColumn(TaskStatus value) {
        if (value == null) {
            return null;
        }

        return value.name();
    }

    /**
     * Преобразовать из строкового представления в объект
     *
     * @param value
     * @return - объект
     */
    public TaskStatus convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return TaskStatus.valueOf(value);
    }
}