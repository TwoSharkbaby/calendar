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
public class RecodeDto {

    private Long id;

    @Size(max = 100, message = "메모는 100글자 이하만 가능합니다")
    private String memo;

}
