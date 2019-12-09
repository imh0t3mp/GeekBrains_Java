package name.imh0t3mp.course.geekbrains.task_tracker.entity.specs;

import name.imh0t3mp.course.geekbrains.task_tracker.TaskStatus;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> statusEq(TaskStatus status) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("taskStatus"), status);
    }

    public static Specification<Task> ownerEq(String owner) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("owner").get("username"), owner);
    }

    public static Specification<Task> executorEq(String executor) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("executor").get("username"), executor);
    }
}
