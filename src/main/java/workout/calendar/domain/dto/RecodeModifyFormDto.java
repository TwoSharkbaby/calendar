package workout.calendar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import workout.calendar.domain.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeModifyFormDto {

    private Long id;

    @NotEmpty(message = "제목은 필수 입니다")
    @Size(max = 10, message = "제목은 10글자 이하만 가능합니다")
    private String title;

    @Size(max = 100, message = "메모는 100글자 이하만 가능합니다")
    private String memo;

    @NotEmpty(message = "날짜는 필수 입니다")
    private String date;

    private User user;
}
