package name.imh0t3mp.course.geekbrains.services.mapper;

import name.imh0t3mp.course.geekbrains.entity.Task;
import name.imh0t3mp.course.geekbrains.entity.User;
import name.imh0t3mp.geekbrains.dto.TaskDTO;
import name.imh0t3mp.geekbrains.dto.UserDTO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//@Mapper
public interface TaskMapper { //} extends EntityMapper<TaskDTO, Task> {

    @Mappings({
            @Mapping(target = "createdAt",
                    source = "task.createdAt",
                    dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    TaskDTO toDto(Task task);

    @Mappings({
            @Mapping(target = "createdAt",
                    source = "dto.createdAt",
                    dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    Task toEntity(TaskDTO dto);

    UserDTO toOwnerDto(User owner);

    User toOwner(UserDTO owner);

    UserDTO toPerformerDto(User performer);

    User toPerformer(UserDTO performer);

    default Task fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }

}
