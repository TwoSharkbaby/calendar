package workout.calendar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workout.calendar.domain.RoleType;

@NoArgsConstructor
@Getter
@Setter
public class userRoleDto {

    private Long id;
    private String username;
    private String nickname;
    private RoleType role;

}
