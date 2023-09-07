package workout.calendar.domain.dto.performance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PerformanceResisterFormDto {

    private String type;

    private Integer weight;

    private Integer rep;

    private Integer setCount;
}
