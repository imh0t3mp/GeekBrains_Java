package name.imh0t3mp.course.geekbrains.task_tracker.repository.impl;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.RepositoryError;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskNotFound;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TaskHibernateRepoImpl implements TaskRepository {

    private SessionFactory factory;

    public TaskHibernateRepoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Добавить задачу в хранилище
     *
     * @param task - задача для добавления
     * @throws TaskAlreadyExists - если такая запись уже есть в базе
     * @throws RepositoryError   - возникла ошибка при работе
     */
    @Override
    public void addTask(Task task) throws TaskAlreadyExists, RepositoryError {
        try (Session session = factory.getCurrentSession()) {
            if (hasTask(task))
                throw new TaskAlreadyExists("Запись " + task + " уже есть в базе");
            Transaction transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            throw new RepositoryError(e.getMessage());
        }

    }

    /**
     * Удалить запись из базы
     *
     * @param task - запись
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - ошибка при работе с репозиторием
     */
    @Override
    public void deleteTask(Task task) throws TaskNotFound, RepositoryError {
        try (Session session = factory.getCurrentSession()) {
            if (!hasTask(task))
                throw new TaskNotFound("Запись " + task + " не найдена в базе");
            Transaction transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        } catch (Exception e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Удалить запись по ID
     *
     * @param taskId - ID записи
     * @throws TaskNotFound    - запись не найдена
     * @throws RepositoryError - ошибка в репозитории
     */
    @Override
    public void deleteTask(int taskId) throws TaskNotFound, RepositoryError {
        try (Session session = factory.getCurrentSession()) {
            if (!hasTask(taskId))
                throw new TaskNotFound("Задача с ID:" + taskId + " не найдена");
            Transaction transaction = session.beginTransaction();
            Task task = getTask(taskId);
            deleteTask(task);
            transaction.commit();
        } catch (Exception e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Удалить запись по наименованию задачи
     *
     * @param taskName - название задачи
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - ошибка при работе с репозиторием
     */
    @Override
    public void deleteTask(String taskName) throws TaskNotFound, RepositoryError {
        try (Session session = factory.getCurrentSession()) {
            if (!hasTask(taskName))
                throw new TaskNotFound("Задача с NAME:" + taskName + " не найдена в базе");
            Transaction transaction = session.beginTransaction();
            Task task = getTask(taskName);
            session.delete(task);
            transaction.commit();
        } catch (Exception e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить задачу по ID
     *
     * @param taskId - ID задачи
     * @return - найденная задача
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - ошибка при работе
     */
    @Override
    public Task getTask(int taskId) throws TaskNotFound, RepositoryError {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            transaction.commit();
            if (null == task)
                throw new TaskNotFound("Задача с ID:" + taskId + " не найдена в базе");
            return task;
        } catch (Exception e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    @Override
    public Task getTask(String taskName) throws TaskNotFound, RepositoryError {
        return null;
    }

    @Override
    public void changeTaskStatus(int taskId, TaskStatus status) throws TaskNotFound, RepositoryError {

    }

    @Override
    public void changeTaskStatus(String taskName, TaskStatus status) throws TaskNotFound, RepositoryError {

    }

    @Override
    public boolean hasTask(Task task) {
        return false;
    }

    @Override
    public boolean hasTask(int taskId) {
        return false;
    }

    @Override
    public boolean hasTask(String taskName) {
        return false;
    }

    @Override
    public Task[] getAllTasks() throws RepositoryError {
        return new Task[0];
    }

    @Override
    public List<Task> getTasksList() throws RepositoryError {
        return null;
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) throws TaskNotFound, RepositoryError {
        return null;
    }
}
