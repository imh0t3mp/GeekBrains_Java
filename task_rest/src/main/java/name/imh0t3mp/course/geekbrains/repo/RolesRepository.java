package name.imh0t3mp.course.geekbrains.repo;

import name.imh0t3mp.course.geekbrains.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, String> {
}
