package workout.calendar.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import workout.calendar.domain.dto.RecodeModifyFormDto;
import workout.calendar.domain.dto.RecodeResisterFormDto;

import javax.persistence.*;

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

    @Column(length = 100)
    private String memo;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public void setRecode(RecodeResisterFormDto recodeResisterFormDto) {
        this.title = recodeResisterFormDto.getTitle();
        this.memo = recodeResisterFormDto.getMemo();
        this.date = recodeResisterFormDto.getDate();
        this.user = recodeResisterFormDto.getUser();
    }

    public void modifyRecode(RecodeModifyFormDto recodeModifyFormDto) {
        this.title = recodeModifyFormDto.getTitle();
        this.memo = recodeModifyFormDto.getMemo();
        this.date = recodeModifyFormDto.getDate();
    }
}
