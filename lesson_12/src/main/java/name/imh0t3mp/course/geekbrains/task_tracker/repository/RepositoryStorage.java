package name.imh0t3mp.course.geekbrains.task_tracker.repository;

import name.imh0t3mp.course.geekbrains.errors.TaskStorageError;

public interface RepositoryStorage {

    void loadTasks() throws TaskStorageError;

    void saveTasks() throws TaskStorageError;
}
