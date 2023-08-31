package workout.calendar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import workout.calendar.domain.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserModifyFormDto {

    private Long id;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    @Size(min = 5, max = 15, message = "비밀번호는 5글자 이상 15글자 이하만 가능합니다")
    private String password;

    @NotEmpty(message = "닉네임은 필수 입니다")
    @Size(min = 3, max = 15, message = "닉네임은는 2글자 이상 15글자 이하만 가능합니다")
    private String nickname;

    @NotEmpty(message = "이메일은 필수 입니다")
    @Email(message = "올바르지 않는 이메일 형식 입니다")
    private String email;

    public UserModifyFormDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
