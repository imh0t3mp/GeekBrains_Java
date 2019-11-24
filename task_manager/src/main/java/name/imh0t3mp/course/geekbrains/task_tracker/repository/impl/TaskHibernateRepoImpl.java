package name.imh0t3mp.course.geekbrains.task_tracker.repository.impl;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.errors.RepositoryError;
import name.imh0t3mp.course.geekbrains.errors.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.errors.TaskNotFound;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.TaskRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TaskHibernateRepoImpl implements TaskRepository {
    private static Logger log = Logger.getLogger(TaskHibernateRepoImpl.class);
    private SessionFactory factory;

    public TaskHibernateRepoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Добавить задачу в хранилище
     *
     * @param task - задача для добавления
     * @throws TaskAlreadyExists - если такая запись уже есть в базе
     */
    @Override
    public void addTask(Task task) throws TaskAlreadyExists, RepositoryError {
        try (Session session = factory.openSession()) {
            if (hasTask(task))
                throw new TaskAlreadyExists("Запись " + task + " уже есть в базе");
            saveOrUpdate(session, task);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Удалить запись из базы
     *
     * @param task - запись
     * @throws TaskNotFound - задача не найдена
     */
    @Override
    public void deleteTask(Task task) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            if (!hasTask(task))
                throw new TaskNotFound("Запись " + task + " не найдена в базе");
            Transaction transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Удалить запись по ID
     *
     * @param taskId - ID записи
     * @throws TaskNotFound - запись не найдена
     */
    @Override
    public void deleteTask(int taskId) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            if (!hasTask(taskId))
                throw new TaskNotFound("Задача с ID:" + taskId + " не найдена");
            Transaction transaction = session.beginTransaction();
            Task task = getTask(taskId);
            deleteTask(task);
            transaction.commit();
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Удалить запись по наименованию задачи
     *
     * @param taskName - название задачи
     * @throws TaskNotFound - задача не найдена
     */
    @Override
    public void deleteTask(String taskName) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            if (!hasTask(taskName))
                throw new TaskNotFound("Задача с NAME:" + taskName + " не найдена в базе");
            Transaction transaction = session.beginTransaction();
            Task task = getTask(taskName);
            session.delete(task);
            transaction.commit();
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить задачу по ID
     *
     * @param taskId - ID задачи
     * @return - найденная задача
     * @throws TaskNotFound - задача не найдена
     */
    @Override
    public Task getTask(int taskId) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);

            transaction.commit();
            if (null == task)
                throw new TaskNotFound("Задача с ID:" + taskId + " не найдена в базе");
            return task;
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить задачу по имени
     *
     * @param taskName - имя задачи
     * @return - найденная запись
     * @throws TaskNotFound - задача не найдена
     */
    @Override
    public Task getTask(String taskName) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.
                    createQuery("SELECT t FROM Task t WHERE t.name = :task_name",
                            Task.class);
            query.setParameter("task_name", taskName);
            Task task = (Task) query.uniqueResult();
            transaction.commit();
            if (null == task)
                throw new TaskNotFound("Задача NAME:" + taskName + " не найдена");
            return task;
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Обновить статус задачи
     *
     * @param taskId - ID задачи
     * @param status - новый статус задачи
     * @throws TaskNotFound - задача не найдена
     */
    @Override
    public void changeTaskStatus(int taskId, TaskStatus status) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            Task task = getTask(taskId);
            task.setStatus(status);
            saveOrUpdate(session, task);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }


    /**
     * Обновить статус задачи
     *
     * @param taskName - имя задачи
     * @param status   - нвый статус у задачи
     * @throws TaskNotFound - задача не найдена в базе
     */
    @Override
    public void changeTaskStatus(String taskName, TaskStatus status) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            Task task = getTask(taskName);
            task.setStatus(status);
            saveOrUpdate(session, task);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Есть такая запись в базе ?
     *
     * @param task - запись о задаче
     * @return TRUE|FALSE
     */
    @Override
    public boolean hasTask(Task task) {
        return hasTask(task.getId());
    }

    /**
     * Есть такая запись в базе по ID
     *
     * @param taskId - ID задачи
     * @return - TRUE|FALSE
     */
    @Override
    public boolean hasTask(int taskId) {
        try {
            getTask(taskId);
            return true;
        } catch (TaskNotFound e) {
            return false;
        } catch (RepositoryError e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * Есть такая запись в базе по имени задачи
     *
     * @param taskName - имя задачи
     * @return - TRUE|FALSE
     */
    @Override
    public boolean hasTask(String taskName) {
        try {
            getTask(taskName);
            return true;
        } catch (TaskNotFound e) {
            return false;
        } catch (RepositoryError e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * Получить список задач
     *
     * @return - список задач
     */
    @Override
    public Task[] getAllTasks() throws RepositoryError {
        return getTasksList().toArray(Task[]::new);
    }

    /**
     * Получит список задач
     *
     * @return - список задач
     */
    @Override
    public List<Task> getTasksList() throws RepositoryError {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Task", Task.class);
            List<Task> tasks = query.getResultList();
            transaction.commit();
            return tasks;
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить список задач с определённым статусом
     *
     * @param status - статус задачи
     * @return - список найденных задач
     * @throws TaskNotFound - задачи с таким статусом не найдены в базе
     */
    @Override
    public List<Task> getTasksByStatus(TaskStatus status) throws TaskNotFound, RepositoryError {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.
                    createQuery("SELECT t FROM Task t WHERE t.taskStatus=:status ", Task.class);
            query.setParameter("status", status);
            List<Task> tasks = query.getResultList();
            transaction.commit();
            if (0 == tasks.size())
                throw new TaskNotFound("Задачи со статусом " + status + " не найдены ");
            return tasks;
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            throw new RepositoryError(e.getMessage());
        }
    }

    /* ****************************************************************************************** */

    /**
     * Вспомогательный метод для обновления/добавления задачи.
     *
     * @param session - сессия
     * @param task    - задача
     */
    private void saveOrUpdate(Session session, Task task) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(task);
        transaction.commit();
    }

}
