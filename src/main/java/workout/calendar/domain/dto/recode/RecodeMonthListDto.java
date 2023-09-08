package workout.calendar.domain.dto.recode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeMonthListDto {

    private Map<String, Integer> dateAndTotalWeight = new LinkedHashMap<>();

    public void setRecodeMonthDto(List<RecodeTotalDto> recodeTotalDtoList) {
        for (RecodeTotalDto recodeMonthDto : recodeTotalDtoList) {
            String date = recodeMonthDto.getDate();
            Integer totalWeight = recodeMonthDto.getTotalWeight();

            String[] parts = date.split("-");
            if (parts.length == 3) {
                date = parts[2] + "일";
            }

            if (dateAndTotalWeight.containsKey(date)) {
                Integer existingTotalWeight = dateAndTotalWeight.get(date);
                dateAndTotalWeight.put(date, existingTotalWeight + totalWeight);
            } else {
                dateAndTotalWeight.put(date, totalWeight);
            }
        }
    }

}
