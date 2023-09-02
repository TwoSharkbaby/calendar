package workout.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workout.calendar.domain.Date;
import workout.calendar.domain.entity.ScheduleEntity;
import workout.calendar.repository.ScheduleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recode")
@RequiredArgsConstructor
public class recodeController {

    private final ScheduleRepository scheduleRepository;

    @GetMapping
    public String main(HttpServletRequest request, Date date, Model model) {
        Calendar calendar = Calendar.getInstance();
        Date calendarDate;
        if (date.getDate().equals("") && date.getMonth().equals("")) {
            date = new Date(String.valueOf(calendar.get(Calendar.YEAR)), String.valueOf(calendar
                    .get(Calendar.MONTH)), String.valueOf(calendar.get(Calendar.DATE)), null, null);
        }

        Map<String, Integer> todayInfo = date.todayInfo(date);
        List<Date> dateList = new ArrayList<Date>();
        List<ScheduleEntity> scheduleList = scheduleRepository.getList(date);
        ScheduleEntity[][] scheduleEntities = new ScheduleEntity[32][4];
        if (!scheduleList.isEmpty()) {
            int j = 0;
            for (int i = 0; i < scheduleList.size(); i++) {
                int intDate = Integer.parseInt(
                        String.valueOf(
                                scheduleList.get(i).getDate()).substring(
                                String.valueOf(scheduleList.get(i).getDate()).length() - 2,
                                String.valueOf(scheduleList.get(i).getDate()).length()));
                if (i > 0) {
                    int dateBefore = Integer.parseInt(String.valueOf(scheduleList.get(i - 1).getDate())
                            .substring(String.valueOf(scheduleList.get(i - 1).getDate()).length() - 2,
                                    String.valueOf(scheduleList.get(i - 1).getDate()).length()));
                    if (dateBefore == intDate) {
                        j = j + 1;
                        scheduleEntities[intDate][j] = scheduleList.get(i);
                    } else {
                        j = 0;
                        scheduleEntities[intDate][j] = scheduleList.get(i);
                    }
                } else {
                    scheduleEntities[intDate][j] = scheduleList.get(i);
                }
            }
        }
        for (int i = todayInfo.get("startDay"); i <= todayInfo.get("endDay"); i++) {
            ScheduleEntity[] scheduleEntities1 = new ScheduleEntity[4];
            scheduleEntities1 = scheduleEntities[i];
            if (i == todayInfo.get("today")) {
                calendarDate = new Date(String.valueOf(date.getYear()), String.valueOf(date.getMonth()),
                        String.valueOf(i), "today", scheduleEntities1);
            } else {
                calendarDate = new Date(String.valueOf(date.getYear()), String.valueOf(date.getMonth()),
                        String.valueOf(i), "normalDate", scheduleEntities1);
            }
            dateList.add(calendarDate);
        }
        int index = 7 - dateList.size() % 7;
        if (dateList.size() % 7 != 0){
            for (int i = 0; i < index; i++){
                calendarDate = new Date(null, null, null, null, null);
                dateList.add(calendarDate);
            }
        }
        model.addAttribute("dateList", dateList);
        model.addAttribute("todayInfo", todayInfo);
        System.out.println("dateList = " + dateList);
        System.out.println("todayInfo = " + todayInfo);
        return "recode/home";
    }
}
