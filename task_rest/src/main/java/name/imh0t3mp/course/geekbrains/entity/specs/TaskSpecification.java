package name.imh0t3mp.course.geekbrains.entity.specs;

import name.imh0t3mp.course.geekbrains.entity.TaskStatus;
import name.imh0t3mp.course.geekbrains.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> statusEq(TaskStatus status) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> ownerEq(Integer userId) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("owner"), userId);
    }

    public static Specification<Task> performerEq(Integer userId) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("performer"), userId);
    }
}
