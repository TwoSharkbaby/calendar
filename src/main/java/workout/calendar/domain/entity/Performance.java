package workout.calendar.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import workout.calendar.domain.dto.PerformanceResisterFormDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long id;

    private String type;

    private Integer weight;

    private Integer rep;

    private Integer setCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recodeId")
    private Recode recode;

    public void setRecode(Recode recode) {
        this.recode = recode;
    }

    public void setPerformance(PerformanceResisterFormDto performanceResisterFormDto){
        this.type = performanceResisterFormDto.getType();
        this.weight = performanceResisterFormDto.getWeight();
        this.rep = performanceResisterFormDto.getRep();
        this.setCount = performanceResisterFormDto.getSetCount();
    }
}
