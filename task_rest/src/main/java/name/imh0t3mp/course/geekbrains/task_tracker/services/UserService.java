//package name.imh0t3mp.course.geekbrains.task_tracker.services;
//
//import name.imh0t3mp.course.geekbrains.task_tracker.entity.User;
//import name.imh0t3mp.course.geekbrains.task_tracker.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    private UserRepository userRepository;
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User getById(Integer id) {
//        return this.userRepository.findById(id).get();
//    }
//
//    public List<User> getAll() {
//        return (List<User>) this.userRepository.findAll();
//    }
//}
