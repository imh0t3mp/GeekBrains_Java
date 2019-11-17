package name.imh0t3mp.course.geekbrains.task_tracker.repository.impl;

import name.imh0t3mp.course.geekbrains.task_tracker.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.errors.TaskStorageError;
import name.imh0t3mp.course.geekbrains.task_tracker.repository.RepositoryStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TaskListAndStreamInFileReporitoryImpl extends TaskListAndStreamRepositoryImpl
        implements RepositoryStorage {
    private String filePath;
    private String fileName;

    public TaskListAndStreamInFileReporitoryImpl(String filePath, String fileName) {
        super();
        this.filePath = filePath;
        this.fileName = fileName;
    }

    /**
     * Загрузит данные из файла
     * Открывает входящий поток данных из файла и считывает из хранилища данные
     *
     * @throws TaskStorageError - если возникла ошибка при работе с хранилищем
     */
    @Override
    public void loadTasks() throws TaskStorageError {
        File inFile = new File(this.filePath, this.fileName);
        if (!inFile.exists())
            throw new TaskStorageError("Файла данных:" + inFile.getAbsolutePath() + " не найден");
        if (!inFile.canRead())
            throw new TaskStorageError("Файла данных:" + inFile.getAbsolutePath() + " не доступен" +
                    " для чтения");

        try (FileInputStream fis = new FileInputStream(inFile);
             ObjectInputStream objOut = new ObjectInputStream(fis)) {
            taskList = (List<Task>) objOut.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new TaskStorageError(e.toString());
        }
    }

    /**
     * Записать данные в файл
     *
     * @throws TaskStorageError - если возникли проблемы при работе
     */
    @Override
    public void saveTasks() throws TaskStorageError {
        File outFile = new File(this.filePath, this.fileName);
        if (outFile.exists() && !outFile.canWrite())
            throw new TaskStorageError("Файла данных:" + outFile.getAbsolutePath() + " не доступен" +
                    " для чтения");
        if (!Files.exists(Paths.get(this.filePath))) {
            try {
                Files.createDirectories(Paths.get(this.filePath));
            } catch (IOException e) {
                throw new TaskStorageError(e.toString());
            }
        }
        try (FileOutputStream fos = new FileOutputStream(this.filePath + "/" + this.fileName);
             ObjectOutputStream objOut = new ObjectOutputStream(fos)) {
            objOut.writeObject(taskList);
        } catch (IOException e) {
            throw new TaskStorageError(e.toString());
        }
    }
}
