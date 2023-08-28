package workout.calendar.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import workout.calendar.domain.RoleType;
import workout.calendar.domain.dto.UserModifyFormDto;
import workout.calendar.domain.dto.UserResisterFormDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 200, unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 16, unique = true)
    private String nickname;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(length = 50)
    private String provider;

    @Column(length = 100)
    private String providerId;

    public void setUser(UserResisterFormDto userResisterFormDto) {
        this.username = userResisterFormDto.getUsername();
        this.password = userResisterFormDto.getPassword();
        this.nickname = userResisterFormDto.getNickname();
        this.email = userResisterFormDto.getEmail();
        this.role = RoleType.ROLE_USER;
    }

    public void changeRole(RoleType role){
        this.role = role;
    }

    public void changeInfo(UserModifyFormDto userModifyFormDto) {
        this.password = userModifyFormDto.getPassword();
        this.nickname = userModifyFormDto.getNickname();
        this.email = userModifyFormDto.getEmail();
    }
}