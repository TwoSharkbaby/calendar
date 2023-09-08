package workout.calendar.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import workout.calendar.domain.dto.performance.PerformanceDto;
import workout.calendar.domain.dto.recode.RecodeModifyFormDto;
import workout.calendar.domain.dto.recode.RecodeResisterFormDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Recode extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recode_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "recode", cascade = CascadeType.ALL)
    private List<Performance> performance = new ArrayList<>();

    private Integer totalWeight;

    public void setRecode(RecodeResisterFormDto recodeResisterFormDto) {
        this.title = recodeResisterFormDto.getTitle();
        this.date = recodeResisterFormDto.getDate();
        this.user = recodeResisterFormDto.getUser();
        this.totalWeight = recodeResisterFormDto.getPerformanceList()
                .stream()
                .mapToInt(dto -> dto.getWeight() * dto.getRep() * dto.getSetCount())
                .sum();
        this.performance = recodeResisterFormDto.getPerformanceList()
                .stream().map(dto -> {
                    Performance performance1 = new Performance();
                    performance1.setPerformance(dto);
                    performance1.setRecode(this);
                    return performance1;
                }).collect(Collectors.toList());
    }

    public void modifyRecode(RecodeModifyFormDto recodeModifyFormDto) {
        this.title = recodeModifyFormDto.getTitle();
        this.date = recodeModifyFormDto.getDate();
        this.totalWeight = recodeModifyFormDto.getPerformanceDto()
                .stream()
                .mapToInt(dto -> dto.getWeight() * dto.getRep() * dto.getSetCount())
                .sum();
        this.performance = recodeModifyFormDto.getPerformanceDto()
                .stream().map(dto -> {
                    Performance performance1 = new Performance();
                    performance1.setPerformanceDto(dto);
                    performance1.setRecode(this);
                    return performance1;
                }).collect(Collectors.toList());
    }

}
