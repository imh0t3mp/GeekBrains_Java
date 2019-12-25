package name.imh0t3mp.course.geekbrains.services.mapper;

import name.imh0t3mp.course.geekbrains.entity.Task;
import name.imh0t3mp.geekbrains.dto.TaskDTO;
import name.imh0t3mp.geekbrains.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {UserDTO.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mappings({
            @Mapping(target = "createdAt",
                    source = "task.createdAt",
                    dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "ownerId", source = "owner.id"),
            @Mapping(target = "performerId", source = "performer.id"),
    })
    TaskDTO toDto(Task task);

    @Mappings({
            @Mapping(target = "createdAt",
                    source = "dto.createdAt",
                    dateFormat = "yyyy-MM-dd HH:mm:ss",
                    ignore = true),
            @Mapping(source = "ownerId", target = "owner.id"),
            @Mapping(source = "performerId", target = "performer.id"),
            @Mapping(target = "id", source = "id", ignore = true),
    })
    Task toEntity(TaskDTO dto);

    default Task fromId(Integer id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }

}
