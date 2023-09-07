package workout.calendar.domain.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workout.calendar.domain.RoleType;
import workout.calendar.domain.entity.User;

@NoArgsConstructor
@Getter
@Setter
public class UserRoleFormDto {

    private Long id;
    private String username;
    private String nickname;
    private RoleType role;

    public UserRoleFormDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }

}
