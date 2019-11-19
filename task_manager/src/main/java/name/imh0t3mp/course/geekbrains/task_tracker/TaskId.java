package name.imh0t3mp.course.geekbrains.task_tracker;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Instant;

public class TaskId {
    private static int nextId;

    /**
     * Получить следующий id
     *
     * @return - ID
     */
    public static int getNextId() {
        if (0 == nextId)
            nextId = getStoredId() + 1;
        else
            nextId++;

        return nextId;
    }

    /**
     * Прочитать ID из файла. А если такового нет, то вернет unixtimestamp
     *
     * @return
     */
    private static int getStoredId() {
        File idFile = new File(".lastid");
        if (!idFile.exists())
            return (int) Instant.now().getEpochSecond();
        try (RandomAccessFile raf = new RandomAccessFile(idFile, "r")) {
            return raf.readInt();
        } catch (IOException e) {
            System.err.println(e);
            return (int) Instant.now().getEpochSecond();
        }
    }

    /**
     * Сбросить запись в файл
     */
    private static void flushRecord() {
        File idFile = new File(".lastid");
        if (idFile.isFile() && idFile.canWrite()) {
            try (RandomAccessFile raf = new RandomAccessFile(idFile, "rw")) {
                raf.writeInt(nextId);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
