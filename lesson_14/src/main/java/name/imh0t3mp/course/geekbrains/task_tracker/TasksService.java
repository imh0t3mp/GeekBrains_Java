package name.imh0t3mp.course.geekbrains.task_tracker;

import name.imh0t3mp.course.geekbrains.exceptions.*;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.RepositoryStorage;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Унифицированный класс для работы с разными типами репозиториев
 */
@Service
public class TasksService {
    private static Logger log = LoggerFactory.getLogger(TasksService.class);

    private TaskRepository repository;

    /**
     * Инициализация сервиса определённым типом репозитория
     */
    @Autowired
    public TasksService(@Qualifier("taskHibernateRepoImpl") TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Инициализатор репозитория
     *
     * @param repository - репозиторий для сервиса
     */
    @Autowired
    @Qualifier(value = "taskHibernateRepoImpl")
    public void setRepository(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Добавить задачу в список
     *
     * @param task - задача для добавления
     */
    @Transactional
    public void addTask(Task task) {
        try {
            repository.addTask(task);
        } catch (RepositoryIsFull | TaskAlreadyExists | RepositoryError err) {
            log.warn(err.getMessage(), err);
        }

    }

    /**
     * Получить задачу по ID
     *
     * @param id - ID задачи
     * @return - найденная задача или NULL
     */
    public Task getTask(int id) {
        try {
            return repository.getTask(id);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
            return null;
        }
    }

    /**
     * Получить задачу по её имени
     *
     * @param taskName - имя задачи
     * @return - найдненная задача или NULL
     */
    public Task getTask(String taskName) {
        try {
            return repository.getTask(taskName);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
            return null;
        }
    }


    public boolean hasTask(Task task) {
        return repository.hasTask(task);
    }

    public boolean hasTask(int tasKId) {
        return repository.hasTask(tasKId);
    }

    public boolean hasTask(String taskName) {
        return hasTask(taskName);
    }

    /**
     * Обнсвить статус задачи
     *
     * @param taskId - ID задачи
     * @param status - статус задачи
     */
    @Transactional
    public void changeTaskStatus(int taskId, TaskStatus status) {
        try {
            repository.changeTaskStatus(taskId, status);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
        }
    }

    /**
     * Обновить статус задачи
     *
     * @param taskName - имя задачи
     * @param status   - статус задачи
     */
    @Transactional
    public void changeTaskStatus(String taskName, TaskStatus status) {
        try {
            repository.changeTaskStatus(taskName, status);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
        }
    }

    /**
     * Удалить задачу из списка
     *
     * @param task - объект задачи для удаления
     */
    @Transactional
    public void deleteTask(Task task) {
        try {
            repository.deleteTask(task);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
        }
    }

    /**
     * Удалить задачу по её ID
     *
     * @param id - ID удаляемой задачи
     */
    @Transactional
    public void deleteTask(int id) {
        try {
            repository.deleteTask(id);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
        }
    }

    /**
     * Удалить задачу по её имени
     *
     * @param taskName - имя задачи для удаления
     */
    @Transactional
    public void deleteTask(String taskName) {
        try {
            repository.deleteTask(taskName);
        } catch (TaskNotFound | RepositoryError err) {
            log.warn(err.getMessage(), err);
        }
    }

    /**
     * Преобразовать список задач в репозитории в строку.
     *
     * @return - строковое представление списка задач
     */
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task task : repository.getAllTasks()) {
                sb.append(task).append("\n");
            }
            return sb.toString();
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return "";
        }

    }

    /**
     * Получить список всех задач в репозитории
     *
     * @return - полный список задач
     */
    public List<Task> getAllTasks() {
        try {
            return repository.getTasksList();
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return new ArrayList<>();
        }
    }

    /**
     * Выбрать все задачи с определённым статусом
     *
     * @param status - статус искомой задачи
     * @return - список найденных задач
     */
    public List<Task> searchByStatus(TaskStatus status) {
        try {
            return repository.getTasksByStatus(status);
        } catch (TaskNotFound err) {
            return new ArrayList<>();
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return new ArrayList<>();
        }
    }

    /**
     * Искать задачи по исполнителю, владельцу и статусу
     *
     * @param ownerName    - вледелец
     * @param executorName - исполнитель
     * @param status       - статус
     * @return - список задач
     */
    public List<Task> searchBy(String ownerName, String executorName, TaskStatus status) {
        try {
            return repository.getTasksBy(ownerName, executorName, status);
        } catch (TaskNotFound err) {
            return new ArrayList<>();
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return new ArrayList<>();
        }
    }


    /**
     * Вернуть список задач отсортированный определённым образом
     *
     * @param comparator - компаратор для сортировки, например:
     *                   (o1,o2) -> -o1.getName().compareTo(o2.getName()) - отсортировать список по имени
     *                   в обратном порядке
     *                   (o1,o2) -> o1.getStatus().compareTo(o2.getStatus()) - отсортировать список
     *                   по статусу
     * @return - сортированный список
     */
    public List<Task> sortTasksBy(Comparator<Task> comparator) {
        try {
            return repository.getTasksList().stream().
                    sorted(comparator).
                    collect(Collectors.toList());
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return new ArrayList<>();
        }

    }

    public List<Task> sortListByStatus() {
        return sortTasksBy(Comparator.comparing(Task::getStatus));
    }

    /**
     * Подсчет количества задач по статусу
     * Для подсчёта задач, обращаемся напрямую к методу репозитория
     *
     * @param status - статус задачи
     * @return - количество найденных задач
     */
    public long getTaskCount(TaskStatus status) {
        try {
            return repository.getTasksByStatus(status).size();
        } catch (TaskNotFound err) {
            return 0;
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return 0;
        }
    }

    /**
     * Универсальный счетчик задач по правилу
     *
     * @param predicate - правило подсчёта задач
     * @return - количество найденных задач
     */
    public long getTaskCount(Predicate<Task> predicate) {
        try {
            return repository.getTasksList().
                    stream().
                    filter(predicate).count();
        } catch (RepositoryError err) {
            log.warn(err.getMessage(), err);
            return 0;
        }
    }

    /**
     * Записать задачи в хранилище
     * Будет выполнено только если объект реализует интерфейс RepositoryStorage
     */
    public void saveTasks() {
        if (repository instanceof RepositoryStorage) {
            try {
                ((RepositoryStorage) repository).saveTasks();
            } catch (TaskStorageError err) {
                log.warn(err.getMessage(), err);
            }
        } else {
            log.error("Класс " + repository.getClass().getSimpleName() + " не реализует " +
                    "методы класса RepositoryStorage. Сохранение невозможно");
        }
    }

    /**
     * Загрузить задачи из хранилища
     */
    public void loadTasks() {
        if (repository instanceof RepositoryStorage) {
            try {
                ((RepositoryStorage) repository).loadTasks();
            } catch (TaskStorageError err) {
                log.warn(err.getMessage(), err);
            }
        } else {
            log.error("Класс " + repository.getClass().getSimpleName() + " не реализует " +
                    "методы класса RepositoryStorage. Загрузка невозможна");
        }
    }
}
