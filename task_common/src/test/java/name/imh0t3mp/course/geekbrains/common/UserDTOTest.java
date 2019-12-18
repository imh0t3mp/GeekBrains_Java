package name.imh0t3mp.course.geekbrains.common;

import name.imh0t3mp.course.geekbrains.task_tracker.entity.User;
import org.junit.Test;
import org.modelmapper.ModelMapper;

class UserDTOTest {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkExamMapping() {
        UserDTO created = new UserDTO();
        created.setUsername("user");
        created.setFirstName("I'm");
        created.setLastname("User");
        created.setEmail("user@localhost");

        User user = modelMapper.map(created, User.class);

        assert user.getUsername().equals(created.getUsername());
    }

}