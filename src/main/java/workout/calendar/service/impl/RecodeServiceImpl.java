package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.auth.PrincipalDetails;
import workout.calendar.domain.dto.RecodeDto;
import workout.calendar.domain.dto.RecodeModifyFormDto;
import workout.calendar.domain.dto.RecodeResisterFormDto;
import workout.calendar.domain.entity.Recode;
import workout.calendar.repository.RecodeRepository;
import workout.calendar.service.RecodeService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecodeServiceImpl implements RecodeService {

    private final RecodeRepository recodeRepository;

    @Transactional
    @Override
    public Long createRecode(RecodeResisterFormDto recodeResisterFormDto) {
        Recode recode = new Recode();
        recode.setRecode(recodeResisterFormDto);
        Long id = recodeRepository.save(recode).getId();
        return id;
    }

    @Override
    public List<Recode> getRecodeAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long id = principalDetails.getUser().getId();
        List<Recode> recodeList = recodeRepository.findByUserId(id);
        return recodeList;
    }

    @Transactional
    @Override
    public Long modifyRecode(RecodeModifyFormDto recodeModifyFormDto) {
        Recode recode = recodeRepository.findById(recodeModifyFormDto.getId()).orElseThrow(IllegalArgumentException::new);
        recode.modifyRecode(recodeModifyFormDto);
        return recode.getId();
    }

    @Override
    public RecodeDto getRecode(Long id) {
        Recode recode = recodeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        RecodeDto recodeDto = new RecodeDto();
        //recodeDto.setMemo(recode.getMemo());
        recodeDto.setId(recode.getId());
        return recodeDto;
    }

    @Transactional
    @Override
    public void deleteRecode(Long id) {
        recodeRepository.deleteById(id);
    }
}
