package workout.calendar.domain.dto.recode;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeTotalDto {

    private String date;

    private Integer totalWeight;

    @QueryProjection
    public RecodeTotalDto(String date, Integer totalWeight){
        this.date = date;
        this.totalWeight = totalWeight;
    }
}
