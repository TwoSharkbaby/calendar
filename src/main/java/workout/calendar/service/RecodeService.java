package workout.calendar.service;

import workout.calendar.domain.dto.recode.RecodeDto;
import workout.calendar.domain.dto.recode.RecodeModifyFormDto;
import workout.calendar.domain.dto.recode.RecodeResisterFormDto;

import java.util.List;

public interface RecodeService {

    Long createRecode(RecodeResisterFormDto recodeResisterFormDto);

    List<RecodeDto> getRecodeAll();

    Long modifyRecode(RecodeModifyFormDto recodeModifyFormDto);

    RecodeModifyFormDto getRecode(Long id);

    void deleteRecode(Long id);
}
