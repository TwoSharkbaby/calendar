package workout.calendar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.dto.FormLoginUserDto;
import workout.calendar.domain.dto.UserRoleDto;
import workout.calendar.domain.entity.User;

public interface UserService {

    Long register(FormLoginUserDto formLoginUserDto);

    Page<UserRoleDto> getUsers(String cat, String info, Pageable pageable);

    UserRoleDto getUser(Long id);

    Long modifyRole(UserRoleDto userRoleDto);

    void deleteUser(Long id);
}
