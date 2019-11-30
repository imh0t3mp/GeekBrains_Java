package name.imh0t3mp.course.geekbrains.task_tracker.repository.impl;

import name.imh0t3mp.course.geekbrains.exceptions.RepositoryError;
import name.imh0t3mp.course.geekbrains.exceptions.TaskAlreadyExists;
import name.imh0t3mp.course.geekbrains.exceptions.TaskNotFound;
import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDatabaseRepoImpl implements TaskRepository {

    private Connection connection;

    /**
     * Конструктор класса
     *
     * @param con - соединение с БД
     */
    public TaskDatabaseRepoImpl(Connection con) {
        this.connection = con;
    }

    /**
     * Инициализатор соединения с БД
     *
     * @param connection - соединение
     */
    @Autowired
    @Qualifier(value = "jdbcConnection")
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Добавить задачу в базу
     *
     * @param task - задача для добавления
     * @throws TaskAlreadyExists - задача уже есть в базе
     * @throws RepositoryError   - ошибка ри работе с репозиторием
     */
    @Override
    public void addTask(Task task) throws TaskAlreadyExists, RepositoryError {
        String sql = "INSERT INTO task_repo (id,task_name,owner_name,executor_name," +
                "description,task_status) VALUES (?,?,?,?,?,?)";
        if (hasTask(task.getName()) || hasTask(task.getId()))
            throw new TaskAlreadyExists("Задача: " + task + " уже есть в базе");
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, task.getId());
            pst.setString(2, task.getName());
            pst.setString(3, task.getOwnerName());
            pst.setString(4, task.getExecutorName());
            pst.setString(5, task.getDescription());
            pst.setString(6, task.getStatus().name());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }

    }

    /**
     * Удалить задачу
     *
     * @param task - задача для удаления
     * @throws TaskNotFound    - запись не найдена
     * @throws RepositoryError - ошибка при работе с репозиторием
     */
    @Override
    public void deleteTask(Task task) throws TaskNotFound, RepositoryError {
        if (!hasTask(task)) throw new TaskNotFound("Задача " + task + " не найдена в базе");
        deleteTask(task.getId());

    }

    /**
     * Удалить задачу по ID
     *
     * @param taskId - ID задачи
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - возникла ошибка при работе с репозтиторием
     */
    @Override
    public void deleteTask(int taskId) throws TaskNotFound, RepositoryError {
        if (!hasTask(taskId))
            throw new TaskNotFound("Задача c ID:" + taskId + " не найдена в базе");
        String sql = "DELETE FROM task_repo WHERE id=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, taskId);
            stm.executeUpdate();
        } catch (SQLException err) {
            throw new RepositoryError(err.getMessage());
        }
    }

    /**
     * Удалиьт задачу по её имени
     *
     * @param taskName - имя назадчи
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - возникла ошибка при работе с репозиторием
     */
    @Override
    public void deleteTask(String taskName) throws TaskNotFound, RepositoryError {
        if (!hasTask(taskName))
            throw new TaskNotFound("Задача c NAME:" + taskName + " не найдена в базе");
        String sql = "DELETE FROM task_repo WHERE task_name=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, taskName);
            stm.executeUpdate();
        } catch (SQLException err) {
            throw new RepositoryError(err.getMessage());
        }
    }

    /**
     * Получить задачу по ID
     *
     * @param taskId - ID задачи
     * @return - найденная задача
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - ошибка при работе с репозиторием
     */
    @Override
    public Task getTask(int taskId) throws TaskNotFound, RepositoryError {
        String sql = "SELECT * FROM task_repo WHERE id=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, taskId);
            ResultSet rs = stm.executeQuery();
            List<Task> taskList = getListFromStatement(stm);

            if (0 == taskList.size())
                throw new TaskNotFound("Задача с ID:" + taskId + " не найдена в " +
                        "базе");
            return taskList.get(0);
        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить задачу по её имени
     *
     * @param taskName - имя задачи
     * @return - найденная задача
     * @throws TaskNotFound    - задача не найдена
     * @throws RepositoryError - возникла ошибка при работе с репозиторием
     */
    @Override
    public Task getTask(String taskName) throws TaskNotFound, RepositoryError {
        String sql = "SELECT * FROM task_repo WHERE task_name=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, taskName);
            List<Task> taskList = getListFromStatement(stm);

            if (0 == taskList.size())
                throw new TaskNotFound("Задача с NAME:" + taskName + " не найдена в " +
                        "базе");
            return taskList.get(0);
        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Изменить статус задачи по ID
     *
     * @param taskId - ID задачи
     * @param status - статус задачи
     * @throws TaskNotFound
     * @throws RepositoryError
     */
    @Override
    public void changeTaskStatus(int taskId, TaskStatus status) throws TaskNotFound, RepositoryError {
        if (!hasTask(taskId))
            throw new TaskNotFound("Задача с ID:" + taskId + " не найдена в базе");
        String sql = "UPDATE task_repo SET task_status = ? WHERE id=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, status.name());
            stm.setInt(2, taskId);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Обновить статус задачи по  её имени
     *
     * @param taskName - имя задачи
     * @param status   - статус задачи
     * @throws TaskNotFound
     * @throws RepositoryError
     */
    @Override
    public void changeTaskStatus(String taskName, TaskStatus status) throws TaskNotFound, RepositoryError {
        if (!hasTask(taskName))
            throw new TaskNotFound("Задача с NAME:" + taskName + " не найдена в базе");
        String sql = "UPDATE task_repo SET task_status = ? WHERE task_name=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, status.name());
            stm.setString(2, taskName);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Проверить, есть ли задача в репозитории
     *
     * @param task - искомая задача
     * @return
     */
    @Override
    public boolean hasTask(Task task) {
        try {
            getTask(task.getId());
            return true;
        } catch (TaskNotFound | RepositoryError e) {
            return false;
        }
    }

    /**
     * Есть задача по ID
     *
     * @param taskId
     * @return
     */
    @Override
    public boolean hasTask(int taskId) {
        try {
            getTask(taskId);
            return true;
        } catch (TaskNotFound | RepositoryError e) {
            return false;
        }
    }

    /**
     * Есть задача по имени
     *
     * @param taskName
     * @return
     */
    @Override
    public boolean hasTask(String taskName) {
        try {
            getTask(taskName);
            return true;
        } catch (TaskNotFound | RepositoryError e) {
            return false;
        }
    }

    /**
     * Вернуть массив со списком задач
     *
     * @return
     * @throws RepositoryError
     */
    @Override
    public Task[] getAllTasks() throws RepositoryError {
        return (Task[]) getTasksList().toArray();
    }

    /**
     * Получить список задач
     *
     * @return
     * @throws RepositoryError - возникла ошибка при работе с репозиторием
     */
    @Override
    public List<Task> getTasksList() throws RepositoryError {
        String sql = "SELECT * FROM task_repo ORDER  BY id";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            return getListFromStatement(stm);

        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить все задачи со статусом
     *
     * @param status - статус задачи
     * @return
     * @throws TaskNotFound
     * @throws RepositoryError
     */
    @Override
    public List<Task> getTasksByStatus(TaskStatus status) throws TaskNotFound, RepositoryError {
        String sql = "SELECT * FROM task_repo WHERE task_status=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, status.name());
            List<Task> taskList = getListFromStatement(stm);
            if (0 == taskList.size())
                throw new TaskNotFound("Задачи со статусом:" + status + " не найдена в " +
                        "базе");
            return taskList;
        } catch (SQLException e) {
            throw new RepositoryError(e.getMessage());
        }
    }

    /**
     * Получить список задач из предподготовленногоззапроса
     *
     * @param stm
     * @return - список задач
     * @throws SQLException - если возникла ошибка
     */
    private List<Task> getListFromStatement(PreparedStatement stm) throws SQLException {
        ResultSet rs = stm.executeQuery();
        List<Task> taskList = new ArrayList<>();
        while (rs.next()) {
            taskList.add(new Task(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    TaskStatus.valueOf(rs.getString(6))
            ));
        }
        stm.close();
        return taskList;
    }
}
