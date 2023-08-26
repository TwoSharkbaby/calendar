package workout.calendar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.dto.FormLoginUserDto;
import workout.calendar.domain.dto.UserRoleDto;

public interface UserService {

    Long register(FormLoginUserDto formLoginUserDto);

    Page<UserRoleDto> getUsers(String cat, String info, Pageable pageable);

}
