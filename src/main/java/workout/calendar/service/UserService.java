package workout.calendar.service;

import workout.calendar.domain.dto.FormLoginUserDto;
import workout.calendar.domain.dto.userRoleDto;

import java.util.List;

public interface UserService {

    Long register(FormLoginUserDto formLoginUserDto);

    List<userRoleDto> getUsers(String username);

}
