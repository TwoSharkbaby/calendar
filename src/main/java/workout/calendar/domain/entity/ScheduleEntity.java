package workout.calendar.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
//@NoArgsConstructor
//@ToString
@Data
@NoArgsConstructor
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    private Long num;
    private String subject;
    private String scheduleDesc;
    private Date date;
    private String color;

}
