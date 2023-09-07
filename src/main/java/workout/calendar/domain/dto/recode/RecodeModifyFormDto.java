package workout.calendar.domain.dto.recode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import workout.calendar.domain.dto.performance.PerformanceDto;
import workout.calendar.domain.entity.Recode;
import workout.calendar.domain.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeModifyFormDto {

    private Long id;

    @NotEmpty(message = "제목은 필수 입니다")
    @Size(max = 10, message = "제목은 10글자 이하만 가능합니다")
    private String title;

    @NotEmpty(message = "날짜는 필수 입니다")
    private String date;

    private User user;

    private List<PerformanceDto> performanceDto;

    public void setRecodeDto(Recode recode) {
        this.id = recode.getId();
        this.title = recode.getTitle();
        this.date = recode.getDate();
        this.performanceDto = recode.getPerformance().stream().map(performance -> {
            PerformanceDto performanceDto1 = new PerformanceDto();
            performanceDto1.setPerformanceDto(performance);
            return performanceDto1;
        }).collect(Collectors.toList());
    }
}
