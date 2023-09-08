package workout.calendar.domain.dto.recode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecodeYearListDto {

    private Map<String, Double> dateAndTotalWeight = new LinkedHashMap<>();

    public void setRecodeYearDto(List<RecodeTotalDto> yearTotalWeight) {
        for (RecodeTotalDto recodeYearDto : yearTotalWeight) {
            String date = recodeYearDto.getDate();
            Integer totalWeightKg = recodeYearDto.getTotalWeight();

            String[] parts = date.split("-");
            if (parts.length == 3) {
                date = parts[1] + "월";
            }

            if (dateAndTotalWeight.containsKey(date)) {
                Double existingTotalWeightT = dateAndTotalWeight.get(date);
                double totalWeightT = existingTotalWeightT + (totalWeightKg / 1000.0);
                totalWeightT = Math.round(totalWeightT * 100) / 100.0;
                dateAndTotalWeight.put(date, totalWeightT);
            } else {
                double totalWeightT = totalWeightKg / 1000.0;
                totalWeightT = Math.round(totalWeightT * 100) / 100.0;
                dateAndTotalWeight.put(date, totalWeightT);
            }
        }
    }

}
