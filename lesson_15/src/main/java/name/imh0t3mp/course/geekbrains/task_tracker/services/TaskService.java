package name.imh0t3mp.course.geekbrains.task_tracker.services;

import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import name.imh0t3mp.course.geekbrains.task_tracker.repo.TaskRepository;
import name.imh0t3mp.course.geekbrains.task_tracker.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class TaskService {
    private TaskRepository taskRepository;

    private UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task getTask(Integer id) {
        return taskRepository.findById(id).get();
    }

    public List<Task> getAll() {
        return (List<Task>) taskRepository.findAll();
    }

    @Transactional
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Transactional
    public void changeTaskStatus(Integer taskId, TaskStatus status) {
        Task task = getTask(taskId);
        task.setTaskStatus(status);
        taskRepository.save(task);

    }

    public Page<Task> getTasks(Specification<Task> spec, Pageable pageable){
        return taskRepository.findAll(spec, pageable);
    }
//    public List<Task> findAllByOwner(String ownerName) {
//        return taskRepository.findTaskByOwner_Username(ownerName);
//    }
//
//    public List<Task> findAllByExecutor(String executorName) {
//        return taskRepository.findTaskByExecutor_Username(executorName);
//    }
//
//    public List<Task> findAllByStatus(TaskStatus status) {
//        return taskRepository.findTaskByStatus(status.name());
//    }


}
