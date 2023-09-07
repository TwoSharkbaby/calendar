package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.auth.PrincipalDetails;
import workout.calendar.domain.dto.recode.RecodeDto;
import workout.calendar.domain.dto.recode.RecodeModifyFormDto;
import workout.calendar.domain.dto.recode.RecodeResisterFormDto;
import workout.calendar.domain.entity.Performance;
import workout.calendar.domain.entity.Recode;
import workout.calendar.repository.PerformanceRepository;
import workout.calendar.repository.RecodeRepository;
import workout.calendar.service.RecodeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecodeServiceImpl implements RecodeService {

    private final RecodeRepository recodeRepository;
    private final PerformanceRepository performanceRepository;
    @Transactional
    @Override
    public Long createRecode(RecodeResisterFormDto recodeResisterFormDto) {
        Recode recode = new Recode();
        recode.setRecode(recodeResisterFormDto);
        Long id = recodeRepository.save(recode).getId();
        return id;
    }

    @Override
    public List<RecodeDto> getRecodeAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long id = principalDetails.getUser().getId();
        List<Recode> recodeList = recodeRepository.findByUserId(id);
        List<RecodeDto> recodeDtoList = recodeList.stream().map(recode -> {
            RecodeDto recodeDto = new RecodeDto();
            recodeDto.setRecode(recode);
            return recodeDto;
        }).collect(Collectors.toList());
        return recodeDtoList;
    }

    @Transactional
    @Override
    public Long modifyRecode(RecodeModifyFormDto recodeModifyFormDto) {
        Recode recode = recodeRepository.findById(recodeModifyFormDto.getId()).orElseThrow(IllegalArgumentException::new);
        recode.getPerformance().forEach(performanceRepository::delete);
        recode.modifyRecode(recodeModifyFormDto);
        return recode.getId();
    }

    @Override
    public RecodeModifyFormDto getRecode(Long id) {
        Recode recode = recodeRepository.findByIdWithPerformance(id);
        RecodeModifyFormDto recodeModifyFormDto = new RecodeModifyFormDto();
        recodeModifyFormDto.setRecodeDto(recode);
        return recodeModifyFormDto;
    }

    @Transactional
    @Override
    public void deleteRecode(Long id) {
        recodeRepository.deleteById(id);
    }
}
