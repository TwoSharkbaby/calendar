package workout.calendar.domain.dto.recode;

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
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeDto {

    private Long id;

    private String title;

    private String date;

    public void setRecode(Recode recode) {
        this.id = recode.getId();
        this.title = recode.getTitle();
        this.date = recode.getDate();
    }
}
