package workout.calendar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import workout.calendar.domain.dto.UserModifyFormDto;
import workout.calendar.domain.dto.UserResisterFormDto;
import workout.calendar.domain.dto.UserRoleFormDto;

public interface UserService {

    Long createUser(UserResisterFormDto userResisterFormDto);

    Page<UserRoleFormDto> getUsers(String cat, String info, Pageable pageable);

    UserRoleFormDto getUserRoleForm(Long id);

    UserModifyFormDto getUserModifyForm(Long id);

    Long modifyRole(UserRoleFormDto userRoleFormDto);

    void deleteUser(Long id);

    Long modifyUser(UserModifyFormDto userModifyFormDto);
}
