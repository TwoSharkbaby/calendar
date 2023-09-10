package workout.calendar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.auth.PrincipalDetails;
import workout.calendar.domain.dto.recode.*;
import workout.calendar.domain.entity.Performance;
import workout.calendar.domain.entity.Recode;
import workout.calendar.repository.PerformanceRepository;
import workout.calendar.repository.RecodeRepository;
import workout.calendar.service.RecodeService;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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

    @Override
    public RecodeMonthListDto getMonthRecode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS"))) {
            return null;
        } else {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long id = principalDetails.getUser().getId();

            YearMonth thisMonth = YearMonth.now();
            LocalDate startDate = thisMonth.atDay(1);
            LocalDate endDate = thisMonth.atEndOfMonth();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String startDateStr = startDate.format(formatter);
            String endDateStr = endDate.format(formatter);

            List<RecodeTotalDto> monthTotalWeight = recodeRepository.getTotalWeight(id, startDateStr, endDateStr);
            RecodeMonthListDto recodeMonthListDto = new RecodeMonthListDto();
            recodeMonthListDto.setRecodeMonthDto(monthTotalWeight);

            return recodeMonthListDto;
        }
    }

    @Override
    public RecodeYearListDto getYearRecode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS"))) {
            return null;
        } else {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long id = principalDetails.getUser().getId();

            Year thisYear = Year.now();
            LocalDate startOfYear = thisYear.atDay(1);
            LocalDate endOfYear = thisYear.atDay(1).plusYears(1).minusDays(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startOfYearStr = startOfYear.format(formatter);
            String endOfYearStr = endOfYear.format(formatter);

            List<RecodeTotalDto> monthTotalWeight = recodeRepository.getTotalWeight(id, startOfYearStr, endOfYearStr);
            RecodeYearListDto recodeYearListDto = new RecodeYearListDto();
            recodeYearListDto.setRecodeYearDto(monthTotalWeight);
            return recodeYearListDto;
        }
    }
}
