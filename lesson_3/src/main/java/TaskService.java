public interface TaskService {
    public void addTask(Task task);

    public void deleteTask(Task task);

    public void deleteTask(int id);

    public void deleteTask(String taskName);

    public Task getTask(int id);

    public Task getTask(String taskName);

    public String tasksToString();
}
