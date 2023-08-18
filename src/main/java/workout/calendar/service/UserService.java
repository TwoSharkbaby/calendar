package workout.calendar.service;

import workout.calendar.domain.dto.FormLoginUserDto;

public interface UserService {

    Long register(FormLoginUserDto formLoginUserDto);

}
