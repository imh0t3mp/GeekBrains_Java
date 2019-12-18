package name.imh0t3mp.course.geekbrains.task_tracker.services;

import name.imh0t3mp.course.geekbrains.common.UserDTO;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.User;
import name.imh0t3mp.course.geekbrains.task_tracker.mappers.UserMapper;
import name.imh0t3mp.course.geekbrains.task_tracker.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public Optional<UserDTO> getById(Integer id) {
        return userRepository.findById(id).map(userMapper::map);
    }

    public List<UserDTO> getAll(Sort sort) {
        List<User> users = userRepository.findAll();
        System.out.println(users.toString());
        return userMapper.map(users);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
