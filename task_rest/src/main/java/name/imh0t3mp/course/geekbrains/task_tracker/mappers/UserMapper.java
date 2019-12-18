package name.imh0t3mp.course.geekbrains.task_tracker.mappers;


import name.imh0t3mp.course.geekbrains.common.UserDTO;
import name.imh0t3mp.course.geekbrains.config.MapperConfig;
import name.imh0t3mp.course.geekbrains.task_tracker.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(config = MapperConfig.class)
@Component
public interface UserMapper {
    @Mappings({@Mapping(target = "id", source = "id")})
    UserDTO map(User user);

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "email", source = "email")
    })
    User map(UserDTO userDTO);

    void assignToUser(UserDTO userDTO, @MappingTarget User user);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<UserDTO> map(List<User> users);
}
