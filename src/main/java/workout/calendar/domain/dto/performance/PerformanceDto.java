package workout.calendar.domain.dto.performance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import workout.calendar.domain.entity.Performance;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PerformanceDto {

    private Long id;

    private String type;

    private Integer weight;

    private Integer rep;

    private Integer setCount;

    public void setPerformanceDto(Performance performance1) {
        this.id = performance1.getId();
        this.type = performance1.getType();
        this.weight = performance1.getWeight();
        this.rep = performance1.getRep();
        this.setCount = performance1.getSetCount();
    }
}
