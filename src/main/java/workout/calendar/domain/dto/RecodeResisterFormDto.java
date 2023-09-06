package workout.calendar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import workout.calendar.domain.entity.Performance;
import workout.calendar.domain.entity.Recode;
import workout.calendar.domain.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeResisterFormDto {

    @NotEmpty(message = "제목은 필수 입니다")
    @Size(max = 10, message = "제목은 10글자 이하만 가능합니다")
    private String title;

    @NotEmpty(message = "날짜는 필수 입니다")
    private String date;

    private User user;

    private List<PerformanceResisterFormDto> performanceList;

}
