package workout.calendar;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import workout.calendar.domain.dto.recode.RecodeMonthListDto;
import workout.calendar.domain.dto.recode.RecodeTotalDto;
import workout.calendar.repository.RecodeRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@Transactional
public class RecodeRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecodeRepository recodeRepository;

    @Test
    public void testMember() {
        YearMonth thisMonth = YearMonth.now();
        LocalDate startDate = thisMonth.atDay(1);
        LocalDate endDate = thisMonth.atEndOfMonth();

        // 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 이번 달의 시작일과 종료일을 문자열로 변환
        String startDateStr = startDate.format(formatter);
        String endDateStr = endDate.format(formatter);

        List<RecodeTotalDto> monthTotalWeight = recodeRepository.getTotalWeight(1L, startDateStr, endDateStr);
        RecodeMonthListDto recodeMonthListDto = new RecodeMonthListDto();
        recodeMonthListDto.setRecodeMonthDto(monthTotalWeight);
        System.out.println("recodeMonthListDto = " + recodeMonthListDto);
    }
}
