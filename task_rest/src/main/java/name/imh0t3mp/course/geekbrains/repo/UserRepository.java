package name.imh0t3mp.course.geekbrains.repo;

import name.imh0t3mp.course.geekbrains.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>,
        JpaRepository<User, Integer> {



    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByUsernameIgnoreCase(String username);
}