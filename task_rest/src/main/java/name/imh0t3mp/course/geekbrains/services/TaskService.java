package name.imh0t3mp.course.geekbrains.services;

import name.imh0t3mp.course.geekbrains.repo.TaskRepository;
import name.imh0t3mp.course.geekbrains.repo.UserRepository;
import name.imh0t3mp.course.geekbrains.services.mapper.TaskMapper;
import name.imh0t3mp.geekbrains.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

public class TaskService {

    private final static Logger log = LoggerFactory.getLogger(TaskService.class);


    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Transactional(readOnly = true)
    public Optional<TaskDTO> getById(Integer id) {
        log.debug("Request to get Task : {}", id);
        return taskRepository.findById(id).map(taskMapper::toDto);
    }

//    public Task getTask(Integer id) {
//        return taskRepository.findById(id).get();
//    }
//
//    public List<Task> getAll() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
//        return (List<Task>) taskRepository.findAll(sort);
//    }
//
//    @Transactional
//    public void addTask(Task task) {
//        taskRepository.save(task);
//    }
//
//    @Transactional
//    public void deleteTask(Task task) {
//        taskRepository.delete(task);
//    }
//
//    @Transactional
//    public void changeTaskStatus(Integer taskId, TaskStatus status) {
//        Task task = getTask(taskId);
//        task.setStatus(status);
//        taskRepository.save(task);
//
//    }
//
//    public Page<Task> getTasks(Specification<Task> spec, Pageable pageable) {
//        return taskRepository.findAll(spec, pageable);
//    }
}
