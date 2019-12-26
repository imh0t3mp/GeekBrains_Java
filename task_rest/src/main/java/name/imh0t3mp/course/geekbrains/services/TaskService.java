package name.imh0t3mp.course.geekbrains.services;

import lombok.extern.slf4j.Slf4j;
import name.imh0t3mp.course.geekbrains.entity.Task;
import name.imh0t3mp.course.geekbrains.entity.TaskStatus;
import name.imh0t3mp.course.geekbrains.repo.TaskRepository;
import name.imh0t3mp.course.geekbrains.repo.UserRepository;
import name.imh0t3mp.course.geekbrains.services.mapper.TaskMapper;
import name.imh0t3mp.geekbrains.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {

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

    public List<TaskDTO> findAll(Specification<Task> spec, Sort sort) {
        return taskMapper.toDto(taskRepository.findAll(spec, sort));
    }

    public Optional<TaskDTO> getTask(Integer id) {
        return this.taskRepository.findById(id).map(taskMapper::toDto);
    }

    @Transactional
    public TaskDTO addTask(TaskDTO taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        task.setCreatedAt(Calendar.getInstance());
        task.setStatus(TaskStatus.CREATED);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Transactional
    public Optional<TaskDTO> updateTask(Integer id, TaskDTO taskDto) {
        return taskRepository.findById(id)
                .map(taskData -> {
                    taskData.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
                    Task task = taskRepository.save(taskData);
                    return task;
                }).map(taskMapper::toDto);
    }

    @Transactional
    public Optional<ResponseEntity> removeTask(int id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                });
    }
}
