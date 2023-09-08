package workout.calendar.repository;

import workout.calendar.domain.dto.recode.RecodeTotalDto;

import java.util.List;

public interface RecodeRepositoryCustom {

    List<RecodeTotalDto> getTotalWeight(Long id, String startDateStr, String endDateStr);

}
