package name.imh0t3mp.course.geekbrains.task_tracker.repo;


import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Integer>,
        JpaSpecificationExecutor<Task> {
//    List<Task> findTaskByOwner_Username(String ownerName);
//
//    List<Task> findTaskByExecutor_Username(String ownerName);
//
//    List<Task> findTaskByStatus(String status);

//    void deleteById(Integer id);

}
