package name.imh0t3mp.course.geekbrains.task_tracker.repo;


import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends
        JpaRepository<Task, Integer>,
        PagingAndSortingRepository<Task, Integer>,
        JpaSpecificationExecutor<Task> {
    List<Task> findTaskByOwner_Username(String ownerName);

    List<Task> findTaskByPerformer_Username(String performerName);

    List<Task> findTaskByStatus(String status);

}
