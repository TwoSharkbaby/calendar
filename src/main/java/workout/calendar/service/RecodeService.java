package workout.calendar.service;

import workout.calendar.domain.dto.RecodeDto;
import workout.calendar.domain.dto.RecodeModifyFormDto;
import workout.calendar.domain.dto.RecodeResisterFormDto;
import workout.calendar.domain.entity.Recode;

import java.util.List;

public interface RecodeService {

    Long createRecode(RecodeResisterFormDto recodeResisterFormDto);

    List<Recode> getRecodeAll();

    Long modifyRecode(RecodeModifyFormDto recodeModifyFormDto);

    RecodeDto getRecode(Long id);

    void deleteRecode(Long id);
}
